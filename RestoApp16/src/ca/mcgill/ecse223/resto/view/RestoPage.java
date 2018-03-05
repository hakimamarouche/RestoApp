package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoController;
/*	from jdatepicker.jar library
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
*/
import ca.mcgill.ecse223.resto.model.Table;

public class RestoPage extends JFrame {
	
	//UI elements
	private JLabel errorMessage;
	//restaurant visualization
	private RestoVisualizer restoVisualizer;
	//create table
	private JTextField addTableNumberTextField;
	private JLabel addTableNumberLabel;
	private JTextField addTableXTextField;
	private JLabel addTableXLabel;
	private JTextField addTableYTextField;
	private JLabel addTableYLabel;
	private JTextField addTableWidthTextField;
	private JLabel addTableWidthLabel;
	private JTextField addTableLengthTextField;
	private JLabel addTableLengthLabel;
	private JTextField addTableNumberOfSeatsTextField;
	private JLabel addTableNumberOfSeatsLabel;
	private JButton addTableButton;
	//update or delete table
	private JComboBox<String> selectTableDropdown;
	private JLabel selectTableDropdownLabel;
	private JTextField updateTableXTextField;
	private JLabel updateTableXLabel;
	private JTextField updateTableYTextField;
	private JLabel updateTableYLabel;
	private JTextField updateTableNumberOfSeatsTextField;
	private JLabel updateTableNumberOfSeatsLabel;
	private JButton updateTableButton;
	private JButton deleteTableButton;
	
	//data elements
	
	// restaurant visualization
	private static final int WIDTH_RESTO_VISUALIZATION = 200;
	private static final int HEIGHT_RESTO_VISUALIZATION = 200;
	//error
	private String error = null;
	//Table
	private HashMap<Integer, Table> tables;
	private Integer selectedTable = -1;
	
