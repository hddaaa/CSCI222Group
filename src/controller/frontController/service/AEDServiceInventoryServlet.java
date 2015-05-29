package controller.frontController.service;

import controller.subSystemFunction.ServiceSystem;
import model.entity.ServiceInventory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hdd on 16/05/15.
 */
@WebServlet(name = "AEDServiceInventoryServlet", urlPatterns = {"/AEDServiceInventory"})
public class AEDServiceInventoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        boolean result = false;
        ServiceInventory serviceInventory = new ServiceInventory();
        switch (action) {
            case "add":
                serviceInventory.setItem(request.getParameter("item"));
                serviceInventory.setCost(Integer.parseInt(request.getParameter("cost")));
                serviceInventory.setAvailability(request.getParameter("availability"));
                ServiceSystem.addInventory(serviceInventory);
                result = true;
                break;
            case "edit":
                serviceInventory.setId(Integer.parseInt(request.getParameter("inventoryId")));
                serviceInventory.setItem(request.getParameter("item"));
                serviceInventory.setCost(Integer.parseInt(request.getParameter("cost")));
                serviceInventory.setAvailability(request.getParameter("availability"));
                result = ServiceSystem.editInventory(serviceInventory);
                break;
            case "delete":
                result = ServiceSystem.deleteInventory(Integer.parseInt(request.getParameter("inventoryId")));
                break;
            default:
        }
        if (result) {
            request.getRequestDispatcher("success.jsp").forward(request,response);
        } else {
            request.setAttribute("errorMessage","Add/Edit/Delete Service Inventory fail");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
