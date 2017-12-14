package com.ui.admin.mainapp;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import com.ui.admin.mainframe.AdminMainFrame;
import com.ui.user.mainframe.UserMainFrame;

public class AdminUserView {

	public static void main(String args[])
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
			AdminMainFrame frame = new AdminMainFrame();
			new UserMainFrame().setVisible(true);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
            }
	});
	}
}
