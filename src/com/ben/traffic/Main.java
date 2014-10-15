package com.ben.traffic;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Main {
    final static Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Simulation.start();
    }
}
