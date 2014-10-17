package com.ben.traffic.graphics.painters;

import java.awt.Graphics2D;
import java.awt.Point;

import org.apache.log4j.Logger;

import com.ben.traffic.converters.LogicToGraphicsConverter;
import com.ben.traffic.logic.Car;
import com.ben.traffic.logic.LogicCoordinates;

/**
 * Created by Ben on 10/14/2014.
 */
public class CarPainter {
	final static Logger LOG = Logger.getLogger(CarPainter.class);
    public static void paint(Object carObj, Graphics2D g) {
    	
        if(carObj instanceof Car) {
            Car car = (Car) carObj;

            LogicCoordinates carCenter = car.getCoordinates();
            LogicCoordinates bottomLeft = new LogicCoordinates((carCenter.getX() - car.getWidth() / 2), (carCenter.getY() + car.getLength() / 2));
            LogicCoordinates bottomRight = new LogicCoordinates((carCenter.getX() + car.getWidth() / 2), (carCenter.getY() + car.getLength() / 2));
            LogicCoordinates topLeft = new LogicCoordinates((carCenter.getX() - car.getWidth() / 2), (carCenter.getY() - car.getLength() / 2));

            Point point_bottomLeft = LogicToGraphicsConverter.convertCoordinates(bottomLeft);
            Point point_bottomRight = LogicToGraphicsConverter.convertCoordinates(bottomRight);
            Point point_topLeft = LogicToGraphicsConverter.convertCoordinates(topLeft);

            Integer gfx_width = (int) point_bottomRight.getX() - (int) point_bottomLeft.getX();
            Integer gfx_height = (int) point_topLeft.getY() - (int) point_bottomLeft.getY();

            g.setColor(car.getColor());
            g.fillRect((int) point_bottomLeft.getX(), (int) point_bottomRight.getY(), gfx_width, gfx_height);
        }
    }
}
