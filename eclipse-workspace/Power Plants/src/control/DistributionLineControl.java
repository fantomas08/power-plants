package control;

import org.neodatis.odb.Objects;

import entities.DistributionLine;

public class DistributionLineControl extends Control {
	
	public Objects<DistributionLine> getDistributionLines() {
		return getOdb().getObjects(DistributionLine.class);
	}

}
