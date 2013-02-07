package davenkin.step1_failure;

import davenkin.DataSourceUtils;
import davenkin.TestFixture;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 2/5/13
 * Time: 10:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class FailureBankServiceTest extends TestFixture {
    @Test
    public void transferSuccess() throws SQLException {
        BankDao bankDao = new BankDao(dataSource);
        InsuranceDao insuranceDao = new InsuranceDao(dataSource);

        FailureBankService bankService = new FailureBankService(dataSource);
        bankService.setBankDao(bankDao);
        bankService.setInsuranceDao(insuranceDao);

        bankService.transfer(1234, 5678,200);

        assertEquals(800,getBankAmount(1234));
        assertEquals(1200, getInsuranceAmount(5678));

    }

    @Test
    public void transferFailure() throws SQLException {
        BankDao bankDao = new BankDao(dataSource);
        InsuranceDao insuranceDao = new InsuranceDao(dataSource);

        FailureBankService bankService = new FailureBankService(dataSource);
        bankService.setBankDao(bankDao);
        bankService.setInsuranceDao(insuranceDao);

        bankService.transfer(1234, 56780,200);

        assertEquals(1000, getInsuranceAmount(5678));
        assertEquals(1000,getBankAmount(1234));

    }
}
