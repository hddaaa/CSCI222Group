<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
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
    boolean service = false;

    if (request.getAttribute("service")!=null)
        service = (boolean) request.getAttribute("service");
%>
	<link rel="stylesheet" type="text/css" href="css/fms.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<table>
    <tr>
        <th>Source Airport</th>
        <th>Departure Time</th>
        <th>Destination Airport</th>
        <th>Arrive Time</th>
        <th>Flight Cost</th>
        <th>Service Cost</th>
        <th>Cost</th>
        <th>Action</th>
    </tr>
    <%
        for (Map<String, String> booking : bookingsList) {
    %>
    <tr>
        <td><%=booking.get("sourceAirport")%>
        </td>
        <td><%=booking.get("departTime")%>
        </td>
        <td><%=booking.get("destinationAirport")%>
        </td>
        <td><%=booking.get("arriveTime")%>
        </td>
        <td><%=booking.get("flightCost")%>
        </td>
        <td><%=booking.get("serviceCost")%>
        </td>
        <td><%=booking.get("totalCost")%>
        </td>
        <td>
            <%if (!service){%>
            <button type="button" onclick="window.location='/ShowBookingDetail?ticketId=<%=booking.get("ticketId")%>'">Detail</button>
            <%}else{%>
            <button type="button" onclick="window.location='/ShowServiceDetail?ticketId=<%=booking.get("ticketId")%>'">Service Detail</button>
            <%}%>
        </td>
    </tr>
    <%
        }
    %>
</table>