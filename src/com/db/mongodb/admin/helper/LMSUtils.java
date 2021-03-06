package com.db.mongodb.admin.helper;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LMSUtils {
	public static boolean checkEmpty(Object[][] dataArray, Object[] labelArray, JPanel panel) {
		// TODO Auto-generated method stub
		if(labelArray==null||dataArray == null||labelArray.length==0||dataArray.length==0)
		{
			JOptionPane.showMessageDialog(panel, "There are no results that match your search");
			return true;
		}
		return false;
		
	}
	public static String getConfigFolderPath()
	{
		String path = System.getProperty("user.dir");
		if (path.matches("(.*)src$")) {
			return "config";
		}
		else return "src/config";
	}

}
