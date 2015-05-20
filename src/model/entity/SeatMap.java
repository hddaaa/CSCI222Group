package model.entity;

import java.util.List;

/**
 * Created by hdd on 14/05/15.
 */
public class SeatMap extends Entity {
    private int scheduleId;
    private List map;
    private int fClassSpare;
    private int bClassSpare;
    private int peClassSpare;
    private int eClassSpare;

    public SeatMap() {
    }

    public SeatMap(int scheduleId, List map, int fClassSpare, int bClassSpare, int peClassSpare, int eClassSpare) {
        this.scheduleId = scheduleId;
        this.map = map;
        this.fClassSpare = fClassSpare;
        this.bClassSpare = bClassSpare;
        this.peClassSpare = peClassSpare;
        this.eClassSpare = eClassSpare;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public List getMap() {
        return map;
    }

    public void setMap(List map) {
        this.map = map;
    }

    public int getfClassSpare() {
        return fClassSpare;
    }

    public void setfClassSpare(int fClassSpare) {
        this.fClassSpare = fClassSpare;
    }

    public int getbClassSpare() {
        return bClassSpare;
    }

    public void setbClassSpare(int bClassSpare) {
        this.bClassSpare = bClassSpare;
    }

    public int getPeClassSpare() {
        return peClassSpare;
    }

    public void setPeClassSpare(int peClassSpare) {
        this.peClassSpare = peClassSpare;
    }

    public int geteClassSpare() {
        return eClassSpare;
    }

    public void seteClassSpare(int eClassSpare) {
        this.eClassSpare = eClassSpare;
    }
}
