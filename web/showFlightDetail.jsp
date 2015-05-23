<%@ page import="model.entity.Schedule" %>
<%@ page import="model.entity.Fleet" %>
<%@ page import="model.entity.Route" %>
<%@ page import="model.entity.SeatMap" %>
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
  SeatMap seatMap=(SeatMap) request.getAttribute("seatmap");
  Map<String,Integer> priceMap = (Map<String, Integer>) request.getAttribute("pricemap");
%>