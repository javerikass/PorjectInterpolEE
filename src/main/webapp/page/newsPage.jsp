<%--
  Created by IntelliJ IDEA.
  User: ERIK
  Date: 28-Apr-22
  Time: 9:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/page/header.jsp"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>News</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.css">
</head>
<body>
<c:forEach var="news" items="${requestScope.allNews}">
    <div class="bd-inline">
        <div class="card-body">
            <h3 class="card-title">${news.headline}</h3>
            <c:forEach var="tag" items="${news.tag}">
            <div>
                <h6 class="card-title">#${tag.tag}</h6>
            </div>
            </c:forEach>
            <p class="card-text">${news.text}</p>
            <a href="#" class="btn btn-primary">Go somewhere</a>
        </div>
    </div>
</c:forEach>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
</body>
</html>
