package main;

import java.util.ArrayList;

import org.neodatis.odb.*;

import control.Control;
import control.PlantControl;
import entities.Delivery;
import entities.HydraulicPlant;
import entities.NuclearPlant;
import entities.Plant;
import interfaces.Menu;

public class Main {

	public static final String ODB_NAME = "power_plants.neodatis";
	
	public static void main(String[] args) throws Exception {
		PlantControl plantControl = new PlantControl();
		
		//HydraulicPlant hPlant = new HydraulicPlant("planta1", 5, 6, null, null, 7, 8, 9);
		//plantControl.addPlant(hPlant);
		//plantControl.closeOdb();
		//plantControl.getAllPlants(HydraulicPlant.class);
		
		
		Menu menu = new Menu(plantControl);
		menu.startMenu();
	}

}
