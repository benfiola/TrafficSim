package com.ben.traffic.logic;

import org.apache.log4j.Logger;

import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Created by Ben on 10/13/2014.
 * This class will spawn cars randomly
 */
public class CarFactory {
    final static Logger LOG = Logger.getLogger(CarFactory.class);

    /*we need a list of lanes to spawn our car in*/
    private List<Lane> lanes;

    /*this will provide random colors we can choose for our cars*/
    private Color[] colors = {Color.BLUE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.GRAY, Color.ORANGE};

    private Driver[] drivers = {new Driver(30.0, 0.2)};

    public CarFactory(List<Lane> lanes) {
        this.lanes = lanes;
    }

    /*spawns a random car into a random lane*/
    public Car spawnCar() {
        Random rand = new Random();
        Lane randomLane = lanes.get(rand.nextInt(lanes.size()));
        Color randomColor = colors[rand.nextInt(colors.length)];
        Driver randomDriver = drivers[rand.nextInt(drivers.length)];
        Car toReturn = new Car(randomDriver, randomLane, randomColor);
        return toReturn;
    }
}
