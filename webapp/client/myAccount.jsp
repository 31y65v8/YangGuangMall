<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>ygmall</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
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
</head>
<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td width="25%">
        <table width="100%" border="0" cellspacing="0" style="margin-top:30px">
            <tr>
    <td class="listtitle">个人中心</td>
</tr>

<tr>
    <td class="listtd">
        <button onclick="window.location.href='${pageContext.request.contextPath}/findOrderByUser'" 
                style="background-color: white; color: #808080; border: none; outline: none; padding: 5px 10px; font-size: 15px; cursor: pointer;">
            历史订单
        </button>
    </td>
</tr>

            
        </table>
    </td>

				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						
					</div>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="2" class="upline">
									<tr>
										<td style="text-align:right; width:20%">用户邮箱：</td>
										<td style="width:40%; padding-left:20px">${user.email }</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td style="text-align:right">用户名：</td>
										<td style="padding-left:20px">${user.username }</td>
										<td>&nbsp;</td>
									</tr>
									
								</table>
								
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
