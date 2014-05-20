<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
    <title><spring:message code="springSecurity.title" /></title>
</head>
<body onload="document.f.username.focus();">

<c:if test="${not empty param.login_error}">
    <font color="red"> <spring:message code="springSecurity.loginerror" />
        : <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/> </font>
</c:if>

<c:url var="authUrl" value="/j_spring_security_check" />
<form name="f" method="post" class="signin" action="${authUrl}">
    <fieldset>
        <legend>Login</legend>
        <table>
            <tr>
                <td align="right"><spring:message code="springSecurity.login" /></td>
                <td><input id="username" type="text" name="j_username" /></td>
                <%--<td><input id="username" type='text' name='j_username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td>--%>
            </tr>
            <tr>
                <td align="right"><spring:message code="springSecurity.password" /></td>
                <td><input type="password" name="j_password" /></td>
                <%--<small><a href="/account/resend_password">Forgot?</a></small>--%>
            </tr>
            <tr>
                <td align="right"><spring:message code="springSecurity.remember" /></td>
                <td><input type="checkbox" name="_spring_security_remember_me" /></td>
            </tr>
            <tr>
                <td colspan="3" align="right">
                    <input type="submit" value="<spring:message code="springSecurity.submit" />" />
                    <a href="<c:url value="/register"/> ">Register</a>
                    <input type="button" class="back-button" onclick="history.back();" value="<spring:message code="button.back" />" />
                </td>
            </tr>
        </table>
        <%--<input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>--%>
        <security:csrfInput />
    </fieldset>
</form>

</body>
</html>
