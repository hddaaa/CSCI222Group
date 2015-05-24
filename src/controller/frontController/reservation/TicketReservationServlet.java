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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hdd on 15/05/15.
 */
@WebServlet(name = "TicketReservationServlet", urlPatterns = {"/TicketReservation"})
public class TicketReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //read post form
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        String fareClass = request.getParameter("fareClass");
        int seatNum = Integer.parseInt(request.getParameter("seatNum"));
        //get user
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //if not login ,then goto register page
        if(user==null){
            session.setAttribute("Process","TicketReservation");
            session.setAttribute("scheduleId",scheduleId);
            session.setAttribute("fareClass",fareClass);
            session.setAttribute("seatNum",seatNum);
            request.setAttribute("nextStep","register");
            request.getRequestDispatcher("searchScheduleWithoutLogin.jsp").forward(request, response);
        }
        //create new ticket
        Ticket ticket = new Ticket();
        ticket.setUsername(user.getUsername());
        ticket.setScheduleId(scheduleId);
        ticket.setFareClass(fareClass);
        ticket.setSeat(seatNum);
        String agentEmail = null;
        if (user!=null&&user.getAuthority() == UserAuthority.Agent) {

            agentEmail = user.getUsername();
        }
        ticket.setFlightCost(ReservationSystem.getPrice(scheduleId, fareClass, agentEmail));
        ticket.setServiceCost(0);
        ticket.setTotal(ticket.getFlightCost());
        ticket = ReservationSystem.ticketReservation(ticket);
        //keep ticket in session
        session.setAttribute("ticket", ticket);
        request.getRequestDispatcher("").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
