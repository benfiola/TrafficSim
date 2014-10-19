package com.ben.traffic.graphics.components;

import com.ben.traffic.logic.MainLogic;
import com.ben.traffic.messaging.EventSystem;
import com.ben.traffic.messaging.events.CarSpawnEvent;
import com.ben.traffic.messaging.events.SimulationRestartEvent;
import com.ben.traffic.messaging.events.SimulationStartEvent;
import com.ben.traffic.messaging.events.SimulationStopEvent;
import com.ben.traffic.messaging.listeners.SimulationStartListener;

import com.ben.traffic.messaging.listeners.SimulationStopListener;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Ben on 10/12/2014.
 */
public class SimulationSideMenu extends JPanel {

    final static Logger LOG = Logger.getLogger(SimulationSideMenu.class);

    JButton startButton;
    JButton stopButton;
    JButton restartButton;
    JButton spawnButton;

    public SimulationSideMenu(Dimension dimension){
        super();
        this.setSize(dimension);
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addMouseListener(new StartButtonMouseListener());
        this.add(startButton);

        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        stopButton.addMouseListener(new StopButtonMouseListener());
        this.add(stopButton);

        restartButton = new JButton("Restart");
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.addMouseListener(new RestartButtonMouseListener());
        this.add(restartButton);

        spawnButton = new JButton("Spawn Vehicle");
        spawnButton.setEnabled(false);
        spawnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        spawnButton.addMouseListener(new SpawnButtonMouseListener());
        this.add(spawnButton);

        EventSystem.addListener(new MenuSimulationStartListener(this));
        EventSystem.addListener(new MenuSimulationStopListener(this));
    }

    private class MenuSimulationStartListener extends SimulationStartListener {
        SimulationSideMenu menu;

        public MenuSimulationStartListener(SimulationSideMenu menu) {
            this.menu = menu;
        }

        @Override
        public void doAction() {
            this.menu.startButton.setEnabled(false);
            this.menu.stopButton.setEnabled(true);
            this.menu.spawnButton.setEnabled(true);
            this.menu.restartButton.setEnabled(true);
        }
    }

    private class MenuSimulationStopListener extends SimulationStopListener {
        SimulationSideMenu menu;

        public MenuSimulationStopListener(SimulationSideMenu menu) {
            this.menu = menu;
        }

        @Override
        public void doAction() {
            this.menu.startButton.setEnabled(true);
            this.menu.stopButton.setEnabled(false);
            this.menu.spawnButton.setEnabled(false);
            this.menu.restartButton.setEnabled(true);
        }
    }

    /*
        MouseListener for clicking the Stop Button.
     */
    private class StopButtonMouseListener implements MouseListener {
        public StopButtonMouseListener() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            EventSystem.fireEvent(new SimulationStopEvent());
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

    /*
        Mouse listener for clicking the Start Button.
     */
    private class StartButtonMouseListener implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            EventSystem.fireEvent(new SimulationStartEvent());
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

    /*
        Mouse Listener for clicking the Spawn button.
     */
    private class SpawnButtonMouseListener implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            EventSystem.fireEvent(new CarSpawnEvent());
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

    /*
    Mouse Listener for clicking the Restart button.
 */
    private class RestartButtonMouseListener implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            EventSystem.fireEvent(new SimulationRestartEvent());
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
