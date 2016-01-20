package lab_screen;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import org.eclipse.jdt.internal.compiler.batch.Main;

import permissions.Log;
import permissions.Staff;
public class Window {
	
	public JFrame frame;
	public  String uname,uid;
	public Image image;
	public JFrame createWindow(){
		frame= new JFrame("Blood Test");
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
					Log l=new Log(uid,false,true);
					l.check();
			}
		});
		Staff s= new Staff();
		try {
			
			image= new ImageIcon(Main.class.getResource("/images/logo.jpg")).getImage();
			frame.setIconImage(image);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		frame.setEnabled(false);
		s.check();	
		frame.setEnabled(true);
		this.uname=s.getname();
		frame.setAlwaysOnTop(true);
		frame.setAlwaysOnTop(false);
		uid=s.getid();
		return frame;
	}	
	public String getuname(){
		return this.uname;
		}
}


