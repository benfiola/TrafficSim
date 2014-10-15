package com.ben.traffic.graphics.components;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ben on 10/12/2014.
 */
public class SimulationContentPane extends JPanel {
    final static Logger LOG = Logger.getLogger(SimulationContentPane.class);

    SimulationPanel canvas;
    SimulationSideMenu menu;

    public SimulationContentPane(Dimension dimension) {
        super();
        BorderLayout layout = new BorderLayout();
        this.setSize(dimension);
        this.setLayout(layout);
        Dimension canvasDimension = new Dimension((int) Math.round(dimension.getWidth() * .75), (int) Math.round(dimension.getHeight()));
        Dimension menuDimension = new Dimension((int) Math.round(dimension.getWidth() * .25), (int) Math.round(dimension.getHeight()));
        canvas = new SimulationPanel(canvasDimension);
        menu = new SimulationSideMenu(menuDimension);
        this.add(canvas, BorderLayout.CENTER);
        this.add(menu, BorderLayout.LINE_END);
    }

    public SimulationPanel getCanvas() {
        return canvas;
    }
}
