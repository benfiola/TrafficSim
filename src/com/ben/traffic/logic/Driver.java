package com.ben.traffic.logic;

import org.apache.log4j.Logger;

import com.ben.traffic.converters.AccelerationConverter;

/**
 * Created by Ben on 10/12/2014.
 *
 * A driver ... drives a car.  This class defines the behavioral aspect of each car.
 */
public class Driver {
    final static Logger LOG = Logger.getLogger(Driver.class);

    private static Double SAFE_LOOK_AHEAD_TIME = 3000.0;
    private Double desiredVelocity;
    private Double aggression;
    private Car car;

    public Driver(Double desiredVelocity, Double aggression) {
        this.desiredVelocity = desiredVelocity;
        this.aggression = aggression;
    }

    public Double getDesiredAcceleration(Double currentVelocity){
       if(currentVelocity < desiredVelocity) {
           return car.ACCELERATION_THRESHOLD * this.aggression;
       }
       return 0.0;
    }
    /*
     * 
     */

    /*
     * Statistically, 3 seconds ahead is a 'safe' distance to keep behind traffic
     * We use this to measure a need to accelerate/decelerate
     */
    public Double getLookaheadDistance(Double currentVelocity){
    	//get inverse of aggression so that a totally aggressive person will take
    	//the least amount of distance to look ahead before considering stopping
    	Double aggression = 1 - this.aggression;
    	
    	//let's calculate the minimum amount of time needed to stop given the braking
    	//threshold - we don't want to cause an accident!
    	Double timeToStop = currentVelocity/Car.BRAKING_THRESHOLD;
    	Double minimumBrakingDistance = 0  + (currentVelocity * timeToStop) + (car.BRAKING_THRESHOLD * timeToStop * timeToStop);
    	
    	//we take the aggression and multiply it by the safe distance at which a car should follow
    	Double toReturn = (aggression) * (currentVelocity * SAFE_LOOK_AHEAD_TIME);	
    	
    	//if toReturn is less than the least amount of distance needed to stop, let's return the 
    	//least amount of distance instead
    	if(toReturn < minimumBrakingDistance) {
    		toReturn = minimumBrakingDistance;
    	}
    	return toReturn;
    }

    public Double getDesiredVelocity(){ return this.desiredVelocity; }
    public Double getAggression() { return this.aggression; }
    public Car getCar(){ return this.car; }
    public void setCar(Car car) { this.car = car; }
}
