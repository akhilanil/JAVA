package entry_db;

import java.sql.Connection;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.eclipse.jdt.internal.compiler.batch.Main;

import connection.Connect_db;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class CreateBill {
	String documents;
	public CreateBill(String id,int choose) {
		documents=new JFileChooser().getFileSystemView().getDefaultDirectory().toString()+"\\Blood Test";
			
		try{
				Connection conn= new Connect_db().connect();
				
				if(choose==0){
					try{
						JasperDesign jd=JRXmlLoader.load(Main.class.getResourceAsStream("/reports/Bill_Sub.jrxml"));
						String query="select *  from Customer c ,Test_customer t,Test_sub s "
									  +"where c.customer_id='"+id+"' and c.customer_id= t.customer_id and  t.testcode=s.sub_testcode";
						JRDesignQuery myQuery=new JRDesignQuery(); 
						myQuery.setText(query);
						jd.setQuery(myQuery);
						JasperCompileManager.compileReportToFile(jd,documents+"\\Bill_Sub.jasper");
						JasperPrint jp=JasperFillManager.fillReport(documents+"\\Bill_Sub.jasper",null,conn);
						//JasperPrint jp=JasperFillManager.fillReport(JasperCompileManager.compileReport(jd),null,conn);
						JasperViewer.viewReport(jp,false);
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, e.getLocalizedMessage()+"\n Rolled Back", e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
						new Rollback(id);
						e.printStackTrace();
					}
				}
				else{
					try{
						JasperDesign jd=JRXmlLoader.load(Main.class.getResourceAsStream("/reports/Bill_Main.jrxml"));
						String query="select  distinct c.customer_id, name, main_testname,discount,age,gender,phoneno,main_charge,price "
								+ "from Test_main m, Test_customer t, Test_sub s,Customer c "
								+ "where  t.customer_id=c.customer_id and  t.testcode=s.sub_testcode and s.main_testcode=m.main_testcode and c.customer_id='"+id+"'";
						JRDesignQuery myQuery=new JRDesignQuery(); 
						myQuery.setText(query);
						jd.setQuery(myQuery);
						JasperCompileManager.compileReportToFile(jd,documents+"\\Bill_Main.jasper");
						JasperPrint jp=JasperFillManager.fillReport(documents+"\\Bill_Main.jasper",null,conn);
						JasperViewer.viewReport(jp,false);
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, e.getLocalizedMessage()+"\n Rolled Back", e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
						new Rollback(id);
						e.printStackTrace();
					}

				}
			}catch(Exception e){
				new Rollback(id);
				JOptionPane.showMessageDialog(null, e.getLocalizedMessage()+"\n Rolled Back", e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}

}
