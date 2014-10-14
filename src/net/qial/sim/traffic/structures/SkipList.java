package net.qial.sim.traffic.structures;

import java.util.LinkedList;
import java.util.List;

// Abstract so that we can define different styles of skip lists.
// For traffic patterns, a skiplist where higher levels are mostly 
//   individual exits/intersections.
// Possibly a new datastructure to handle multi-line roads. Like a
//   multi-dimensional skip list?
public abstract class SkipList<E> {
	// base skip list idea to test traffic things
	private List<Node<E>> lists;
	private int levels = 0;
	
	public SkipList() {
		lists = new LinkedList<Node<E>>();
	}
	
	// Should E extend Comparable so that we can natively sort them without caring about values?
	public abstract void add(E object, Double value);
	
	class Node<E> {
		private Double value;
		private E object;
		private Node next;
		private Node up;
		
		public Node(E object) {
			this(object,null);
		}
		
		public Node(E object, Double value) {
			this.setObject(object);
			value = null;
			next = null;
			up = null;
		}

		public Double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

		public E getObject() {
			return object;
		}

		public void setObject(E object) {
			this.object = object;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public Node<E> getUp() {
			return up;
		}

		public void setUp(Node<E> up) {
			this.up = up;
		}
		
		public boolean isHead() {
			return value == null;
		}
	}
}
