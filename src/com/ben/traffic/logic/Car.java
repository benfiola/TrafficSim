package com.ben.traffic.logic;

import com.ben.traffic.calculators.AngleCalculator;
import com.ben.traffic.calculators.DistanceCalculator;
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
    public static Double MAX_LANE_SWITCH_ANGLE = 45.0;

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
    private LogicCoordinates destination;
    private boolean toBeRemoved;
    
    public Car(Driver d, Lane lane, Color color) {
    	this(d,lane,color,new Date().getTime());
    }

    private Car(Driver d, Lane lane, Color color, long time) {
        this.driver = d;
        d.setCar(this);
        this.length = LENGTH;
        this.width = WIDTH;
        this.currentVelocity = d.getDesiredVelocity();
        this.destination = lane.getEndCoordinates();
        this.currentAcceleration = 0.0;
        this.currentCoordinates = lane.getStartCoordinates();
        this.color = color;
        this.lastTime = time;
        this.lane = lane;
        this.toBeRemoved = false;
    }

    public Double getDistance(Car other) {
        return DistanceCalculator.getDistance(this.getCoordinates(), other.getCoordinates());
    }

    public Double getLaneSwitchAngle() {
        return MAX_LANE_SWITCH_ANGLE;
    }

    /*
        Accessor methods for the fields we need.
     */
    public Driver getDriver() { return this.driver; }
    public Double getWidth() { return this.width; }
    public Double getLength() { return this.length; }

    public Double getAcceleration() { return this.currentAcceleration; }
    public void setAcceleration(Double a) { currentAcceleration = a; }
    public Double getVelocity() { return this.currentVelocity; }
    public void setVelocity(Double v) { currentVelocity = v; }
    public LogicCoordinates getCoordinates() { return this.currentCoordinates; }

    public void setCoordinates(LogicCoordinates c) {
        //i overrode the logic coordinates equal method.  generally, dealing with doubles and equality is a pain in the
        //ass.  i figure if they're within .5 of each other, they're...pretty darn close.  we can modify this as needed.
        if(c.equals(this.getDestination())) {
            this.setDestination(this.getLane().getEndCoordinates());
        }
        currentCoordinates = c;
    }

    public void setDestination(LogicCoordinates c) { destination = c;}
    public Lane getLane() { return this.lane; }
    public void setLane(Lane l) { this.lane = l; }
    public Color getColor() { return this.color; }
    public long getTime() { return this.lastTime; }
    public void setTime(long t) { lastTime = t; }

    public LogicCoordinates getDestination() { return this.destination; }

    public Double getHeading() { return AngleCalculator.getAngle(this.getCoordinates(), this.getDestination()); }

    public void setToRemove(boolean remove) { this.toBeRemoved = remove; }
    public boolean willBeRemoved() { return this.toBeRemoved; }
}
