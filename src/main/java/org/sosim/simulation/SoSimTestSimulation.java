package org.sosim.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sosim.model.SoSimProcess;

public class SoSimTestSimulation implements SoSimSimulation {

	private static final long serialVersionUID = 300851062958568225L;
	
	private final int MAX_QUEUE_SIZE = 10;

	private List<SoSimProcess> processQueue;
	
	private Integer cpuSlot;
	
	private Integer ioSlot;

	private Integer nextProcess;
	
	public SoSimTestSimulation(){
		this.processQueue = new ArrayList<SoSimProcess>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SoSimProcess> getCurrentProcessQueue() {
		return (List<SoSimProcess>) ((this.processQueue != null) ? this.processQueue : (Collections.emptyList()));
	}

	@Override
	public SoSimProcess getRunningProcess() {
		return (this.cpuSlot != null) ? this.processQueue.get(this.cpuSlot) : null;
	}

	@Override
	public SoSimProcess getIoProcess() {
		return (this.ioSlot != null) ? this.processQueue.get(this.ioSlot) : null;
	}

	@Override
	public void createProcess(SoSimProcess created) throws PcbQueueFullException {
		
		if(this.processQueue.size() == MAX_QUEUE_SIZE){
			throw new PcbQueueFullException("SoSimTestSimulation: maximum limit reached: " + MAX_QUEUE_SIZE);
		}
		
		this.processQueue.add(created);
		
		if( this.nextProcess == null ){
			this.nextProcess = 0;
		}
		
	}

	@Override
	public void incrementSimulationClock() {
		//System.out.println(this);
		if(!isReady()){
			throw new IllegalStateException("Simulation is not Ready.");
		}
		if( this.cpuSlot != null || this.ioSlot != null ){
			if( this.ioSlot != null ){
				sendIoToWaiting();
			}
			if( this.cpuSlot != null ){
				sendCpuToIo();
			}
			if(	!(this.processQueue.size() == 2 && this.ioSlot != null)
				&&		(
							( this.processQueue.size() == 2 && this.ioSlot == null)
								||
							( this.processQueue.size() > 2 )
						)
				){
				sendNextToRunning();
			}
		}else{
			sendNextToRunning();
		}

	}

	private void sendNextToRunning() {
		SoSimProcess fromWating;
		if( this.cpuSlot == null ){
			fromWating = this.processQueue.get(0);
			this.cpuSlot = 0;
		}else {
			fromWating = this.processQueue.get(this.cpuSlot);
		}
		fromWating.setState(SoSimProcess.State.RUNNING);	
	}

	private void sendIoToWaiting() {
		
		if(this.ioSlot != null){
			SoSimProcess fromIo = this.processQueue.get(this.ioSlot);
			fromIo.setState(SoSimProcess.State.WAITING);
		}
		
		if(this.cpuSlot != null){
			this.ioSlot = this.cpuSlot;
		}else{
			this.ioSlot = null;
		}
		
	}

	private void sendCpuToIo() {
		
		if(this.cpuSlot != null){
			SoSimProcess fromIo = this.processQueue.get(this.cpuSlot);
			fromIo.setState(SoSimProcess.State.IO);
			this.ioSlot = this.cpuSlot;
		}
		
		if( this.processQueue.size() > this.cpuSlot + 1 ){
			this.cpuSlot += 1;
		}else{
			this.cpuSlot = null;
		}
		
	}

	@Override
	public boolean isReady() {
		return ( this.processQueue.size() > 0 );
	}

	@Override
	public String toString() {
		return "SoSimTestSimulation [cpuSlot= " + cpuSlot + ", ioSlot= " + ioSlot
				+ "]";
	}

	@Override
	public List<SoSimProcess> getProcessListByPriority(int priority) {
		
		if(priority == 1){
			return this.processQueue;
		}else{
			return Collections.emptyList();
		}
	}

	@Override
	public List<Integer> getAvailablePriorities() {
		List<Integer> available = new ArrayList<Integer>();
		
		for(int i = 1; i<=8; i++){
			available.add(i);
		}
		
		return available;
	}

	@Override
	public SoSimProcess getNextProcessForPriority(int priority) {
		
		try{
			if( priority == 1 && this.nextProcess != null ){
				return this.processQueue.get( this.nextProcess );
			}else{
				return null;
			}
		}catch ( IndexOutOfBoundsException ex ){
			return null;
		}
	
	}
	
}
