<%@ page import="model.entity.User" %>
<%@ page import="util.Enum.UserAuthority" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 29/05/15
  Time: 6:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="css/fms.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/functions.js"></script>
</head>
<body>
<form class="form form-aligned" method="get" action="/ModifyStaff">
    <fieldset>
        <legend>Enter username</legend>
        <div class="control-group">
            <label for="username">User Name</label>
            <input id="username" name="username" type="text">
        </div>

        <div class="controls">
            <button type="submit" class="button button-primary">Search</button>
        </div>
    </fieldset>
</form>
<%
    if (request.getAttribute("thisUser") != null) {
        User thisUser = (User) request.getAttribute("thisUser");
%>
<br><br><br><br><br>
<form method="post" action="/ModifyStaff">
    <fieldset>
        <legend>User : <%=thisUser.getUsername()%> (<%=thisUser.getId()%>)</legend>
        <input type="hidden" name="oldUsername" value="<%=thisUser.getUsername()%>">
        <div class="control-group">
            <label for="authority">Authority</label>
            <select id="authority" name="authority">
                <option value="Staff" <%=thisUser.getAuthority()== UserAuthority.Staff?"selected":""%>>Staff</option>
                <option value="ServiceManager" <%=thisUser.getAuthority()== UserAuthority.ServiceManager?"selected":""%>>Service Manager</option>
                <option value="FlightManager" <%=thisUser.getAuthority()== UserAuthority.FlightManager?"selected":""%>>Flight Manager</option>
                <option value="Admin" <%=thisUser.getAuthority()== UserAuthority.Admin?"selected":""%>>Admin</option>
            </select>
        </div>
        <div class="control-group">
            <label for="newUsername">User Name</label>
            <input id="newUsername" name="newUsername" type="text" value="<%=thisUser.getUsername()%>">
        </div>
        <br><br>
        <p>Keep password empty if don't want change it!</p>
        <div class="control-group">
            <label for="pwd">Password</label>
            <input id="pwd" name="pwd" type="password">
        </div>
        <div class="controls">
            <button type="submit" class="button button-primary">Modify</button>
        </div>
    </fieldset>
</form>
<%
    }
%>
</body>
</html>
