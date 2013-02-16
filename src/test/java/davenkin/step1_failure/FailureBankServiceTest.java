package davenkin.step1_failure;

import davenkin.BankFixture;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 2/5/13
 * Time: 10:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class FailureBankServiceTest extends BankFixture
{
    @Test
    public void transferSuccess() throws SQLException
    {
        FailureBankDao failureBankDao = new FailureBankDao(dataSource);
        FailureInsuranceDao failureInsuranceDao = new FailureInsuranceDao(dataSource);

        FailureBankService bankService = new FailureBankService(dataSource);
        bankService.setFailureBankDao(failureBankDao);
        bankService.setFailureInsuranceDao(failureInsuranceDao);

        bankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));

    }

    @Test
    public void transferFailure() throws SQLException
    {
        FailureBankDao failureBankDao = new FailureBankDao(dataSource);
        FailureInsuranceDao failureInsuranceDao = new FailureInsuranceDao(dataSource);

        FailureBankService bankService = new FailureBankService(dataSource);
        bankService.setFailureBankDao(failureBankDao);
        bankService.setFailureInsuranceDao(failureInsuranceDao);

        int toNonExistId = 3333;
        bankService.transfer(1111, toNonExistId, 200);

        assertEquals(1000, getInsuranceAmount(2222));
        assertEquals(800, getBankAmount(1111));
    }
}
