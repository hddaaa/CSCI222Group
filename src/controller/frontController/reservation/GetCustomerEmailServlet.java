package controller.frontController.reservation;

import model.entity.User;
import util.Enum.UserAuthority;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hdd on 28/05/15.
 */
@WebServlet(name = "GetCustomerEmailServlet",urlPatterns = {"/GetCustomerEmail"})
public class GetCustomerEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            response.sendRedirect("index.jsp");
            return;
        }
        if(request.getParameter("action")==null){
            request.setAttribute("errorMessage","show bookings wrong: need action attribute");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
            request.setAttribute("nextAction", "showBookings");

            switch (request.getParameter("action")) {
                case "flight":
                    request.setAttribute("action", "flight");
                    break;
                case "service":
                    request.setAttribute("action", "service");
                    break;
                case "info":
                    request.setAttribute("action", "info");
                    break;
            }
            request.getRequestDispatcher("getCustomerEmailForm.jsp").forward(request, response);

    }
}
