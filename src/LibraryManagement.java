import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LibraryManagement
{
	public Font font = new Font("Helvetica", Font.CENTER_BASELINE,8);
	public Font font_all = new Font("Helvetica", Font.CENTER_BASELINE,14);
	
	private JFrame frame;

	public LibraryManagement()
	{
		initLibMgmt();
	}

	private void initLibMgmt()
	{
		frame = new JFrame("Librarian Tool");
		frame.setBounds(200, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		JLabel lblLibraryManagement = new JLabel("Central Console");
		lblLibraryManagement.setBounds(22, 22, 402, 47);
		lblLibraryManagement.setFont(font_all);
		frame.getContentPane().add(lblLibraryManagement);
		
		JButton btnBookSearch = new JButton("Library Search");
		btnBookSearch.setFont(font_all);
		btnBookSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new BookSearch();
			}
		});
		btnBookSearch.setBounds(12, 79, 196, 61);
		frame.getContentPane().add(btnBookSearch);

		JButton btnBorrowerManagement = new JButton("Borrower Management");
		btnBorrowerManagement.setFont(font_all);
		btnBorrowerManagement.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new BorrowerMgmt();
			}
		});
		btnBorrowerManagement.setBounds(230, 79, 204, 61);
		frame.getContentPane().add(btnBorrowerManagement);
		
		JButton btnCheckIn = new JButton("Check In");
		btnCheckIn.setFont(font_all);
		btnCheckIn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new CheckIn();
			}
		});
		btnCheckIn.setBounds(12, 182, 196, 61);
		frame.getContentPane().add(btnCheckIn);
		
		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.setFont(font_all);
		btnCheckOut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new BookCheckOut();
			}
		});
		btnCheckOut.setBounds(230, 182, 208, 61);
		frame.getContentPane().add(btnCheckOut);
		JLabel copyRight = new JLabel("\u00a9 Ninaad Pai, 2015");
		copyRight.setFont(font);
		copyRight.setBounds(370,240,402,47);
		frame.getContentPane().add(copyRight);
	}
	
	
}
