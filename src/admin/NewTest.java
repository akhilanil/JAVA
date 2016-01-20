package admin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.eclipse.jdt.internal.compiler.batch.Main;

import java.util.Arrays;

import connection.Connect_db;

public class NewTest implements Runnable {

	Thread t;
	JFrame frame;
	JPanel mp,sp1,sp2,sp3;
	
	JLabel main;
	JLabel mname,mcode,mcharge;
	JTextField mname1,mcode1,mcharge1;
	
	JLabel sub;
	JLabel scode,sname,scharge,sunit,sref;
	JTextField scode1,sname1,scharge1,sunit1,sref1;
	
	CardLayout cl;
	GridBagConstraints gbc;
	JComboBox<String> test;
	String[] test_name;
	int count,x,y;
	Connection conn;
	JRadioButton[] button;
	Image image;
	
	public NewTest(){
		t=new Thread(this,"Test");
		t.start();
		this.count=-1;
		this.x=-1;
		this.y=4;
		this.test_name=new String[1];
		this.button=new JRadioButton[1];
		this.image=new ImageIcon(Main.class.getResource("/images/admin.jpg")).getImage();
	}
	
	public void run() {
		
		frame=new JFrame("New Test");
		frame.setSize(750, 750);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		cl= new CardLayout();
		
		//JPanel hello=new JPanel();
		
		mp=new JPanel();
		mp.setLayout(cl);
		
		createsp1();
		mp.add(sp1, "1");
		
		createsp2();
		mp.add(sp2,"2");
		
		createsp3();
		mp.add(sp3,"3");
		
		//hello.setBackground(Color.BLACK);
		frame.add(mp);
		
	}
	
	
	private void createsp1(){
		sp1=new Design();
		
		
		sp1.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(10,10,10,10);
		
		main=new JLabel("Click Here To Add  New Main Test ");
		sub=new JLabel("Click Here to Add New Sub Test");
		
		main.setFont(new Font("Times New Roman",Font.PLAIN,20));
		sub.setFont(new Font("Times New Roman",Font.PLAIN,20));
		
		main.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				cl.show(mp, "2");
			}
			public void mouseEntered(MouseEvent arg0) {labelgraphics(main, 1);}
			public void mouseExited(MouseEvent arg0) {labelgraphics(main, 0);}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		
		sub.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				cl.show(mp, "3");
			}
			public void mouseEntered(MouseEvent arg0) {labelgraphics(sub, 1);}
			public void mouseExited(MouseEvent arg0) {labelgraphics(sub, 0);}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		gbc.gridx=0;
		gbc.gridy=0;
		sp1.add(main,gbc);
		gbc.gridy=1;
		sp1.add(sub,gbc);
	}
	
	
	private void createsp2(){
		sp2=new Design();
		sp2.setLayout(new GridBagLayout());
		
		JButton confirm= new JButton("Confirm");
		JButton back= new JButton("Back");
		
		GridBagConstraints gbc=new GridBagConstraints();

		gbc.insets=new Insets(5,5,5,5);
		
		mname=new JLabel("Name: ");
		mname1=new JTextField(20);
		
		mcode=new JLabel("Main Code: ");
		mcode1=new JTextField(3);
		
		mcharge=new JLabel("Charge: ");
		mcharge1=new JTextField(5);
		
		test=new JComboBox<>();
		
		
		gbc.gridx=0;
		gbc.gridy=0;
		sp2.add(mcode, gbc);
		gbc.gridx=1;
		sp2.add(mcode1, gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		sp2.add(mname, gbc);
		gbc.gridx=1;
		sp2.add(mname1, gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		sp2.add(mcharge, gbc);
		gbc.gridx=1;
		sp2.add(mcharge1, gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		test.insertItemAt("Test name", 0);
		test.setSelectedIndex(0);
		sp2.add(test, gbc);
		
		gbc.gridx=1;
		sp2.add(confirm, gbc);
		
		
		gbc.gridx=2;
		sp2.add(back, gbc);
		
		test.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED){
					
					if(test.getSelectedIndex()!=0){
						if(set(test.getSelectedItem().toString())){
							button=Arrays.copyOf(button, (count+1));
							button[count]=new JRadioButton(test.getSelectedItem().toString(),true);
							button[count].setOpaque(false);
							button[count].addMouseListener(new MouseListener(){
								public void mouseClicked(MouseEvent e) {
									for(int j=0; j<=count; j++){
										if(e.getSource()==button[j]){//checking which button is clicked
											button[j].setVisible(false);
											remove(j);
										}
									}
								}
								public void mouseEntered(MouseEvent e) {}
								public void mouseExited(MouseEvent e) {}
								public void mousePressed(MouseEvent e) {}
								public void mouseReleased(MouseEvent e) {}
							});
							if(x==2){y++;x=0;}
							else{x++;}
							gbc.gridx=x;
							gbc.gridy=y;
							sp2.add(button[count], gbc);
							sp2.validate();
							
						}
					}
					
				}
			}
			
		});
		
		try {
			int i=1;
			conn=new Connect_db().connect();
			Statement stat=conn.createStatement();
			
			ResultSet rs=stat.executeQuery("select sub_testname from Test_sub where main_testcode is null order by (sub_testname) asc");
			while(rs.next()){
				test.insertItemAt(rs.getString(1), i);
				i++;
			}
			i=0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				for(int j=0; j<=count; j++){
					button[j].setVisible(false);
				}
				count=-1;
				x=-1;
				y=4;
				mname1.setText("");
				mcode1.setText("");
				mcharge1.setText("");
				test.setSelectedIndex(0);
				cl.show(mp, "1");
			}
		});
		
		confirm.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				EntryMain m=new EntryMain(mcode1.getText(),mname1.getText(),mcharge1.getText(),test_name);
				try{m.t.join();}catch(Exception e){JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);}
				for(int j=0; j<=count; j++){
					button[j].setVisible(false);
				}
				count=-1;
				x=-1;
				y=4;
				mname1.setText("");
				mcode1.setText("");
				mcharge1.setText("");
				test.setSelectedIndex(0);
				cl.show(mp, "1");
			}
		});
	}
	
	
	
	
	private void createsp3(){
		sp3=new Design();
		
		sp3.setLayout(new GridBagLayout());
		
		JButton confirm= new JButton("Confirm");
		JButton back= new JButton("Back");
		
		
		GridBagConstraints gbc=new GridBagConstraints();

		gbc.insets=new Insets(5,5,5,5);

		scode=new JLabel("Code: ");
		sname=new JLabel("Test Name: ");
		scharge=new JLabel("Amount: ");
		sunit=new JLabel("Unit: ");
		sref=new JLabel("Reference Range: ");
		
		scode1=new JTextField(5);
		sname1=new JTextField(20);
		scharge1=new JTextField(7);
		sunit1=new JTextField(10);
		sref1=new JTextField(20);
		
		
		
		gbc.gridx=0;
		gbc.gridy=0;
		sp3.add(scode, gbc);
		gbc.gridx=1;
		sp3.add(scode1, gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		sp3.add(sname, gbc);
		gbc.gridx=1;
		sp3.add(sname1, gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		sp3.add(scharge, gbc);
		gbc.gridx=1;
		sp3.add(scharge1, gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		sp3.add(sunit, gbc);
		gbc.gridx=1;
		sp3.add(sunit1, gbc);
		
		gbc.gridx=0;
		gbc.gridy=4;
		sp3.add(sref, gbc);
		gbc.gridx=1;
		sp3.add(sref1, gbc);
		
		gbc.gridx=1;
		gbc.gridy=5;
		sp3.add(confirm, gbc);
		
		gbc.gridx=2;
		sp3.add(back, gbc);
		
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				scode1.setText("");
				sname1.setText("");
				scharge1.setText("");
				sunit1.setText("");
				sref1.setText("");
				cl.show(mp, "1");
			}
		});
		
		confirm.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				EntrySub s=new EntrySub(scode1.getText(),sname1.getText(),scharge1.getText(),sunit1.getText(),sref1.getText());
				try{s.t.join();}catch(Exception e){JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);}
				scode1.setText("");
				sname1.setText("");
				scharge1.setText("");
				sunit1.setText("");
				sref1.setText("");
				cl.show(mp, "1");
			}
		});
		
		
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
	
	private boolean set( String s){
		if(check(s)){
			count++;
			test_name=Arrays.copyOf(test_name, (count+1));
			test_name[count]=s;
			return true;
		}
		else return false;
	}
	
	private boolean check( String s){
		
		for(int k=0; k<=count; k++){
			if(test_name[k]==s){
				return false;
			}
		}
		return true;
	}
	
	private void remove(int i){
		int next,prv;
		next=i+1;
		prv=i;
		while(next<=count){
			test_name[prv]=test_name[next];
			button[prv]=button[next];
			next++;
			prv++;
		}
	count--;
	}
	@SuppressWarnings("serial")
	private class Design extends JPanel{
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}
		
}
