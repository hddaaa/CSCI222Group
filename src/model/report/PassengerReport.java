package model.report;

/**
 * Created by hdd on 17/05/15.
 */
public class PassengerReport {
    private int passengerNum;
    private int noFlyPassengerNum;
    private int passportHolderNum;
    private int passengerWithoutAgentNum;

    public int getPassengerNum() {
        return passengerNum;
    }

    public void setPassengerNum(int passengerNum) {
        this.passengerNum = passengerNum;
    }

    public int getNoFlyPassengerNum() {
        return noFlyPassengerNum;
    }

    public void setNoFlyPassengerNum(int noFlyPassengerNum) {
        this.noFlyPassengerNum = noFlyPassengerNum;
    }

    public int getPassportHolderNum() {
        return passportHolderNum;
    }

    public void setPassportHolderNum(int passportHolderNum) {
        this.passportHolderNum = passportHolderNum;
    }

    public int getPassengerWithoutAgentNum() {
        return passengerWithoutAgentNum;
    }

    public void setPassengerWithoutAgentNum(int passengerWithoutAgentNum) {
        this.passengerWithoutAgentNum = passengerWithoutAgentNum;
    }
}
