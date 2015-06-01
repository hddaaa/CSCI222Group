<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 24/05/15
  Time: 10:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="css/fms.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/functions.js"></script>
	</head>
	<body>
<a href="searchScheduleWithoutLogin.jsp?nextStep=login">log in</a>

<form class="form form-aligned" method="post" action="/RegisterServlet">

    <legend>register</legend>
    <div class="control-group">
        <label for="title">title</label>
        <input id="title" type="text" name="title"><br><br>
    </div>

    <div class="control-group">
        <label for="firstName">first name:</label>
        <input id="firstName" type="text" name="firstName"><br><br>
    </div>

    <div class="control-group">
        <label for="lastName">last name:</label>
        <input id="lastName" type="text" name="lastName"><br><br>
    </div>

    <div class="control-group">
        <label for="genderMale">Male:</label>
        <input id="genderMale" type="radio" name="gender" value="Male">
        <label for="genderFemale">Female:</label>
        <input id="genderFemale" type="radio" name="gender" value="Female">
        <br><br>
    </div>

    <div class="control-group">
        <label for="DOB">date of birth:</label>
        <input id="DOB" type="date" name="DOB"><br><br>
    </div>

    <div class="control-group">
        <label for="phone">phone:</label>
        <input id="phone" type="tel" name="phone"><br><br>
    </div>

    <div class="control-group">
        <label for="email">email:</label>
        <input id="email" type="email" name="email"><br><br>
    </div>

    <div class="control-group">
        <label for="street">street:</label>
        <input id="street" type="text" name="street"><br><br>
    </div>

    <div class="control-group">
        <label for="state">state:</label>
        <input id="state" type="text" name="state"><br><br>
    </div>

    <div class="control-group">
        <label for="city">city:</label>
        <input id="city" type="text" name="city"><br><br>
    </div>

    <div class="control-group">
        <label for="country">country:</label>
        <input id="country" type="text" name="country"><br><br>
    </div>

    <div class="control-group">
        <label for="creditCard">credit card type:</label>
        <select id="creditCard" name="creditCard">
            <option value="americanexpress">americanexpress</option>
            <option value="bankcard">bankcard</option>
            <option value="china-unionpay">china-unionpay</option>
            <option value="diners-club-carte-blanche">diners-club-carte-blanche</option>
            <option value="diners-club-enroute">diners-club-enroute</option>
            <option value="diners-club-international">diners-club-international</option>
            <option value="diners-club-us-ca">diners-club-us-ca</option>
            <option value="instapayment">instapayment</option>
            <option value="jcb">jcb</option>
            <option value="laser">laser</option>
            <option value="maestro">maestro</option>
            <option value="mastercard">mastercard</option>
            <option value="solo">solo</option>
            <option value="switch">switch</option>
            <option value="visa">visa</option>
            <option value="visa-electron">visa-electron</option>
        </select><br><br>
    </div>

    <div class="control-group">
        <label for="cardNum">credit card number:</label>
        <input id="cardNum" type="text" name="cardNum"><br><br>
    </div>

    <div class="control-group">
        <label for="passportHolder">passport holder:</label>
        <input id="passportHolder" type="checkbox" name="passportHolder" value="true"><br><br>
    </div>
    Your can login by email and password!
    <br>

    <div class="control-group">
        <label for="pwd">password:</label>
        <input id="pwd" type="password" name="pwd"><br><br>
    </div>
    <input class="button button-primary" type="submit" value="submit">
    </fieldset>
</form>
</body>