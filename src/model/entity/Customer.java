package model.entity;

import java.sql.Date;

/**
 * Created by hdd on 12/05/15.
 */
public class Customer extends Entity {
    private String title;
    private String firstName;
    private String lastName;
    private String gender;
    private Date DOB;
    private String phone;
    private String email;
    private String street;
    private String state;
    private String city;
    private String country;
    private String creditCard;
    private String cardNum;
    private int freqFlierPoint;
    private boolean passportHolder;
    private String isFly;
    private String travelAgent;

    public Customer() {
    }

    public Customer(String title, String firstName, String lastName, String gender, Date DOB, String phone, String email, String street, String state, String city, String country, String creditCard, String cardNum, int freqFlierPoint, boolean passportHolder, String isFly, String travelAgent) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.DOB = DOB;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.state = state;
        this.city = city;
        this.country = country;
        this.creditCard = creditCard;
        this.cardNum = cardNum;
        this.freqFlierPoint = freqFlierPoint;
        this.passportHolder = passportHolder;
        this.isFly = isFly;
        this.travelAgent = travelAgent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getFreqFlierPoint() {
        return freqFlierPoint;
    }

    public void setFreqFlierPoint(int freqFlierPoint) {
        this.freqFlierPoint = freqFlierPoint;
    }

    public boolean isPassportHolder() {
        return passportHolder;
    }

    public void setPassportHolder(boolean passportHolder) {
        this.passportHolder = passportHolder;
    }

    public String getIsFly() {
        return isFly;
    }

    public void setIsFly(String isFly) {
        this.isFly = isFly;
    }

    public String getTravelAgent() {
        return travelAgent;
    }

    public void setTravelAgent(String travelAgent) {
        this.travelAgent = travelAgent;
    }
}
