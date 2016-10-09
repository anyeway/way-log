package com.way.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.Driver;
import com.nd.gaea.core.config.SystemConfig;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by way on 2016/9/29.
 * <p/>
 * todo 修改为任意连接池或者c3p0
 */
public class ConnectionFactory {

    private static interface Singleton {
        final ConnectionFactory INSTANCE = new ConnectionFactory();
    }

    private final DataSource dataSource;

    /**
     * 不知道为什么这个类没注册，手工注册
     */
    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException var1) {
            throw new RuntimeException("Can\'t register driver!");
        }
    }

    private ConnectionFactory() {
        Properties properties = new Properties();
        properties.setProperty("user", SystemConfig.instance.getProperty("log.jdbc.username"));
        properties.setProperty("password", SystemConfig.instance.getProperty("log.jdbc.password")); // or get properties from some configuration file

        GenericObjectPool pool = new GenericObjectPool();
        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                SystemConfig.instance.getProperty("log.jdbc.url"), properties
        );
        new PoolableConnectionFactory(
                connectionFactory, pool, null, "SELECT 1", 3, false, false, Connection.TRANSACTION_READ_COMMITTED
        );

        this.dataSource = new PoolingDataSource(pool);
    }

    public static Connection getDatabaseConnection() throws SQLException {
        return Singleton.INSTANCE.dataSource.getConnection();
    }
}
