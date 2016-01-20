package admin;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.jdt.internal.compiler.batch.Main;

import permissions.Log;



public class Window {
	
	public Image image,logo;
	public JFrame frame;
	public static String uname;
	
	public JFrame create_window(){
		
		frame=new JFrame("ADMINISTRATOR");
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		try {
			logo= new ImageIcon(Main.class.getResource("/images/logo.jpg")).getImage();
			frame.setIconImage(logo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
					Log l=new Log("S0",false,true);
					l.check();
			}
		});
		
		Permission p= new Permission();

		try {
			image= new ImageIcon(Main.class.getResource("/images/logo.jpg")).getImage();
			frame.setIconImage(image);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		frame.setEnabled(false);
		p.check();
		frame.setEnabled(true);
		uname=p.getname();
		frame.setAlwaysOnTop(true);
		frame.setAlwaysOnTop(false);
		return frame;
	}
}
