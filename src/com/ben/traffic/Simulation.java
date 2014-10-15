package com.ben.traffic;
import com.ben.traffic.graphics.MainWindow;
import com.ben.traffic.logic.MainLogic;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Created by Ben on 10/12/2014.
 */
public class Simulation {
    final static Logger LOG = Logger.getLogger(Simulation.class);

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
