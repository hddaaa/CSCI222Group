<%--
  Created by IntelliJ IDEA.
  User: hdd
  Date: 25/05/15
  Time: 12:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="box login">
    <h2>Login</h2>

    <form class="form form-aligned" method="post" action="">
        <fieldset>
            <div class="control-group">
                <label for="username">Username</label>
                <input id="username" type="text" placeholder="Username">
            </div>
            <div class="control-group">
                <label for="password">Password</label>
                <input id="password" type="password">
            </div>
            <div class="controls">
                <button type="submit" class="button button-primary">Login</button>
                <button type="button" class="button button-primary" onclick="parent.location='searchScheduleWithoutLogin.jsp?nextStep=register'">Register</button>
            </div>
        </fieldset>
    </form>
</div>
