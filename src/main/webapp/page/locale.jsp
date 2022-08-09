<%--
  Created by IntelliJ IDEA.
  User: ERIK
  Date: 28-Apr-22
  Time: 9:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en_US'}" scope="session"/>
<fmt:setBundle basename="translations/translations" scope="session"/>

<div>
    <a class="btn btn-primary"
       href="${pageContext.request.contextPath}/controller?command=changeLanguage&lang=en">EN</a>
    <a class="btn btn-primary"
       href="${pageContext.request.contextPath}/controller?command=changeLanguage&lang=ru">RU</a>
</div>

