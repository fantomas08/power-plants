package control;

import entities.ServiceZone;

public class ServiceZoneControl extends Control {

	public void addZone(ServiceZone zone) {
		getOdb().store(zone);
	}
	
	public void setAvgConsumption(ServiceZone zone) {
		
	}
	
	public void setNumConsumers(ServiceZone zone) {
		
	}
	
	public void registerDistributionLine() {
		
	}
	
	public void deleteDistributionLine() {
		
	}
}
