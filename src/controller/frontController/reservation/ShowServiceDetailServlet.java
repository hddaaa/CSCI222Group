package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by hdd on 21/05/15.
 */
@WebServlet(name = "ShowServiceDetailServlet" , urlPatterns = {"/ShowServiceDetail"})
public class ShowServiceDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        List<Service> serviceList = ReservationSystem.getServiceList(ticketId);
        request.setAttribute("serviceList",serviceList);
        request.getRequestDispatcher("showServiceDetail.jsp").forward(request,response);
    }
}
