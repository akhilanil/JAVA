package resultscreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.eclipse.jdt.internal.compiler.batch.Main;

import connection.Connect_db;
import lab_screen.Window;

public class Result {
	JPanel mainpanel,toppanel,basepanel,panel1,panel2;
	JSplitPane divider_main,divider_sub;
	GridBagLayout gb;
	GridBagConstraints gbc;
	JLabel Name,Age,Gender,DOC_REF,Phone,id,label;
	JTextField Name1,Age1,Gender1,DOC_REF1,Phone1,id1,field;
	Connection conn;
	JButton Enter,Clear,Confirm;
	String[] Queries= new String[5];
	public JTextField[] fieldarray;
	public JLabel[] labelarray;
	JScrollPane scroll;
	JFrame frame;
	float TC,Tri,HDL;
	int si,ti,hi,li,vi;
	int ldl,vldl;
	Image imagetop,imagebase_left,imagebase_right;
	
	public Result(){
		frame=new Window().createWindow();
	}
	
	@SuppressWarnings("serial")
	private class DesignTop extends JPanel{
		public void paintComponent(Graphics g) {
	        g.drawImage(imagetop, 0, 0, getWidth(), getHeight(), this);
	    }
	} 
	
	@SuppressWarnings("serial")
	private class DesignBaseLeft extends JPanel{
		public void paintComponent(Graphics g) {
	        g.drawImage(imagebase_left, 0, 0, getWidth(), getHeight(), this);
	    }
	}
	@SuppressWarnings("serial")
	private class DesignBaseRight extends JPanel{
		public void paintComponent(Graphics g) {
	        g.drawImage(imagebase_right, 0, 0, getWidth(), getHeight(), this);
	    }
	}
	public void resultwindow(){
		
		try {
			imagetop= new ImageIcon(Main.class.getResource("/images/top.jpg")).getImage();
			imagebase_left=new ImageIcon(Main.class.getResource("/images/baseimage_left.jpg")).getImage();
			imagebase_right=new ImageIcon(Main.class.getResource("/images/baseimage_right.jpg")).getImage();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Failed to load Image..", "Error..!!", JOptionPane.ERROR_MESSAGE);
		}

		mainpanel=new JPanel();
		toppanel=new DesignTop();
		basepanel=new JPanel();
		
		toppanel.setLayout(new BorderLayout());
		basepanel.setLayout(new BorderLayout());
		frame.add(toppanel);
		basepanel_creator();
		frame.add(basepanel);
		
		basepanel.setBackground(Color.BLUE);
		divider_main=new JSplitPane(JSplitPane.VERTICAL_SPLIT);//Splitting horizontally :P
		
		divider_main.setTopComponent(toppanel);//bottom panel
		divider_main.setBottomComponent(basepanel);//top panel which dosen't change
		
		divider_main.setDividerLocation(200);
		divider_main.setBackground(Color.BLACK);
		divider_main.setDividerSize(2);
		frame.add(divider_main);
		frame.validate();

		
	}
	
