package com.ben.traffic.logic;

import org.apache.log4j.Logger;

/**
 * Created by Ben on 10/12/2014.
 *
 * This class represents coordinates (in feet) of the logic portion of our simulation.
 * We want to separate our coordinates logically from our graphics component, because the graphics
 * coordinates can scale based upon screen size.  This should offer a single point of truth
 * for any location in our simulation.  For lanes, the origin starts in the lower left hand corner,
 * and coordinates generally identify a point central to a lane.
 *
 *
 *     |      |    .    |
 *     |      |    .    |
 *     |      |    .    |
 *     |      |    .    |
 *     |      |    .    |
 *     |
 *     |_________________________
 */
public class LogicCoordinates {
    final static Logger LOG = Logger.getLogger(LogicCoordinates.class);

    private Integer x;
    private Integer y;

    public LogicCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() { return this.x; }
    public Integer getY() { return this.y; }
}
