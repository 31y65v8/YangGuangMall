<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://www.ygmall.org/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>ygmall</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	<script type="text/javascript">
	//删除订单
	function o_del() {   
	    var msg = "您确定要删除该订单吗？";   
	    if (confirm(msg)==true){   
	    return true;   
	    }else{   
	    return false;   
	    }   
	} 
	</script>
	<script>
    function receiveconfirm(orderId, event) {
        // 弹出确认框，询问用户是否确认收货
        if (confirm('确认收货吗？')) {
            // 发送 POST 请求，通知服务器修改订单状态
            fetch('${pageContext.request.contextPath}/orderreceived', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: 'id=' + orderId
            })
            .then(response => response.json())
            .then(data => {
                // 如果返回的状态是成功，更新页面的订单状态
                if (data.success) {
                    // 更新订单状态文本
                    let statusElement = document.getElementById('order-status-' + orderId);
                    statusElement.textContent = '已收货';  // 更新为“已收货”
                    // 禁用或移除收货按钮
                    let linkElement = event.target;  // 获取点击的<a>元素
                    linkElement.textContent = '已收货'; // 修改按钮为已收货
                    linkElement.style.pointerEvents = 'none'; // 禁用点击
                } else {
                    alert('收货失败，请稍后重试！');
                }
            })
            .catch(error => {
                console.error('请求失败:', error);
                alert('请求失败，请稍后重试！');
            });
        }
    }
</script>
</head>
<body class="main">
	<!-- 使用了自定义标签 -->
	<p:user/>
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td width="25%">
					<table width="100%" border="0" cellspacing="0" style="margin-top:30px">
						 <tr>
    
</tr>

<tr>
    <td class="listtd">
        <button onclick="window.location.href='${pageContext.request.contextPath}/myAccount.jsp'" 
                style="background-color: white; color: #808080; border: none; outline: none; padding: 5px 10px; font-size: 15px; cursor: pointer;">
            返回个人中心
        </button>
    </td>
</tr>

						
					</table>
				</td>
				<td>
					
					<table cellspacing="0" class="infocontent">
						<tr>
							<td style="padding:20px"><p><b>我的订单</b></p>
								<p>
									共有<font style="color:#FF0000" >${orders.size()}</font>订单
								</p>
								<table width="100%" border="0" cellspacing="0" class="tableopen">
									<tr>
										<td bgcolor="#ADD8E6" class="tableopentd01">订单号</td>
<td bgcolor="#ADD8E6" class="tableopentd01">收件人</td>
<td bgcolor="#ADD8E6" class="tableopentd01">订单时间</td>
<td bgcolor="#ADD8E6" class="tableopentd01">状态</td>
<td bgcolor="#ADD8E6" class="tableopentd01">操作</td>

									</tr>
									<c:forEach items="${orders}" var="order">
										<tr>
											<td class="tableopentd02">${order.id}</td>
											<td class="tableopentd02">${order.receiverName }</td>
											<td class="tableopentd02">${order.ordertime}</td>
											<td class="tableopentd02" id="order-status-${order.id}">
                                                ${order.paystate == 0 ? "未支付" : (order.paystate == 1 ? "已支付" : (order.paystate == 2 ? "已发货" : "已收货"))}
                                             </td>

											<td class="tableopentd03">
												<a href="${pageContext.request.contextPath}/findOrderById?id=${order.id}">详情</a>&nbsp;&nbsp;
												<c:if test="${order.paystate == 2}">
                                                <a href="javascript:void(0);" onclick="receiveconfirm('<c:out value="${order.id}"/>', event)">收货</a>
                                                 </c:if>
												
											</td>
										</tr>
									</c:forEach>
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
