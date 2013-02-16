package davenkin.step3_connection_holder;

import davenkin.DataSourceUtils;
import davenkin.TestFixture;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class ConnectionHolderBankServiceTest extends TestFixture
{

    @Test
    public void transferSuccess() throws SQLException
    {
        BasicDataSource dataSource = DataSourceUtils.createDataSource();
        ConnectionHolderBankService connectionHolderBankService = new ConnectionHolderBankService(dataSource);
        connectionHolderBankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));

    }

    @Test
    public void transferFailure() throws SQLException
    {
        BasicDataSource dataSource = DataSourceUtils.createDataSource();
        ConnectionHolderBankService connectionHolderBankService = new ConnectionHolderBankService(dataSource);

        int toNonExistId = 3333;
        connectionHolderBankService.transfer(1111, toNonExistId, 200);

        assertEquals(1000, getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));

    }
}
