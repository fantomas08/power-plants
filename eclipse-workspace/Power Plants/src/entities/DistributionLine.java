package entities;

import java.util.List;

public class DistributionLine {
	
	private int id;
	private static int count;
	private List<ServiceZone> zones;

	public DistributionLine(List<ServiceZone> zones) {
		++count;
		this.id = count;
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
