<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
    <title>HTTP Error ${ errorCode }</title>
</head>
<body>
<h1>HTTP Error ${ errorCode }</h1>

<p>${ message }</p>
</body>
</html>
