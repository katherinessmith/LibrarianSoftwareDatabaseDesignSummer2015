import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AddBorrower
{
	public Font font = new Font("Helvetica", Font.CENTER_BASELINE,8);
	public Font font_all = new Font("Helvetica", Font.CENTER_BASELINE,14);
	
	private JFrame frame;
	private JTextField fnameTextField;
	private JTextField lnameTextField;
	private JTextField addressTextField;
	private JTextField phoneTextField;
	private JButton btnAddBorrower;
	private JButton btnBack;

	public AddBorrower()
	{
		initAddBorrower();
	}

	private void initAddBorrower()
	{
		frame = new JFrame("Add New Borrower");
		frame.setBounds(200, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(font_all);
		lblFirstName.setBounds(63, 30, 98, 27);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(font_all);
		lblLastName.setBounds(63, 70, 98, 27);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(font_all);
		lblAddress.setBounds(63, 110, 98, 27);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblPhone = new JLabel("Phone No.");
		lblPhone.setFont(font_all);
		lblPhone.setBounds(63, 150, 98, 27);
		frame.getContentPane().add(lblPhone);
		
		JLabel copyRight = new JLabel("\u00a9 Ninaad Pai, 2015");
		copyRight.setFont(font);
		copyRight.setBounds(370,240,402,47);
		frame.getContentPane().add(copyRight);
		
		fnameTextField = new JTextField();
		fnameTextField.setBounds(179, 34, 218, 25);
		frame.getContentPane().add(fnameTextField);
		fnameTextField.setColumns(10);
		
		lnameTextField = new JTextField();
		lnameTextField.setBounds(179, 74, 218, 25);
		frame.getContentPane().add(lnameTextField);
		lnameTextField.setColumns(10);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(180, 114, 217, 25);
		frame.getContentPane().add(addressTextField);
		addressTextField.setColumns(10);
		
		phoneTextField = new JTextField();
		phoneTextField.setBounds(179, 154, 218, 25);
		frame.getContentPane().add(phoneTextField);
		phoneTextField.setColumns(10);
		
		btnAddBorrower = new JButton("Add");
		btnAddBorrower.setFont(font_all);
		btnAddBorrower.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String fname = fnameTextField.getText();
				String lname = lnameTextField.getText();
				String address = addressTextField.getText();
				String phone = phoneTextField.getText();
				if(ValidateAll())
				{
					Borrower borrower = new Borrower(fname, lname, address, phone);
					borrower.addToDb();
					JOptionPane.showMessageDialog(null, borrower.returnMessage);
					refreshAll();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please fill all details");
				}
			}

			private void refreshAll() {
				fnameTextField.setText("");
				lnameTextField.setText("");
				addressTextField.setText("");
				phoneTextField.setText("");
			}

			private boolean ValidateAll()
			{
				if(fnameTextField.getText().isEmpty())
					return false;
				if(lnameTextField.getText().isEmpty())
					return false;
				if(addressTextField.getText().isEmpty())
					return false;
				if(phoneTextField.getText().isEmpty())
					return false;
				else
					return true;
			}
		});
		btnAddBorrower.setBounds(250, 204, 147, 45);
		frame.getContentPane().add(btnAddBorrower);

		btnBack = new JButton("Back");
		btnBack.setFont(font_all);
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(63, 204, 147, 45);
		frame.getContentPane().add(btnBack);
	}

}
