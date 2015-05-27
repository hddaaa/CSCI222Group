package controller.frontController.service;

import controller.subSystemFunction.ServiceSystem;
import model.entity.Entity;
import model.entity.ServiceInventory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by hdd on 16/05/15.
 */
@WebServlet(name = "ShowServiceInventoryServlet", urlPatterns = {"/ShowServiceInventory"})
public class ShowServiceInventoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ServiceInventory> inventoryList = ServiceSystem.showServiceInventory();
        request.setAttribute("inventoryList", inventoryList);
        request.getRequestDispatcher("showAllServiceInventory.jsp").forward(request, response);
    }
}
