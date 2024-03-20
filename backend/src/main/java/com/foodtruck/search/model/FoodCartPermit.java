package com.foodtruck.search.model;

import java.sql.Date;

//all the fields in the table, they are public for simplicity
public class FoodCartPermit {

    public Long locationId;

    public String applicant;
    public String facilityType;
    public String cnn;
    public String locationDescription;
    public String address;
    public String blockLot;
    public Integer block;
    public Integer lot;
    public String permit;
    public String status;
    public String foodItems;
    public Double x;
    public Double y;
    public Double latitude; 
    public Double longitude;
    public String schedule;
    public String daysHours;
    public String noiseNt; // Assuming typo in your table definition
    public String approved;
    public Date received;
    public String priorPermit;
    public Date expirationDate;
    public String location;
    public String firePreventionDistricts;
    public String policeDistricts;
    public String supervisorDistricts;
    public String zipCodes;
    public String neighborhoodsOld;

    // Getters and Setters omitted for brevity
}
