<html lang="">
<head>
    <title>${title}</title>
</head>
<body>
        <ul>
        <#list systems as system>
            <li>${system_index + 1}. ${system.name}</li>
        </#list>
        </ul>
</body>
</html>