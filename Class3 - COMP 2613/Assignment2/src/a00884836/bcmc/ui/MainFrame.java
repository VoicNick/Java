/**
 * Project: A00884836Lab9
 * File: MainFrame.java
 * Date: Nov 21, 2016
 * Time: 9:49:40 PM
 */
package a00884836.bcmc.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import a00884836.bcmc.data.AllData;
import a00884836.bcmc.data.Inventory;

// import a00884836.data.Customer;
// import a00884836.exception.ApplicationException;
// import a00884836.io.db.sql.CustomerDao;

import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	// private CustomerDao customerDao;
	private JPanel contentPane;
	private String filterMake;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @param customerDao
	 */
	public MainFrame() {
		setResizable(false);
		setTitle("Lab9 - Voicu Chirtes A00884836");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		// this.customerDao = customerDao;

		buildMenu();
	}

	private void buildMenu() {

		JMenuBar mainMenuBar = new JMenuBar();
		setJMenuBar(mainMenuBar);

		JMenu mnFile = new JMenu("File");

		mnFile.setMnemonic(KeyEvent.VK_F);
		mainMenuBar.add(mnFile);
		JMenuItem quit = new JMenuItem("Quit", KeyEvent.VK_X);
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_MASK));

		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});

		JMenuItem mntmDropDbTables = new JMenuItem("Drop DB tables and Quit");
		mntmDropDbTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String message = "You are about to drop all your tables from the Database and quit the application.\n" + "In order to load the data back up ensure you have \n"
							+ "the data files available locally and re-launch the application.\n" + "Are you sure you want to proceed?";

					int n = JOptionPane.showOptionDialog(MainFrame.this, message, "Drop DB tables and Quit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
							new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);

					if (n == JOptionPane.YES_OPTION) {
						AllData.dropTables();
						dispose();
					}
				} catch (SQLException | IOException e1) {

					JOptionPane.showMessageDialog(MainFrame.this, "Database error", "Database error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mntmDropDbTables.setMnemonic(KeyEvent.VK_D);
		mntmDropDbTables.setToolTipText("Drop all the tables from DB and quit the application");
		mnFile.add(mntmDropDbTables);

		mnFile.add(quit);

		JMenu mnData = new JMenu("Data");
		mnData.setMnemonic(KeyEvent.VK_T);
		mainMenuBar.add(mnData);

		JMenuItem mntmCustomers = new JMenuItem("Customers");
		mntmCustomers.setMnemonic(KeyEvent.VK_C);
		mntmCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(MainFrame.this, "This feature is not available yet", "Feature unavailable", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnData.add(mntmCustomers);

		JMenuItem mntmService = new JMenuItem("Service");
		mntmService.setMnemonic(KeyEvent.VK_S);
		mntmService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(MainFrame.this, "This feature is not available yet", "Feature unavailable", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnData.add(mntmService);

		JMenuItem mntmInventory = new JMenuItem("Inventory");
		mntmInventory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_MASK));
		mntmInventory.setMnemonic(KeyEvent.VK_I);
		mntmInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				InventoryList inventoryList = new InventoryList();
				inventoryList.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				inventoryList.setVisible(true);
			}
		});
		mnData.add(mntmInventory);

		JMenu mnReports = new JMenu("Reports");
		mnReports.setMnemonic(KeyEvent.VK_R);
		mainMenuBar.add(mnReports);

		JMenuItem mntmTotal = new JMenuItem("Total");
		mntmTotal.setMnemonic(KeyEvent.VK_T);
		mntmTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int total = 0;
				try {
					for (Inventory i : AllData.getAllInventoryFromDB()) {
						total += (i.getQuantity() * i.getPrice());
					}

					NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
					String s = n.format(total);
					System.out.println(s);

					JOptionPane.showMessageDialog(MainFrame.this, "The Total Inventory value is:\n" + s, "Total Inventory", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
					JOptionPane.showMessageDialog(MainFrame.this, "DB Error", "DB Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnReports.add(mntmTotal);

		JCheckBox chckbxDescending = new JCheckBox("Descending");
		chckbxDescending.setMnemonic(KeyEvent.VK_S);
		mnReports.add(chckbxDescending);

		JMenuItem mntmByDescription = new JMenuItem("By Description");
		mntmByDescription.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK));
		mntmByDescription.setMnemonic(KeyEvent.VK_D);
		mntmByDescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryReportDialog inventoryReportDialog = new InventoryReportDialog(true, false, chckbxDescending.isSelected(), filterMake);
				inventoryReportDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				inventoryReportDialog.setVisible(true);
			}
		});
		mnReports.add(mntmByDescription);

		JMenuItem mntmByCount = new JMenuItem("By Count");
		mntmByCount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mntmByCount.setMnemonic(KeyEvent.VK_C);
		mntmByCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryReportDialog inventoryReportDialog = new InventoryReportDialog(false, true, chckbxDescending.isSelected(), filterMake);
				inventoryReportDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				inventoryReportDialog.setVisible(true);
			}
		});
		mnReports.add(mntmByCount);

		JMenuItem mntmMake = new JMenuItem("Make");
		mntmMake.setMnemonic(KeyEvent.VK_M);
		mntmMake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String make = JOptionPane.showInputDialog(MainFrame.this, "Enter a valid make to filter the report.\nLeave blank and click OK to diable the filter.", "Make Filter",
						JOptionPane.QUESTION_MESSAGE);
				try {
					if (make.isEmpty()) {
						filterMake = null;
					} else {

						if (AllData.isValidMake(make)) {
							filterMake = make;
						} else {
							JOptionPane.showMessageDialog(MainFrame.this, "Invalid make: " + make + ". Try again. \nLeave blank to diable the filter.", "Invalid make",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				}

			}
		});
		mnReports.add(mntmMake);
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		mainMenuBar.add(helpMenu);

		JMenuItem about = new JMenuItem("About", KeyEvent.VK_A);
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(MainFrame.this, "BCMC - Assignment 2\n By Voicu Chirtes A00884836", "About BCMC - Assignemnt 2", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		helpMenu.add(about);

	}

}
