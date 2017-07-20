import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDriver implements Driver {
	private String dbHost;
	private int dbPort;
	private String dbName;
	private String dbUser;
	private String dbPass;
	
	private Connection con;
	
	public MySQLDriver(String dbHost, String dbName, String dbUser) {
		this.dbHost = dbHost;
		this.dbName = dbName;
		this.dbUser = dbUser;
		dbPort = 3306;
		initialize();
	}
	
	public MySQLDriver(String dbHost, String dbName, String dbUser, String dbPass) {
		this.dbHost = dbHost;
		this.dbName = dbName;
		this.dbUser = dbUser;
		this.dbPass = dbPass;
		dbPort = 3306;
		initialize();
	}
	
	@Override
	public void initialize() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(toString(), dbUser, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public int getDbPort() {
		return dbPort;
	}

	public void setDbPort(int dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPass() {
		return dbPass;
	}

	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}
	
	public ResultSet executeStatement(String statement) {
		try {
			Statement stmt = con.createStatement();
			return stmt.executeQuery(statement);
		} catch (Exception e) {
 			e.printStackTrace();
		}
		return null;
	}
	
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("jdbc:mysql://");
		sb.append(dbHost);
		sb.append(":");
		sb.append(dbPort);
		sb.append("/");
		sb.append(dbName);
		return sb.toString();
	}
	
	////
	//Static Methods
	////
	
	public static String getColumnNames(ResultSet rs) {
		StringBuilder sb = new StringBuilder();
		try {
			for(int col=1; col <= rs.getMetaData().getColumnCount(); col++) {
				sb.append(rs.getMetaData().getColumnName(col));
				sb.append(" ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String getStringResults(ResultSet rs) {
		StringBuilder sb = new StringBuilder();
		try {
			while (rs.next()) {
				for(int col = 1; col <= rs.getMetaData().getColumnCount(); col++) {
					sb.append(rs.getString(col));
					sb.append(" ");
				}
			}
		} catch (Exception e) {
			 e.printStackTrace();
		 }
		return sb.toString();
	}
	
}
