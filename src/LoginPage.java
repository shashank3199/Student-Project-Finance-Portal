import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

@SuppressWarnings("serial")
public class LoginPage extends JFrame{

	@SuppressWarnings("unused")
	private String TABLE_NAME;
	private JTextField userField;
	private JPasswordField pwdField;
	private DataLink dl = new DataLink();

	public LoginPage(String TABLE_NAME) 
	{
		this.TABLE_NAME = TABLE_NAME;
		String title = "Login: ";
		title += (TABLE_NAME.equals("Fac_Adv")) ? "Faculty" : "Member";
		this.setTitle(title);
		initialize();
	}

	private void initialize() 
	{

		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setBounds(86, 59, 46, 14);
		getContentPane().add(lblUserId);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(84, 97, 70, 14);
		getContentPane().add(lblPassword);

		userField = new JTextField(20);
		userField.setBounds(164, 56, 164, 20);
		getContentPane().add(userField);
		lblUserId.setLabelFor(userField);

		pwdField = new JPasswordField(20);
		pwdField.setBounds(164, 94, 164, 20);
		getContentPane().add(pwdField);
		lblPassword.setLabelFor(pwdField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String userID = userField.getText();
				String pwd  = new String(pwdField.getPassword());											

				if(dl.checkData(new String[] {TABLE_NAME,"ID"}, userID))
				{
					if(dl.checkData(new String[] {TABLE_NAME,"PASS_HASH"},getMd5(pwd)))
					{
						System.out.println("Login Successful");
						if(TABLE_NAME.equalsIgnoreCase("Member"))
						{
							MemberPage window = new MemberPage(userID);
							window.setVisible(true);
							dispose();
						}
						else
						{
							FacultyWindow window = new FacultyWindow(userID);
							window.setVisible(true);
							dispose();
						}
					}
					else
					{
						System.out.println("Login Fail 2");
					}
				}
				else
				{
					System.out.println("Login Fail 1");
				}
				userField.setText("");
				pwdField.setText("");
			}
		});

		btnLogin.setBounds(292, 166, 89, 23);
		getContentPane().add(btnLogin);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		btnExit.setBounds(164, 166, 89, 23);
		getContentPane().add(btnExit);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomePage window = new WelcomePage();
				window.setTitle("Student Project Finance Portal");
				window.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(43, 166, 89, 23);
		getContentPane().add(btnBack);


	}

	public static String getMd5(String input) 
	{ 
		try 
		{ 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			byte[] messageDigest = md.digest(input.getBytes()); 

			BigInteger no = new BigInteger(1, messageDigest); 
			String hashtext = no.toString(16); 
			while (hashtext.length() < 32)  
				hashtext = "0" + hashtext; 
			return hashtext; 
		}  

		catch (NoSuchAlgorithmException e) 
		{ 
			throw new RuntimeException(e); 
		} 
	}
}
