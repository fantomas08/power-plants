package control;

import java.util.ArrayList;

import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import entities.DistributionLine;
import entities.DistributionNetwork;
import entities.Distributor;
import entities.ServiceZone;

public class DistributorControl extends Control {

	// Method to store new distributor at bd
	public void addDistributor(Distributor distributor) {
		getOdb().store(distributor);
	}

	public void addNetwork(Distributor distributor) {
		distributor.getNetworks().add(new DistributionNetwork(new ArrayList<DistributionLine>()));
		getOdb().store(distributor);
	}

	public void addLine(DistributionNetwork network) {
		int id = 0;
		for(DistributionLine line : network.getLines()) {
			if(line.getId() > id) {
				id = line.getId();
			}
		}
		++id;
		network.getLines().add(new DistributionLine(id, new ArrayList<ServiceZone>()));
		getOdb().store(network);
	}

	public void deleteDistributor(Distributor distributor) {
		getOdb().delete(distributor);
	}

	public void deleteNetwork(DistributionNetwork network, Distributor distributor) {
		distributor.getNetworks().remove(network);
		getOdb().store(distributor);
	}

	public void deleteLine(DistributionLine line, DistributionNetwork network) {
		network.getLines().remove(line);
		getOdb().store(network);
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
	
	public Distributor findDistributor(String name) {
		IQuery q = new CriteriaQuery(Distributor.class, Where.equal("name", name));
		Objects<Distributor> distributors = getOdb().getObjects(q);
		if (!distributors.isEmpty()) {
			return distributors.getFirst();
		}
		return null;
	}
}

