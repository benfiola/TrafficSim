package com.ben.traffic.graphics.painters;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;

import org.apache.log4j.Logger;

import com.ben.traffic.converters.LogicToGraphicsConverter;
import com.ben.traffic.logic.Car;
import com.ben.traffic.logic.Freeway;
import com.ben.traffic.logic.Lane;
import com.ben.traffic.logic.LogicCoordinates;
import com.ben.traffic.logic.MainLogic;

/**
 * Created by Ben on 10/14/2014.
 */
public class FreewayPainter {
	final static Logger LOG = Logger.getLogger(FreewayPainter.class);
	
    private static BasicStroke BORDER = new BasicStroke(1.0f);
    private static float[] dash = {3.0f};
    private static BasicStroke DASHED = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 10.0f, dash, 0.0f);

    private Dimension dimensions;

    protected FreewayPainter(Dimension dimensions){
        this.dimensions = dimensions;
    }

    public static FreewayPainter with(Dimension dimensions) {
        return new FreewayPainter(dimensions);
    }

    public void paint(Object obj, Graphics2D g) {
        if (obj instanceof Freeway) {
            Integer gfxHeight = (int)Math.round(dimensions.getHeight());
            Integer gfxWidth = (int)Math.round(dimensions.getWidth());

            /*Draw the borders of the freeway*/
            g.setColor(Color.BLACK);
            g.setStroke(BORDER);
            g.drawLine(0, 0, 0, gfxHeight);
            g.drawLine(gfxWidth - 1, 0, gfxWidth - 1, gfxHeight);

            /*Draw the lanes of the freeway*/
            g.setStroke(DASHED);
            Freeway freeway = MainLogic.getFreeway();

            for (int x = 0; x < freeway.getLanes().size(); x++) {
                if (x != freeway.getLanes().size() - 1) {
                    Lane currLane = freeway.getLanes().get(x);
                    LogicCoordinates laneLineStart = new LogicCoordinates(currLane.getStartCoordinates().getX() + Lane.WIDTH / 2, currLane.getStartCoordinates().getY());
                    LogicCoordinates laneLineEnd = new LogicCoordinates(currLane.getEndCoordinates().getX() + Lane.WIDTH / 2, currLane.getEndCoordinates().getY());
                    Point laneLineStartPoint = LogicToGraphicsConverter.convertCoordinates(laneLineStart);
                    Point laneLineEndPoint = LogicToGraphicsConverter.convertCoordinates(laneLineEnd);
                    g.drawLine((int) laneLineStartPoint.getX(), (int) laneLineStartPoint.getY(), (int) laneLineEndPoint.getX(), (int) laneLineEndPoint.getY());
                }
            }
            List<Car> cars = freeway.getCars();
            for (Car car : cars) {
                CarPainter.paint(car, g);
            }
        }
    }
}
