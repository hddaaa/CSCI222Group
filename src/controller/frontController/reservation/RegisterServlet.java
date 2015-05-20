package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.Customer;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by hdd on 15/05/15.
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer customer = new Customer();
        customer.setTitle(request.getParameter("title"));
        customer.setFirstName(request.getParameter("firstName"));
        customer.setLastName(request.getParameter("lastName"));
        customer.setGender(request.getParameter("gender"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            customer.setDOB(new Date(sdf.parse(request.getParameter("DOB")).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        customer.setPhone(request.getParameter("phone"));
        customer.setEmail(request.getParameter("email"));
        customer.setStreet(request.getParameter("street"));
        if (request.getParameter("state").equals(""))
            customer.setState(null);
        else
            customer.setState(request.getParameter("state"));
        customer.setCity(request.getParameter("city"));
        customer.setCountry(request.getParameter("country"));
        customer.setCreditCard(request.getParameter("creditCard"));
        customer.setCardNum(request.getParameter("cardNum"));
        customer.setFreqFlierPoint(0);
        customer.setPassportHolder((request.getParameter("passportHolder")).equals("true") ? true : false);
        customer.setTravelAgent(request.getParameter("travelAgent"));
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setAuthority(1);
        boolean result = ReservationSystem.register(customer, user, request.getParameter("pwd"));
        request.setAttribute("registerResult", result);
        request.getRequestDispatcher("").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
