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
	<link rel="stylesheet" type="text/css" href="css/fms.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<body>
<%
  if(request.getAttribute("action")==null){
%>
<table class="table">
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
<%
  }else {
%>
<table class="table">
  <tr>
    <th>Service</th>
    <th>Cost</th>
    <th>Availability</th>
    <th>Action</th>
  </tr>
  <%for(ServiceInventory si:inventoryList){
  %>
  <form method="post" action="/AEDServiceInventory">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="inventoryId" value="<%=si.getId()%>">
  <tr>
    <td><input type="text" name="item" value="<%=si.getItem()%>"></td>
    <td><input type="number" name="cost" value="<%=si.getCost()%>"></td>
    <td>
      <select name="availability">
      <option value="all" <%=si.getAvailability().equals("all")?"selected":""%>>all</option>
      <option value="international" <%=si.getAvailability().equals("international")?"selected":""%>>international</option>
      </select>
    </td>
    <td>
      <input class="button button-primary" type="submit" value="Edit">
      <input class="button button-primary" type="button" value="Delete" onclick="window.location='/AEDServiceInventory?action=delete&inventoryId=<%=si.getId()%>'">
    </td>
  </tr>
  </form>
  <%
    }
  %>
</table>
<button class="button button-primary" type="button" onclick="window.location='addServiceInventoryForm.jsp'" >Add Service Inventory</button>
<%
  }
%>
</body>
</html>