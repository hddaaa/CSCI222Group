package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.Schedule;
import model.entity.ServiceInventory;
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
@WebServlet(name = "SearchServiceForScheduleServlet", urlPatterns = {"/SearchServiceForSchedule"})
public class SearchServiceForScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ServiceInventory> serviceList=null;
        if(request.getParameter("ticketId")!=null){
            int ticketId = Integer.parseInt(request.getParameter("ticketId"));
            request.getSession().setAttribute("ticket",ReservationSystem.ticketDetail(ticketId));
            serviceList=ReservationSystem.getServiceNotInTicket(ticketId);
        }else {
            HttpSession session = request.getSession();
            Ticket ticket = (Ticket) session.getAttribute("ticket0");
            serviceList = ReservationSystem.getServiceForSchedule(ticket.getScheduleId());
            request.setAttribute("return",request.getSession().getAttribute("return"));
            request.setAttribute("passageNum",request.getSession().getAttribute("passageNum"));
        }
        request.setAttribute("serviceList", serviceList);
        request.getRequestDispatcher("showSearchServiceResult.jsp").forward(request, response);
    }
}
