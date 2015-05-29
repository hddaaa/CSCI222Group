<%@ page import="model.dao.AirportDao" %>
<%@ page import="java.util.Collections" %>
<%@ page import="model.entity.Airport" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Comparator" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 26/05/15
  Time: 6:47 AM
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
%>
<html>
<head>
    <title>search schedule</title>
	<link rel="stylesheet" type="text/css" href="css/fms.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<body>
<div class="box searchflight">
  <h2>Search Flights</h2>

  <form class="form form-aligned">
    <fieldset>
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
      <%if (request.getAttribute("action")==null){

      %>
      <div class="control-group">
        <label for="depart_date">Departure Date</label>
        <input id="depart_date" type="date" name="departureDate">
      </div>
      <div class="control-group">
        <label for="return">Return Flight?</label>
        <input id="return" type="checkbox" name="isReturn" value="return">
      </div>
      <div class="control-group">
        <label for="return_date">Return Date</label>
        <input id="return_date" type="date" name="returnDate">
      </div>
      <div class="control-group">
        <label for="passengers">Number of Passengers</label>
        <input id="passengers" type="number" min="1" value="1" name="passageNum">
      </div>
      <div class="controls">
        <input type="submit" class="button button-primary" value="Submit" formaction="/SearchSchedule" formmethod="post">
      </div>
          <%
            }else {
          %>
      <input type="submit" class="button button-primary" value="Submit" formaction="/SearchScheduleForModify" formmethod="post">
      <%
        }
      %>
    </fieldset>
  </form>
</div>
</body>
</html>
