package main;

import control.DistributorControl;
import control.PlantControl;
import control.ServiceZoneControl;
import interfaces.Menu;

public class Main {
	
	public static void main(String[] args) throws Exception {
		PlantControl plantControl = new PlantControl();
		DistributorControl distributorControl = new DistributorControl();
		ServiceZoneControl serviceZoneControl = new ServiceZoneControl();
		Menu menu = new Menu(plantControl, distributorControl, serviceZoneControl);
		menu.startMenu();
	}

}
