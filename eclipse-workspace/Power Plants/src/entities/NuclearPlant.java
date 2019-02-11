package entities;

import java.util.List;

public class NuclearPlant extends Plant {

	private int numReactors;
	private double volConsumedPlutonium;
	private double volProducedWaste;
	
	public NuclearPlant(String name, double avgProduction, double maxProduction, String startedWorkingDate,
			List<Delivery> deliveries, int numReactors, double volConsumedPlutonium, double volProducedWaste) {
		super(name, avgProduction, maxProduction, startedWorkingDate, deliveries);
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
