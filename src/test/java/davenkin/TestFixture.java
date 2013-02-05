package davenkin;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.After;
import org.junit.Before;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 2/5/13
 * Time: 10:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestFixture {

    protected final BasicDataSource dataSource = DataSourceUtils.createDataSource();

    @Before
    public void setUp() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO BANK_ACCOUNT VALUES (1234, 500000);");
        statement.execute("INSERT INTO INSURANCE_ACCOUNT VALUES (5678, 100000);");
        statement.close();
        connection.close();
    }

    @After
    public void cleanUp() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM BANK_ACCOUNT;");
        statement.execute("DELETE FROM INSURANCE_ACCOUNT;");
        statement.close();
        connection.close();
    }
}
