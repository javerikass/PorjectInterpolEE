<%--
  Created by IntelliJ IDEA.
  User: ERIK
  Date: 28-Apr-22
  Time: 9:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/page/header.jsp"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Request</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.css">
</head>
<body class="bg-light">
<div class="container">
    <div class="row g-5">
        <div class="col-md-5 col-lg-4 order-md-last">

            <div class="py-5 text-center">
                <fmt:message key="header.createRequest" var="createRequest"/>
                <h2>${createRequest}</h2>
            </div>

            <form method="post" action="${pageContext.request.contextPath}/controller?command=createRequest">

                <div class="col-sm-6">
                    <fmt:message key="request.firstName" var="firstName"/>
                    <label for="firstName" class="form-label">${firstName}</label>
                    <input type="text" class="form-control" id="firstName" placeholder="${firstName}" name="firstName">
                </div>

                <div class="col-sm-6">
                    <fmt:message key="request.lastName" var="lastName"/>
                    <label for="lastName" class="form-label">${lastName}</label>
                    <input type="text" class="form-control" id="lastName" placeholder="${lastName}" name="lastName">
                </div>

                <div class="col-12">
                    <fmt:message key="request.age" var="age"/>
                    <label for="age" class="form-label">${age}</label>
                    <div class="input-group has-validation">
                        <input type="number" class="form-control" id="age" name="age" placeholder="${age}"
                               max="150">
                    </div>
                </div>

                <div class="col-12">
                    <fmt:message key="request.nationality" var="nationality"/>
                    <label for="nationality" class="form-label">${nationality}</label>
                    <input type="text" class="form-control" id="nationality" name="nationality"
                           placeholder="${nationality}">
                </div>

                <div class="col-12">
                    <fmt:message key="request.reward" var="reward"/>
                    <label for="reward" class="form-label">${reward}</label>
                    <input type="number" class="form-control" name="reward" id="reward" placeholder="${reward}">
                </div>

                <div class="col-md-5">
                    <fmt:message key="request.gender" var="gender"/>
                    <label for="gender" class="form-label">${gender}</label>
                    <select class="form-select" name="gender" id="gender">
                        <fmt:message key="request.choose" var="choose"/>
                        <option value="">${choose}</option>
                        <fmt:message key="request.male" var="male"/>
                        <option value="MALE">${male}</option>
                        <fmt:message key="request.female" var="female"/>
                        <option value="FEMALE">${female}</option>
                    </select>
                </div>

                <div class="mb-3">
                    <fmt:message key="request.details" var="details"/>
                    <label for="details" class="form-label">${details}</label>
                    <textarea class="form-control" id="details" placeholder="${details}"
                              name="details"></textarea>
                </div>

                <div class="col-md-4">
                    <fmt:message key="request.status" var="status"/>
                    <label for="status" class="form-label">${status}</label>
                    <select class="form-select" id="status" name="status">
                        <fmt:message key="request.choose" var="choose"/>
                        <option value="">${choose}</option>
                        <fmt:message key="request.wanted" var="wanted"/>
                        <option value="WANTED">${wanted}</option>
                        <fmt:message key="request.missing" var="missing"/>
                        <option value="MISSING">${missing}</option>
                    </select>
                </div>
                <%--                    <input name="user" value="${sessionScope.sessionUser.username}" hidden>--%>
                <fmt:message key="request.send" var="send"/>
                <button class="w-100 btn btn-primary btn-lg" type="submit">${send}</button>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
</body>
</html>
