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
@WebServlet(name = "SwitchSeatServlet",urlPatterns = {"/SwitchSeat"})
public class SwitchSeatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int newSeat = Integer.parseInt(request.getParameter("newSeatNum"));
        Ticket ticket = (Ticket) request.getSession().getAttribute("ticket");
        ticket = ReservationSystem.switchSeat(ticket,newSeat);
        if(ticket!=null){
            request.getRequestDispatcher("success.jsp").forward(request,response);
        }else{
            request.setAttribute("errorMessage","switch seat fail");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
