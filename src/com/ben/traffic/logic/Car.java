package com.ben.traffic.logic;

/**
 * Created by Ben on 10/12/2014.
 */
public class Car {
    private Driver driver;
    private Double length = 13.6;
    private Double width = 6.5;
    private Double currentHeading;
    private Double currentAcceleration;
    private Double currentVelocity;

    public Car(Driver d, double startVel, double startHeading) {
        this.driver = d;
        this.length = length;
        this.width = width;
        this.currentVelocity = startVel;
        this.currentHeading = startHeading;
        this.currentAcceleration = 0.0;
    }

    public Driver getDriver() { return this.driver; }
    public Double getWidth() { return this.width; }
    public Double getLength() { return this.length; }
    public Double getCurrentAcceleration() { return this.currentAcceleration; }
    public Double getCurrentHeading() { return this.currentHeading; }
    public Double getCurrentVelocity() { return this.currentVelocity; }
}
