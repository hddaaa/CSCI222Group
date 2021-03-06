package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.Route;
import model.entity.Schedule;
import util.common.ParseDateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by hdd on 29/05/15.
 */
@WebServlet(name = "ModifyScheduleServlet",urlPatterns = {"/ModifySchedule"})
public class ModifyScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        int fleetId = Integer.parseInt(request.getParameter("fleetId"));
        String sourceAirport = request.getParameter("sourceAirport");
        String destinationAirport = request.getParameter("destinationAirport");
        String dTimeStr = request.getParameter("departTime");
        String aTimeStr = request.getParameter("arriveTime");
        Date departTime = ParseDateUtil.parseDateTime(dTimeStr);
        Date arriveTime = ParseDateUtil.parseDateTime(aTimeStr);
        boolean result = ReservationSystem.modifySchedule(scheduleId,fleetId,sourceAirport,destinationAirport,departTime,arriveTime);
        if (result){
            request.getRequestDispatcher("success.jsp").forward(request, response);
        }else {
            request.setAttribute("errorMessage","modify Schedule wrong");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
        Schedule schedule = ReservationSystem.scheduleDetail(scheduleId);
        Route route = ReservationSystem.routeDetail(schedule.getRoute());
        String departTime = ParseDateUtil.formatTime(schedule.getDepartTime());
        String arriveTime = ParseDateUtil.formatTime(schedule.getArrivedTime());
        request.setAttribute("schedule",schedule);
        request.setAttribute("departTime",departTime);
        request.setAttribute("arriveTime",arriveTime);
        request.setAttribute("route",route);
        request.getRequestDispatcher("modifySchedule.jsp").forward(request,response);
    }
}
