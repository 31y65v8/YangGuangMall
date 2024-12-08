package org.wxl.ygmall.web.servlet.manager;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.wxl.ygmall.service.ReportService;
import org.wxl.ygmall.domain.Report;
import java.util.List;

public class ShowReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 获取前端传来的年份和月份
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        System.out.println("Year: " + year + ", Month: " + month);
        // 如果没有选择年份或月份，使用当前日期作为默认值
        if (year == null || month == null) {
            year = String.valueOf(java.time.Year.now().getValue());
            month = String.valueOf(java.time.LocalDate.now().getMonthValue());
        }
        ReportService reportService = new ReportService();
        try {
            // 获取商品销量与销售额排名
            List<Report> salesReport = reportService.getMonthlySalesReportByYearAndMonth(year, month);
            // 获取商品类别销量与销售额排名
            List<Report> categoryReport = reportService.getMonthlyCategorySalesReportByYearAndMonth(year, month);

            // 将查询结果存入请求属性
            request.setAttribute("salesReport", salesReport);
            request.setAttribute("categoryReport", categoryReport);
         
            System.out.println("Sales Report: " + salesReport);
            System.out.println("Category Report: " + categoryReport);


            // 转发请求到销售报表页面
            request.getRequestDispatcher("/admin/report/report.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("数据库查询失败");
        }
    }
}
