package controller.frontController.profile;

import controller.subSystemFunction.ProfileSystem;
import model.dao.UserDao;
import model.entity.Agent;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hdd on 29/05/15.
 */
@WebServlet(name = "UpdateAgentInfoServlet",urlPatterns = {"/UpdateAgentInfo"})
public class UpdateAgentInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int agentId = Integer.parseInt(request.getParameter("agentId"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        if(ProfileSystem.updateAgentInfo(agentId,name,email,phone)){
            User user = UserDao.getUser(email);
            request.getSession().setAttribute("user",user);
            request.getRequestDispatcher("success.jsp").forward(request,response);
        }else{
            request.setAttribute("errorMessage", "Update Agent Information Fail");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
