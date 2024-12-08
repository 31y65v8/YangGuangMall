<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>注册</title>
<%--导入css和js --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/form.js"></script>

</head>

<body class="main">
<!-- 1.顶部 start -->
       <%@include file="head.jsp"%>
<!-- 1.顶部  end -->

<!-- 3.用户注册  start -->
	<div id="divcontent">
		<form action="${pageContext.request.contextPath}/register" method="post" onsubmit="return checkForm();">
			<table width="850px" border="0" cellspacing="0">
				<tr>
					<td style="color: orange;"><h1>欢迎注册阳光商城！</h1>
						<table width="70%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align: right; width: 20%">QQ邮箱：</td>
								<td style="width: 40%">
								<input type="text" class="textinput"  id="email" name="email" onkeyup="checkEmail();"/>
								</td>
								<td colspan="2"><span id="emailMsg"></span><font color="#999999">请输入有效的QQ邮箱地址</font></td>
							</tr>
							<tr>
								<td style="text-align: right">用户名：</td>
								<td><input type="text" class="textinput"  id="username" name="username" onkeyup="checkUsername();"/>
								</td>
								<td colspan="2"><span id="usernameMsg"></span><font color="#999999">字母数字下划线1到10位, 不能是数字开头</font></td>
							</tr>
							<tr>
								<td style="text-align: right">密码：</td>
								<td><input type="password" class="textinput"  id="password" name="password" onkeyup="checkPassword();"/></td>
								<td><span id="passwordMsg"></span><font color="#999999">密码请设置6-16位字符</font></td>
							</tr>
							<tr>
								<td style="text-align: right">重复密码：</td>
								<td>
								<input type="password" class="textinput"  id="repassword" name="repassword" onkeyup="checkConfirm();"/>
								</td>
								<td><span id="confirmMsg"></span>&nbsp;</td>
							</tr>
						</table>


						

						<table width="70%" border="0" cellspacing="0">
    <tr>
        <td style="padding-top: 20px; text-align: center">
            <button type="submit" style="background-color: orange; color: white; padding: 10px 20px; font-size: 16px; border: none; border-radius: 5px; cursor: pointer;">
                注册
            </button>
        </td>
    </tr>
</table>

					</td>
				</tr>
			</table> 
		</form>
	</div>
	
 <!-- 3.用户注册  end -->


</body>
</html>
