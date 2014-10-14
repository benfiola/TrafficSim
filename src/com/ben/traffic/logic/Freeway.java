package com.ben.traffic.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 10/12/2014.
 */
public class Freeway {
    private Integer numLanes;
    private Integer length;
    private Integer width;
    private List<Lane> lanes;
    private List<Onramp> onramps;
    private List<Offramp> offramps;

    public Freeway() {
        this.numLanes = 3;
        this.length = 5280/16;
        this.lanes = new ArrayList<Lane>();
        this.onramps = new ArrayList<Onramp>();
        this.offramps = new ArrayList<Offramp>();
        initLanes();
    }

    private void initLanes(){
        for(int x = 0; x < numLanes; x++) {
            int centerX = ((x * Lane.WIDTH) + (Lane.WIDTH/2));
            this.lanes.add(new Lane(new LogicCoordinates(centerX, 0), new LogicCoordinates(centerX, this.length)));
        }
        this.width = this.numLanes * Lane.WIDTH;
    }

    private void initOnramps(){
        //for now, let's just spawn one onramp with a angle of 15 degrees to the freeway
        //sin = opposite / hypotenuse
        //sin 15 * 1320 = opposite
        int numOnramp = 1;
        int length = 1320;
        double heading = 15;
        int y = 5280;
        int distanceFromFreewayX = (int) Math.round( Math.sin(Math.toDegrees(heading)) * length);
        int distanceFromFreewayY = (int) Math.round( Math.cos(Math.toDegrees(heading)) * length);

        for(int x = 0; x < numOnramp; x++) {
            Lane rightMostLane = this.lanes.get(this.lanes.size() - 1);
            int centerXEnd = rightMostLane.getStartCoordinates().getX();
            int centerXStart = rightMostLane.getStartCoordinates().getX() + distanceFromFreewayX + Lane.WIDTH;
            int centerYEnd = y;
            int centerYStart = y - distanceFromFreewayY;
            this.onramps.add(new Onramp(new LogicCoordinates(centerXStart, centerYStart), new LogicCoordinates(centerXEnd, centerYEnd)));

            int rightMostX = centerXStart + (Lane.WIDTH/2);
            if(this.width < rightMostX) {
                this.width = rightMostX;
            }
        }
    }

    private void initOfframps(){

    }

    public Integer getNumLanes() { return this.numLanes; }
    public List<Lane> getLanes() { return this.lanes; }
    public List<Onramp> getOnramps() { return this.onramps; }
    public List<Offramp> getOfframps() { return this.offramps; }
    public Integer getWidth() { return this.width; }
    public Integer getLength() { return this.length; }

}
