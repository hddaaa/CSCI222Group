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
@WebServlet(name = "ServiceReservationServlet", urlPatterns = {"/ServiceReservation"})
public class ServiceReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (request.getParameter("passageNum") == null) {
            String[] serviceAndCost = request.getParameterValues("services");
            Ticket ticket = (Ticket) session.getAttribute("ticket");
            ticket = ReservationSystem.serviceReservation(serviceAndCost, ticket);
            if (ticket != null) {
                session.removeAttribute("ticket");
                request.getRequestDispatcher("success.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "reserve service error");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }else {
            boolean error= false;
            int passageNum = Integer.parseInt(request.getParameter("passageNum"));
            for(int i=0;i<passageNum&&!error;i++){
                String[] serviceAndCost = request.getParameterValues("services" + i);
                Ticket ticket = (Ticket) session.getAttribute("ticket"+i);
                ticket = ReservationSystem.serviceReservation(serviceAndCost, ticket);
                if (ticket == null){
                    error =true;
                }

            }
            if(request.getParameter("return")!=null){
                for(int i=0;i<passageNum&&!error;i++){
                    String[] serviceAndCost = request.getParameterValues("rservices"+i);
                    Ticket ticket = (Ticket) session.getAttribute("rticket"+i);
                    ticket = ReservationSystem.serviceReservation(serviceAndCost, ticket);
                    if (ticket == null){
                        error =true;
                    }

                }
            }
            if (!error) {
                session.removeAttribute("ticket");
                request.getRequestDispatcher("success.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "reserve service error");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
