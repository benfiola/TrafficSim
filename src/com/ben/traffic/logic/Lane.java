package com.ben.traffic.logic;

import org.apache.log4j.Logger;

/**
 * Created by Ben on 10/12/2014.
 * A Lane represents a pathway for traffic to navigate down.
 * It contains starting and ending coordinates.  For a typical lane,
 * these represent the start and end of a freeway - should we add onramps
 * and offramps (which theoretically are lanes as well), then they'll point to
 * the start and end points of the lanes.
 */
public class Lane {
    final static Logger LOG = Logger.getLogger(Lane.class);

    private LogicCoordinates startCoordinates;
    private LogicCoordinates endCoordinates;
    private Lane left;
    private Lane right;
    public static Integer WIDTH = 10; //feet

    public Lane(LogicCoordinates start, LogicCoordinates end) {
        this.startCoordinates = start;
        this.endCoordinates = end;
    }

    public LogicCoordinates getDestinationCoordinates(LogicCoordinates currPos, Double heading) {
        double forwardDistance = Math.tan(Math.toRadians(heading)) * Lane.WIDTH;
        return new LogicCoordinates(this.getStartCoordinates().getX(), currPos.getY() + forwardDistance);
    }

    public LogicCoordinates getStartCoordinates() { return this.startCoordinates; }
    public LogicCoordinates getEndCoordinates() { return this.endCoordinates; }
    public void setLeftLane(Lane l) { this.left = l; }
    public Lane getLeftLane() { return this.left; }
    public void setRightLane(Lane l) { this.right = l; }
    public Lane getRightLane() { return this.right; }
}
