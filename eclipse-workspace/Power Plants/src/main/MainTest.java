package main;

import java.util.ArrayList;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import control.Control;
import control.DistributorControl;
import control.PlantControl;
import control.ServiceZoneControl;
import entities.Delivery;
import entities.DistributionLine;
import entities.DistributionNetwork;
import entities.Distributor;
import entities.NuclearPlant;
import entities.Plant;
import entities.ServiceZone;

public class MainTest {

	public static final String ODB_NAME = "power_plants.neodatis";
	
	public static void main(String[] args) {
		ODB odb = Control.getOdb();
		
		
		ServiceZoneControl szc = new ServiceZoneControl();
		DistributorControl dc = new DistributorControl();
		
		/*
		ServiceZone zone1 = new ServiceZone(123.12, 30, "Toledo");
		szc.addZone(zone1);
		ServiceZone zone2 = new ServiceZone(356.78, 100, "Madrid");
		szc.addZone(zone2);
		Objects<ServiceZone> zones = odb.getObjects(ServiceZone.class);
		//System.out.println(zones.toString() + "\n");
		
		Distributor dis1 = new Distributor("distributor1", new ArrayList());
		Distributor dis2 = new Distributor("distributor2", new ArrayList());
		
		DistributionNetwork net1 = new DistributionNetwork(new ArrayList());
		DistributionNetwork net2 = new DistributionNetwork(new ArrayList());
		
		DistributionLine line1 = new DistributionLine(new ArrayList());	
		DistributionLine line2 = new DistributionLine(new ArrayList());
		
		line1.getZones().add(zone1);
		line2.getZones().add(zone2);
		net1.getLines().add(line1);
		net2.getLines().add(line2);
		dis1.getNetworks().add(net1);
		dis2.getNetworks().add(net2);
		
		dc.addLine(line1, net1);
		dc.addLine(line2, net2);
		dc.addNetwork(dis1, net1);
		dc.addNetwork(dis2, net2);
		dc.addDistributor(dis1);
		dc.addDistributor(dis2);
		*/
		
		/*ArrayList<Distributor> disAr = new ArrayList<Distributor>();
		ArrayList<DistributionNetwork> netAr = new ArrayList<DistributionNetwork>();
		ArrayList<DistributionLine> lineAr = new ArrayList<DistributionLine>();
		
		
		Objects<Distributor> dis = odb.getObjects(Distributor.class);
		while (dis.hasNext()) {
			disAr.add(dis.next());
		}
		
		Objects<DistributionNetwork> nets = odb.getObjects(DistributionNetwork.class);
		while (nets.hasNext()) {
			netAr.add(nets.next());
		}
		
		Objects<DistributionLine> lines = odb.getObjects(DistributionLine.class);
		while (lines.hasNext()) {
			lineAr.add(lines.next());
		}
		
		dc.deleteDistributor(disAr.get(0));
		
		System.out.println(disAr.toString());
		System.out.println(netAr.toString());
		System.out.println(lineAr.toString());
		*/
		
		
		odb.close();
	}

}
