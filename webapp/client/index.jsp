<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>ygmall</title>
       <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>  <!-- 引入 jQuery -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
    
   <script type="text/javascript">
   
	   console.log("Document is ready."); // 确认文档加载完成
	    console.log("logBrowse function is defined:", typeof logBrowse); 
	    function logBrowse(productId) {
	        console.log("logBrowse called for product ID: " + productId); // 调试信息
	        jQuery.ajax({
	            type: 'POST',
	            url: '${pageContext.request.contextPath}/logbrowse',
	            data: { productId: productId },
	            success: function(response) {
	                console.log('浏览记录保存成功');
	                window.location.href = '${pageContext.request.contextPath}/findProductById?id=' + productId;
	            },
	            error: function(xhr, status, error) {
	                console.error("浏览记录保存失败：" + error);
	                window.location.href = '${pageContext.request.contextPath}/findProductById?id=' + productId;
	            }
	        });
	    }


	    // 使用事件委托绑定点击事件
	   jQuery(document).on('click', '.product-link', function() {
    var productId = jQuery(this).data('product-id');
    console.log("Product link clicked. Product ID: " + productId); // 确认点击事件是否触发
    logBrowse(productId);
});

	

</script>

</head>

<body class="main">
    <%@ include file="head.jsp" %>
    <%@ include file="menu_search.jsp" %>

    

    <!-- 商品展示部分 -->
    <div id="divpagecontent">
        <div class="product-list">
            <c:forEach items="${products}" var="product">
                <div class="product-item">
                    <a href="javascript:void(0);" class="product-link" data-product-id="${product.id}">
                    <img src="${pageContext.request.contextPath}${product.imgurl}" alt="${product.name}" width="150" height="150"/>
                    </a>

                    <div class="product-info">
                        <a href="javascript:void(0);" class="product-link" data-product-id="${product.id}">
                        <h3>${product.name}</h3>
                        <p>售价：￥${product.price}</p>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
