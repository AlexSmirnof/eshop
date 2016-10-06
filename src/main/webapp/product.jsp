<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.eshop.entity.Product" %>
<%--comments--%>
<html>
<head>
    <meta charset="utf-8">
    <title>Product Page</title>
</head>
<body>
<h1>PRODUCT PAGE</h1>
<c:set var="productId" scope="page" value="${product.id}" />
<p>id: ${productId} or &nbsp                                  <%--EL - Expression Language--%>
       <%=((Product)request.getAttribute("product")).getId()%> <%--Scriplet--%>
</p>
<h3>name: ${product.name}</h3>
<a href="./productAddToBucket.do?id=${productId}">Add to cart</a>
<hr>
<h2>Products Cart</h2>
<ul>
     <%-- Map productInBucket from ProductAddToBucketController --%>
    <c:forEach var="productInBucket" items="${productsInBucket}">
        <li>
            <a href="./product.do?id=${productInBucket.key.id}">${productInBucket.key.name}</a>: ${productInBucket.value}
            &nbsp;[<a href="./productRemoveFromBucket.do?id=${productInBucket.key.id}&redirectToId=${productId}">X</a>]
        </li>
    </c:forEach>
</ul>
</body>
</html>