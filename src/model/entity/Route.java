package model.entity;

/**
 * Created by hdd on 12/05/15.
 */
public class Route extends Entity {
    private String sourceAirport;
    private String destinationAirport;
    private boolean codeShared;
    private int stop;


    public Route() {
    }

    public Route(String sourceAirport, String destinationAirport, boolean codeShared, int stop) {
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.codeShared = codeShared;
        this.stop = stop;
    }

    public String getSourceAirport() {
        return sourceAirport;
    }

    public void setSourceAirport(String sourceAirport) {
        this.sourceAirport = sourceAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public boolean isCodeShared() {
        return codeShared;
    }

    public void setCodeShared(boolean codeShared) {
        this.codeShared = codeShared;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }
}
