package com.ben.traffic.controllers;

import com.ben.traffic.logic.Car;
import com.ben.traffic.logic.Lane;
import com.ben.traffic.logic.LogicCoordinates;
import com.ben.traffic.structures.CarLinkedList;
import org.apache.log4j.Logger;

import java.util.Random;

public class CarController {
	
	public void updateTrajectory(Car car,long nextTime, Car nearestFrontNeighbor, Car nearestLeftNeighbor, Car nearestRightNeighbor) {
        final Logger LOG = Logger.getLogger(CarController.class);
        //get our time difference here
        long timeDifferential = nextTime - car.getTime();

        //is there a car in front of us we should worry about?
        if(nearestFrontNeighbor != null && nearestFrontNeighbor.getVelocity() <= car.getVelocity()) {
            boolean switchingLanes = false;
            //are we going to switch lanes?
            if(car.getDriver().wantsToSwitchLanes(car.getVelocity()) && (nearestLeftNeighbor == null || nearestRightNeighbor == null)) {
                Lane laneToSwitchTo = null;
                Lane leftLane = car.getLane().getLeftLane();
                Lane rightLane = car.getLane().getRightLane();
                if((leftLane != null && nearestLeftNeighbor == null) && (rightLane != null && nearestRightNeighbor == null) ) {
                    int leftOrRight = new Random().nextInt(2);
                    if(leftOrRight == 0) {
                        laneToSwitchTo = leftLane;
                    }
                    else {
                        laneToSwitchTo = rightLane;
                    }
                }
                else if(leftLane != null && nearestLeftNeighbor == null){
                    laneToSwitchTo = leftLane;
                }
                else if (rightLane != null && nearestRightNeighbor == null) {
                    laneToSwitchTo = rightLane;
                }

                if(laneToSwitchTo != null) {
                    switchingLanes = true;
                    LOG.debug("switching lanes!");
                    car.setLane(laneToSwitchTo);
                    car.setDestination(laneToSwitchTo.getDestinationCoordinates(car.getCoordinates(), car.getLaneSwitchAngle()));
                }
            }

            //if we're either choosing to not switch lanes or we simply can't (hence the reason for the bool flag)
            if(!switchingLanes) {
                //how far do we typically look ahead to brake safely?
                Double lookaheadDistance = car.getDriver().getLookaheadDistance(car.getVelocity());

                //how far away is the car? factoring in that the distance is taken from the car's center
                Double carDistance = car.getDistance(nearestFrontNeighbor) - (Car.LENGTH/2);

                //urgency is defined as the distance of the car divided by the distance that we look ahead - if we're too close, we're more likely to
                //slam on the brakes.
                Double urgency = (lookaheadDistance - carDistance)/lookaheadDistance;

                //update our acceleration as a result
                Double newAcceleration = -(Car.BRAKING_THRESHOLD * urgency);
                car.setAcceleration(newAcceleration);
            }
        } else {
            //if there isn't a car in front of us to worry about, let's speed up.
            car.setAcceleration(car.getDriver().getDesiredAcceleration(car.getVelocity()));
        }

        Double velocityX = Math.cos(Math.toRadians(car.getHeading())) * car.getVelocity();
        Double velocityY = Math.sin(Math.toRadians(car.getHeading())) * car.getVelocity();
        Double newX = car.getCoordinates().getX() + (velocityX*timeDifferential)  + (.5*car.getAcceleration()*timeDifferential*timeDifferential);
        Double newY = car.getCoordinates().getY() + (velocityY*timeDifferential) + (.5*car.getAcceleration()*timeDifferential*timeDifferential);
        LogicCoordinates newLocation = new LogicCoordinates(newX, newY);

        //update for the next timecycle
        car.setCoordinates(newLocation);
        car.setVelocity(car.getVelocity() + (car.getAcceleration() * timeDifferential));
        car.setTime(nextTime);
    }
    
    public void calculateDriverChanges() {
    	
    }
}
