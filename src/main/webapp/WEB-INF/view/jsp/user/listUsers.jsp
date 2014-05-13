<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: panser
  Date: 4/24/14
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="listUsers.title" /></title>
    <%--<security:csrfMetaTags />--%>
</head>
<body>
    <c:set var="root" value="${pageContext.request.contextPath}"/>
<div>
    <h2><spring:message code="listUsers.header" /></h2>

    <span style="float: right">
        <security:authorize access="!isAuthenticated()">
            <c:url value="/login" var="url"/>
            <a href="<c:out value='${url}'/>">
                <spring:message code="listUsers.url.login" />
            </a>
<%--
            <a href="${root}/login">
            <spring:message code="listUsers.url.login" />
            </a>
            <spring:url value="/login" var="url"/>
            <a href="${url}">
                <spring:message code="listUsers.url.login" />
            </a>
--%>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <p>
                Hello <security:authentication property="principal.username" />,
                <a href="<c:url value="/user/userInfo"/>">
                    <spring:message code="listUsers.url.userInfo" />
                </a>
            </p>
            <p>
<%--
                <a href="<c:url value="/logout"/>">
                    <spring:message code="listUsers.url.logout" />
                </a>
--%>
                <!-- csrf for log out-->
                <a href="javascript:formSubmit()">
                    <spring:message code="listUsers.url.logout" />
                </a>
                <script>
                    function formSubmit() {
                        document.getElementById("logoutForm").submit();
                    }
                </script>
                <c:url value="/logout" var="logoutUrl" />
                <form action="${logoutUrl}" method="post" id="logoutForm">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </p>
        </security:authorize>
        <br/>
        <a href="?lang=en">en</a>
        |
        <a href="?lang=ru">ru</a>
    </span>

    <p>${flashMessageEdit}</p>
    <p>${flashMessageAdd}</p>
    <p>${flashMessageDelete}</p>

    <table>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><img src="<s:url value="${user.avatorPath}"/>" width="48" height="48" /></td>
                <td><c:out value="${user.id}"/> </td>
                <td>
                    <%--<a href="<s:url value="edit/${user.login}"/>" />--%>
                    <c:out value="${user.login}"/>
                </td>
                <td><c:out value="${user.email}"/> </td>
                <td><c:out value="${user.password}"/> </td>
                <security:authorize url="/user/edit/">
                    <td>
                        <form method="get" action="edit/${user.login}">
                            <input type="submit" value="<spring:message code="listUsers.button.edit" />"/>
                        </form>
                        <%--<input type="submit" value="<spring:message code="listUsers.button.edit" />" onclick="window.location='${user.login}/edit';" />--%>
                    </td>
                </security:authorize>
                <security:authorize url="/user/delete/">
                    <td>
                        <%--<sf:form method="DELETE" action="delete/${user.login}" modelAttribute="${user}">--%>
                        <sf:form method="DELETE" action="delete/${user.login}" cssClass="deleteForm">
                            <input type="submit" value="<spring:message code="listUsers.button.delete" />"/>
                        </sf:form>
                    </td>
                </security:authorize>
            </tr>
        </c:forEach>
    </table>
<%--
    <form method="get" action="add">
        <input type="submit" value="<spring:message code="listUsers.button.add" />"/>
    </form>
--%>
    <security:authorize url="/user/add">
        <input type="submit" value="<spring:message code="listUsers.button.add" />" onclick="window.location='add';" />
    </security:authorize>
</div>
</body>
</html>
