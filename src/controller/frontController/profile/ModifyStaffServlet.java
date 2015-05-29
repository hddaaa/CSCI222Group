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
@WebServlet(name = "ModifyStaffServlet",urlPatterns = {"/ModifyStaff"})
public class ModifyStaffServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String oldUsername = request.getParameter("oldUsername");
        UserAuthority userAuthority = UserAuthority.valueOf(request.getParameter("authority"));
        String newUsername = request.getParameter("newUsername");
        String pwd = request.getParameter("pwd");
        User thisUser = ProfileSystem.getStuff(user,oldUsername);
        thisUser.setUsername(newUsername);
        thisUser.setAuthority(userAuthority);
        thisUser.setAvailability(true);
        boolean result = false;
        if(pwd.equals("")){
            result = ProfileSystem.editUser(thisUser);
        }else {
            result = ProfileSystem.editUser(thisUser,pwd);
        }
        if(result){
            request.getRequestDispatcher("success.jsp").forward(request,response);
        }else{
            request.setAttribute("errorMessage","create staff fail");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        User user = (User) request.getSession().getAttribute("user");
        if (user.getUsername().equals(username)){
            request.setAttribute("errorMessage","Can not authority for yourself");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
        User thisUser = ProfileSystem.getStuff(user,username);
        if(thisUser!=null){
            request.setAttribute("thisUser",thisUser);
            request.getRequestDispatcher("/modifyStaff.jsp").forward(request,response);
        }else if(thisUser.getAuthority()==UserAuthority.Admin){
            request.setAttribute("errorMessage","Can not Administrator's Authority");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }else {
            request.setAttribute("errorMessage","Can not find this user");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }

    }
}
