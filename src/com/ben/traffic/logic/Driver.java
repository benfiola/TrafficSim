package com.ben.traffic.logic;

/**
 * Created by Ben on 10/12/2014.
 *
 * A driver ... drives a car.  This class defines the behavioral aspect of each car.
 */
public class Driver {
    private Double desiredVelocity;
    private Double aggression;

    public Driver(Double desiredVelocity, Double aggression) {
        this.desiredVelocity = desiredVelocity;
        this.aggression = aggression;
    }

    public Double getDesiredVelocity(){ return this.desiredVelocity; }
    public Double getAggression() { return this.aggression; }
}
