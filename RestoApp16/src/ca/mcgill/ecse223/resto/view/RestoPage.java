package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.*;

import ca.mcgill.ecse223.resto.model.Table;

public class RestoPage {
	
	//UI elements
	private JLabel errorMessage;
	//create table
	private JTextField addTableNumberTextField;
	private JLabel addTableNumberLabel;
	private JTextField addTableXTextField;
	private JLabel addTtableXLabel;
	private JTextField addTableYTextField;
	private JLabel addTableYLabel;
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
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		// elements for creating table
		addTableNumberTextField = new JTextField();
		addTableNumberLabel = new JLabel();
		addTableXTextField = new JTextField();
		addTtableXLabel = new JLabel();
		addTableYTextField = new JTextField();
		addTableYLabel = new JLabel();
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
		
	}
	
	private void refreshData() {
		// TODO Auto-generated method stub
		
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
}
