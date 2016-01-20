package report;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

import javax.swing.JOptionPane;

import connection.Connect_db;

public class Validate{
	
	Connection conn;
	String id,phone,query1,query2,query3;
	
	public Validate(String id, String phone,File pdf){
		this.id=id;
		this.phone=phone;
		query1="select count(*) from customer where customer_id='"+id+"'";
		query2="select count(*) from customer where phoneno='"+phone+"' and customer_id='"+id+"'";
		query3="select count(*) from Result_customer where customerid='"+id+"'";
		try{
			conn=new Connect_db().connect();
			Statement stat=conn.createStatement();
			ResultSet Rs1=stat.executeQuery(query1);
			Rs1.next();
			
			if(Rs1.getInt(1)==1){
				ResultSet Rs2=stat.executeQuery(query2);
				Rs2.next();
				if(Rs2.getInt(1)==1){
					ResultSet Rs3=stat.executeQuery(query3);
					Rs3.next();
					if(Rs3.getInt(1)==1){
						new Print(id,pdf);
					}
					else{
						JOptionPane.showMessageDialog(null,"Sorry..!!Your Result is not yet Ready", "Error..!!",JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Phone Number Entered Incorrectly", "Error..!!",JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null,"Customerid Entered Incorrectly", "Error..!!",JOptionPane.ERROR_MESSAGE);
			}
		}catch(Exception e){e.printStackTrace();}
		
	}
}
