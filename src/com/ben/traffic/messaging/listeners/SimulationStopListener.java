package com.ben.traffic.messaging.listeners;

import com.ben.traffic.messaging.events.SimulationStopEvent;

/**
 * Created by Ben on 10/18/2014.
 */
public abstract class SimulationStopListener extends Listenable  {

    public SimulationStopListener(){
        super(SimulationStopEvent.class);
    }
}
