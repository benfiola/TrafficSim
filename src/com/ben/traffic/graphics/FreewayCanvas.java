package com.ben.traffic.graphics;

import com.ben.traffic.logic.*;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * Created by Ben on 10/12/2014.
 */
public class FreewayCanvas extends JPanel {
    final static Logger LOG = Logger.getLogger(FreewayCanvas.class);

    private static BasicStroke BORDER = new BasicStroke(1.0f);
    private static float[] dash = {3.0f};
    private static BasicStroke DASHED = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 10.0f, dash, 0.0f);
    public FreewayCanvas(Dimension dimension) {
        super();

        /*Let's scale the freeway to the display port*/
        Freeway freeway = MainLogic.getFreeway();
        LogicToGraphicsConverter.setFreeway(freeway);
        LogicToGraphicsConverter.setPanel(this);

        double freewayWidth = freeway.getNumLanes() * Lane.WIDTH;
        Integer width = (int) Math.round(( freewayWidth / freeway.getLength()) * dimension.getHeight());
        Dimension adjustedDimension = new Dimension(width, (int) dimension.getHeight());
        this.setSize(adjustedDimension);
        this.setPreferredSize(adjustedDimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        /*Draw the borders of the freeway*/
        g2.setColor(Color.BLACK);
        g2.setStroke(BORDER);
        g2.drawLine(0, 0, 0, this.getHeight());
        g2.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight());

        /*Draw the lanes of the freeway*/
        g2.setStroke(DASHED);
        Freeway freeway = MainLogic.getFreeway();
        int currXCoord = 0;

        for(int x = 0; x < freeway.getLanes().size(); x++) {
            if(x != freeway.getLanes().size() - 1) {
                Lane currLane = freeway.getLanes().get(x);
                LogicCoordinates laneLineStart = new LogicCoordinates(currLane.getStartCoordinates().getX() + Lane.WIDTH/2, currLane.getStartCoordinates().getY());
                LogicCoordinates laneLineEnd = new LogicCoordinates(currLane.getEndCoordinates().getX() + Lane.WIDTH/2, currLane.getEndCoordinates().getY());
                Point laneLineStartPoint = LogicToGraphicsConverter.convertCoordinates(laneLineStart);
                Point laneLineEndPoint = LogicToGraphicsConverter.convertCoordinates(laneLineEnd);
                g2.drawLine((int)laneLineStartPoint.getX(), (int)laneLineStartPoint.getY(), (int)laneLineEndPoint.getX(), (int)laneLineEndPoint.getY());
            }
        }
        List<Car> cars = freeway.getCars();
        for(Car car : cars) {
            paintCar(car, g2);
        }
    }

    private void paintCar(Car car, Graphics2D g) {
        LogicCoordinates carCenter = car.getCurrCoordinates();
        LogicCoordinates bottomLeft = new LogicCoordinates((carCenter.getX() - car.getWidth()/2), (carCenter.getY() + car.getLength()/2));
        LogicCoordinates bottomRight = new LogicCoordinates((carCenter.getX() + car.getWidth()/2), (carCenter.getY() + car.getLength()/2));
        LogicCoordinates topLeft = new LogicCoordinates((carCenter.getX() - car.getWidth()/2), (carCenter.getY() - car.getLength()/2));
        LogicCoordinates topRight = new LogicCoordinates((carCenter.getX() + car.getWidth()/2), (carCenter.getY() - car.getLength()/2));
        Point point_bottomLeft = LogicToGraphicsConverter.convertCoordinates(bottomLeft);
        Point point_bottomRight = LogicToGraphicsConverter.convertCoordinates(bottomRight);
        Point point_topLeft = LogicToGraphicsConverter.convertCoordinates(topLeft);
        Integer gfx_width = (int) point_bottomRight.getX() - (int) point_bottomLeft.getX();
        Integer gfx_height = (int) point_topLeft.getY() - (int) point_bottomLeft.getY();
        g.setColor(car.getColor());
        g.fillRect((int) point_bottomLeft.getX(), (int) point_bottomRight.getY(), gfx_width, gfx_height);
    }
}
