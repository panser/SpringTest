<%@ page session="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: panser
  Date: 4/24/14
  Time: 5:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="index.title" /></title>
</head>
<body>
<ul>
    <li>
        <a href="${pageContext.request.contextPath}/user/list" >USERS CRUD</a>
    </li>
    <li>
        test:
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/test/testHeaders" >HTTP Headers</a>
            </li>
            <li>
                security:
                <ul>
                    <li>
                        <a href="${pageContext.request.contextPath}/test/testSecurityClickjacking" >test Clickjacking</a>
                    </li>
                </ul>
            </li>
        </ul>
    </li>
</ul>
</body>
</html>
