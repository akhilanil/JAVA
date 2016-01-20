package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.eclipse.jdt.internal.compiler.batch.Main;

import connection.Connect_db;


public class Design {
	JFrame frame;
	JButton button;
	JPanel top_panel,base_panel;
	JLabel custreport,staffreport,logreport,edit,remove,add,test;
	JTextField field;
	Image imagetop,imagebase;
	JSplitPane divider;
	GridBagConstraints gbc;
	String documents;
	Connection conn;
	public Design(){
		documents=new JFileChooser().getFileSystemView().getDefaultDirectory().toString()+"\\Blood Test";
		try{
			File fileimg=new File(documents+"\\Logo.jpg");
			File filesig=new File(documents+"\\Sgnature.jpg");
			if(!fileimg.exists()){Files.copy(Main.class.getResourceAsStream("/reports/Logo.jpg"),new File(documents+"\\Logo.jpg").toPath());}
			if(!filesig.exists()){Files.copy(Main.class.getResourceAsStream("/reports/Sgnature.jpg"),new File(documents+"\\Sgnature.jpg").toPath());}
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage()+"\n Rolled Back", e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
		}
		try {
			conn= new Connect_db().connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage()+"\n Rolled Back", e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
		}
		frame=new Window().create_window();
		imagebase= new ImageIcon(Main.class.getResource("/images/admin.jpg")).getImage();
		
		designtop();
		frame.add(top_panel);
		
		designbase();
		frame.add(base_panel);
		
		setdivider();
		frame.add(divider);
		frame.validate();
		
	}
	
