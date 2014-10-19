package com.ben.traffic.messaging.listeners;

import com.ben.traffic.messaging.events.SimulationStartEvent;

/**
 * Created by Ben on 10/18/2014.
 */
public abstract class SimulationStartListener extends Listenable  {
    public SimulationStartListener(){
        super(SimulationStartEvent.class);
    }
}
