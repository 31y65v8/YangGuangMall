package org.wxl.ygmall.web.servlet.manager;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.wxl.ygmall.service.BrowselogService;
import org.wxl.ygmall.domain.Browselog;
import java.util.List;

public class ShowLogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取用户输入的查询条件
        String username = request.getParameter("username");

        BrowselogService browseLogService = new BrowselogService();
        List<Browselog> logs = null;

        try {
            if (username != null && !username.isEmpty()) {
                // 按用户名查询浏览记录
                logs = browseLogService.getBrowseLogsByUsername(username);
                }
            else {
                // 查询全部浏览记录
                logs = browseLogService.getAllBrowseLogs();
            }

            if (logs == null || logs.isEmpty()) {
                // 如果没有查询到结果，弹出提示框
                request.setAttribute("message", "请输入正确名称！");
            } else {
                request.setAttribute("browseLogs", logs);
            }

            // 转发到 JSP 页面
            request.getRequestDispatcher("/admin/log/browselog.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("数据库查询失败");
        }
    }
}
