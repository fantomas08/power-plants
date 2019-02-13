package main;

import java.util.ArrayList;

import org.neodatis.odb.*;

import control.Control;
import control.PlantControl;
import entities.Delivery;
import entities.NuclearPlant;
import entities.Plant;
import interfaces.Menu;

public class Main {

	public static final String ODB_NAME = "power_plants.neodatis";
	
	public static void main(String[] args) throws Exception {
		PlantControl plantControl = new PlantControl();
		Menu menu = new Menu(plantControl);
		menu.startMenu();
	}

}
