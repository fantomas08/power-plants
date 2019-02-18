package control;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class Control {
	private static final String ODB_NAME = "power_plants_test.neodatis";
	private static ODB odb;
	
	public static ODB getOdb() {
		if(odb == null) {
			odb = ODBFactory.open(ODB_NAME);
		} 
		return odb;
	}
	
	public static void closeOdb() {
		if(odb != null) {
			odb.close();
		}
	}
	
}
