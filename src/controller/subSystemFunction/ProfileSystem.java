package controller.subSystemFunction;

import model.dao.AgentDao;
import model.dao.CustomerDao;
import model.dao.UserDao;
import model.entity.Agent;
import model.entity.Customer;
import model.entity.User;
import util.Enum.UserAuthority;
import util.common.DataNotFoundException;

import java.util.List;

/**
 * Created by hdd on 15/05/15.
 */
public class ProfileSystem {
    public static Customer customerInfo(String customerEmail) {
        try {
            Customer customer = CustomerDao.getCustomerByEmail(customerEmail);
            return customer;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean editCustomer(Customer customer) {

        return CustomerDao.updateCustomer(customer);
    }

    public static boolean editUser(User user){
        return UserDao.updateUser(user);
    }

    public static User editUser(User user,String oldpwd,String newUsername,String newpwd){

        if (UserDao.updateUser(user, oldpwd, newUsername, newpwd)){
            if(user.getAuthority()== UserAuthority.Customer) {
                try {
                    Customer customer = CustomerDao.getCustomerByEmail(user.getUsername());
                    customer.setEmail(newUsername);
                    if (!CustomerDao.updateCustomer(customer)){
                        return null;
                    }
                } catch (DataNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            user.setUsername(newUsername);
            return user;
        }
        return null;
    }


    public static Agent agentInfo(String agentEmail) {
        try {
            Agent agent = AgentDao.getAgentByEmail(agentEmail);
            return agent;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Customer> showAgentCustomerList(String agentName) {
        try {
            List<Customer> customers = CustomerDao.getCustomersByAgent(agentName);
            return customers;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addCustomerToAgent(String customerEmail, String agentName) {
        try {
            Customer customer = CustomerDao.getCustomerByEmail(customerEmail);
            customer.setTravelAgent(agentName);
            return CustomerDao.updateCustomer(customer);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delCustomerFromAgent(String customerEmail) {
        try {
            Customer customer = CustomerDao.getCustomerByEmail(customerEmail);
            customer.setTravelAgent("");
            return CustomerDao.updateCustomer(customer);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean editNoFlyStatus(String customerEmail, String isFly) {
        try {
            Customer customer = CustomerDao.getCustomerByEmail(customerEmail);
            customer.setIsFly(isFly);
            return CustomerDao.updateCustomer(customer);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
