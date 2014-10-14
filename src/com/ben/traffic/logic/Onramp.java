package com.ben.traffic.logic;

import org.apache.log4j.Logger;

/**
 * Created by Ben on 10/12/2014.
 */
public class Onramp extends Lane{
    final static Logger LOG = Logger.getLogger(Onramp.class);

    public Onramp(LogicCoordinates start, LogicCoordinates end) {
        super(start, end);
    }

}
