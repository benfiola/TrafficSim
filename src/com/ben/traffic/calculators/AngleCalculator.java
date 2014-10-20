package com.ben.traffic.calculators;

import com.ben.traffic.logic.LogicCoordinates;

/**
 * Created by Ben on 10/19/2014.
 */
public class AngleCalculator {
    public static Double getAngle(LogicCoordinates c1, LogicCoordinates c2) {
        Double angle = Math.toDegrees(Math.atan2(c2.getY() - c1.getY(), c2.getX() - c1.getX()));
        if(angle < 0) {
            angle = angle + 360.0;
        }
        return angle;
    }
}
