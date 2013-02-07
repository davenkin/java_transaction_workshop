package davenkin;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;

import java.sql.*;

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
        statement.execute("DELETE FROM BANK_ACCOUNT;");
        statement.execute("DELETE FROM INSURANCE_ACCOUNT;");

        statement.execute("INSERT INTO BANK_ACCOUNT VALUES (1234, 1000);");
        statement.execute("INSERT INTO INSURANCE_ACCOUNT VALUES (5678, 1000);");
        statement.close();
        connection.close();
    }

    protected int getBankAmount(int bankId) throws SQLException {
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

    protected int getInsuranceAmount(int insuranceId) throws SQLException {
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
