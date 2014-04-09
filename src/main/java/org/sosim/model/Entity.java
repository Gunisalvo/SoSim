package org.sosim.model;

public abstract class Entity {
	
	private Integer id;

	public Entity(){}
	
	public Entity(int id){
		
		this.id = id;
	
	}
	
	public int getId() {
		
		return id;
	
	}

	public void setId(int id) {
		
		this.id = id;
	
	}

}
