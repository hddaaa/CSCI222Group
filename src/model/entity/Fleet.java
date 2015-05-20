package model.entity;

/**
 * Created by hdd on 12/05/15.
 */
public class Fleet extends Entity {
    private String aircraft;
    private int inService;
    private int FClass;
    private int BClass;
    private int PEClass;
    private int EClass;
    private int total;

    public Fleet() {
    }

    public Fleet(String aircraft, int inService, int FClass, int BClass, int PEClass, int EClass, int total) {
        this.aircraft = aircraft;
        this.inService = inService;
        this.FClass = FClass;
        this.BClass = BClass;
        this.PEClass = PEClass;
        this.EClass = EClass;

        this.total = total;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public int getInService() {
        return inService;
    }

    public void setInService(int inService) {
        this.inService = inService;
    }

    public int getFClass() {
        return FClass;
    }

    public void setFClass(int FClass) {
        this.FClass = FClass;
    }

    public int getBClass() {
        return BClass;
    }

    public void setBClass(int BClass) {
        this.BClass = BClass;
    }

    public int getPEClass() {
        return PEClass;
    }

    public void setPEClass(int PEClass) {
        this.PEClass = PEClass;
    }

    public int getEClass() {
        return EClass;
    }

    public void setEClass(int EClass) {
        this.EClass = EClass;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


}
