package davenkin.step5_transaction_proxy;

import davenkin.BankService;
import davenkin.step3_connection_holder.ConnectionHolderBankDao;
import davenkin.step3_connection_holder.ConnectionHolderInsuranceDao;

import javax.sql.DataSource;

public class BareBankService implements BankService
{
    private ConnectionHolderBankDao connectionHolderBankDao;
    private ConnectionHolderInsuranceDao connectionHolderInsuranceDao;

    public BareBankService(DataSource dataSource)
    {
        connectionHolderBankDao = new ConnectionHolderBankDao(dataSource);
        connectionHolderInsuranceDao = new ConnectionHolderInsuranceDao(dataSource);
    }

    public void transfer(final int fromId, final int toId, final int amount)
    {
        try
        {
            connectionHolderBankDao.withdraw(fromId, amount);
            connectionHolderInsuranceDao.deposit(toId, amount);
        } catch (Exception e)
        {
            throw new RuntimeException();
        }
    }
}
