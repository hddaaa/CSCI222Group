package controller.subSystemFunction;

import model.dao.AgentDao;
import model.dao.CustomerDao;
import model.dao.TicketDao;
import model.entity.Agent;
import model.entity.Customer;
import model.entity.Entity;
import model.entity.Ticket;
import model.report.AgentReport;
import model.report.CustomerReport;
import model.report.PassengerReport;
import util.common.DataNotFoundException;

import java.util.List;

/**
 * Created by hdd on 15/05/15.
 */
public class ReportSystem {
    public CustomerReport getCustomerReport(String customerEmail) {
        CustomerDao customerDao = new CustomerDao();
        try {

            Customer customer = customerDao.getCustomerByEmail(customerEmail);
            TicketDao ticketDao = new TicketDao();
            List<Ticket> tickets = ticketDao.getAllTicketFormACustomer(customer.getId());
            int tCost = 0, fCost = 0, sCost = 0;
            for (Ticket ticket : tickets) {
                tCost += ticket.getTotal();
                fCost += ticket.getFlightCost();
                sCost += ticket.getServiceCost();
            }
            CustomerReport customerReport = new CustomerReport();
            customerReport.setTotalReservation(tickets.size());
            customerReport.setTotalCost(tCost);
            customerReport.setFlightCost(fCost);
            customerReport.setServiceCost(sCost);
            return customerReport;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AgentReport getAgentReport(String agentEmail) {
        AgentDao agentDao = new AgentDao();

        try {
            Agent agent = agentDao.getAgentByEmail(agentEmail);
            TicketDao ticketDao = new TicketDao();
            List<Ticket> tickets = ticketDao.getAllTicketFormAgent(agent.getId());
            int tCost = 0, fCost = 0, sCost = 0;
            for (Ticket ticket : tickets) {
                tCost += ticket.getTotal();
                fCost += ticket.getFlightCost();
                sCost += ticket.getServiceCost();
            }
            AgentReport agentReport = new AgentReport();
            agentReport.setTotalReservation(tickets.size());
            agentReport.setTotalCost(tCost);
            agentReport.setFlightCost(fCost);
            agentReport.setServiceCost(sCost);

            CustomerDao customerDao = new CustomerDao();
            List<Customer> customers = customerDao.getCustomersByAgent(agent.getName());
            agentReport.setCustomerNum(customers.size());
            return agentReport;
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PassengerReport getPassengerReport() {
        CustomerDao customerDao = new CustomerDao();
        try {
            List<Entity> customers = customerDao.getAllEntity();
            Customer customer = null;
            int isNoFluNum = 0, passportHolder = 0, pWithoutA = 0;
            for (Entity entity : customers) {
                customer = (Customer) entity;
                if (customer.getIsFly() == null | customer.getIsFly().equals("")) {
                    isNoFluNum++;
                }
                if (customer.isPassportHolder()) {
                    passportHolder++;
                }
                if (customer.getTravelAgent() == null | customer.getTravelAgent().equals("")) {
                    pWithoutA++;
                }
            }
            PassengerReport passengerReport = new PassengerReport();
            passengerReport.setPassengerNum(customers.size());
            passengerReport.setNoFlyPassengerNum(isNoFluNum);
            passengerReport.setPassportHolderNum(passportHolder);
            passengerReport.setPassengerWithoutAgentNum(pWithoutA);
            return passengerReport;

        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

}
