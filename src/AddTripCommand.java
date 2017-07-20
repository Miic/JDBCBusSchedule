import java.sql.ResultSet;

public class AddTripCommand implements SQLCommand {

	private MySQLDriver driver;
	private static final String[] PARAMETERS = {"TripNumber", "Date", "ScheduledStartTime", "StopNumber", "ScheduledArrivalTime", "ActualStartTime", "ActualArrivalTime", "NumberOfPassengerIN", "NumberOfPassengerOut"};
	
	public AddTripCommand(MySQLDriver driver) {
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
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO ActualTripStopInfo(");
			for (String i : PARAMETERS) {
				sb.append(i);
				sb.append(",");
			}
			sb.append(") VALUES ( ");
			for(String i : args) {
				sb.append(i);
				sb.append(",");
			}
			sb.append(");");
			return driver.executeStatement(sb.toString());
		}
		return null;
	}
	
}
