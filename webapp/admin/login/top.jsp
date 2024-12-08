<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
BODY {
	MARGIN: 0px;
	BACKGROUND-COLOR: #ffffff
}

BODY {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TD {
	FONT-SIZE: 12px;
	COLOR: #000000
}

TH {
	FONT-SIZE: 12px;
	COLOR: #000000
}
.height1{width:100%; height:74px; }
.img_logo{
	min-width:100%;
	max-width:100%;
	height:74px;
}

</style>
<link href="${pageContext.request.contextPath}/admin/css/Style.css"
	rel="stylesheet" type="text/css">
<script>
    function confirm_logout() {
        if (confirm("确定要退出登录吗?")) {
            // 使用 AJAX 调用后端注销接口
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "${pageContext.request.contextPath}/managerlogout", true);
            xhr.onload = function() {
                if (xhr.status === 200) {
                    // 注销成功后，跳转到首页
                    window.top.location.href = "${pageContext.request.contextPath}/index.jsp";
                }
            };
            xhr.send();
            return false; // 防止链接的默认行为
        }
        return false;
    }
</script>


</HEAD>
<body>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="26" valign="bottom"
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="15%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="listtd">
                                   <a href="${pageContext.request.contextPath}/managerlogout" onclick="javascript:return confirm_logout()">退出登录</a>									</td>
								</tr>
							</table>
						</td>
						<td align="right" width="5%"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</HTML>
