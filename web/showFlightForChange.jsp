<%@ page import="java.util.Map" %>
<%@ page import="model.entity.*" %>
<%@ page import="util.common.ParseDateUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 1/06/15
  Time: 5:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Schedule schedule = (Schedule) request.getAttribute("schedule");
  Fleet fleet = (Fleet) request.getAttribute("fleet");
  Route route = (Route) request.getAttribute("route");
  SeatMap seatMap = (SeatMap) request.getAttribute("seatmap");
  Map<String, Integer> priceMap = (Map<String, Integer>) request.getAttribute("pricemap");
  Airport sourceAirport = (Airport) request.getAttribute("sourceAirport");
  Airport destinationAirport = (Airport) request.getAttribute("destinationAirport");
%>
<form method="post" action="/ChangeFlight">
  <table>
    <tr>
      <th>Flight ID</th>
      <th>Source airport</th>
      <th>Departure time</th>
      <th>Destination airport</th>
      <th>Arrive time</th>
    </tr>
    <tr>
      <td><%=schedule.getFlightID()%>
      </td>
      <td><%=sourceAirport.getCity()%>
      </td>
      <td><%=ParseDateUtil.formatDate(schedule.getDepartTime(), sourceAirport.getDatabase_timezone())%>
      </td>
      <td><%=destinationAirport.getCity()%>
      </td>
      <td><%=ParseDateUtil.formatDate(schedule.getArrivedTime(), destinationAirport.getDatabase_timezone())%>
      </td>
    </tr>
  </table>
  <input type="hidden" name="scheduleId" value="<%=schedule.getId()%>">
  <br>
  <table>
    <tr>
      <th>Airplane</th>
      <th>First Class</th>
      <th>Business Class</th>
      <th>Premium Economy Class</th>
      <th>Economy Class</th>
    </tr>
    <tr>
      <td><%=fleet.getAircraft()%>
      </td>
      <td>available seat:<%=seatMap.getfClassSpare()%>
      </td>
      <td>available seat:<%=seatMap.getbClassSpare()%>
      </td>
      <td>available seat:<%=seatMap.getPeClassSpare()%>
      </td>
      <td>available seat:<%=seatMap.geteClassSpare()%>
      </td>
    </tr>
    <tr>
      <td>Price:</td>
      <td><%out.print(seatMap.getfClassSpare() > 0 ? priceMap.get("FClass") + "<input type='radio' name='fareClass' value='FClass'>" : "");%></td>
      <td><%out.print(seatMap.getbClassSpare() > 0 ? priceMap.get("BClass") + "<input type='radio' name='fareClass' value='BClass'>" : "");%></td>
      <td><%out.print(seatMap.getPeClassSpare() > 0 ? priceMap.get("PEClass") + "<input type='radio' name='fareClass' value='PEClass'>" : "");%></td>
      <td><%out.print(seatMap.geteClassSpare() > 0 ? priceMap.get("EClass") + "<input type='radio' name='fareClass' value='EClass'>" : "");%></td>
    </tr>
  </table>
  <br>

<h5>Seat Number:</h5>
      <%
          out.print("<select name='seatNum'>");
          for (int i = 0; i < seatMap.getMap().size(); i++) {
            if (((double) seatMap.getMap().get(i)) == -1) {
              out.print("<option value='" + i + "'>" + (i + 1) + "</option>");
            }
          }
          out.print("</select>");
      %>
  <input class="button button-primary" type="submit" value="Change Flight">
</form>

