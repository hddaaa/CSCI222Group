package controller.frontController.reservation;

import controller.subSystemFunction.ReservationSystem;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hdd on 26/05/15.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        User user = ReservationSystem.login(username, pwd);
        if(user==null){
            request.setAttribute("errorMessage","login fail: wrong username or password");
            request.getRequestDispatcher("errorWithoutLogin.jsp").forward(request,response);
        }else{
            request.getSession().setAttribute("user", user);
            switch (user.getAuthority()){
                case Customer:
                    response.sendRedirect("homeCustomer.jsp");
                    break;
                case Agent:
                    response.sendRedirect("homeAgent.jsp");
                    break;
                case Staff:
                case ServiceManager:
                case FlightManager:
                case Admin:
                    response.sendRedirect("homeStaff.jsp");
                    break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
