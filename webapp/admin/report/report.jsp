<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    	<link href="${pageContext.request.contextPath}/admin/css/Style.css" rel="stylesheet" type="text/css" />
    	
    
    <title>销售报表查询</title>
</head>
<body>
    <h2>销售报表查询</h2>
    <form action="${pageContext.request.contextPath}/showReport" method="get">
        <label for="year">年份:</label>
        <select name="year" id="year">
            <option value="2024">2024</option>
            <option value="2025">2025</option>
            <!-- 可以根据需要继续添加年份 -->
        </select>
        
        <label for="month">月份:</label>
        <select name="month" id="month">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
        </select>
        
        <input type="submit" value="查询" class="button-green">
    </form>

    <!-- 显示查询结果 -->
    <h3>商品销量与销售额排名</h3>
    <table border="1">
        <thead>
            <tr>
                <th>商品名称</th>
                <th>销量</th>
                <th>销售额</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="report" items="${salesReport}">
                <tr>
                    <td>${report.productName}</td>
                    <td>${report.totalSales}</td>
                    <td>${report.totalRevenue}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h3>商品类别销量与销售额排名</h3>
    <table border="1">
        <thead>
            <tr>
                <th>商品类别</th>
                <th>销量</th>
                <th>销售额</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="report" items="${categoryReport}">
                <tr>
                    <td>${report.categoryName}</td>
                    <td>${report.totalSales}</td>
                    <td>${report.totalRevenue}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
