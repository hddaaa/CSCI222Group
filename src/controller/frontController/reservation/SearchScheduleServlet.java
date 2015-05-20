package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.Schedule;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by hdd on 15/05/15.
 */
@WebServlet(name = "SearchScheduleServlet")
public class SearchScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sourceAirport = request.getParameter("sourceAirport");
        String destinationAirport = request.getParameter("destinationAirport");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date departureDate;
        try {
            departureDate = sdf.parse(request.getParameter("departureDate"));
            List<Schedule> schedules = ReservationSystem.searchSchedule(sourceAirport, destinationAirport, departureDate);
            request.setAttribute("schedules", schedules);
            request.getRequestDispatcher("").forward(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
