package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.Ticket;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hdd on 15/05/15.
 */
@WebServlet(name = "TicketReservationServlet")
public class TicketReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ticket ticket = new Ticket();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        String fareClass = request.getParameter("fareClass");
        ticket.setUsername(user.getUsername());
        ticket.setScheduleId(scheduleId);
        ticket.setFareClass(fareClass);
        String agentEmail = null;
        if (user.getAuthority() == 2) {

            agentEmail = user.getUsername();
        }
        ticket.setFlightCost(ReservationSystem.getPrice(scheduleId, fareClass, agentEmail));
        ticket.setServiceCost(0);
        ticket.setTotal(ticket.getFlightCost());
        ticket = ReservationSystem.ticketReservation(ticket);
        session.setAttribute("ticket", ticket);
        request.getRequestDispatcher("").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
