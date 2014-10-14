package com.ben.traffic.graphics;

import com.ben.traffic.exceptions.UninitializedConverterException;
import com.ben.traffic.logic.Freeway;
import com.ben.traffic.logic.Lane;
import com.ben.traffic.logic.LogicCoordinates;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ben on 10/13/2014.
 * This class serves to convert coordinates from our logical component
 * into our graphics component
 */
public class LogicToGraphicsConverter {
    final static Logger LOG = Logger.getLogger(LogicToGraphicsConverter.class);

    private static LogicToGraphicsConverter instance;
    private FreewayCanvas panel;
    private Freeway freeway;
    protected LogicToGraphicsConverter() {
    }

    private void setPanel_int(FreewayCanvas panel) {
        this.panel = panel;
    }

    private void setFreeway_int(Freeway fway) {
        this.freeway = fway;
    }

    public static void setPanel(FreewayCanvas panel) {
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
        Integer freewayHeight = instance.freeway.getLength();
        Integer freewayWidth = (Lane.WIDTH * instance.freeway.getNumLanes());
        Integer scaledCoordY = (int)Math.round((panelHeight/freewayHeight) * coords.getY());
        Integer scaledCoordX = (int)Math.round((panelWidth/freewayWidth) * coords.getX());
        return new Point(scaledCoordX, scaledCoordY);
    }

}
