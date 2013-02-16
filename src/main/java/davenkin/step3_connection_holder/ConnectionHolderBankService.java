package davenkin.step3_connection_holder;

import davenkin.BankService;

import javax.sql.DataSource;

public class ConnectionHolderBankService implements BankService
{
    private TransactionManager transactionManager;
    private ConnectionHolderBankDao connectionHolderBankDao;
    private ConnectionHolderInsuranceDao connectionHolderInsuranceDao;

    public ConnectionHolderBankService(DataSource dataSource)
    {
        transactionManager = new TransactionManager(dataSource);
        connectionHolderBankDao = new ConnectionHolderBankDao(dataSource);
        connectionHolderInsuranceDao = new ConnectionHolderInsuranceDao(dataSource);

    }

    public void transfer(int fromId, int toId, int amount)
    {
        try
        {
            transactionManager.start();
            connectionHolderBankDao.withdraw(fromId, amount);
            connectionHolderInsuranceDao.deposit(toId, amount);
            transactionManager.commit();
        } catch (Exception e)
        {
            transactionManager.rollback();
        } finally
        {
            transactionManager.close();
        }
    }
}
