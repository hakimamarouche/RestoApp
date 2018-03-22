package ca.mcgill.ecse223.resto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JInternalFrame;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.SwingConstants;

public class RestoAppGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtAvdsh;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblName;
	private JTextPane textPane_1;
	private JTextPane textPane_2;
	private JTextPane textPane_3;
	private JTextPane textPane_4;
	private JTextPane textPane_5;
	private JTextPane textPane_6;
	private JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestoAppGUI frame = new RestoAppGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RestoAppGUI()
	{
		initComponentGui();
		createEvents();
		
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////
	// this method contains all of the code 
	//for creation and initializing components.
	////////////////////////////////////////////////////////////////////////////////
	private void initComponentGui() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 974, 549);
		
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
		contentPane.add(btnMoveTable);
		
		JButton btnUpdateTable = new JButton("Update table");
		btnUpdateTable.setBounds(692, 251, 115, 29);
		btnUpdateTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnUpdateTable);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(562, 251, 115, 29);
		contentPane.add(btnDelete);
		
		JButton btnAddTable = new JButton("Add table");
		btnAddTable.setBounds(692, 322, 115, 29);
		btnAddTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnAddTable);
		
		textPane = new JTextPane();
		textPane.setBounds(0, 0, 541, 288);
		contentPane.add(textPane);
		
		JButton btnReservation = new JButton("Reservation");
		btnReservation.setBounds(234, 413, 115, 29);
		contentPane.add(btnReservation);
		
		textPane_1 = new JTextPane();
		textPane_1.setBounds(764, 8, 75, 26);
		contentPane.add(textPane_1);
		
		textPane_2 = new JTextPane();
		textPane_2.setBounds(764, 39, 75, 26);
		contentPane.add(textPane_2);
		
		textPane_3 = new JTextPane();
		textPane_3.setBounds(764, 69, 75, 26);
		contentPane.add(textPane_3);
		
		textPane_4 = new JTextPane();
		textPane_4.setBounds(764, 99, 75, 26);
		contentPane.add(textPane_4);
		
		textPane_5 = new JTextPane();
		textPane_5.setBounds(764, 129, 75, 26);
		contentPane.add(textPane_5);
		
		textPane_6 = new JTextPane();
		textPane_6.setBounds(764, 163, 75, 26);
		contentPane.add(textPane_6);
		
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
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"select a table", "1", "2", "3", "4", "5", "6", "7"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(658, 205, 200, 26);
		contentPane.add(comboBox);
		
		lblName = new JLabel("Name :");
		lblName.setBounds(10, 304, 69, 20);
		contentPane.add(lblName);
		
		// for the name
		txtAvdsh = new JTextField();
		txtAvdsh.setBounds(64, 301, 146, 26);
		contentPane.add(txtAvdsh);
		txtAvdsh.setColumns(10);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(20, 346, 43, 20);
		contentPane.add(lblDate);
		
		textField = new JTextField();
		textField.setBounds(64, 343, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNumberOfPerson = new JLabel("Number of person :");
		lblNumberOfPerson.setBounds(238, 304, 150, 20);
		contentPane.add(lblNumberOfPerson);
		
		textField_1 = new JTextField();
		textField_1.setBounds(403, 301, 75, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("phone number :");
		lblPhoneNumber.setBounds(234, 346, 120, 20);
		contentPane.add(lblPhoneNumber);
		
		textField_2 = new JTextField();
		textField_2.setBounds(360, 343, 146, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblEmailAdress = new JLabel("email adress :");
		lblEmailAdress.setBounds(10, 385, 107, 20);
		contentPane.add(lblEmailAdress);
		
		textField_3 = new JTextField();
		textField_3.setBounds(133, 382, 238, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
	}



	
	////////////////////////////////////////////////////////////////////////////////
	// this method contains all of the code for creating events 
	////////////////////////////////////////////////////////////////////////////////
	private void createEvents() {
		// TODO Auto-generated method stub
		
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
}
