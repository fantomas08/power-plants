package main;

import java.util.ArrayList;

import org.neodatis.odb.*;

import control.Control;
import control.DistributorControl;
import control.PlantControl;
import control.ServiceZoneControl;
import entities.Delivery;
import entities.HydraulicPlant;
import entities.NuclearPlant;
import entities.Plant;
import interfaces.Menu;

public class Main {

	public static final String ODB_NAME = "power_plants.neodatis";
	
	public static void main(String[] args) throws Exception {
		PlantControl plantControl = new PlantControl();
		DistributorControl distributorControl = new DistributorControl();
		ServiceZoneControl serviceZoneControl = new ServiceZoneControl();
		//HydraulicPlant hPlant = new HydraulicPlant("planta1", 5, 6, null, null, 7, 8, 9);
		//plantControl.addPlant(hPlant);
		//plantControl.closeOdb();
		//plantControl.getAllPlants(HydraulicPlant.class);
		
		
		Menu menu = new Menu(plantControl, distributorControl, serviceZoneControl);
		menu.startMenu();
	}

}
