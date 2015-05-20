package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.Fleet;
import model.entity.Route;
import model.entity.Schedule;
import model.entity.User;

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
@WebServlet(name = "ShowFlightDetailServlet")
public class ShowFlightDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        Schedule schedule = ReservationSystem.scheduleDetail(scheduleId);
        Fleet fleet = ReservationSystem.fleetDetail(schedule.getPlane());
        Route route = ReservationSystem.routeDetail(schedule.getRoute());
        List<Integer> map = ReservationSystem.showEmptySeat(scheduleId);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String agentEmail = null;
        if (user.getAuthority() == 2) {

            agentEmail = user.getUsername();
        }
        Map<String, Integer> priceMap = ReservationSystem.getPriceList(route, agentEmail);
        request.setAttribute("schedule", schedule);
        request.setAttribute("fleet", fleet);
        request.setAttribute("route", route);
        request.setAttribute("seatmap", map);
        request.setAttribute("pricemap", priceMap);
        request.getRequestDispatcher("").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));


    }
}
