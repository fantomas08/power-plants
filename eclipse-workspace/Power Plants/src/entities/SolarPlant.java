package entities;

import java.util.Date;

public class SolarPlant extends Plant {
	
	private double solarPanelSurface;
	private double yearlyAvgSunHours;
	private PanelType panelType;
	
	public SolarPlant(String name, double avgProduction, double maxProduction, Date startedWorkingDate,
			double solarPanelSurface, double yearlyAvgSunHours, PanelType panelType) {
		super(name, avgProduction, maxProduction, startedWorkingDate);
		this.solarPanelSurface = solarPanelSurface;
		this.yearlyAvgSunHours = yearlyAvgSunHours;
		this.panelType = panelType;
	}

	public double getSolarPanelSurface() {
		return solarPanelSurface;
	}

	public void setSolarPanelSurface(double solarPanelSurface) {
		this.solarPanelSurface = solarPanelSurface;
	}

	public double getYearlyAvgSunHours() {
		return yearlyAvgSunHours;
	}

	public void setYearlyAvgSunHours(double yearlyAvgSunHours) {
		this.yearlyAvgSunHours = yearlyAvgSunHours;
	}

	public PanelType getPanelType() {
		return panelType;
	}

	public void setPanelType(PanelType panelType) {
		this.panelType = panelType;
	}

	@Override
	public String toString() {
		return "SolarPlant [solarPanelSurface=" + solarPanelSurface + ", yearlyAvgSunHours=" + yearlyAvgSunHours
				+ ", panelType=" + panelType + "]";
	}
	
}
