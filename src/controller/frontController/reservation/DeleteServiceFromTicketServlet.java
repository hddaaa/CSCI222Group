package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hdd on 21/05/15.
 */
@WebServlet(name = "DeleteServiceFromTicketServlet",urlPatterns = {"/DeleteServiceFromTicket"})
public class DeleteServiceFromTicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        if(ReservationSystem.deleteServiceReservation(serviceId)){
            response.sendRedirect("/ShowServiceDetail?ticketId="+ticketId);
        }else{
            request.setAttribute("errorMessage", "delete Service error");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
