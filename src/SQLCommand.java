import java.sql.ResultSet;

//Composite Pattern

public interface SQLCommand {
	public MySQLDriver getDriver();
	public void setDriver(MySQLDriver driver);
	public ResultSet execute(String...args);
	public String[] getParameters();
}
