package org.wxl.ygmall.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wxl.ygmall.domain.User;

public class MyAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//在session中查找名为“user”的会话
		User user = (User) request.getSession().getAttribute("user");
		//如果没有找到，跳转到登录页面
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/client/login.jsp");
			return;
		}
		//如果是管理员，进入后台管理系统；如果是用户进入个人中心。
		if ("管理员".equals(user.getRole())) {
			response.sendRedirect(request.getContextPath() + "/admin/login/home.jsp");
//			return;
		}else{
			response.sendRedirect(request.getContextPath() + "/client/myAccount.jsp");
//			return;
		}
	}
}
