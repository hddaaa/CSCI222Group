<%@ page import="model.entity.ServiceInventory" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.Ticket" %>
<%@ page import="controller.subSystemFunction.ProfileSystem" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 26/05/15
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" type="text/css" href="css/fms.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<%
    if (request.getAttribute("serviceList") != null) {
        List<ServiceInventory> serviceList = (List<ServiceInventory>) request.getAttribute("serviceList");

%>
<form method="post" action="/ServiceReservation">
    <%
        if (request.getAttribute("passageNum") == null) {
    %>
    <table>
        <tr>
            <th>Item</th>
            <th>Price</th>
            <th>Select</th>
        </tr>
        <%
            for (ServiceInventory si : serviceList) {
        %>
        <tr>
            <td><%=si.getItem()%>
            </td>
            <td><%=si.getCost()%>
            </td>
            <td><input type="checkbox" name="services" value="<%=si.getItem()+','+si.getCost()%>"></td>
        </tr>
        <%
            }
        %>

    </table>

    <%
    } else {
        int passageNum = (int) request.getAttribute("passageNum");
    %>
    <input type="hidden" name="passageNum" value="<%=passageNum%>">

    <table>
        <tr>
            <th>Item</th>
            <th>Price</th>
            <%

                for (int i = 0; i < passageNum; i++) {
            %>
            <th><%=ProfileSystem.getCustomerName(((Ticket) session.getAttribute("ticket" + i)).getCustomerId())%></th>
            <%
                }
            %>

        </tr>
        <%
            for (ServiceInventory si : serviceList) {
        %>
        <tr>
            <td><%=si.getItem()%>
            </td>
            <td><%=si.getCost()%>
            </td>
            <%

                for (int i = 0; i < passageNum; i++) {
            %>
            <td><input type="checkbox" name="services<%=i%>" value="<%=si.getItem()+','+si.getCost()%>"></td>
            <%
                }
            %>
        </tr>
        <%
            }
        %>

    </table>
<%
    if(request.getAttribute("return")!=null){
%>
    <input type="hidden" name="return" value="return">
    <br>
    <br>
    <br>
    <h2>return</h2>
    <table>
        <tr>
            <th>Item</th>
            <th>Price</th>
            <%

                for (int i = 0; i < passageNum; i++) {
            %>
            <th><%=ProfileSystem.getCustomerName(((Ticket) session.getAttribute("rticket" + i)).getCustomerId())%></th>
            <%
                }
            %>

        </tr>
        <%
            for (ServiceInventory si : serviceList) {
        %>
        <tr>
            <td><%=si.getItem()%>
            </td>
            <td><%=si.getCost()%>
            </td>
            <%

                for (int i = 0; i < passageNum; i++) {
            %>
            <td><input type="checkbox" name="rservices<%=i%>" value="<%=si.getItem()+','+si.getCost()%>"></td>
            <%
                }
            %>
        </tr>
        <%
            }
        %>

    </table>
    <%
            }
        }
    %>
    <input type="submit" value="Add to my Ticket">
</form>
<%

} else {

%>
<h1>no extra services are found</h1>
<%
    }
%>