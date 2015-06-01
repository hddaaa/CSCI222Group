<%@ page import="model.entity.Service" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entity.Customer" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.Collections" %>
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
<form method="post" action="/AEDCustomerInAgent">
    <fieldset>
        <legend>Add New Customer to Agent List</legend>
        <div class="control-group">
            <label for="customerEmail">Customer Email</label>
            <input id="customerEmail" type="text" name="customerEmail">
        </div>
        <input type="hidden" name="action" value="add">
        <input type="submit" class="button button-primary" value="Add to List">
    </fieldset>
</form>
<br><br><br><br><br>
<%
    if(request.getAttribute("customerList")!=null){
        List<Customer> customerList = (List<Customer>) request.getAttribute("customerList");
        Collections.sort(customerList, new Comparator<Customer>() {
            @Override
            public int compare(Customer c1, Customer c2) {
                int result = c1.getFirstName().compareTo(c1.getFirstName());
                if(result!=0){
                    return result;
                }else{
                    return c1.getLastName().compareTo(c1.getLastName());
                }
            }
        });
%>

<table class="table">
    <tr>
        <th>Title</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Country</th>
        <th>Action</th>
    </tr>
    <%for(Customer c:customerList){
    %>
    <tr>
        <td><%=c.getTitle()%></td>
        <td><%=c.getFirstName()+" "+c.getLastName()%></td>
        <td><%=c.getEmail()%></td>
        <td><%=c.getPhone()%></td>
        <td><%=c.getCountry()%></td>
        <td>
            <button class="button button-primary" type="button" onclick="window.location='/AEDCustomerInAgent?action=delete&customerEmail=<%=c.getEmail()%>'">Delete</button>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
}else{
%>
<h1>no Customer in your Agent List</h1>
<%
    }
%>
</body>
</html>
