package entities;

import java.util.List;

public class Plant {
	
	private String name;
	private double avgProduction;
	private double maxProduction;
	private String startedWorkingDate;
	private List<Delivery> deliveries;
	
	public Plant(String name, double avgProduction, double maxProduction, String startedWorkingDate, List<Delivery> deliveries) {
		this.name = name;
		this.avgProduction = avgProduction;
		this.maxProduction = maxProduction;
		this.startedWorkingDate = startedWorkingDate;
		this.deliveries = deliveries;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAvgProduction() {
		return avgProduction;
	}

	public void setAvgProduction(double avgProduction) {
		this.avgProduction = avgProduction;
	}

	public double getMaxProduction() {
		return maxProduction;
	}

	public void setMaxProduction(double maxProduction) {
		this.maxProduction = maxProduction;
	}

	public String getStartedWorkingDate() {
		return startedWorkingDate;
	}

	public void setStartedWorkingDate(String startedWorkingDate) {
		this.startedWorkingDate = startedWorkingDate;
	}

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	@Override
	public String toString() {
		return "Plant [name=" + name + ", avgProduction=" + avgProduction + ", maxProduction=" + maxProduction
				+ ", startedWorkingDate=" + startedWorkingDate + ", deliveries=" + deliveries + "]";
	}
	
}
