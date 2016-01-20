package admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.eclipse.jdt.internal.compiler.batch.Main;

import permissions.Log;
import connection.Connect_db;


public class Permission implements Runnable {

	public Thread t;
	JFrame frame;
	JLabel user,pass;
	JTextField user1;
	JPasswordField pass1;
	JButton enter;
	JPanel basepanel,panel;
	Connection conn;
	String name;
	Image image,icon;
	String usertext,passtext;
	boolean verify;
	ImageIcon top;
	JLabel imagelabel;
	
	public Permission(){
		verify=true;
		t=new Thread(this,"Permission");
		t.start();
	}
	
	@SuppressWarnings("serial")
	private class Design extends JPanel{
		public void paintComponent(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRoundRect(5, 5, getWidth(), getHeight(), 30, 30);
	       //g.fillRect(5, 5, 300, 500);
	    }
	} 
	
	public void run() {
		
		try {
			icon= new ImageIcon(Main.class.getResource("/images/logo.jpg")).getImage();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		frame= new JFrame();
		frame.setTitle("ADMINISTRATOR");
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(icon);
		frame.setResizable(false);
		
		usertext="Enter User name..";
		passtext="Enter Ppassword..";
		
		enter=new JButton("Log In");
		enter.setSize(new Dimension(30,30));
		enter.setFont(new Font("Seriff",Font.PLAIN,20));
		enter.setBackground(new Color(37,122,163));
		enter.setForeground(Color.WHITE);
		
		user1=new JTextField(usertext,15);
		pass1=new JPasswordField(passtext,15);
		basepanel=new JPanel();
		panel=new Design();
		
		user1.setFont(new Font("Times New Roman",Font.ITALIC,20));
		user1.setBackground(new Color(255,254,255));
		pass1.setBackground(new Color(255,254,255));
		user1.addKeyListener(new Security());
		pass1.addKeyListener(new Security());
		
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc= new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		
		user1.addFocusListener(new FocusListener(){

			public void focusGained(FocusEvent arg0) {
				if(user1.getText().equals(usertext)==true){user1.setText("");}
				
				}
			public void focusLost(FocusEvent arg0) {
				if(user1.getText().equals("")==true){user1.setText(usertext);}
			}
			
		});
		
		pass1.addFocusListener(new FocusListener(){

			public void focusGained(FocusEvent arg0) {
				if(new String(pass1.getPassword()).equals(passtext)==true){pass1.setText("");}
				
				}
			public void focusLost(FocusEvent arg0) {
				if(new String(pass1.getPassword()).equals("")==true){pass1.setText(passtext);}
			}
			
		});
		
		pass1.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					listener();
					}
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}
			
		});
		enter.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				listener();
			}
		});
		
		gbc.gridy=0;
		gbc.gridx=0;
		panel.add(user1, gbc);
		
		gbc.gridy=1;
		gbc.gridx=0;
		panel.add(pass1, gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.gridwidth=3;
		panel.add(enter, gbc);
		
		panel.setPreferredSize(new Dimension(300,200));
		panel.setMaximumSize(new Dimension(300,300));
		
		basepanel.setLayout(new GridBagLayout());
		basepanel.setBackground(new Color(33,109,149));
		
		GridBagConstraints c1= new GridBagConstraints();
		c1.insets=new Insets(20,20,20,20);
		c1.gridx=0;
		c1.gridy=0;
		imagelabel=new JLabel("ADMINISTRATOR");
		imagelabel.setFont(new Font("Bauhaus 93",Font.BOLD,29));
		imagelabel.setForeground(Color.WHITE);
		
		basepanel.add(imagelabel, c1);
		
		c1.gridy=1;
	    basepanel.add(panel,c1);
		frame.add(basepanel);
		frame.validate();
	}
	
	protected void listener() {
		if(user1.getText().equals(usertext) || new String(pass1.getPassword()).equals(passtext)){
			JOptionPane.showMessageDialog(null,"Field Left Blank","Error..!!",JOptionPane.ERROR_MESSAGE);
		}
		else{
			
			String queryname="select count(*) from Staff where uname='"+user1.getText()+"' and name= 'administrator'";
			String querypassword="select count(*) from Staff where uname='"+user1.getText()+"' and password='"+new String(pass1.getPassword())+"'";
			
			try{
				conn=new Connect_db().connect();
				Statement stat= conn.createStatement();
				ResultSet rs1=stat.executeQuery(queryname);
				rs1.next();
				if(rs1.getInt(1)==1){
					ResultSet rs2=stat.executeQuery(querypassword);
					rs2.next();
							if(rs2.getInt(1)==1){
								name="ADMINISTRATOR";
								verify=false;
								frame.dispose();
								Log l=new Log("S0",true,false);
								l.log.join();
								check();
							}
							else{
								JOptionPane.showMessageDialog(null, "Incorrect Password for ADMINISTRATOR..!!", "Error..!!", JOptionPane.ERROR_MESSAGE);
								pass1.setText(passtext);
								user1.setText(usertext);
							}
				}
				else{
					JOptionPane.showMessageDialog(null, "Enter ADMINISTRATOR User name and password", "Error..!!", JOptionPane.ERROR_MESSAGE);
					pass1.setText(passtext);
					user1.setText(usertext);
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				}
			
		}
	}
		
	public synchronized void check(){
		try {
			if(verify==true){
			this.wait();
			}
			else
				this.notify();
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public String getname(){return name;}
	
	private class Security implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {}
		public void keyReleased(KeyEvent arg0) {}
		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar()=='/' || e.getKeyChar()=='-' || e.getKeyChar()=='*' || e.getKeyChar()=='\'' || e.getKeyChar()=='#'){
				e.consume();
			}
		}
		
	}
}
