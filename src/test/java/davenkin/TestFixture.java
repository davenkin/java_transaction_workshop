package davenkin;

import org.junit.Before;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 2/5/13
 * Time: 10:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestFixture
{

    protected final DataSource dataSource = DataSourceFactory.createDataSource();

    @Before
    public void setUp() throws SQLException
    {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("DROP TABLE BANK_ACCOUNT IF EXISTS");
        statement.execute("DROP TABLE INSURANCE_ACCOUNT IF EXISTS");
        statement.execute("CREATE TABLE BANK_ACCOUNT (\n" +
                "BANK_ID INT,\n" +
                "BANK_AMOUNT INT,\n" +
                "PRIMARY KEY(BANK_ID)\n" +
                ");");

        statement.execute("CREATE TABLE INSURANCE_ACCOUNT (\n" +
                "INSURANCE_ID INT,\n" +
                "INSURANCE_AMOUNT INT,\n" +
                "PRIMARY KEY(INSURANCE_ID)\n" +
                ");");

        statement.execute("INSERT INTO BANK_ACCOUNT VALUES (1111, 1000);");
        statement.execute("INSERT INTO INSURANCE_ACCOUNT VALUES (2222, 1000);");

        statement.close();
        connection.close();
    }

    protected int getBankAmount(int bankId) throws SQLException
    {
        Connection connection = dataSource.getConnection();
        PreparedStatement selectStatement = connection.prepareStatement("SELECT BANK_AMOUNT FROM BANK_ACCOUNT WHERE BANK_ID = ?");
        selectStatement.setInt(1, bankId);
        ResultSet resultSet = selectStatement.executeQuery();
        resultSet.next();
        int amount = resultSet.getInt(1);
        resultSet.close();
        selectStatement.close();
        connection.close();
        return amount;
    }

    protected int getInsuranceAmount(int insuranceId) throws SQLException
    {
        Connection connection = dataSource.getConnection();
        PreparedStatement selectStatement = connection.prepareStatement("SELECT INSURANCE_AMOUNT FROM INSURANCE_ACCOUNT WHERE INSURANCE_ID = ?");
        selectStatement.setInt(1, insuranceId);
        ResultSet resultSet = selectStatement.executeQuery();
        resultSet.next();
        int amount = resultSet.getInt(1);
        resultSet.close();
        selectStatement.close();
        connection.close();
        return amount;
    }

}
