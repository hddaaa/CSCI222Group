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
<html>
<head>
  <title></title>
  <link rel="stylesheet" type="text/css" href="css/fms.css">
  <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<body>
<%
  int ticketId = (int) request.getAttribute("ticketId");
  if(request.getAttribute("serviceList")!=null){
    List<Service> serviceList = (List<Service>) request.getAttribute("serviceList");

%>

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
      <button class="button button-primary" type="button" onclick="window.location='/DeleteServiceFromTicket?serviceId=<%=s.getId()%>&ticketId=<%=ticketId%>'">Delete</button>
    </td>
  </tr>
  <%
    }
  %>
</table>
<%
}else{
%>
<h1>no service in this ticket</h1>
<%
  }
%>
<button class="button button-primary" type="button" onclick="window.location='/SearchServiceForSchedule?ticketId=<%=ticketId%>'">Add New Service</button>
</body>
</html>