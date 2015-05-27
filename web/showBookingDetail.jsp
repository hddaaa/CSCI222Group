<%@ page import="model.entity.SeatMap" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 27/05/15
  Time: 1:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<String, String> booking = (Map<String, String>) request.getAttribute("booking");
    SeatMap seatMap = (SeatMap) request.getAttribute("seatMap");
%>
<table>
    <tr>
        <th>Passage</th>
        <th>Email</th>
        <th>Source Airport</th>
        <th>Departure Time</th>
        <th>Destination Airport</th>
        <th>Arrive Time</th>
    </tr>
    <tr>
        <td><%=booking.get("customer")%>
        </td>
        <td><%=booking.get("email")%>
        </td>
        <td><%=booking.get("sourceAirport")%>
        </td>
        <td><%=booking.get("departTime")%>
        </td>
        <td><%=booking.get("destinationAirport")%>
        </td>
        <td><%=booking.get("arriveTime")%>
        </td>
    </tr>
    <tr>
        <th>Fare Class</th>
        <th>seatNumber</th>
        <th>Flight Cost</th>
        <th>Service Cost</th>
        <th>Cost</th>
    </tr>
    <tr>
        <td><%=booking.get("fareClass")%>
        </td>
        <td><%=booking.get("seat")%>
        </td>
        <td><%=booking.get("flightCost")%>
        </td>
        <td><%=booking.get("serviceCost")%>
        </td>
        <td><%=booking.get("totalCost")%>
        </td>
    </tr>
</table>
<form method="post" action="/ChangeSeat">
    <fieldset>
        <legend>Change Seat</legend>
        <select name="seatNum">
            <%
                for (int i = 0; i < seatMap.getMap().size(); i++) {
                    if (((double) seatMap.getMap().get(i)) == -1) {
                        out.print("<option value='" + i + "'>" + (i + 1) + "</option>");
                    }
                }
            %>
        </select>
        <input type="submit" value="Change Seat">
    </fieldset>
</form>
<button type="button" onclick="window.location=''">change flight</button>