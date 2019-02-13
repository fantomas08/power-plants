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
	
	public Objects<Object> getPlants(Class plantType) {
		return getOdb().getObjects(plantType);
	}
	
	public Objects<Object> getAllPlants() {
		Objects<Object> objects = getOdb().getObjects(HydraulicPlant.class);
		objects.addAll(getOdb().getObjects(SolarPlant.class));
		objects.addAll(getOdb().getObjects(NuclearPlant.class));
		objects.addAll(getOdb().getObjects(ThermalPlant.class));
		return objects;
	}
	
	public void addPlant(Plant plant) {
		getOdb().store(plant);
	}
	
}
