<%--
  Created by IntelliJ IDEA.
  User: ERIK
  Date: 28-Apr-22
  Time: 10:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/page/header.jsp"/>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.css">
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-5 p-3">
            <form method="post" action="${pageContext.request.contextPath}/controller?command=login"
                  enctype="application/x-www-form-urlencoded">

                <div class="mb-3">
                    <label class="form-label"><fmt:message key="login.username"/></label>
                    <input class="form-control" name="username" type="text" required
                           pattern="^[A-Za-zА-Яа-я1-9]{3,20}$">
                    <span></span>
                </div>

                <div class="mb-3">
                    <label class="form-label"><fmt:message key="login.password"/></label>
                    <input class="form-control" name="password" type="password" required pattern="^\w{2,64}$">
                    <c:if test="${requestScope.errorLogin.startsWith('I')}">
                        <p>${requestScope.errorLogin}</p>
                    </c:if>
                </div>

                <input class="btn btn-dark" type="submit" value="<fmt:message key="login.submit"/>">

                <div class="signup_link">
                    <a href="${pageContext.request.contextPath}registrationPage.jsp"><fmt:message
                            key="header.sign-up"/></a>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
</body>
</html>
