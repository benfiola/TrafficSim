package com.ben.traffic.controllers;

import com.ben.traffic.logic.Car;
import com.ben.traffic.logic.LogicCoordinates;
import com.ben.traffic.structures.CarLinkedList;
import org.apache.log4j.Logger;

public class CarController {
	
	public void updateTrajectory(Car car,long nextTime, Car nearestNeighbor) {
        final Logger LOG = Logger.getLogger(CarController.class);
        //get our time difference here
        long timeDifferential = nextTime - car.getTime();

        //calculate the velocities in each component's direction
        Double velocityX = Math.cos(Math.toRadians(car.getHeading())) * car.getVelocity();
        Double velocityY = Math.sin(Math.toRadians(car.getHeading())) * car.getVelocity();

        //calculate our new coordinates and create a wrapper object
        Double newX = car.getCoordinates().getX() + (velocityX*timeDifferential)  + (.5*car.getAcceleration()*timeDifferential*timeDifferential);
        Double newY = car.getCoordinates().getY() + (velocityY*timeDifferential) + (.5*car.getAcceleration()*timeDifferential*timeDifferential);
        LogicCoordinates newLocation = new LogicCoordinates(newX, newY);

        //update for the next timecycle
        car.setCoordinates(newLocation);

        //should we be braking?
        if(nearestNeighbor != null && nearestNeighbor.getVelocity() <= car.getVelocity()) {
            //how far do we typically look ahead to brake safely?
            Double lookaheadDistance = car.getDriver().getLookaheadDistance(car.getVelocity());

            //how far away is the car? factoring in that the distance is taken from the car's center
            Double carDistance = car.getDistance(nearestNeighbor) - (Car.LENGTH/2);

            //urgency is defined as the distance of the car divided by the distance that we look ahead - if we're too close, we're more likely to
            //slam on the brakes.
            Double urgency = (lookaheadDistance - carDistance)/lookaheadDistance;

            //update our acceleration as a result
            Double newAcceleration = -(Car.BRAKING_THRESHOLD * urgency);
            car.setAcceleration(newAcceleration);
        } else {
            //if there isn't a car in front of us to worry about, let's speed up.
            car.setAcceleration(car.getDriver().getDesiredAcceleration(car.getVelocity()));
        }
        car.setVelocity(car.getVelocity() + (car.getAcceleration() * timeDifferential));
        car.setTime(nextTime);
    }
    
    public void calculateDriverChanges() {
    	
    }
}