	// Creates new form RestoPage
	public RestoPage() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		//elements for restaurant visualization
		restoVisualizer = new RestoVisualizer();
		restoVisualizer.setMinimumSize(new Dimension(WIDTH_RESTO_VISUALIZATION, HEIGHT_RESTO_VISUALIZATION));
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		// elements for creating table
		addTableNumberTextField = new JTextField();
		addTableNumberLabel = new JLabel();
		addTableXTextField = new JTextField();
		addTableXLabel = new JLabel();
		addTableYTextField = new JTextField();
		addTableYLabel = new JLabel();
		addTableLengthLabel = new JLabel();
		addTableLengthTextField = new JTextField();
		addTableWidthLabel = new JLabel();
		addTableWidthTextField = new JTextField();
		addTableNumberOfSeatsTextField = new JTextField();
		addTableNumberOfSeatsLabel = new JLabel();
		addTableButton = new JButton();
		//elements for updating or deleting table
		selectTableDropdown = new JComboBox<String>(new String[0]);
		selectTableDropdown.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedTable = cb.getSelectedIndex();
			}
		});
		selectTableDropdownLabel = new JLabel();
		updateTableXTextField = new JTextField();
		updateTableXLabel = new JLabel();
		updateTableYTextField = new JTextField();
		updateTableYLabel = new JLabel();
		updateTableNumberOfSeatsTextField = new JTextField();
		updateTableNumberOfSeatsLabel = new JLabel();
		updateTableButton = new JButton();
		deleteTableButton = new JButton();
		
		//global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Resto App");
		deleteTableButton.setText("Delete");
		deleteTableButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteTableButtonActionPerformed(evt);
			}
		});
		
		//layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(errorMessage)
					.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup()
									.addGroup(layout.createSequentialGroup()
											.addComponent(addTableNumberLabel)
											.addComponent(addTableNumberTextField)
											)
									.addGroup(layout.createSequentialGroup()
											.addComponent(addTableXLabel)
											.addComponent(addTableXTextField)
											.addComponent(addTableYLabel)
											.addComponent(addTableYTextField)
											)
									.addGroup(layout.createSequentialGroup()
											.addComponent(addTableWidthLabel)
											.addComponent(addTableWidthTextField)
											.addComponent(addTableLengthLabel)
											.addComponent(addTableLengthTextField)
											)
									.addGroup(layout.createSequentialGroup()
											.addComponent(addTableNumberOfSeatsLabel)
											.addComponent(addTableNumberOfSeatsTextField)
											)
									.addComponent(addTableButton)
									)
							.addGroup(layout.createParallelGroup()
									.addGroup(layout.createSequentialGroup()
											.addComponent(selectTableDropdownLabel)
											.addComponent(selectTableDropdown)
											)
									.addGroup(layout.createSequentialGroup()
											.addComponent(updateTableXLabel)
											.addComponent(updateTableXTextField)
											)
									.addGroup(layout.createSequentialGroup()
											.addComponent(updateTableYLabel)
											.addComponent(updateTableYTextField)
											)
									.addGroup(layout.createSequentialGroup()
											.addComponent(updateTableNumberOfSeatsLabel)
											.addComponent(updateTableNumberOfSeatsTextField)
											)
									.addGroup(layout.createSequentialGroup()
											.addComponent(updateTableButton)
											.addComponent(deleteTableButton)
											)
									)
							)
				)
				.addComponent(restoVisualizer)
			);
		layout.setVerticalGroup(layout.createParallelGroup()
					.addGroup(layout.createSequentialGroup()
							.addComponent(errorMessage)
							.addGroup(layout.createParallelGroup()
									.addComponent(addTableNumberLabel)
									.addComponent(addTableNumberTextField)
									.addComponent(selectTableDropdownLabel)
									.addComponent(selectTableDropdown)
									)
							.addGroup(layout.createParallelGroup()
									.addComponent(addTableXLabel)
									.addComponent(addTableXTextField)
									.addComponent(addTableYLabel)
									.addComponent(addTableYTextField)
									.addComponent(updateTableXLabel)
									.addComponent(updateTableXTextField)
									)
							.addGroup(layout.createParallelGroup()
									.addComponent(addTableWidthLabel)
									.addComponent(addTableWidthTextField)
									.addComponent(addTableLengthLabel)
									.addComponent(addTableLengthTextField)
									.addComponent(updateTableYLabel)
									.addComponent(updateTableYTextField)
									)
							.addGroup(layout.createParallelGroup()
									.addComponent(addTableNumberOfSeatsLabel)
									.addComponent(addTableNumberOfSeatsTextField)
									.addComponent(updateTableNumberOfSeatsLabel)
									.addComponent(updateTableNumberOfSeatsTextField)
									)
							.addGroup(layout.createParallelGroup()
									.addComponent(addTableButton)
									.addComponent(updateTableButton)
									.addComponent(deleteTableButton)
									)
							)
					.addComponent(restoVisualizer)
				);
		pack();
	}
	
	private void refreshData() {
		//error
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			//empty the text fields
			addTableNumberTextField.setText("");
			addTableXTextField.setText("");
			addTableYTextField.setText("");
			addTableNumberOfSeatsTextField.setText("");
			addTableWidthTextField.setText("");
			addTableLengthTextField.setText("");
			updateTableXTextField.setText("");
			updateTableYTextField.setText("");
			updateTableNumberOfSeatsTextField.setText("");
			//update table Dropdown
			tables = new HashMap<Integer, Table>();
			selectTableDropdown.removeAllItems();
			Integer index = 0;
			for (Table table : RestoController.getTables()) {
				tables.put(index, table);
				selectTableDropdown.addItem("#" + table.getNumber());
				index++;
			}
			selectedTable = -1;
			selectTableDropdown.setSelectedIndex(selectedTable);
		}
		pack();
	}
	
	private void deleteTableButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		if (selectedTable < 0) {
			error = "Table needs to be selected for deletion!";
		}
		if (error.length() == 0) {
			try {
				RestoController.removeTable(tables.get(selectedTable));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		//update visuals
		refreshData();
	}
	
}
