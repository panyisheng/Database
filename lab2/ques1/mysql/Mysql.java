package hw12;

import java.sql.*;



public class Mysql {
    public static void main(String[] args) throws ClassNotFoundException {  
		try {
			long start3,end3;
			start3 = System.currentTimeMillis();			
			Thread1 mTh1 = new Thread1("Insert");
			Thread2 mTh2 = new Thread2("Querry");
			mTh1.start();
			mTh2.start();
			end3 = System.currentTimeMillis();
			
			//System.out.println("total runtime:"+(end3-start3)+"ms");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
  
        
    }
}


class Thread1 extends Thread{
	private String name;
	
	public Thread1(String name) {
		this.name = name;
		System.out.println("Thread "+ name + " Created!");
	}
	
	private Connection connect() throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/prob1";
		String user	="root";
		String password="pysxp8500723.";
		Connection conn1 = null;
		try {
			conn1 = DriverManager.getConnection(url,user,password);
		}
		catch (SQLException e1){
			System.out.println(e1.getMessage());
		}
		System.out.println("Thread "+name+" connected database");
		return conn1;
	}
	
	private void insertop  () throws ClassNotFoundException {
		String SQLinsert = "INSERT INTO movies(id,title,year,runtime) values (?,?,?,?)";
		
		ResultSet rs1 = null;
		Connection conn1 = null;
		PreparedStatement pstm1 = null;
		
		int idnum = 9999999;
		String myid;
		
		try {
			// connect to database
			conn1 = this.connect();
			if(conn1 == null)
				return;
			
			// set antocommit to false
			conn1.setAutoCommit(false);	
			conn1.setTransactionIsolation(conn1.TRANSACTION_READ_COMMITTED);
			
			for(int i = 0;i<100000;i++) {
				myid = "tt"+idnum;
				
				pstm1 = conn1.prepareStatement(SQLinsert);
				pstm1.setString(1, myid);
				pstm1.setString(2, "pys");
				pstm1.setInt(3, 1963);
				pstm1.setInt(4, 66);
				pstm1.execute();
				
				idnum --;
			}
			//commit work
			conn1.commit();
			conn1.setAutoCommit(true);
		}
		catch(SQLException e11) {
			try {
				if(conn1 != null)
					conn1.rollback();
			}
			catch(SQLException e12) {
				System.out.println("12"+e12.getMessage());
			}
			System.out.println("11"+e11.getMessage());
		}
		finally {
			try {
				if(rs1!=null) {
					rs1.close();
				}
				if(pstm1!=null) {
					pstm1.close();
				}
				if(conn1!=null) {
					conn1.close();
				}
			}
			catch(SQLException e13){
				System.out.println("13"+e13.getMessage());
			}
		}
	}
	
	public void run()  {
		try  {
			long start1,end1;
			
			//record the start time
			start1 = System.currentTimeMillis();  
			
			this.insertop();
			
			//record the end time
			end1 = System.currentTimeMillis();
			
			System.out.print("Thread "+this.name+" Done!");
			System.out.println(" runtime:"+(end1-start1)+" ms");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}


class Thread2 extends Thread{
	private String name;
	
	public Thread2(String name) {
		this.name = name;
		System.out.println("Thread "+ name + " Created!");
	}
	
	private Connection connect() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/prob1";
		String user	="root";
		String password="pysxp8500723.";
		Connection conn2 = null;
		try {
			conn2 = DriverManager.getConnection(url,user,password);
		}
		catch (SQLException e2){
			System.out.println(e2.getMessage());
		}
		System.out.println("Thread "+name+" connected database");
		return conn2;
	}
	
	private void lookup () throws ClassNotFoundException {
		String SQLquery = "SELECT avg(runtime) FROM movies WHERE year = 1963 and runtime is not null lock in share mode	 ;";
							
		
		ResultSet rs2 = null;
		Connection conn2 = null;
		Statement stat2 = null;
		
		try {
			// connect to database
			conn2 = this.connect();
			if(conn2 == null)
				return;
			
			// set antocommit to false
			conn2.setAutoCommit(false);	
			conn2.setTransactionIsolation(conn2.TRANSACTION_READ_COMMITTED);
			
			stat2 = conn2.createStatement();
			rs2 = stat2.executeQuery(SQLquery);
			while (rs2.next())
			{
				System.out.println("average time:"+rs2.getDouble("avg(runtime)"));
			}
			//System.out.println("average time:"+rs2.getDouble("avg(runtime)"));
			
			//commit work
			conn2.commit();
			conn2.setAutoCommit(true);
		}
		catch(SQLException e21) {
			try {
				if(conn2 != null)
					conn2.rollback();
			}
			catch(SQLException e22) {
				System.out.println("22"+e22.getMessage());
			}
			System.out.println("21"+e21.getMessage());
		}
		finally {
			try {
				if(rs2!=null) {
					rs2.close();
				}
				if(conn2!=null) {
					conn2.close();
				}
			}
			catch(SQLException e23){
				System.out.println("23"+e23.getMessage());
			}
		}
	}
	
	public void run() {
		try {
			long start2,end2;
			
			//record the start time
			start2 = System.currentTimeMillis();  
			
			this.lookup();
			
			//record the end time
			end2 = System.currentTimeMillis();
			
			System.out.print("Thread "+this.name+" Done!");
			System.out.println(" runtime:"+(end2-start2)+" ms");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}