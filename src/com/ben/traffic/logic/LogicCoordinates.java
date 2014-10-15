package com.ben.traffic.logic;

import org.apache.log4j.Logger;

/**
 * Created by Ben on 10/12/2014.
 *
 * This is just a wrapper for any coordinates we use on the logic side.
 */
public class LogicCoordinates {
    final static Logger LOG = Logger.getLogger(LogicCoordinates.class);

    private Double x;
    private Double y;

    public LogicCoordinates(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() { return this.x; }
    public Double getY() { return this.y; }
}
