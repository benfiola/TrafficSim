package com.ben.traffic.logic;

import org.apache.log4j.Logger;

import java.awt.*;
import java.util.Date;

/**
 * Created by Ben on 10/12/2014.
 *
 * A car is a singular unit of travel in a lane.  It consists of a driver, and current physical properties
 * about this body.
 */
public class Car {
    final static Logger LOG = Logger.getLogger(Car.class);

    private static Double LENGTH = 13.6;
    private static Double WIDTH = 6.5;
    private Double length;
    private Double width;
    private Driver driver;

    /*probably the only instance of graphics + logic coupling - let's give this car a color here*/
    private Color color;

    /*
        Current heading specifies the current angle of travel to be used in conjunction with acceleration/velocity
        to determine the trajectory of this body.
     */
    private Double currentHeading;
    private Double currentAcceleration;
    private Double currentVelocity;
    private long currTime;
    private LogicCoordinates currCoords;

    public Car(Driver d, Lane lane, Color color) {
        this.driver = d;
        this.length = LENGTH;
        this.width = WIDTH;
        this.currentVelocity = d.getDesiredVelocity();
        this.currentHeading = getAngle(lane.getStartCoordinates(), lane.getEndCoordinates());
        this.currentAcceleration = 0.0;
        this.currCoords = lane.getStartCoordinates();
        this.color = color;
        this.currTime = new Date().getTime();
    }

    private Double getAngle(LogicCoordinates point1, LogicCoordinates point2) {
        Double angle = Math.toDegrees(Math.atan2(point2.getY() - point1.getY(), point2.getX() - point1.getX()));
        if(angle < 0) {
            angle = angle + 360.0;
        }
        return angle;
    }

    public void calculateTrajectory(long nextTime) {
        long timeDifferential = nextTime - this.currTime;

        //perform distance calculations here;
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
    public LogicCoordinates getCurrCoordinates() { return this.currCoords; }
    public Color getColor() { return this.color; }
}
