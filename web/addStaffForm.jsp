<%@ page import="util.Enum.UserAuthority" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 29/05/15
  Time: 5:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <link rel="stylesheet" type="text/css" href="css/fms.css">
  <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<body>
<form class="form form-aligned" method="post" action="/AddStaff">
  <fieldset>
    <legend>Add new staff</legend>
    <div class="control-group">
      <label for="authority">Authority</label>
      <select id="authority" name="authority">
        <option value="Staff">Staff</option>
        <option value="ServiceManager">Service Manager</option>
        <option value="FlightManager">Flight Manager</option>
        <option value="Admin">Admin</option>
      </select>
    </div>
    <div class="control-group">
      <label for="username">User Name</label>
      <input id="username" name="username" type="text">
    </div>
    <div class="control-group">
      <label for="pwd">Password</label>
      <input id="pwd" name="pwd" type="password">
    </div>
    <div class="controls">
      <button type="submit" class="button button-primary">Submit</button>
    </div>
  </fieldset>
</form>
</body>
</html>
