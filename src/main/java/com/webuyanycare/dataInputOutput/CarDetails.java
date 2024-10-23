package com.webuyanycare.dataInputOutput;

/**
 * By Naresh Savalia
 */
public class CarDetails {
    private String registration;
    private String make;
    private String model;
    private String year;

    public CarDetails(String registration, String make, String model, String year) {
        this.registration = registration;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getRegistration() {
        return registration;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "CarDetails{" +
                "registration='" + registration + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
