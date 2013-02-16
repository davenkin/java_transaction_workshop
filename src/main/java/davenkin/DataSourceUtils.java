package davenkin;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 2/5/13
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceUtils
{

    private static final BasicDataSource dataSource = new BasicDataSource();

    static
    {
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUsername("SA");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:hsqldb:mem:bank");
    }

    public static DataSource createDataSource()
    {
        return dataSource;
    }


}