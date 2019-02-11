package main;

import java.util.ArrayList;

import org.neodatis.odb.*;

import control.Control;
import control.PlantControl;
import entities.Delivery;
import entities.NuclearPlant;
import entities.Plant;

public class Main {

	public static final String ODB_NAME = "power_plants.neodatis";
	
	public static void main(String[] args) throws Exception {
		
		Plant plant = new NuclearPlant("Planta nuclear 1", 
				100, 150, "25-11-1998", new ArrayList<Delivery>(),
				4, 50, 3);
		
		//odb.store(plant);
		PlantControl pc = new PlantControl();
		pc.printPlants();
		
		PlantControl pc2 = new PlantControl();
		pc2.printPlants();
		
		
		// odb.close();
	}

}
