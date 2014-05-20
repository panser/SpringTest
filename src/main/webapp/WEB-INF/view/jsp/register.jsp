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
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Register Form</title>
</head>
<body onload="document.f.label.focus();">
<h2>Register:</h2>
<div id="container">

    <sf:form name="f" method="POST" modelAttribute="user" enctype="application/x-www-form-urlencoded">
        <fieldset>
            <legend>Register</legend>
            <sf:label path="login">Username: </sf:label>
            <sf:input path="login" id="login"/>
            <sf:errors path="login"/>
            <p/>
            <sf:label path="password">Password: </sf:label>
            <sf:password path="password" id="password" showPassword="true"/>
            <sf:errors path="password"/>
            <p/>
            <br/>

            <input name="commit" type="submit" value="Register" />
        </fieldset>
    </sf:form>

</div>

</body>
</html>
