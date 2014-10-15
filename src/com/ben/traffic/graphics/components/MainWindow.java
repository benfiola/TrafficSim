package com.ben.traffic.graphics.components;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ben on 10/12/2014.
 */
public class MainWindow extends JFrame{
    final static Logger LOG = Logger.getLogger(MainWindow.class);

    private static MainWindow instance;
    private SimulationContentPane contentPane;

    protected MainWindow() {
        super("Traffic Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600, 600));
        contentPane = new SimulationContentPane(new Dimension(600, 600));
        this.setContentPane(contentPane);
        this.setVisible(true);
    }

    public static MainWindow getInstance(){
        if(instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }


    public static SimulationPanel getSimulationCanvas(){
        return MainWindow.getInstance().contentPane.getCanvas();
    }

    public static void startAnimation(){
        MainWindow.getSimulationCanvas().startAnimation();
    }

    public static void stopAnimation(){
        MainWindow.getSimulationCanvas().stopAnimation();
    }
}
