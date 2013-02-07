package davenkin.step2_ugly;

import davenkin.BankService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 2/7/13
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class UglyBankService implements BankService {
    private DataSource dataSource;
    private UglyBankDao uglyBankDao;
    private UglyInsuranceDao uglyInsuranceDao;

    public UglyBankService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void transfer(int fromId, int toId, int amount) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            uglyBankDao.withdraw(fromId, amount, connection);
            uglyInsuranceDao.deposit(toId, amount, connection);

            connection.commit();
        } catch (Exception e) {
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void setUglyBankDao(UglyBankDao uglyBankDao) {
        this.uglyBankDao = uglyBankDao;
    }

    public void setUglyInsuranceDao(UglyInsuranceDao uglyInsuranceDao) {
        this.uglyInsuranceDao = uglyInsuranceDao;
    }
}
