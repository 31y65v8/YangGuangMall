<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>ygmall</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
	<script type="text/javascript">
        // 发送 Ajax 请求将商品添加到购物车
        function addToCart(productId) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "${pageContext.request.contextPath}/addCart", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var response = JSON.parse(xhr.responseText);
                    if (response.success) {
                        alert(response.message);  // 弹出成功提示
                    } else {
                        alert(response.message);  // 弹出失败提示
                    }
                }
            };

            // 发送商品ID
            xhr.send("id=" + productId);
        }
    </script>
</head>
<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td>
				 、
					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
								<table width="100%%" border="0" cellspacing="0">
									<tr>
										<td width="30%">
											<div class="divbookcover">
												<p>
													<img src="${pageContext.request.contextPath}${p.imgurl}" width="213" height="269" border="0" />
												</p>
											</div>
											 <div style="text-align:center; margin-top:10px">
                                                <button onclick="addToCart('${p.id}')" style="background-color: orange; color: white; padding: 10px 20px; text-align: center; border-radius: 5px; border: none; cursor: pointer;">
                                                    加入购物车
                                                </button>
                                            </div>
										</td>
										<td style="padding:20px 5px 5px 5px">
											<img src="${pageContext.request.contextPath }/client/images/miniicon3.gif" width="16" height="16" />
											<font class="bookname">&nbsp;${p.name}</font>
											<hr />售价：<font color="#FF0000">￥${p.price}</font>
											<hr /> 类别：${p.category }
											<hr />
											<p>
												<b>简介：</b>
											</p> ${p.description}
										</td>
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
