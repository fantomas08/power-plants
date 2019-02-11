package entities;

import java.util.List;

public class Distributor {
	
	private String name;
	private List<DistributionNetwork> networks;

	public Distributor(String name, List<DistributionNetwork> networks) {
		this.name = name;
		this.networks = networks;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DistributionNetwork> getNetworks() {
		return networks;
	}

	public void setNetworks(List<DistributionNetwork> networks) {
		this.networks = networks;
	}

	@Override
	public String toString() {
		return "Distributor [name=" + name + ", networks=" + networks + "]";
	}
}
