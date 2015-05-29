<%@ page import="model.entity.Airport" %>
<%@ page import="model.dao.AirportDao" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.Collections" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 29/05/15
  Time: 3:51 AM
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
<form class="form form-aligned" method="post" action="/SearchScheduleForModify">
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
    <div class="controls">
      <button type="submit" class="button button-primary">Submit</button>
    </div>
  </fieldset>
</form>
</body>
</html>
