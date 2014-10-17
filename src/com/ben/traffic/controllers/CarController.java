package com.ben.traffic.controllers;

import com.ben.traffic.logic.Car;
import com.ben.traffic.logic.LogicCoordinates;

public class CarController {
	
	public void updateTrajectory(Car car,long nextTime) {
        //get our time difference here
        long timeDifferential = nextTime - car.getTime();

        //calculate the velocities in each component's direction
        Double velocityX = Math.cos(Math.toRadians(car.getHeading())) * car.getVelocity();
        Double velocityY = Math.sin(Math.toRadians(car.getHeading())) * car.getVelocity();

        //calculate our new coordinates and create a wrapper object
        Double newX = car.getCoordinates().getX() + (velocityX*timeDifferential)  + (.5*car.getAcceleration()*timeDifferential*timeDifferential);
        Double newY = car.getCoordinates().getY() + (velocityY*timeDifferential) + (.5*car.getAcceleration()*timeDifferential*timeDifferential);
        LogicCoordinates newLocation = new LogicCoordinates(newX, newY);

        //set all the pertinent fields
        car.setCoordinates(newLocation);
        car.setVelocity(car.getVelocity() + (car.getAcceleration() * timeDifferential));
        car.setTime(nextTime);
    }
    
    public void calculateDriverChanges() {
    	
    }
}
