package org.wxl.ygmall.web.servlet.client;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.wxl.ygmall.domain.Product;
/**
 * 购物车内容变更
 */
public class ChangeCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到商品id
		String id = request.getParameter("id");
		// 得到要修改的数量
		int count = Integer.parseInt(request.getParameter("count"));
		// 从session中获取购物车.
		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
		Product p = new Product();
		p.setId(id);
		if (count != 0) {
			cart.put(p, count);
		} else {
			cart.remove(p);
		}
		response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
		return;
	}
}
