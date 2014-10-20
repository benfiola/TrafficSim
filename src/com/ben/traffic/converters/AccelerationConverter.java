package com.ben.traffic.converters;

import org.apache.log4j.Logger;

public class  AccelerationConverter {
	final static Logger LOG = Logger.getLogger(AccelerationConverter.class);

	public static Double metersps2(Double mps2) {
		return (mps2 * (3.28084) * (1.0/1000.0) * (1.0/1000.0));
	}
	
	public static Double feetps2(Double fts2) { return (fts2 * (1.0/1000.0) * (1.0/1000.0)); }
	
	public static Double milesphs(Double mphs){ return (mphs * 5280.0 * (1.0/60.0) * (1.0/60.0) * (1.0/1000.0) * (1.0/1000.0)); }
}
