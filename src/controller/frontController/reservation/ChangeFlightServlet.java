package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.*;
import util.Enum.UserAuthority;
import util.common.ParseDateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by hdd on 21/05/15.
 */
@WebServlet(name = "ChangeFlightServlet", urlPatterns = {"/ChangeFlight"})
public class ChangeFlightServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        int seatNum = Integer.parseInt(request.getParameter("seatNum"));
        String fareClass = request.getParameter("fareClass");
        Ticket ticket = (Ticket) request.getSession().getAttribute("ticket");
        request.getSession().removeAttribute("ticket");
        String agentEmail=null;
        if(user.getAuthority()==UserAuthority.Agent){
            agentEmail= user.getUsername();
        }
        ticket = ReservationSystem.changeFlight(ticket,user.getUsername(),scheduleId,seatNum,fareClass,agentEmail);
        if (ticket!=null){
            request.getRequestDispatcher("success.jsp").forward(request, response);
        }else {
            request.setAttribute("errorMessage","change flight wrong");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        Schedule schedule = ReservationSystem.scheduleDetail(scheduleId);
        Fleet fleet = ReservationSystem.fleetDetail(schedule.getPlane());
        Route route = ReservationSystem.routeDetail(schedule.getRoute());
        SeatMap map = ReservationSystem.showSeatMap(scheduleId);
        Airport sourceAirport = ReservationSystem.airportDetail(route.getSourceAirport());
        Airport destinationAirport = ReservationSystem.airportDetail(route.getDestinationAirport());
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String agentEmail = null;
        if (user.getAuthority() == UserAuthority.Agent) {

            agentEmail = user.getUsername();
        }
        Map<String, Integer> priceMap = ReservationSystem.getPriceList(route, agentEmail);
        request.setAttribute("schedule", schedule);
        request.setAttribute("fleet", fleet);
        request.setAttribute("route", route);
        request.setAttribute("seatmap", map);
        request.setAttribute("sourceAirport", sourceAirport);
        request.setAttribute("destinationAirport", destinationAirport);
        request.setAttribute("pricemap", priceMap);
        request.getRequestDispatcher("showFlightForChange.jsp").forward(request,response);
    }
}
