package com.sxm.vehicle.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Vehicle is the entity-model serving as a POJO class . . .
 *
 * @author Lakshay Mukhi
 *
 */
@Entity
@Table(name = "vehicle")
public class Vehicle {


    /**
     * Id of the vehicle
     */
    @Id
    private String vin;


    /**
     * Manufacturer of the vehicle
     */
    private String make;

    /**
     * model number of the vehicle
     */
    private String model;

    /**
     * manufacturing year of the vehicle
     */
    private int year;

    /**
     * Type of transmission in a vehicle ( Manual or Auto)
     */
    private String transmissionType;

    public Vehicle(String vin, int year, String make, String model, String transmissionType) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.transmissionType = transmissionType;
    }

    public Vehicle() {
    }
    public String getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getTransmissionType() {
        return transmissionType;
    }


    @Override
    public String toString() {
        return "{" +
                "vin:" + vin + '\'' +
                ", year:" + year +
                ", make:" + make + '\'' +
                ", model:" + model + '\'' +
                ", transmissionType:" + transmissionType + '\'' +
                "}";
    }

}