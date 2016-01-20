package entry_db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import connection.Connect_db;
public class Store {
	int Customer_id;
	String id;
	DateFormat dateformat= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Connect_db conn= new Connect_db();
	DateFormat formatter = null;
	public Store(String name, String age, String gender, String phone, String doc, String[] test_name, int size,int type) {//type=0 for individual test and type=1 for group test
		try{
			Connection blood_db=conn.connect();
			Statement Stat1=blood_db.createStatement();
			
			ResultSet Rs1=Stat1.executeQuery("select count(customer_id) from customer");
			Rs1.next();
			
			if(Rs1.getInt(1)==0){
				Customer_id=1;
				id= "AAJ" + Integer.toString(Customer_id);
			}
			else{
				Customer_id=Rs1.getInt(1)+1;
				id= "AAJ" + Integer.toString(Customer_id);
			}
			
			String query1="insert into Customer(customer_id,name,phoneno,doc_reference,age,gender,date_and_time) values(?,?,?,?,?,?,?)";
			PreparedStatement ps;
			ps=blood_db.prepareStatement(query1);
			ps.setString(1, id);
			ps.setString(2,name);
			ps.setString(3,phone );
			ps.setInt(5,Integer.valueOf(age));
			ps.setString(6, gender.toUpperCase());
			ps.setTimestamp(7, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
			ps.setString(4, doc);
			ps.executeUpdate();
			
			if(type==0){
				//USE THREAD HERE>>>>>
				TestnameThread individual= new TestnameThread(test_name,id);
				try{
					individual.t.join();
					String query2="Update Customer set price = (select sum(charge) from Test_sub s,Test_customer t"  
								  +" where t.customer_id= '"+id+"' and t.testcode=s.sub_testcode)"
								  +" where  customer_id='"+id+"'";
					Stat1.executeUpdate(query2);
					String query6="select price from Customer c "
							 +"where c.customer_id='"+id+"'" ;
					ResultSet Rs4= Stat1.executeQuery(query6);
					Rs4.next();
					int price=Rs4.getInt(1);
					String query7="select age from Customer c "
							 +"where c.customer_id='"+id+"'" ;
					ResultSet Rs5=Stat1.executeQuery(query7);
					Rs5.next();
					int check_age=Integer.parseInt(Rs5.getString(1));
					int dprice=0;
					if(check_age>=60){
						dprice=(price*(5))/100;
					}
					dprice=price-dprice;
					String query8="Update Customer set discount ="+dprice+" where customer_id='"+id+"'";
					Stat1.executeUpdate(query8);
					new CreateBill(id,0);
					}catch(Exception e){
						//executing roll back;
						
						new Rollback(id);
						e.printStackTrace();
					}
				}
				else{
						int s=0;
					for(int i=0; i<=size; i++){
					String query3="select count(sub_testname) as number from Test_sub s, Test_main m "
							+ "where s.main_testcode=m.main_testcode and m.main_testname='" +test_name[i]+"'";
					
					ResultSet Rs2= Stat1.executeQuery(query3);
					Rs2.next();
					s+=Rs2.getInt(1);
					}
					int n=size;
					size=0;
					String[] testname= new String[s];
					
					/*testname is a new array to store sub_names of main_test 
					since size of the previous array is not sufficient to hold all the sub_test name*/ 
					for(int i=0; i<=n; i++){
						
					String query4="select sub_testname from Test_sub s, Test_main m "
							+ "where s.main_testcode=m.main_testcode and m.main_testname='" +test_name[i]+"'";
					ResultSet Rs3= Stat1.executeQuery(query4);
						while(Rs3.next()){
							String Result= Rs3.getString("sub_testname");
							testname[size]=Result;
							size++;
						}
					}
					TestnameThread group= new TestnameThread(testname,id);
					int price=0;
					try{
						
						group.t.join();
						for(int i=0; i<=n; i++){
						String query="select main_charge from Test_main"
								+" where main_testname='"+test_name[i]+"'";
						ResultSet Rs= Stat1.executeQuery(query);
						Rs.next();
						price+=Rs.getInt(1);
								 
						}
						String query5="Update Customer set price ="+price+" where  customer_id='"+id+"'";// Insert into Customer from Test main
						Stat1.executeUpdate(query5);
						String query8="select price from Customer c "
								      +"where c.customer_id='"+id+"'";
						
						ResultSet Rs4= Stat1.executeQuery(query8);
						Rs4.next();
						price=Rs4.getInt(1);
						String query9="select age from Customer c "
								 +"where c.customer_id='"+id+"'";
						ResultSet Rs5=Stat1.executeQuery(query9);
						Rs5.next();
						int check_age=Integer.parseInt(Rs5.getString(1));
						int dprice=0;
						if(check_age>=60){
							dprice=(price*(5))/100;
						}
						dprice=price-dprice;
						String query10="Update Customer set discount = "+dprice+"  where customer_id='"+id+"'";
						Stat1.executeUpdate(query10);
						new CreateBill(id,1);
						}catch(Exception e){
							//executing roll back;
							new Rollback(id);
							e.printStackTrace();
						}
				}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}