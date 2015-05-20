package model.entity;

/**
 * Created by hdd on 12/05/15.
 */
public class Service extends Entity {
    private int ticketId;
    private int customerId;
    private String item;
    private int cost;

    public Service() {
    }

    public Service(int ticketId, int customerId, String item, int cost) {
        this.ticketId = ticketId;
        this.customerId = customerId;
        this.item = item;
        this.cost = cost;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
