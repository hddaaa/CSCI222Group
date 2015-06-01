<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 28/05/15
  Time: 8:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Service Inventory</title>
</head>
<body>
<form class="form form-aligned" method="post" action="/AEDServiceInventory">
  <fieldset>
    <legend>Add New Service Inventory</legend>
    <div class="control-group">
      <label for="item">Item Name: </label>
      <input id="item" type="text" name="item"><br><br>
    </div>
    <div class="control-group">
      <label for="cost">Item Cost: </label>
      <input id="cost" type="number" name="cost"><br><br>
    </div>
    <div class="control-group">
      <label for="availability">Availability: </label>
      <select id="availability" name="availability">
        <option value="all" selected>all</option>
        <option value="international">international</option>
      </select><br><br>
    </div>
    <input type="hidden" name="action" value="add">
    <input class="button button-primary" type="submit" value="Submit">
  </fieldset>
</form>
</body>
</html>
