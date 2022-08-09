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
    <title>Missing</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.css">

    <style>
        .text-inline {
            display: inline;
        }
    </style>
</head>

<body>

<c:forEach begin="0" end="${requestScope.pages-1}" var="page">
    <a href="${pageContext.request.contextPath}/controller?command=showAllWantedRequests&offset=${page*5}">${page+1}</a>
</c:forEach>

<div class="text-inline">
    <c:forEach var="request" items="${requestScope.missingRequests}">
        <form action="${pageContext.request.contextPath}/controller?command=deleteRequest" method="post">
            <div class="text-inline">
<%--                <img src="https://edguru.ru/uploads/images/00/01/87/2016/04/26/avatar_64x64.jpg?100232"--%>
<%--                     class="card-img-top">--%>
                <h5 class="card-title">${request.firstName} ${request.lastName}</h5>
                <fmt:message key="request.missing" var="missing"/>
                <h6 class="card-subtitle mb-2 text-muted">${missing}</h6>
                <fmt:message key="request.female" var="female"/>
                <fmt:message key="request.male" var="male"/>
                <h6 class="card-subtitle mb-2">${request.gender eq 'FEMALE' ? female : male}</h6>
                <fmt:message key="request.nationality" var="nationality"/>
                <h6 class="card-subtitle mb-2">${nationality}: ${request.nationality}</h6>
                <fmt:message key="request.age" var="age"/>
                <h6 class="card-subtitle mb-2">${age}: ${request.age}</h6>
                <fmt:message key="request.reward" var="reward"/>
                <h6 class="card-subtitle mb-2">${reward}: ${request.reward}</h6>
                <fmt:message key="request.details" var="details"/>
                <h5 class="card-text">${details}: ${request.details}</h5>
                <input name="id" value="${request.id}" hidden/>
                <input name="firstName" value="${request.firstName}" hidden/>
                <input name="lastName" value="${request.lastName}" hidden/>
                <input name="age" value="${request.age}" hidden/>
                <input name="gender" value="${request.gender}" hidden/>
                <input name="nationality" value="${request.nationality}" hidden/>
                <input name="details" value="${request.details}" hidden/>
                <input name="reward" value="${request.reward}" hidden/>
                <input name="status" value="${request.status}" hidden/>
                <c:if test="${sessionScope.sessionUser.role.name() eq 'ADMIN'}">
                    <fmt:message key="request.delete" var="delete"/>
                    <button type="submit">${delete}</button>
                </c:if>
            </div>
        </form>
    </c:forEach>
</div>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
</body>
</html>
