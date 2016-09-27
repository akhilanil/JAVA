package connection;
import java.sql.*;

public class Connect_db{
	public Connection connect() throws Exception{
		Connection blood_db=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Master;user=sa;password=password");
		return blood_db;
	}
}
