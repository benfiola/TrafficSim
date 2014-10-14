package com.ben.traffic.logic;

/**
 * Created by Ben on 10/12/2014.
 *
 * A car is a singular unit of travel in a lane.  It consists of a driver, and current physical properties
 * about this body.
 */
public class Car {
    private static Double LENGTH = 13.6;
    private static Double WIDTH = 6.5;
    private Double length;
    private Double width;
    private Driver driver;

    /*
        Current heading specifies the current angle of travel to be used in conjunction with acceleration/velocity
        to determine the trajectory of this body.
     */
    private Double currentHeading;
    private Double currentAcceleration;
    private Double currentVelocity;

    public Car(Driver d, double startVel, double startHeading) {
        this.driver = d;
        this.length = LENGTH;
        this.width = WIDTH;
        this.currentVelocity = startVel;
        this.currentHeading = startHeading;
        this.currentAcceleration = 0.0;
    }

    /*
        Accessor methods for the fields we need.
     */
    public Driver getDriver() { return this.driver; }
    public Double getWidth() { return this.width; }
    public Double getLength() { return this.length; }
    public Double getCurrentAcceleration() { return this.currentAcceleration; }
    public Double getCurrentHeading() { return this.currentHeading; }
    public Double getCurrentVelocity() { return this.currentVelocity; }
}
