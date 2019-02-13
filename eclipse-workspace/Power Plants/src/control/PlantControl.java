package control;

import org.neodatis.odb.Objects;

import entities.HydraulicPlant;
import entities.NuclearPlant;
import entities.SolarPlant;
import entities.ThermalPlant;

public class PlantControl extends Control {
	
	public PlantControl() {
		
	}
	
	public void printPlants() {
		Objects<Object> list = getOdb().getObjects(NuclearPlant.class);
		
		for(Object obj : list) {
			System.out.println(obj.toString());
		}
	}
	
	public void addHPlant(HydraulicPlant hPlant) {
		
	}
	
	public void addSPlant(SolarPlant hPlant) {
		
	}

	public void addNPlant(NuclearPlant hPlant) {
	
	}

	public void addTPlant(ThermalPlant hPlant) {
	
	}
	
	public Objects<HydraulicPlant> getAllPlants(Class plantType) {
		return getOdb().getObjects(plantType);
	}
	
}
