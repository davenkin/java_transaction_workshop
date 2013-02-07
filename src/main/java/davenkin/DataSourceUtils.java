package davenkin;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 2/5/13
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
import org.apache.commons.dbcp.BasicDataSource;

public class DataSourceUtils
{

    private static final BasicDataSource dataSource = new BasicDataSource();
    static {
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUsername("SA");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:hsqldb:hsql://localhost/bank");
        dataSource.setMaxActive(3);
        dataSource.setMaxIdle(2);
        dataSource.setInitialSize(2);
    }

    public static BasicDataSource createDataSource()
    {

        return dataSource;
    }



}