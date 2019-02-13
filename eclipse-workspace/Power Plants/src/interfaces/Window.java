package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window {

	private JFrame frmPowerPlants;
	private JTextField textName;
	private JTextField textAvgProduction;
	private JTextField textMaxProduction;
	private JTextField textStartedDate;
	private JTextField textMaxCapacity;
	private JTextField textOccupation;
	private JTextField textNumTurbines;
	private JTable table;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmPowerPlants.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Window() {
		initialize();
	}
	
	private void initialize() {
		frmPowerPlants = new JFrame();
		frmPowerPlants.setTitle("Power Plants");
		frmPowerPlants.setBounds(0, 0, 785, 676);
		frmPowerPlants.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPowerPlants.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmPowerPlants.getContentPane().add(tabbedPane);
		
		JTabbedPane tabbedPanePlants = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Power plants", null, tabbedPanePlants, null);
		
		JPanel panelHydraulicPlants = new JPanel();
		tabbedPanePlants.addTab("Hydraulic", null, panelHydraulicPlants, null);
		SpringLayout sl_panelHydraulicPlants = new SpringLayout();
		panelHydraulicPlants.setLayout(sl_panelHydraulicPlants);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_panelHydraulicPlants.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, panelHydraulicPlants);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, panelHydraulicPlants);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, panelHydraulicPlants);
		panelHydraulicPlants.add(scrollPane);
		
		JLabel lblName = new JLabel("Name");
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, lblName, 10, SpringLayout.WEST, panelHydraulicPlants);
		panelHydraulicPlants.add(lblName);
		
		textName = new JTextField();
		sl_panelHydraulicPlants.putConstraint(SpringLayout.SOUTH, lblName, -6, SpringLayout.NORTH, textName);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.SOUTH, textName, -41, SpringLayout.SOUTH, panelHydraulicPlants);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, textName, 10, SpringLayout.WEST, panelHydraulicPlants);
		textName.setColumns(10);
		panelHydraulicPlants.add(textName);
		
		JLabel lblAvgProduction = new JLabel("Avg. Production");
		sl_panelHydraulicPlants.putConstraint(SpringLayout.NORTH, lblAvgProduction, 0, SpringLayout.NORTH, lblName);
		panelHydraulicPlants.add(lblAvgProduction);
		
		textAvgProduction = new JTextField();
		sl_panelHydraulicPlants.putConstraint(SpringLayout.NORTH, textAvgProduction, 6, SpringLayout.SOUTH, lblAvgProduction);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, lblAvgProduction, 0, SpringLayout.WEST, textAvgProduction);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, textAvgProduction, 6, SpringLayout.EAST, textName);
		textAvgProduction.setColumns(10);
		panelHydraulicPlants.add(textAvgProduction);
		
		JLabel lblMaxProduction = new JLabel("Max. Production");
		sl_panelHydraulicPlants.putConstraint(SpringLayout.NORTH, lblMaxProduction, 0, SpringLayout.NORTH, lblName);
		panelHydraulicPlants.add(lblMaxProduction);
		
		textMaxProduction = new JTextField();
		sl_panelHydraulicPlants.putConstraint(SpringLayout.NORTH, textMaxProduction, 6, SpringLayout.SOUTH, lblMaxProduction);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, lblMaxProduction, 0, SpringLayout.WEST, textMaxProduction);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, textMaxProduction, 6, SpringLayout.EAST, textAvgProduction);
		textMaxProduction.setColumns(10);
		panelHydraulicPlants.add(textMaxProduction);
		
		JLabel lblStartedDate = new JLabel("Started Working");
		sl_panelHydraulicPlants.putConstraint(SpringLayout.NORTH, lblStartedDate, 0, SpringLayout.NORTH, lblName);
		panelHydraulicPlants.add(lblStartedDate);
		
		textStartedDate = new JTextField();
		sl_panelHydraulicPlants.putConstraint(SpringLayout.NORTH, textStartedDate, 6, SpringLayout.SOUTH, lblStartedDate);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, lblStartedDate, 0, SpringLayout.WEST, textStartedDate);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, textStartedDate, 6, SpringLayout.EAST, textMaxProduction);
		textStartedDate.setColumns(10);
		panelHydraulicPlants.add(textStartedDate);
		
		JLabel lblMaxCapacity = new JLabel("Max. Capacity");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Avg. Production", "Max. Production", "Started Work", "Max. Capacity", "Occupation", "Num. Turbines"
			}
		));
		scrollPane.setViewportView(table);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, lblMaxCapacity, 10, SpringLayout.WEST, panelHydraulicPlants);
		panelHydraulicPlants.add(lblMaxCapacity);
		
		textMaxCapacity = new JTextField();
		sl_panelHydraulicPlants.putConstraint(SpringLayout.SOUTH, textMaxCapacity, -6, SpringLayout.NORTH, lblName);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.SOUTH, lblMaxCapacity, -6, SpringLayout.NORTH, textMaxCapacity);
		textMaxCapacity.setColumns(10);
		panelHydraulicPlants.add(textMaxCapacity);
		
		JLabel lblOccupation = new JLabel("Occupation");
		sl_panelHydraulicPlants.putConstraint(SpringLayout.NORTH, lblOccupation, 0, SpringLayout.NORTH, lblMaxCapacity);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, lblOccupation, 0, SpringLayout.WEST, textAvgProduction);
		panelHydraulicPlants.add(lblOccupation);
		
		textOccupation = new JTextField();
		sl_panelHydraulicPlants.putConstraint(SpringLayout.EAST, textMaxCapacity, -6, SpringLayout.WEST, textOccupation);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, textOccupation, 0, SpringLayout.WEST, textAvgProduction);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.SOUTH, textOccupation, -6, SpringLayout.NORTH, lblAvgProduction);
		textOccupation.setColumns(10);
		panelHydraulicPlants.add(textOccupation);
		
		JLabel lblNumTurbines = new JLabel("Num. Turbines");
		sl_panelHydraulicPlants.putConstraint(SpringLayout.NORTH, lblNumTurbines, 0, SpringLayout.NORTH, lblMaxCapacity);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, lblNumTurbines, 0, SpringLayout.WEST, textMaxProduction);
		panelHydraulicPlants.add(lblNumTurbines);
		
		textNumTurbines = new JTextField();
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, textNumTurbines, 0, SpringLayout.WEST, textMaxProduction);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.SOUTH, textNumTurbines, 0, SpringLayout.SOUTH, textMaxCapacity);
		textNumTurbines.setColumns(10);
		panelHydraulicPlants.add(textNumTurbines);
		
		JButton btnAddHydraulicPlant = new JButton("Add");
		btnAddHydraulicPlant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		sl_panelHydraulicPlants.putConstraint(SpringLayout.NORTH, btnAddHydraulicPlant, 6, SpringLayout.SOUTH, textName);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, btnAddHydraulicPlant, 10, SpringLayout.WEST, panelHydraulicPlants);
		panelHydraulicPlants.add(btnAddHydraulicPlant);
		
		JButton btnEditHydraulicPlant = new JButton("Edit");
		sl_panelHydraulicPlants.putConstraint(SpringLayout.EAST, btnAddHydraulicPlant, -6, SpringLayout.WEST, btnEditHydraulicPlant);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.NORTH, btnEditHydraulicPlant, 6, SpringLayout.SOUTH, textAvgProduction);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, btnEditHydraulicPlant, 0, SpringLayout.WEST, lblAvgProduction);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.EAST, btnEditHydraulicPlant, 0, SpringLayout.EAST, textAvgProduction);
		panelHydraulicPlants.add(btnEditHydraulicPlant);
		
		JButton btnDeleteHydraulicPlant = new JButton("Delete");
		sl_panelHydraulicPlants.putConstraint(SpringLayout.NORTH, btnDeleteHydraulicPlant, 6, SpringLayout.SOUTH, textMaxProduction);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, btnDeleteHydraulicPlant, 0, SpringLayout.WEST, lblMaxProduction);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.EAST, btnDeleteHydraulicPlant, 0, SpringLayout.EAST, textMaxProduction);
		panelHydraulicPlants.add(btnDeleteHydraulicPlant);
		
		JButton btnClearText = new JButton("Clear text");
		sl_panelHydraulicPlants.putConstraint(SpringLayout.SOUTH, btnClearText, -10, SpringLayout.SOUTH, panelHydraulicPlants);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.EAST, btnClearText, -10, SpringLayout.EAST, panelHydraulicPlants);
		panelHydraulicPlants.add(btnClearText);
		
		JLabel lblMessages = new JLabel("Error");
		lblMessages.setForeground(Color.RED);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.WEST, lblMessages, 10, SpringLayout.WEST, panelHydraulicPlants);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.SOUTH, scrollPane, -6, SpringLayout.NORTH, lblMessages);
		sl_panelHydraulicPlants.putConstraint(SpringLayout.SOUTH, lblMessages, -6, SpringLayout.NORTH, lblMaxCapacity);
		panelHydraulicPlants.add(lblMessages);
		
		JPanel panelSolarPlants = new JPanel();
		tabbedPanePlants.addTab("Solar", null, panelSolarPlants, null);
		
		JPanel panelNuclearPlants = new JPanel();
		tabbedPanePlants.addTab("Nuclear", null, panelNuclearPlants, null);
		
		JPanel panelThermalPlants = new JPanel();
		tabbedPanePlants.addTab("Thermal", null, panelThermalPlants, null);
		
		JPanel panelDistributors = new JPanel();
		tabbedPane.addTab("Distributors", null, panelDistributors, null);
		
		JPanel panelServiceZones = new JPanel();
		tabbedPane.addTab("Service zones", null, panelServiceZones, null);
		
		JPanel panelDeliveries = new JPanel();
		tabbedPane.addTab("Deliveries", null, panelDeliveries, null);
		
		JPanel panelSearch = new JPanel();
		tabbedPane.addTab("Search", null, panelSearch, null);
	}
}
