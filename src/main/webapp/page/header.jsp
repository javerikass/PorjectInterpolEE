<%--
  Created by IntelliJ IDEA.
  User: ERIK
  Date: 28-Apr-22
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/page/locale.jsp"/>
<c:if test="${sessionScope.sessionUser eq null}">
    <div class="container">
        <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
            <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
                <fmt:message key="header.home" var="home"/>
                <li><a href="${pageContext.request.contextPath}/page/interpol.jsp" class="nav-link px-2 link-dark">${home}</a>
                </li>
                <fmt:message key="header.newsDTO" var="news"/>
                <li><a href="${pageContext.request.contextPath}/controller?command=showAllNews" class="nav-link px-2 link-dark">${news}</a>
                </li>
                <fmt:message key="header.wanted" var="wanted"/>
                <li><a href="${pageContext.request.contextPath}/controller?command=showAllWantedRequests&offset=0"
                       class="nav-link px-2 link-dark">${wanted}</a></li>
                <fmt:message key="header.missing" var="missing"/>
                <li><a href="${pageContext.request.contextPath}/controller?command=showAllMissingRequests&offset=0"
                       class="nav-link px-2 link-dark">${missing}</a></li>
            </ul>
            <div class="col-md-3 text-end">
                <fmt:message key="header.login" var="login"/>
                <a href="${pageContext.request.contextPath}loginPage.jsp"
                   class="btn btn-outline-primary me-2">${login}</a>
                <fmt:message key="header.sign-up" var="signUp"/>
                <a href="${pageContext.request.contextPath}registrationPage.jsp" class="btn btn-primary">${signUp}</a>
            </div>
        </header>
    </div>
</c:if>

<c:if test="${sessionScope.sessionUser.role.name() eq 'USER' or sessionScope.sessionUser.role.name() eq 'ADMIN'}">
    <div class="container">
        <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
            <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">



                <fmt:message key="header.home" var="home"/>
                <li><a href="${pageContext.request.contextPath}/page/interpol.jsp" class="nav-link px-2 link-dark">${home}</a></li>



                <fmt:message key="header.newsDTO" var="news"/>
                <li><a href="${pageContext.request.contextPath}/controller?command=showAllNews" class="nav-link px-2 link-dark">${news}</a>
                </li>
                <fmt:message key="header.wanted" var="wanted"/>
                <li><a href="${pageContext.request.contextPath}/controller?command=showAllWantedRequests&offset=0"
                       class="nav-link px-2 link-dark">${wanted}</a></li>
                <fmt:message key="header.missing" var="missing"/>
                <li><a href="${pageContext.request.contextPath}/controller?command=showAllMissingRequests&offset=0"
                       class="nav-link px-2 link-dark">${missing}</a></li>

                <c:if test="${sessionScope.sessionUser.role.name() eq 'USER' or sessionScope.sessionUser.role.name() eq 'ADMIN'}">
                    <fmt:message key="header.createRequest" var="createRequest"/>
                    <li><a href="${pageContext.request.contextPath}/page/createRequest.jsp"
                           class="nav-link px-2 link-dark">${createRequest}</a></li>
                </c:if>

                <c:if test="${sessionScope.sessionUser.role.name() eq 'ADMIN'}">
                    <fmt:message key="header.requestsDTO" var="requests"/>
                    <li><a href="${pageContext.request.contextPath}/controller?command=showAllDisapprovedRequest"
                           class="nav-link px-2 link-dark">${requests}</a></li>
                </c:if>

                <c:if test="${sessionScope.sessionUser.role.name() eq 'ADMIN'}">
                    <fmt:message key="header.createNews" var="createNews"/>
                    <li><a href="${pageContext.request.contextPath}/controller?command=showAllTags"
                           class="nav-link px-2 link-dark">${createNews}</a></li>
                </c:if>

                <c:if test="${sessionScope.sessionUser.role.name() eq 'ADMIN'}">
                    <fmt:message key="header.users" var="users"/>
                    <li><a href="${pageContext.request.contextPath}/controller?command=showAllUsers"
                           class="nav-link px-2 link-dark">${users}</a></li>
                </c:if>
            </ul>
            <div class="col-md-3 text-end">
                <form action="${pageContext.request.contextPath}/controller?command=logout" method="post">
                    <input class="btn btn-outline-primary me-2" value="<fmt:message key="login.logout"/>"
                           type="submit">
                </form>
            </div>
        </header>
    </div>
</c:if>
</header>

