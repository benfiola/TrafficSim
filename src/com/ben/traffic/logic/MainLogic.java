package com.ben.traffic.logic;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Ben on 10/12/2014.
 * This class represents the 'main' class behind the logic component of this simulation.
 * If we want to access anything related to the logic component of the simulation, we should do so through here.
 */
public class MainLogic {
    final static Logger LOG = Logger.getLogger(MainLogic.class);

    /*
        We make this class a singleton - there's no way we're going to deal with more than one concurrent simulation
     */
    public static MainLogic instance;

    /*
        Currently we have the freeway we're simulating
     */
    public Freeway freeway;

    protected MainLogic(){
        freeway = new Freeway();
    }

    public static MainLogic getInstance(){
        if(instance == null) {
            instance = new MainLogic();
        }
        return instance;
    }

    //these three methods are called elsewhere in the program.
    //we need getFreeway so that our graphics component can retrieve the logic data to paint
    public static Freeway getFreeway(){
        return MainLogic.getInstance().freeway;
    }
}