	private void designtop(){top_panel=new DrawTop();}
	
	
	private void designbase(){
		
		base_panel=new DrawBase();
		base_panel.setLayout(new GridBagLayout());
		
		custreport=new JLabel("Click here to view Customer details...!!");
		staffreport=new JLabel("Click here to view Staff details..!!");
		logreport=new JLabel("Click here to view login details..!!");
		add=new JLabel("Click here to add a Staff..!!");
		remove=new JLabel("Click here to remove a Staff..!!");
		edit=new JLabel("Click here to edit a Staff's Detail..!!");
		test=new JLabel("Click here to add new test..!!");
		
		
		
		
		gbc=new GridBagConstraints();

		gbc.insets= new Insets(10,10,10,10);

		custreport.setFont(new Font("Times New Roman",Font.PLAIN,20));
		staffreport.setFont(new Font("Times New Roman",Font.PLAIN,20));
		logreport.setFont(new Font("Times New Roman",Font.PLAIN,20));
		add.setFont(new Font("Times New Roman",Font.PLAIN,20));
		remove.setFont(new Font("Times New Roman",Font.PLAIN,20));
		edit.setFont(new Font("Times New Roman",Font.PLAIN,20));
		test.setFont(new Font("Times New Roman",Font.PLAIN,20));
	
		custreport.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				try{
					JasperDesign jd=JRXmlLoader.load(Main.class.getResourceAsStream("/reports/Customer_Details.jrxml"));
					JasperCompileManager.compileReportToFile(jd,documents+"\\Customer_Details.jasper");
					JasperPrint jp=JasperFillManager.fillReport(documents+"\\Customer_Details.jasper",null,conn);
					JasperViewer.viewReport(jp,false);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getLocalizedMessage()+"\n Rolled Back", e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();}
				
			}
			public void mouseEntered(MouseEvent arg0) {labelgraphics(custreport, 1);}
			public void mouseExited(MouseEvent arg0) {labelgraphics(custreport, 0);}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		staffreport.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				try{
					JasperDesign jd=JRXmlLoader.load(Main.class.getResourceAsStream("/reports/Staff_Details.jrxml"));
					JasperCompileManager.compileReportToFile(jd,documents+"\\Staff_Details.jasper");
					JasperPrint jp=JasperFillManager.fillReport(documents+"\\Staff_Details.jasper",null,conn);
					JasperViewer.viewReport(jp,false);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getLocalizedMessage()+"\n Rolled Back", e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();}
			}
			public void mouseEntered(MouseEvent arg0) {labelgraphics(staffreport, 1);}
			public void mouseExited(MouseEvent arg0) {labelgraphics(staffreport, 0);}
			
		});
		
		logreport.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				try{
					JasperDesign jd=JRXmlLoader.load(Main.class.getResourceAsStream("/reports/Log.jrxml"));
					JasperCompileManager.compileReportToFile(jd,documents+"\\Log.jasper");
					JasperPrint jp=JasperFillManager.fillReport(documents+"\\Log.jasper",null,conn);
					JasperViewer.viewReport(jp,false);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getLocalizedMessage()+"\n Rolled Back", e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();}
				
			}
			public void mouseEntered(MouseEvent arg0) {labelgraphics(logreport, 1);}
			public void mouseExited(MouseEvent arg0) {labelgraphics(logreport, 0);}
		});
		
		add.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				AddStaff as=new AddStaff();
				try{as.t.join();}catch(Exception e1){JOptionPane.showMessageDialog(null, e1.getLocalizedMessage(), e1.getClass().getName(), JOptionPane.ERROR_MESSAGE);e1.printStackTrace();}
			}
			public void mouseEntered(MouseEvent arg0) {labelgraphics(add, 1);}
			public void mouseExited(MouseEvent arg0) {labelgraphics(add, 0);}
			
		});
		
		edit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				EditStaff es=new EditStaff();
				try{es.t.join();}catch(Exception e1){JOptionPane.showMessageDialog(null, e1.getLocalizedMessage(), e1.getClass().getName(), JOptionPane.ERROR_MESSAGE);e1.printStackTrace();}
			}
			public void mouseEntered(MouseEvent arg0) {labelgraphics(edit, 1);}
			public void mouseExited(MouseEvent arg0) {labelgraphics(edit, 0);}
			
		});
		
		remove.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				RemoveStaff rs=new RemoveStaff();
				try{rs.t.join();}catch(Exception e1){JOptionPane.showMessageDialog(null, e1.getLocalizedMessage(), e1.getClass().getName(), JOptionPane.ERROR_MESSAGE);e1.printStackTrace();}
			}
			public void mouseEntered(MouseEvent arg0) {labelgraphics(remove, 1);}
			public void mouseExited(MouseEvent arg0) {labelgraphics(remove, 0);}
			
		});
		
		test.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				NewTest nt= new NewTest();
				try{nt.t.join();}catch(Exception e1){JOptionPane.showMessageDialog(null, e1.getLocalizedMessage(), e1.getClass().getName(), JOptionPane.ERROR_MESSAGE);e1.printStackTrace();}
			}
			public void mouseEntered(MouseEvent arg0) {labelgraphics(test, 1);}
			public void mouseExited(MouseEvent arg0) {labelgraphics(test, 0);}
		});
			
		
		gbc.gridx=0;
		gbc.gridy=0;
		base_panel.add(custreport, gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		base_panel.add(staffreport, gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		base_panel.add(logreport, gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		base_panel.add(add, gbc);
		
		gbc.gridx=0;
		gbc.gridy=4;
		base_panel.add(remove, gbc);
		
		gbc.gridx=0;
		gbc.gridy=5;
		base_panel.add(edit, gbc);
		
		gbc.gridx=0;
		gbc.gridy=6;
		base_panel.add(test, gbc);
		
		
		
	}
	
	private void setdivider(){
		divider=new JSplitPane(JSplitPane.VERTICAL_SPLIT);//Splitting horizontally :P
		
		divider.setRightComponent(base_panel);//bottom panel
		divider.setLeftComponent(top_panel);//top panel which dosen't change
		
		divider.setDividerLocation(200);
		divider.setBackground(Color.WHITE);
		divider.setDividerSize(1);
	}
	
	
@SuppressWarnings("serial")
private class DrawTop extends JPanel {

		public void paintComponent(Graphics g) {
			Image imagetop= new ImageIcon(Main.class.getResource("/images/top.jpg")).getImage();
	        super.paintComponent(g);

	        g.drawImage(imagetop, 0, 0, getWidth(), getHeight(), this);
			
	    }
	}
	
	@SuppressWarnings("serial")
	private class DrawBase extends JPanel {
		
		public void paintComponent(Graphics g) {
	        g.drawImage(imagebase, 0, 0, getWidth(), getHeight(), this);
	    }
	}
	
	
	public void labelgraphics(JLabel label, int state){
		
		if(state==1){
			label.setFont(new Font("monotype corsiva",Font.ITALIC,30));
			label.setForeground(Color.RED);
		}
		if (state==0){
			label.setFont(new Font("Times New Roman",Font.PLAIN,20));
			label.setForeground(Color.BLACK);
		}
	}
}
