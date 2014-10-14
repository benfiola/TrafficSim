package com.ben.traffic.graphics;

import com.ben.traffic.logic.MainLogic;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ben on 10/12/2014.
 */
public class SimulationContentPaneCanvas extends JPanel {
    final static Logger LOG = Logger.getLogger(SimulationContentPaneCanvas.class);

    private FreewayCanvas freewayCanvas;
    private Timer animationTimer;
    private Integer animationSpeed = 10;

    public SimulationContentPaneCanvas(Dimension dimension){
        super();
        this.setSize(dimension);
        freewayCanvas = new FreewayCanvas(dimension);
        this.add(freewayCanvas);
        animationTimer = new Timer(animationSpeed, new AnimationActionListener(this));
    }

    public void startAnimation(){
        if(animationTimer == null) {
            animationTimer = new Timer(animationSpeed, new AnimationActionListener(this));
        }
        animationTimer.start();
    }

    public void stopAnimation(){
        if(animationTimer != null) {
            animationTimer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g2.fillRect(0,0,this.getWidth(), this.getHeight());
    }

    private class AnimationActionListener implements ActionListener {
        SimulationContentPaneCanvas canvas;
        public AnimationActionListener(SimulationContentPaneCanvas canvas){
            super();
            this.canvas = canvas;
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            canvas.repaint();
        }
    }
}
