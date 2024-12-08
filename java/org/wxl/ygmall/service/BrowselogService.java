package org.wxl.ygmall.service;

import org.wxl.ygmall.domain.Browselog;
import org.wxl.ygmall.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.SQLException;
import java.util.List;

public class BrowselogService {

	 // 根据用户名查询浏览记录
    public List<Browselog> getBrowseLogsByUsername(String username) throws SQLException {
        String sql = "SELECT ub.id AS browse_log_id, " +
                     "u.username, " +
                     "p.name AS product_name, " +
                     "ub.product_id, " +
                     "ub.browse_time " +
                     "FROM user_browse_log ub " +
                     "JOIN user u ON ub.user_id = u.id " +
                     "JOIN products p ON ub.product_id = p.id " +
                     "WHERE u.username = ?";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        List<Browselog> logs = runner.query(sql, new BeanListHandler<>(Browselog.class), username);
        // 输出查询结果
        if (logs != null && !logs.isEmpty()) {
            System.out.println("Query returned " + logs.size() + " records:");
            for (Browselog log : logs) {
                System.out.println(log);
            }
        } else {
            System.out.println("No records found for username: " + username);
        }

        return logs;
    }

   

 // 获取所有浏览记录
    public List<Browselog> getAllBrowseLogs() throws SQLException {
        String sql = "SELECT ub.id AS browse_log_id, " +
                     "u.username, " +
                     "p.name AS product_name, " +
                     "ub.product_id, " +
                     "ub.browse_time " +
                     "FROM user_browse_log ub " +
                     "JOIN user u ON ub.user_id = u.id " +
                     "JOIN products p ON ub.product_id = p.id";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanListHandler<>(Browselog.class));
    }
}
