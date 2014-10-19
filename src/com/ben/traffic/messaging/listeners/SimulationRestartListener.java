package com.ben.traffic.messaging.listeners;

import com.ben.traffic.messaging.events.SimulationRestartEvent;
/**
 * Created by Ben on 10/18/2014.
 */
public abstract class SimulationRestartListener extends Listenable  {
    public SimulationRestartListener(){
        super(SimulationRestartEvent.class);
    }
}
