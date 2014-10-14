package com.ben.traffic.logic;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 10/12/2014.
 * This represents a logical freeway consisting of a length and a variable number of lanes in which cars can travel down.
 */
public class Freeway {
    final static Logger LOG = Logger.getLogger(Freeway.class);

    /*
        These represent static values for freeway length and number of lanes.
        In the future, it'd be nice to customize these via the UI but let's not get ahead of ourselves.
     */
    private static Integer FREEWAY_LENGTH = 5280/16;
    private static Integer NUM_LANES = 3;

    /*
        private field variables that store the various properties of the freeway.
     */
    private Integer numLanes;
    private Integer length;
    private List<Lane> lanes;
    private List<Car> cars;
    private CarFactory factory;

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
    }

    /*
        this helper method will initialize lanes as needed.  didn't want to make the constructor messy.
     */
    private void initLanes(){
        for(int x = 0; x < numLanes; x++) {
            int centerX = ((x * Lane.WIDTH) + (Lane.WIDTH/2));
            this.lanes.add(new Lane(new LogicCoordinates(centerX, 0), new LogicCoordinates(centerX, this.length)));
        }
    }

    /*
        we call this method from the simulation controller as needed to spawn cars.
     */
    public void spawnCar(){
        this.factory.spawnCar();
    }

    /*
        standard accessor methods for the fields we'll need from this freeway.
     */
    public Integer getNumLanes() { return this.numLanes; }
    public List<Lane> getLanes() { return this.lanes; }
    public Integer getLength() { return this.length; }
    public List<Car> getCars() { return this.cars; }
}
