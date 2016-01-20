package permissions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connection.Connect_db;

public class Log implements Runnable{
	public Thread log;
	public Connection conn;
	public String id;
	public Statement stat;
	public String query;
	boolean login,logout,verify;
	public Log(String id, boolean login,boolean logout){
		log=new Thread(this,"Log");
		this.id=id;
		this.login=login;
		this.logout=logout;
		this.verify=true;
		log.start();
		
		
	}
	public void run() {
		try {
			conn=new Connect_db().connect();
			if(login==true){
				query="insert into log (staffid,logintime) values (?,?)";
				PreparedStatement ps;
				ps=conn.prepareStatement(query);
				ps.setString(1, id);
				ps.setTimestamp(2, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
				ps.executeUpdate();
			}
			else if(logout==true){
				stat=conn.createStatement();
				query="Update log set logouttime='"+java.sql.Timestamp.valueOf(java.time.LocalDateTime.now())+"' where logouttime IS NULL "
						+ "and staffid='"+id+"'";
				
				stat.executeUpdate(query);
				verify=false;
				check();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public synchronized void check(){
		try {
			if(verify==true){this.wait();}
			else{this.notify();}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
