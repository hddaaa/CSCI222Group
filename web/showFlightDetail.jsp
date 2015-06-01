<%@ page import="model.entity.*" %>
<%@ page import="util.common.ParseDateUtil" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 24/05/15
  Time: 8:17 AM
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
    int passageNum = (int) request.getAttribute("passageNum");


%>
<link rel="stylesheet" type="text/css" href="css/fms.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<form class="form form-aligned" method="post" action="/TicketReservation">
    <table class="table">
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
    <table class="table">
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
    <input type="hidden" name="passageNum" value="<%=passageNum%>">
    <table class="table">
        <tr>
            <%
            for (int passage = 0; passage < passageNum; passage++) {
                out.print("<td><h3>passage "+(passage+1)+"</h3></td>");
            }
            %>
        </tr>
        <tr>
            <%
                for (int passage = 0; passage < passageNum; passage++) {
                    out.print("<td><select name='seatNum"+passage+"'>");
                    for (int i = 0; i < seatMap.getMap().size(); i++) {
                        if (((double) seatMap.getMap().get(i)) == -1) {
                            out.print("<option value='" + i + "' " + (i == passage ? "selected" : "") + ">" + (i + 1) + "</option>");
                        }
                    }
                    out.print("</select></td>");
                }
            %>
        </tr>
    </table>

    <br>
    <br>
    <br>
    <%

        if (request.getAttribute("return") != null) {
        Schedule rschedule = (Schedule) request.getAttribute("rschedule");
        Fleet rfleet = (Fleet) request.getAttribute("rfleet");
        Route rroute = (Route) request.getAttribute("rroute");
        SeatMap rseatMap = (SeatMap) request.getAttribute("rseatmap");
        Map<String, Integer> rpriceMap = (Map<String, Integer>) request.getAttribute("rpricemap");
        Airport rsourceAirport = (Airport) request.getAttribute("rsourceAirport");
        Airport rdestinationAirport = (Airport) request.getAttribute("rdestinationAirport");
    %>
    <input type="hidden" name="return" value="return">
    <table class="table">
        <tr>
            <th>Flight ID</th>
            <th>Source airport</th>
            <th>Departure time</th>
            <th>Destination airport</th>
            <th>Arrive time</th>
        </tr>
        <tr>
            <td><%=rschedule.getFlightID()%>
            </td>
            <td><%=rsourceAirport.getCity()%>
            </td>
            <td><%=ParseDateUtil.formatDate(rschedule.getDepartTime(), rsourceAirport.getDatabase_timezone())%>
            </td>
            <td><%=rdestinationAirport.getCity()%>
            </td>
            <td><%=ParseDateUtil.formatDate(rschedule.getArrivedTime(), rdestinationAirport.getDatabase_timezone())%>
            </td>
        </tr>
    </table>
    <input type="hidden" name="rscheduleId" value="<%=rschedule.getId()%>">
    <br>
    <table class="table">
        <tr>
            <th>Airplane</th>
            <th>First Class</th>
            <th>Business Class</th>
            <th>Premium Economy Class</th>
            <th>Economy Class</th>
        </tr>
        <tr>
            <td><%=rfleet.getAircraft()%>
            </td>
            <td>available seat:<%=rseatMap.getfClassSpare()%>
            </td>
            <td>available seat:<%=rseatMap.getbClassSpare()%>
            </td>
            <td>available seat:<%=rseatMap.getPeClassSpare()%>
            </td>
            <td>available seat:<%=rseatMap.geteClassSpare()%>
            </td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><%
                out.print(rseatMap.getfClassSpare() > 0 ? rpriceMap.get("FClass") + "<input type='radio' name='rfareClass' value='FClass'>" : "");%></td>
            <td><%
                out.print(rseatMap.getbClassSpare() > 0 ? rpriceMap.get("BClass") + "<input type='radio' name='rfareClass' value='BClass'>" : "");%></td>
            <td><%
                out.print(rseatMap.getPeClassSpare() > 0 ? rpriceMap.get("PEClass") + "<input type='radio' name='rfareClass' value='PEClass'>" : "");%></td>
            <td><%
                out.print(rseatMap.geteClassSpare() > 0 ? rpriceMap.get("EClass") + "<input type='radio' name='rfareClass' value='EClass'>" : "");%></td>
        </tr>
    </table>
    <table class="table">
        <tr>
            <%
                for (int passage = 0; passage < passageNum; passage++) {
                out.print("<td><h3>passage "+(passage+1)+"</h3></td>");
                }
            %>
        </tr>
        <tr>
            <%
                for (int passage = 0; passage < passageNum; passage++) {
                out.print("<td><select name='rseatNum"+passage+"'>");
                for (int i = 0; i < rseatMap.getMap().size(); i++) {
                if (((double) rseatMap.getMap().get(i)) == -1) {
                out.print("<option value='" + i + "' " + (i == passage ? "selected" : "") + ">" + (i + 1) + "</option>");
                }
                }
                out.print("</select></td>");
                }
            %>
        </tr>
    </table>

    <%
            }
    %>
    <br>
    <br>
    <input class="button button-primary" type="submit" value="next">
</form>
