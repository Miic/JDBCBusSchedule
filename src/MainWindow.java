import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.Color;
import javax.swing.JButton;

public class MainWindow {

	private JFrame frmLab;
	private JLabel[] labels;
	private JTextField[] textFields;
	private JTextPane commandDisplay;
	private JTextPane consoleDisplay;
	private JTree tree;
	private JButton executeCommand;
	
	private DefaultMutableTreeNode selectedNode;
	
	public MainWindow() {
		labels =  new JLabel[10];
		textFields = new JTextField[10];
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLab = new JFrame();
		frmLab.setTitle("Lab 4");
		frmLab.setResizable(false);
		frmLab.setBounds(200, 200, 900, 600);
		frmLab.setVisible(true);
		frmLab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLab.getContentPane().setLayout(null);
		
		commandDisplay = new JTextPane();
		commandDisplay.setBackground(Color.BLACK);
		commandDisplay.setForeground(Color.GREEN);
		commandDisplay.setFont(new Font("Tahoma", Font.PLAIN, 24));
		commandDisplay.setEditable(false);
		commandDisplay.setText("Click a Command From the Command Tree! ");
		commandDisplay.setBounds(225, 11, 649, 41);
		frmLab.getContentPane().add(commandDisplay);
		
		consoleDisplay = new JTextPane();
		consoleDisplay.setText("Lab 4\r\n\r\nMichael Cruz\r\nKathleen Phan\r\n\r\nProf Salloum\r\nCPP CS 435 SQL\r\n\r\n");
		consoleDisplay.setBackground(Color.BLACK);
		consoleDisplay.setForeground(Color.GREEN);
		consoleDisplay.setBounds(225, 191, 649, 360);
		frmLab.getContentPane().add(consoleDisplay);
		
		for(int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel("Arg" + i);
			labels[i].setEnabled(false);;
			frmLab.getContentPane().add(labels[i]);
		}
		labels[0].setBounds(235, 66, 56, 14);
		labels[1].setBounds(235, 91, 56, 14);
		labels[2].setBounds(235, 116, 56, 14);
		labels[3].setBounds(235, 141, 56, 14);		
		labels[4].setBounds(235, 166, 56, 14);
		labels[5].setBounds(387, 66, 56, 14);
		labels[6].setBounds(387, 91, 56, 14);
		labels[7].setBounds(387, 116, 56, 14);		
		labels[8].setBounds(387, 141, 56, 14);
		labels[9].setBounds(387, 166, 56, 14);
		
		for(int i = 0; i < textFields.length; i++) {
			textFields[i] = new JTextField();
			textFields[i].setColumns(10);
			textFields[i].setEnabled(false);
			frmLab.getContentPane().add(textFields[i]);
		}
		textFields[0].setBounds(281, 63, 96, 20);
		textFields[1].setBounds(281, 88, 96, 20);
		textFields[2].setBounds(281, 113, 96, 20);
		textFields[3].setBounds(281, 138, 96, 20);
		textFields[4].setBounds(281, 163, 96, 20);
		textFields[5].setBounds(443, 63, 96, 20);
		textFields[6].setBounds(443, 88, 96, 20);
		textFields[7].setBounds(443, 113, 96, 20);
		textFields[8].setBounds(443, 138, 96, 20);
		textFields[9].setBounds(443, 163, 96, 20);
		
		tree = new JTree();
		tree.setRootVisible(false);
		tree.setShowsRootHandles(true);
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Commands") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Trip");
						node_1.add(new DefaultMutableTreeNode("Display Trip Schedule", false));
						node_1.add(new DefaultMutableTreeNode("Delete Trip", false));
						node_1.add(new DefaultMutableTreeNode("Add Trip Offerings", false));
						node_1.add(new DefaultMutableTreeNode("Display Trip Stops", false));
						node_1.add(new DefaultMutableTreeNode("Update ActualTripInfo", false));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Bus");
						node_1.add(new DefaultMutableTreeNode("Change Bus", false));
						node_1.add(new DefaultMutableTreeNode("Add Bus", false));
						node_1.add(new DefaultMutableTreeNode("Delete Bus", false));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Driver");
						node_1.add(new DefaultMutableTreeNode("Change Driver", false));
						node_1.add(new DefaultMutableTreeNode("Add Driver", false));
					add(node_1);
				}
			}
		));
		tree.setBounds(10, 11, 205, 540);
		frmLab.getContentPane().add(tree);
		
		executeCommand = new JButton("Execute Command");
		executeCommand.setBounds(549, 63, 325, 117);
		executeCommand.setEnabled(false);
		frmLab.getContentPane().add(executeCommand);
		
		executeCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executeCommand();
			}
		});
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
			   if (tree.getLastSelectedPathComponent() != null) {
				    selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
					if (selectedNode.getAllowsChildren()) {
						executeCommand.setEnabled(false);
					} else {
						executeCommand.setEnabled(true);
					}
				    
				    StringBuilder output = new StringBuilder();
				    output.append("Selected Node: " + selectedNode + "\n\n");	    
				    if (selectedNode.getAllowsChildren() == false) {
				    	String[] params = ProgramDriver.getInstance().getCommandParam(selectedNode.toString());
				    	output.append("Command: " + selectedNode.toString());
				    	output.append("\nNumber of Arguments: " + params.length);
				    	output.append("\n\nArguments:\n");
				    	for(int i = 0; i < params.length; i++) {
				    		output.append("    Arg" + i + " - " + params[i] + "\n");
				    	}
				    	setArgs(params);
				    	commandDisplay.setText("Command: " + selectedNode.toString());
				    } else {
				    	commandDisplay.setText("Click a Command From the Command List! ");
				    	setArgs(new String[0]);
				    	output.append("Type: Command Folder");
				    }
			   		consoleDisplay.setText(output.toString());
			   }
			  }
			});
	}
	
	private void setArgs(String[] args) {
		for(int i = 0; i < labels.length; i++) {
			if (i < args.length) {
				labels[i].setEnabled(true);
				labels[i].setText(args[i]);
				textFields[i].setEnabled(true);
				textFields[i].setText("");
			} else {
				labels[i].setEnabled(false);
				labels[i].setText("Arg"+i);
				textFields[i].setEnabled(false);
				textFields[i].setText("");
			}
		}
	}
	
	private void executeCommand() {
		String[] args = new String[ProgramDriver.getInstance().getCommandParam(selectedNode.toString()).length];
		boolean flag = true;
		
		for(int i = 0; i < args.length; i++) {
			if (textFields[i].getText().equals("")) {
				flag = false;
				break;
			}
		}
		if (flag) {
			for(int i = 0; i < textFields.length; i++) {
				if (i < args.length) {
					args[i] = textFields[i].getText();
				}
				textFields[i].setText("");
			}
			setOutput("\n****Command Executed****\n\n");
			try {
				ResultSet output = ProgramDriver.getInstance().executeCommand(selectedNode.toString(), args);
				if (output != null) {
					setOutput(MySQLDriver.getColumnNames(output) + "\n" + MySQLDriver.getStringResults(output));
				}
			} catch (Exception e) {
				e.printStackTrace();
				setOutput("Exception Hit: " + e.toString() + "\n\nAre you connected to the Database? >_>");
			}
		} else {
			setOutput("Missing Required Arg!");
		}
	}
	
	protected void setOutput(String output) {
		consoleDisplay.setText(output);
		commandDisplay.setText("**Output**");
	}
}
