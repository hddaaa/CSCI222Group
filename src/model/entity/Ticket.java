package model.entity;

/**
 * Created by hdd on 12/05/15.
 */
public class Ticket extends Entity {
    private int customerId;
    private String username;
    private int scheduleId;
    private String fareClass;
    private int seat;
    private int flightCost;
    private int serviceCost;
    private int total;

    public Ticket() {
    }

    public Ticket(int customerId, String username, int scheduleId, String fareClass, int seat, int flightCost, int serviceCost, int total) {
        this.customerId = customerId;
        this.username = username;
        this.scheduleId = scheduleId;
        this.fareClass = fareClass;
        this.seat = seat;
        this.flightCost = flightCost;
        this.serviceCost = serviceCost;
        this.total = total;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getFareClass() {
        return fareClass;
    }

    public void setFareClass(String fareClass) {
        this.fareClass = fareClass;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getFlightCost() {
        return flightCost;
    }

    public void setFlightCost(int flightCost) {
        this.flightCost = flightCost;
    }

    public int getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(int serviceCost) {
        this.serviceCost = serviceCost;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
