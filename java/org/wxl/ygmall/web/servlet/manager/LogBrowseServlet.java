package org.wxl.ygmall.web.servlet.manager;

import org.wxl.ygmall.dao.BrowselogDao;
import org.wxl.ygmall.domain.Browselog;
import org.wxl.ygmall.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;


public class LogBrowseServlet extends HttpServlet {
    private BrowselogDao browselogDao = new BrowselogDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取商品ID和商品名称
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        // 打印调试信息
        System.out.println("Received request: productId=" + productId + ", productName=" + productName);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // 获取当前用户
        // 打印调试信息
        if (user != null) {
            System.out.println("Current user: " + user.getUsername());
        } else {
            System.out.println("User not logged in.");
        }
        if (user != null && productId != null && !productId.isEmpty() ) {
            // 创建新的浏览记录
            Browselog browseLog = new Browselog();
            browseLog.setuser_id(user.getId()); // 使用int类型
            browseLog.setproduct_id(productId);  // 设置商品ID
            browseLog.setbrowse_time(LocalDateTime.now()); // 使用当前时间
            // 打印调试信息
            System.out.println("Creating new Browselog: " + browseLog);
            try {
                // 将浏览记录保存到数据库
                browselogDao.addBrowseLog(browseLog);
                System.out.println("Browse log saved successfully.");
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception e) {
                e.printStackTrace();
                // 打印调试信息
                System.out.println("Error saving browse log: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            // 打印调试信息
            System.out.println("Invalid request: user or product information is missing.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
