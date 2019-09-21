import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AddBillPage extends JFrame
{
	private String Member_ID;
	private JTextField IDField;
	private JTextField bAmtField;
	private JTextField mNameField;
	private JTextField bDateField;
	private String sqlCmd[];
	private DataLink dl = new DataLink();

	public AddBillPage(String Member_ID) 
	{
		this.Member_ID = Member_ID;
		getContentPane().setLayout(null);
		this.setTitle("Add Bills");
		initialize();
	}

	private void initialize() 
	{
		setBounds(100, 100, 450, 338);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel billIDLabel = new JLabel("Biil No: ");
		billIDLabel.setBounds(43, 30, 79, 14);
		getContentPane().add(billIDLabel);

		IDField = new JTextField();
		IDField.setBounds(195, 27, 180, 20);
		getContentPane().add(IDField);
		IDField.setColumns(10);

		JLabel bDateLabel = new JLabel("Bill Date (dd-mm-yyyy):");
		bDateLabel.setBounds(43, 182, 150, 14);
		getContentPane().add(bDateLabel);

		bDateField = new JTextField();
		bDateField.setColumns(10);
		bDateField.setBounds(195, 179, 180, 20);
		getContentPane().add(bDateField);

		JLabel bAmtLabel = new JLabel("Bill Amount: ");
		bAmtLabel.setBounds(43, 81, 104, 14);
		getContentPane().add(bAmtLabel);

		bAmtField = new JTextField();
		bAmtField.setColumns(10);
		bAmtField.setBounds(195, 78, 180, 20);
		getContentPane().add(bAmtField);

		JLabel mNameLabel = new JLabel("Merchant Name: ");
		mNameLabel.setBounds(43, 133, 119, 14);
		getContentPane().add(mNameLabel);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String billID = IDField.getText();
				String billDate = bDateField.getText();
				String billAmt  = bAmtField.getText();
				String mName  = mNameField.getText();
				sqlCmd = new String[] 
						{
								"Bills",
								"INSERT",
								billID,
								"TO_DATE('" + billDate + "','dd-mm-yyyy')",
								billAmt,
								Member_ID,
								"'Pending'",
								"'" + mName + "'"
						};
				dl.modifyData(sqlCmd);
				dispose();
				MemberPage window = new MemberPage(Member_ID);
				window.setVisible(true);
			}
		});

		mNameField = new JTextField();
		mNameField.setColumns(10);
		mNameField.setBounds(195, 130, 180, 20);
		getContentPane().add(mNameField);
		btnSubmit.setBounds(164, 235, 89, 23);
		getContentPane().add(btnSubmit);			
	}

}
