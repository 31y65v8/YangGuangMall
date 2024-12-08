package org.wxl.ygmall.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.wxl.ygmall.domain.Report;
import org.wxl.ygmall.utils.DataSourceUtils;

public class ReportDao {

 // 根据年份和月份获取商品销量与销售额
    public List<Report> getMonthlySalesReportByYearAndMonth(String year, String month) throws SQLException {
        String sql = "SELECT p.id AS product_id, p.name AS products_name, SUM(oi.buynum) AS total_sales, " +
                     "SUM(oi.buynum * p.price) AS total_revenue " +
                     "FROM orders o " +
                     "JOIN orderitem oi ON o.id = oi.order_id " +
                     "JOIN products p ON oi.product_id = p.id " +
                     "WHERE o.paystate != 0 " +  // 只统计已支付的订单
                     "AND YEAR(o.pay_time) = ? AND MONTH(o.pay_time) = ? " +
                     "GROUP BY p.id " +
                     "ORDER BY total_revenue DESC";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new ResultSetHandler<List<Report>>() {
            public List<Report> handle(java.sql.ResultSet rs) throws SQLException {
                List<Report> reports = new ArrayList<>();
                while (rs.next()) {
                    Report report = new Report();
                    report.setProductId(rs.getString("product_id"));  // 根据数据库定义，产品ID应该是String类型
                    report.setProductName(rs.getString("products_name"));
                    report.setTotalSales(rs.getInt("total_sales"));
                    report.setTotalRevenue(rs.getDouble("total_revenue"));
                    reports.add(report);
                }
                return reports;
            }
        }, year, month);
    }


 // 根据年份和月份获取商品类别销量与销售额
    public List<Report> getMonthlyCategorySalesReportByYearAndMonth(String year, String month) throws SQLException {
        String sql = "SELECT p.category, SUM(oi.buynum) AS total_sales, " +
                     "SUM(oi.buynum * p.price) AS total_revenue " +
                     "FROM orders o " +
                     "JOIN orderitem oi ON o.id = oi.order_id " +
                     "JOIN products p ON oi.product_id = p.id " +         
                     "WHERE o.paystate != 0 " +  // 只统计已支付的订单
                     "AND YEAR(o.pay_time) = ? AND MONTH(o.pay_time) = ? " +
                     "GROUP BY p.category " +
                     "ORDER BY total_revenue DESC";

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new ResultSetHandler<List<Report>>() {
            public List<Report> handle(java.sql.ResultSet rs) throws SQLException {
                List<Report> reports = new ArrayList<>();
                while (rs.next()) {
                    Report report = new Report();
                    report.setCategoryName(rs.getString("category"));  // category 字段已经存在
                    report.setTotalSales(rs.getInt("total_sales"));
                    report.setTotalRevenue(rs.getDouble("total_revenue"));
                    reports.add(report);
                }
                return reports;
            }
        }, year, month);
    }

}
