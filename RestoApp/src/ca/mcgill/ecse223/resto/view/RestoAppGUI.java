package ca.mcgill.ecse223.resto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.resto.application.RestoApplication;
import ca.mcgill.ecse223.resto.controller.InvalidInputException;
import ca.mcgill.ecse223.resto.controller.RestoController;
import ca.mcgill.ecse223.resto.model.Reservation;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;
import ca.mcgill.ecse223.resto.model.Event;
import ca.mcgill.ecse223.resto.model.MenuItem;
import ca.mcgill.ecse223.resto.model.MenuItem.ItemCategory;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class RestoAppGUI extends JFrame {

	private JLabel errorMessage;
	private JPanel contentPane;
	private JDateChooser reservationDateChooser;
	//Reservations
	private JSpinner reservationTimeSpinner;
	private JTextField reservationNameTextField;
	private JTextField reservationNumberOfPersonTextField;
	private JTextField reservationPhoneNumberTextField;
	private JTextField reservationEmailTextField;
	private JLabel lblName;
	//Adding or editing tables
	private JTextPane tableNumberTextField;
	private JTextPane tableXPostionTextField;
	private JTextPane tableYPostionTextField;
	private JTextPane tableWidthTextField;
	private JTextPane tableLengthTextField;
	private JTextPane tableNumberOfSeatsTextField;
	private JComboBox selectTableDropdown;
	//Events
	private JDateChooser eventStartDateChooser;
	private JDateChooser eventEndDateChooser;
	private JTextField eventDescription;
	private JTextField eventName;
	private JTable eventTable;
	//menu Items
	private JComboBox<ItemCategory> selectMenuCategoryDropDown;
	//seats for issue bill
	protected JComboBox selectSeatDropdown;
	private Integer selectedSeatIndex;
	protected Seat selectedSeatObject;
	
	//error
	private String error = null;
	//Data
	private HashMap<Integer, Reservation> reservations;
	private HashMap<Integer, Table> tables;
	private HashMap<Integer, MenuItem> nonAlcoholicBeverages;
	private HashMap<Integer, MenuItem> alcoholicBeverages;
	private HashMap<Integer, MenuItem> desserts;
	private HashMap<Integer, MenuItem> mainDishes;
	private HashMap<Integer, MenuItem> appetizers;
	private HashMap<Integer, Event> events;
	protected HashMap<Integer, Seat> seats;
	//Table
	private Integer selectedTable = -1;
	private JTextField tablesToReserve;
	//Menu category
	private Integer selectedCategory = -1;
	//Table Visualization
	TableVisualizer tableVisualization;
	private JTable appetizerTable;
	private JTable mainTable;
	private JTable dessertTable;
	private JTable alcoholicBeverageTable;
	private JTable nonAlcoholicBeverageTable;
	private JTextField txtMenuItemName;
	private JTextField txtMenuItemPrice;
	protected Object menuItemSelected;
	private JTable tableSeat;


	

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
		setBounds(100, 100, 1220, 810);

		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnMoveTable = new JButton("Move table");
		btnMoveTable.setBounds(692, 329, 115, 29);
		btnMoveTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				moveTableButtonActionPerformed(evt);
			}
		});
		contentPane.add(btnMoveTable);
		
		JButton btnUpdateTable = new JButton("Update table");
		btnUpdateTable.setBounds(692, 289, 115, 29);
		btnUpdateTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdateTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateTableButtonActionPerformed(evt);
			}
		});
		contentPane.add(btnUpdateTable);
		
		JButton btnDeleteTable = new JButton("Delete table");
		btnDeleteTable.setBounds(562, 291, 115, 29);
		btnDeleteTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteTableButtonActionPerformed(evt);
			}
		});
		contentPane.add(btnDeleteTable);
		
		JButton btnAddTable = new JButton("Add table");
		btnAddTable.setBounds(562, 329, 115, 29);
		btnAddTable.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addTableButtonActionPerformed(evt);
			}
		});
		contentPane.add(btnAddTable);
		
		JButton btnReservation = new JButton("Reservation");
		btnReservation.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				reserveButtonActionPerformed(evt);
			}
		});
		btnReservation.setBounds(234, 439, 115, 29);
		contentPane.add(btnReservation);
		
		tableNumberTextField = new JTextPane();
		tableNumberTextField.setBounds(657, 59, 65, 20);
		contentPane.add(tableNumberTextField);
		
		tableXPostionTextField = new JTextPane();
		tableXPostionTextField.setBounds(657, 83, 65, 20);
		contentPane.add(tableXPostionTextField);
		
		tableYPostionTextField = new JTextPane();
		tableYPostionTextField.setBounds(657, 108, 65, 20);
		contentPane.add(tableYPostionTextField);
		
		tableWidthTextField = new JTextPane();
		tableWidthTextField.setBounds(657, 133, 65, 20);
		contentPane.add(tableWidthTextField);
		
		tableLengthTextField = new JTextPane();
		tableLengthTextField.setBounds(657, 158, 65, 20);
		contentPane.add(tableLengthTextField);
		
		tableNumberOfSeatsTextField = new JTextPane();
		tableNumberOfSeatsTextField.setBounds(657, 183, 65, 20);
		contentPane.add(tableNumberOfSeatsTextField);
		
		JLabel lblTableNumber = new JLabel("Table Number:");
		lblTableNumber.setBounds(562, 59, 91, 20);
		contentPane.add(lblTableNumber);
		
		JLabel lblXPosition = new JLabel("X position:");
		lblXPosition.setBounds(587, 83, 59, 20);
		contentPane.add(lblXPosition);
		
		JLabel lblYPosition = new JLabel("Y position:");
		lblYPosition.setBounds(587, 108, 59, 20);
		contentPane.add(lblYPosition);
		
		JLabel lblTableWidth = new JLabel("Table width :");
		lblTableWidth.setBounds(575, 133, 71, 20);
		contentPane.add(lblTableWidth);
		
		JLabel lblTableHeight = new JLabel("Table height :");
		lblTableHeight.setBounds(571, 158, 75, 20);
		contentPane.add(lblTableHeight);
		
		JLabel lblNumberOfSeats = new JLabel("Number of seats :");
		lblNumberOfSeats.setBounds(545, 183, 108, 20);
		contentPane.add(lblNumberOfSeats);
		
		
		
		selectTableDropdown = new JComboBox();
		selectTableDropdown.setBounds(657, 227, 65, 20);
		selectTableDropdown.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedTable = cb.getSelectedIndex();
				
				//update seat Dropdown
				if (selectedTable >= 0) {
					seats = new HashMap<Integer, Seat>();
					selectSeatDropdown.removeAllItems();
					Integer index = 0;
					for (Seat seat : tables.get(selectedTable).getSeats()) {
						seats.put(index, seat);
						selectSeatDropdown.addItem("seat " + (index+1));
						index++;
					}
					selectedSeatIndex = -1;
					selectSeatDropdown.setSelectedIndex(selectedSeatIndex);
				}
			}
		});
		contentPane.add(selectTableDropdown);
		
		lblName = new JLabel("Name :");
		lblName.setBounds(10, 338, 69, 20);
		contentPane.add(lblName);
		
		// for the name
		reservationNameTextField = new JTextField();
		reservationNameTextField.setBounds(80, 339, 130, 23);
		contentPane.add(reservationNameTextField);
		reservationNameTextField.setColumns(10);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(10, 381, 43, 20);
		contentPane.add(lblDate);
		
		JLabel lblNumberOfPerson = new JLabel("Number of person :");
		lblNumberOfPerson.setBounds(234, 344, 151, 20);
		contentPane.add(lblNumberOfPerson);
		
		reservationNumberOfPersonTextField = new JTextField();
		reservationNumberOfPersonTextField.setBounds(395, 338, 146, 26);
		contentPane.add(reservationNumberOfPersonTextField);
		reservationNumberOfPersonTextField.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("phone number :");
		lblPhoneNumber.setBounds(234, 376, 151, 20);
		contentPane.add(lblPhoneNumber);
		
		reservationPhoneNumberTextField = new JTextField();
		reservationPhoneNumberTextField.setBounds(395, 373, 146, 26);
		contentPane.add(reservationPhoneNumberTextField);
		reservationPhoneNumberTextField.setColumns(10);
		
		JLabel lblEmailAdress = new JLabel("email adress :");
		lblEmailAdress.setBounds(234, 408, 151, 20);
		contentPane.add(lblEmailAdress);
		
		reservationEmailTextField = new JTextField();
		reservationEmailTextField.setBounds(395, 405, 146, 26);
		contentPane.add(reservationEmailTextField);
		reservationEmailTextField.setColumns(10);
		
		errorMessage = new JLabel();
		errorMessage.setForeground(new Color(255, 0, 0));
		errorMessage.setBounds(10, 0, 936, 20);
		contentPane.add(errorMessage);
		
		reservationDateChooser = new JDateChooser();
		reservationDateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		reservationDateChooser.setBounds(80, 373, 130, 28);
		contentPane.add(reservationDateChooser);
		
		JLabel lblTime = new JLabel("Time :");
		lblTime.setBounds(7, 412, 46, 14);
		contentPane.add(lblTime);
		
		reservationTimeSpinner = new JSpinner();
		reservationTimeSpinner.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
		reservationTimeSpinner.setEditor(new JSpinner.DateEditor(reservationTimeSpinner,"HH:mm"));
		reservationTimeSpinner.setBounds(80, 406, 130, 26);
		contentPane.add(reservationTimeSpinner);
		
		JLabel lblTables = new JLabel("Tables :");
		lblTables.setBounds(10, 446, 46, 14);
		contentPane.add(lblTables);
		
		tablesToReserve = new JTextField();
		tablesToReserve.setToolTipText("Table numbers, seperated by commas");
		tablesToReserve.setText("1,2,3,4");
		tablesToReserve.setBounds(80, 441, 130, 24);
		contentPane.add(tablesToReserve);
		tablesToReserve.setColumns(10);
		
		JLabel lblEventName = new JLabel("Event Name:");
		lblEventName.setBounds(10, 496, 83, 14);
		contentPane.add(lblEventName);
		
		eventName = new JTextField();
		eventName.setBounds(94, 490, 130, 26);
		contentPane.add(eventName);
		eventName.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setBounds(10, 533, 83, 14);
		contentPane.add(lblDescription);
		
		JLabel lblStartDate = new JLabel("Start Date :");
		lblStartDate.setBounds(234, 490, 150, 14);
		contentPane.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date :");
		lblEndDate.setBounds(234, 533, 93, 14);
		contentPane.add(lblEndDate);
		
		eventStartDateChooser = new JDateChooser();
		eventStartDateChooser.setBounds(308, 490, 115, 26);
		contentPane.add(eventStartDateChooser);
		
		eventEndDateChooser = new JDateChooser();
		eventEndDateChooser.setBounds(308, 528, 115, 26);
		contentPane.add(eventEndDateChooser);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addEventActionPerformed(evt);
			}
		});
		btnAddEvent.setBounds(433, 490, 108, 26);
		contentPane.add(btnAddEvent);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 565, 541, 106);
		contentPane.add(scrollPane);
		
		eventTable = new JTable();
		scrollPane.setViewportView(eventTable);
		eventTable.setToolTipText("Events");
		eventTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Description", "Start Date", "End Date"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		tableVisualization = new TableVisualizer();
		tableVisualization.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableVisualization.setBounds(562, 369, 384, 302);
		contentPane.add(tableVisualization);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 35, 531, 273);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("Main", null, scrollPane_2, null);
		
		mainTable = new JTable();
		mainTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuItemSelected = mainDishes.get(mainTable.getSelectedRow());
			}
		});
		mainTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_2.setViewportView(mainTable);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane.addTab("Dessert", null, scrollPane_3, null);
		
		dessertTable = new JTable();
		dessertTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuItemSelected = desserts.get(dessertTable.getSelectedRow());
			}
		});
		dessertTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_3.setViewportView(dessertTable);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		tabbedPane.addTab("Alcoholic Beverage", null, scrollPane_4, null);
		
		alcoholicBeverageTable = new JTable();
		alcoholicBeverageTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuItemSelected = alcoholicBeverages.get(alcoholicBeverageTable.getSelectedRow());
			}
		});
		alcoholicBeverageTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_4.setViewportView(alcoholicBeverageTable);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Appetizer", null, scrollPane_1, null);
		
		appetizerTable = new JTable();
		appetizerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuItemSelected = appetizers.get(appetizerTable.getSelectedRow());
			}
		});
		appetizerTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		scrollPane_1.setViewportView(appetizerTable);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		tabbedPane.addTab("Non-Alcoholic Beverage", null, scrollPane_5, null);
		
		nonAlcoholicBeverageTable = new JTable();
		nonAlcoholicBeverageTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuItemSelected = nonAlcoholicBeverages.get(nonAlcoholicBeverageTable.getSelectedRow());
			}
		});
		nonAlcoholicBeverageTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Price"
			}
		) {

			Class[] columnTypes = new Class[] {
				String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_5.setViewportView(nonAlcoholicBeverageTable);
		
		JButton btnDeleteEvent = new JButton("Delete Event");
		btnDeleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteEventActionPerformed(arg0);
			}
		});
		btnDeleteEvent.setBounds(433, 529, 108, 25);
		contentPane.add(btnDeleteEvent);
		
		eventDescription = new JTextField();
		eventDescription.setColumns(10);
		eventDescription.setBounds(94, 527, 130, 26);
		contentPane.add(eventDescription);
		
		JButton btnAddMenuItem = new JButton("Add menu item");
		btnAddMenuItem.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAddMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
					addMenuItemButtonActionPerformed(evt);
			}
		});
		
		btnAddMenuItem.setBounds(822, 289, 115, 29);
		contentPane.add(btnAddMenuItem);
		
		txtMenuItemName = new JTextField();
		txtMenuItemName.setToolTipText("");
		txtMenuItemName.setBounds(857, 59, 86, 20);
		contentPane.add(txtMenuItemName);
		txtMenuItemName.setColumns(10);
		
		JLabel lblMenuItemName = new JLabel("Menu Item name:");
		lblMenuItemName.setBounds(756, 59, 99, 14);
		contentPane.add(lblMenuItemName);
		
		JLabel lblMenuItemPrice = new JLabel("Menu Item Price:");
		lblMenuItemPrice.setBounds(756, 85, 99, 14);
		contentPane.add(lblMenuItemPrice);
		
		txtMenuItemPrice = new JTextField();
		txtMenuItemPrice.setBounds(857, 83, 86, 20);
		contentPane.add(txtMenuItemPrice);
		txtMenuItemPrice.setColumns(10);
		
		selectMenuCategoryDropDown = new JComboBox();
		selectMenuCategoryDropDown.setBounds(857, 108, 86, 20);
		contentPane.add(selectMenuCategoryDropDown);
		
		JLabel lblSelectATable = new JLabel("Select a table:");
		lblSelectATable.setBounds(562, 239, 91, 26);
		contentPane.add(lblSelectATable);
		
		JLabel lblMenuCategory = new JLabel("Menu Category:");
		lblMenuCategory.setBounds(756, 108, 99, 20);
		contentPane.add(lblMenuCategory);
		
		JButton btnDeleteMenuItem = new JButton("Delete Menu Item");
		btnDeleteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteMenuItemButtonActionPerformed(arg0);
			}
		});
		btnDeleteMenuItem.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnDeleteMenuItem.setBounds(822, 329, 115, 29);
		contentPane.add(btnDeleteMenuItem);
		
		JLabel lblSelectSeat = new JLabel("Select a seat:");
		lblSelectSeat.setBounds(572, 266, 81, 14);
		contentPane.add(lblSelectSeat);
		
		selectSeatDropdown = new JComboBox();
		selectSeatDropdown.setBounds(657, 258, 65, 20);
		contentPane.add(selectSeatDropdown);
		
		
		
		JScrollPane scrollPaneSeat = new JScrollPane();
		scrollPaneSeat.setBounds(786, 197, 151, 81);
		contentPane.add(scrollPaneSeat);
		
		tableSeat = new JTable();
		tableSeat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedSeatObject = seats.get(tableSeat.getSelectedRow());
			}
		});
		tableSeat.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Table", "Seat"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Double.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		scrollPaneSeat.setViewportView(tableSeat);
		
		JButton btnAddSeatToTable = new JButton("+");
		btnAddSeatToTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnAddSeatToTable.setBounds(732, 245, 44, 14);
		contentPane.add(btnAddSeatToTable);
		
		JButton btnRemoveSeatFromTable = new JButton("-");
		btnRemoveSeatFromTable.setBounds(733, 264, 43, 14);
		contentPane.add(btnRemoveSeatFromTable);
		
		JButton btnDisplayOrder = new JButton("Display Order");
		btnDisplayOrder.setBounds(952, 289, 130, 29);
		contentPane.add(btnDisplayOrder);
		
		JButton btnDeleteOrderItem = new JButton("delete order Item");
		btnDeleteOrderItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDeleteOrderItem.setBounds(952, 338, 155, 45);
		contentPane.add(btnDeleteOrderItem);
		
		JButton btnDeleteTableOrder = new JButton("Delete table order");
		btnDeleteTableOrder.setBounds(953, 397, 164, 29);
		contentPane.add(btnDeleteTableOrder);
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
			txtMenuItemName.setText("");
			txtMenuItemPrice.setText("");
			//update table Dropdown
			tables = new HashMap<Integer, Table>();
			selectTableDropdown.removeAllItems();
			Integer index = 0;
			for (Table table : RestoController.getTables()) {
				tables.put(index, table);
				selectTableDropdown.addItem("Table " + table.getNumber());
				index++;
			}
			selectedTable = -1;
			selectTableDropdown.setSelectedIndex(selectedTable);
			
			//update MenuItemCategoryDropDown
			selectMenuCategoryDropDown.removeAllItems();
			index = 0;
			for (ItemCategory itemCategory : ItemCategory.values())  {
				selectMenuCategoryDropDown.addItem(itemCategory);
				index++;
			}
			selectedCategory = -1;
			selectMenuCategoryDropDown.setSelectedIndex(selectedCategory);
			
			//update reservations:
			index = 0;
			reservations = new HashMap<Integer, Reservation>();
			for(Reservation reservation : RestoApplication.getRestoApp().getReservations()) {
				reservations.put(index, reservation);
				//System.out.println("Adding Reservation for date: "+reservation.getDate()+". time: "+reservation.getTime());
				index++;
			}
			index = 0;
			//update Tables:
			tableVisualization.addTables(tables);
			//Menu Display:			
			ArrayList<MenuItem> AppetizerItems = (ArrayList<MenuItem>) RestoController.getMenuItems(MenuItem.ItemCategory.Appetizer);
			ArrayList<MenuItem> mainItems = (ArrayList<MenuItem>) RestoController.getMenuItems(MenuItem.ItemCategory.Main);
			ArrayList<MenuItem> dessertItems = (ArrayList<MenuItem>) RestoController.getMenuItems(MenuItem.ItemCategory.Dessert);
			ArrayList<MenuItem> alcoholicItems = (ArrayList<MenuItem>) RestoController.getMenuItems(MenuItem.ItemCategory.AlcoholicBeverage);
			ArrayList<MenuItem> nonAlcoholicItems = (ArrayList<MenuItem>) RestoController.getMenuItems(MenuItem.ItemCategory.NonAlcoholicBeverage);
			//Clear Menu JTables:
			((DefaultTableModel)appetizerTable.getModel()).setRowCount(0);
			((DefaultTableModel)mainTable.getModel()).setRowCount(0);
			((DefaultTableModel)dessertTable.getModel()).setRowCount(0);
			((DefaultTableModel)alcoholicBeverageTable.getModel()).setRowCount(0);
			((DefaultTableModel)nonAlcoholicBeverageTable.getModel()).setRowCount(0);
			//populate Menu JTables:
			populateAppetizerTable(AppetizerItems);
			populateMainTable(mainItems);
			populateDessertTable(dessertItems);
			populateAlcoholicBeverageTable(alcoholicItems);
			populateNonAlcoholicBeverageTable(nonAlcoholicItems);
			//Events Display:
			List<Event> restoEvents = RestoController.getEvents();
			//Clear Event JTable:
			((DefaultTableModel)eventTable.getModel()).setRowCount(0);
			//populate Event JTable:
			populateEventTable(restoEvents);
		}
	}
	
	
	private void populateEventTable(List<Event> restoEvents) {
		if(restoEvents != null) {
			SimpleDateFormat df = new SimpleDateFormat("E, MMM-d-YYY");
			DefaultTableModel model = (DefaultTableModel) eventTable.getModel();
			events = new HashMap<Integer, Event>();
			int index = 0;
			for(Event event : restoEvents) {
				if(event.getEndDate().getTime() < new Date().getTime()) {
					try {
						RestoController.removeEvent(event);
					} catch (InvalidInputException e) {
						System.out.println("Could not remove event.");
					}
					continue;
				}
				Object[] newData = {event.getNameOfEvent(), event.getDescription(), df.format(event.getStartDate()), df.format(event.getEndDate())};
				model.addRow(newData);
				events.put(index, event);
				index++;
			}
		}
	}



	private void populateNonAlcoholicBeverageTable(ArrayList<MenuItem> nonAlcoholicItems) {
		DefaultTableModel model = (DefaultTableModel) nonAlcoholicBeverageTable.getModel();
		nonAlcoholicBeverages = new HashMap<Integer, MenuItem>();
		int index = 0;
		for(MenuItem item : nonAlcoholicItems) {
			Object[] newData = {item.getName(), item.getCurrentPricedMenuItem().getPrice()};
			model.addRow(newData);
			nonAlcoholicBeverages.put(index, item);
			index++;
		}
		
	}



	private void populateAlcoholicBeverageTable(ArrayList<MenuItem> alcoholicItems) {
		DefaultTableModel model = (DefaultTableModel) alcoholicBeverageTable.getModel();
		alcoholicBeverages = new HashMap<Integer, MenuItem>();
		int index = 0;
		for(MenuItem item : alcoholicItems) {
			Object[] newData = {item.getName(), item.getCurrentPricedMenuItem().getPrice()};
			model.addRow(newData);
			alcoholicBeverages.put(index, item);
			index++;
		}
	}



	private void populateDessertTable(ArrayList<MenuItem> dessertItems) {
		DefaultTableModel model = (DefaultTableModel) dessertTable.getModel();
		desserts = new HashMap<Integer, MenuItem>();
		int index = 0;
		for(MenuItem item : dessertItems) {
			Object[] newData = {item.getName(), item.getCurrentPricedMenuItem().getPrice()};
			model.addRow(newData);
			desserts.put(index, item);
			index++;
		}
	}



	private void populateMainTable(ArrayList<MenuItem> mainItems) {
		DefaultTableModel model = (DefaultTableModel) mainTable.getModel();
		mainDishes = new HashMap<Integer, MenuItem>();
		int index = 0;
		for(MenuItem item : mainItems) {
			Object[] newData = {item.getName(), item.getCurrentPricedMenuItem().getPrice()};
			model.addRow(newData);
			mainDishes.put(index, item);
			index++;
		}		
	}



	private void populateAppetizerTable(ArrayList<MenuItem> appetizerItems) {
			DefaultTableModel model = (DefaultTableModel) appetizerTable.getModel();
			appetizers = new HashMap<Integer, MenuItem>();
			int index = 0;
			for(MenuItem item : appetizerItems) {
				Object[] newData = {item.getName(), item.getCurrentPricedMenuItem().getPrice()};
				model.addRow(newData);
				appetizers.put(index, item);
				index++;
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
		error = "";
		
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
			try {
				Object reservationTime = reservationTimeSpinner.getValue();
				if (reservationTime instanceof Date) {
					//List<Table> table = new LinkedList<Table>();
					//table.add(tables.get(selectedTable));//expand this later
					//to format date and time:
					Date timeDate = (Date) reservationTime;
					Date dateDate = reservationDateChooser.getDate();
					Calendar timeCal = Calendar.getInstance();
					Calendar dateCal = Calendar.getInstance();
					Calendar mergeCal = dateCal;
					timeCal.setTime(timeDate);
					dateCal.setTime(dateDate);
					mergeCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
					mergeCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
					mergeCal.set(Calendar.SECOND, 0);
					//to get tables to reserve:
					
					List<String> tablesToReserveList = Arrays.asList(tablesToReserve.getText().split("\\s*,\\s*"));
					
					
					RestoController.reserve(
						tablesToReserveList,
						new java.sql.Date(mergeCal.getTime().getTime()),
						new java.sql.Time(mergeCal.getTime().getTime()),
						Integer.parseInt(reservationNumberOfPersonTextField.getText()),
						reservationNameTextField.getText(),
						reservationEmailTextField.getText(),
						reservationPhoneNumberTextField.getText());
				}
				else {
					error = "the time should have the following format, HH:mm";
				}

			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			
		//update visuals
		refreshData();
	}
	
	private void addEventActionPerformed(ActionEvent evt) {
		try {
			RestoController.createEvent(eventName.getText(), 
										eventDescription.getText(), 
										new java.sql.Date(eventStartDateChooser.getDate().getTime()), 
										new java.sql.Date(eventEndDateChooser.getDate().getTime()));
			refreshData();
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		//System.out.println(df.format(eventStartDateChooser.getDate()));
	}
	
	private void deleteEventActionPerformed(ActionEvent evt) {
		int selectedEventRow = eventTable.getSelectedRow();
		//System.out.println(selectedEventRow);
		if (selectedEventRow != -1) {
			Event selectedEvent = events.get(selectedEventRow);
			try {
				RestoController.removeEvent(selectedEvent);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		
		refreshData();
	}

	private void addMenuItemButtonActionPerformed(ActionEvent evt) {
		// clear error message
		error = "";

		double price = 0;
		try {
			price = Double.parseDouble(txtMenuItemPrice.getText());
		}
		catch (NumberFormatException e) {
			error = "The price needs to be a numerical value!";
		}
		error.trim();

		// call the controller
		if (error.length() == 0) {
			try {

				RestoController.addMenuItem(
						txtMenuItemName.getText(), 
						(ItemCategory)selectMenuCategoryDropDown.getSelectedItem(), 
						price);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}

		// update visuals
		refreshData();
	}
	
	private void deleteMenuItemButtonActionPerformed(ActionEvent evt) {
		// clear error message
		error = "";
		
		// call the controller
			try {

				RestoController.removeMenuItem((MenuItem)menuItemSelected);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		
		// update visuals
		refreshData();
	}
	
    protected void initDetail(int selectedRow) {
    	menuItemSelected = appetizers.get(selectedRow);
    }
}
