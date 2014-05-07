<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: panser
  Date: 5/6/14
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<security:authorize access="!isAuthenticated()">
    <a href="<c:url value="/login"/>">
        <spring:message code="userInfo.url.login" />
    </a>
</security:authorize>

<security:authorize access="isAuthenticated()">
    <b>Spring Security</b> info:
    <table>
        <tr>
            <td>principal.username</td>
            <td><b>
                <security:authentication property="principal.username" />
            </b></td>
        </tr>
    </table>
</security:authorize>
<br/>
<input type="button" class="back-button" onclick="history.back();" value="<spring:message code="button.back" />" />

</body>
</html>
