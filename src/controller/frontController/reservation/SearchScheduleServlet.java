package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.dao.AirportDao;
import model.entity.Schedule;
import util.common.DataNotFoundException;
import util.common.ParseDateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by hdd on 15/05/15.
 */
@WebServlet(name = "SearchScheduleServlet", urlPatterns = {"/SearchSchedule"})
public class SearchScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sourceAirport = request.getParameter("sourceAirport");
        String destinationAirport = request.getParameter("destinationAirport");
        String[] isReturn = request.getParameterValues("isReturn");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        request.getSession().setAttribute("return",null);
        Date departureDate=ParseDateUtil.parseDate(request.getParameter("departureDate"));
        List<Schedule> schedules = ReservationSystem.searchSchedule(sourceAirport, destinationAirport, departureDate);
        request.setAttribute("schedules", schedules);
        if(isReturn!=null){
            Date returnDate = ParseDateUtil.parseDate(request.getParameter("returnDate"));
            List<Schedule> returnSchedules = ReservationSystem.searchSchedule(destinationAirport, sourceAirport, returnDate);
            request.getSession().setAttribute("return","return");
            request.setAttribute("returnSchedules", returnSchedules);
        }
        request.setAttribute("sourceAirport", ReservationSystem.airportDetail(sourceAirport));
        request.setAttribute("destinationAirport", ReservationSystem.airportDetail(destinationAirport));
        request.setAttribute("nextStep","scheduleSearch");
        request.getRequestDispatcher("searchScheduleWithoutLogin.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
