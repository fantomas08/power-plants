package control;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class Control {
	private static final String ODB_NAME = "power_plants.neodatis";
	private static ODB odb;
	
	protected static ODB getOdb() {
		if(odb == null) {
			odb = ODBFactory.open(ODB_NAME);
		} 
		return odb;
	}
	
}
