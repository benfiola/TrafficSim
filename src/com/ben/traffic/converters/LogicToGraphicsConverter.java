package com.ben.traffic.converters;

import com.ben.traffic.graphics.components.SimulationCanvas;
import com.ben.traffic.logic.Freeway;
import com.ben.traffic.logic.Lane;
import com.ben.traffic.logic.LogicCoordinates;
import org.apache.log4j.Logger;

import java.awt.*;

/**
 * Created by Ben on 10/13/2014.
 * This class serves to convert coordinates from our logical component
 * into our graphics component
 *
 * This is where it gets confusing - the coordinate system we use for our logic
 * actually gets flipped for the graphics right now.  A positive coordinate system
 * on a graphics panel goes towards the bottom right of the screen, whereas logically,
 * our coordinate system goes towards the upper right of the screen.
 */
public class LogicToGraphicsConverter {
    final static Logger LOG = Logger.getLogger(LogicToGraphicsConverter.class);

    private static LogicToGraphicsConverter instance;
    private SimulationCanvas panel;
    private Freeway freeway;
    protected LogicToGraphicsConverter() {
    }

    private void setPanel_int(SimulationCanvas panel) {
        this.panel = panel;
    }

    private void setFreeway_int(Freeway fway) {
        this.freeway = fway;
    }

    public static void setPanel(SimulationCanvas panel) {
        if(instance == null) {
            instance = new LogicToGraphicsConverter();
        }
        instance.setPanel_int(panel);
    }

    public static void setFreeway(Freeway fway) {
        if(instance == null) {
            instance = new LogicToGraphicsConverter();
        }
        instance.setFreeway_int(fway);
    }

    /*
        This should throw an exception if the converter isn't properly initialized with the freeway logic component
        and the canvas that we're scaling the freeway to, but i'll add this later if this idea pans out.
     */
    public static Point convertCoordinates(LogicCoordinates coords) {
        Dimension panelDim = instance.panel.getSize();
        Double panelHeight = panelDim.getHeight();
        Double panelWidth = panelDim.getWidth();
        Double freewayHeight = instance.freeway.getLength();
        Double freewayWidth = (Lane.WIDTH * instance.freeway.getNumLanes());
        Integer scaledCoordY = (int) Math.round(panelHeight - ((panelHeight/freewayHeight) * coords.getY()));
        Integer scaledCoordX = (int) Math.round(((panelWidth/freewayWidth) * coords.getX()));
        return new Point(scaledCoordX, scaledCoordY);
    }

}
