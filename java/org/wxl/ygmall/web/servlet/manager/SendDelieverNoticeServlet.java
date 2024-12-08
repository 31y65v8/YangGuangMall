package org.wxl.ygmall.web.servlet.manager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wxl.ygmall.domain.Order;
import org.wxl.ygmall.service.OrderService;
import org.wxl.ygmall.utils.DataSourceUtils;
import org.wxl.ygmall.utils.MailUtils;

public class SendDelieverNoticeServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应类型为 JSON
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // 获取订单ID
        String orderId = request.getParameter("orderId");
        System.out.println("Received orderId: " + orderId);  // 打印传入的 orderId

        // 创建返回的响应对象
        String jsonResponse = "{ \"success\": false, \"message\": \"发货提醒发送失败，请重试！\" }";

        // 如果订单ID为空，则直接返回错误
        if (orderId == null || orderId.isEmpty()) {
            jsonResponse = "{ \"success\": false, \"message\": \"无效的订单ID！\" }";
            response.getWriter().write(jsonResponse);
            return;
        }

        // 从数据库获取订单信息
        OrderService orderService = new OrderService();
        Order order = orderService.findOrderById(orderId);
        if (order == null) {
            jsonResponse = "{ \"success\": false, \"message\": \"找不到该订单！\" }";
            response.getWriter().write(jsonResponse);
            return;
        }

        // 处理事务和邮件发送
        try {
            // 开启事务
            DataSourceUtils.startTransaction();
            // 更新订单状态为“已发货”，即将 state 设置为 2
            orderService.updateState1(orderId);
            // 获取买家邮箱
            String buyerEmail = order.getReceiverMail();
            String subject = "您的订单已发货";
            String message = "尊敬的用户，您的订单已经发货，请查收！订单号：" + order.getId();
            // 发送邮件
            boolean success = MailUtils.sendEmail(buyerEmail, subject, message);
            if (success) {
                // 邮件发送成功，提交事务
                DataSourceUtils.releaseAndCloseConnection();
                // 返回成功响应
                jsonResponse = "{ \"success\": true, \"message\": \"发货提醒邮件已发送！\" }";
            } else {
                // 邮件发送失败，回滚事务
                DataSourceUtils.rollback();
                jsonResponse = "{ \"success\": false, \"message\": \"邮件发送失败！\" }";
            }
        } catch (SQLException e) {
            try {
                // 发生异常时回滚事务
                DataSourceUtils.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            jsonResponse = "{ \"success\": false, \"message\": \"数据库操作失败！\" }";
        }

        // 将 JSON 响应返回给前端
        response.getWriter().write(jsonResponse);
    }
}
