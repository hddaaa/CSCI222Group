<%@ page import="java.util.List" %>
<%@ page import="model.entity.ServiceInventory" %>
<%@ page import="controller.subSystemFunction.ServiceSystem" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 27/05/15
  Time: 8:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<ServiceInventory> inventoryList = (List<ServiceInventory>) request.getAttribute("inventoryList");
%>
<html>
<head>
    <title>Our Service</title>
</head>
<body>
<table>
  <tr>
    <th>Service</th>
    <th>Cost</th>
    <th>Availability</th>
  </tr>
  <%for(ServiceInventory si:inventoryList){
  %>
  <tr>
    <td><%=si.getItem()%></td>
    <td><%=si.getCost()%></td>
    <td><%=si.getAvailability()%></td>
  </tr>
  <%
    }
  %>
</table>
</body>
</html>
