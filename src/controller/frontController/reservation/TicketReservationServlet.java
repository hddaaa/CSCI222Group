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
        int scheduleId = -1, seatNum = -1;
        String fareClass = null;
        //get user
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //if not login ,then goto register page
        if (user == null) {
            session.setAttribute("process", "ticketReservation");
            session.setAttribute("scheduleId", Integer.parseInt(request.getParameter("scheduleId")));
            session.setAttribute("fareClass", request.getParameter("fareClass"));
            session.setAttribute("seatNum", Integer.parseInt(request.getParameter("seatNum")));
            request.setAttribute("nextStep", "register");
            request.getRequestDispatcher("searchScheduleWithoutLogin.jsp").forward(request, response);
            return;
        } else if (session.getAttribute("process") != null && session.getAttribute("process").equals("ticketReservation")) {
            //read post form from session
            scheduleId = (int) session.getAttribute("scheduleId");
            fareClass = (String) session.getAttribute("fareClass");
            seatNum = (int) session.getAttribute("seatNum");
            session.removeAttribute("process");
            session.removeAttribute("scheduleId");
            session.removeAttribute("fareClass");
            session.removeAttribute("seatNum");
        } else {
            //read post form
            scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
            fareClass = request.getParameter("fareClass");
            seatNum = Integer.parseInt(request.getParameter("seatNum"));
        }
        if (scheduleId != -1 && seatNum != -1 && fareClass != null) {
            //create new ticket
            Ticket ticket = new Ticket();
            ticket.setUsername(user.getUsername());
            ticket.setCustomerId(-1);
            ticket.setScheduleId(scheduleId);
            ticket.setFareClass(fareClass);
            ticket.setSeat(seatNum);
            String agentEmail = null;
            if (user != null && user.getAuthority() == UserAuthority.Agent) {
                agentEmail = user.getUsername();
            }
            ticket.setFlightCost(ReservationSystem.getPrice(scheduleId, fareClass, agentEmail));
            ticket.setServiceCost(0);
            ticket.setTotal(ticket.getFlightCost());
            ticket = ReservationSystem.ticketReservation(ticket,request.getParameter("customerEmail"));
            //keep ticket in session
            if (ticket != null) {
                session.setAttribute("ticket", ticket);
                request.getRequestDispatcher("/SearchServiceForSchedule").forward(request, response);
                return;
            }
        }
        request.setAttribute("errorMessage", "ticket reservation fail: invalid data");
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
