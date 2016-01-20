package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import connection.Connect_db;

public class EntryMain implements Runnable {
	
	Connection conn;
	Thread t;
	String[] test;
	String code,name,charge;
	public EntryMain(String code,String name, String charge, String[] test){
		t=new Thread(this,"Main DB");
		t.start();
		this.test=test;
		this.code=code;
		this.name=name;
		this.charge=charge;
		
	}
	
	public void run() {
		try{
			conn=new Connect_db().connect();
			PreparedStatement ps= conn.prepareStatement("insert into Test_main values(?,?,?)");
			ps.setInt(1, Integer.parseInt(code));
			ps.setString(2, name);
			ps.setInt(3, Integer.parseInt(charge));
			ps.executeUpdate();
			
			for(int i=0; i<test.length; i++){
				ps=conn.prepareStatement("update Test_sub set main_testcode= ? where sub_testname = ?");
				ps.setInt(1, Integer.parseInt(code));
				ps.setString(2, test[i]);
				ps.executeUpdate();
			}
			
		}catch(Exception e){e.printStackTrace();JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);}
	}
}
