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

public class EditStaff implements Runnable{

	Thread t;
	JFrame frame;
	JPanel panel;
	JLabel name,password,mail,phint,uname,address,id;
	JTextField name1,password1,mail1,phint1,uname1,address1,id1;
	JButton enter,confirm;
	GridBagConstraints gbc;
	Connection conn;
	int flag;
	String[] queryset;
	public EditStaff(){
		t=new Thread(this,"Edit");
		this.flag=0;
		t.start();
	}
	public void run() {
	
		frame=new JFrame("User Accounts");
		frame= new JFrame("User Accounts");
		frame.setVisible(true);
		frame.setSize(550, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		
		gbc=new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		
		gbc=new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		
		id=new JLabel("Enter ID: ");
		name=new JLabel("Name: ");
		password=new JLabel("Password: ");
		mail=new JLabel("Email id: ");
		phint= new JLabel("Password Hint: ");
		uname=new JLabel("User name: ");
		address=new JLabel("Address: ");
		
		id1=new JTextField(15);
		name1=new JTextField(30);
		password1=new JTextField(30);
		mail1=new JTextField(30);
		phint1= new JTextField(30);
		uname1=new JTextField(30);
		address1=new JTextField(30);
		
		confirm=new JButton("Confirm");
		enter=new JButton("Enter");
		
		gbc.gridx=0;
		gbc.gridy=0;
		panel.add(id,gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		panel.add(id1,gbc);
		
		gbc.gridx=2;
		gbc.gridy=0;
		gbc.weightx=0.1;
		panel.add(enter,gbc);
		gbc.weightx=0.0;
		
		gbc.gridx=0;
		gbc.gridy=1;
		panel.add(name,gbc);

		gbc.gridx=1;
		gbc.gridy=1;
		panel.add(name1,gbc);

		gbc.gridx=0;
		gbc.gridy=2;
		panel.add(address,gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		panel.add(address1,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		panel.add(mail,gbc);
		
		gbc.gridx=1;
		gbc.gridy=3;
		panel.add(mail1,gbc);
		
		gbc.gridx=0;
		gbc.gridy=4;
		panel.add(uname,gbc);
		
		gbc.gridx=1;
		gbc.gridy=4;
		panel.add(uname1,gbc);
		
		gbc.gridx=0;
		gbc.gridy=5;
		panel.add(password,gbc);
		
		gbc.gridx=1;
		gbc.gridy=5;
		panel.add(password1,gbc);
		
		gbc.gridx=0;
		gbc.gridy=6;
		panel.add(phint,gbc);
		
		gbc.gridx=1;
		gbc.gridy=6;
		panel.add(phint1,gbc);
		
		gbc.gridx=1;
		gbc.gridy=7;
		panel.add(confirm,gbc);
		
		confirm.setEnabled(false);
		
		
		enter.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try{
					conn=new Connect_db().connect();
					Statement stat=conn.createStatement();
					ResultSet rs1=stat.executeQuery("select count(*) from staff where id='"+id1.getText()+"'");
					rs1.next();
					if(rs1.getInt(1)==1){
						setQuery(id1.getText());
						ResultSet rs;
						for(int i=0; i<6; i++){
							rs=stat.executeQuery(getQuery(i));
							rs.next();
							setField(rs.getString(1),i);
						}
						confirm.setEnabled(true);
					}
					else{
						JOptionPane.showMessageDialog(null, "Incorrect ID", "Error..!!", JOptionPane.ERROR_MESSAGE);
					}
						
				}catch(Exception e1){JOptionPane.showMessageDialog(null, e1.getLocalizedMessage(), e1.getClass().getName(), JOptionPane.ERROR_MESSAGE);e1.printStackTrace();}
			}
			
		});
		
		confirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
					int choice=JOptionPane.showConfirmDialog(frame, "Are You Sure You Want To Confirm..??", "CONFIRMATIION", JOptionPane.YES_NO_OPTION);
					if(choice==JOptionPane.YES_OPTION){
						conn=new Connect_db().connect();
						Statement stat=conn.createStatement();
						String query="update Staff set name='"+name1.getText()+"',password='"+password1.getText()+"',emailid='"+mail1.getText()+"',uname='"+uname1.getText()+"',address='"+address1.getText()+"' where id='"+id1.getText()+"'";
						stat.executeUpdate(query);
						frame.dispose();
					}
				}catch (Exception e1){JOptionPane.showMessageDialog(null, e1.getLocalizedMessage(), e1.getClass().getName(), JOptionPane.ERROR_MESSAGE);e1.printStackTrace();}
			}
			
		});
		
		
		frame.add(panel);
		frame.validate();
		
	}
	
	private void setQuery(String id){
		queryset=new String[6];
		queryset[0]="select name from staff where id='"+id+"'";
		queryset[1]="select address from staff where id='"+id+"'";
		queryset[2]="select emailid from staff where id='"+id+"'";
		queryset[3]="select uname from staff where id='"+id+"'";
		queryset[4]="select password from staff where id='"+id+"'";
		queryset[5]="select hint from staff where id='"+id+"'";
	}
	
	private String getQuery(int index){
		
		return queryset[index];
		
	}
	
	private void setField(String value, int index){
		if(index==0){name1.setText(value);}
		else if(index==1){address1.setText(value);}
		else if(index==2){mail1.setText(value);}
		else if(index==3){uname1.setText(value);}
		else if(index==4){password1.setText(value);}
		else if(index==5){phint1.setText(value);}
	}
}
