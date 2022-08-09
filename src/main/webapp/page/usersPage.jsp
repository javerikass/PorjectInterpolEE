<%--
  Created by IntelliJ IDEA.
  User: ERIK
  Date: 28-Apr-22
  Time: 9:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/page/header.jsp"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.css">
</head>
<body>
<c:forEach var="user" items="${requestScope.allUsers}">
    <form action="${pageContext.request.contextPath}/controller?command=deleteUsers" method="post">
        <input name="id" value="${user.id}"/>
        <input name="username" value="${user.username}"/>
        <input name="role" value="${user.role}"/>
        <fmt:message key="request.delete" var="delete"/>
        <button type="submit" >${delete}</button>
    </form>
</c:forEach>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
</body>
</html>
