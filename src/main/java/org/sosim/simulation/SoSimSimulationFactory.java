package org.sosim.simulation;

public class SoSimSimulationFactory {
	
	@SuppressWarnings("rawtypes")
	private static final Class DEFAULT = SoSimTestSimulation.class;

	public static SoSimSimulation getDefaultSimulation() {
		try {
			
			return (SoSimSimulation) DEFAULT.newInstance();
		
		} catch (InstantiationException e) {
			
			throw new IllegalStateException("simulation class not registred");
		
		} catch (IllegalAccessException e) {
			
			throw new IllegalStateException("simulation class not visible");
		}
	}

}
