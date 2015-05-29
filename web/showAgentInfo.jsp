<%@ page import="model.entity.Agent" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 29/05/15
  Time: 8:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Agent agent = (Agent) request.getAttribute("agent");
%>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/UpdateAgentInfo" method="post">
  <fieldset>
    <legend>Customer Info</legend>
    <div class="control-group">
      <label for="name">Name</label>
      <input id="name" type="text" name="name" value="<%=agent.getName()%>"><br><br>
    </div>

    <div class="control-group">
      <label for="email">first name:</label>
      <input id="email" type="email" name="email" value="<%=agent.getEmail()%>"><br><br>
    </div>

    <div class="control-group">
      <label for="phone">last name:</label>
      <input id="phone" type="text" name="phone" value="<%=agent.getPhone()%>"><br><br>
    </div>

    <input type="hidden" name="agentId" value="<%=agent.getId()%>">
    <input class="button button-primary" type="submit" value="update">
  </fieldset>
</form>
</body>
</html>
