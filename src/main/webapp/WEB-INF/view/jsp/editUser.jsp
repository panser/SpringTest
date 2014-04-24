<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: panser
  Date: 4/24/14
  Time: 5:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2>Edit User</h2>
<div>
    <sf:form method="post" modelAttribute="user">
        <sf:label path="login">Login:</sf:label>
        <sf:input path="login" id="login" />
        <sf:errors path="login" cssClass="error" />
        <p/>
        <sf:label path="email">Email:</sf:label>
        <sf:input path="email" id="email"/>
        <sf:errors path="email" cssClass="error" />
        <p/>
        <sf:label path="password">Password:</sf:label>
        <sf:password path="password" id="password"/>
        <sf:errors path="password" cssClass="error" />
        <p/>

        <button type="submit">Save</button>
    </sf:form>

</div>

</body>
</html>
