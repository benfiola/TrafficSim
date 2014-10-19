package com.ben.traffic.messaging.listeners;

import com.ben.traffic.messaging.events.Eventable;

/**
 * Created by Ben on 10/18/2014.
 */
public abstract class Listenable {
    private Class<? extends Eventable> event;
    public Listenable(Class<? extends Eventable> event) {
        this.event = event;
    }

    public Class<? extends Eventable> getEventClass(){
        return event;
    }

    public abstract void doAction();
}
