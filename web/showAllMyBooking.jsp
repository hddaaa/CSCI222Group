<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 26/05/15
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Map<String, String>> bookingsList = (List<Map<String, String>>) request.getAttribute("bookingsList");
%>
<html>
<head>
    <title></title>
</head>
<body>
<table>
  <tr>
    <th>Source Airport</th>
    <th>Departure Time</th>
    <th>Destination Airport</th>
    <th>Arrive Time</th>
    <th>Flight Cost</th>
    <th>Service Cost</th>
    <th>action</th>
    <th>Cost</th>
  </tr>
  <%
    for (Map<String, String> booking : bookingsList){
      %>
      <tr>
        <td><%=booking.get("sourceAirport")%></td>
        <td><%=booking.get("departTime")%></td>
        <td><%=booking.get("destinationAirport")%></td>
        <td><%=booking.get("arriveTime")%></td>
        <td><%=booking.get("flightCost")%></td>
        <td><%=booking.get("serviceCost")%></td>
        <td><%=booking.get("totalCost")%></td>
        <td><button type="button" onclick="">Detail</button></td>
      </tr>
      <%
    }
  %>
</table>
</body>
</html>
