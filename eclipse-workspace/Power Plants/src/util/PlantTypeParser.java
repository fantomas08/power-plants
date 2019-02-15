package util;

import entities.HydraulicPlant;
import entities.NuclearPlant;
import entities.SolarPlant;
import entities.ThermalPlant;

public class PlantTypeParser {
	
	public static String parseType(Class type) {
		if(type == HydraulicPlant.class) {
			return "Hydraulic Plant";
		}
		if(type == SolarPlant.class) {
			return "Solar Plant";
		}
		if(type == NuclearPlant.class) {
			return "Nuclear Plant";
		}
		if(type == ThermalPlant.class) {
			return "Thermal Plant";
		}
		return "Unknown";
	}
}
