package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.neodatis.*;
import org.neodatis.odb.*;

import entities.Delivery;
import entities.NuclearPlant;
import entities.Plant;

public class Main {

	public static final String ODB_NAME = "power_plants.neodatis";
	
	public static void main(String[] args) throws Exception {
		
		Plant plant = new NuclearPlant("Planta nuclear 1", 
				100, 150, "25-11-1998", new ArrayList<Delivery>(),
				4, 50, 3);
		
		ODB odb = ODBFactory.open(ODB_NAME);
		
		//odb.store(plant);
		odb.close();
		
	}

}
