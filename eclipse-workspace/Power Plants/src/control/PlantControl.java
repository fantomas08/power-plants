package control;

import org.neodatis.odb.Objects;

import entities.NuclearPlant;

public class PlantControl extends Control {
	
	public PlantControl() {
		
	}
	
	public void printPlants() {
		Objects<Object> list = getOdb().getObjects(NuclearPlant.class);
		
		for(Object obj : list) {
			System.out.println(obj.toString());
		}
	}
}
