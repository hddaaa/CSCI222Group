package controller.frontController.profile;

import controller.subSystemFunction.ProfileSystem;
import model.entity.Customer;
import model.entity.User;
import util.Enum.UserAuthority;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hdd on 27/05/15.
 */
@WebServlet(name = "ShowCreditCardInfoServlet",urlPatterns = {"/ShowCreditCardInfo"})
public class ShowCreditCardInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getAuthority() == UserAuthority.Customer) {
            Customer customer = ProfileSystem.customerInfo(user.getUsername());
            request.setAttribute("creditCard", customer.getCreditCard());
            request.setAttribute("cardNum",customer.getCardNum());
            request.getRequestDispatcher("showCreditCardInfo.jsp").forward(request,response);
        }else {
            request.setAttribute("errorMessage","show customer credit information error: wrong authority");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }
}
