<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/admin/css/Style.css" rel="stylesheet" type="text/css" />
    <title>浏览记录</title>
    <script type="text/javascript">
        function validateForm() {
            var username = document.getElementById("username").value;
            var productName = document.getElementById("productName").value;

            if (username === "" ) {
                alert("请输入用户名进行查询！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h1>浏览记录查询</h1>
    <h2>若要查询全部浏览记录请直接点击“查询”</h2>
    <!-- 查询表单 -->
    <form method="post" action="${pageContext.request.contextPath}/showlog" onsubmit="return validateForm()">
        <label for="username">用户名：</label>
        <input type="text" id="username" name="username">
        <br>
        
        <button type="submit">查询</button>
    </form>

    <!-- 错误提示 -->
    <c:if test="${not empty message}">
        <script type="text/javascript">
            alert("${message}");
        </script>
    </c:if>

    <!-- 显示浏览记录 -->
    <c:if test="${not empty browseLogs}">
        <table border="1">
            <thead>
                <tr>
                    <th>用户名</th>
                    <th>商品名</th>
                    <th>浏览时间</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="log" items="${browseLogs}">
                    <tr>
                        <td>${log.username}</td>
                        <td>${log.product_name}</td>
                        <td>${log.browse_time}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
