package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.Customer;
import model.entity.User;
import util.Enum.UserAuthority;
import util.common.ParseDateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

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
        customer.setDOB(new Date(ParseDateUtil.parseDate(request.getParameter("DOB")).getTime()));
        customer.setPhone(request.getParameter("phone"));
        customer.setEmail(request.getParameter("email"));
        customer.setStreet(request.getParameter("street"));
        if (request.getParameter("state") != null && !request.getParameter("state").equals(""))
            customer.setState(request.getParameter("state"));
        else
            customer.setState(null);
        customer.setCity(request.getParameter("city"));
        customer.setCountry(request.getParameter("country"));
        customer.setCreditCard(request.getParameter("creditCard"));
        customer.setCardNum(request.getParameter("cardNum"));
        customer.setFreqFlierPoint(0);
        customer.setPassportHolder(request.getParameter("passportHolder") != null);
        User user = new User();
        user.setUsername(request.getParameter("email"));
        user.setAuthority(UserAuthority.Customer);
        boolean result = ReservationSystem.register(customer, user, request.getParameter("pwd"));
        //request.setAttribute("registerResult", result);
        if(result) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("homeCustomer.jsp");
        }else {
            PrintWriter out = response.getWriter();
            out.print("register error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
