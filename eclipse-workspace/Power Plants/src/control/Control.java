package control;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class Control {
	public static final String ODB_NAME = "power_plants.neodatis";
	public static final ODB odb = ODBFactory.open(ODB_NAME);
}
