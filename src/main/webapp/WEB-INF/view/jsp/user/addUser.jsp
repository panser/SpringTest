<%--
  Created by IntelliJ IDEA.
  User: panser
  Date: 4/22/14
  Time: 4:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="addUser.title" /></title>
</head>
<body onload="document.f.login.focus();">
<h2><spring:message code="addUser.header" /></h2>
    <div>
        <sf:form name="f" method="POST" modelAttribute="user" enctype="application/x-www-form-urlencoded">
            <fieldset>
                <sf:label path="login"><spring:message code="addUser.label.login" /></sf:label>
                <sf:input path="login" id="login"/>
                <sf:errors path="login" cssClass="error" />
                <p/>
                <sf:label path="email"><spring:message code="addUser.label.email" /></sf:label>
                <sf:input path="email" id="email"/>
                <sf:errors path="email" cssClass="error" />
                <p/>
                <sf:label path="password"><spring:message code="addUser.label.password" /></sf:label>
                <sf:password path="password" id="password" showPassword="true"/>
                <sf:errors path="password" cssClass="error" />
                <p/>
                <spring:hasBindErrors name="user">
                    <b>Please fix all errors!</b>
                </spring:hasBindErrors>
                <br/>

                <input name="commit" type="submit" value="<spring:message code="addUser.button.add" />" />
                <input type="button" class="back-button" onclick="history.back();" value="<spring:message code="button.back" />" />
<%--
                <a href="${pageContext.request.contextPath}/" title="Back">
                    <spring:message code="addUser.button.back" />
                </a>
--%>
                <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
            </fieldset>
        </sf:form>

    </div>

</body>
</html>
