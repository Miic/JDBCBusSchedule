import java.sql.ResultSet;

public class UpdateActualTripInfo implements SQLCommand {

	private MySQLDriver driver;
	private static final String[] PARAMETERS = {"TripNumber", "Date", "ScheduledStartTime", "StopNumber", "ParameterToChange", "NewValue"};
	
	public UpdateActualTripInfo(MySQLDriver driver) {
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
	public ResultSet execute(String... args) { //Param Name, Param Update
		if (args.length == PARAMETERS.length) { //4 Idef Keys + 2 Params --Unique Case: Can be any single attribute of ActualTripStopInfo
			return driver.executeStatement("UPDATE ActualTripStopInfo SET " + args[4] + " = " + args[5] 
					+ " WHERE ActualTripStopInfo.TripNumber = '" + args[0]
							+ "' AND ActualTripStopInfo.Date = '" + args[1]
							+ "' AND ActualTripStopInfo.ScheduledStartTime = '" + args[2]
						    + "' AND ActualTripStopInfo.StopNumber = '" + args[3] + "';" );
		}
		return null;
	}

}
