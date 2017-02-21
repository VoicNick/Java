/**
* Project: A00884836Lab9
* File: MainFrame.java
* Date: Nov 21, 2016
* Time: 9:49:40 PM
*/
package a00884836.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import a00884836.data.Customer;
import a00884836.exception.ApplicationException;
import a00884836.io.db.sql.CustomerDao;

import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

/**
* @author Voicu Chirtes, A00884836
*
*/
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private CustomerDao customerDao;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 * @param customerDao 
	 */
	public MainFrame(CustomerDao customerDao) {
		setResizable(false);
		setTitle("Lab9 - Voicu Chirtes A00884836");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.customerDao = customerDao;

		buildMenu();
	}
	
	
	private void buildMenu() {
		
		JMenuBar mainMenuBar = new JMenuBar();
		setJMenuBar(mainMenuBar);

		JMenu fileMenu = new JMenu("File");
		
		fileMenu.setMnemonic(KeyEvent.VK_F);
		mainMenuBar.add(fileMenu);
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		mainMenuBar.add(helpMenu);

		JMenuItem customer = new JMenuItem("Customer",KeyEvent.VK_C);
		customer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		fileMenu.add(customer);
		

//
//		int count = 0;
//		try {
//			count = customerDao.getCustomerCount();
//			
//		} catch (SQLException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		
		customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {

					Customer theCustomer;

					int r = (int)(Math.random() * customerDao.getCustomerCount()) + 1;
					theCustomer = customerDao.getCustomer(String.valueOf(r));
					CustomerDialog dialog = new CustomerDialog(theCustomer);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (ApplicationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		fileMenu.addSeparator();
		JMenuItem exit = new JMenuItem("Exit",KeyEvent.VK_X);
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));
		
		exit.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
				
		fileMenu.add(exit);
		
		
		
		
		JMenuItem about = new JMenuItem("About",KeyEvent.VK_A);
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(MainFrame.this, "Lab 9\n By Voicu Chirtes A00884836", "About Lab 9", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		helpMenu.add(about);
		
	}


}
