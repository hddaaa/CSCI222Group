<%@ page import="model.entity.Airport" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.List" %>
<%@ page import="model.dao.AirportDao" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="model.entity.Fleet" %>
<%@ page import="model.dao.FleetDao" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 28/05/15
  Time: 9:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
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
    <link rel="stylesheet" href="css/fms.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/functions.js"></script>
</head>
<body>
<form class="form form-aligned" method="post" action="/AddSchedule">
  <fieldset>
    <legend>Add a new schedule</legend>
    <div class="control-group">
      <label for="plane">Plane</label>
      <select id="plane" name="fleetId">
        <%
          for (Fleet f : fleetList)
            out.print("<option value='" + f.getId() + "'>" + f.getAircraft() +  "</option>");%>
      </select>
    </div>
    <div class="control-group">
      <label for="source">Origin</label>
      <select id="source" name="sourceAirport">
        <%
          for (Airport a : airportList)
            out.print("<option value='" + a.getIATA_FAA() + "'>" + a.getCity() + ", " + a.getCountry() + "</option>");%>
      </select>
    </div>
    <div class="control-group">
      <label for="destination">Destination</label>
      <select id="destination" name="destinationAirport">
        <%
          for (Airport a : airportList)
            out.print("<option value='" + a.getIATA_FAA() + "'>" + a.getCity() + ", " + a.getCountry() + "</option>");%>
      </select>
    </div>
    <div class="control-group">
      <label for="depart_date">Depart Time(base on local timezone)</label>
      <input id="depart_date" name="departTime" type="datetime-local">
    </div>
    <div class="control-group">
      <label for="arrive_date">Arrive Time(base on local timezone)</label>
      <input id="arrive_date" name="arriveTime" type="datetime-local">
    </div>
    <div class="controls">
      <button type="submit" class="button button-primary">Submit</button>
    </div>
  </fieldset>
</form>
</body>
</html>
