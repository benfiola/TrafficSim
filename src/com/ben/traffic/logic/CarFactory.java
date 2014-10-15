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
    private Color[] colors = {Color.BLUE, Color.GREEN, Color.CYAN, Color.GRAY, Color.ORANGE};

    /*is this 10mph?*/
    private Driver[] drivers = {new Driver((10.0 * 5280/(1*60*60*1000)), 0.2),
    		new Driver((15.0 * 5280/(1*60*60*1000)), 0.2),
    		new Driver((20.0 * 5280/(1*60*60*1000)), 0.2),
    		new Driver((25.0 * 5280/(1*60*60*1000)), 0.2)
    };

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
