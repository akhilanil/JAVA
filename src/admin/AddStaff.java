package admin;
/*a simple comment*/
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connection.Connect_db;

public class AddStaff implements Runnable {

	Thread t;
	JFrame frame;
	JPanel panel;
	JLabel name,password,mail,phint,uname,address;
	JTextField name1,password1,mail1,phint1,uname1,address1;
	JButton enter;
	GridBagConstraints gbc;
	Connection conn;
	public AddStaff(){
		t=new Thread(this,"Staff");
		t.start();
	}
	
	public void run() {
		frame= new JFrame("User Accounts");
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		
		gbc=new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		
		name=new JLabel("Name: ");
		password=new JLabel("Password: ");
		mail=new JLabel("Email id: ");
		phint= new JLabel("Password Hint: ");
		uname=new JLabel("User name: ");
		address=new JLabel("Address: ");
		
		name1=new JTextField(30);
		password1=new JTextField(30);
		mail1=new JTextField(30);
		phint1= new JTextField(30);
		uname1=new JTextField(30);
		address1=new JTextField(30);
		
		enter=new JButton("Enter");
		
		gbc.gridx=0;
		gbc.gridy=0;
		panel.add(name,gbc);

		gbc.gridx=1;
		gbc.gridy=0;
		panel.add(name1,gbc);

		gbc.gridx=0;
		gbc.gridy=1;
		panel.add(address,gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		panel.add(address1,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		panel.add(mail,gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		panel.add(mail1,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		panel.add(uname,gbc);
		
		gbc.gridx=1;
		gbc.gridy=3;
		panel.add(uname1,gbc);
		
		gbc.gridx=0;
		gbc.gridy=4;
		panel.add(password,gbc);
		
		gbc.gridx=1;
		gbc.gridy=4;
		panel.add(password1,gbc);
		
		gbc.gridx=0;
		gbc.gridy=5;
		panel.add(phint,gbc);
		
		gbc.gridx=1;
		gbc.gridy=5;
		panel.add(phint1,gbc);
		
		gbc.gridx=1;
		gbc.gridy=6;
		panel.add(enter,gbc);
		
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int choice=JOptionPane.showConfirmDialog(frame, "Are You Sure you want to Confirm..??", "CONFORMATION", JOptionPane.YES_NO_OPTION);
				if(choice==JOptionPane.YES_OPTION){
					if(check()==false){
						JOptionPane.showMessageDialog(frame, "Fileds Left Blank..!!", "Error..!!", JOptionPane.ERROR_MESSAGE);
					}
					else{
						try {
							conn=new Connect_db().connect();
							Statement stat=conn.createStatement();
							ResultSet rs=stat.executeQuery("select count(id) from staff");
							rs.next();
							int id=rs.getInt(1);
							String query="insert into staff(name,password,emailid,hint,id,uname,address) values(?,?,?,?,?,?,?)";
							PreparedStatement ps=conn.prepareStatement(query);
							ps.setString(1, name1.getText().toUpperCase());
							ps.setString(2,password1.getText());
							ps.setString(3,mail1.getText().toLowerCase());
							ps.setString(4,phint1.getText());
							ps.setString(5,"S"+Integer.toString(id));
							ps.setString(6,uname1.getText().toLowerCase());
							ps.setString(7, address1.getText().toUpperCase());
							ps.executeUpdate();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getLocalizedMessage(), e1.getClass().getName(), JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						frame.dispose();
					}
				}
				
			}
			
		});
		
		frame.add(panel);
		
		frame.validate();
		
	}
	
	private boolean check(){
		
		boolean verify=false;
		if(name1.getText().equals("")){return verify;}
		else if(password1.getText().equals("")){return verify;}
		else if(mail1.getText().equals("")){return verify;}
		else if(phint1.getText().equals("")){return verify;}
		else if(uname1.getText().equals("")){return verify;}
		else if(address1.getText().equals("")){return verify;}
		else{verify= true; return verify;}
		
	}
}
