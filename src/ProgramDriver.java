import java.awt.EventQueue;
import java.sql.ResultSet;
import java.util.Hashtable;

public class ProgramDriver implements Driver {
	private static ProgramDriver instance;
	private MySQLDriver driver;
	private Hashtable<String, SQLCommand> commands;
	
	@SuppressWarnings("unused")
	private MainWindow window; //We just need it stored in a variable so Java Cleanup won't destroy it
	
	private ProgramDriver() {
		//Create User Interface
		EventQueue.invokeLater(new Runnable() {
				public void run() {
				try {
					window = new MainWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		initialize();
	}
	
	//Singleton Pattern
	//Restriction Rule: Only one Program Driver!
	public static ProgramDriver getInstance() {
		if (instance == null) {
			instance = new ProgramDriver();
		}
		return instance;
	}

	@Override
	public void initialize() {
		// <!> Hard Coding
		driver = new MySQLDriver("localhost", "lab4", "root", "probablyNotMyPassword");
		commands = new Hashtable<String, SQLCommand>();
		
		//Load-in Preset Commands. (Takes advantage of Composite + Command Patterns)
		commands.put("Display Trip Schedule", new DisplayTripScheduleCommand(driver));
		commands.put("Delete Trip", new DeleteTripCommand(driver));
		commands.put("Add Trip Offerings", new AddTripCommand(driver));
		commands.put("Change Driver", new ChangeDriverCommand(driver)); //Needs Command 
		commands.put("Change Bus", new ChangeBusCommand(driver)); // Needs Command
		
		commands.put("Display Trip Stops", new DisplayTripStopsCommand(driver)); //Needs Command
		
		commands.put("Display Driver Schedule", new DisplayDriverScheduleCommand(driver)); //Needs Command
		commands.put("Add Driver", new AddDriverCommand(driver));
		commands.put("Add Bus", new AddBusCommand(driver));
		commands.put("Delete Bus", new DeleteBusCommand(driver)); //Needs Command
		commands.put("Update ActualTripInfo", new UpdateActualTripInfo(driver));
	}
	
	protected ResultSet executeCommand(String hash, String[] arguments) {
		return commands.get(hash).execute(arguments);
	}
	
	protected boolean checkCommand(String hash) {
		return commands.contains(hash);
	}
	
	protected String[] getCommandParam(String hash) {
		return commands.get(hash).getParameters();
	}
}
