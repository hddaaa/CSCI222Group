package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hdd on 21/05/15.
 */
@WebServlet(name = "ChangeFlightServlet", urlPatterns = {"/ChangeFlight"})
public class ChangeFlightServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        request.getSession().setAttribute("ticket", ReservationSystem.ticketDetail(ticketId));

    }
}
