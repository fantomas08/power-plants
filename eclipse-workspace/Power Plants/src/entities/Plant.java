package entities;

import java.util.Date;

public class Plant {
	
	private String name;
	private double averageProduction;
	private double maxProduction;
	private Date startedWorkingDate;
	
	public Plant(String name, double averageProduction, double maxProduction, Date startedWorkingDate) {
		this.name = name;
		this.averageProduction = averageProduction;
		this.maxProduction = maxProduction;
		this.startedWorkingDate = startedWorkingDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAverageProduction() {
		return averageProduction;
	}

	public void setAverageProduction(double averageProduction) {
		this.averageProduction = averageProduction;
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
		return "Plant [name=" + name + ", averageProduction=" + averageProduction + ", maxProduction=" + maxProduction
				+ ", startedWorkingDate=" + startedWorkingDate + "]";
	}
	
}
