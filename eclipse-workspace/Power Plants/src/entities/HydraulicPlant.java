package entities;

import java.util.Date;

public class HydraulicPlant extends Plant {
	
	private double maxCapacity;
	private double occupation;
	private int numTurbines;
	
	public HydraulicPlant(String name, double avgProduction, double maxProduction, Date startedWorkingDate,
			double maxCapacity, double occupation, int numTurbines) {
		super(name, avgProduction, maxProduction, startedWorkingDate);
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
		return "HydraulicPlant [maxCapacity=" + maxCapacity + ", occupation=" + occupation + ", numTurbines="
				+ numTurbines + "]";
	}
	
}
