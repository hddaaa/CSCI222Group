<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 24/05/15
  Time: 1:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sunset Airways - Home</title>
    <link rel="stylesheet" href="css/fms.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>
<body class="main">
<div class="container">
    <div class="search">
        <%
            if (request.getAttribute("nextStep") != null)
                if (request.getAttribute("nextStep").equals("scheduleSearch")) {
        %>
        <%@include file="showScheduleSearchResult.jsp" %>
        <%
        } else if (request.getAttribute("nextStep").equals("flightDetail")) {
        %>
        <%@include file="showFlightDetail.jsp" %>
        <%
        } else if (request.getAttribute("nextStep").equals("register")) {
        %>
        <%@include file="register.jsp" %>
        <%
        } else if (request.getAttribute("nextStep").equals("login")) {
        %>
        <%@include file="login.jsp" %>
        <%
                }
            if (request.getParameter("nextStep") != null)
                if (request.getParameter("nextStep").equals("register")) {
        %>
        <%@include file="register.jsp" %>
        <%
        } else if (request.getParameter("nextStep").equals("login")) {
        %>
        <%@include file="login.jsp" %>
        <%
                }

        %>
    </div>


</div>
</body>
</html>