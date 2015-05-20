package controller.frontController.profile;

import controller.subSystemFunction.ProfileSystem;
import model.entity.Customer;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hdd on 16/05/15.
 */
@WebServlet(name = "ShowCustomerInfoServlet")
public class ShowCustomerInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getAuthority() == 1) {
            Customer customer = ProfileSystem.customerInfo(user.getUsername());
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("").forward(request, response);
        }

    }
}
