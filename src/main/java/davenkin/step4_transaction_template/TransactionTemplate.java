package davenkin.step4_transaction_template;

import davenkin.step3_connection_holder.TransactionManager;

import javax.sql.DataSource;

public abstract class TransactionTemplate
{
    private TransactionManager transactionManager;

    protected TransactionTemplate(DataSource dataSource)
    {
        transactionManager = new TransactionManager(dataSource);
    }

    public void doJobInTransaction()
    {
        try
        {
            transactionManager.start();
            doJob();
            transactionManager.commit();
        } catch (Exception e)
        {
            transactionManager.rollback();
        } finally
        {
            transactionManager.close();
        }
    }

    protected abstract void doJob() throws Exception;
}
