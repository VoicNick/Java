/**
 * Project: A00884836_Assignment2
 * File: InventoryList.java
 * Date: Nov 30, 2016
 * Time: 3:09:10 PM
 */
package a00884836.bcmc.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import a00884836.bcmc.ApplicationException;
import a00884836.bcmc.data.AllData;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JList;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.SwingConstants;
// import javax.swing.event.ListSelectionEvent;
// import javax.swing.event.ListSelectionListener;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
@SuppressWarnings("serial")
public class InventoryList extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// try {
	// InventoryList dialog = new InventoryList();
	// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	// dialog.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * Create the dialog.
	 */
	public InventoryList() {
		setResizable(false);
		setTitle("Inventory List");
		setModal(true);

		setSize(250, 350);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			// Map<String, Inventory> inventory = AllData.getInventory();
			contentPanel.setLayout(new MigLayout("", "[200.00px]", "[44.00px][][]"));
			{
				JLabel lblTheInventoryList = new JLabel("The Inventory List");
				lblTheInventoryList.setFont(new Font("Tahoma", Font.BOLD, 14));
				contentPanel.add(lblTheInventoryList, "cell 0 0");
			}
			{
				JLabel lblSelectAnItem = new JLabel("Select an item to view or edit:");
				contentPanel.add(lblSelectAnItem, "cell 0 1");
			}
			// contentPanel.setLayout(new MigLayout("", "[258px]", "[130px]"));

			// JList<String> list = new JList<String>(inventory.keySet().toArray(new String[0]));
			final JList<String> list;
			try {
				list = new JList<String>(AllData.getInventoryPartNumbersFromDB().toArray(new String[0]));
				// list = new JList<String>();
				list.setToolTipText("Select an item to view or edit");
				list.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						String selected = list.getSelectedValue();

						try {
							InventoryDialog inventoryDialog = new InventoryDialog(AllData.getOneInventory(selected));
							inventoryDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							inventoryDialog.setVisible(true);
						} catch (SQLException | ApplicationException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(InventoryList.this, "Could not retrieve the item " + selected + " from the database", "Error reading data",
									JOptionPane.ERROR_MESSAGE);
							// e1.printStackTrace();
						}
					}

				});
				JScrollPane listScroller = new JScrollPane(list);
				listScroller.setPreferredSize(new Dimension(200, 300));
				listScroller.setAlignmentX(CENTER_ALIGNMENT);
				listScroller.setAlignmentY(BOTTOM_ALIGNMENT);
				contentPanel.add(listScroller, "cell 0 2,alignx left,aligny top");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeButton = new JButton("Close");
				closeButton.setActionCommand("Close");
				buttonPane.add(closeButton);
				getRootPane().setDefaultButton(closeButton);
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}
	}

}
