package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.dao.AirportDao;
import model.entity.Schedule;
import util.common.DataNotFoundException;

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
        Date departureDate;
        try {
            departureDate = sdf.parse(request.getParameter("departureDate"));
            List<Schedule> schedules = ReservationSystem.searchSchedule(sourceAirport, destinationAirport, departureDate);
            request.setAttribute("schedules", schedules);
            if(isReturn!=null){
                Date returnDate = sdf.parse(request.getParameter("returnDate"));
                List<Schedule> returnSchedules = ReservationSystem.searchSchedule(destinationAirport, sourceAirport, returnDate);
                request.getSession().setAttribute("return","return");
                request.setAttribute("returnSchedules", returnSchedules);
            }
            request.setAttribute("sourceAirport", AirportDao.getAirport(sourceAirport));
            request.setAttribute("destinationAirport", AirportDao.getAirport(destinationAirport));

            request.getRequestDispatcher("searchScheduleWithoutLogin.jsp").forward(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
