package entities;

import java.util.Date;

public class HydraulicPlant extends Plant {
	
	private double maxCapacity;
	private double occupation;
	private int numberOfTurbines;
	
	public HydraulicPlant(String name, double averageProduction, double maxProduction, Date startedWorkingDate, 
			double maxCapacity, double occupation, int numberOfTurbines) {
		super(name, averageProduction, maxProduction, startedWorkingDate);
		this.maxCapacity = maxCapacity;
		this.occupation = occupation;
		this.numberOfTurbines = numberOfTurbines;
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

	public int getNumberOfTurbines() {
		return numberOfTurbines;
	}

	public void setNumberOfTurbines(int numberOfTurbines) {
		this.numberOfTurbines = numberOfTurbines;
	}

	@Override
	public String toString() {
		return "HydraulicPlant [maxCapacity=" + maxCapacity + ", occupation=" + occupation + ", numberOfTurbines="
				+ numberOfTurbines + "]";
	}

}
