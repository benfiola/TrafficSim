package com.ben.traffic.structures;

import com.ben.traffic.logic.Car;
import com.ben.traffic.logic.Lane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ben on 10/19/2014.
 */
public class CarLinkedList {
    private CarLinkedListNode head;
    HashMap<Car, CarLinkedListNode> nodeMap;
    private int size;

    public CarLinkedList(){
        nodeMap = new HashMap<Car, CarLinkedListNode>();
    }

    public CarLinkedList(List<Car> sortedList) {
        this.nodeMap = new HashMap<Car, CarLinkedListNode>();
        for(Car car : sortedList) {
            this.insertEnd(car);
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int getSize(){
        return this.size;
    }

    public CarLinkedListNode getHead(){
        return this.head;
    }

    public CarLinkedListNode getNode(Car car) {
        return this.nodeMap.get(car);
    }

    public boolean insertAfter(Car currCar,  Car toAdd) {
        if(nodeMap.get(currCar) == null) {
            return false;
        }
        return insertAfter(nodeMap.get(currCar), toAdd);
    }

    public boolean insertAfter(CarLinkedListNode node, Car toAdd) {
        CarLinkedListNode nodeToAdd = new CarLinkedListNode(node, node.getNext(), toAdd);
        nodeMap.put(toAdd, nodeToAdd);
        CarLinkedListNode oldNextNode = node.getNext();
        node.setNext(nodeToAdd);
        if(oldNextNode != null) {
            oldNextNode.setPrevious(nodeToAdd);
        }
        this.size++;
        return true;
    }

    public boolean insertBefore(Car currCar, Car toAdd) {
        if(nodeMap.get(currCar) == null) {
            return false;
        }
        return insertBefore(nodeMap.get(currCar), toAdd);
    }

    public boolean insertBefore(CarLinkedListNode node, Car toAdd) {
        if(node != null) {
            CarLinkedListNode nodeToAdd = new CarLinkedListNode(node.getPrevious(), node, toAdd);
            nodeMap.put(toAdd, nodeToAdd);
            CarLinkedListNode oldPrevNode = node.getPrevious();
            node.setPrevious(nodeToAdd);
            if (oldPrevNode != null) {
                oldPrevNode.setNext(nodeToAdd);
            } else if (node.equals(head)) {
                this.head = nodeToAdd;
            }
        } else if(this.head == null) {
            CarLinkedListNode nodeToAdd = new CarLinkedListNode(null, null, toAdd);
            this.head = nodeToAdd;
            nodeMap.put(toAdd, nodeToAdd);
        }
        this.size++;
        return true;
    }

    public boolean insertBeginning(Car toAdd) {
       return insertBefore(this.head, toAdd);
    }

    public boolean insertEnd(Car toAdd) {
        CarLinkedListNode curr = this.head;
        if(curr == null) {
            return insertBeginning(toAdd);
        } else {
            while(curr.getNext() != null) {
                curr = curr.getNext();
            }
            return insertAfter(curr, toAdd);
        }
    }

    public boolean remove(CarLinkedListNode node) {
        return remove(node.getValue());
    }

    public boolean remove(Car car) {
        if(head != null && nodeMap.get(car) != null) {
            CarLinkedListNode toRemove = nodeMap.remove(car);
            CarLinkedListNode previous = toRemove.getPrevious();
            CarLinkedListNode next = toRemove.getNext();

            //are we removing the head?
            if(toRemove.equals(head)) {
                //is there a node after the head?
                if(next != null) {
                    //set the next node to be the head, and set that 'next' node to not reference our previous head
                    this.head = next;
                    next.setPrevious(null);
                } else {
                    //get rid of the head
                    this.head = null;
                }
            } else {
                //we're not removing the head, which means that there has to be a previous node
                previous.setNext(next);
                //is there a next node?
                if(next != null) {
                    //if there is, make sure it points to 'previous' node.
                    next.setPrevious(previous);
                }
            }
            //adjust the size accordingly
            this.size--;
            return true;
        }
        return false;
    }

    public Car findNearestNeighbors(Car c, double maximumDistance, Lane laneToSearch) {
        if(laneToSearch != null) {
            CarLinkedListNode curr = this.nodeMap.get(c).getNext();
            double currDistance;
            if (curr == null) {
                currDistance = maximumDistance + 1;
            } else {
                currDistance = c.getDistance(curr.getValue());
            }
            while (curr != null && currDistance <= maximumDistance) {
                if (!curr.getValue().willBeRemoved() && curr.getValue().getLane().equals(laneToSearch)) {
                    return curr.getValue();
                } else {
                    curr = curr.getNext();
                    if (curr == null) {
                        currDistance = maximumDistance + 1;
                    } else {
                        currDistance = c.getDistance(curr.getValue());
                    }
                }
            }
        }
        return null;
    }


    public void print(){
        CarLinkedListNode curr = this.head;
        String toPrint;
        if(this.head == null) {
            toPrint = "[ ]";
        }
        else {
            toPrint = "";
            while(curr != null) {
                toPrint = toPrint + "[" + curr.toString() + "]";
                curr = curr.getNext();
                if(curr != null) {
                    toPrint = toPrint + "<=>";
                }
            }

        }
        System.out.println(toPrint);
    }

}
