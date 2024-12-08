<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>商品列表</title>
	<%--导入css --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>  <!-- 引入 jQuery -->
	<script type="text/javascript">
        // 记录浏览记录的AJAX请求
        function logBrowse(productId) {
            jQuery.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/logbrowse',  // 后端Servlet路径
                data: { 
                    productId: productId,
                    //userId: '${sessionScope.user_id}'  // 用户ID从Session中获取
                },
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

        jQuery(document).ready(function() {
            // 绑定点击事件，使用事件委托方式
            jQuery(document).on('click', '.product-link', function() {
                var productId = jQuery(this).data('product-id');
                console.log("Product link clicked. Product ID: " + productId); // 确认点击事件是否触发
                logBrowse(productId);  // 调用记录浏览记录的函数
            });
        });
    </script>
</head>
<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td>
					
					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>${bean.category}</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${bean.totalCount}种商品
								<hr />
								

								<table cellspacing="0" class="goodslist">
									<tr>
										<c:forEach items="${bean.ps}" var="p" varStatus="vs">
											<td>
                                                <div class="divgoodspic">
                                                    <p>
                                                        <!-- 商品图片和链接 -->
                                                        <a href="javascript:void(0);" class="product-link" data-product-id="${p.id}">
                                                            <img src="${pageContext.request.contextPath}${p.imgurl}" width="115" height="129" border="0" />
                                                        </a>
                                                    </p>
                                                </div>
                                                <div class="divlisttitle">
                                                    <a href="javascript:void(0);" class="product-link" data-product-id="${p.id}">
                                                        商品名： ${p.name}<br />售价：￥${p.price}
                                                    </a>
                                                </div>
                                            </td>
										</c:forEach>
									</tr>
								</table>
								<div class="pagination">
									<ul>
										<c:if test="${bean.currentPage!=1}">
											<li class="disablepage_p">
												<a class="disablepage_a" href="${pageContext.request.contextPath}/showProductByPage?currentPage=${bean.currentPage-1}&category=${bean.category}"></a>
											</li>
										</c:if>
										<c:if test="${bean.currentPage==1}">
											<li class="disablepage_p2"></li>
										</c:if>
										<c:forEach begin="1" end="${bean.totalPage}" var="pageNum">
											<c:if test="${pageNum==bean.currentPage}">
												<li class="currentpage">${pageNum }</li>
											</c:if>
											<c:if test="${pageNum!=bean.currentPage}">
												<li><a href="${pageContext.request.contextPath}/showProductByPage?currentPage=${pageNum}&category=${bean.category}">${pageNum}</a>
												</li>
											</c:if>
										</c:forEach>

										<c:if test="${bean.currentPage==bean.totalPage||bean.totalPage==0}">
											<li class="disablepage_n2"></li>
										</c:if>
										<c:if test="${bean.currentPage!=bean.totalPage&&bean.totalPage!=0}">
											<li class="disablepage_n">
												<a class="disablepage_a" href="${pageContext.request.contextPath}/showProductByPage?currentPage=${bean.currentPage+1}&category=${bean.category}"></a>
											</li>
										</c:if>
									</ul>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	
</body>
</html>
