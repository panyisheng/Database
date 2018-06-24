package hw12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Mysql2 {
    public static void main(String[] args) throws ClassNotFoundException {  
		try {
			long start3,end3;
			start3 = System.currentTimeMillis();			
			Thread3 mTh1 = new Thread3("Delete");
			Thread4 mTh2 = new Thread4("Querry");
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


class Thread3 extends Thread{
	private String name;
	
	public Thread3(String name) {
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
		String SQLdelete = "DELETE FROM movies WHERE id IN (SELECT movie_id FROM ratings WHERE rating > 6.0) AND year IN (2016, 2017);";
							 
		
		//ResultSet rs2 = null;
		Connection conn2 = null;
		Statement stat2 = null;
		
		try {
			// connect to database
			conn2 = this.connect();
			if(conn2 == null)
				return;
			
			// set antocommit to false
			conn2.setAutoCommit(false);	
			conn2.setTransactionIsolation(conn2.TRANSACTION_READ_UNCOMMITTED);
			
			stat2 = conn2.createStatement();
			stat2.executeUpdate(SQLdelete);
		
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


class Thread4 extends Thread{
	private String name;
	
	public Thread4(String name) {
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
			conn2.setTransactionIsolation(conn2.TRANSACTION_READ_UNCOMMITTED);
			
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