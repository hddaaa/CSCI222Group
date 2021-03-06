package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.dao.AirportDao;
import model.entity.*;
import util.Enum.UserAuthority;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by hdd on 16/05/15.
 */
@WebServlet(name = "ShowFlightDetailServlet", urlPatterns = {"/ShowFlightDetail"})
public class ShowFlightDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        int passageNum = Integer.parseInt(request.getParameter("passageNum"));
        Schedule schedule = ReservationSystem.scheduleDetail(scheduleId);
        Fleet fleet = ReservationSystem.fleetDetail(schedule.getPlane());
        Route route = ReservationSystem.routeDetail(schedule.getRoute());
        SeatMap map = ReservationSystem.showSeatMap(scheduleId);
        Airport sourceAirport = ReservationSystem.airportDetail(route.getSourceAirport());
        Airport destinationAirport = ReservationSystem.airportDetail(route.getDestinationAirport());
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String agentEmail = null;
        if (user!=null && user.getAuthority() == UserAuthority.Agent) {

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
        request.setAttribute("passageNum",passageNum);
        if(request.getParameter("return")!=null){
            int rscheduleId = Integer.parseInt(request.getParameter("returnScheduleId"));
            Schedule rschedule = ReservationSystem.scheduleDetail(rscheduleId);
            Fleet rfleet = ReservationSystem.fleetDetail(rschedule.getPlane());
            Route rroute = ReservationSystem.routeDetail(rschedule.getRoute());
            SeatMap rmap = ReservationSystem.showSeatMap(rscheduleId);
            Airport rsourceAirport = ReservationSystem.airportDetail(rroute.getSourceAirport());
            Airport rdestinationAirport = ReservationSystem.airportDetail(rroute.getDestinationAirport());

            Map<String, Integer> rpriceMap = ReservationSystem.getPriceList(rroute, agentEmail);
            request.setAttribute("rschedule", rschedule);
            request.setAttribute("rfleet", rfleet);
            request.setAttribute("rroute", rroute);
            request.setAttribute("rseatmap", rmap);
            request.setAttribute("rsourceAirport", rsourceAirport);
            request.setAttribute("rdestinationAirport", rdestinationAirport);
            request.setAttribute("rpricemap", rpriceMap);
            request.setAttribute("return",true);
        }



        if (request.getSession().getAttribute("user")==null){
            request.setAttribute("nextStep","flightDetail");
            request.getRequestDispatcher("searchScheduleWithoutLogin.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("showFlightDetail.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));


    }
}
