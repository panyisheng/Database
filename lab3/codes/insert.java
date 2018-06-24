package HW3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Random;


public class insert {
    public static void main(String[] args) throws ClassNotFoundException {  
    	Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mydb";
		String user	="root";
		String password="pysxp8500723.";
		Connection conn = null;
    	
    	try {
			conn = DriverManager.getConnection(url,user,password);
			Statement stm1 = null;
			PreparedStatement pstm1 = null;
			String customerid;
			String sellerid;
			int age = 80;
			
			//String SQLdelete = "DELETE FROM customer";
			String SQLinsert = "INSERT INTO customer(account,password,name,telephone,age,sex,totalpoint,ismember) values (?,?,?,?,?,?,?,?)";
			
			String SQLinsert2 = "INSERT INTO saller(account,password,name,telephone,age,sex) values(?,?,?,?,?,?)";
			
			String SQLinsert3  = "INSERT INTO goods(goodsid,name,type) values(?,?,?)";
			
			String SQLinsert4 = "INSERT INTO store(saller_account,goods_goodsid,price,discount1,remainings,`production date`,`shelf date`) values(?,?,?,?,?,?,?)";
			
			// insert 100 customer
			conn.setAutoCommit(false);	
			// customer 1-25 is normal member
			
			for(int i = 1;i<=25;i++) {
				customerid = "customer"+ i;
				
				pstm1 = conn.prepareStatement(SQLinsert);
				pstm1.setString(1, customerid);
				pstm1.setString(2, "password");
				pstm1.setString(3, "pys");
				pstm1.setString(4, "88888888");
				pstm1.setInt(5, age);
				pstm1.setString(6, "Å®");
				pstm1.setInt(7, 25*i);
				pstm1.setInt(8, 1);
				pstm1.execute();	
				age --;
			}
			
			age = 70;
			//  customer 26-50 is gold member 
			for(int i =26;i<=50;i++) {
				customerid = "customer" + i;
				
				pstm1 = conn.prepareStatement(SQLinsert);
				pstm1.setString(1, customerid);
				pstm1.setString(2, "password");
				pstm1.setString(3, "pys");
				pstm1.setString(4, "88888888");
				pstm1.setInt(5, age);
				pstm1.setString(6, "ÄÐ");
				pstm1.setInt(7, 5*i);
				pstm1.setInt(8, 1);
				pstm1.execute();
				
				age -- ;
			}
			
			
			age = 40;
			// customer 51-75 is bojin menber
			for(int i =51;i<=75;i++) {
				customerid = "customer" + i;
				
				pstm1 = conn.prepareStatement(SQLinsert);
				pstm1.setString(1, customerid);
				pstm1.setString(2, "password");
				pstm1.setString(3, "pys");
				pstm1.setString(4, "88888888");
				pstm1.setInt(5, age);
				pstm1.setString(6, "Å®");
				pstm1.setInt(7, 10*i);
				pstm1.setInt(8, 1);
				pstm1.execute();
				age --;
			}
			
			age =  60;
			// customer 76-100 is not member
			for(int i =76;i<=100;i++) {
				customerid = "customer" + i;
				
				pstm1 = conn.prepareStatement(SQLinsert);
				pstm1.setString(1, customerid);
				pstm1.setString(2, "password");
				pstm1.setString(3, "pys");
				pstm1.setString(4, "88888888");
				pstm1.setInt(5, age);
				pstm1.setString(6, "ÄÐ");
				pstm1.setNull(7, java.sql.Types.INTEGER); 
				pstm1.setInt(8, 0);
				pstm1.execute();
				age --;
			}
			
			
			// insert 10 seller information
			String sallerid;
			PreparedStatement pstm2 = null;
			for(int i = 1;i<=10;i++) {
				sallerid = "seller" + i;
				
				pstm2 = conn.prepareStatement(SQLinsert2);
				pstm2.setString(1, sallerid);
				pstm2.setString(2, "password");
				pstm2.setString(3, "pys");
				pstm2.setString(4, "88888888");
				pstm2.setInt(5, 18);
				pstm2.setString(6, "ÄÐ");
				pstm2.execute();
			}
			
			/* insert 16 goods information 
			*/
			
			String goodsid;
			String goodsname;
			PreparedStatement pstm3 = null;
			
			//int seller_count = 1;
			int goods_count = 1;
			
			for(int seller_count = 1;seller_count<=10;seller_count++) {
			for(int i = 1;i<=4;i++) {
				goodsid = "goods" + goods_count;
				goodsname ="seller"+seller_count+ "_cloth_" + i;
				pstm3 = conn.prepareStatement(SQLinsert3);
				pstm3.setString(1, goodsid);
				pstm3.setString(2, goodsname);
				pstm3.setString(3, "0001");
				pstm3.execute();
				goods_count ++ ;
			}
			for(int i = 1;i<=4;i++) {
				goodsid = "goods" + goods_count;
				goodsname ="seller"+seller_count+ "_food_" + i;
				pstm3 = conn.prepareStatement(SQLinsert3);
				pstm3.setString(1, goodsid);
				pstm3.setString(2, goodsname);
				pstm3.setString(3, "0010");
				pstm3.execute();
				goods_count ++ ;
			}
			for(int i = 1;i<=4;i++) {
				goodsid = "goods" + goods_count;
				goodsname ="seller"+seller_count+ "_play_" + i;
				pstm3 = conn.prepareStatement(SQLinsert3);
				pstm3.setString(1, goodsid);
				pstm3.setString(2, goodsname);
				pstm3.setString(3, "0100");
				pstm3.execute();
				goods_count ++ ;
			}
			for(int i = 1;i<=4;i++) {
				goodsid = "goods" + goods_count;
				goodsname ="seller"+seller_count+ "_luxury_" + i;
				pstm3 = conn.prepareStatement(SQLinsert3);
				pstm3.setString(1, goodsid);
				pstm3.setString(2, goodsname);
				pstm3.setString(3, "1000");
				pstm3.execute();
				goods_count ++ ;
			}	
			}
			
			// insert store information of all seller
			goods_count = 1;
			int goods_price;
			double goods_discount;
			PreparedStatement pstm4 = null;
			Random random,random2 ;
			
			
			for(int count=1;count<=10;count++) {
				for(int j=1;j<=16;j++) {
					goodsid = "goods" + goods_count;
					sellerid = "seller" + count;
					random = new Random();
					random2 = new Random();
					goods_price = random.nextInt(300)%(300-50+1) + 50;
					goods_discount = random2.nextDouble()/2+0.5;
					
					pstm4 = conn.prepareStatement(SQLinsert4);
					pstm4.setString(1, sellerid);
					pstm4.setString(2, goodsid);
					pstm4.setInt(3, goods_price);
					pstm4.setDouble(4, goods_discount);
					pstm4.setInt(5, 10);
					pstm4.setString(6, "2017-09-26");
					pstm4.setString(7, "2019-09-26");
					pstm4.execute();
					goods_count++;
				}
					
			}
			
			conn.commit();
			conn.setAutoCommit(true);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
  
        
    }
}
