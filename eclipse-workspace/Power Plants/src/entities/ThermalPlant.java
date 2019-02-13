package entities;

import java.util.Date;
import java.util.List;

public class ThermalPlant extends Plant {

	private int numFurnaces;
	private double volConsumedCarbon;
	private double volGasEmissions;
	
	public ThermalPlant(String name, double avgProduction, double maxProduction, Date startedWorkingDate,
			List<Delivery> deliveries, int numFurnaces, double volConsumedCarbon, double volGasEmissions) {
		super(name, avgProduction, maxProduction, startedWorkingDate, deliveries);
		this.numFurnaces = numFurnaces;
		this.volConsumedCarbon = volConsumedCarbon;
		this.volGasEmissions = volGasEmissions;
	}

	public int getNumFurnaces() {
		return numFurnaces;
	}

	public void setNumFurnaces(int numFurnaces) {
		this.numFurnaces = numFurnaces;
	}

	public double getVolConsumedCarbon() {
		return volConsumedCarbon;
	}

	public void setVolConsumedCarbon(double volConsumedCarbon) {
		this.volConsumedCarbon = volConsumedCarbon;
	}

	public double getVolGasEmissions() {
		return volGasEmissions;
	}

	public void setVolGasEmissions(double volGasEmissions) {
		this.volGasEmissions = volGasEmissions;
	}

	@Override
	public String toString() {
		return super.toString() + " ThermalPlant [numFurnaces=" + numFurnaces + ", volConsumedCarbon=" + volConsumedCarbon
				+ ", volGasEmissions=" + volGasEmissions + "]";
	}
	
}
