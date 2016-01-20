package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.Connect_db;

public class EntrySub implements Runnable{

	Connection conn;
	Thread t;
	String code,name,charge,unit,ref;
	public EntrySub(String code,String name,String charge,String unit, String ref){
		t=new Thread(this,"Sub DB");
		this.code=code;
		this.name=name;
		this.charge=charge;
		this.unit=unit;
		this.ref=ref;
		t.start();
		
	}
	
	public void run() {
		try{
			conn=new Connect_db().connect();
			PreparedStatement ps=conn.prepareStatement("insert into Test_sub values(?,?,?,?,?,?)");
			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setInt(2,Integer.parseInt(code));
			ps.setString(3, name);
			if(charge.equals("")){ps.setNull(4, java.sql.Types.INTEGER);}
			else{ps.setInt(4, Integer.parseInt(charge));}
			ps.setString(5, unit);
			ps.setString(6, ref);
			ps.executeUpdate();
			
		}catch(Exception e){e.printStackTrace();}
	}

}
