package controller.frontController.profile;

import controller.subSystemFunction.ProfileSystem;
import model.entity.Agent;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hdd on 17/05/15.
 */
@WebServlet(name = "AEDCustomerInAgentServlet")
public class AEDCustomerInAgentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Agent agent = ProfileSystem.agentInfo(user.getUsername());
        String action = request.getParameter("action");
        String customerEmail = request.getParameter("email");
        if (action.equals("add")) {
            ProfileSystem.addCustomerToAgent(customerEmail, agent.getName());
        } else if (action.equals("delete")) {
            ProfileSystem.delCustomerFromAgent(customerEmail);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
