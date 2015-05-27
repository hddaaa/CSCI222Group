<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 27/05/15
  Time: 6:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String creditCard = (String) request.getAttribute("creditCard");
  String cardNum = (String) request.getAttribute("cardNum");
%>
<html>
<head>
    <title>Credit Card Info</title>
</head>
<body>
<form action="/UpdateCustomerInfo" method="post">
  <fieldset>
    <legend>Credit Card Info</legend>
    <div class="control-group">
      <label for="creditCard">credit card type:</label>
      <select id="creditCard" name="creditCard">
        <option value="americanexpress" <%=creditCard.equals("americanexpress")?"selected":""%>>americanexpress</option>
        <option value="bankcard" <%=creditCard.equals("bankcard")?"selected":""%>>bankcard</option>
        <option value="china-unionpay" <%=creditCard.equals("unionpay")?"selected":""%>>china-unionpay</option>
        <option value="diners-club-carte-blanche" <%=creditCard.equals("diners-club-carte-blanche")?"selected":""%>>diners-club-carte-blanche</option>
        <option value="diners-club-enroute" <%=creditCard.equals("diners-club-enroute")?"selected":""%>>diners-club-enroute</option>
        <option value="diners-club-international" <%=creditCard.equals("diners-club-international")?"selected":""%>>diners-club-international</option>
        <option value="diners-club-us-ca" <%=creditCard.equals("diners-club-us-ca")?"selected":""%>>diners-club-us-ca</option>
        <option value="instapayment" <%=creditCard.equals("instapayment")?"selected":""%>>instapayment</option>
        <option value="jcb" <%=creditCard.equals("jcb")?"selected":""%>>jcb</option>
        <option value="laser" <%=creditCard.equals("laser")?"selected":""%>>laser</option>
        <option value="maestro" <%=creditCard.equals("maestro")?"selected":""%>>maestro</option>
        <option value="mastercard" <%=creditCard.equals("mastercard")?"selected":""%>>mastercard</option>
        <option value="solo" <%=creditCard.equals("solo")?"selected":""%>>solo</option>
        <option value="switch" <%=creditCard.equals("switch")?"selected":""%>>switch</option>
        <option value="visa" <%=creditCard.equals("visa")?"selected":""%>>visa</option>
        <option value="visa-electron" <%=creditCard.equals("visa-electron")?"selected":""%>>visa-electron</option>
      </select><br><br>
    </div>

    <div class="control-group">
      <label for="cardNum">credit card number:</label>
      <input id="cardNum" type="text" name="cardNum"  value="<%=cardNum%>"><br><br>
    </div>

    <input type="hidden" name="action" value="updateCreditCard">
    <input class="button button-primary" type="submit" value="update">
  </fieldset>
</form>

</body>
</html>
