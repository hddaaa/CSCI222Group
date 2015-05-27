package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.SeatMap;
import model.entity.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by hdd on 26/05/15.
 */
@WebServlet(name = "ShowBookingDetailServlet",urlPatterns = {"/ShowBookingDetail"})
public class ShowBookingDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int ticketID = Integer.parseInt(request.getParameter("ticketId"));
        Ticket ticket = ReservationSystem.ticketDetail(ticketID);
        request.getSession().setAttribute("ticket",ticket);
        Map<String,String> booking = ReservationSystem.getBookingDetail(ticketID);
        SeatMap seatMap = ReservationSystem.showSeatMap(ticket.getScheduleId());
        request.setAttribute("booking",booking);
        request.setAttribute("seatMap",seatMap);
        request.getRequestDispatcher("showBookingDetail.jsp").forward(request,response);
    }
}
