package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.Schedule;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by hdd on 29/05/15.
 */
@WebServlet(name = "SearchScheduleForModifyServlet", urlPatterns = {"/SearchScheduleForModify"})
public class SearchScheduleForModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sourceAirport = request.getParameter("sourceAirport");
        String destinationAirport = request.getParameter("destinationAirport");
        List<Schedule> schedules = ReservationSystem.searchScheduleForModify(sourceAirport,destinationAirport);
        request.setAttribute("action","modify");
        request.setAttribute("schedules", schedules);
        request.setAttribute("sourceAirport", ReservationSystem.airportDetail(sourceAirport));
        request.setAttribute("destinationAirport", ReservationSystem.airportDetail(destinationAirport));
        request.getRequestDispatcher("showScheduleSearchResult.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("action","modify");
        request.getRequestDispatcher("/searchScheduleForm.jsp").forward(request,response);
    }
}
