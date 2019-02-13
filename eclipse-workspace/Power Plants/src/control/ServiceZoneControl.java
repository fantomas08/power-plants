package control;

import org.neodatis.odb.Objects;

import entities.DistributionLine;
import entities.ServiceZone;

public class ServiceZoneControl extends Control {
	
	// Method to add new zone or modify existing
	public void addZone(ServiceZone zone) {
		getOdb().store(zone);
	}
	
	// Method to add new distribution line to service zone
	public void registerDistributionLine(ServiceZone zone, DistributionLine line) {
		line.getZones().add(zone);
		getOdb().store(line);
	}
	
	// Method to remove distribution line from service zone
	public void deleteDistributionLine(ServiceZone zone, DistributionLine line) {
		line.getZones().remove(zone);
		getOdb().store(line);
	}
	
	public Objects<ServiceZone> getServiceZones() {
		return getOdb().getObjects(ServiceZone.class);
	}
}
