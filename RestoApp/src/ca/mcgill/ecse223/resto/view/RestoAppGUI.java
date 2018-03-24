package ca.mcgill.ecse223.resto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoController;
import ca.mcgill.ecse223.resto.model.Table;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.AbstractListModel;
import javax.swing.JInternalFrame;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JMenuBar;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import lu.tudor.santec.jtimechooser.JTimeChooser;
import lu.tudor.santec.jtimechooser.demo.JTimeChooserDemo;
import lu.tudor.santec.jtimechooser.TimeUnit;
import lu.tudor.santec.jtimechooser.TimeChooserModel;
import com.toedter.components.JSpinField;

public class RestoAppGUI extends JFrame {

	private JLabel errorMessage;
	private JPanel contentPane;
	private JDateChooser reservationDateChooser;
	
	private JSpinner reservationTimeSpinner;
	private JTextField reservationNameTextField;
	private JTextField reservationNumberOfPersonTextField;
	private JTextField reservationPhoneNumberTextField;
	private JTextField reservationEmailTextField;
	private JLabel lblName;
	
	private JTextPane tableNumberTextField;
	private JTextPane tableXPostionTextField;
	private JTextPane tableYPostionTextField;
	private JTextPane tableWidthTextField;
	private JTextPane tableLengthTextField;
	private JTextPane tableNumberOfSeatsTextField;
	private JTextPane java2DRepresentationOfResto;
	private JComboBox selectTableDropdown;
	
	//error
	private String error = null;
	//Table
	private HashMap<Integer, Table> tables;
	private Integer selectedTable = -1;

