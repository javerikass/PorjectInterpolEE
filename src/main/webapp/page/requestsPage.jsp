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
<%--<jsp:include page="/src/main/webapp/page/header.jsp"/>--%>
<jsp:include page="/page/header.jsp"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Requests</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.css">
</head>
<body>
<div>
</div>
    <c:forEach var="request" items="${requestScope.allDisapprovedRequests}">
        <form action="${pageContext.request.contextPath}/controller?command=approveRequest" method="post">
            <input name="id" value="${request.id}" hidden/>
            <input name="firstName" value="${request.firstName}"/>
            <input name="lastName" value="${request.lastName}"/>
            <input name="age" value="${request.age}"/>
            <input name="gender" value="${request.gender}"/>
            <input name="nationality" value="${request.nationality}"/>
            <input name="details" value="${request.details}"/>
            <input name="reward" value="${request.reward}"/>
            <input name="status" value="${request.status}"/>

            <fmt:message key="request.approved" var="approved"/>
            <input type="radio" name="approved" value="true"><span>${approved}</span>

            <fmt:message key="request.delete" var="delete"/>
            <input type="radio" name="approved" value="false"><span>${delete}</span>

            <fmt:message key="request.confirm" var="confirm"/>
            <button type="submit">${confirm}</button>
        </form>
    </c:forEach>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
</body>
</html>
