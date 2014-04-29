<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
</head>
<body>
<div>
    <h2><spring:message code="listUsers.header" /></h2>

    <span style="float: right">
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
                <td><img src="<s:url value="${user.photoName}"/>" width="48" height="48" /></td>
                <td><c:out value="${user.id}"/> </td>
                <td>
                    <%--<a href="<s:url value="edit/${user.login}"/>" />--%>
                    <c:out value="${user.login}"/>
                </td>
                <td><c:out value="${user.email}"/> </td>
                <td><c:out value="${user.password}"/> </td>
                <td>
                    <form method="get" action="edit/${user.login}">
                        <input type="submit" value="<spring:message code="listUsers.button.edit" />"/>
                    </form>
                    <%--<input type="submit" value="<spring:message code="listUsers.button.edit" />" onclick="window.location='${user.login}/edit';" />--%>
                </td>
                <td>
                    <sf:form method="DELETE" action="delete/${user.login}" cssClass="deleteForm">
                        <input type="submit" value="<spring:message code="listUsers.button.delete" />"/>
                    </sf:form>
                </td>
            </tr>
        </c:forEach>
    </table>
<%--
    <form method="get" action="add">
        <input type="submit" value="<spring:message code="listUsers.button.add" />"/>
    </form>
--%>
    <input type="submit" value="<spring:message code="listUsers.button.add" />" onclick="window.location='add';" />

</div>
</body>
</html>
