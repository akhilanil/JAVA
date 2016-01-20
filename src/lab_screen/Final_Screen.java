package lab_screen;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entry_db.Store;
public class Final_Screen implements Runnable {
	
	Thread t;
	public JFrame frame;
	JPanel Screen;
	GridBagLayout g;
	GridBagConstraints gbc=new GridBagConstraints();
	String name,age,gender,phone,doc;
	int size,type;
	String[] test_name;
	int choose;
	boolean verify;
	
	
	
	
	
	public Final_Screen(String name, String age, String gender,String phone, String doc, String test_name[], int size, int type){
		
		
		t= new Thread(this,"ConfirmScreen");
		if(doc.equals("Dr.")){this.doc="NA";}
		else{this.doc=doc;}
		this.name=name;
		this.age=age;
		this.gender=gender;
		this.phone=phone;
		this.test_name=test_name;
		this.size=size;
		this.type=type;
		this.verify=true;
		t.start();
		
		
		
		
	}


	public void run() {
		
		frame= new JFrame("Blood Test");
		frame.setVisible(true);
		frame.setSize(750,750);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		/*frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				frame.notify();
			}
		});*/
		gbc.insets=new Insets(10,10,10,10);
		
		Screen= new JPanel();
		g= new GridBagLayout();
		Screen.setLayout(g);
		frame.add(Screen,BorderLayout.NORTH);
		
		JLabel Name= new JLabel("Name: ");
		gbc.gridx=0;
		gbc.gridy=2;
		Screen.add(Name, gbc);
		JTextField Name1=new JTextField(name.toUpperCase(),30);
		gbc.gridx=1;
		gbc.gridy=2;
		Name1.setEditable(false);
		Screen.add(Name1, gbc);
		
		JLabel Age= new JLabel("Age: ");
		gbc.gridx=0;
		gbc.gridy=4;
		Screen.add(Age, gbc);
		JTextField Age1=new JTextField(age,30);
		gbc.gridx=1;
		gbc.gridy=4;
		Age1.setEditable(false);
		Screen.add(Age1, gbc);
		
		JLabel Gender= new JLabel("Gender: ");
		gbc.gridx=0;
		gbc.gridy=6;
		Screen.add(Gender, gbc);
		JTextField Gender1=new JTextField(gender,30);
		gbc.gridx=1;
		gbc.gridy=6;
		Gender1.setEditable(false);
		Screen.add(Gender1, gbc);
		
		JLabel Phone= new JLabel("Phone: ");
		gbc.gridx=0;
		gbc.gridy=8;
		Screen.add(Phone, gbc);
		JTextField Phone1=new JTextField(phone,30);
		gbc.gridx=1;
		gbc.gridy=8;
		Phone1.setEditable(false);
		Screen.add(Phone1, gbc);
		
		JLabel Doc= new JLabel("Doctor Reference: ");
		gbc.gridx=0;
		gbc.gridy=10;
		Screen.add(Doc, gbc);
		JTextField Doc1=new JTextField(doc,30);
		gbc.gridx=1;
		gbc.gridy=10;
		Doc1.setEditable(false);
		Screen.add(Doc1, gbc);
		
		JLabel[] Test= new JLabel[size+1];
		JTextField[] Test_name= new JTextField[size+1];
		for(int i=0; i<=size; i++){
			Test[i]= new JLabel("Tests ");
			gbc.gridx=0;
			gbc.gridy=(2*i)+12;
			Screen.add(Test[i], gbc);
			Test_name[i]=new JTextField(test_name[i],30);
			gbc.gridx=1;
			gbc.gridy=(2*i)+12;
			Test_name[i].setEditable(false);
			Screen.add(Test_name[i], gbc);
		}

		gbc.anchor=GridBagConstraints.WEST;
		JButton Confirm= new JButton("CONFIRM");
		gbc.gridx=1;
		gbc.gridy=(2*(size+1))+12;
		Screen.add(Confirm,gbc);
		JButton Close= new JButton("CLOSE");
		gbc.anchor=GridBagConstraints.EAST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;		
		gbc.gridx=2;
		gbc.gridy=(2*(size+1))+12;
		Screen.add(Close,gbc);
		
		Close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				verify=true;
				//check();
			}
		});
		
		Confirm.addActionListener(new ActionListener(){
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				
				int response=JOptionPane.showConfirmDialog(null,"Do You Want To Continue...", "Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(response==JOptionPane.YES_OPTION){
					String name=Name1.getText();
					String age=Age1.getText();
					String gender=Gender1.getText();
					String	phone=Phone1.getText();
					String doc=Doc1.getText();
					Store s= new Store(name,age,gender,phone,doc,test_name,size,type);//type=0 for individual test and type=1 for group test
					frame.dispose();
					choose=0;
					verify=true;
					//check();
				}
			}
			
		});
		
	}
}
