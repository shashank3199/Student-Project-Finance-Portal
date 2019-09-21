import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FacultyWindow extends JFrame
{
	private String Faculty_ID;
	private DataLink dl = new DataLink();

	public FacultyWindow(String Faculty_ID)
	{
		this.Faculty_ID =Faculty_ID;
		getContentPane().setLayout(null);
		initialize();
	}

	private void initialize() 
	{
		setTitle("Faculty Window");
		setBounds(100, 100, 628, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String[] columns = {"Bill_Id", "Bill_Date", "Merchant_Name", "Amount","Member_ID","Approval_Status"};
		String sql = "select Bill_ID, Bill_Date, merchant_name , amt, member_id, approval_status from bills b,member m, team t where t.fa_id = " + Faculty_ID + " and m.team_id = t.team_id and b.member_id = m.id order by bill_date, approval_status";		
		Object[][] data_raw = dl.getData(sql);

		Object data[][] = new Object[data_raw.length][6];

		for(int i=0;i<data.length;i++)
		{
			for(int j=0;j<5;j++)
			{
				data[i][j] = data_raw[i][j];
			}
			data[i][5] = (String.valueOf(data_raw[i][5])).equalsIgnoreCase("Approved");
		}		

		DefaultTableModel model = new DefaultTableModel(data, columns);

		JTable table = new JTable(model){

			@Override
			public Class<?> getColumnClass(int column) 
			{
				if(column<=4)
					return String.class;
				else
					return Boolean.class;
			}
		};

		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 30, 592, 169);
		getContentPane().add(scrollPane);

		JButton btnApprove = new JButton("Approve");
		btnApprove.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				for(int i=0;i<data.length;i++)
				{
					data_raw[i][5] = (boolean) (table.getModel().getValueAt(i, 5)) ? "Approved" : "Pending";	
					dl.modifyData(new String[] {"Bills","Update",(String) data_raw[i][5],(String) (table.getModel().getValueAt(i, 0))});
				}	
			}
		});
		btnApprove.setBounds(449, 227, 89, 23);
		getContentPane().add(btnApprove);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				LoginPage window = new LoginPage("Fac_Adv");
				window.setVisible(true);;
				dispose();
			}
		});
		btnLogOut.setBounds(53, 227, 89, 23);
		getContentPane().add(btnLogOut);

	}
}