<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 1/06/15
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="css/fms.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/functions.js"></script>
</head>
<form class="form form-aligned" method="post" action="/TicketReservation">
<%
  int passageNum = (int)request.getAttribute("passageNum");
  for(int i =0;i<passageNum;i++){

%>

<div class="control-group">
  <label for="passageEmail<%=i%>">Passager Email</label>
  <input id="passageEmail<%=i%>" name="passageEmail<%=i%>" type="text" value="<%=(i==0&&request.getAttribute("MyEmail")!=null)?((String)request.getAttribute("MyEmail")):""%>">
</div>

<%
  }
%>
  <button type="submit" class="button button-primary">Next</button>
</form>