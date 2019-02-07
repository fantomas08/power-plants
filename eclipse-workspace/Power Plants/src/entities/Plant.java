package entities;

import java.util.Date;

public class Plant {
	
	private String name;
	private double avgProduction;
	private double maxProduction;
	private Date startedWorkingDate;
	
	public Plant(String name, double avgProduction, double maxProduction, Date startedWorkingDate) {
		super();
		this.name = name;
		this.avgProduction = avgProduction;
		this.maxProduction = maxProduction;
		this.startedWorkingDate = startedWorkingDate;
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

	public Date getStartedWorkingDate() {
		return startedWorkingDate;
	}

	public void setStartedWorkingDate(Date startedWorkingDate) {
		this.startedWorkingDate = startedWorkingDate;
	}

	@Override
	public String toString() {
		return "Plant [name=" + name + ", avgProduction=" + avgProduction + ", maxProduction=" + maxProduction
				+ ", startedWorkingDate=" + startedWorkingDate + "]";
	}
	
}
