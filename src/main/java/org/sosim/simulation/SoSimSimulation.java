package org.sosim.simulation;

import java.io.Serializable;
import java.util.List;

import org.sosim.model.SoSimProcess;

public interface SoSimSimulation extends Serializable{
	
	public boolean isReady();
	
	public List<SoSimProcess> getCurrentProcessQueue();
	public List<SoSimProcess> getProcessListByPriority( int priority );
	public List<Integer> getAvailablePriorities();
	public SoSimProcess getNextProcessForPriority( int priority );
	public SoSimProcess getRunningProcess();
	public SoSimProcess getIoProcess();
	
	public void createProcess(SoSimProcess created) throws PcbQueueFullException;
	public void incrementSimulationClock();

}
