/**
 * Project: A00884836Lab9
 * File: InventoryDialog.java
 * Date: Nov 22, 2016
 * Time: 8:29:45 PM
 */
package a00884836.bcmc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import a00884836.bcmc.data.AllData;
import a00884836.bcmc.data.Inventory;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
@SuppressWarnings("serial")
public class InventoryDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textID;
	private JTextField textDescription;
	private JTextField textPartNo;
	private JTextField textPrice;
	private JTextField textQuantity;

	/**
	 * Create the dialog.
	 */
	public InventoryDialog(Inventory inventory) {
		setTitle("Edit Inventory Item");
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);

		setSize(350, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[72.00,right][grow]", "[][][][][][][][][]"));
		{
			JLabel lblID = new JLabel("Motorcycle ID");
			contentPanel.add(lblID, "cell 0 0,alignx trailing");
		}
		{
			textID = new JTextField();
			textID.setText(inventory.getMotorcycleId());
			textID.setEditable(false);
			contentPanel.add(textID, "cell 1 0,growx");
			textID.setColumns(10);
		}
		{
			JLabel lblDescription = new JLabel("Description");
			contentPanel.add(lblDescription, "cell 0 1,alignx trailing");
		}
		{
			textDescription = new JTextField();
			textDescription.setColumns(10);
			textDescription.setText(inventory.getDescription());
			contentPanel.add(textDescription, "cell 1 1,growx");
		}
		{
			JLabel lblPartNo = new JLabel("Part No.");
			contentPanel.add(lblPartNo, "cell 0 2,alignx trailing");
		}
		{
			textPartNo = new JTextField();
			textPartNo.setEditable(false);
			textPartNo.setColumns(10);
			textPartNo.setText(inventory.getPartNumber());
			contentPanel.add(textPartNo, "cell 1 2,growx");
		}
		{
			JLabel lblPrice = new JLabel("Price");
			contentPanel.add(lblPrice, "cell 0 3,alignx trailing");
		}
		{
			textPrice = new JTextField();
			textPrice.setColumns(10);
			textPrice.setText(String.valueOf(inventory.getPrice()));
			contentPanel.add(textPrice, "cell 1 3,growx");
		}
		{
			JLabel lblQuantity = new JLabel("Quantity");
			contentPanel.add(lblQuantity, "cell 0 4,alignx trailing");
		}
		{
			textQuantity = new JTextField();
			textQuantity.setColumns(10);
			textQuantity.setText(String.valueOf(inventory.getQuantity()));
			contentPanel.add(textQuantity, "cell 1 4,growx");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setToolTipText("Press OK to save changes");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							int price = Integer.parseInt(textPrice.getText());
							int quantity = Integer.parseInt(textQuantity.getText());
							Inventory updatedInventory = new Inventory.Builder(textPartNo.getText()).setDescription(textDescription.getText()).setPrice(price).setQuantity(quantity)
									.setMotorcycleId(textID.getText()).build();
							AllData.updateInventory(updatedInventory);
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(InventoryDialog.this, "Could not save the item " + inventory.getPartNumber() + " to the database", "Error writting data",
									JOptionPane.ERROR_MESSAGE);
							// e.printStackTrace();
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(InventoryDialog.this, "Invalid data, \nprice and quantity must be integer numbers", "Invalid data",
									JOptionPane.ERROR_MESSAGE);
							// e.printStackTrace();
						}

						dispose();
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setToolTipText("Press Cancel to exit without saving changes");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						dispose();
					}
				});
			}
		}
	}

}
