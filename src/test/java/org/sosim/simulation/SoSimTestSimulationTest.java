package org.sosim.simulation;

import junit.framework.Assert;

import org.junit.Test;
import org.sosim.model.PcbColor;
import org.sosim.model.SoSimProcess;

public class SoSimTestSimulationTest {

	private SoSimSimulation cobaia;
	
	@Test
	public void readyCheck() {
		
		cobaia = new SoSimTestSimulation();
		Assert.assertFalse(cobaia.isReady());
		SoSimProcess p1 = new SoSimProcess(PcbColor.RED,1);
		
		try{
			cobaia.createProcess(p1);
		}catch(PcbQueueFullException ex){
			Assert.fail();
		}
		Assert.assertTrue(cobaia.isReady());
	}
	
	
	@Test
	public void factoryCheck(){
		cobaia = SoSimSimulationFactory.getDefaultSimulation();
		Assert.assertNotNull(cobaia);
		Assert.assertTrue(cobaia instanceof SoSimSimulation);
	}
	
	@Test
	public void oneProcess() {
		
		cobaia = new SoSimTestSimulation();
		SoSimProcess p1 = new SoSimProcess(PcbColor.RED,1);
		
		try{
			cobaia.createProcess(p1);
		}catch(PcbQueueFullException ex){
			Assert.fail();
		}
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 1);
		Assert.assertNull(cobaia.getRunningProcess());
		Assert.assertNull(cobaia.getIoProcess());
		
		cobaia.incrementSimulationClock();
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 1);
		Assert.assertEquals(cobaia.getRunningProcess(),p1);
		Assert.assertNull(cobaia.getIoProcess());
		
		cobaia.incrementSimulationClock();
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 1);
		Assert.assertNull(cobaia.getRunningProcess());
		Assert.assertEquals(cobaia.getIoProcess(),p1);
		
		cobaia.incrementSimulationClock();
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 1);
		Assert.assertNull(cobaia.getRunningProcess());
		Assert.assertNull(cobaia.getIoProcess());
		
	}
	@Test
	public void twoProcesses() {
		
		cobaia = new SoSimTestSimulation();
		SoSimProcess p1 = new SoSimProcess(PcbColor.RED,1);
		SoSimProcess p2 = new SoSimProcess(PcbColor.BLUE,1);
		
		try{
			cobaia.createProcess(p1);
			cobaia.createProcess(p2);
		}catch(PcbQueueFullException ex){
			Assert.fail();
		}
		
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 2);
		Assert.assertNull(cobaia.getRunningProcess());
		Assert.assertNull(cobaia.getIoProcess());
		
		cobaia.incrementSimulationClock();
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 2);
		Assert.assertEquals(cobaia.getRunningProcess(),p1);
		Assert.assertNull(cobaia.getIoProcess());
		
		cobaia.incrementSimulationClock();
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 2);
		Assert.assertEquals(cobaia.getRunningProcess(),p2);
		Assert.assertEquals(cobaia.getIoProcess(),p1);
		
		cobaia.incrementSimulationClock();
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 2);
		Assert.assertNull(cobaia.getRunningProcess());
		Assert.assertEquals(cobaia.getIoProcess(),p2);
		
		cobaia.incrementSimulationClock();
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 2);
		Assert.assertEquals(cobaia.getRunningProcess(),p1);
		Assert.assertNull(cobaia.getIoProcess());
		
		cobaia.incrementSimulationClock();
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 2);
		Assert.assertEquals(cobaia.getRunningProcess(),p2);
		Assert.assertEquals(cobaia.getIoProcess(),p1);
		
	}
	@Test
	public void threeProcesses() {
		
		cobaia = new SoSimTestSimulation();
		SoSimProcess p1 = new SoSimProcess(PcbColor.RED,1);
		SoSimProcess p2 = new SoSimProcess(PcbColor.BLUE,1);
		SoSimProcess p3 = new SoSimProcess(PcbColor.GREEN,1);
		
		try{
			cobaia.createProcess(p1);
			cobaia.createProcess(p2);
			cobaia.createProcess(p3);
		}catch(PcbQueueFullException ex){
			Assert.fail();
		}
		
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 3);
		Assert.assertNull(cobaia.getRunningProcess());
		Assert.assertNull(cobaia.getIoProcess());
		
		cobaia.incrementSimulationClock();
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 3);
		Assert.assertEquals(cobaia.getRunningProcess(),p1);
		Assert.assertNull(cobaia.getIoProcess());
		
		cobaia.incrementSimulationClock();
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 3);
		Assert.assertEquals(cobaia.getRunningProcess(),p2);
		Assert.assertEquals(cobaia.getIoProcess(),p1);
		
		cobaia.incrementSimulationClock();
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 3);
		Assert.assertEquals(cobaia.getRunningProcess(),p3);
		Assert.assertEquals(cobaia.getIoProcess(),p2);
		
		cobaia.incrementSimulationClock();
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 3);
		Assert.assertEquals(cobaia.getRunningProcess(),p1);
		Assert.assertEquals(cobaia.getIoProcess(),p3);
		
		cobaia.incrementSimulationClock();
		
		Assert.assertTrue(cobaia.getCurrentProcessQueue().size() == 3);
		Assert.assertEquals(cobaia.getRunningProcess(),p2);
		Assert.assertEquals(cobaia.getIoProcess(),p1);
	}
	@Test
	public void tenProcessos() {
		
		SoSimProcess[] processes = new SoSimProcess[]{	new SoSimProcess(PcbColor.GRAY,1),
														new SoSimProcess(PcbColor.GRAY,1),
														new SoSimProcess(PcbColor.GRAY,1),
														new SoSimProcess(PcbColor.GRAY,1),
														new SoSimProcess(PcbColor.GRAY,1),
														new SoSimProcess(PcbColor.GRAY,1),
														new SoSimProcess(PcbColor.GRAY,1),
														new SoSimProcess(PcbColor.GRAY,1),
														new SoSimProcess(PcbColor.GRAY,1),
														new SoSimProcess(PcbColor.GRAY,1)};
		cobaia = new SoSimTestSimulation();
		
		try{
			for(SoSimProcess p : processes){	
					cobaia.createProcess(p);	
			}
		}catch(PcbQueueFullException ex){
			Assert.fail();
		}
		
		for(int i = 0; i< 12; i++){
			cobaia.incrementSimulationClock();
			System.out.println(cobaia);
		}
		

	}

}
