package admin;

import java.io.File;

import javax.swing.JFileChooser;


public class Administrator {
	public static void main(String[] args) {
		File file=new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString()+"\\Blood Test");
		if(!file.exists()){file.mkdirs();}
		new Design();
		
	}

}
