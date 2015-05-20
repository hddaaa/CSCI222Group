package model.entity;

import java.util.Date;

/**
 * Created by hdd on 12/05/15.
 */
public class Schedule extends Entity {
    private String flightID;
    private int plane;
    private int route;
    private Date DepartTime;
    private Date ArrivedTime;

    public Schedule() {
    }

    public Schedule(String flightID, int plane, int route, Date departTime, Date arrivedTime) {
        this.flightID = flightID;
        this.plane = plane;
        this.route = route;
        DepartTime = departTime;
        ArrivedTime = arrivedTime;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public int getPlane() {
        return plane;
    }

    public void setPlane(int plane) {
        this.plane = plane;
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public Date getDepartTime() {
        return DepartTime;
    }

    public void setDepartTime(Date departTime) {
        DepartTime = departTime;
    }

    public Date getArrivedTime() {
        return ArrivedTime;
    }

    public void setArrivedTime(Date arrivedTime) {
        ArrivedTime = arrivedTime;
    }
}
