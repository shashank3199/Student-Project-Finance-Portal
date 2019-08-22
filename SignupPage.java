import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SignupPage extends JFrame{

	private String TABLE_NAME;
	private JTextField tidField;
	private JTextField nameField;
	private JTextField userField;
	private JPasswordField pwdField;
	private JPasswordField confPwdField;
	private String sqlCmd[];
	private DataLink dl = new DataLink();

	public SignupPage(String TABLE_NAME) 
	{
		this.TABLE_NAME = TABLE_NAME;
		String title = "Sign Up: ";
		title += (TABLE_NAME == "Fac_Adv") ? "Faculty" : "Member";
		this.setTitle(title);
		initialize();
	}

	private void initialize() 
	{
		setBounds(100, 100, 475, 327);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblEnterName = new JLabel("Enter Name: ");
		lblEnterName.setBounds(112, 33, 89, 20);
		getContentPane().add(lblEnterName);

		JLabel lblUserId = new JLabel("Enter User ID:");
		lblUserId.setBounds(100, 65, 104, 14);
		getContentPane().add(lblUserId);

		JLabel lblPassword = new JLabel("Enter Password: ");
		lblPassword.setBounds(90, 90, 121, 14);
		getContentPane().add(lblPassword);

		JLabel lblConfPassword = new JLabel("Confirm Password: ");
		lblConfPassword.setBounds(81, 119, 120, 14);
		getContentPane().add(lblConfPassword);

		nameField = new JTextField(20);
		nameField.setBounds(220, 31, 164, 20);
		getContentPane().add(nameField);
		lblEnterName.setLabelFor(nameField);

		userField = new JTextField(20);
		userField.setBounds(220, 59, 164, 20);
		getContentPane().add(userField);
		lblUserId.setLabelFor(userField);

		pwdField = new JPasswordField(20);
		pwdField.setBounds(220, 87, 164, 20);
		getContentPane().add(pwdField);
		lblPassword.setLabelFor(pwdField);

		confPwdField = new JPasswordField(20);
		confPwdField.setBounds(220, 115, 164, 20);
		getContentPane().add(confPwdField);
		lblPassword.setLabelFor(confPwdField);

		if(TABLE_NAME.equalsIgnoreCase("Member"))
		{
			JLabel lblTid = new JLabel("Team ID: ");
			lblTid.setBounds(110, 146, 120, 14);
			getContentPane().add(lblTid);

			tidField = new JTextField(20);
			tidField.setBounds(220, 143, 164, 20);
			getContentPane().add(tidField);
			lblTid.setLabelFor(tidField);
		}

		JLabel label = new JLabel();
		label.setBounds(168, 164, 129, 20);
		getContentPane().add(label);
		label.setVisible(false);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String name = nameField.getText();
				String userID = userField.getText();
				String pwd  = new String(pwdField.getPassword());
				String confPwd  = new String(confPwdField.getPassword());
				String tID = null;
				if(TABLE_NAME.equalsIgnoreCase("Member"))
				{
					tID = tidField.getText();				
				}

				if((!dl.checkData(new String[] {"Team","Team_ID"}, tID)) && (!TABLE_NAME.equalsIgnoreCase("Fac_Adv")))
					label.setText("Invalid Team ID.");
				else 
				{
					if(!(pwd.equals(confPwd)))
					{
						label.setText("Passwords do not match. Try Again.");
					}				
					else
					{
						label.setText("Entry Recorded.");

						nameField.setText("");
						userField.setText("");
						pwdField.setText("");
						confPwdField.setText("");
						if(TABLE_NAME.equalsIgnoreCase("Member"))
							tidField.setText("");									

						if(TABLE_NAME.equalsIgnoreCase("Member")) 
						{
							sqlCmd = new String[] 
									{
											"Member",
											"INSERT",
											"'" + name + "'",
											userID,
											tID,
											"'"+getMd5(pwd)+"'"
									}; 			
							dl.modifyData(sqlCmd);
						}
						else
						{
							sqlCmd = new String[] 
									{
											"Fac_Adv",
											"INSERT",
											"'" + name + "'",
											userID,
											"'"+getMd5(pwd)+"'"
									};
							dl.modifyData(sqlCmd);
						}
					}						
					dispose();
					WelcomePage window = new WelcomePage();
					window.setTitle("Student Project Finance Portal");
					window.setVisible(true);
				}
				label.setVisible(true);				

			}
		});
		btnSignUp.setBounds(295, 195, 89, 23);
		getContentPane().add(btnSignUp);		

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(ABORT);
			}
		});
		btnExit.setBounds(178, 195, 89, 23);
		getContentPane().add(btnExit);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				WelcomePage window = new WelcomePage();
				window.setTitle("Student Project Finance Portal");
				window.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(56, 195, 89, 23);
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
