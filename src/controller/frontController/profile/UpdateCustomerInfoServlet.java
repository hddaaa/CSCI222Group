package controller.frontController.profile;

import controller.subSystemFunction.ProfileSystem;
import controller.subSystemFunction.ReservationSystem;
import model.entity.Customer;
import model.entity.User;
import util.Enum.UserAuthority;
import util.common.ParseDateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by hdd on 16/05/15.
 */
@WebServlet(name = "UpdateCustomerInfoServlet",urlPatterns = {"/UpdateCustomerInfo"})
public class UpdateCustomerInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean result=false;
        User user = (User) request.getSession().getAttribute("user");
        String customerEmail = request.getParameter("customerEmail");

        Customer customer = ProfileSystem.customerInfo(customerEmail);
        // if update customer information
        if(request.getParameter("action").equals("updateInfo")) {
            customer.setTitle(request.getParameter("title"));
            customer.setFirstName(request.getParameter("firstName"));
            customer.setLastName(request.getParameter("lastName"));
            customer.setGender(request.getParameter("gender"));
            customer.setDOB(new Date(ParseDateUtil.parseDate(request.getParameter("DOB")).getTime()));
            customer.setPhone(request.getParameter("phone"));
            customer.setEmail(request.getParameter("email"));
            customer.setStreet(request.getParameter("street"));
            if (request.getParameter("state") != null && !request.getParameter("state").equals(""))
                customer.setState(request.getParameter("state"));
            else
                customer.setState(null);
            customer.setCity(request.getParameter("city"));
            customer.setCountry(request.getParameter("country"));
            customer.setPassportHolder(request.getParameter("passportHolder") != null);
            result = ProfileSystem.editCustomer(customer);
            if (user.getAuthority()!= UserAuthority.Customer){
                if (!request.getParameter("email").equals(customerEmail)) {
                    result = result && ReservationSystem.changeUsernameByStaff(user,customerEmail,request.getParameter("email"));
                }
            }else {
                if (!request.getParameter("email").equals(user.getUsername())) {
                    user.setUsername(request.getParameter("email"));
                    result = result && ProfileSystem.editUser(user);
                }
            }

        }
        // if update credit card information
        else if(request.getParameter("action").equals("updateCreditCard")) {
            customer.setCreditCard(request.getParameter("creditCard"));
            customer.setCardNum(request.getParameter("cardNum"));
            result = ProfileSystem.editCustomer(customer);
        }
        if (result) {
            request.getRequestDispatcher("success.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "update customer information error");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
