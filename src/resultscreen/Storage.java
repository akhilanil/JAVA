package resultscreen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import connection.Connect_db;

public class Storage {
	
	int counter;
	String testname[];
	String unit[];
	String result[];
	Connection conn;
	int limit,id;
	String cid,rid;
	ResultSet Rs2;
		
		
	public Storage(String id, JTextField[] fieldarray, JLabel[] labelarray,int limit) {
		counter=0;
		this.limit=limit;
		testname=new String[limit];
		unit=new String[limit];
		result=new String[limit];
		this.cid=id;
		
		for(int i=0; i<limit; i++){
			testname[i]=labelarray[2*i].getText();
			result[i]=fieldarray[i].getText();
			unit[i]=labelarray[2*i+1].getText();
		}
		
	}
	
	public void store(){
		try{
			conn=new Connect_db().connect();
			Statement Stat1=conn.createStatement();
			ResultSet Rs1=Stat1.executeQuery("select count (resultid) from Result_customer");
			Rs1.next();
			
			if(Rs1.getInt(1)==0){
				id=1;
				rid= "R" + Integer.toString(id);
			}
			else{
				id=Rs1.getInt(1)+1;
				rid= "R" + Integer.toString(id);
			}

			PreparedStatement ps;
			ps=conn.prepareStatement("insert into Result_customer (customerid,resultid) values (?,?)");
			ps.setString(1, cid);
			ps.setString(2, rid);
			
			ps.executeUpdate();
			
			String query1="insert into Result (resultid,testname,result,refrange,date_and_time,unit) values(?,?,?,?,?,?)";
			for(int i=0; i<limit; i++){
				ps=conn.prepareStatement(query1);
				ps.setString(1,rid);
				ps.setString(2, testname[i]);
				ps.setString(3, result[i]);
				Rs2=Stat1.executeQuery("Select ref_range from Test_sub where sub_testname='"+testname[i].substring(0,testname[i].length()-2)+"'");
				Rs2.next();
				ps.setString(4, Rs2.getString(1));
				ps.setTimestamp(5, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
				ps.setString(6, unit[i]);
				ps.executeUpdate();
			}
		}catch(Exception e){
			try{
				Statement Stat1=conn.createStatement();
				Stat1.executeUpdate("delete from Result where resultid=(select resultid from Result_customer where customerid='"+cid+"'");
				Stat1.executeUpdate("delete from Result_customer where customerid='"+cid+"'");
				JOptionPane.showMessageDialog(null, "Error In connection..!!", "ERROR..!!", JOptionPane.ERROR_MESSAGE);
			}catch(Exception e1){e1.printStackTrace();}
			e.printStackTrace();
		}
	}
}
