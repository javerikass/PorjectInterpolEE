<%--
  Created by IntelliJ IDEA.
  User: ERIK
  Date: 28-Apr-22
  Time: 9:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/page/header.jsp"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create News</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.css">
</head>
<body class="bg-light">
<div class="container">
    <div class="col-md-7 col-lg-8">
        <fmt:message key="header.createNews" var="createNews"/>
        <h4 class="mb-3">${createNews}</h4>
        <form action="${pageContext.request.contextPath}/controller?command=createNews" method="post">
            <div class="row g-3">
                <div class="col-sm-6">
                    <div class="input-group">
                        <fmt:message key="newsDTO.headline" var="headline"/>
                        <label for="headline" class="input-group-text">${headline}</label>
                        <textarea id="headline" class="form-control" name="headline" type="text"
                                  aria-label="${headline}" required></textarea>

                    </div>
                    <div class="input-group">
                        <fmt:message key="newsDTO.newsText" var="newsText"/>
                        <span class="input-group-text">${newsText}</span>
                        <textarea class="form-control" name="text" type="text"
                                  aria-label="${newsText}" required></textarea>
                    </div>
                    <hr class="my-4">
                    <c:forEach var="tag" items="${requestScope.allTags}">
                        <div class="form-check">
                            <label class="form-check-label" for="tags">${tag.tag}</label>
                            <input type="checkbox" name="tag" value="${tag.tag}" id="tags">
                        </div>
                    </c:forEach>
                    <hr class="my-4">
                </div>
            </div>
            <fmt:message key="header.createNews" var="createNews"/>
            <input class="w-50 btn btn-primary btn-lg" type="submit" value="${createNews}">
        </form>

        <br>

        <form class="col-md-5" action="${pageContext.request.contextPath}/controller?command=createTag" method="post" enctype="application/x-www-form-urlencoded">
            <fmt:message key="newsDTO.tag" var="tags"/>
            <label for="tag" class="form-label">${tags}</label>
            <input name="tag" type="text" id="tag" required>
            <fmt:message key="newsDTO.createTag" var="createTag"/>
            <button class="w-50 btn btn-primary btn-lg" type="submit">${createTag}</button>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
</body>
</html>