<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
    <title>FunnyException catched by CustomExceptionResolver</title>
</head>
<body>
<h1>FunnyException catched by CustomExceptionResolver</h1>

<P>${exception.message}</P>
<p>Model message: ${ funnyMessage }</p>
</body>
</html>
