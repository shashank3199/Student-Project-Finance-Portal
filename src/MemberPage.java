import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MemberPage extends JFrame
{

	private String Member_ID;
	private DataLink dl = new DataLink();

	public MemberPage(String Member_ID) 
	{
		this.Member_ID = Member_ID;
		getContentPane().setLayout(null);
		initialize();
	}

	private void initialize() 
	{
		this.setTitle("Member Page");
		setBounds(100, 100, 534, 307);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	

		JButton btnAddBills = new JButton("Add Bills");
		btnAddBills.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				AddBillPage window = new AddBillPage(Member_ID);
				window.setVisible(true);
				dispose();
			}
		});
		btnAddBills.setBounds(299, 213, 145, 33);
		getContentPane().add(btnAddBills);

		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(169, 11, 175, 14);
		getContentPane().add(lblName);

		JLabel lblBalanceDue = new JLabel("Balance Due:");
		lblBalanceDue.setBounds(169, 63, 175, 14);
		getContentPane().add(lblBalanceDue);

		JLabel lblUserId = new JLabel("User ID: ");
		lblUserId.setBounds(169, 38, 175, 14);
		getContentPane().add(lblUserId);

		JLabel lblUserIdVal = new JLabel(Member_ID);
		lblUserIdVal.setBounds(300, 38, 175, 14);
		getContentPane().add(lblUserIdVal);

		String sql = "select sum(amt) from (Select * from bills where member_id = " + Member_ID + " order by Approval_status,bill_date) where Approval_Status = 'Pending'";
		String userData[][] = dl.getData(sql);

		String userBalance = userData[0][0] == null ? "0" : userData[0][0] ;

		userData = dl.getData("Select name from member where id = " + Member_ID );
		String userName = userData[0][0];

		JLabel lblUserName = new JLabel(userName);
		lblUserName.setBounds(300, 11, 175, 14);
		getContentPane().add(lblUserName);

		JLabel lblUserBalance = new JLabel(userBalance);
		lblUserBalance.setBounds(300, 63, 175, 14);
		getContentPane().add(lblUserBalance);

		String[] columns = {"Bill_Id", "Bill_Date", "Merchant_Name", "Amount","Approval_Status"};
		String[][] data = dl.getData("Select bill_id, bill_date , merchant_name,amt,approval_status from bills where member_id = " + Member_ID + " order by Approval_status,bill_date");

		TableModel model = new DefaultTableModel(data, columns)
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};

		JTable table = new JTable(data,columns);
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 98, 498, 99);
		getContentPane().add(scrollPane);		

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				LoginPage window = new LoginPage("Member");
				window.setVisible(true);;
				dispose();
			}
		});
		btnLogOut.setBounds(85, 213, 145, 33);
		getContentPane().add(btnLogOut);
	}
}