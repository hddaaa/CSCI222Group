package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import util.common.ParseDateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by hdd on 28/05/15.
 */
@WebServlet(name = "AddScheduleServlet",urlPatterns = {"/AddSchedule"})
public class AddScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int fleetId = Integer.parseInt(request.getParameter("fleetId"));
        String sourceAirport = request.getParameter("sourceAirport");
        String destinationAirport = request.getParameter("destinationAirport");
        String dTimeStr = request.getParameter("departTime");
        String aTimeStr = request.getParameter("arriveTime");
        Date departTime = ParseDateUtil.parseDateTime(dTimeStr);
        Date arriveTime = ParseDateUtil.parseDateTime(aTimeStr);
        String flightId = ReservationSystem.addSchedule(fleetId,sourceAirport,destinationAirport,departTime,arriveTime);
        if (flightId!=null){
            request.setAttribute("successMessage",flightId);
            request.getRequestDispatcher("success.jsp").forward(request, response);
        }else {
            request.setAttribute("errorMessage","add new Schedule wrong");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
