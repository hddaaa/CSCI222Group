<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 26/05/15
  Time: 7:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>success</title>
	<link rel="stylesheet" type="text/css" href="css/fms.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<body>
<h1>success!</h1>
<br>
<h2><%=request.getAttribute("successMessage")!=null?(String)request.getAttribute("successMessage"):""%></h2>
</body>
</html>
