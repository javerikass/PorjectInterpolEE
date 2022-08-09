<%--
  Created by IntelliJ IDEA.
  User: ERIK
  Date: 28-Apr-22
  Time: 9:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/page/header.jsp"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.css">
</head>
<body>
<h1>
    You did something wrong, please <a href="${pageContext.request.contextPath}/"> Try again</a>.
</h1>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
</body>
</html>
