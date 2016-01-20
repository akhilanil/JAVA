package report;

import java.io.File;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.eclipse.jdt.internal.compiler.batch.Main;


public class CreateReport {

	public static void main(String[] args) {
		File file=new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString()+"\\Blood Test");
		if(!file.exists()){file.mkdirs();}
		String documents=new JFileChooser().getFileSystemView().getDefaultDirectory().toString()+"\\Blood Test";
		try{
			File fileimg=new File(documents+"\\Logo.jpg");
			File filesig=new File(documents+"\\Sgnature.jpg");
			if(!fileimg.exists()){Files.copy(Main.class.getResourceAsStream("/reports/Logo.jpg"),new File(documents+"\\Logo.jpg").toPath());}
			if(!filesig.exists()){Files.copy(Main.class.getResourceAsStream("/reports/Sgnature.jpg"),new File(documents+"\\Sgnature.jpg").toPath());}
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage()+"\n Rolled Back", e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
		}	
		File pdf=new File(documents+"\\print.pdf");
		new Window(pdf);
	}
}