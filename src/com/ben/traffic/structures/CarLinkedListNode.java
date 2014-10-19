package com.ben.traffic.structures;

import com.ben.traffic.logic.Car;

/**
 * Created by Ben on 10/19/2014.
 */
public class CarLinkedListNode {

    private CarLinkedListNode next;
    private CarLinkedListNode previous;
    private Car value;

    public CarLinkedListNode(CarLinkedListNode previous, CarLinkedListNode next, Car value) {
        this.next = next;
        this.previous = previous;
        this.value = value;
    }

    public CarLinkedListNode getNext() {
        return this.next;
    }

    public CarLinkedListNode getPrevious(){
        return this.previous;
    }

    public void setNext(CarLinkedListNode node) {
        this.next = node;
    }

    public void setPrevious(CarLinkedListNode node) {
        this.previous = node;
    }

    public Car getValue(){
        return this.value;
    }

}
