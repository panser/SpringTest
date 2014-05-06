<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
    <title>CustomException catched by SimpleMappingExceptionResolver</title>
</head>
<body>
<h1>CustomException catched by SimpleMappingExceptionResolver</h1>

<P>${exception.message}</P>
</body>
</html>
