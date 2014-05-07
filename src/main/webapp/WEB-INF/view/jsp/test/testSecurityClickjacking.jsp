<%--
  Created by IntelliJ IDEA.
  User: panser
  Date: 5/7/14
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<p>This contains frames, but the frames will not be loaded due to the <a href="http://tools.ietf.org/html/draft-ietf-websec-x-frame-options">X-Frame-Options</a>
    being specified as denied. This protects against <a href="http://en.wikipedia.org/wiki/Clickjacking">clickjacking attacks</a></p>
<iframe src="/" width="500" height="500"></iframe>
<br/>
<input type="button" class="back-button" onclick="history.back();" value="<spring:message code="button.back" />" />

</body>
</html>
