package org.sosim.model;

import java.io.Serializable;

public class SoSimProcess implements Serializable{

	private static final long serialVersionUID = 667850871446477811L;

	private static int ID_POOL = 1;
	
	public enum State{
		WAITING,
		RUNNING,
		SUSPENDED,
		IO;
	}
	
	public SoSimProcess(PcbColor color, final int priority){
		this.id = ID_POOL;
		this.color = color;
		this.priority = priority;
		ID_POOL++;
		this.state = SoSimProcess.State.WAITING;
	}
	
	private final int id;
	
	private final int priority;
	
	private SoSimProcess.State state;
	
	private final PcbColor color;

	public int getId() {
		return id;
	}

	public SoSimProcess.State getState() {
		return state;
	}

	public void setState(SoSimProcess.State state) {
		this.state = state;
	}

	public PcbColor getColor() {
		return color;
	}

	public int getPriority() {
		return priority;
	}
}
