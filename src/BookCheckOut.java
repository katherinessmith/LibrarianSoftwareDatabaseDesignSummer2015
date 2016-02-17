import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BookCheckOut {
	public Font font = new Font("Helvetica", Font.CENTER_BASELINE, 8);
	public Font font_all = new Font("Helvetica", Font.CENTER_BASELINE,14);
	
	private JFrame frame;
	private JTextField bookIdTextField;
	private JTextField branchIdTextField;
	private JTextField cardNumTextField;
	private JButton btnCheckOut;
	private JButton btnBack;

	public BookCheckOut() {
		initCheckOut();
	}

	private void initCheckOut() {
		frame = new JFrame("Check Out");
		frame.setBounds(200, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		JLabel lblBookId = new JLabel("ISBN");
		lblBookId.setFont(font_all);
		lblBookId.setBounds(63, 30, 98, 27);
		frame.getContentPane().add(lblBookId);

		JLabel lblBranchId = new JLabel("Branch ID");
		lblBranchId.setFont(font_all);
		lblBranchId.setBounds(63, 67, 98, 27);
		frame.getContentPane().add(lblBranchId);

		JLabel lblCardNum = new JLabel("Card No.");
		lblCardNum.setFont(font_all);
		lblCardNum.setBounds(63, 106, 98, 27);
		frame.getContentPane().add(lblCardNum);

		JLabel copyRight = new JLabel("\u00a9 Ninaad Pai, 2015");
		copyRight.setFont(font);
		copyRight.setBounds(370, 240, 402, 47);
		frame.getContentPane().add(copyRight);

		bookIdTextField = new JTextField();
		bookIdTextField.setBounds(179, 34, 200, 25);
		frame.getContentPane().add(bookIdTextField);
		bookIdTextField.setColumns(10);

		branchIdTextField = new JTextField();
		branchIdTextField.setBounds(179, 72, 200, 25);
		frame.getContentPane().add(branchIdTextField);
		branchIdTextField.setColumns(10);

		cardNumTextField = new JTextField();
		cardNumTextField.setBounds(179, 110, 200, 25);
		frame.getContentPane().add(cardNumTextField);
		cardNumTextField.setColumns(10);

		btnCheckOut = new JButton("Check out");
		btnCheckOut.setFont(font_all);
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cardNumTextField.getText().isEmpty()
						|| branchIdTextField.getText().isEmpty()
						|| bookIdTextField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Please fill all details");
					return;
				}
				Borrower borrower = new Borrower(Integer
						.parseInt(cardNumTextField.getText()));
				try {
					if (borrower.checkOutNewBook(bookIdTextField.getText(),
							branchIdTextField.getText())) {
						JOptionPane.showMessageDialog(null,
								"Book has been checked out");
					} else
						JOptionPane.showMessageDialog(null,
								borrower.returnMessage);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} finally {
					Refresh();
				}
			}
		});
		btnCheckOut.setBounds(246, 171, 147, 43);
		frame.getContentPane().add(btnCheckOut);

		btnBack = new JButton("Back");
		btnBack.setFont(font_all);
		btnBack.setBounds(63, 171, 160, 43);
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
	}

	public void setCheckOutBookDetails(String bookId, String branchId) {
		bookIdTextField.setText(bookId);
		branchIdTextField.setText(branchId);
	}

	public void Refresh() {
		bookIdTextField.setText("");
		cardNumTextField.setText("");
		branchIdTextField.setText("");
	}
}
