package com.ben.traffic.graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ben on 10/12/2014.
 */
public class SimulationContentPane extends JPanel {
    SimulationContentPaneCanvas canvas;
    SimulationContentPaneSideMenu menu;

    public SimulationContentPane(Dimension dimension) {
        super();
        BorderLayout layout = new BorderLayout();
        this.setSize(dimension);
        this.setLayout(layout);
        Dimension canvasDimension = new Dimension((int) Math.round(dimension.getWidth() * .75), (int) Math.round(dimension.getHeight()));
        Dimension menuDimension = new Dimension((int) Math.round(dimension.getWidth() * .25), (int) Math.round(dimension.getHeight()));
        canvas = new SimulationContentPaneCanvas(canvasDimension);
        menu = new SimulationContentPaneSideMenu(menuDimension);
        this.add(canvas, BorderLayout.CENTER);
        this.add(menu, BorderLayout.LINE_END);
    }
}
