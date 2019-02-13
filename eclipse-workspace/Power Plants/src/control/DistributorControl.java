package control;

import java.util.List;

import org.neodatis.odb.Objects;

import entities.DistributionLine;
import entities.DistributionNetwork;
import entities.Distributor;
import entities.ServiceZone;

public class DistributorControl extends Control {

	// Method to store new distributor at bd
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
	public void deleteNetwork(DistributionNetwork network, Distributor distributor) {
		distributor.getNetworks().remove(network);
		if (!network.getLines().isEmpty()) {
			for (DistributionLine line : network.getLines()) {
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

	public Objects<Distributor> getDistributors() {
		return getOdb().getObjects(Distributor.class);
	}
	
	public Objects<DistributionNetwork> getDistributionNetworks() {
		return getOdb().getObjects(DistributionNetwork.class);
	}
	
	public Objects<DistributionLine> getDistributionLines() {
		return getOdb().getObjects(DistributionLine.class);
	}
}
