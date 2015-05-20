package model.entity;

/**
 * Created by hdd on 12/05/15.
 */
public class ServiceInventory extends Entity {
    private String item;
    private int cost;
    private String availability;

    public ServiceInventory() {
    }

    public ServiceInventory(String item, int cost, String availability) {
        this.item = item;
        this.cost = cost;
        this.availability = availability;
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

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
