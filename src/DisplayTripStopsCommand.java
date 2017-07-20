import java.sql.ResultSet;

public class DisplayTripStopsCommand implements SQLCommand {

	private MySQLDriver driver;
	private static final String[] PARAMETERS = {"TripNumber"};
	
	public DisplayTripStopsCommand(MySQLDriver driver) {
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
			return driver.executeStatement("Select * From TripStopInfo Where TripNumber=" + args[0]);
		}
		return null;
	}

}
