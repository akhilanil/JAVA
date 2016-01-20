package entry_db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connection.Connect_db;

public class TestnameThread implements Runnable {
	
	Thread t;
	String [] test_name;
	int size;
	String id;
	TestnameThread(String[] test_name, /*int size*/String id){
		t=new Thread(this,"Testname");
		this.size=test_name.length;
		this.test_name=test_name;
		this.id=id;
		t.start();
	}
	public void run(){
		Connect_db conn= new Connect_db();
		try {
				Connection blood_db=conn.connect();
				Statement Stat1=blood_db.createStatement();
				for(int i=0; i<size; i++){
					String query1="select sub_testcode from Test_sub where sub_testname='"+test_name[i]+"'";
					ResultSet Rs1=Stat1.executeQuery(query1);
					Rs1.next();
					String query2="insert into Test_customer(customer_id,testcode) values(?,?)";
					PreparedStatement ps=blood_db.prepareStatement(query2);
					ps.setString(1, id);
					ps.setInt(2, Rs1.getInt(1));
					ps.executeUpdate();
				}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage()+"\n Rolled Back", e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
			//executing roll back;
			e.printStackTrace();
			new Rollback(id);
		}
	}
}
