<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<HTML>
<HEAD>
	<meta http-equiv="Content-Language" content="zh-cn">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/admin/css/Style.css" rel="stylesheet" type="text/css" />
	<script language="javascript" src="${pageContext.request.contextPath}/admin/js/public.js"></script>
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
	<script type="text/javascript">
	function sendDeliveryNotice(orderId) {
	    if (confirm("确认发送发货提醒吗？")) {
	        // 使用 jQuery Ajax 提交请求
	        $.ajax({
	            url: "${pageContext.request.contextPath}/delievernotice",  // 发送请求的地址
	            type: "POST",  // 请求方式
	            data: { orderId: orderId },  // 传递的参数
	            success: function(response) {
	            	console.log(response);  // 打印响应，查看内容
	                // 服务器返回的响应是 JSON 格式
	                if (response.success) {
	                    alert(response.message);  // 成功时提示消息
	                    
	                    // 获取该订单的状态元素，更新其状态
	                    // 假设你有订单状态显示的元素，可以通过 CSS 选择器或 ID 获取
	                    var statusElement = $('#orderStatus_' + orderId);  // 假设你使用了orderId作为元素的id的一部分
	                    if (statusElement) {
	                        statusElement.text('已发货');  // 更新为新的状态
	                    }

	                 // 禁用该订单的发货提醒按钮
	                    var btnElement = $('#deliveryNoticeBtn_' + orderId);  // 使用 orderId 构造按钮的 ID
	                    if (btnElement) {
	                        btnElement.prop('disabled', true);  // 禁用按钮
	                    }
	                } else {
	                    alert(response.message);  // 失败时提示消息
	                }
	            },
	            error: function(xhr, status, error) {
	                alert("发货提醒发送失败，请检查网络连接。");
	            }
	        });
	        return false;  // 阻止页面跳转
	    }
	    return false;  // 如果用户取消，则阻止跳转
	}

	</script>
</HEAD>
<body>
	<br />
	<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/findOrderByManyCondition" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
						<strong>查 询 条 件</strong>
					</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									订单编号</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="id" size="15" value="" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									收件人：</td>
								<td class="ta_01" bgColor="#ffffff">
									<input type="text" name="receiverName" size="15" value="" id="Form1_userName" class="bg" />
								</td>
							</tr>
							<tr>
								<td width="100" height="22" align="center" bgColor="#f5fafe" class="ta_01"></td>
								<td class="ta_01" bgColor="#ffffff">
									<font face="宋体" color="red"> &nbsp;</font>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<br /><br />
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<button type="submit" id="search" name="search" value="&#26597;&#35810;" class="button_view">
										&#26597;&#35810;
									</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
						<strong>订 单 列 表</strong>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="right"></td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
    <td align="center" width="20%">订单编号</td>
    <td align="center" width="10%">收件人</td>
    <td align="center" width="15%">地址</td>
    <td align="center" width="10%">QQ邮箱</td>
    <td width="10%" align="center">总价</td>
    <td width="9%" align="center">所属用户</td>
    <td width="10%" align="center">订单状态</td>
    <td width="7%" align="center">查看</td>
    <td width="7%" align="center">删除</td>
    <td width="10%" align="center">发货提醒</td> 
</tr>
<c:forEach items="${orders}" var="order">
    <tr onmouseover="this.style.backgroundColor = 'white'" onmouseout="this.style.backgroundColor = '#F5FAFE';">
        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">${order.id}</td>
        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${order.receiverName}</td>
        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">${order.receiverAddress}</td>
        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${order.receiverMail}</td>
        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">${order.money}</td>
        <td width="8%" align="center">${order.user.username}</td>
        <td width="10%" align="center" id="orderStatus_${order.id}">
    <c:choose>
        
        <c:when test="${order.paystate == 0}">
            未支付
        </c:when>
        
        <c:when test="${order.paystate == 1 }">
            已支付
        </c:when>
        
        
        <c:when test="${order.paystate == 2}">
            已发货
        </c:when>
        <c:when test="${order.paystate == 3}">
            已收货
        </c:when>
    </c:choose>
</td>

      
        <td align="center" style="HEIGHT: 22px">
            <a href="${pageContext.request.contextPath}/findOrderById?id=${order.id}&type=admin">
                <img src="${pageContext.request.contextPath}/admin/images/view.png" width="16" height="16" border="0" style="CURSOR: hand">
            </a>
        </td>
        
        <td align="center" style="HEIGHT: 22px">
            <c:if test="${order.paystate!=0 }">
                <a href="${pageContext.request.contextPath}/delOrderById?id=${order.id}&type=admin" onclick="javascript:return o_del()">
                    <img src="${pageContext.request.contextPath}/admin/images/delete.png" width="16" height="16" border="0" style="CURSOR: hand">
                </a>
            </c:if> 
            <c:if test="${order.paystate==0 }">
                <a href="javascript:alert('不能删除未支付订单')">
                    <img src="${pageContext.request.contextPath}/admin/images/delete.png" width="16" height="16" border="0" style="CURSOR: hand">
                </a>
            </c:if>
        </td>

        <td align="center" style="HEIGHT: 22px">
            <c:if test="${order.paystate == 1}">
                  <button type="button" id="deliveryNoticeBtn_${order.id}" onclick="return sendDeliveryNotice('${order.id}');">
    <img src="${pageContext.request.contextPath}/admin/images/sendnotice.png" alt="发货提醒" style="width: 16px; height: 16px;">
</button>

            </c:if>

        </td>
    </tr>
</c:forEach>

						</table>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>