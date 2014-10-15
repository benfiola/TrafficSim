package com.ben.traffic.structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// Abstract so that we can define different styles of skip lists.
// For traffic patterns, a skiplist where higher levels are mostly 
//   individual exits/intersections.
// Possibly a new datastructure to handle multi-line roads. Like a
//   multi-dimensional skip list?
public abstract class SkipList<E extends Comparable<E>> implements Iterable<E> {
	// base skip list idea to test traffic things
	//private List<Node<E>> lists;
	//private int levels = 0;
	
	public SkipList() {
		//lists = new LinkedList<Node<E>>();
	}
	
	// Should E extend Comparable so that we can natively sort them without caring about values?
	public abstract void add(E object);
	
	public abstract Iterator<E> iterator();
	
	public abstract Iterator<E> iterateRange(E from, E to);
	
	/**
	 * Each node has a 
	 * 
	 * @author kw@qial.net
	 *
	 */
	class Node {
		private E object;
		private Node next;
		private Node down;
		
		public Node(E object) {
			this.setObject(object);
			next = null;
			down = null;
		}

		public E getObject() {
			return object;
		}

		public void setObject(E object) {
			this.object = object;
		}
		
		public boolean isHead() {
			return object == null;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
		public boolean hasNext() {
			return next != null;
		}

		public Node getDown() {
			return down;
		}

		public void setDown(Node down) {
			this.down = down;
		}
		
		public boolean hasDown() {
			return down != null;
		}
	}
}
