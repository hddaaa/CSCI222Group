package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hdd on 19/05/15.
 */
@WebServlet(name = "ServiceReservationServlet")
public class ServiceReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] serviceAndCost = request.getParameterValues("serviceCheckbox");
        HttpSession session = request.getSession();
        Ticket ticket = (Ticket) session.getAttribute("ticket");
        ticket = ReservationSystem.serviceReservation(serviceAndCost,ticket);
        session.setAttribute("ticket",ticket);
        request.getRequestDispatcher("").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
