<%@ page import="model.entity.User" %>
<%@ page import="model.entity.Customer" %>
<%@ page import="model.dao.CustomerDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  if(session.getAttribute("user")==null){
    response.sendRedirect("index.jsp");
  }
  User user = (User) session.getAttribute("user");
  Customer customer = CustomerDao.getCustomerByEmail(user.getUsername());
%>
<!DOCTYPE html>
<html>
<head>
  <title>Sunset Airways - My Account</title>
  <link rel="stylesheet" href="css/fms.css">
  <link rel="stylesheet" href="css/font-awesome.min.css">
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="js/functions.js"></script>
</head>
<body>
<div class="header">
  <div class="navbar">
    <a href="/#"> Home </a>
    <i class="fa fa-chevron-right"></i>
    <a href="#"> My Account </a>
    <i class="fa fa-chevron-right"></i>
    <a href="#" id="t1"> Reservations </a>
    <i class="fa fa-chevron-right"></i>
    <a href="#" id="t2"> Search for Flight </a>
  </div>
  <div class="nav_line">
    <div class="_1"></div>
    <div class="_3"></div>
    <div class="_1"></div>
    <div class="_2"></div>
    <div class="_1"></div>
  </div>
  <div class="heading">
    <div class="col">
      <div class="logo"><i class="fa fa-plane"></i> Sunset Airways </div>
      <div class="account">
        <i class="fa fa-user"></i>
        <span class="title"> <%=customer.getTitle()%></span>
        <span class="name"> <%=customer.getLastName()%> </span>
        <span class="id"> (<%=customer.getId()%>) </span>
        <span class="menu"><a href="/Logout">Log Out <i class="fa fa-sign-out"></i></a></span>
      </div>
    </div>
  </div>
</div>
<div class="page_title">
  <div class="container" id="page_title">
    Search for Flight
  </div>
</div>
<div class="contents">
  <div class="container">
    <ul class="acc_nav">
      <li id="t1_1" class="active"><a href='#' class='push_button blue'>Reservations</a></li>
      <li id="t1_2"><a href='#' class='push_button blue'>Profile</a></li>
      <li id="t1_3"><a href='#' class='push_button blue'>Services</a></li>
      <li id="t1_4"><a href='#' class='push_button blue'>Reports</a></li>
    </ul>
    <div class="outer_frame">
      <div class="nav_pane">
        <ul id="t2_1" class="active">
          <li id="res_1" class="active"><a href='searchScheduleForm.jsp' target="iframe" class="push_button blue">Search for Flight</a></li>
          <li id="res_2"><a href='/ShowAllMyBookings' target="iframe" class="push_button blue">My Bookings</a></li>
        </ul>
        <ul id="t2_2">
          <li id="pro_1"><a href='#' target="iframe" class="push_button blue">Account Settings</a></li>
          <li id="pro_2"><a href='#' target="iframe" class="push_button blue">Personal Information</a></li>
          <li id="pro_3"><a href='#' target="iframe" class="push_button blue">Credit Card</a></li>
        </ul>
        <ul id="t2_3">
          <li id="ser_1"><a href='#' target="iframe" class="push_button blue">Our Services</a></li>
          <li id="ser_2"><a href='#' target="iframe" class="push_button blue">Change Flight Services</a></li>
        </ul>
        <ul id="t2_4">
          <li id="rep_1"><a href='#' target="iframe" class="push_button blue">My Report</a></li>
        </ul>
      </div>
      <div class="inner_frame">
        <%
          if (session.getAttribute("process")!=null&&session.getAttribute("process").equals("ticketReservation")){
            out.print("<iframe id='iframe' name='iframe' src='/TicketReservation' frameborder='0' scrolling='no' width='100%' onload='reset();' height='500px'></iframe>");
          }else{
            out.print("<iframe id='iframe' name='iframe' src='searchScheduleForm.jsp' frameborder='0' scrolling='no' width='100%' onload='reset();' height='500px'></iframe>");
          }
        %>
      </div>
    </div>
  </div>
</div>
</body>
</html>