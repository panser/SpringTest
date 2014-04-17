<html>
<head>
    <title>Simple FreeMaker page</title>
</head>
<body>
<h1>Simple FreeMaker page</h1>

<ul>
    <#list users as user>
        <li>
            ${user.id}
            ${user.login}
            ${user.email}
            ${user.password}
        </li>
    </#list>
</ul>
</body>
</html>