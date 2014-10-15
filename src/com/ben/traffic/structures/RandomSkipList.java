package com.ben.traffic.structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomSkipList<E extends Comparable<E>> extends SkipList<E> {

	private List<Node> lists;
	//private int height = 0;
	private Random rand = new Random();
	
	public RandomSkipList() {
		lists = new ArrayList<Node>();
		lists.add(new Node(null));
	}
	
	@Override
	public void add(E object) {
		Node start = lists.get(lists.size()-1);
		// start by calling addHelper on the top node
		Node helpReturn = addHelper(start,object);
		if(helpReturn != null) {
			// we need to go higher
			Node level = new Node(null);
			Node obj = helpReturn;
			level.setDown(start);
			level.setNext(helpReturn);
			lists.add(level);
			while(rand.nextBoolean()) {
				// go up again, make next level objects
				Node nextObj = new Node(object);
				nextObj.setDown(obj);
				Node nextLevel = new Node(null);
				nextLevel.setDown(level);
				nextLevel.setNext(nextObj);
				lists.add(nextLevel);
				
				// set objects again
				level = nextLevel;
				obj = nextObj;
			}
		}
	}
	
	/**
	 * This function recursively searches each level to find a node's proper
	 * location on the bottom row. Once it hits the bottom (at which point start
	 * is null), it creates a Node for the layer above it. As the function moves
	 * back out of the recursion, it has a 50% chance of returning a Node. If
	 * it returns a Node, the function connects it into the next level.
	 * 
	 * 
	 * 
	 * @param start
	 * @param object
	 * @return
	 */
	private Node addHelper(Node start, E object) {
		if(start == null) {
			// this means we are at the bottom and need to return a Node for the level above
			return new Node(object);
		}
		Node nodeBefore = start;
		while(nodeBefore.hasNext() && 
				(nodeBefore.getNext().getObject().compareTo(object) < 0) ) {
			// we're still bigger
			nodeBefore = nodeBefore.getNext();
		}
		
		Node myNode = addHelper(nodeBefore.getDown(),object);
		
		if(myNode == null) {
			// we don't jump up, just return out
			return null;
		}
		
		// add myNode after nodeBefore
		myNode.setNext(nodeBefore.getNext());
		nodeBefore.setNext(myNode);

		// test if we move up
		if(rand.nextBoolean()) {
			Node upNode = new Node(object);
			upNode.setDown(myNode);
			return upNode;
		}
		// test failed, return null
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterateRange(E from, E to) {
		// TODO Auto-generated method stub
		return null;
	}

}
