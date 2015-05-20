package controller.frontController.profile;

import controller.subSystemFunction.ProfileSystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hdd on 17/05/15.
 */
@WebServlet(name = "EditNoFlyStatusServlet")
public class EditNoFlyStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerEmail = request.getParameter("customerEmail");
        String noFlyStatus = request.getParameter("noFly");
        ProfileSystem.editNoFlyStatus(customerEmail, noFlyStatus);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
