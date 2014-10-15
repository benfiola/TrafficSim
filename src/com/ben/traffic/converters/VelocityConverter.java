package com.ben.traffic.converters;

/*
 * This class will convert miles per hour to a functional value
 * which currently is feet/milisecond.
 */
public class VelocityConverter {
	public static Double milesph(Double mph) {
		return (mph * 5280/(1*60*60*1000));
	}
}
