package com.ben.traffic.logic;

import org.apache.log4j.Logger;

/**
 * Created by Ben on 10/12/2014.
 *
 * This is just a wrapper for any coordinates we use on the logic side.
 */
public class LogicCoordinates {
    final static Logger LOG = Logger.getLogger(LogicCoordinates.class);
    private static Double EQUALITY_BUFFER = .5;

    private Double x;
    private Double y;

    public LogicCoordinates(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    //comparing coordinates to see if they're equal (maybe we want to see if a car has reached it's destination
    //while performing a lane change or something) when the x and y coordinates are double is a pain - here, i've
    //implemented a sort of buffer that allows us to say two coordinate points are equal if they're 'close enough'
    @Override
    public boolean equals(Object other) {
        if(other instanceof LogicCoordinates) {
            LogicCoordinates otherLc = (LogicCoordinates) other;
            Double thisX = this.getX();
            Double thisY = this.getY();
            Double otherX = otherLc.getX();
            Double otherY = otherLc.getY();
            if (Math.abs(otherX - thisX) <= EQUALITY_BUFFER && Math.abs(otherY - thisY) <= EQUALITY_BUFFER) {
                return true;
            }
        }
        return false;
    }

    public Double getX() { return this.x; }
    public Double getY() { return this.y; }
}
