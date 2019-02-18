package util;

import entities.HydraulicPlant;
import entities.NuclearPlant;
import entities.SolarPlant;
import entities.ThermalPlant;

public class PlantTypeParser {
	
	public static String parseType(Class type) {
		if(type == HydraulicPlant.class) {
			return "Hydraulic";
		}
		if(type == SolarPlant.class) {
			return "Solar";
		}
		if(type == NuclearPlant.class) {
			return "Nuclear";
		}
		if(type == ThermalPlant.class) {
			return "Thermal";
		}
		return "Unknown";
	}
}
