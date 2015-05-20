package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.Schedule;
import model.entity.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by hdd on 19/05/15.
 */
@WebServlet(name = "SearchServiceForScheduleServlet")
public class SearchServiceForScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Ticket ticket = (Ticket) session.getAttribute("ticket");
        List serviceList = ReservationSystem.getServiceForSchedule(ticket.getScheduleId());
        request.setAttribute("serviceList", serviceList);

    }
}
