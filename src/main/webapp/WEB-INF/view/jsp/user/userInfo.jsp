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
    <p>
        <b>security:authentication principal</b> info:
        <table>
            <tr><td>principal.username</td><td><b><security:authentication property="principal.username" /></b></td></tr>
            <tr><td>principal.password</td><td><b><security:authentication property="principal.password" /></b></td></tr>
            <tr><td>principal.authorities</td><td><b><security:authentication property="principal.authorities" /></b></td></tr>
            <tr><td>principal.accountNonExpired</td><td><b><security:authentication property="principal.accountNonExpired" /></b></td></tr>
            <tr><td>principal.accountNonLocked</td><td><b><security:authentication property="principal.accountNonLocked" /></b></td></tr>
            <tr><td>principal.credentialsNonExpired</td><td><b><security:authentication property="principal.credentialsNonExpired" /></b></td></tr>
            <tr><td>principal.enabled</td><td><b><security:authentication property="principal.enabled" /></b></td></tr>
        </table>
    </p>
    <p>
        <b>security:authentication authorities</b> info:
        <table>
            <tr><td>authorities</td><td><b><security:authentication property="authorities" /></b></td></tr>
        </table>
    </p>
    <p>
        <b>security:authentication credentials</b> info:
        <table>
            <tr><td>credentials</td><td><b><security:authentication property="credentials" /></b></td></tr>
        </table>
    </p>
    <p>
        <b>security:authentication authorities</b> info:
        <table>
            <tr><td>details</td><td><b><security:authentication property="details" /></b></td></tr>
        </table>
    </p>
    <p>
        <b>request.getUserPrincipal()....:</b> <%= request.getUserPrincipal() %>
    </p>
</security:authorize>
<br/>
<input type="button" class="back-button" onclick="history.back();" value="<spring:message code="button.back" />" />

</body>
</html>
