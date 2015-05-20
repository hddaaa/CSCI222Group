package model.report;

/**
 * Created by hdd on 17/05/15.
 */
public class CashierReport {
    private int totalIncome;
    private int flightIncome;
    private int serviceIncome;

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    public int getFlightIncome() {
        return flightIncome;
    }

    public void setFlightIncome(int flightIncome) {
        this.flightIncome = flightIncome;
    }

    public int getServiceIncome() {
        return serviceIncome;
    }

    public void setServiceIncome(int serviceIncome) {
        this.serviceIncome = serviceIncome;
    }
}
