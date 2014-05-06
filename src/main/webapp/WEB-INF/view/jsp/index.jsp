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
        <b>exceptions:</b>
        <ul>
            <li><a
                    href="${pageContext.request.contextPath}/exception?type=catched"
                    title="IOException Catched by @ExceptionHandler">IOException Catched by @ExceptionHandler</a></li>
            <li><a
                    href="${pageContext.request.contextPath}/exception?type=custom"
                    title="CustomException catched by SimpleMappingExceptionResolver">CustomException catched by SimpleMappingExceptionResolver</a></li>
            <li><a
                    href="${pageContext.request.contextPath}/exception?type=secondCustom"
                    title="SecondCustomException catched by SimpleMappingExceptionResolver">SecondCustomException catched by SimpleMappingExceptionResolver</a></li>
            <li><a
                    href="${pageContext.request.contextPath}/exception?type=funny"
                    title="FunnyException catched by CustomExceptionResolver and using ModelAndView">FunnyException catched by CustomExceptionResolver and using ModelAndView</a></li>
            <li><a
                    href="${pageContext.request.contextPath}/exception?type=uncatched"
                    title="Default exception catched by SimpleMappingExceptionResolver">Default exception catched by SimpleMappingExceptionResolver</a></li>
            <li><a
                    href="${pageContext.request.contextPath}/http-error?code=404"
                    title="HTTP Error 404 (catched)">HTTP Error 404 (catched)</a></li>
            <li><a
                    href="${pageContext.request.contextPath}/http-error?code=505"
                    title="HTTP Error 505 (uncatched)">HTTP Error 505 (uncatched)</a></li>
        </ul>
    </li>
</ul>
</body>
</html>
