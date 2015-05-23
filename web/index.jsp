<%@ page import="model.dao.AirportDao" %>
<%@ page import="model.entity.Airport" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 12/05/15
  Time: 3:56 AM
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
<!DOCTYPE html>
<html>
<head>
    <title>Sunset Airways - Home</title>
    <link rel="stylesheet" href="css/fms.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>
<body class="main">
<div class="container">
    <div class="box searchflight">
        <h2>Search Flights</h2>

        <form class="form form-aligned" method="post" action="/SearchSchedule">
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
                    <button type="submit" class="button button-primary">Submit</button>
                </div>
            </fieldset>
        </form>
    </div>
    <div id="divider">
        <p> or </p>
    </div>
    <div class="box login">
        <h2>Login</h2>

        <form class="form form-aligned">
            <fieldset>
                <div class="control-group">
                    <label for="username">Username</label>
                    <input id="username" type="text" placeholder="Username">
                </div>
                <div class="control-group">
                    <label for="password">Password</label>
                    <input id="password" type="password">
                </div>
                <div class="controls">
                    <button type="submit" class="button button-primary">Login</button>
                    <button type="button" class="button button-primary">Register</button>
                </div>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>