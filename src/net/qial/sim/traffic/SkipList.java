package net.qial.sim.traffic;

import java.util.LinkedList;
import java.util.List;

public class SkipList<E> {
	// base skip list idea to test traffic things
	private List<Node<E>> lists;
	private int levels = 0;
	
	public SkipList() {
		lists = new LinkedList<Node<E>>();
	}
	
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
