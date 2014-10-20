package com.ben.traffic.logic;

import com.ben.traffic.converters.AccelerationConverter;
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

    public static Double LENGTH = 13.6;
    public static Double WIDTH = 6.5;
    public static Double ACCELERATION_THRESHOLD = AccelerationConverter.metersps2(3.5);
    public static Double BRAKING_THRESHOLD = AccelerationConverter.feetps2(35.0);
    private Double length;
    private Double width;
    private Driver driver;
    private Lane lane;

    /*probably the only instance of graphics + logic coupling - let's give this car a color here*/
    private Color color;

    /*
        Current heading specifies the current angle of travel to be used in conjunction with acceleration/velocity
        to determine the trajectory of this body.
     */
    private Double currentHeading;
    private Double currentAcceleration;
    private Double currentVelocity;
    private long lastTime;
    private LogicCoordinates currentCoordinates;
    
    public Car(Driver d, Lane lane, Color color) {
    	this(d,lane,color,new Date().getTime());
    }

    private Car(Driver d, Lane lane, Color color, long time) {
        this.driver = d;
        d.setCar(this);
        this.length = LENGTH;
        this.width = WIDTH;
        this.currentVelocity = d.getDesiredVelocity();
        this.currentHeading = getAngle(lane.getStartCoordinates(), lane.getEndCoordinates());
        this.currentAcceleration = 0.0;
        this.currentCoordinates = lane.getStartCoordinates();
        this.color = color;
        this.lastTime = time;
        this.lane = lane;
    }

    public Double getAngle(LogicCoordinates point1, LogicCoordinates point2) {
        Double angle = Math.toDegrees(Math.atan2(point2.getY() - point1.getY(), point2.getX() - point1.getX()));
        if(angle < 0) {
            angle = angle + 360.0;
        }
        return angle;
    }

    public Double getDistance(Car other) {
        Double otherY = other.getCoordinates().getY();
        Double otherX = other.getCoordinates().getX();
        Double thisY = this.getCoordinates().getY();
        Double thisX = this.getCoordinates().getX();
        return Math.sqrt(Math.pow((otherX - thisX),2) + Math.pow((otherY - thisY), 2));
    }

    

    /*
        Accessor methods for the fields we need.
     */
    public Driver getDriver() { return this.driver; }
    public Double getWidth() { return this.width; }
    public Double getLength() { return this.length; }
    public Double getAcceleration() { return this.currentAcceleration; }
    public void setAcceleration(Double a) { currentAcceleration = a; }
    public Double getHeading() { return this.currentHeading; }
    public void setHeading(Double h) { currentHeading = h; }
    public Double getVelocity() { return this.currentVelocity; }
    public void setVelocity(Double v) { currentVelocity = v; }
    public LogicCoordinates getCoordinates() { return this.currentCoordinates; }
    public void setCoordinates(LogicCoordinates c) { currentCoordinates = c; }
    public Color getColor() { return this.color; }
    public long getTime() { return this.lastTime; }
    public void setTime(long t) { lastTime = t; }
    public Lane getLane() { return this.lane; }
}
