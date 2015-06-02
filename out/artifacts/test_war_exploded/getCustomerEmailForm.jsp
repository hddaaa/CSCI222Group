<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 28/05/15
  Time: 3:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> customer id</title>
    <link rel="stylesheet" type="text/css" href="css/fms.css">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<body>
<%
    if (request.getAttribute("action") != null) {
%>
<form class="form form-aligned">
    <fieldset>
        <legend>Customer ID</legend>
        <div class="control-group">
            <label for="customerEmail">Customer Email: </label>
            <input id="customerEmail" type="text" name="customerEmail"><br><br>
        </div>
        <%
            if (request.getAttribute("action").equals("info")) {
        %>
        <input class="button button-primary" type="submit" value="next" formmethod="post"
               formaction="/ShowCustomerInfo">
        <%
        } else {
            if (request.getAttribute("action").equals("service")) {
        %>
        <input type="hidden" name="service" value="true">
        <%
            }
        %>
        <input class="button button-primary" type="submit" value="next" formmethod="post"
               formaction="/ShowAllMyBookings">
        <%


            }
        %>
    </fieldset>
</form>

<%
} else {
%>
<h1>no Action in this request</h1>
<%
    }
%>
</body>
</html>