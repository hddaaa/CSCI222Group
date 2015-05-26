<%@ page import="model.entity.Airport" %>
<%@ page import="model.entity.Schedule" %>
<%@ page import="util.common.ParseDateUtil" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 23/05/15
  Time: 5:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    List<Schedule> schedules = (List<Schedule>) request.getAttribute("schedules");
    List<Schedule> returnSchedules = (List<Schedule>) request.getAttribute("returnSchedules");
    Airport sourceAirport = (Airport) request.getAttribute("sourceAirport");
    Airport destinationAirport = (Airport) request.getAttribute("destinationAirport");
    if(schedules!=null){
%>
<form action="/ShowFlightDetail" method="post">
    <h1>Schedule</h1>
    <table>
        <tr>
            <th>Flight ID</th>
            <th>Source airport</th>
            <th>Departure time</th>
            <th>Destination airport</th>
            <th>Arrive time</th>
            <th>Select</th>
        </tr>
        <%
            for (Schedule s : schedules) {
                out.print("<tr>" +
                        "<td>" + s.getFlightID() + "</td>" +
                        "<td>" + sourceAirport.getCity() + "</td>" +
                        "<td>" + ParseDateUtil.formatDate(s.getDepartTime(), sourceAirport.getDatabase_timezone()) + "</td>" +
                        "<td>" + destinationAirport.getCity() + "</td>" +
                        "<td>" + ParseDateUtil.formatDate(s.getArrivedTime(), destinationAirport.getDatabase_timezone()) + "</td>" +
                        "<td><input type='radio'" + (s.equals(schedules.get(0)) ? " checked='checked'" : "") + " name='scheduleId' value='" + s.getId() + "' />" +
                        "</tr>");
            }
        %>
    </table>
    <%
        if (returnSchedules != null) {
    %>
    <h1>Return</h1>
    <table>
        <tr>
            <th>Flight ID</th>
            <th>Source airport</th>
            <th>Departure time</th>
            <th>Destination airport</th>
            <th>Arrive time</th>
            <th>Select</th>
        </tr>
        <%
            for (Schedule rs : returnSchedules) {
                out.print("<tr>" +
                        "<td>" + rs.getFlightID() + "</td>" +
                        "<td>" + destinationAirport.getCity() + "</td>" +
                        "<td>" + ParseDateUtil.formatDate(rs.getDepartTime(), destinationAirport.getDatabase_timezone()) + "</td>" +
                        "<td>" + sourceAirport.getCity() + "</td>" +
                        "<td>" + ParseDateUtil.formatDate(rs.getArrivedTime(), sourceAirport.getDatabase_timezone()) + "</td>" +
                        "<td><input type='radio'" + (rs.equals(schedules.get(0)) ? " checked='checked'" : "") + " name='returnScheduleId' value='" + rs.getId() + "' />" +
                        "</tr>");
            }
        %>
    </table>
    <input type="hidden" name="return" value="return">
    <%
        }
    %>
    <input type="submit" value="next">
</form>
<%
    }else {
%>
<h2>sorry, no schedule is found</h2>
<%
    }
%>