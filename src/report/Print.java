package report;

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.eclipse.jdt.internal.compiler.batch.Main;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import connection.Connect_db;

public class Print {
	String id;
	String documents;
	File pdf;
	public Print(String id,File pdf){
		this.id=id;
		this.pdf=pdf;
		documents=new JFileChooser().getFileSystemView().getDefaultDirectory().toString()+"\\Blood Test";
		try{
			Connection conn= new Connect_db().connect();
			DefaultJasperReportsContext context = DefaultJasperReportsContext.getInstance();
			JRPropertiesUtil.getInstance(context).setProperty("net.sf.jasperreports.xpath.executer.factory",
			    "net.sf.jasperreports.engine.util.xml.JaxenXPathExecuterFactory");
			JasperDesign jd=JRXmlLoader.load(Main.class.getResourceAsStream("/reports/Result.jrxml"));
			String query="select customer_id,testname,result,refrange,"
					+ "customer.date_and_time as cdate,result.date_and_time as rdate,name,doc_reference,"
					+"age,gender,Result.resultid,Result.unit from Result_customer,Customer,Result where customer_id='"+this.id+
					"'and result.resultid=Result_customer.resultid and customer_id=customerid";
			JRDesignQuery myQuery=new JRDesignQuery(); 
			myQuery.setText(query);
			jd.setQuery(myQuery);
			JasperCompileManager.compileReportToFile(jd,documents+"\\Report.jasper");
			JasperPrint jp=JasperFillManager.fillReport(documents+"\\Report.jasper",null,conn);
			JasperExportManager.exportReportToPdfFile(jp,documents+"\\print.pdf");
			Desktop.getDesktop().open(this.pdf);
			//09048499686
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage()+"\n Rolled Back", e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
