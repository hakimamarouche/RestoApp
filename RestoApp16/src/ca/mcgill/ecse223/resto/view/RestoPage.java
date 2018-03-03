package ca.mcgill.ecse223.resto.view;

import java.util.HashMap;

import javax.swing.*;

import ca.mcgill.ecse223.resto.model.Table;

public class RestoPage {
	
	//UI elements
	private JLabel errorMessage;
	//table
	private JTextField tableNumberTextField;
	private JLabel tableNumberLabel;
	private JLabel tableXLabel;
	private JLabel tableYLabel;
	private JComboBox<String> tableToggleList;
	private JLabel tableToggleLabel;
	private JButton deleteTableButton;
	
	//data elements
	private String error = null;
	//Table
	private HashMap<Integer, Table> tables;
	
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
	
}
