package com.ben.traffic.converters;

public class AccelerationConverter {
	
	public static Double metersps2(Double mps2) {
		return (mps2 * (3.28084) * (1/1000) * (1/1000));
	}
	
	public static Double feetps2(Double fts2) {
		return (fts2 * (1/1000) * (1/1000));
	}
	
	public static Double milesphs(Double mphs){
		return (mphs * 5280 * (1/60) * (1/60) * (1/1000) * (1/1000));
	}
}
