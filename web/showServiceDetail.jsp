<%@ page import="model.entity.Service" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 27/05/15
  Time: 9:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Service> serviceList = (List<Service>) request.getAttribute("serviceList");
%>
<html>
<head>
    <title></title>
	<link rel="stylesheet" type="text/css" href="css/fms.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<body>
<table>
  <tr>
    <th>Service</th>
    <th>Cost</th>
    <th>Action</th>
  </tr>
  <%for(Service s:serviceList){
  %>
  <tr>
    <td><%=s.getItem()%></td>
    <td><%=s.getCost()%></td>
    <td>
      <button type="button" onclick="window.location='/DeleteServiceFromTicket?serviceId=<%=s.getId()%>&ticketId=<%=s.getTicketId()%>'">Delete</button>
    </td>
  </tr>
  <%
    }
  %>
</table>
</body>
</html>
