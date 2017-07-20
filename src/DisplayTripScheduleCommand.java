import java.sql.ResultSet;

public class DisplayTripScheduleCommand implements SQLCommand {
	private MySQLDriver driver;
	private static final String[] PARAMETERS = {"ScheduledStartTime", "ScheduledArrivalTime", "DriverID", "BusID"};
	
	public DisplayTripScheduleCommand(MySQLDriver driver) {
		this.driver = driver;	
	}
	
	@Override
	public ResultSet execute(String...args) {
		if (args.length == PARAMETERS.length) {
			return driver.executeStatement("SELECT A.ActualTripStopInfo, A.ScheduledArrivalTime, TO.DriverName, TO.BusID FROM Trip T, "
					+ "TripOffering TO, ActualTripStopInfo WHERE A.TripNumber = A.TripNumber"
					+ "AND A.TripNumber = TO.TripNumber AND T.StartLocationName = '" + args[0]
					+ "' AND T.DestinationName = '" + args[1]
					+ "' AND TO.Date = " + args[2] + "';");
		}
		return null;
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
}
