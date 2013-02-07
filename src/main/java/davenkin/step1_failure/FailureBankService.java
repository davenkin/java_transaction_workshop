package davenkin.step1_failure;

import davenkin.BankService;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 2/5/13
 * Time: 10:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class FailureBankService implements BankService{
    private BankDao bankDao;
    private InsuranceDao insuranceDao;
    private BasicDataSource dataSource;

    public FailureBankService(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void transfer(int fromId, int toId, int amount) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            bankDao.withdraw(fromId, amount);
            insuranceDao.deposit(toId, amount);

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

    public void setBankDao(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    public void setInsuranceDao(InsuranceDao insuranceDao) {
        this.insuranceDao = insuranceDao;
    }
}
