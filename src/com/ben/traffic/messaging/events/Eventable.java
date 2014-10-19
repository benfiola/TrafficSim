package com.ben.traffic.messaging.events;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 10/18/2014.
 */
public abstract class Eventable {
    private List<Object> details;

    public Eventable(){
        this.details = new ArrayList();
    }

    public Eventable(List<Object> details) {
        this.details = details;
    }

    public List<Object> getDetails(){
        return this.details;
    }
}
