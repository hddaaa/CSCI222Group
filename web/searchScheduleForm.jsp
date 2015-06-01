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
      <%if (request.getAttribute("action")==null||request.getAttribute("action").equals("change")){

      %>
      <div class="control-group">
        <label for="depart_date">Departure Date</label>
        <input id="depart_date" type="date" name="departureDate">
      </div>
      <%
        }
        if(request.getAttribute("action")==null){
      %>
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
        <select id="passengers" name="passageNum">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
        </select>
      </div>
      <div class="controls">
        <input type="submit" class="button button-primary" value="Submit" formaction="/SearchSchedule" formmethod="post">
      </div>
          <%
            }else if (request.getAttribute("action").equals("modify")){
          %>
      <input type="submit" class="button button-primary" value="Submit" formaction="/SearchScheduleForModify" formmethod="post">
      <%
        }else if (request.getAttribute("action").equals("change")){
      %>
      <input type="submit" class="button button-primary" value="Submit" formaction="/SearchScheduleForChangeFlight" formmethod="post">
      <%
        }
      %>
    </fieldset>
  </form>
</div>
</body>
</html>
