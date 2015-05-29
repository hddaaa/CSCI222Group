<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 26/05/15
  Time: 6:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>error</title>
		<link rel="stylesheet" type="text/css" href="css/fms.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<body>
<h1>error!</h1>
<br>
<h2><%=request.getAttribute("errorMessage")!=null?(String)request.getAttribute("errorMessage"):""%></h2>
</body>
</html>
