<%--
  Created by IntelliJ IDEA.
  User: ERIK
  Date: 28-Apr-22
  Time: 9:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/page/header.jsp"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-5 p-3">
            <form method="post" action="${pageContext.request.contextPath}/controller?command=register" enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="command" value="registerUser">
                <div class="mb-3">
                    <label for="inputUsername" class="form-label"><fmt:message key="login.username"/></label>
                    <input name="username" type="text" class="form-control" pattern="^[A-za-z][A-Za-z0-9\._]{3,10}$" id="inputUsername" required>
                </div>
                <div class="mb-3">
                    <fmt:message key="login.password" var="password"/>
                    <label for="inputPassword" class="form-label">${password}</label>
                    <input name="password" type="password" class="form-control" pattern="^[A-za-z0-9\._]{4,16}$" id="inputPassword" required>
                </div>
                <button type="submit" class="btn btn-dark"><fmt:message key="login.submit"/></button>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
</body>
</html>
