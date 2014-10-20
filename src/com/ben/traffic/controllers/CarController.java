package com.ben.traffic.controllers;

import com.ben.traffic.logic.Car;
import com.ben.traffic.logic.LogicCoordinates;
import com.ben.traffic.structures.CarLinkedList;

public class CarController {
	
	public void updateTrajectory(Car car,long nextTime, Car nearestNeighbor) {
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
        if(nearestNeighbor == null) {
            car.setAcceleration(car.getDriver().getDesiredAcceleration(car.getVelocity()));
        } else {
            car.setAcceleration(car.getDriver().getDesiredAcceleration(nearestNeighbor, car.getVelocity()));
        }
        car.setVelocity(car.getVelocity() + (car.getAcceleration() * timeDifferential));
        car.setTime(nextTime);
    }
    
    public void calculateDriverChanges() {
    	
    }
}
