package entities;

import java.util.List;

public class DistributionNetwork {
	
	private List<DistributionLine> lines;

	public DistributionNetwork(List<DistributionLine> lines) {
		this.lines = lines;
	}
	
	public List<DistributionLine> getLines() {
		return lines;
	}

	public void setLines(List<DistributionLine> lines) {
		this.lines = lines;
	}

	@Override
	public String toString() {
		return "DistributionNetwork [lines=" + lines + "]";
	}

	

}
