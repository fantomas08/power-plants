package control;

import org.neodatis.odb.Objects;

import entities.HydraulicPlant;
import entities.NuclearPlant;
import entities.Plant;
import entities.SolarPlant;
import entities.ThermalPlant;

public class PlantControl extends Control {
	
	public PlantControl() {
		
	}
	
	public Objects<Object> getAllPlants(Class plantType) {
		return getOdb().getObjects(plantType);
	}
	
	public void addPlant(Plant plant) {
		getOdb().store(plant);
	}
	
}
