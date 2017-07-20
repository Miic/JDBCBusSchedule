import java.sql.ResultSet;

public class DeleteTripCommand implements SQLCommand {
	private MySQLDriver driver;
	private static final String[] PARAMETERS = {"Trip#", "Date", "ScheduledStartTime"};
	
	public DeleteTripCommand(MySQLDriver driver) {
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
	public ResultSet execute(String... args) {
		if (args.length == PARAMETERS.length) {
			return driver.executeStatement("DELETE FROM TO FROM TripOffering TO WHERE TO.TripNumber = '" + args[0]
					+ "' AND TO.DATE = '" + args[1]
					+ "' AND TO.ScheduledStartTime = '" + args[2] + "';");
		}
		return null;
	}

	@Override
	public String[] getParameters() {
		return PARAMETERS;
	}

}
