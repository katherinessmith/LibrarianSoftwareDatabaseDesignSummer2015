import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BorrowerMgmt
{
	public Font font = new Font("Helvetica", Font.CENTER_BASELINE,8);
	public Font font_all = new Font("Helvetica", Font.CENTER_BASELINE,14);
	public Font font_all1 = new Font("Helvetica", Font.CENTER_BASELINE,12);
	
	private JFrame frame;

	public BorrowerMgmt()
	{
		initBorrowerMgmt();
	}

	private void initBorrowerMgmt()
	{
		frame = new JFrame("Borrower Management");
		frame.setBounds(200, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		JButton btnBorrowerStats = new JButton("Borrower History");
		btnBorrowerStats.setFont(font_all);
		btnBorrowerStats.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new BorrowerStatistics();
			}
		});
		btnBorrowerStats.setBounds(34, 41, 165, 67);
		frame.getContentPane().add(btnBorrowerStats);

		JButton btnNewBorrower = new JButton("New Borrower");
		btnNewBorrower.setFont(font_all);
		btnNewBorrower.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				new AddBorrower();
			}
		});
		btnNewBorrower.setBounds(240, 41, 165, 67);
		frame.getContentPane().add(btnNewBorrower);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(font_all);
		btnBack.setBounds(133, 162, 165, 67);
		frame.getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
			}
		});
		JLabel copyRight = new JLabel("\u00a9 Ninaad Pai, 2015");
		copyRight.setFont(font);
		copyRight.setBounds(370,240,402,47);
		frame.getContentPane().add(copyRight);
	}
	
}
