package model.report;

import java.util.List;

/**
 * Created by hdd on 17/05/15.
 */
public class MonthlyBookingReport {
    private List MonthlyReservation;
    private List MonthlyIncome;
    private List MonthlyFlightIncome;
    private List MonthlyServiceIncome;

    public List getMonthlyReservation() {
        return MonthlyReservation;
    }

    public void setMonthlyReservation(List monthlyReservation) {
        MonthlyReservation = monthlyReservation;
    }

    public List getMonthlyIncome() {
        return MonthlyIncome;
    }

    public void setMonthlyIncome(List monthlyIncome) {
        MonthlyIncome = monthlyIncome;
    }

    public List getMonthlyFlightIncome() {
        return MonthlyFlightIncome;
    }

    public void setMonthlyFlightIncome(List monthlyFlightIncome) {
        MonthlyFlightIncome = monthlyFlightIncome;
    }

    public List getMonthlyServiceIncome() {
        return MonthlyServiceIncome;
    }

    public void setMonthlyServiceIncome(List monthlyServiceIncome) {
        MonthlyServiceIncome = monthlyServiceIncome;
    }
}
