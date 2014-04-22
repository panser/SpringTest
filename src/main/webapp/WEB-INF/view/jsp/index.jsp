<%--
  Created by IntelliJ IDEA.
  User: panser
  Date: 4/22/14
  Time: 4:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>test Spring MVC Form</title>
</head>
<body>
<h2>User Registration</h2>
    <div>
        <sf:form method="post" modelAttribute="User">
            <%--<label for="login"> login </label> <sf:input path="login"  id="login" />--%>
            <sf:label path="login">Login:</sf:label>
            <sf:input path="login" id="login"/>
            <sf:errors path="login" cssClass="error" />
            <p/>
            <%--<label for="email"> email </label> <sf:input path="email"  id="email" />--%>
            <sf:label path="email">Email:</sf:label>
            <sf:input path="email" id="email"/>
            <sf:errors path="email" cssClass="error" />
            <p/>
            <%--<label for="password"> password </label> <sf:password path="password"  id="password" />--%>
            <sf:label path="password">Password:</sf:label>
            <sf:password path="password" id="password"/>
            <sf:errors path="password" cssClass="error" />
            <p/>

            <button type="submit">Registration</button>
        </sf:form>

    </div>

</body>
</html>
