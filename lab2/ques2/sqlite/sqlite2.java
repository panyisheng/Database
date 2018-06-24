package hw2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class sqlite2 {  
  
    public static void main(String[] args) throws ClassNotFoundException {  
		try {
			Thread3 mTh1 = new Thread3("Delete");
			Thread4 mTh2 = new Thread4("Querry");
			mTh1.start();
			mTh2.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
  
        
    }
}  


//Thread 1: insert 10000 movies 
class Thread3 extends Thread{
	private String name;
	
	public Thread3(String name) {
		this.name = name;
		System.out.println("Thread "+ name + " Created!");
	}
	
	private Connection connect() throws ClassNotFoundException{
		Class.forName("org.sqlite.JDBC");
		String url = "jdbc:sqlite:prob1.db";
		Connection conn1 = null;
		try {
			conn1 = DriverManager.getConnection(url);
		}
		catch (SQLException e1){
			System.out.println(e1.getMessage());
		}
		System.out.println("Thread "+name+" connected database");
		return conn1;
	}
	
	private void insertop  () throws ClassNotFoundException {
		String SQLinsert = "DELETE FROM movies WHERE id IN (SELECT movie_id FROM ratings WHERE rating > 6.0) AND year IN (2016, 2017);";
		
	//	ResultSet rs1 = null;
		Connection conn1 = null;
	//	PreparedStatement pstm1 = null;
		Statement stat1 =null;
		
		int idnum = 9999999;
		String myid;
		
		try {
			// connect to database
			conn1 = this.connect();
			if(conn1 == null)
				return;
			
			// set antocommit to false
			conn1.setAutoCommit(false);	
			conn1.setTransactionIsolation(conn1.TRANSACTION_SERIALIZABLE);
			stat1 = conn1.createStatement();
			stat1.executeUpdate(SQLinsert);
		    
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
			
			System.out.print("Thread "+this.name+" Done");
			System.out.println(" runtime:"+(end1-start1));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

class Thread4 extends Thread{
	private String name;
	
	public Thread4(String name) {
		this.name = name;
		System.out.println("Thread "+ name + " Created!");
	}
	
	private Connection connect() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		String url = "jdbc:sqlite:prob1.db";
		Connection conn2 = null;
		try {
			conn2 = DriverManager.getConnection(url);
		}
		catch (SQLException e2){
			System.out.println(e2.getMessage());
		}
		System.out.println("Thread "+name+" connected database");
		return conn2;
	}
	
	private void lookup () throws ClassNotFoundException {
		String SQLquery = "select AVG(runtime) from movies where id IN (select movie_id from ratings where rating > 6.0) ;";
							
		
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
			conn2.setTransactionIsolation(conn2.TRANSACTION_SERIALIZABLE);
			
			stat2 = conn2.createStatement();
			rs2 = stat2.executeQuery(SQLquery);
			while(rs2.next()){	
			System.out.println("average time:"+rs2.getDouble("avg(runtime)"));
			}
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
			System.out.println(" runtime:"+(end2-start2));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
