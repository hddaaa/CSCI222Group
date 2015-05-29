<%@ page import="model.entity.Airport" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.List" %>
<%@ page import="model.dao.AirportDao" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="model.entity.Fleet" %>
<%@ page import="model.dao.FleetDao" %>
<%@ page import="model.entity.Route" %>
<%@ page import="model.entity.Schedule" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 28/05/15
  Time: 9:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Schedule schedule = (Schedule) request.getAttribute("schedule");
  Route route = (Route) request.getAttribute("route");
  String departTime = (String) request.getAttribute("departTime");
  String arriveTime = (String) request.getAttribute("arriveTime");

  List<Airport> airportList = AirportDao.getAllAirport();
  Collections.sort(airportList, new Comparator<Airport>() {
    @Override
    public int compare(Airport o1, Airport o2) {
      return o1.getCity().compareTo(o2.getCity());
    }
  });
  List<Fleet> fleetList = FleetDao.getAllFleet();
%>
<html>
<head>
  <title></title>
</head>
<body>
<form class="form form-aligned" method="post" action="/ModifySchedule">
  <fieldset>
    <legend>Add a new schedule</legend>
    <input type="hidden" name="scheduleId" value="<%=schedule.getId()%>">
    <div class="control-group">
      <label for="plane">Plane</label>
      <select id="plane" name="fleetId">
        <%
          for (Fleet f : fleetList)
            out.print("<option value='" + f.getId() + "' "+(f.getId()==schedule.getPlane()?"selected":"")+">" + f.getAircraft() +  "</option>");%>
      </select>
    </div>
    <div class="control-group">
      <label for="source">Origin</label>
      <select id="source" name="sourceAirport">
        <%
          for (Airport a : airportList)
            out.print("<option value='" + a.getIATA_FAA() + "' "+(a.getIATA_FAA().equals(route.getSourceAirport())?"selected":"")+">" + a.getCity() + ", " + a.getCountry() + "</option>");%>
      </select>
    </div>
    <div class="control-group">
      <label for="destination">Destination</label>
      <select id="destination" name="destinationAirport">
        <%
          for (Airport a : airportList)
            out.print("<option value='" + a.getIATA_FAA() + "' "+(a.getIATA_FAA().equals(route.getDestinationAirport())?"selected":"")+">" + a.getCity() + ", " + a.getCountry() + "</option>");%>
      </select>
    </div>
    <div class="control-group">
      <label for="depart_date">Depart Time(base on local timezone)</label>
      <input id="depart_date" name="departTime" type="datetime-local" value="<%=departTime%>">
    </div>
    <div class="control-group">
      <label for="arrive_date">Arrive Time(base on local timezone)</label>
      <input id="arrive_date" name="arriveTime" type="datetime-local" value="<%=arriveTime%>">
    </div>
    <div class="controls">
      <button type="submit" class="button button-primary">Submit</button>
    </div>
  </fieldset>
</form>
</body>
</html>
