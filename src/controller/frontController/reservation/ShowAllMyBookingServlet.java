package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if(user.getAuthority()!= UserAuthority.Customer){

        }else{
            List<Map<String, String>> bookingsList = ReservationSystem.getAllBookingsFromACustomer(user.getUsername());
            request.setAttribute("bookingsList",bookingsList);
            request.getRequestDispatcher("showAllMyBooking.jsp").forward(request,response);
        }
    }
}
