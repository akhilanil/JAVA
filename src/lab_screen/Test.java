package lab_screen;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;




import org.eclipse.jdt.internal.compiler.batch.Main;

import connection.Connect_db;

public class Test {

	JRadioButton temp;
	public JRadioButton[] button= new JRadioButton[10];
	public ButtonGroup Group;
	GridBagConstraints c2; 
	public int size=2;
	public String[] Get_Name= new String[10];
	String[] Test_name;
	JTextField Name1,Phone1,Age1,Gender1,DOC_REF1;
	JLabel Name,Phone,Age,Gender,DOC_REF;
	public int Testname_size=-1;
	public String name;
	public String age;
	public String gender;
	public String phone;
	public String doc;
	public JLabel welcome;
	public JFrame frame;
	public Window w;
	public JButton Enter;
	JPanel toppanel,bpleft,bpright,bpr1,bpr2,bpr3,base_panel;
	JPanel panel1;
	JSplitPane dividermain,dividersub;
	JRadioButton male,female;
	int[] testcode=new int[3];
	CardLayout cl= new CardLayout();//to switch workspaces
	Connection conn;
	Image imagetop,imagebase1,imagebase2,imagebase3;
	JComboBox<String> dropdown_subname,dropdown_mainname;
	
	
	public Test(){
			w=new Window();
			frame=w.createWindow();
			dividemain();
			createtop();
			frame.add(dividermain);
			imagetop= new ImageIcon(Main.class.getResource("/images/top.jpg")).getImage();
			imagebase1=new ImageIcon(Main.class.getResource("/images/base1.jpg")).getImage();
			imagebase2=new ImageIcon(Main.class.getResource("/images/base2.png")).getImage();
			imagebase3=new ImageIcon(Main.class.getResource("/images/base2.png")).getImage();
			frame.validate();
	}
	
	@SuppressWarnings("serial")
	private class DesignTop extends JPanel {
		
		public void paintComponent(Graphics g) {
	        g.drawImage(imagetop, 0, 0, getWidth(), getHeight(), this);
	    }
	}
	
	@SuppressWarnings("serial")
	private class DesignBase1 extends JPanel {
		
		public void paintComponent(Graphics g) {
	        g.drawImage(imagebase1, 0, 0, getWidth(), getHeight(), this);
	    }
	}
	
	@SuppressWarnings("serial")
	private class DesignBase3 extends JPanel {
		
		public void paintComponent(Graphics g) {
	        g.drawImage(imagebase3, 0, 0, getWidth(), getHeight(), this);
	    }
	}
	
	@SuppressWarnings("serial")
	private class DesignBase extends JPanel{
		public void paintComponent(Graphics g) {
			
			this.setBackground(Color.WHITE);
			super.paintComponent(g);
			g.setColor(Color.RED);
			g.setFont(new Font("Times New Roman",Font.ITALIC,40));
			g.drawString("No Contents selected", 250, 246);
			
		}
	}
	private void dividemain(){
		dividermain=new JSplitPane(JSplitPane.VERTICAL_SPLIT);//Splitting horizontally :P
		createtop();
		dividermain.setLeftComponent(toppanel);//top panel which dosen't change
		createbase();
		dividermain.setRightComponent(base_panel);//bottom panel
		
		dividermain.setDividerLocation(200);
		dividermain.setBackground(Color.WHITE);
		dividermain.setEnabled(false);
		dividermain.setDividerSize(1);
	}
	
	private void createtop(){
		imagetop= new ImageIcon(Main.class.getResource("/images/top.jpg")).getImage();
		toppanel=new DesignTop();
	}
	
	private void createbase(){
		base_panel=new JPanel();
		base_panel.setLayout(new BorderLayout());
		dividesub();
		base_panel.add(dividersub,BorderLayout.CENTER);
	}
	
	private void dividesub(){
		
		dividersub=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		
		createbpleft();
		dividersub.setLeftComponent(bpleft);//bottom panel
		createbpright();
		dividersub.setRightComponent(bpright);//top panel which dosen't change
		
		dividersub.setBackground(Color.WHITE);
		
		dividersub.setDividerSize(1);
		dividersub.setDividerLocation(-1);
		dividersub.setEnabled(false);
	}
	
