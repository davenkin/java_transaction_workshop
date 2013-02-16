package davenkin.step5_transaction_proxy;

import davenkin.BankFixture;
import davenkin.BankService;
import davenkin.step3_connection_holder.TransactionManager;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class BareBankServiceTest extends BankFixture
{
    @Test
    public void transferSuccess() throws SQLException
    {
        TransactionEnabledProxyManager transactionEnabledProxyManager = new TransactionEnabledProxyManager(new TransactionManager(dataSource));
        Object bankService = new BareBankService(dataSource);
        BankService proxyBankService = (BankService) transactionEnabledProxyManager.proxyFor(bankService);
        proxyBankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));

    }

    @Test
    public void transferFailure() throws SQLException
    {
        TransactionEnabledProxyManager transactionEnabledProxyManager = new TransactionEnabledProxyManager(new TransactionManager(dataSource));
        Object bankService = new BareBankService(dataSource);
        BankService proxyBankService = (BankService) transactionEnabledProxyManager.proxyFor(bankService);

        int toNonExistId = 3333;
        proxyBankService.transfer(1111, toNonExistId, 200);

        assertEquals(1000, getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));
    }
}
