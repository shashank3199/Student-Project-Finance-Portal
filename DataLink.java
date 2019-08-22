import java.sql.*;

public class DataLink 
{
	private Connection con;
	private Statement stmt; 
	private ResultSet rs;

	public DataLink() 
	{	
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");				
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "System", "root");			
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			con.setAutoCommit(true);
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e);
		}
	}

	public void modifyData(String[] args) 
	{

		if(args.length==0)
		{
			System.out.println("No Parameters Supplied.");
			return ;
		}

		String table = args[0];
		String instruction = args[1];
		String sql = "";		

		if(instruction.equalsIgnoreCase("Insert"))
		{
			sql = "INSERT into " + table + " values(";
			for(int i=2;i<args.length;i++)
			{
				sql += args[i]; 
				if(i != args.length-1)
					sql += ",";
			}
			sql +=')';
		}

		else if(instruction.equalsIgnoreCase("Update"))
		{
			sql = "Update Bills set Approval_Status = '" + args[2] + "' where Bill_ID = " + args[3];			
		}

		try 
		{
			stmt.executeUpdate(sql);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public boolean checkData(String[] args,String data) 
	{
		if(args.length==0)
		{
			System.out.println("No Parameters Supplied.");
			return false;
		}

		String table = args[0];
		String sql = "";
		boolean flag = false;

		sql = "Select ";
		for(int i=1;i<args.length;i++)
		{
			sql += args[i]; 
			if(i != args.length-1)
				sql += ",";
		}
		sql += " from " + table;
		try 
		{
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{		
				String temp = rs.getString(1);
				if(temp.equalsIgnoreCase(data))
				{	
					flag = true;
					break;
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return flag;
	}

	public String[][] getData(String sql)
	{
		String tuple[][] = null;
		try 
		{
			rs = stmt.executeQuery(sql);
			int c = rs.getMetaData().getColumnCount();
			rs.last();
			int r = rs.getRow();
			rs.beforeFirst();
			tuple = new String[r][];
			for(int i=0;i<r;i++)
			{
				rs.next();
				tuple[i] = new String[rs.getMetaData().getColumnCount()];
				for(int j=0;j<c;j++)
					tuple[i][j] = rs.getString(j+1);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return tuple;
	}
}
