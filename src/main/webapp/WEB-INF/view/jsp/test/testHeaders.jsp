<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: panser
  Date: 4/30/14
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h4>
    <c:set var="keyName" value="user-agent" />
    ${requestHeaderMap[keyName]}
</h4>

<table>
    <c:forEach items="${requestHeaderMap}" var="entry">
        <tr>
            <td><c:out value="${entry.key}"/></td>
            <td><c:out value="${entry.value}"/></td>
        </tr>
    </c:forEach>
</table>
<br/>
<input type="button" class="back-button" onclick="history.back();" value="<spring:message code="button.back" />" />

</body>
</html>
