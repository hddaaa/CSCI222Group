<%@ page import="java.util.List" %>
<%@ page import="model.entity.ServiceInventory" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 26/05/15
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<ServiceInventory> serviceList = (List<ServiceInventory>) request.getAttribute("serviceList");
%>
	<link rel="stylesheet" type="text/css" href="css/fms.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<form method="post" action="/ServiceReservation">
<table>
  <tr>
    <th>Item</th>
    <th>Price</th>
    <th>Select</th>
  </tr>
  <%
    for (ServiceInventory si : serviceList){
  %>
    <tr>
      <td><%=si.getItem()%></td>
      <td><%=si.getCost()%></td>
      <td><input type="checkbox" name="services" value="<%=si.getItem()+','+si.getCost()%>"></td>
    </tr>
  <%
    }
  %>

</table>
<input type="submit" value="Add to my Ticket">
</form>