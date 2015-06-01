package controller.frontController.reservation;

import controller.subSystemFunction.ProfileSystem;
import controller.subSystemFunction.ReservationSystem;
import model.entity.Agent;
import model.entity.Customer;
import model.entity.Ticket;
import model.entity.User;
import util.Enum.UserAuthority;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by hdd on 26/05/15.
 */
@WebServlet(name = "ShowAllMyBookingServlet",urlPatterns = {"/ShowAllMyBookings"})
public class ShowAllMyBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String customerEmail = request.getParameter("customerEmail");
        Customer customer = ReservationSystem.customerDetail(customerEmail);
        if(user.getAuthority()==UserAuthority.Agent){
            Agent agent = ProfileSystem.agentDetail(user.getUsername());
            if(!agent.getName().equals(customer.getTravelAgent())){
                request.setAttribute("errorMessage","this customer not in your Customer List");
                request.getRequestDispatcher("error.jsp").forward(request,response);
                return;
            }
        }
        List<Map<String, String>> bookingsList = ReservationSystem.getAllBookingsFromACustomer(customerEmail);
        request.setAttribute("bookingsList", bookingsList);
        if(request.getParameter("service")!=null)
            request.setAttribute("service",true);
        request.getRequestDispatcher("showAllMyBooking.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            response.sendRedirect("index.jsp");
            return;
        }else if(user.getAuthority()==UserAuthority.Customer && request.getParameter("booking")==null) {
            List<Map<String, String>> bookingsList = ReservationSystem.getAllBookingsFromACustomer(user.getUsername());
            request.setAttribute("bookingsList", bookingsList);
            if (request.getParameter("action").equals("service"))
                request.setAttribute("service", true);
            request.getRequestDispatcher("showAllMyBooking.jsp").forward(request, response);
        }else{
            List<Map<String, String>> bookingsList = ReservationSystem.getAllBookingsFromAUser(user.getUsername());
            request.setAttribute("bookingsList", bookingsList);
            if (request.getParameter("action").equals("service"))
                request.setAttribute("service", true);
            request.getRequestDispatcher("showAllMyBooking.jsp").forward(request, response);
        }
    }
}
