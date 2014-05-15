<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: panser
  Date: 5/10/14
  Time: 1:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<code>
<fieldset>
    <h1>Images of <font color="red">${login}</font></h1>
    <table>
        <c:forEach items="${images}" var="image">
            <tr>
                <td>
                    <a href="<c:url value="/image/${login}/${image.id}"/>">
                        <c:out value="${image.id}"/>
                    </a>
                </td>
                <td><c:out value="${image.imagePath}"/> </td>
                <td><fmt:formatDate value="${image.createDate}" pattern="d MMM,yyyy HH:mm" /></td>
            </tr>
        </c:forEach>
    </table>
</fieldset>
</code>
</body>
</html>
