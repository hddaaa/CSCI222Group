package controller.frontController.report;

import controller.subSystemFunction.ReportSystem;
import model.entity.User;
import model.report.CustomerReport;
import util.Enum.UserAuthority;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hdd on 21/05/15.
 */
@WebServlet(name = "CustomerReportServlet",urlPatterns = {"/CustomerReport"})
public class CustomerReportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user.getAuthority()== UserAuthority.Customer){
            CustomerReport customerReport = ReportSystem.getCustomerReport(user.getUsername());
            request.setAttribute("customerReport",customerReport);
            request.getRequestDispatcher("showCustomerReport.jsp").forward(request,response);
        }else {
            request.setAttribute("errorMessage","user not a customer");
            request.getRequestDispatcher("error").forward(request,response);
        }
    }
}
