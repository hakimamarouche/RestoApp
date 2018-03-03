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
	private JLabel tableNumberLabel;
	private JTextField tableXTextField;
	private JLabel tableXLabel;
	private JTextField tableYTextField;
	private JLabel tableYLabel;
	private JComboBox<String> tableToggleList;
	private JLabel tableToggleLabel;
	private JButton deleteTableButton;
	
	//data elements
	private String error = null;
	//Table
	private HashMap<Integer, Table> tables;
	
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
		tableNumberLabel = new JLabel();
		tableXTextField = new JTextField();
		tableXLabel = new JLabel();
		tableYTextField = new JTextField();
		tableYLabel = new JLabel();
		
	}
	
	private void refreshData() {
		// TODO Auto-generated method stub
		
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
}
