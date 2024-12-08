<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.wxl.ygmall.domain.User"%>
<script type="text/javascript">
//退出确认框
function confirm_logout() {   
    var msg = "您确定要退出登录吗？";   
    if (confirm(msg)==true){   
    return true;   
    }else{   
    return false;   
    }   
} 
</script>


<div id="divhead">
	<table cellspacing="0" class="headtable">
		<tr>
		 <td style="width: auto; vertical-align: middle;">
        <!-- 网站的icon图标 -->
        <img src="${pageContext.request.contextPath}/client/images/icon.jpg" alt="网站图标" style="width: 40px; height: 40px; margin-right: 10px;">
        
        <!-- “阳光商城”文字 -->
        <span style="color: #FFA500; font-size: 40px; font-weight: bold;">阳光商城</span>
      </td>
			
			<td style="text-align:right">
				
				<% 
				User user = (User) request.getSession().getAttribute("user");
				if(null == user){
					// 未登录状态，显示登录和注册链接
				%>
				<img src="${pageContext.request.contextPath}/client/images/login.jpg" width="23" height="20" style="margin-bottom:-4px" />
				   &nbsp;
				 <a href="${pageContext.request.contextPath}/client/login.jsp">登录</a>
				| <a href="${pageContext.request.contextPath}/client/register.jsp">注册</a>							
				<% 	
				}else{
					 // 已登录状态，显示购物车、个人中心、注销和欢迎信息
				%>
				<img src="${pageContext.request.contextPath}/client/images/cart.png" width="35" height="32" style="margin-bottom:-4px" />
				   &nbsp;
				  <a href="${pageContext.request.contextPath}/client/cart.jsp">购物车</a> 
				<img src="${pageContext.request.contextPath}/client/images/center.png" width="35" height="32" style="margin-bottom:-4px" />
				   &nbsp;
				<a href="${pageContext.request.contextPath}/myAccount">个人中心</a> 
				<img src="${pageContext.request.contextPath}/client/images/logout.png" width="27" height="24" style="margin-bottom:-4px" />
				   &nbsp;
				<a href="${pageContext.request.contextPath}/logout" onclick="javascript:return confirm_logout()">退出登录</a>
				<br><br><br> 
                <span style="font-size: 20px; color: #FFA500;">${user.username} ，欢迎！</span>

				<% 	
				}
				%>			
			</td>		
		</tr>
	</table>
</div>