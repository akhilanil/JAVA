package admin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connection.Connect_db;

public class RemoveStaff implements Runnable{

	Thread t;
	JFrame frame;
	JPanel panel;
	JLabel id,uname,password;
	JTextField id1,uname1,password1;
	JButton enter;
	GridBagConstraints gbc;
	Connection conn;
	
	public RemoveStaff(){
		t=new Thread(this,"Staff");
		t.start();
	}
	
	public void run() {
		frame=new JFrame("User Accounts");
		frame= new JFrame("User Accounts");
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		
		gbc=new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		
		id=new JLabel("Staff ID: ");
		uname=new JLabel("User Name: ");
		password=new JLabel("Password: ");
		
		id1=new JTextField(5);
		uname1=new JTextField(30);
		password1=new JTextField(30);
		
		enter=new JButton("Enter");
		
		gbc.gridx=0;
		gbc.gridy=0;
		panel.add(id,gbc);

		gbc.gridx=1;
		gbc.gridy=0;
		panel.add(id1,gbc);

		gbc.gridx=0;
		gbc.gridy=1;
		panel.add(uname,gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		panel.add(uname1,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		panel.add(password,gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		panel.add(password1,gbc);
		
		gbc.gridx=1;
		gbc.gridy=3;
		panel.add(enter,gbc);
		
		
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int choice=JOptionPane.showConfirmDialog(frame, "Are You Sure you want to Confirm..??", "CONFORMATION", JOptionPane.YES_NO_OPTION);
				if(choice==JOptionPane.YES_OPTION){
					if(check()==false){
						JOptionPane.showMessageDialog(frame, "Fileds Left Blank..!!", "Error..!!", JOptionPane.ERROR_MESSAGE);
					}
					else{
						try{
							conn=new Connect_db().connect();
							Statement stat=conn.createStatement();
							ResultSet rs1=stat.executeQuery("select count(*) from staff where id='"+id1.getText()+"'");
							rs1.next();
							if(rs1.getInt(1)==1){
								ResultSet rs2=stat.executeQuery("select count(*) from staff where id='"+id1.getText()+"' and uname='"+uname1.getText()+"'");
								rs2.next();
								if(rs2.getInt(1)==1){
									ResultSet rs3=stat.executeQuery("select count(*) from staff where id='"+id1.getText()+"' and password='"+password1.getText()+"'");
									rs3.next();
									if(rs3.getInt(1)==1){
										stat.executeUpdate("delete from staff where id='"+id1.getText()+"'");
										
										frame.dispose();
									}
									else{
										JOptionPane.showMessageDialog(frame, "Incorrect Password..!!", "Error..!!", JOptionPane.ERROR_MESSAGE);
									}
								}
								else{
									JOptionPane.showMessageDialog(frame, "Incorrect Username..!!", "Error..!!", JOptionPane.ERROR_MESSAGE);
								}
							}
							else{
								JOptionPane.showMessageDialog(frame, "Incorrect Staff Id..!!", "Error..!!", JOptionPane.ERROR_MESSAGE);
							}
						}catch(Exception e1){JOptionPane.showMessageDialog(null, e1.getLocalizedMessage(), e1.getClass().getName(), JOptionPane.ERROR_MESSAGE);e1.printStackTrace();}
					}
						
				}
			}
			
		});
		
		
		frame.add(panel);
		frame.validate();
		
	}
private boolean check(){
		
		boolean verify=false;
		if(uname1.getText().equals("")){return verify;}
		else if(id1.getText().equals("")){return verify;}
		else if(password1.getText().equals("")){return verify;}
		else{verify= true; return verify;}
		
	}
}
