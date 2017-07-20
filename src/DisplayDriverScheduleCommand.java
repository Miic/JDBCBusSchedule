import java.sql.ResultSet;

public class DisplayDriverScheduleCommand implements SQLCommand {

	private MySQLDriver driver;
	private static final String[] PARAMETERS = {"driverName","date"};
	
	public DisplayDriverScheduleCommand(MySQLDriver driver) {
		this.driver = driver;
	}
	
	@Override
	public MySQLDriver getDriver() {
		return driver;
	}

	@Override
	public void setDriver(MySQLDriver driver) {
		this.driver = driver;
	}

	@Override
	public String[] getParameters() {
		return PARAMETERS;
	}

	@Override
	public ResultSet execute(String... args) {
		if (args.length == PARAMETERS.length) {
		     return driver.executeStatement("Select * From Driver D,TripOffering T"
						     + "Where T.DriverName=D.DriverName AND D.DriverName=\"" 
				             + args[0] + "\"AND T.Date=\"" + args[1] + "\"");

		}
		return null;
	}
}
