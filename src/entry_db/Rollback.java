package entry_db;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connection.Connect_db;
public class Rollback {
	Rollback(String id){
		try{
			
			Connect_db conn= new Connect_db();
			Connection blood_db=conn.connect();
			Statement Stat1=blood_db.createStatement();
			String query1="delete from Customer where customer_id= '" +id +"'";
			String query2="delete from Test_customer where customer_id= '" +id +"'";
			Stat1.executeUpdate(query2);
			Stat1.executeUpdate(query1);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
		}
	}
}
