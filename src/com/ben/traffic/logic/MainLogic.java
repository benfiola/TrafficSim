package com.ben.traffic.logic;

import java.util.List;

/**
 * Created by Ben on 10/12/2014.
 */
public class MainLogic {
    public static MainLogic instance;
    public Freeway freeway;
    public List<Car> cars;

    protected MainLogic(){
        freeway = new Freeway();
    }

    public static MainLogic getInstance(){
        if(instance == null) {
            instance = new MainLogic();
        }
        return instance;
    }

    public static Freeway getFreeway(){
        return instance.freeway;
    }


}
