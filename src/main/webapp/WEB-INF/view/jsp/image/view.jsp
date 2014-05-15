<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: panser
  Date: 5/12/14
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<fieldset>
    <h1>Image detail:</h1>
    <table>
        <tr>
            <td><label>Id: </label></td>
            <td><c:out value="${image.id}"/></td>
        </tr>
        <tr>
            <td><label>User owner: </label></td>
            <td><c:out value="${login}"/></td>
        </tr>
        <tr>
            <td><label>Image server path: </label></td>
            <td><c:out value="${image.imagePath}"/></td>
        </tr>
        <tr>
            <td><label>Image create date: </label></td>
            <td><fmt:formatDate value="${image.createDate}" pattern="d MMM,yyyy HH:mm" /></td>
        </tr>
    </table>
</fieldset>
</body>
</html>
