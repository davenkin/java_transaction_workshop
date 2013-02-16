package davenkin.step2_ugly;

import davenkin.TestFixture;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 2/7/13
 * Time: 8:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class UglyBankServiceTest extends TestFixture{

    @Test
    public void transferSuccess() throws SQLException {
        UglyBankDao failureBankDao = new UglyBankDao();
        UglyInsuranceDao failureInsuranceDao = new UglyInsuranceDao();

        UglyBankService bankService = new UglyBankService(dataSource);
        bankService.setUglyBankDao(failureBankDao);
        bankService.setUglyInsuranceDao(failureInsuranceDao);

        bankService.transfer(1111, 2222,200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));
    }

    @Test
    public void transferFailure() throws SQLException {
        UglyBankDao failureBankDao = new UglyBankDao();
        UglyInsuranceDao failureInsuranceDao = new UglyInsuranceDao();

        UglyBankService bankService = new UglyBankService(dataSource);
        bankService.setUglyBankDao(failureBankDao);
        bankService.setUglyInsuranceDao(failureInsuranceDao);

        int toNonExistId = 3333;
        bankService.transfer(1111, toNonExistId,200);

        assertEquals(1000,getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));
    }

}
