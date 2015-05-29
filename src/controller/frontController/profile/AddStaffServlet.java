package controller.frontController.profile;

import controller.subSystemFunction.ProfileSystem;
import model.entity.User;
import util.Enum.UserAuthority;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hdd on 29/05/15.
 */
@WebServlet(name = "AddStaffServlet",urlPatterns = {"/AddStaff"})
public class AddStaffServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserAuthority userAuthority = UserAuthority.valueOf(request.getParameter("authority"));
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        User user = (User) request.getSession().getAttribute("user");
        User newUser = ProfileSystem.getStuff(user,username);
        if(newUser!=null){
            request.setAttribute("errorMessage","username already exist");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
        newUser = new User();
        newUser.setUsername(username);
        newUser.setAuthority(userAuthority);
        newUser.setAvailability(true);
        if(ProfileSystem.addStuff(newUser, pwd)) {
            request.getRequestDispatcher("success.jsp").forward(request,response);
        } else {
            request.setAttribute("errorMessage","create staff fail");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
