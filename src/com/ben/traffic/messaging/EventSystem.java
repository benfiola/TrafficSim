package com.ben.traffic.messaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.ben.traffic.messaging.events.*;
import com.ben.traffic.messaging.listeners.Listenable;

/**
 * Created by Ben on 10/18/2014.
 */
public class EventSystem {
    HashMap<Class<? extends Eventable>, List<Listenable>> listenerMap;
    public static EventSystem instance;

    protected EventSystem(){
        listenerMap = new HashMap<Class<? extends Eventable>, List<Listenable>>();
    }

    public static void addListener(Listenable toAdd) {
        if(instance == null) {
            instance = new EventSystem();
        }
        if(instance.listenerMap.get(toAdd.getEventClass()) == null) {
            instance.listenerMap.put(toAdd.getEventClass(), new ArrayList<Listenable>());
        }
        List<Listenable> listOfListeners = instance.listenerMap.get(toAdd.getEventClass());
        listOfListeners.add(toAdd);
        instance.listenerMap.put(toAdd.getEventClass(), listOfListeners);
    }

    public static void fireEvent(Eventable event) {
        if(instance.listenerMap.get(event.getClass()) != null) {
            List<? extends Listenable> eventListeners = instance.listenerMap.get(event.getClass());
            for (Listenable listener : eventListeners) {
                listener.doAction();
            }
        }
    }

}
