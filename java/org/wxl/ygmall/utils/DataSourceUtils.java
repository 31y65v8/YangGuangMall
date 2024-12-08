package org.wxl.ygmall.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {

    // 数据源初始化，确保 C3P0 配置正确加载
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    // 静态块确保加载数据库驱动
    static {
        try {
            // 显式加载 MySQL JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found.");
        }

        // 配置数据库连接池（避免使用外部的 XML 配置）
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ygmall?useSSL=false&serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("wxl123456");
        try {
			dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // 根据需要设置其他 C3P0 配置
        dataSource.setTestConnectionOnCheckin(true);
        dataSource.setMaxPoolSize(15);
        dataSource.setMinPoolSize(3);
        dataSource.setInitialPoolSize(3);
        dataSource.setAcquireIncrement(3);
        dataSource.setMaxIdleTime(300); // 设置空闲连接的最大空闲时间(可选)
    }

    // 获取 DataSource 对象
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获取数据库连接
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection con = tl.get();
        if (con == null) {
            con = dataSource.getConnection();
            tl.set(con);
        }
        return con;
    }

    /**
     * 开启事务
     * @throws SQLException
     */
    public static void startTransaction() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.setAutoCommit(false); // 关闭自动提交
        }
    }

    /**
     * 完成事务，释放并关闭连接
     * @throws SQLException
     */
    public static void releaseAndCloseConnection() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            try {
            	 // 如果 autoCommit 是 true，先关闭它
                if (con.getAutoCommit()) {
                    con.setAutoCommit(false);  // 关闭自动提交，进入手动提交模式
                }

                con.commit();  // 提交事务
            } finally {
                // 恢复 autoCommit 设置为 true
                con.setAutoCommit(true);
                tl.remove();   // 移除线程本地存储的连接
                con.close();   // 关闭连接
            }
        }
    }


    /**
     * 发生异常，事务回滚
     * @throws SQLException
     */
    public static void rollback() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            try {
            	 // 如果 autoCommit 是 true，先关闭它
                if (con.getAutoCommit()) {
                    con.setAutoCommit(false);  // 关闭自动提交，进入手动提交模式
                }

                con.rollback(); // 回滚事务
            } finally {
                // 恢复 autoCommit 设置为 true
                con.setAutoCommit(true);
            }
        }
    }

}
