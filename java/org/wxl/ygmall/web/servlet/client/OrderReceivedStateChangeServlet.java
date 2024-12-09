package org.wxl.ygmall.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wxl.ygmall.service.OrderService;

public class OrderReceivedStateChangeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取订单号数据
        String orderid = request.getParameter("id");  // 参数名应与前端一致
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 判断订单号是否为空
        if (orderid != null && !orderid.trim().isEmpty()) {
            OrderService service = new OrderService();
            try {
                // 调用业务层方法，更新订单状态为已收货
                service.updateState2(orderid);

                // 返回成功的 JSON 响应
                response.getWriter().write("{\"success\": true}");
            } catch (Exception e) {
                e.printStackTrace();
                // 返回失败的 JSON 响应
                response.getWriter().write("{\"success\": false, \"message\": \"修改订单状态失败\"}");
            }
        } else {
            // 如果订单号为空，返回失败的 JSON 响应
            response.getWriter().write("{\"success\": false, \"message\": \"订单号不能为空\"}");
        }
    }
}

