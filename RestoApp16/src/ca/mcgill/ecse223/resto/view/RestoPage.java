package ca.mcgill.ecse223.resto.view;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.*;

import ca.mcgill.ecse223.resto.model.Table;

public class RestoPage {
	
	//UI elements
	private JLabel errorMessage;
	//table
	private JTextField tableNumberTextField;
	private JLabel addTableNumberLabel;
	private JTextField addTableXTextField;
	private JLabel addTtableXLabel;
	private JTextField addTableYTextField;
	private JLabel addTableYLabel;
	private JTextField addTableNumberOfSeatsTextField;
	private JLabel addTableNumberOfSeatsLabel;
	private JButton addTableButton;
	private JLabel addTableLabel;
	private JComboBox<String> selectTableDropdown;
	private JLabel selectTableDropdownLabel;
	private JTextField updateTableXTextField;
	private JLabel updateTableXLabel;
	private JTextField updateTableYTextField;
	private JLabel updateTableYLabel;
	private JTextField updateTableNumberOfSeatsTextField;
	private JTextField updateTableNumberOfSeatsLabel;
	private JButton deleteTableButton;
	private JLabel deleteTableLabel;
	
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
		// elements for table
		tableNumberTextField = new JTextField();
		addTableNumberLabel = new JLabel();
		addTableXTextField = new JTextField();
		addTtableXLabel = new JLabel();
		addTableYTextField = new JTextField();
		addTableYLabel = new JLabel();
		selectTableDropdown = new JComboBox<String>(new String[0]);
		selectTableDropdown.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedTable = cb.getSelectedIndex();
			}
		});
		
	}
	
	private void refreshData() {
		// TODO Auto-generated method stub
		
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
}
