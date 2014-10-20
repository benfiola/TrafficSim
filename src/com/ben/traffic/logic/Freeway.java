package com.ben.traffic.logic;

import com.ben.traffic.messaging.EventSystem;
import com.ben.traffic.messaging.listeners.CarSpawnListener;
import com.ben.traffic.messaging.listeners.SimulationRestartListener;
import com.ben.traffic.messaging.listeners.SimulationStartListener;
import com.ben.traffic.messaging.listeners.SimulationStopListener;
import com.ben.traffic.structures.CarLinkedList;
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
        EventSystem.addListener(new SimulationCarSpawnListener(this));
        EventSystem.addListener(new LogicSimulationStartListener(this));
        EventSystem.addListener(new LogicSimulationStopListener(this));
        EventSystem.addListener(new LogicSimulationRestartListener(this));
    }

    /*
        this helper method will initialize lanes as needed.  didn't want to make the constructor messy.
     */
    private void initLanes(){
        for(int x = 0; x < numLanes; x++) {
            Double centerX = ((x * Lane.WIDTH) + (Lane.WIDTH/2.0));
            this.lanes.add(new Lane(new LogicCoordinates(centerX, 0.0), new LogicCoordinates(centerX, this.length)));
        }
        for(int x = 0; x < this.lanes.size(); x++) {
            Lane leftLane;
            Lane rightLane;
            Lane currLane = this.lanes.get(x);
            if( (x-1) > 0) {
                leftLane = this.lanes.get(x-1);
                currLane.setLeftLane(leftLane);
            }
            if(x+1 < this.lanes.size()) {
                rightLane = this.lanes.get(x+1);
                currLane.setRightLane(rightLane);
            }
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

                    //I'm under the impression that having to update our linked list at every time step could be pretty complicated as it requires us to figure out whether a car in a particular position
                    //has been eclipsed by potentially many other drivers.  What I figure might be an easy implementation for now is to sort our list of cars once with every timestep by their y coordinates
                    //and rebuild our linked list.  This then means that we're dealing with O(n^2 log n) in terms of time complexity for every time step, but then linked list accesses will be constant time
                    //since we've backed our linked list with a hashmap thus removing our need to iterate through the linked list every time we want to get a car's relative neighbors.
                    Collections.sort(cars, new Comparator<Car>() {
                        @Override
                        public int compare(Car o1, Car o2) {
                            return o1.getCoordinates().getY().compareTo(o2.getCoordinates().getY());
                        }
                    });
                    CarLinkedList neighborLinkedList = new CarLinkedList(cars);
                    ArrayList<Car> toRemove = new ArrayList<Car>();
                    for(int i = 0; i < cars.size(); i++) {
                    	Car car = cars.get(i);
                    	controller.updateTrajectory(car, new Date().getTime(),
                                neighborLinkedList.findNearestNeighbors(car, car.getDriver().getLookaheadDistance(car.getVelocity()), car.getLane()),
                                neighborLinkedList.findNearestNeighbors(car, car.getDriver().getLookaheadDistance(car.getVelocity()) + car.getLane().WIDTH, car.getLane().getLeftLane()),
                                neighborLinkedList.findNearestNeighbors(car, car.getDriver().getLookaheadDistance(car.getVelocity()) + car.getLane().WIDTH, car.getLane().getRightLane()));
                        if (isCarOutOfBounds(car)) {
                            //cars aren't removed until after we've calculated the trajectories for all cars -
                            //we can't modify this list right now, but we need a way to flag a car so that we know it will be removed
                            //so that's what this flag is.  other trajectory calculations will ignore this car.
                            car.setToRemove(true);
                            toRemove.add(car);
                        }
                    }
                    cars.removeAll(toRemove);
                }
            }, 0, 5);
        }
    }

    private boolean isCarOutOfBounds(Car c){
        Double carYCoords = c.getCoordinates().getY();
        Double laneYCoords = c.getLane().getEndCoordinates().getY();
        return carYCoords >= laneYCoords;
    }

    public void stopSimulation(){
        if(simulationTimer != null) {
            simulationTimer.cancel();
            simulationTimer = null;
        }
    }

    public void resetSimulation(){
        this.cars = new ArrayList<Car>();
    }

    /*
        standard accessor methods for the fields we'll need from this freeway.
     */
    public Double getNumLanes() { return this.numLanes; }
    public List<Lane> getLanes() { return this.lanes; }
    public Double getLength() { return this.length; }
    public List<Car> getCars() { return this.cars; }

    private class SimulationCarSpawnListener extends CarSpawnListener
    {
        private Freeway fway;

        public SimulationCarSpawnListener(Freeway fway) {
            this.fway = fway;
        }

        @Override
        public void doAction(){
            this.fway.spawnCar();
        }
    }

    private class LogicSimulationStartListener extends SimulationStartListener {
        private Freeway fway;
        public LogicSimulationStartListener(Freeway fway) {
            this.fway = fway;
        }

        @Override
        public void doAction(){
            this.fway.startSimulation();
        }
    }

    private class LogicSimulationStopListener extends SimulationStopListener {
        private Freeway fway;
        public LogicSimulationStopListener(Freeway fway) {
            this.fway = fway;
        }

        @Override
        public void doAction(){
            this.fway.stopSimulation();
        }
    }

    private class LogicSimulationRestartListener extends SimulationRestartListener {
        private Freeway fway;
        public LogicSimulationRestartListener(Freeway fway) {
            this.fway = fway;
        }

        @Override
        public void doAction(){
            this.fway.resetSimulation();
        }
    }
}
