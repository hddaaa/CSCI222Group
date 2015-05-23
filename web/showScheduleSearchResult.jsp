<%@ page import="model.entity.Schedule" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.entity.Airport" %>
<%@ page import="java.util.TimeZone" %>
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
  SimpleDateFormat ssdf = new SimpleDateFormat("hh:mm dd MMM yyyy");
  ssdf.setTimeZone(TimeZone.getTimeZone(sourceAirport.getDatabase_timezone()));
  SimpleDateFormat dsdf = new SimpleDateFormat("hh:mm dd MMM yyyy");
  dsdf.setTimeZone(TimeZone.getTimeZone(destinationAirport.getDatabase_timezone()));
%>
<form action="/ShowFlightDetail" method="post">
<h1>Schedule</h1>
<table>
  <tr>
    <th>Flight ID</th>
    <th>source airport</th>
    <th>departure time</th>
    <th>destination airport</th>
    <th>arrive time</th>
    <th>select</th>
  </tr>
  <%
    for (Schedule s :schedules){
      out.print("<tr>" +
              "<td>"+s.getFlightID()+"</td>" +
              "<td>"+sourceAirport.getCity()+"</td>" +
              "<td>"+ssdf.format(s.getDepartTime())+"</td>" +
              "<td>"+destinationAirport.getCity()+"</td>" +
              "<td>"+ssdf.format(s.getArrivedTime())+"</td>" +
              "<td><input type='radio'"+(s.equals(schedules.get(0))?" checked='checked'":"")+" name='scheduleId' value='"+s.getId()+"' />" +
              "</tr>");
    }
  %>
</table>

<%
  if(returnSchedules!=null){
%>
<h1>Return</h1>
<table>
  <tr>
    <th>Flight ID</th>
    <th>source airport</th>
    <th>departure time</th>
    <th>destination airport</th>
    <th>arrive time</th>
    <th>select</th>
  </tr>
  <%
    for (Schedule rs :returnSchedules){
      out.print("<tr>" +
              "<td>"+rs.getFlightID()+"</td>" +
              "<td>"+destinationAirport.getCity()+"</td>" +
              "<td>"+ssdf.format(rs.getDepartTime()) +"</td>" +
              "<td>"+sourceAirport.getCity()+"</td>" +
              "<td>"+ssdf.format(rs.getArrivedTime())+"</td>" +
              "<td><input type='radio'"+(rs.equals(schedules.get(0))?" checked='checked'":"")+" name='returnScheduleId' value='"+rs.getId()+"' />" +
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