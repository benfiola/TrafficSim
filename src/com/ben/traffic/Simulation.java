package com.ben.traffic;
import com.ben.traffic.graphics.components.MainWindow;
import com.ben.traffic.logic.MainLogic;
import org.apache.log4j.Logger;

/**
 * Created by Ben on 10/12/2014.
 * Really, this is just the single point of entry into our simulation - called only by the main inside the Main.class
 * There's only one of these and it holds the two main classes for the two subcomponents of this simulation - the graphics
 * and the logic.
 */
public class Simulation {
    final static Logger LOG = Logger.getLogger(Simulation.class);

    //we only want one simulation - let's make this class a singleton
    static Simulation instance;
    MainWindow window;
    MainLogic logic;

    protected Simulation(){
        //you know how i wanted to avoid coupling these two components?  well i'm pretty sure that we have problems
        //if the logic isn't instantiated first.  i'm stupid.  i'll fix this later.
        logic = MainLogic.getInstance();
        window = MainWindow.getInstance();
    }

    public static void start(){
        if(instance == null) {
            instance = new Simulation();
        }
    }
}
