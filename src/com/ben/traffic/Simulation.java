package com.ben.traffic;
import com.ben.traffic.graphics.MainWindow;
import com.ben.traffic.logic.MainLogic;

/**
 * Created by Ben on 10/12/2014.
 */
public class Simulation {
    //we only want one simulation - let's make this class a singleton
    static Simulation instance;
    MainWindow window;
    MainLogic logic;

    protected Simulation(){
        logic = MainLogic.getInstance();
        window = MainWindow.getInstance();
    }

    public static void start(){
        if(instance == null) {
            instance = new Simulation();
        }
    }
}
