package entities;

import java.util.Date;

public class SolarPlant extends Plant {
	
	private double solarPanelSurface;
	private double yearlyAverageSunHours;
	private PanelType panelType;
	
	public SolarPlant(String name, double averageProduction, double maxProduction, Date startedWorkingDate,
			double solarPanelSurface, double yearlyAverageSunHours, PanelType panelType) {
		super(name, averageProduction, maxProduction, startedWorkingDate);
		this.solarPanelSurface = solarPanelSurface;
		this.yearlyAverageSunHours = yearlyAverageSunHours;
		this.panelType = panelType;
	}

}
