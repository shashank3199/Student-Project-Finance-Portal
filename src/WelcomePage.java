import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class WelcomePage extends JFrame
{

	private static String TABLE_NAME = "Member";

	public WelcomePage() 
	{
		initialize();
	}

	private void initialize() 
	{
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Faculty Advisor");
		rdbtnNewRadioButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				TABLE_NAME = "Fac_Adv";
			}
		});

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Team Member");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				TABLE_NAME = "Member";	
			}
		});

		rdbtnNewRadioButton.setBounds(168, 82, 138, 23);
		getContentPane().add(rdbtnNewRadioButton);

		rdbtnNewRadioButton_1.setBounds(168, 43, 132, 23);
		getContentPane().add(rdbtnNewRadioButton_1);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);

		rdbtnNewRadioButton_1.setSelected(true);

		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				LoginPage window = new LoginPage(TABLE_NAME);
				window.setVisible(true);
				dispose();
			}
		});

		btnLogIn.setBounds(100, 159, 89, 23);
		getContentPane().add(btnLogIn);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				SignupPage window = new SignupPage(TABLE_NAME);
				window.setVisible(true);
				dispose();
			}
		});

		btnSignUp.setBounds(259, 159, 89, 23);
		getContentPane().add(btnSignUp);
	}
}
