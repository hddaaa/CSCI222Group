package controller.subSystemFunction;

import model.dao.AgentDao;
import model.dao.CustomerDao;
import model.entity.Agent;
import model.entity.Customer;
import util.common.DataNotFoundException;

import java.util.List;

/**
 * Created by hdd on 15/05/15.
 */
public class ProfileSystem {
    public static Customer customerInfo(String customerEmail) {
        CustomerDao customerDao = new CustomerDao();
        try {
            Customer customer = customerDao.getCustomerByEmail(customerEmail);
            return customer;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean editCustomer(Customer customer) {
        CustomerDao customerDao = new CustomerDao();
        return customerDao.updateEntity(customer);
    }

    public static Agent agentInfo(String agentEmail) {
        AgentDao agentDao = new AgentDao();
        try {
            Agent agent = agentDao.getAgentByEmail(agentEmail);
            return agent;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Customer> showAgentCustomerList(String agentName) {
        CustomerDao customerDao = new CustomerDao();
        try {
            List<Customer> customers = customerDao.getCustomersByAgent(agentName);
            return customers;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addCustomerToAgent(String customerEmail, String agentName) {
        CustomerDao customerDao = new CustomerDao();
        try {
            Customer customer = customerDao.getCustomerByEmail(customerEmail);
            customer.setTravelAgent(agentName);
            return customerDao.updateEntity(customer);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delCustomerFromAgent(String customerEmail) {
        CustomerDao customerDao = new CustomerDao();
        try {
            Customer customer = customerDao.getCustomerByEmail(customerEmail);
            customer.setTravelAgent("");
            return customerDao.updateEntity(customer);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean editNoFlyStatus(String customerEmail, String isFly) {
        CustomerDao customerDao = new CustomerDao();
        try {
            Customer customer = customerDao.getCustomerByEmail(customerEmail);
            customer.setIsFly(isFly);
            return customerDao.updateEntity(customer);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
