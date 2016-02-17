import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class SysLogin {
	public Font font = new Font("Helvetica", Font.CENTER_BASELINE,8);
	public Font font_all = new Font("Helvetica", Font.CENTER_BASELINE,14);
	
	private JFrame frame;
	private JTextField txtUser;
	private JPasswordField txtPass;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					WrapDB.attachShutDownHook();
					SysLogin window = new SysLogin();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public SysLogin() {
		initSysLogin();
	}

	private void initSysLogin() {
		frame = new JFrame("Login");
		frame.setBounds(200, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JLabel lblUserName = new JLabel("Username");
		lblUserName.setBounds(102, 63, 121, 34);
		lblUserName.setFont(font_all);
		frame.getContentPane().add(lblUserName);
		
		txtUser = new JTextField();
		txtUser.setText("");
		txtUser.setBounds(211, 68, 162, 25);
		frame.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setBounds(102, 118, 121, 34);
		lblPass.setFont(font_all);
		frame.getContentPane().add(lblPass);
		
		txtPass = new JPasswordField();
		txtPass.setText("");
		txtPass.setBounds(211, 123, 162, 25);
		frame.getContentPane().add(txtPass);
		txtPass.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(font_all);
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String user = txtUser.getText();
				String pass = new String(txtPass.getPassword());
				if(WrapDB.validateUser(user, pass))
				{
					frame.setVisible(false);
					new LibraryManagement();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Incorrect username or password. Try again.");
					refresh();
				}
			}

			private void refresh() {
				txtUser.setText("");
				txtPass.setText("");
			}
		});
		btnLogin.setBounds(146, 189, 144, 44);
		frame.getContentPane().add(btnLogin);
		
		JLabel copyRight = new JLabel("\u00a9 Ninaad Pai, 2015");
		copyRight.setFont(font);
		copyRight.setBounds(370,240,402,47);
		frame.getContentPane().add(copyRight);
	}
}
