package entities;

import java.util.Date;

public class NuclearPlant extends Plant {

	private int numReactors;
	private double volConsumedPlutonium;
	private double volProducedWaste;
	
	public NuclearPlant(String name, double avgProduction, double maxProduction, Date startedWorkingDate,
			int numReactors, double volConsumedPlutonium, double volProducedWaste) {
		super(name, avgProduction, maxProduction, startedWorkingDate);
		this.numReactors = numReactors;
		this.volConsumedPlutonium = volConsumedPlutonium;
		this.volProducedWaste = volProducedWaste;
	}

	public int getNumReactors() {
		return numReactors;
	}

	public void setNumReactors(int numReactors) {
		this.numReactors = numReactors;
	}

	public double getVolConsumedPlutonium() {
		return volConsumedPlutonium;
	}

	public void setVolConsumedPlutonium(double volConsumedPlutonium) {
		this.volConsumedPlutonium = volConsumedPlutonium;
	}

	public double getVolProducedWaste() {
		return volProducedWaste;
	}

	public void setVolProducedWaste(double volProducedWaste) {
		this.volProducedWaste = volProducedWaste;
	}

	@Override
	public String toString() {
		return "NuclearPlant [numReactors=" + numReactors + ", volConsumedPlutonium=" + volConsumedPlutonium
				+ ", volProducedWaste=" + volProducedWaste + "]";
	}
	
}
