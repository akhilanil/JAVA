package report;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.eclipse.jdt.internal.compiler.batch.Main;

public class Window {

	public JFrame frame;
	private JLabel id,phone;
	public JTextField id1,phone1;
	public JPanel panel;
	public JButton enter;
	String idtext,phonetext;
	File pdf;
	public Window(File pdf){
		this.pdf=pdf;
		frame=new JFrame("Print Report");
		frame.setSize(300,200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(Main.class.getResource("/images/logo.jpg")).getImage());
		
		frame.addWindowListener(new WindowAdapter(){

			public void windowClosing(WindowEvent e) {
				pdf.delete();
			}

			
		});
		
		idtext="Enter Customer Id..";
		phonetext="Enter Phone Number..";
		
		id=new JLabel("Customer id:");
		phone=new JLabel("Phone: ");
		enter=new JButton("Enter");
		id1=new JTextField(idtext,15);
		phone1=new JTextField(phonetext,15);
		
		
		panel=new JPanel();
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc= new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		
		
		
		id1.addFocusListener(new FocusListener(){

			public void focusGained(FocusEvent arg0) {
				if(id1.getText().equals(idtext)==true){id1.setText("");}
				
				}
			public void focusLost(FocusEvent arg0) {
				if(id1.getText().equals("")==true){id1.setText(idtext);}
			}
			
		});
		
		phone1.addFocusListener(new FocusListener(){

			public void focusGained(FocusEvent arg0) {
				if(phone1.getText().equals(phonetext)==true){phone1.setText("");}
				
				}
			public void focusLost(FocusEvent arg0) {
				if(phone1.getText().equals("")==true){phone1.setText(phonetext);}
			}
			
		});
		
		phone1.addKeyListener(new KeyListener(){

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
		
		gbc.gridx=0;
		gbc.gridy=0;
		panel.add(id, gbc);
		
		gbc.gridx=1;
		panel.add(id1, gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		panel.add(phone, gbc);
		
		gbc.gridx=1;
		panel.add(phone1, gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.gridwidth=3;
		gbc.fill=(GridBagConstraints.HORIZONTAL);
		panel.add(enter, gbc);
		
		frame.add(panel);
		frame.validate();
	}
	
	private void listener(){
		if(id1.getText().equals(idtext) || phone1.getText().equals(phonetext)){
			JOptionPane.showMessageDialog(null,"Field Left Blank","Error..!!",JOptionPane.ERROR_MESSAGE);
		}
		else{
			new Validate(id1.getText(),phone1.getText(),pdf);
		}
	}
}
