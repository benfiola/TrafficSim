package com.ben.traffic.graphics;

import com.ben.traffic.logic.MainLogic;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Ben on 10/12/2014.
 */
public class SimulationContentPaneSideMenu extends JPanel {

    final static Logger LOG = Logger.getLogger(SimulationContentPaneSideMenu.class);

    JButton startButton;
    JButton stopButton;
    JButton restartButton;
    JButton spawnButton;

    public SimulationContentPaneSideMenu(Dimension dimension){
        super();
        this.setSize(dimension);
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addMouseListener(new StartButtonMouseListener());
        this.add(startButton);

        stopButton = new JButton("Stop");
        stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        stopButton.addMouseListener(new StopButtonMouseListener());
        this.add(stopButton);

        restartButton = new JButton("Restart");
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(restartButton);

        spawnButton = new JButton("Spawn Vehicle");
        spawnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(spawnButton);
    }

    private class StopButtonMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            MainWindow.getSimulationCanvas().stopAnimation();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) { }
    }

    private class StartButtonMouseListener implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            MainWindow.getSimulationCanvas().startAnimation();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) { }
    }

    private class SpawnButtonMouseListener implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            MainLogic.getFreeway().spawnCar();
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) { }
    }
}
