package com.ben.traffic.logic;

/**
 * Created by Ben on 10/12/2014.
 */
public class Lane {
    private LogicCoordinates startCoordinates;
    private LogicCoordinates endCoordinates;
    public static Integer WIDTH = 20;

    public Lane(LogicCoordinates start, LogicCoordinates end) {
        this.startCoordinates = start;
        this.endCoordinates = end;
    }

    public LogicCoordinates getStartCoordinates() { return this.startCoordinates; }
    public LogicCoordinates getEndCoordinates() { return this.endCoordinates; }
}
