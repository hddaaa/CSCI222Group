<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 27/05/15
  Time: 7:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String username = (String) request.getAttribute("username");
%>
<html>
<head>
    <title>Account Info</title>
	<link rel="stylesheet" type="text/css" href="css/fms.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<body>
<form method="post" action="/AccountInfo">
  <fieldset>
    <legend>Account Info</legend>
    <div class="control-group">
      <label for="username">user name:</label>
      <input id="username" type="text" name="username"  value="<%=username%>"><br><br>
    </div>
    <div class="control-group">
      <label for="oldpwd">old password:</label>
      <input id="oldpwd" type="password" name="oldpwd"><br><br>
    </div>
    <div class="control-group">
      <label for="newpwd">new password:</label>
      <input id="newpwd" type="password" name="newpwd"><br><br>
    </div>
    <input type="submit" value="update Info">
  </fieldset>
</form>
</body>
</html>
