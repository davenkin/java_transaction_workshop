package davenkin.step4_transaction_template;

import davenkin.TestFixture;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class TransactionTemplateBankServiceTest extends TestFixture
{
    @Test
    public void transferSuccess() throws SQLException
    {
        TransactionTemplateBankService transactionTemplateBankService = new TransactionTemplateBankService(dataSource);
        transactionTemplateBankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));

    }

    @Test
    public void transferFailure() throws SQLException
    {
        TransactionTemplateBankService transactionTemplateBankService = new TransactionTemplateBankService(dataSource);

        int toNonExistId = 3333;
        transactionTemplateBankService.transfer(1111, toNonExistId, 200);

        assertEquals(1000, getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));
    }
}