	/**
	 * Create the frame.
	 */
	public RestoAppGUI()
	{
		setExtendedState(Frame.MAXIMIZED_VERT);
		initComponentGui();
		refreshData();
		
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////
	// this method contains all of the code 
	//for creation and initializing components.
	////////////////////////////////////////////////////////////////////////////////
	private void initComponentGui() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Resto App");
		setBounds(100, 100, 972, 550);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu Categories");
		mnMenu.setHorizontalAlignment(SwingConstants.RIGHT);
		mnMenu.setForeground(new Color(0, 0, 0));
		menuBar.add(mnMenu);
		
		JMenuItem mntmAppetizer = new JMenuItem("Appetizer");
		mnMenu.add(mntmAppetizer);
		
		JMenuItem mntmMain = new JMenuItem("Main");
		mnMenu.add(mntmMain);
		
		JMenuItem mntmDessert = new JMenuItem("Dessert");
		mnMenu.add(mntmDessert);
		
		JMenuItem mntmAlcholo = new JMenuItem("Alcoholic Beverage");
		mnMenu.add(mntmAlcholo);
		
		JMenuItem mntmNoneAlcoholicBeverage = new JMenuItem("None Alcoholic Beverage");
		mnMenu.add(mntmNoneAlcoholicBeverage);
		contentPane = new JPanel();
		contentPane.setToolTipText("dessert\r\nappitizer");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnMoveTable = new JButton("Move table");
		btnMoveTable.setBounds(822, 251, 115, 29);
		btnMoveTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				moveTableButtonActionPerformed(evt);
			}
		});
		contentPane.add(btnMoveTable);
		
		JButton btnUpdateTable = new JButton("Update table");
		btnUpdateTable.setBounds(692, 251, 115, 29);
		btnUpdateTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdateTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateTableButtonActionPerformed(evt);
			}
		});
		contentPane.add(btnUpdateTable);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(562, 251, 115, 29);
		btnDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteTableButtonActionPerformed(evt);
			}
		});
		contentPane.add(btnDelete);
		
		JButton btnAddTable = new JButton("Add table");
		btnAddTable.setBounds(692, 322, 115, 29);
		btnAddTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addTableButtonActionPerformed(evt);
			}
		});
		contentPane.add(btnAddTable);
		
		java2DRepresentationOfResto = new JTextPane();
		java2DRepresentationOfResto.setBounds(0, 0, 541, 288);
		contentPane.add(java2DRepresentationOfResto);
		
		JButton btnReservation = new JButton("Reservation");
		btnAddTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				reserveButtonActionPerformed(evt);
			}
		});
		btnReservation.setBounds(234, 413, 115, 29);
		contentPane.add(btnReservation);
		
		tableNumberTextField = new JTextPane();
		tableNumberTextField.setBounds(764, 8, 75, 26);
		contentPane.add(tableNumberTextField);
		
		tableXPostionTextField = new JTextPane();
		tableXPostionTextField.setBounds(764, 39, 75, 26);
		contentPane.add(tableXPostionTextField);
		
		tableYPostionTextField = new JTextPane();
		tableYPostionTextField.setBounds(764, 69, 75, 26);
		contentPane.add(tableYPostionTextField);
		
		tableWidthTextField = new JTextPane();
		tableWidthTextField.setBounds(764, 99, 75, 26);
		contentPane.add(tableWidthTextField);
		
		tableLengthTextField = new JTextPane();
		tableLengthTextField.setBounds(764, 129, 75, 26);
		contentPane.add(tableLengthTextField);
		
		tableNumberOfSeatsTextField = new JTextPane();
		tableNumberOfSeatsTextField.setBounds(764, 163, 75, 26);
		contentPane.add(tableNumberOfSeatsTextField);
		
		JLabel lblTableNumber = new JLabel("Table Number:");
		lblTableNumber.setBounds(634, 8, 150, 20);
		contentPane.add(lblTableNumber);
		
		JLabel lblXPosition = new JLabel("X position:");
		lblXPosition.setBounds(634, 39, 150, 20);
		contentPane.add(lblXPosition);
		
		JLabel lblYPosition = new JLabel("Y position:");
		lblYPosition.setBounds(634, 75, 100, 20);
		contentPane.add(lblYPosition);
		
		JLabel lblTableWidth = new JLabel("Table width :");
		lblTableWidth.setBounds(634, 105, 160, 20);
		contentPane.add(lblTableWidth);
		
		JLabel lblTableHeight = new JLabel("Table height :");
		lblTableHeight.setBounds(634, 135, 120, 20);
		contentPane.add(lblTableHeight);
		
		JLabel lblNumberOfSeats = new JLabel("Number of seats :");
		lblNumberOfSeats.setBounds(634, 163, 130, 20);
		contentPane.add(lblNumberOfSeats);
		
		
		
		selectTableDropdown = new JComboBox();
		selectTableDropdown.setModel(new DefaultComboBoxModel(new String[] {"select a table", "1", "2", "3", "4", "5", "6", "7"}));
		selectTableDropdown.setToolTipText("");
		selectTableDropdown.setBounds(658, 205, 200, 26);
		selectTableDropdown.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedTable = cb.getSelectedIndex();
			}
		});
		contentPane.add(selectTableDropdown);
		
		lblName = new JLabel("Name :");
		lblName.setBounds(10, 304, 69, 20);
		contentPane.add(lblName);
		
		// for the name
		reservationNameTextField = new JTextField();
		reservationNameTextField.setBounds(64, 301, 146, 26);
		contentPane.add(reservationNameTextField);
		reservationNameTextField.setColumns(10);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(20, 346, 43, 20);
		contentPane.add(lblDate);
		
		JLabel lblNumberOfPerson = new JLabel("Number of person :");
		lblNumberOfPerson.setBounds(238, 304, 150, 20);
		contentPane.add(lblNumberOfPerson);
		
		reservationNumberOfPersonTextField = new JTextField();
		reservationNumberOfPersonTextField.setBounds(403, 301, 75, 26);
		contentPane.add(reservationNumberOfPersonTextField);
		reservationNumberOfPersonTextField.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("phone number :");
		lblPhoneNumber.setBounds(234, 346, 120, 20);
		contentPane.add(lblPhoneNumber);
		
		reservationPhoneNumberTextField = new JTextField();
		reservationPhoneNumberTextField.setBounds(360, 343, 146, 26);
		contentPane.add(reservationPhoneNumberTextField);
		reservationPhoneNumberTextField.setColumns(10);
		
		JLabel lblEmailAdress = new JLabel("email adress :");
		lblEmailAdress.setBounds(234, 385, 107, 20);
		contentPane.add(lblEmailAdress);
		
		reservationEmailTextField = new JTextField();
		reservationEmailTextField.setBounds(331, 382, 238, 26);
		contentPane.add(reservationEmailTextField);
		reservationEmailTextField.setColumns(10);
		
		errorMessage = new JLabel();
		errorMessage.setForeground(new Color(255, 0, 0));
		errorMessage.setBounds(20, 465, 917, 14);
		contentPane.add(errorMessage);
		
		reservationDateChooser = new JDateChooser();
		reservationDateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		reservationDateChooser.setBounds(64, 341, 146, 28);
		contentPane.add(reservationDateChooser);
		
		SpinnerDateModel sm = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
		JSpinner reservationTimeSpinner = new JSpinner(sm);
		JSpinner.DateEditor te = new JSpinner.DateEditor(reservationTimeSpinner, "HH:mm");
		reservationTimeSpinner.setEditor(te);
		reservationTimeSpinner.setBounds(64, 377, 146, 29);
		contentPane.add(reservationTimeSpinner);
		

	}



	
	////////////////////////////////////////////////////////////////////////////////
	// this method contains all of the code for creating events 
	////////////////////////////////////////////////////////////////////////////////
	private void createEvents() {
		// TODO Auto-generated method stub
		
	}
	
	private void refreshData() {
		//error
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			//empty the text fields
			reservationNameTextField.setText("");
			//reservationDateTextField.setText("");
			reservationNumberOfPersonTextField.setText("");
			reservationPhoneNumberTextField.setText("");
			reservationEmailTextField.setText("");
			tableNumberTextField.setText("");
			tableXPostionTextField.setText("");
			tableYPostionTextField.setText("");
			tableWidthTextField.setText("");
			tableLengthTextField.setText("");
			tableNumberOfSeatsTextField.setText("");
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
	}
	
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private void addTableButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		error = null;

		// call the controller
		try {
			RestoController.createTable(Integer.parseInt(tableNumberTextField.getText()),
					Integer.parseInt(tableXPostionTextField.getText()), 
					Integer.parseInt(tableYPostionTextField.getText()), 
					Integer.parseInt(tableWidthTextField.getText()), 
					Integer.parseInt(tableLengthTextField.getText()), 
					Integer.parseInt(tableNumberOfSeatsTextField.getText()));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}


		// update visuals
		refreshData();
	}

	private void updateTableButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// clear error message
		error = null;
		
		// call the controller
		try {
			RestoController.updateTable(tables.get(selectedTable),
					Integer.parseInt(tableNumberTextField.getText()),
					Integer.parseInt(tableNumberOfSeatsTextField.getText()));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// update visuals
		refreshData();
	}
	
	private void moveTableButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = "";
		if (selectedTable < 0) {
			error = "Table needs to be selected to move";
		}
		if (error.length() == 0) {
			try {
				RestoController.moveTable(
						tables.get(selectedTable),
						Integer.parseInt(tableXPostionTextField.getText()), 
						Integer.parseInt(tableYPostionTextField.getText()));
			} 
			catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		refreshData();
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
	
	private void reserveButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = null;
			try {
				Object reservationTime = reservationTimeSpinner.getValue();
//				if (reservationTime instanceof Time) {
//
//				}
				
				List<Table> table = new LinkedList<Table>();
				table.add(tables.get(selectedTable));
				RestoController.reserve(
						new java.sql.Date(reservationDateChooser.getDate().getTime()),
						(Time)reservationTime,
						Integer.parseInt(reservationNumberOfPersonTextField.getText()),
						reservationNameTextField.getText(),
						reservationEmailTextField.getText(),
						reservationPhoneNumberTextField.getText(),
						table);

			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			
		//update visuals
		refreshData();
	}
}
