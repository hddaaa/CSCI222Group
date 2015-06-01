<%@ page import="model.entity.SeatMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.entity.User" %>
<%@ page import="util.Enum.UserAuthority" %>
<%@ page import="model.entity.Customer" %>
<%@ page import="controller.subSystemFunction.ReservationSystem" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 27/05/15
  Time: 1:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("user");
    Map<String, String> booking = (Map<String, String>) request.getAttribute("booking");
    SeatMap seatMap = (SeatMap) request.getAttribute("seatMap");
%>
	<link rel="stylesheet" type="text/css" href="css/fms.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<table class="table">
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
        <th></th>
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
        <td></td>
    </tr>
</table>
<form class="form form-aligned" method="post" action="/ChangeSeat">
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
        <input type="submit" class="button button-primary" value="Change Seat">
    </fieldset>
</form>

<%

    if(user.getAuthority().compareTo(UserAuthority.Staff)>=0){
%>
<form class="form form-aligned" method="post" action="/SwitchSeat">
    <fieldset>
        <legend>Change Seat</legend>
        <select name="newSeatNum">
            <%
                for (int i = 0; i < seatMap.getMap().size(); i++) {
                    if (((double) seatMap.getMap().get(i)) != -1) {
                        Customer customer = ReservationSystem.customerDetail((int) (double) seatMap.getMap().get(i));
                        out.print("<option value='" + i + "'>" + (i + 1)+",user: "+customer.getFirstName()+" "+customer.getLastName() + "</option>");
                    }
                }
            %>
        </select>
        <input type="submit" class="button button-primary" value="Switch Seat">
    </fieldset>
</form>

<%
    }
%>

<button type="button" class="button button-primary" onclick="window.location='/SearchScheduleForChangeFlight'">change flight</button>
