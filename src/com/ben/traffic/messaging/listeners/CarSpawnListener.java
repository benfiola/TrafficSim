package com.ben.traffic.messaging.listeners;

import com.ben.traffic.messaging.events.CarSpawnEvent;

/**
 * Created by Ben on 10/18/2014.
 */
public abstract class CarSpawnListener extends Listenable {

    public CarSpawnListener() {
        super(CarSpawnEvent.class);
    }
}
