package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.Schedule;
import util.common.ParseDateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by hdd on 1/06/15.
 */
@WebServlet(name = "SearchScheduleForChangeFlightServlet", urlPatterns = {"/SearchScheduleForChangeFlight"})
public class SearchScheduleForChangeFlightServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sourceAirport = request.getParameter("sourceAirport");
        String destinationAirport = request.getParameter("destinationAirport");
        Date departureDate= ParseDateUtil.parseDate(request.getParameter("departureDate"));
        List<Schedule> schedules = ReservationSystem.searchSchedule(sourceAirport, destinationAirport,departureDate);
        request.setAttribute("action", "change");
        request.setAttribute("schedules", schedules);
        request.setAttribute("sourceAirport", ReservationSystem.airportDetail(sourceAirport));
        request.setAttribute("destinationAirport", ReservationSystem.airportDetail(destinationAirport));
        request.getRequestDispatcher("/showScheduleSearchResult.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("action", "change");
        request.getRequestDispatcher("/searchScheduleForm.jsp").forward(request, response);
    }
}