	private void createbpleft(){
		//bpleft=new DesignBase1();
		bpleft=new DesignBase1();
		bpleft.setBackground(Color.BLACK);
		bpleft.setMaximumSize(new Dimension(579,501));
		bpleft.setPreferredSize(new Dimension(579,501));
		bpleft.setLayout(new GridBagLayout());
		//bpleft.setMaximumSize(new Dimension(579,501));
		GridBagConstraints c= new GridBagConstraints();
		c.insets=new Insets(0,0,10,10);
		
		welcome= new JLabel("Welcome "+w.getuname());
		welcome.setFont(new Font("Monotype Corsiva",Font.PLAIN,20));
		c.gridx=0;
		c.gridy=0;
		//welcome.setMaximumSize(new Dimension(579,501));
		bpleft.add(welcome, c);
		
		Name= new JLabel("Name: ");
		c.gridx=0;
		c.gridy=50;
		bpleft.add(Name,c);
		
		Name1=new JTextField(30);
		c.gridx=1;
		c.gridy=50;
		Name1.setMinimumSize(new Dimension(334,20));
		bpleft.add(Name1,c);
		
		Phone=new JLabel("Phone: ");
		c.gridx=0;
		c.gridy=52;
		bpleft.add(Phone,c);
		
		Phone1=new JTextField("0",11);
		c.gridx=1;
		c.gridy=52;
		Phone1.setMinimumSize(new Dimension(125,20));
		bpleft.add(Phone1,c);
		
		Age= new JLabel("Age: ");
		c.gridx=0;
		c.gridy=54;
		bpleft.add(Age,c);
		
		Age1=new JTextField(3);
		c.gridx=1;
		c.gridy=54;
		Age1.setMinimumSize(new Dimension(37,20));
		bpleft.add(Age1,c);
		
		Gender= new JLabel("Gender: ");
		c.gridx=0;
		c.gridy=56;
		bpleft.add(Gender,c);
		
		Gender1=new JTextField(1);
		Gender1.setColumns(1);
		c.gridx=1;
		c.gridy=56;
		Gender1.setMinimumSize(new Dimension(15,20));
		bpleft.add(Gender1,c);
	
		DOC_REF= new JLabel("DOC_REF: ");
		c.gridx=0;
		c.gridy=58;
		bpleft.add(DOC_REF,c);
				
		DOC_REF1=new JTextField("Dr.",30);
		c.gridx=1;
		c.gridy=58;
		DOC_REF1.setMinimumSize(new Dimension(334,20));
		bpleft.add(DOC_REF1,c);
		
		JButton individual=new JButton("INDIVIDUAL TEST");
		c.gridx=1;
		c.gridy=60;
		bpleft.add(individual,c);
		
		
		
		Name1.addKeyListener(new Escape(Name1));
		DOC_REF1.addKeyListener(new Escape(DOC_REF1));
		
		Phone1.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					Phone1.setText("0");
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent e) {
				
			if(!((Character.isDigit(e.getKeyChar())) || e.getKeyCode()==KeyEvent.VK_TAB || e.getKeyCode()==KeyEvent.VK_DELETE || e.getKeyCode()==KeyEvent.VK_BACK_SPACE) || Phone1.getText().length()>10){
					e.consume();
				}
					
			}
			
		});
		
		
		Age1.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					Age1.setText("");
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent e) {
			if(!((Character.isDigit(e.getKeyChar())) || e.getKeyCode()==KeyEvent.VK_TAB || e.getKeyCode()==KeyEvent.VK_DELETE || e.getKeyCode()==KeyEvent.VK_BACK_SPACE) || Age1.getText().length()>2){
					e.consume();
				}
			}
		});
		
		Gender1.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					Gender1.setText("");
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent e) {
			if(e.getKeyChar()!='M' && e.getKeyChar()!='F' && e.getKeyChar()!='m' && e.getKeyChar()!='f'){
					e.consume();
				}
			}
		});
		
		JButton group=new JButton("GROUP TEST");
		c.gridx=1;
		c.gridy=62;
		bpleft.add(group,c);
		
			
		JButton Clear= new JButton("CLEAR");
		c.gridx=10;
		c.gridy=62;
		bpleft.add(Clear,c);
		
		
		
		
		individual.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				if(check_empty()){
					reset();
					cl.show(bpright, "2");
				}
			}
			
		});
		
		group.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(check_empty()){
					reset();
					cl.show(bpright, "3");
				}
			}
			
		});
		
		Clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Name1.setText("");
				Phone1.setText("0");
				Age1.setText("");
				Gender1.setText("");
				DOC_REF1.setText("Dr.");
				dropdown_subname.setSelectedIndex(0);
				dropdown_mainname.setSelectedIndex(0);
				cl.show(bpright, "1");
			}
			
		});
	
		
	}
	
	private void createbpright(){
		bpright=new JPanel();//DesignBase1();
		//bpright.setPreferredSize(new Dimension((1360)/2,502));
		bpright.setLayout(cl);
		bpr1=new DesignBase();
		bpright.add(bpr1, "1");
		
		createbpr2();
		bpright.add(bpr2, "2");
		
		createbpr3();
		bpright.add(bpr3, "3");
		
		bpright.validate();
		
	}
	
	private void createbpr2(){
		
		bpr2=new DesignBase3();
		int i=0;
		bpr2.setLayout(new GridBagLayout());
		c2=new GridBagConstraints();
		c2.insets=new Insets(0,0,10,10);
		try{//to check for exceptions while connecting to db;
			conn=new Connect_db().connect();// imported from user defined package connection
			Statement stat=conn.createStatement();
			
			ResultSet Rs1=stat.executeQuery("Select count(charge) from Test_sub");
			Rs1.next();
			String[] Name_test= new String[Rs1.getInt(1)];//Initializing the array with size of number of tests available
			
			ResultSet Rs2=stat.executeQuery("select sub_testname from Test_sub where charge IS NOT NULL order by(sub_testname) asc");
			while(Rs2.next()){
				Name_test[i]=Rs2.getString("sub_testname");
				i++;
			} 
			
			i=0;
			
			dropdown_subname=new JComboBox<>(Name_test);// Drop down list
			
			dropdown_subname.insertItemAt("Test name", 0);
			dropdown_subname.setSelectedIndex(0);
			
			
			dropdown_subname.addItemListener(new ItemListener(){// action listener for the drop down list 
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange()==ItemEvent.SELECTED){
						String s=dropdown_subname.getSelectedItem().toString();
						if(s!="Test name"){// checking if the pressed value in drop down is the default value(TEST NAME)
							if(set(s)){
							button[Testname_size]=new JRadioButton(s,true);
							button[Testname_size].setOpaque(false);
							
							
							button[Testname_size].addMouseListener(new MouseListener(){
								public void mouseClicked(MouseEvent e) {
									for(int j=0; j<=Testname_size; j++){
										if(e.getSource()==button[j]){
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
							c2.gridx=24;
							c2.gridy=size;
							bpr2.add(button[Testname_size], c2);
							size=size+2;
							bpr2.validate();
						}
					}
						dropdown_subname.setSelectedIndex(0);
				}
				
			}
			
		
			});
			
			
			
			c2.gridx=20;
			c2.gridy=0;
			bpr2.add(dropdown_subname,c2);
		}catch(Exception e){
			JOptionPane.showMessageDialog(bpr2, e.getLocalizedMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
		}
		
		JButton Enter=new JButton("ENTER");
		c2.gridx=23;
		c2.gridy=0;
		bpr2.add(Enter,c2);
		
		Enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Test_name= new String[Testname_size+1];
				for(int i=0; i<=Testname_size; i++){
					Test_name[i]=Get_Name[i];
				}
				if(Test_name.length!=0){
					Final_Screen f=new Final_Screen(Name1.getText(), Age1.getText(), Gender1.getText(), Phone1.getText(), DOC_REF1.getText(), Test_name, Testname_size,0);
					try {
						f.t.join();
					} catch (InterruptedException e1) {
						JOptionPane.showMessageDialog(null, e1.getLocalizedMessage(), e1.getClass().getName(), JOptionPane.ERROR_MESSAGE);
					}
					dropdown_subname.setSelectedIndex(0);
					reset();
				}
				else{
					JOptionPane.showMessageDialog(null,"No Test Selected..!!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}//0-for INDIVIDUAL and 1 for-GROUP
		});
		JButton Back=new JButton("BACK");
		c2.gridx=25;
		c2.gridy=0;
		bpr2.add(Back,c2);
		Back.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
					cl.show(bpright, "1");
				}
		});
		
		bpr2.validate();
	}
	
	private void createbpr3(){
		
		int i=0;
		bpr3=new DesignBase3();
		bpr3.setLayout(new GridBagLayout());
		c2.insets=new Insets(0,0,10,10);
		try{//to check for exceptions while connecting to database;
			conn=new Connect_db().connect();// imported from user defined package connection
			Statement stat=conn.createStatement();
			ResultSet Rs1=stat.executeQuery("select count(main_testcode) from Test_main");

			Rs1.next();
			
			String[] Name_test= new String[Rs1.getInt(1)];//Initializing the array with size of number of tests available
			
			ResultSet Rs2=stat.executeQuery("select main_testname from Test_main order by(main_testname) asc");
			while(Rs2.next()){
				Name_test[i]=Rs2.getString("main_testname");
				i++;
			} 
			i=0;
			dropdown_mainname=new JComboBox<>(Name_test);// Drop down list
			dropdown_mainname.insertItemAt("Test name", 0);
			dropdown_mainname.setSelectedIndex(0);
			dropdown_mainname.addItemListener(new ItemListener(){// action listener for the drop down list 
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange()==ItemEvent.SELECTED){
						String s=dropdown_mainname.getSelectedItem().toString();
						if(s!="Test name"){// checking if the pressed value in drop down is the default value(TEST NAME)
							if(set(s)==true){
							button[Testname_size]=new JRadioButton(s,true);
							button[Testname_size].setOpaque(false);
							button[Testname_size].addMouseListener(new MouseListener(){
								public void mouseClicked(MouseEvent e) {
									for(int j=0; j<=Testname_size; j++){
										if(e.getSource()==button[j]){
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
							c2.gridx=24;
							c2.gridy=size;
							bpr3.add(button[Testname_size], c2);
							size=size+2;
							bpr3.validate();
						}
					}
						dropdown_mainname.setSelectedIndex(0);
				}
			}
			});
		c2.gridx=20;
		c2.gridy=0;
		
		bpr3.add(dropdown_mainname,c2);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(bpr3, e.getLocalizedMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
		}
		 
		JButton Enter=new JButton("ENTER");
		c2.gridx=23;
		c2.gridy=0;
		bpr3.add(Enter,c2);
		
		Enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Testname_size!=-1){
					Final_Screen f=new Final_Screen(Name1.getText(), Age1.getText(), Gender1.getText(), Phone1.getText(), DOC_REF1.getText(), Get_Name, Testname_size,1);
					try {
						f.t.join();
					} catch (InterruptedException e1) {
						JOptionPane.showMessageDialog(null, e1.getLocalizedMessage(), e1.getClass().getName(), JOptionPane.ERROR_MESSAGE);
					}
					dropdown_mainname.setSelectedIndex(0);
					reset();
				}//0-for INDIVIDUAL and 1 for-GROUP
				else{
					JOptionPane.showMessageDialog(null,"No Test Selected..!!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		JButton Back=new JButton("BACK");
		c2.gridx=25;
		c2.gridy=0;
		bpr3.add(Back,c2);
		Back.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
					cl.show(bpright, "1");
				}
		});
		
	}
	
	
	private void remove(int i){
		int next,prv;
				next=i+1;
				prv=i;
				while(next<=Testname_size){
					Get_Name[prv]=Get_Name[next];
					button[prv]=button[next];
					next++;
					prv++;
				}
				Testname_size--;
	}
		
	
	
	private boolean set(String s){
		if(check_repeat(s)){
			Testname_size++;//set to 0 after closing..
			Get_Name[Testname_size]=s;
			return true;
		}
		else return false;
	}
	
	private boolean check_repeat(String s){
		for(int k=0; k<=Testname_size; k++){
			if(Get_Name[k]==s){
				return false;
			}
		}
		return true;
	}
	
	private boolean check_empty(){
		if (Name1.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Enter Name.!!", "Error..!!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(Phone1.getText().equals("0")){
			JOptionPane.showMessageDialog(null, "Enter Phone Number..!!");
			return false;
		}
		else if(Age1.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Enter Age..!!");
			return false;
		}
		else if(Gender1.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Enter Gender..!!");
			return false;
		}
		else{
			return true;
		}
	}
	
	private void reset(){
		for(int i=0; i<=Testname_size; i++){
			button[i].setVisible(false);
		}
		Testname_size=-1;
		size=2;
		cl.show(bpright, "1");
	}
	
	
	
	public class Escape implements KeyListener{
		
		JTextField field;
		Escape(JTextField field){
			this.field=field;
		}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
			field.setText("");
		}
		public void keyReleased(KeyEvent e) {
		}
		public void keyTyped(KeyEvent e) {
		}	
	}
}
