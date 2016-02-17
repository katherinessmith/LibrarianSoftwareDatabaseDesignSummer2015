import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class BookSearch
{
	public Font font = new Font("Helvetica", Font.CENTER_BASELINE,8);
	public Font font_all = new Font("Helvetica", Font.CENTER_BASELINE,14);
	public Font font_all1 = new Font("Helvetica", Font.CENTER_BASELINE,12);
	
	private JFrame frame;
	private JTextField bookIdTextField;
	private JTextField titleTextField;
	private JTextField authorTextField;
	private JTable table;

	public BookSearch()
	{
		initBookSearch();
	}

	private void initBookSearch()
	{
		frame = new JFrame("Library Search");
		frame.setBounds(200, 200, 782, 384);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		KeyListener keyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				ShowSearchBookResult();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		};

		JLabel lblBookId = new JLabel("ISBN");
		lblBookId.setBounds(62, 12, 98, 27);
		lblBookId.setFont(font_all);
		frame.getContentPane().add(lblBookId);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(62, 50, 98, 27);
		lblTitle.setFont(font_all);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(62, 88, 98, 27);
		lblAuthor.setFont(font_all);
		frame.getContentPane().add(lblAuthor);
		
		JLabel copyRight = new JLabel("\u00a9 Ninaad Pai, 2015");
		copyRight.setFont(font);
		copyRight.setBounds(700,330,402,47);
		frame.getContentPane().add(copyRight);
		
		bookIdTextField = new JTextField();
		bookIdTextField.setBounds(153, 20, 300, 25);
		frame.getContentPane().add(bookIdTextField);
		bookIdTextField.setColumns(10);
		bookIdTextField.addKeyListener(keyListener);
		
		titleTextField= new JTextField();
		titleTextField.setBounds(153, 58, 300, 25);
		frame.getContentPane().add(titleTextField);
		titleTextField.setColumns(10);
		titleTextField.addKeyListener(keyListener);
		
		authorTextField= new JTextField();
		authorTextField.setBounds(153, 96, 300, 25);
		frame.getContentPane().add(authorTextField);
		authorTextField.setColumns(10);
		authorTextField.addKeyListener(keyListener);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(font_all);
		btnSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ShowSearchBookResult();
			}
		});
		btnSearch.setBounds(641, 20, 117, 27);
		frame.getContentPane().add(btnSearch);

		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.setFont(font_all);
		btnCheckOut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(table.getSelectedRowCount() <= 0)
					JOptionPane.showMessageDialog(null, "Please select a record");
				else
				{
					if(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString()) == 0)
					{
						JOptionPane.showMessageDialog(null, "This branch is out of copies for this book, please check another branch");
					}
					else
					{
						BookCheckOut bc = new BookCheckOut();
						bc.setCheckOutBookDetails(table.getValueAt(table.getSelectedRow(), 0).toString(), 
								table.getValueAt(table.getSelectedRow(), 3).toString());
					}
				}
				ShowSearchBookResult();
			}
		});
		btnCheckOut.setBounds(641, 58, 117, 27);
		frame.getContentPane().add(btnCheckOut);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(font_all);
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(641, 96, 117, 27);
		frame.getContentPane().add(btnBack);

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane);
		scrollPane.setVisible(true);
		scrollPane.setBounds(22, 156, 736, 134);
		table = new JTable();
		scrollPane.setViewportView(table);

		String[] columnNames = { "ISBN", "Title", "Author",
				"Branch ID", "All Copies", "Available Copies" };
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table.setModel(model);
		table.setFont(font_all1);
	}

	private void ShowSearchBookResult() {
		SearchParas bookDetails = new SearchParas(bookIdTextField.getText(),
				titleTextField.getText(), authorTextField.getText());
		DefaultTableModel tableModel = SearchBook.returnAvailability(bookDetails);
		if(tableModel == null)
			JOptionPane.showMessageDialog(null, SearchBook.returnMessage);
		else
		table.setModel(tableModel);
	}
}
