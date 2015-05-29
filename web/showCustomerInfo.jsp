<%@ page import="model.entity.Customer" %>
<%@ page import="util.common.ParseDateUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 27/05/15
  Time: 6:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Customer customer = (Customer) request.getAttribute("customer");
%>
<html>
<head>
    <title>Customer Info</title>
	<link rel="stylesheet" type="text/css" href="css/fms.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
</head>
<body>
<form action="/UpdateCustomerInfo" method="post">
  <fieldset>
    <legend>Customer Info</legend>
    <div class="control-group">
      <label for="title">title</label>
      <input id="title" type="text" name="title" value="<%=customer.getTitle()%>"><br><br>
    </div>

    <div class="control-group">
      <label for="firstName">first name:</label>
      <input id="firstName" type="text" name="firstName" value="<%=customer.getFirstName()%>"><br><br>
    </div>

    <div class="control-group">
      <label for="lastName">last name:</label>
      <input id="lastName" type="text" name="lastName" value="<%=customer.getLastName()%>"><br><br>
    </div>

    <div class="control-group">
      <label for="genderMale">Male:</label>
      <input id="genderMale" type="radio" name="gender" value="Male" <%=customer.getGender().equals("Male")?"checked='checked'":""%>>
      <label for="genderFemale">Female:</label>
      <input id="genderFemale" type="radio" name="gender" value="Female" <%=customer.getGender().equals("Female")?"checked='checked'":""%>>
      <br><br>
    </div>

    <div class="control-group">
      <label for="DOB">date of birth:</label>
      <input id="DOB" type="date" name="DOB"  value="<%=ParseDateUtil.formatDateToyMd(customer.getDOB())%>"><br><br>
    </div>

    <div class="control-group">
      <label for="phone">phone:</label>
      <input id="phone" type="tel" name="phone" value="<%=customer.getPhone()%>"><br><br>
    </div>

    <div class="control-group">
      <label for="email">email:</label>
      <input id="email" type="email" name="email"  value="<%=customer.getEmail()%>"><br><br>
    </div>

    <div class="control-group">
      <label for="street">street:</label>
      <input id="street" type="text" name="street" value="<%=customer.getStreet()%>"><br><br>
    </div>

    <div class="control-group">
      <label for="state">state:</label>
      <input id="state" type="text" name="state" value="<%=customer.getState()==null?"":customer.getState()%>"><br><br>
    </div>

    <div class="control-group">
      <label for="city">city:</label>
      <input id="city" type="text" name="city" value="<%=customer.getCity()%>"><br><br>
    </div>

    <div class="control-group">
      <label for="country">country:</label>
      <input id="country" type="text" name="country" value="<%=customer.getCountry()%>"><br><br>
    </div>

    <div class="control-group">
      <label for="passportHolder">passport holder:</label>
      <input id="passportHolder" type="checkbox" name="passportHolder" value="true" <%=customer.isPassportHolder()?"checked='checked'":""%>><br><br>
    </div>
    <br>
    <input type="hidden" name="action" value="updateInfo">
    <input type="hidden" name="customerEmail" value="<%=customer.getEmail()%>">
    <input class="button button-primary" type="submit" value="update">
  </fieldset>
</form>
</body>
</html>
