package util;

import entities.ServiceZone;

public class ZoneLineCounter {
	
	private ServiceZone zone;
	private int count;
	
	public ZoneLineCounter(ServiceZone zone, int count) {
		this.zone = zone;
		this.count = count;
	}

	public ServiceZone getZone() {
		return zone;
	}

	public void setZone(ServiceZone zone) {
		this.zone = zone;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ZoneLineCounter [zone=" + zone + ", count=" + count + "]";
	}
	
}
