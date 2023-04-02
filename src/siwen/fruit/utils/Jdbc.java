package siwen.fruit.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @projectName: LearnJava
 * @package: com.siwen.fruit.tool
 * @className: GetConnection
 * @author: 749291
 * @description: TODO
 * @date: 2/7/2023 11:06 PM
 * @version: 1.0
 */

public class Jdbc {
    private static DataSource dataSource = null;

    private static ThreadLocal<Connection> connectionThreadLocal = null;

    private Jdbc() { }

    public static Connection getConnection() throws Exception {
        if (dataSource == null) {
            Properties properties = DbProperties.getProperties();
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        }

        if (connectionThreadLocal == null) {
            connectionThreadLocal = new ThreadLocal<>();
        }

        Connection connection = connectionThreadLocal.get();
        if (connection == null || connection.isClosed()) {
            if (connection != null) {
                connectionThreadLocal.remove();
            }
            connection = dataSource.getConnection();
            connectionThreadLocal.set(connection);
        }
        return connection;
    }

    public static void freeConnection() throws SQLException {
        Connection connection = connectionThreadLocal.get();
        if (connection != null) {
            connection.close();
            connectionThreadLocal.remove();
        }
    }
}
