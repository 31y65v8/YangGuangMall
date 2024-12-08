package org.wxl.ygmall.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wxl.ygmall.service.OrderService;

public class ChangeOrderStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得订单号数据
		String orderid = request.getParameter("orderid");
		// 设置响应内容类型为 JSON
        response.setContentType("application/json;charset=UTF-8");
        // 默认返回失败
        String jsonResponse = "{\"success\": false, \"message\": \"支付失败，请稍后再试！\"}";
		if (null != orderid) {
				OrderService service = new OrderService();
				// 根据订单号修改订单状态
				try {
					service.updateStateandPaytime(orderid);
					jsonResponse = "{\"success\": true, \"message\": \"支付成功！\"}";
				} catch (Exception e) {
					e.printStackTrace();
					jsonResponse = "{\"success\": false, \"message\": \"更新订单状态失败\"}";
				}
			}
		// 将 JSON 响应发送给前端
	    response.getWriter().write(jsonResponse);
	}
	 
}
