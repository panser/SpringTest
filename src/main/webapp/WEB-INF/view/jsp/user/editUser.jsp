<%@ page session="false" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: panser
  Date: 4/24/14
  Time: 5:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="editUser.title" /></title>
</head>
<body onload="document.f.login.focus();">
<h2><spring:message code="editUser.header" /></h2>
<div>
    <sf:form name="f" method="POST" modelAttribute="user" enctype="multipart/form-data">
        <fieldset>
            <%--<input id="id" name="id" type="hidden" value="${user.id}"/>--%>

            <sf:label path="login"><spring:message code="editUser.label.login" /></sf:label>
            <sf:input path="login" id="login"/>
            <sf:errors path="login"/>
            <p/>
            <sf:label path="email"><spring:message code="editUser.label.email" /></sf:label>
            <sf:input path="email" id="email"/>
            <%--<input type="email" path="email" id="email"/>--%>
            <sf:errors path="email"/>
            <p/>
            <sf:label path="password"><spring:message code="editUser.label.password" /></sf:label>
            <sf:password path="password" showPassword="true" id="password"/>
            <sf:errors path="password"/>
            <p/>
            <sf:label path="avatorPath"><spring:message code="editUser.label.avatorPath" /></sf:label>
            <input name="photo" type="file">
            <sf:errors path="avatorPath"/>
            <p/>
            <sf:label path="birthDay"><spring:message code="editUser.label.birthDay" /></sf:label>
<%--
            <fmt:formatDate value="${user.birthDay}" pattern="yyyy-MM-dd" var="patternBirthDay"/>
            <form:input path="birthDay" value="${patternBirthDay}"/>
--%>
            <form:input path="birthDay" id="birthDay" placeholder="dd.MM.yyyy"/>
            <%--<input type="date" path="birthDay" value="<fmt:formatDate value="${user.birthDay}" pattern="yyyy-MM-dd"/>"/>--%>
            <sf:errors path="birthDay"/>
            <p/>

            <input name="commit" type="submit" value="<spring:message code="editUser.button.save" />" />
            <input type="button" class="back-button" onclick="history.back();" value="<spring:message code="button.back" />" />
            <%--<input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>--%>
            <security:csrfInput />
        </fieldset>
    </sf:form>


</div>

</body>
</html>
