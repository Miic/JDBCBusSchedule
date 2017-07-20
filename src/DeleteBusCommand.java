import java.sql.ResultSet;

public class DeleteBusCommand implements SQLCommand {

	private MySQLDriver driver;
	private static final String[] PARAMETERS = {"BusID"};
	
	public DeleteBusCommand(MySQLDriver driver) {
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
			return driver.executeStatement("Delete from Bus where BusID=?" + args[0]);
		}
		return null;
	}
}