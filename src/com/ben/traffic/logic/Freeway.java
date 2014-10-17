package com.ben.traffic.logic;

import org.apache.log4j.Logger;

import com.ben.traffic.controllers.CarController;

import java.util.*;

/**
 * Created by Ben on 10/12/2014.
 * This represents a logical freeway consisting of a length and a variable number of lanes in which cars can travel down.
 */
public class Freeway {
    final static Logger LOG = Logger.getLogger(Freeway.class);

    private Timer simulationTimer;

    /*
        These represent static values for freeway length and number of lanes.
        In the future, it'd be nice to customize these via the UI but let's not get ahead of ourselves.
     */
    private static Double FREEWAY_LENGTH = 5280/16.0;
    private static Double NUM_LANES = 3.0;

    /*
        private field variables that store the various properties of the freeway.
     */
    private Double numLanes;
    private Double length;
    private List<Lane> lanes;
    private List<Car> cars;
    private CarFactory factory;
    private CarController controller;

    /*
        initialize this bad boy with the basics - our static values for the fields and an empty list of lanes
     */
    public Freeway() {
        this.numLanes = NUM_LANES;
        this.length = FREEWAY_LENGTH;
        this.lanes = new ArrayList<Lane>();
        initLanes();

        this.cars = new ArrayList<Car>();
        this.factory = new CarFactory(this.lanes);
        this.controller = new CarController();
    }

    /*
        this helper method will initialize lanes as needed.  didn't want to make the constructor messy.
     */
    private void initLanes(){
        for(int x = 0; x < numLanes; x++) {
            Double centerX = ((x * Lane.WIDTH) + (Lane.WIDTH/2.0));
            this.lanes.add(new Lane(new LogicCoordinates(centerX, 0.0), new LogicCoordinates(centerX, this.length)));
        }
    }

    /*
        we call this method from the simulation controller as needed to spawn cars.
     */
    public void spawnCar(){
        Car toAdd = this.factory.spawnCar();
        this.cars.add(toAdd);
        LOG.info("Spawning car " + toAdd);
    }

    public void startSimulation(){
        if(simulationTimer == null) {
            simulationTimer = new Timer();
            simulationTimer.schedule(new TimerTask() {
                public void run() {
                    ArrayList<Car> toRemove = new ArrayList<Car>();
                    for(int i = 0; i < cars.size(); i++) {
                    	Car car = cars.get(i);
                    	controller.updateTrajectory(car, new Date().getTime());
                        //car.calculateTrajectory(new Date().getTime());
                        if (isCarOutOfBounds(car)) {
                            toRemove.add(car);
                        }
                    }
                    cars.removeAll(toRemove);
                }
            }, 0, 5);
        }
    }

    private boolean isCarOutOfBounds(Car c){
        return false;
    	//return c.getCoordinates().getY() < this.getLength();
    }

    public void stopSimulation(){
        if(simulationTimer != null) {
            simulationTimer.cancel();
            simulationTimer = null;
        }
    }

    /*
        standard accessor methods for the fields we'll need from this freeway.
     */
    public Double getNumLanes() { return this.numLanes; }
    public List<Lane> getLanes() { return this.lanes; }
    public Double getLength() { return this.length; }
    public List<Car> getCars() { return this.cars; }
}
