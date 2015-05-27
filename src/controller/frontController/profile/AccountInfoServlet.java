package controller.frontController.profile;

import controller.subSystemFunction.ProfileSystem;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hdd on 27/05/15.
 */
@WebServlet(name = "AccountInfoServlet",urlPatterns = {"/AccountInfo"})
public class AccountInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String oldpwd = request.getParameter("oldpwd");
        String newpwd = request.getParameter("newpwd");

        User user = (User) request.getSession().getAttribute("user");
        user = ProfileSystem.editUser(user,oldpwd,username,newpwd);
        if (user!=null){
            request.getRequestDispatcher("success.jsp").forward(request,response);
        }else {
            request.setAttribute("errorMessage","Update account info error");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

     }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("username",user.getUsername());
        request.getRequestDispatcher("showAccountInfo.jsp").forward(request,response);
    }
}
