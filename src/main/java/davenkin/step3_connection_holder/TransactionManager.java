package davenkin.step3_connection_holder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager
{
    private DataSource dataSource;

    public TransactionManager(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public final void start() throws SQLException
    {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
    }

    public final void commit() throws SQLException
    {
        Connection connection = getConnection();
        connection.commit();
    }

    public final void rollback()
    {
        Connection connection = null;
        try
        {
            connection = getConnection();
            connection.rollback();

        } catch (SQLException e)
        {
            throw new RuntimeException("Couldn't rollback on connection[" + connection + "].", e);
        }
    }

    public final void close()
    {
        Connection connection = null;
        try
        {
            connection = getConnection();
            connection.setAutoCommit(true);
            connection.setReadOnly(false);
            connection.close();
            SingleThreadConnectionHolder.removeConnection(dataSource);
        } catch (SQLException e)
        {
            throw new RuntimeException("Couldn't close connection[" + connection + "].", e);
        }
    }

    private Connection getConnection() throws SQLException
    {
        return SingleThreadConnectionHolder.getConnection(dataSource);
    }
}
