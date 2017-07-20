import java.sql.ResultSet;

public class AddBusCommand implements SQLCommand {

	private MySQLDriver driver;
	private static final String[] PARAMETERS = {"BusID", "Model", "Year"};
	
	public AddBusCommand(MySQLDriver driver) {
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
			sb.append("INSERT INTO Bus(");
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
