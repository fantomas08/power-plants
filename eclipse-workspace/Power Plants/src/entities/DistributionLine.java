package entities;

import java.util.List;

public class DistributionLine {
	
	private int id;
	private List<ServiceZone> zones;

	public DistributionLine(int id, List<ServiceZone> zones) {
		this.id = id;
		this.zones = zones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ServiceZone> getZones() {
		return zones;
	}

	public void setZones(List<ServiceZone> zones) {
		this.zones = zones;
	}
	
	@Override
	public String toString() {
		return "DistributionLine [id=" + id + ", zones=" + zones + "]";
	}
}
