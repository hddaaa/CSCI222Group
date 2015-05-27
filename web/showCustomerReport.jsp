<%@ page import="model.report.CustomerReport" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 27/05/15
  Time: 5:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CustomerReport customerReport = (CustomerReport) request.getAttribute("customerReport");
%>
<html>
<head>
    <title>Customer Report</title>
</head>
<body>
<div>
    <h1>Total Reservation</h1>
    <div><%=customerReport.getTotalReservation()%></div>
</div>
<div>
    <h1>Total Cost</h1>
    <div><%=customerReport.getTotalCost()%></div>
</div>
<div>
    <h1>Flight Cost</h1>
    <div><%=customerReport.getFlightCost()%></div>
</div>
<div>
    <h1>Service Cost</h1>
    <div><%=customerReport.getServiceCost()%></div>
</div>
</body>
</html>
