<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.eshop.entity.Product" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Product Page</title>
</head>
<body>
<h1>ALL PRODUCTS PAGE</h1>
<ul>
    <%-- List productsAll from ProductAllController --%>
    <c:forEach var="product" items="${productsAll}">
        <li>
            <a href="./product.do?id=${product.id}">${product.name}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>