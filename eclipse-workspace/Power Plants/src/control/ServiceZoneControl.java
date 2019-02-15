package control;

import java.util.ArrayList;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import entities.DistributionLine;
import entities.ServiceZone;
import util.ZoneLineCounter;

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
	
	public ArrayList<ServiceZone> getZonesNumLines(int num, DistributorControl control) {
		Objects<DistributionLine> objects = control.getDistributionLines();
		ArrayList<ZoneLineCounter> lineCounters = new ArrayList<ZoneLineCounter>();
		boolean foundCounter = false;
		
		// Loop through all distribution lines
		while(objects.hasNext()) {
			DistributionLine currentDLine = objects.next();
			// Loop through all the zones that the current line passes through
			for(ServiceZone currentZone : currentDLine.getZones()) {
				// Loop through the already counted zones
				for(ZoneLineCounter currentCounter : lineCounters) {
					// If a zone of the current line was already counted, add 1
					if(currentCounter.getZone().equals(currentZone)) {
						currentCounter.setCount(1 + currentCounter.getCount());
						foundCounter = true;
						break;
					}
				}
				// If the zone was not counted, add it
				if(!foundCounter) {
					lineCounters.add(new ZoneLineCounter(currentZone, 1));
				}
				foundCounter = false;
			}
		}
		
		// Loop through all the zones and add the ones with more lines than the attribute
		ArrayList<ServiceZone> zones = new ArrayList<ServiceZone>();
		for(ZoneLineCounter counter : lineCounters) {
			if(counter.getCount() > num) {
				zones.add(counter.getZone());
			}
		}
		return zones;
	}
	
	public ServiceZone findServiceZone(String name) {
		IQuery q = new CriteriaQuery(ServiceZone.class, Where.equal("name", name));
		Objects<ServiceZone> zones = getOdb().getObjects(q);
		if (!zones.isEmpty()) {
			return zones.getFirst();
		}
		return null;
	}
}
