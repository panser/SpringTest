<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: panser
  Date: 4/28/14
  Time: 2:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="login.title" /></title>
</head>
<body>

<a href="<c:url value="/index" />">
<spring:message code="login.contacts" />
</a><br/>

<c:if test="${not empty param.error}">
    <font color="red"> <spring:message code="login.loginerror" />
        : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
</c:if>

<form method="POST" action="<c:url value="/j_spring_security_check" />">
<table>
    <tr>
        <td align="right"><spring:message code="login.login" /></td>
        <td><input type="text" name="j_username" /></td>
    </tr>
    <tr>
        <td align="right"><spring:message code="login.password" /></td>
        <td><input type="password" name="j_password" /></td>
    </tr>
    <tr>
        <td align="right"><spring:message code="login.remember" /></td>
        <td><input type="checkbox" name="_spring_security_remember_me" /></td>
    </tr>
    <tr>
        <td colspan="2" align="right">
            <input type="submit" value="Login" />
            <input type="reset" value="Reset" />
        </td>
    </tr>
</table>
</form>

</body>
</html>
