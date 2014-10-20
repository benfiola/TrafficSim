package com.ben.traffic.calculators;

import com.ben.traffic.logic.LogicCoordinates;

/**
 * Created by Ben on 10/19/2014.
 */
public class DistanceCalculator {
    public static Double getDistance(LogicCoordinates c1, LogicCoordinates c2) {
        Double otherY = c2.getY();
        Double otherX = c2.getX();
        Double thisY = c1.getY();
        Double thisX = c1.getX();
        return Math.sqrt(Math.pow((otherX - thisX),2) + Math.pow((otherY - thisY), 2));
    }
}
