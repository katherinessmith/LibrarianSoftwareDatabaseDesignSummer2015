import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;


public class BorrowerStatistics
{
	public Font font = new Font("Helvetica", Font.CENTER_BASELINE,8);
	public Font font_all = new Font("Helvetica", Font.CENTER_BASELINE,14);
	public Font font_all1 = new Font("Helvetica", Font.CENTER_BASELINE,12);
	
	private JFrame frame;
	private JButton btnPayFine;

	private double checkInFine;
	private double checkOutFine;
	private double finePaidSoFar;

	public BorrowerStatistics()
	{
		initBorrowerStats();
	}

	private void initBorrowerStats()
	{
		frame = new JFrame("Borrower History");
		frame.setBounds(200, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		JLabel lblCardNumber = new JLabel("Card No");
		lblCardNumber.setFont(font_all);
		lblCardNumber.setBounds(52, 27, 100, 25);
		frame.getContentPane().add(lblCardNumber);

		final JTextField cardNumTextField = new JTextField();
		cardNumTextField.setBounds(205, 27, 150, 25);
		frame.getContentPane().add(cardNumTextField);
		cardNumTextField.setColumns(10);

		JLabel lblTotalCheckOuts = new JLabel("Total Check Out Books");
		lblTotalCheckOuts.setFont(font_all);
		lblTotalCheckOuts.setBounds(52, 65, 173, 25);
		frame.getContentPane().add(lblTotalCheckOuts);

		final JLabel lblTotalCheckOutValue = new JLabel("");
		lblTotalCheckOutValue.setBounds(250, 65, 105, 25);
		frame.getContentPane().add(lblTotalCheckOutValue);

		JLabel lblTotalFine = new JLabel("Fine Paid");
		lblTotalFine.setFont(font_all);
		lblTotalFine.setBounds(52, 105, 175, 25);
		frame.getContentPane().add(lblTotalFine);

		final JLabel lblTotalFineValue = new JLabel("");
		lblTotalFineValue.setBounds(250, 105, 105, 25);
		frame.getContentPane().add(lblTotalFineValue);

		JLabel lblCheckInFine = new JLabel("Fine: Checked-In");
		lblCheckInFine.setFont(font_all);
		lblCheckInFine.setBounds(52, 140, 173, 25);
		frame.getContentPane().add(lblCheckInFine);

		final JLabel lblCheckInFineValue = new JLabel("");
		lblCheckInFineValue.setBounds(250, 140, 105, 25);
		frame.getContentPane().add(lblCheckInFineValue);

		JLabel lblCheckOutFine = new JLabel("Fine: Checked-Out");
		lblCheckOutFine.setFont(font_all);
		lblCheckOutFine.setBounds(52, 175, 180, 25);
		frame.getContentPane().add(lblCheckOutFine);

		final JLabel lblCheckOutFineValue = new JLabel("");
		lblCheckOutFineValue.setBounds(250, 175, 105, 25);
		frame.getContentPane().add(lblCheckOutFineValue);

		JLabel copyRight = new JLabel("\u00a9 Ninaad Pai, 2015");
		copyRight.setFont(font);
		copyRight.setBounds(370,240,402,47);
		frame.getContentPane().add(copyRight);

		JButton btnDisplay = new JButton("Display");
		btnDisplay.setFont(font_all);
		btnDisplay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(cardNumTextField.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Please enter card number");
				else
				{
					Borrower borrower = new Borrower(Integer.parseInt(cardNumTextField.getText()));
					double[] stats = borrower.getBorrowerStats();
					if(stats == null)
						JOptionPane.showMessageDialog(null, borrower.returnMessage);
					else
					{
						finePaidSoFar = stats[1];
						checkInFine = stats[2];
						checkOutFine = stats[3];
	
						lblTotalCheckOutValue.setText(": " + String.valueOf((int)stats[0]));
						lblTotalFineValue.setText(": $" + String.valueOf(finePaidSoFar));
						lblCheckInFineValue.setText(": $" + String.valueOf(checkInFine));
						lblCheckOutFineValue.setText(": $" + String.valueOf(checkOutFine));
	
						if(checkInFine != 0.0)
							btnPayFine.setEnabled(true);
					}
				}
			}
		});
		btnDisplay.setBounds(154, 220, 100, 38);
		frame.getContentPane().add(btnDisplay);

		btnPayFine = new JButton("Pay");
		btnPayFine.setFont(font_all);
		btnPayFine.setEnabled(false);
		btnPayFine.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Borrower borrower = new Borrower(Integer.parseInt(cardNumTextField.getText()));
				try
				{
					borrower.payFineAmount();
					JOptionPane.showMessageDialog(null, borrower.returnMessage);
					lblTotalFineValue.setText(": $" + String.valueOf(finePaidSoFar + checkInFine));
					finePaidSoFar += checkInFine;
					checkInFine = 0;
					lblCheckInFineValue.setText(": $0");
					btnPayFine.setEnabled(false);
				}
				catch (SQLException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnPayFine.setBounds(266, 220, 100, 38);
		frame.getContentPane().add(btnPayFine);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(font_all);
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(52, 220, 90, 38);
		frame.getContentPane().add(btnBack);
	}
}
