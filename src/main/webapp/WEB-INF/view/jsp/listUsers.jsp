<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: panser
  Date: 4/24/14
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div>
    <h2>List Users:</h2>

    <table>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><img src="<s:url value="/resources/image/photo/${user.id}/${user.photoName}"/>" width="48" height="48" /></td>
                <td><c:out value="${user.id}"/> </td>
                <td>
                    <a href="<s:url value="edit/${user.login}"/>" />
                    <c:out value="${user.login}"/>
                </td>
                <td><c:out value="${user.email}"/> </td>
                <td><c:out value="${user.password}"/> </td>
                <td>
                    <sf:form method="DELETE" action="${user.login}/delete" cssClass="deleteForm">
                        <input type="submit" value="Delete"/>
                    </sf:form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="add" >ADD USER</a>
</div>
</body>
</html>
