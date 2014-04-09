package org.sosim.util;

public class CircularList<O> {
	
	private Node<O> current;
	
	private int size  = 0;
	
	public CircularList(){}
	
	public CircularList( O[] values ){
		
		for( O value : values ){
			
			Node<O> newNode = new Node<O>(value);
			
			if(this.current == null){
			
				this.current = newNode;

			}else{
				
				newNode.setNext(this.current.getNext());	
			}
				
			this.current.setNext(newNode);
				
			this.current = newNode;
				
			size++;

		}
		next();
	}
	
	public void next(){
		this.current = current.getNext();
	}
	
	public O getCurrentValue(){
		return current.getValue();
	}
	
	public O peek(){
		return current.getNext().getValue();
	}
	
	public void add(O newValue){
		
		Node<O> newNode = new Node<O>(newValue);
		
		newNode.setNext(this.current.getNext());	
		
		this.current.setNext(newNode);
		
		this.current = newNode;
		
		size++;
		
	}
	
	public void removeNext(){
		
		Node<O> oldNext = this.current.getNext();
		
		Node<O> newNext = oldNext.getNext();
		
		this.current.setNext(newNext);
		
		oldNext = null;

		size--;
		
	}
	
	public int getSize(){
		return this.size;
	}
	
	@Override
	public String toString(){
		StringBuilder string = new StringBuilder("[");
		for(int i = 0; i< size; i++){
			string.append(this.current.getValue());
			next();
			if(i != size-1){
				string.append(", ");
			}else{
				string.append("]");
			}
		}
		
		return string.toString();
		
	}

}
