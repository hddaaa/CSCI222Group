package controller.frontController.profile;

import controller.subSystemFunction.ProfileSystem;
import model.entity.Agent;
import model.entity.Customer;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by hdd on 16/05/15.
 */
@WebServlet(name = "ShowAgentInfoServlet")
public class ShowAgentInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Agent agent = ProfileSystem.agentInfo(user.getUsername());
        List<Customer> customerList = ProfileSystem.showAgentCustomerList(agent.getName());
        request.setAttribute("agent", agent);
        request.setAttribute("customerList", customerList);
        request.getRequestDispatcher("").forward(request, response);
    }
}
