package davenkin.step1_failure;

import davenkin.DataSourceUtils;
import davenkin.TestFixture;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 2/5/13
 * Time: 10:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class FailureBankServiceTest extends TestFixture {
    @Test
    public void test() throws SQLException {
        System.out.println("ddd");
        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery("SELECT * FROM BANK_ACCOUNT;");
        resultSet.next();
        System.out.println(resultSet.getInt(1));
    }
}
