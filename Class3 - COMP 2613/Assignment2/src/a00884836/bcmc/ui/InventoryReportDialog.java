/**
 * Project: A00884836_Assignment2
 * File: InventoryReportDialog.java
 * Date: Dec 1, 2016
 * Time: 2:23:01 PM
 */
package a00884836.bcmc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import a00884836.bcmc.data.AllData;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
@SuppressWarnings("serial")
public class InventoryReportDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public InventoryReportDialog(Boolean sortByDescription, Boolean sortByCount, Boolean descending, String filterMake) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setTitle("Inventory Report");

		setResizable(false);

		setSize(800, 600);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		{
			JTextArea textArea = new JTextArea();
			textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
			JScrollPane reportScroller = new JScrollPane(textArea);
			reportScroller.getVerticalScrollBar().setValue(0);
			contentPanel.add(reportScroller, "cell 0 0,grow");

			try {
				File reportFile = AllData.generateInventoryReportFile(sortByDescription, sortByCount, descending, filterMake);
				BufferedReader in = new BufferedReader(new FileReader(reportFile));
				String line = in.readLine();
				while (line != null) {
					textArea.append(line + "\n");
					line = in.readLine();
				}
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				JOptionPane.showMessageDialog(InventoryReportDialog.this, "Error opening the inventory report file", "Error opening file", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				JOptionPane.showMessageDialog(InventoryReportDialog.this, "Error reading the inventory report file", "Error reading file", JOptionPane.ERROR_MESSAGE);
			}

		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Close");
				buttonPane.add(cancelButton);
			}
		}
	}

}
