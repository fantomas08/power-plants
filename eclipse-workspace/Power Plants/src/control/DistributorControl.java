package control;

import java.util.List;

import entities.DistributionLine;
import entities.DistributionNetwork;
import entities.Distributor;

public class DistributorControl extends Control {

	//Method to store new distributor at bd
	public void addDistributor(Distributor distributor) {
		getOdb().store(distributor);
	}
	
	public void addNetwork(Distributor distributor, DistributionNetwork network) {
		distributor.getNetworks().add(network);
		getOdb().store(distributor);
	}
	
	public void addLine(DistributionLine line, DistributionNetwork network) {
		network.getLines().add(line);
		getOdb().store(network);
	}
	
	// Hay que probar
	public void deleteNetwork(int networkIndex, Distributor distributor) {
		DistributionNetwork network = distributor.getNetworks().get(networkIndex);
		
		if (!network.getLines().isEmpty()) {
			for (DistributionLine line : distributor.getNetworks().get(networkIndex).getLines()) {
				getOdb().delete(line);
			}
		}
		getOdb().delete(network);
	}
	
	// Hay que probar
	public void deleteLine(DistributionLine line, DistributionNetwork network) {
		network.getLines().remove(line);
		getOdb().delete(line);
	}
	
	// Hay que probar
	public void deleteDistributor(Distributor distributor) {
		if (!distributor.getNetworks().isEmpty()) {
			for (DistributionNetwork network : distributor.getNetworks()) {
				if (!network.getLines().isEmpty()) {
					for (DistributionLine line : network.getLines()) {
						getOdb().delete(line);
					}
				}
				getOdb().delete(network);
			}
		}
		getOdb().delete(distributor);
	}
}
