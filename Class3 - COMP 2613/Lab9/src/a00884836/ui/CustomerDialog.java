/**
* Project: A00884836Lab9
* File: CustomerDialog.java
* Date: Nov 22, 2016
* Time: 8:29:45 PM
*/
package a00884836.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import a00884836.data.Customer;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
* @author Voicu Chirtes, A00884836
*
*/
@SuppressWarnings("serial")
public class CustomerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textID;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JTextField textStreet;
	private JTextField textCity;
	private JTextField textPostalCode;
	private JTextField textPhone;
	private JTextField textEmail;
	private JTextField textJoinedDate;


	/**
	 * Create the dialog.
	 */
	public CustomerDialog(Customer customer) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		
		setSize(350,300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[72.00,right][grow]", "[][][][][][][][][]"));
		{
			JLabel lblID = new JLabel("ID");
			contentPanel.add(lblID, "cell 0 0,alignx trailing");
		}
		{
			textID = new JTextField();
			textID.setText(String.valueOf(customer.getId()));
			textID.setEditable(false);
			contentPanel.add(textID, "cell 1 0,growx");
			textID.setColumns(10);
		}
		{
			JLabel lblFirstName = new JLabel("First Name");
			contentPanel.add(lblFirstName, "cell 0 1,alignx trailing");
		}
		{
			textFirstName = new JTextField();
			textFirstName.setColumns(10);
			textFirstName.setText(customer.getFirstName());
			contentPanel.add(textFirstName, "cell 1 1,growx");
		}
		{
			JLabel lblLastName = new JLabel("Last Name");
			contentPanel.add(lblLastName, "cell 0 2,alignx trailing");
		}
		{
			textLastName = new JTextField();
			textLastName.setColumns(10);
			textLastName.setText(customer.getLastName());
			contentPanel.add(textLastName, "cell 1 2,growx");
		}
		{
			JLabel lblStreet = new JLabel("Street");
			contentPanel.add(lblStreet, "cell 0 3,alignx trailing");
		}
		{
			textStreet = new JTextField();
			textStreet.setColumns(10);
			textStreet.setText(customer.getStreet());
			contentPanel.add(textStreet, "cell 1 3,growx");
		}
		{
			JLabel lblCity = new JLabel("City");
			contentPanel.add(lblCity, "cell 0 4,alignx trailing");
		}
		{
			textCity = new JTextField();
			textCity.setColumns(10);
			textCity.setText(customer.getCity());
			contentPanel.add(textCity, "cell 1 4,growx");
		}
		{
			JLabel lblPostalCode = new JLabel("Postal Code");
			contentPanel.add(lblPostalCode, "cell 0 5,alignx trailing");
		}
		{
			textPostalCode = new JTextField();
			textPostalCode.setColumns(10);
			textPostalCode.setText(customer.getPostalCode());
			contentPanel.add(textPostalCode, "cell 1 5,growx");
		}
		{
			JLabel lblPhone = new JLabel("Phone");
			contentPanel.add(lblPhone, "cell 0 6,alignx trailing");
		}
		{
			textPhone = new JTextField();
			textPhone.setColumns(10);
			textPhone.setText(customer.getPhone());
			contentPanel.add(textPhone, "cell 1 6,growx");
		}
		{
			JLabel lblEmail = new JLabel("Email");
			contentPanel.add(lblEmail, "cell 0 7,alignx trailing");
		}
		{
			textEmail = new JTextField();
			textEmail.setColumns(10);
			textEmail.setText(customer.getEmailAddress());
			contentPanel.add(textEmail, "cell 1 7,growx");
		}
		{
			JLabel lblJoinedDate = new JLabel("Joined Date");
			contentPanel.add(lblJoinedDate, "cell 0 8,alignx trailing");
		}
		{
			textJoinedDate = new JTextField();
			textJoinedDate.setColumns(10);
			textJoinedDate.setText(customer.getJoinDate().toString());
			contentPanel.add(textJoinedDate, "cell 1 8,growx");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener( new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						dispose();
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener( new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						dispose();
					}
				});
			}
		}
	}

}
