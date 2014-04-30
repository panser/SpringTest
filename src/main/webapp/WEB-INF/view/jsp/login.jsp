<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<body>

<%--
&lt;%&ndash;<a href="<c:url value="/index" />">&ndash;%&gt;
<a href="<c:url value="/user/list" />">
<spring:message code="login.contacts" />
</a>
<br/>
--%>

<c:if test="${not empty param.error}">
    <font color="red"> <spring:message code="springSecurity.loginerror" />
        : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
</c:if>

<spring:url var="authUrl" value="/j_spring_security_check" />
<form method="post" class="signin" action="${authUrl}">
<table>
    <tr>
        <td align="right"><spring:message code="springSecurity.login" /></td>
        <td><input id="username" type="text" name="j_username" /></td>
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
        <td colspan="2" align="right">
            <input type="submit" value="<spring:message code="springSecurity.submit" />" />
            <input type="reset" value="<spring:message code="springSecurity.reset" />" />
        </td>
    </tr>
</table>
</form>
<script type="text/javascript">
    document.getElementById('username').focus();
</script>

</body>
</html>
