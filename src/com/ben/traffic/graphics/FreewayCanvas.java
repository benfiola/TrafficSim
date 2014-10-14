package com.ben.traffic.graphics;

import com.ben.traffic.logic.Freeway;
import com.ben.traffic.logic.Lane;
import com.ben.traffic.logic.MainLogic;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * Created by Ben on 10/12/2014.
 */
public class FreewayCanvas extends JPanel {
    private static BasicStroke BORDER = new BasicStroke(10.0f);
    private static float[] dash = {5.0f};
    private static BasicStroke DASHED = new BasicStroke(2.5f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 10.0f, dash, 0.0f);
    public FreewayCanvas(Dimension dimension) {
        super();
        Freeway freeway = MainLogic.getFreeway();
        Integer width = (int) Math.round(( (double) freeway.getWidth()/ (double) freeway.getLength()) * dimension.getHeight());
        Dimension adjustedDimension = new Dimension(width, (int) dimension.getHeight());
        this.setSize(adjustedDimension);
        this.setPreferredSize(adjustedDimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setStroke(BORDER);
        g2.drawLine(0, 0, 0, this.getHeight());
        g2.drawLine(this.getWidth(), 0, this.getWidth(), this.getHeight());
        g2.setStroke(DASHED);
        Freeway freeway = MainLogic.getFreeway();
        int laneWidth =  (int) Math.round(((double)Lane.WIDTH / (double)freeway.getWidth()) * this.getWidth());
        int currXCoord = 0;
        for(int x = 0; x < freeway.getLanes().size(); x++) {
            if(x != freeway.getLanes().size() - 1) {
                currXCoord = currXCoord + laneWidth;
                g2.drawLine(currXCoord, 0, currXCoord, this.getHeight());
            }
        }
    }
}
