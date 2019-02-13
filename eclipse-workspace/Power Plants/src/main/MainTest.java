package main;

import java.util.ArrayList;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

import control.DistributorControl;
import control.PlantControl;
import control.ServiceZoneControl;
import entities.Delivery;
import entities.Distributor;
import entities.NuclearPlant;
import entities.Plant;
import entities.ServiceZone;

public class MainTest {

	public static final String ODB_NAME = "power_plants.neodatis";
	
	public static void main(String[] args) {
		ODB odb = ODBFactory.open(ODB_NAME);
		
		ServiceZoneControl szc = new ServiceZoneControl();
		DistributorControl dc = new DistributorControl();
		
		szc.addZone(new ServiceZone(123.12, 30, "Toledo"));
		szc.addZone(new ServiceZone(356.78, 100, "Madrid"));
		Objects<ServiceZone> zones = odb.getObjects(ServiceZone.class);
		
				
		
		dc.addDistributor(new Distributor("distributor1", new ArrayList()));
		dc.addDistributor(new Distributor("distributor2", new ArrayList()));
		dc.addNetwork(distributor, network);
	}

}
