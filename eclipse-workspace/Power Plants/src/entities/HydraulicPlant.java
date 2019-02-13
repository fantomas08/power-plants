package entities;

import java.util.Date;
import java.util.List;

public class HydraulicPlant extends Plant {
	
	private double maxCapacity;
	private double occupation;
	private int numTurbines;
	
	public HydraulicPlant(String name, double avgProduction, double maxProduction, Date startedWorkingDate,
			List<Delivery> deliveries, double maxCapacity, double occupation, int numTurbines) {
		super(name, avgProduction, maxProduction, startedWorkingDate, deliveries);
		this.maxCapacity = maxCapacity;
		this.occupation = occupation;
		this.numTurbines = numTurbines;
	}

	public double getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(double maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public double getOccupation() {
		return occupation;
	}

	public void setOccupation(double occupation) {
		this.occupation = occupation;
	}

	public int getNumTurbines() {
		return numTurbines;
	}

	public void setNumTurbines(int numTurbines) {
		this.numTurbines = numTurbines;
	}

	@Override
	public String toString() {
		return super.toString() + " HydraulicPlant [maxCapacity=" + maxCapacity + ", occupation=" + occupation + ", numTurbines="
				+ numTurbines + "]";
	}
	
}
