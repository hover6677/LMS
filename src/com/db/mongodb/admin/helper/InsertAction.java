package com.db.mongodb.admin.helper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import org.bson.Document;

import com.db.mongodb.DAO.AbstractDAO;
import com.db.mongodb.DAO.ProcessDAO;
import com.document.enumeration.ProcessKeyEnum;


public class InsertAction  {
	
	Class DAOClass;
	Document document;
	public InsertAction(String cName,Document d) {
		setDAOClass(cName);
		setDocument(d);
	}
	
	public void action(AbstractDAO dao, JComponent parent) {
		// TODO Auto-generated method stub
        
//        try {
//
//        	
//        	Object instance = DAOClass.getMethod("getInstance").invoke(null);
//        	
//         if ((boolean) DAOClass.getMethod("connDAO" ).invoke(instance)) {
//        	 DAOClass.getMethod("setCollection").invoke(instance);
//        	 if((boolean) DAOClass.getMethod("addOrUpdate",Document.class).invoke(instance, document))
//        		 DAOClass.getMethod("closeDBConn").invoke(instance);
//			
//         }
//        } catch (IllegalAccessException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IllegalArgumentException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (InvocationTargetException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (NoSuchMethodException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (SecurityException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 
		if (dao.connDAO()) {
       	 dao.setCollection();
       	 if(dao.addOrUpdate(document)) {
       		dao.closeDBConn();
       		JOptionPane.showMessageDialog(parent, "Save Successful!");
       	 }
			
        }
	}
	public void setDAOClass(String className) {
		try {
			Class c = Class.forName(className);
			DAOClass = c;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void setDocument(Document d)
	{
		document = d;
	}
	
}
