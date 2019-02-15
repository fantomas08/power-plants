package control;

import java.util.ArrayList;
import java.util.Date;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import entities.Delivery;
import entities.Distributor;
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
	
	public ArrayList<Plant> getPlantsBeforeDate(Date date) {
		Objects<Object> objects = getAllPlants();
		ArrayList<Plant> plants = new ArrayList<Plant>();
		while(objects.hasNext()) {
			Plant plant = (Plant) objects.next();
			if(plant.getStartedWorkingDate().before(date)) {
				plants.add(plant);
			}
		}
		return plants;
	}
	
	public ArrayList<Distributor> getSolarThermalDistributors() {
		ArrayList<Distributor> distributors = new ArrayList<Distributor>();
		Objects<Object> objects = getOdb().getObjects(ThermalPlant.class);
		objects.addAll(getOdb().getObjects(SolarPlant.class));
		
		Plant[] plants = objects.toArray(new Plant[0]);
		
		for(Plant plant : plants) {
			ArrayList<Delivery> deliveries = (ArrayList<Delivery>) plant.getDeliveries();
			for(Delivery delivery : deliveries) {
				if(!distributors.contains(delivery.getDistributor())) {
					distributors.add(delivery.getDistributor());
				}
			}
		}
		
		return distributors;
	}
	
	public ArrayList<Delivery> getDeliveriesByWasteAndDate(double volProducedWaste, Date date) {
		ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
		
		ICriterion iCriterion = Where.gt("volProducedWaste", volProducedWaste);
		CriteriaQuery query = new CriteriaQuery(NuclearPlant.class, iCriterion);
		Objects<NuclearPlant> objects = getOdb().getObjects(query);
		
		for(NuclearPlant plant : objects) {
			for(Delivery current : plant.getDeliveries()) {
				// Includes the specified date
				if(!current.getDate().before(date)) {
					deliveries.add(current);
				}
			}
		}
		
		return deliveries;
	}
	
	
}
