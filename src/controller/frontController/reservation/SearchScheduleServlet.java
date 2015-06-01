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
        Date departureDate=ParseDateUtil.parseDate(request.getParameter("departureDate"));
        List<Schedule> schedules = ReservationSystem.searchSchedule(sourceAirport, destinationAirport, departureDate);
        request.setAttribute("schedules", schedules);
        if(isReturn!=null){
            Date returnDate = ParseDateUtil.parseDate(request.getParameter("returnDate"));
            List<Schedule> returnSchedules = ReservationSystem.searchSchedule(destinationAirport, sourceAirport, returnDate);
            request.setAttribute("returnSchedules", returnSchedules);
        }
        int passageNum = Integer.parseInt(request.getParameter("passageNum"));
        request.setAttribute("passageNum",passageNum);
        request.setAttribute("sourceAirport", ReservationSystem.airportDetail(sourceAirport));
        request.setAttribute("destinationAirport", ReservationSystem.airportDetail(destinationAirport));
        if (request.getSession().getAttribute("user")==null){
            request.setAttribute("nextStep","scheduleSearch");
            request.getRequestDispatcher("searchScheduleWithoutLogin.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("showScheduleSearchResult.jsp").forward(request,response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
