package org.wxl.ygmall.service;

import java.sql.SQLException;
import java.util.List;
import org.wxl.ygmall.dao.ReportDao;
import org.wxl.ygmall.domain.Report;

public class ReportService {

    private ReportDao reportDao = new ReportDao();

    // 根据年份和月份获取商品销量与销售额
    public List<Report> getMonthlySalesReportByYearAndMonth(String year, String month) throws SQLException {
        return reportDao.getMonthlySalesReportByYearAndMonth(year, month);
    }

    // 根据年份和月份获取商品类别销量与销售额
    public List<Report> getMonthlyCategorySalesReportByYearAndMonth(String year, String month) throws SQLException {
        return reportDao.getMonthlyCategorySalesReportByYearAndMonth(year, month);
    }
}
