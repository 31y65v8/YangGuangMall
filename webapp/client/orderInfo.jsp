<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://www.ygmall.org/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ygmall</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    function confirmPayment(orderId, money) {
        if (confirm("确认支付吗？")) {
            // 发送 AJAX 请求
            jQuery.post("${pageContext.request.contextPath}/orderstate", { 
                orderid: orderId 
            }, function(response) {
                if (response.success) {
                    alert("支付成功！");
                    window.location.href = "${pageContext.request.contextPath}/client/myAccount.jsp";  
                } else {
                    alert("支付失败，请稍后再试！");
                }
            }, 'json');
        }
    }
</script>
</head>
<body class="main">
	<p:user/>
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td>
					
					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td>
											<p>订单编号:${order.id}</p>
										</td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td width="10%">序号</td>
													<td width="40%">商品名称</td>
													<td width="10%">价格</td>
													<td width="10%">数量</td>
													<td width="10%">小计</td>
												</tr>
											</table> 
											<c:forEach items="${order.orderItems}" var="item" varStatus="vs">
												<table width="100%" border="0" cellspacing="0">
													<tr>
														<td width="10%">${vs.count }</td>
														<td width="40%">${item.p.name}</td>
														<td width="10%">${item.p.price }</td>
														<td width="10%">${item.buynum }</td>
														<td width="10%">${item.buynum*item.p.price }</td>
													</tr>
												</table>
											</c:forEach>
											<table cellspacing="1" class="carttable">
												<tr>
													<td style="text-align:right; padding-right:40px;"><font
														style="color:#FF0000">合计：&nbsp;&nbsp;${order.money}</font>
													</td>
												</tr>
											</table>
											<p>
												收货地址：${order.receiverAddress }&nbsp;&nbsp;&nbsp;&nbsp;<br />
												收货人：&nbsp;&nbsp;&nbsp;&nbsp;${order.receiverName }&nbsp;&nbsp;&nbsp;&nbsp;<br />
												QQ邮箱：${order.receiverMail }&nbsp;&nbsp;&nbsp;&nbsp;
											</p>
											<hr>
											<c:if test="${order.paystate != 1 }">
											<p style="text-align:right">
                                            <a href="javascript:void(0);" 
                                             onclick="confirmPayment('${order.id}', '${order.money}')"
                                             style="background-color: #FF5733; color: white; padding: 10px 20px; font-size: 18px; text-align: center; text-decoration: none; border-radius: 5px; display: inline-block;">
                                             付款
                                             </a>
                                             </p>
											</c:if>
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
