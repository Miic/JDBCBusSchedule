import java.sql.ResultSet;

public class ChangeBusCommand implements SQLCommand {

	private MySQLDriver driver;
	private static final String[] PARAMETERS = {"busID","tripNumber","date","startTime"};
	
	public ChangeBusCommand(MySQLDriver driver) {
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
			return driver.executeStatement("Update TripOffering " +
					             "Set BusID=\"" + args[0] 
						     + "\"where TripNumber=\"" + args[1] + 
						     "\" AND Date=\"" + args[2] + 
						      "\"AND ScheduleStartTime=\"" + args[3] + "\"");
		}
		return null;
	}

}
