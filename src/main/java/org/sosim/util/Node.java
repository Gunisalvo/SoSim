package org.sosim.util;

public class Node<O> {
	
	private final O value;
	
	private Node<O> next;
	
	
	public Node(O value){
		this.value = value;
	}


	public Node<O> getNext() {
		return next;
	}


	public void setNext(Node<O> next) {
		this.next = next;
	}


	public O getValue() {
		return value;
	}

}
