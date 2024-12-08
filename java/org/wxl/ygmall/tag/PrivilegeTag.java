package org.wxl.ygmall.tag;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.wxl.ygmall.domain.User;
//当用户未登录时，自动重定向到权限错误页面
public class PrivilegeTag extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {//通过 HttpSession 检查用户是否登录，未登录则进行重定向。
		PageContext context = (PageContext) this.getJspContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		HttpServletResponse response = (HttpServletResponse) context.getResponse();
		//获取会话中的 user 属性
		User user = (User) context.getSession().getAttribute("user");
		if (user == null) {
			//重定向到权限错误页面
			response.sendRedirect(request.getContextPath() + "/client/error/privilege.jsp");
		}
	}
}