	private void basepanel_creator(){

		panel1=new DesignBaseLeft();
		panel1.setLayout(new GridBagLayout());
		craeatepanel1();
		panel2=new DesignBaseRight();
		panel2.setLayout(new GridBagLayout());
		
		
		panel2.setPreferredSize(new Dimension(700,1500));
		scroll=new JScrollPane(panel2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(panel2.getPreferredSize());
		
		scroll.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
		divider_sub=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		divider_sub.setLeftComponent(panel1);//bottom panel
		divider_sub.setRightComponent(scroll);
		divider_sub.setDividerSize(2);
		divider_sub.setDividerLocation(-1);
		basepanel.add(divider_sub,BorderLayout.CENTER);
		
	}
	
	private void craeatepanel1() {
		
		
		
		gbc=new GridBagConstraints();
		
		gbc.weighty=0;
		gbc.insets= new Insets(0,0,10,10);
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.NONE;
		gbc.ipadx=3;
		gbc.ipady=3;
		id= new JLabel("Customer_id: ");
		gbc.gridx=0;
		gbc.gridy=20;
		panel1.add(id,gbc);
		
		
		id1=new JTextField("AAJ",10);
		gbc.gridx=1;
		gbc.gridy=20;
		id1.setColumns(10);
		panel1.add(id1,gbc);
		gbc.insets= new Insets(0,0,10,10);
		
		Enter= new JButton("Enter");
		Clear= new JButton("Clear");
		gbc.gridx=2;
		gbc.gridy=20;
		gbc.anchor=GridBagConstraints.WEST;
		panel1.add(Enter, gbc);
		
		gbc.insets= new Insets(0,0,10,10);
		gbc.gridx=3;
		gbc.gridy=20;
		gbc.weightx=0.001;
		panel1.add(Clear, gbc);
		gbc.weightx=0;
		
		Name= new JLabel("Name: ");
		gbc.gridx=0;
		gbc.gridy=22;
		panel1.add(Name,gbc);
		
		
		Name1=new JTextField(30);
		gbc.gridx=1;
		gbc.gridy=22;
		Name1.setEditable(false);
		panel1.add(Name1,gbc);
		
		
		Phone=new JLabel("Phone: ");
		gbc.gridx=0;
		gbc.gridy=24;
		panel1.add(Phone,gbc);
		
		Phone1=new JTextField(11);
		Phone1.setColumns(11);
		gbc.gridx=1;
		gbc.gridy=24;
		Phone1.setEditable(false);
		panel1.add(Phone1,gbc);
		
		Age= new JLabel("Age: ");
		gbc.gridx=0;
		gbc.gridy=26;
		panel1.add(Age,gbc);
		
		Age1=new JTextField(3);
		Age1.setColumns(3);
		gbc.gridx=1;
		gbc.gridy=26;
		Age1.setEditable(false);
		panel1.add(Age1,gbc);
		
		Gender= new JLabel("Gender: ");
		gbc.gridx=0;
		gbc.gridy=28;
		panel1.add(Gender,gbc);
		
		Gender1=new JTextField(1);
		Gender1.setColumns(1);
		gbc.gridx=1;
		gbc.gridy=28;
		Gender1.setEditable(false);
		panel1.add(Gender1,gbc);
		
	
		DOC_REF= new JLabel("DOC_REF: ");
		gbc.gridx=0;
		gbc.gridy=30;
		panel1.add(DOC_REF,gbc);
		
				
		DOC_REF1=new JTextField(30);
		gbc.gridx=1;
		gbc.gridy=30;
		DOC_REF1.setEditable(false);
		panel1.add(DOC_REF1,gbc);
		
		
		
		Enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				clear(1);
				Name1.setText("");
				Phone1.setText("");
				Age1.setText("");
				Gender1.setText("");
				DOC_REF1.setText("");

				try{
					Connection conn=new Connect_db().connect();
					Statement Stat=conn.createStatement();
					ResultSet Rs= Stat.executeQuery("select count(resultid) from Result_customer where customerid='"+id1.getText()+"'");
					Rs.next();
					if(Rs.getInt(1)==0){listener();}
					else{JOptionPane.showMessageDialog(null, "Already Entered..!!", "ERROR..!!", JOptionPane.ERROR_MESSAGE);}
				}catch(Exception e){e.printStackTrace();}
				
				
			}
		});
		id1.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					try{
						Connection conn=new Connect_db().connect();
						Statement Stat=conn.createStatement();
						ResultSet Rs= Stat.executeQuery("select count(resultid) from Result_customer where customerid='"+id1.getText()+"'");
						Rs.next();
						if(Rs.getInt(1)==0){listener();}
						else{JOptionPane.showMessageDialog(null, "Already Entered..!!", "ERROR..!!", JOptionPane.ERROR_MESSAGE);}
					}catch(Exception e1){e1.printStackTrace();}
				}
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		Clear.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {clear(0);}});

	}		
	
	
	
	public void clear(int check){
		if(check==0)
		id1.setText("AAJ");
		Name1.setText("");
		Phone1.setText("");
		Age1.setText("");
		Gender1.setText("");
		DOC_REF1.setText("");
		Component[] comp=panel2.getComponents();
		for(int i=0; i<comp.length; i++){
			comp[i].setVisible(false);
		}
	}
	private void listener(){
		if(check(id1.getText())==false){
			JOptionPane pane= new JOptionPane(JOptionPane.ERROR_MESSAGE); 
			pane.setMessage("CUSTOMER ID NOT FOUND.... PLEASE RE-ENTER");
			JDialog dialog = pane.createDialog("ERROR.....!!!");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
		else{
				queryset(id1.getText());
				Details d=new Details();
				ProducerDetails pd=new ProducerDetails(d);
				new ConsumerDetails(d);
				try{pd.t.join();
				}catch (Exception ex){ex.printStackTrace();}
				Entry e= new Entry(id1.getText());
				ProducerEntry pe=new ProducerEntry(e);
				new ConsumerEntry(e);
				try{pe.t.join();
				}catch (Exception ex){ex.printStackTrace();}
				
				if(check()==true){
					getIndex();
					calculate();
				}
				
				
				
				
				Confirm=new JButton("CONFIRM");
				gbc.gridy=gbc.gridy+2;
				gbc.gridx=gbc.gridx-2;
				scroll.getViewport().setViewPosition(new Point(350,550));
				panel2.add(Confirm,gbc);
				
				
				frame.validate();
				
				Confirm.addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent e) {
						for(int i=0;i<labelarray.length;i++)
						System.out.println(labelarray[i].getText());
						if(isEmpty()==false){
							Storage s=new Storage(id1.getText(),fieldarray,labelarray,fieldarray.length);
							s.store();
							clear(0);
						}
						else{
							JOptionPane.showMessageDialog(null, "Empty Fields Present", "ERROR..!!", JOptionPane.ERROR_MESSAGE);
						}
					}
					
				});
				
		}
	}
	private boolean check(String id){
		boolean verify=true;
		try{
			Connection conn1=new Connect_db().connect();
			Statement Stat1=conn1.createStatement();
			
			String query="select count(customer_id) as count from customer where customer_id='"+id+"'";
			ResultSet Rs=Stat1.executeQuery(query);
			Rs.next();
			if(Rs.getInt("count")==0)
				verify=false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return  verify;
	}
	void queryset(String id){
		Queries[0]="select name from customer where customer_id='"+id+"'";
		Queries[1]="select phoneno from customer where customer_id='"+id+"'";
		Queries[2]="select age from customer where customer_id='"+id+"'";
		Queries[3]="select doc_reference from customer where customer_id='"+id+"'";
		Queries[4]="select Gender from customer where customer_id='"+id+"'";
	}
	
	


	
	
	
	public class ProducerDetails implements Runnable{
		Details d;
		Thread t;
		public ProducerDetails(Details d){
			this.d=d;
			t=new Thread(this,"Producer");
			t.start();
		}
		public void run() {
			while(d.counter!=4){d.produce();}
			if(d.counter==4){d.check();}
		}
		
	}
	
	public class ConsumerDetails implements Runnable{
		Details d;
		
		public ConsumerDetails(Details d){
			this.d=d;
			new Thread(this,"Consumer").start();
		}
		public void run() {
			while(d.tell())
			d.consume();
		}
		
	}

	public class Details{
		public String answer;
		volatile boolean check;
		public int counter;
		boolean verify=true;
		void check(){verify=false;}
		boolean tell(){return verify;}
		Details(){
			check=false;
			counter= 0;
			answer= new String();
		}
		
		public synchronized void produce(){
			if(check){
				try{
					wait();
				}catch (Exception e){e.printStackTrace();}
			}
			String Query=Queries[counter];
			try{
				Connection conn1=new Connect_db().connect();
				Statement Stat1=conn1.createStatement();
				ResultSet Rs=Stat1.executeQuery(Query);
				Rs.next();
				this.answer=Rs.getString(1);
			}catch(Exception e){
				e.printStackTrace();
				frame.dispose();
			}
			
			check=true;
			notify();
		}	
		public synchronized void consume(){
			if(!check){
				try{
					wait();
				}catch (Exception e){e.printStackTrace();}
			}
			if(counter==0){Name1.setText(answer);}
			else if(this.counter==1){Phone1.setText(this.answer);}
			else if(this.counter==2){Age1.setText(this.answer);}
			else if(this.counter==3){DOC_REF1.setText(this.answer);}
			else if(this.counter==4){Gender1.setText(this.answer);}
			check=false;
			this.counter++;
			notify();
		}
	}
	
	public class Entry{
		public String answer1,answer2,answer3;
		public JLabel label1,label2;
		volatile boolean check;
		public int counter,limit;
		boolean verify=true;
		int x,y;
		String id;
		void check(){verify=false;}
		GridBagConstraints c= new GridBagConstraints(); 
		boolean tell(){return verify;}
		Entry(String id){
			check=false;
			counter= 0;
			answer1= new String();
			answer2= new String();
			label1=new JLabel();
			label2=new JLabel();
			this.id=id;
			this.x=0;
			this.y=0;
			gbc.weighty=-1.0;
			try{
				Connection conn1=new Connect_db().connect();
				Statement Stat1=conn1.createStatement();
				ResultSet Rs1=Stat1.executeQuery("select count(sub_testname) from Test_customer c,Test_sub s "
						+ "where c.customer_id='"+id+"' and c.testcode=s.sub_testcode");
				Rs1.next();
				this.limit=Rs1.getInt(1);
			}catch(Exception e){
				e.printStackTrace();
			}
			fieldarray=new JTextField[limit];
			labelarray=new JLabel[(2*limit)];
		}
		public synchronized void produce(){
			if(check){
				try{
					wait();
				}catch (Exception e){e.printStackTrace();}
			}
			if(counter<(limit)){
				String Query1="select top "+(counter+1)+" sub_testname,unit from Test_customer c,Test_sub s "
							+ "where c.customer_id='"+id+"' and c.testcode=s.sub_testcode "
							+ "except "
							+ "select top "+counter+" sub_testname,unit from Test_customer c,Test_sub s "
							+ "where c.customer_id='"+id+"' and c.testcode=s.sub_testcode";
			
				try{
					Connection conn1=new Connect_db().connect();
					Statement Stat1=conn1.createStatement();
					ResultSet Rs1=Stat1.executeQuery(Query1);
					Rs1.next();
					answer1=Rs1.getString(1);
					answer2=Rs1.getString(2);
					check=true;
					notify();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}	
		public synchronized void consume(){
			if(!check){
				try{
					wait();
				}catch (Exception e){e.printStackTrace();}
			}
			if(counter<(limit)){
				gbc.gridx=x;
				gbc.gridy=y+(2*counter);
				
				
				labelarray[2*counter]=new JLabel(answer1+": ");
				panel2.add(labelarray[2*counter], gbc);
				
				
				gbc.gridx=x+1;
				fieldarray[counter]=new JTextField();
				fieldarray[counter].setColumns(10);
				panel2.add(fieldarray[counter], gbc);
			
				gbc.gridx=x+2;
				labelarray[2*(counter)+1]=new JLabel(answer2);
				panel2.add(labelarray[2*(counter)+1], gbc);
			}
			
			check=false;
			this.counter++;
			notify();
		}
	}
	
	public class ProducerEntry implements Runnable{
		Entry e;
		Thread t;
		
		public ProducerEntry(Entry e){
			this.e=e;
			t=new Thread(this,"Producer");
			t.start();
		}
		public void run() {
			while(e.counter<(e.limit)){
				e.produce();
				}
			if(e.counter==e.limit){e.check();}
		}
		
	}
	
	public class ConsumerEntry implements Runnable{
		Entry e;
		
		public ConsumerEntry(Entry e){
			this.e=e;
			new Thread(this,"Consumer").start();
		}
		public void run() {
			while(e.tell())
			e.consume();
		}
		
	}
	
	public boolean check(){
		boolean c=false;
		for(int i=0; i<labelarray.length; i++){
			String str=labelarray[i].getText();
			if(str.length()>2)
			str=str.substring(0, str.length()-2);
			if(str.equals("LDL")){
				c=true;
				fieldarray[i/2].setEditable(false);
				li=i/2;
			}
			else if(str.equals("VLDL")){
				c=true;
				fieldarray[i/2].setEditable(false);
				vi=i/2;
				
			}
		}
		return c;
	}
	
	public void calculate(){
		
				fieldarray[si].addFocusListener(new FocusListener(){
					public void focusGained(FocusEvent arg0) {}
					public void focusLost(FocusEvent arg0) {
						TC=Float.parseFloat(fieldarray[si].getText().trim());
						if(verify()==true){
							HDL=Float.parseFloat(fieldarray[hi].getText().trim());
							Tri=Float.parseFloat(fieldarray[ti].getText().trim());
							TC=Float.parseFloat(fieldarray[si].getText().trim());
							fieldarray[li].setText(Float.toString(TC-HDL-(Tri/5)));
						}
						else{fieldarray[li].setText("");}
					}
					
				});
				fieldarray[ti].addFocusListener(new FocusListener(){
					public void focusGained(FocusEvent e) {}
					public void focusLost(FocusEvent e) {
						if(fieldarray[ti/2].getText().equals("")==false){
							Tri=Float.parseFloat(fieldarray[ti].getText().trim());
							fieldarray[vi].setText(Float.toString(Tri/5));
						}
						else{fieldarray[vi].setText("");}
					}
					
				});
				fieldarray[hi].addFocusListener(new FocusListener(){
					public void focusGained(FocusEvent e) {}
					public void focusLost(FocusEvent e) {
						if(verify()==true){
							HDL=Float.parseFloat(fieldarray[hi].getText().trim());
							Tri=Float.parseFloat(fieldarray[ti].getText().trim());
							TC=Float.parseFloat(fieldarray[si].getText().trim());
							fieldarray[li].setText(Float.toString(TC-HDL-(Tri/5)));
						}
						else{fieldarray[li].setText("");}
					}
					
				});
	}
	
	public boolean verify(){
		boolean c=true;
		if(fieldarray[si/2].getText().equals("")){c=false;}
		if(fieldarray[ti/2].getText().equals("")){c=false;}
		if(fieldarray[hi/2].getText().equals("")){c=false;}
		return c;
	}
	
	public void getIndex(){
		for(int i=0; i<labelarray.length; i++){
			String str=labelarray[i].getText();
			if(str.length()>2)
			str=str.substring(0, str.length()-2);
			if(str.equals("Serum Cholesterol")){ si=i/2;}
			else if(str.equals("Triglycerides")){ti=i/2;}
			else if(str.equals("HDL")){hi=i/2;}
			else if(str.equals("LDL")){li=i/2;}
			else if(str.equals("VLDL")){vi=i/2;}
		}
	}
	
	public boolean isEmpty(){
		boolean v=false;
		for(int i=0; i<fieldarray.length; i++){
			if(fieldarray[i].getText().equals("")){
				v=true;
				break;
			}
		}
		return v;
	}
}
