import java.sql.ResultSet;

public class AddDriverCommand implements SQLCommand {

	private MySQLDriver driver;
	private static final String[] PARAMETERS = {"DriverName", "DriverTelephoneNumber"};
	
	public AddDriverCommand(MySQLDriver driver) {
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
			sb.append("INSERT INTO Driver(");
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
