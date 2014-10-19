package com.ben.traffic.graphics.components;

import com.ben.traffic.converters.LogicToGraphicsConverter;
import com.ben.traffic.graphics.painters.CarPainter;
import com.ben.traffic.graphics.painters.FreewayPainter;
import com.ben.traffic.logic.*;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * Created by Ben on 10/12/2014.
 */
public class SimulationCanvas extends JPanel {
    final static Logger LOG = Logger.getLogger(SimulationCanvas.class);

    public SimulationCanvas(Dimension dimension) {
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
        FreewayPainter.with(this.getSize()).paint(MainLogic.getFreeway(), g2);
    }

}
