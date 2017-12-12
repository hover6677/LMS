package com.db.mongodb.admin.helper;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.db.mongodb.DAO.ProcessDAO;
import com.db.mongodb.DAO.SampleDAO;
import com.db.mongodb.DAO.UserManagementDAO;
import com.document.enumeration.SampleKeyEnum;
import com.document.enumeration.TemplateTypeEnum;
import com.document.enumeration.UserManagementEnum;
import com.mongodb.BasicDBObject;
import com.ui.admin.mainFrame.UMUI;

import org.bson.Document;

public class UMController {

	UMUI umUI;
	UserManagementDAO dao;
	Document myDoc;
	public UMController(UMUI u) {
		umUI = u;
		dao = (UserManagementDAO) UserManagementDAO.getInstance();
	}
	
	public void setAction(String userName, String password, boolean isRev, boolean isPro, boolean isStr, boolean isRep) {
		if(userName==null||password==null) {
			JOptionPane.showMessageDialog(umUI, "Please fill username and password!");
			return;
		}
		myDoc = createDocument(userName, password, isRev, isPro, isStr, isRep);
		InsertAction ia = new InsertAction("com.db.mongodb.UserManagementDAO",myDoc);
		ia.action(dao, umUI);
		umUI.refresh();
		
//		if (dao.connDAO()) {
//	       	 dao.setCollection();
//	       	 if(dao.addOrUpdate(myDoc)) {
//	       		dao.closeDBConn();
//	       		JOptionPane.showMessageDialog(umUI, "Save Successful!");
//	       		umUI.refresh();
//	       	 }
//				
//	        }
	}
	public Document createDocument(String userName, String password, boolean isRev, boolean isPro, boolean isStr, boolean isRep)
	{
		Document doc = new Document();
		doc.append(UserManagementEnum.Active.toString(), 1);
		doc.append(UserManagementEnum.User.toString(), userName);
		doc.append(UserManagementEnum.DateTime.toString(), new Date());
		doc.append(UserManagementEnum.Password.toString(), password);
		Document viewDoc = new Document();
		if(isRev)viewDoc.append(TemplateTypeEnum.Receive.toString(), true);
		else viewDoc.append(TemplateTypeEnum.Receive.toString(), false);
		if(isPro)viewDoc.append(TemplateTypeEnum.Process.toString(), true);
		else viewDoc.append(TemplateTypeEnum.Process.toString(), false);
		if(isStr)viewDoc.append(TemplateTypeEnum.Storage.toString(), true);
		else viewDoc.append(TemplateTypeEnum.Storage.toString(), false);
		if(isRep)viewDoc.append(TemplateTypeEnum.Report.toString(), true);
		else viewDoc.append(TemplateTypeEnum.Report.toString(), false);
		doc.append(UserManagementEnum.View.toString(), viewDoc);
		return doc;
		
	}
	public ArrayList<ArrayList<String>> getData(){
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList fetchTemplate =null;
		if (dao.connDAO()) {
			Document fetchDoc = new Document();
			fetchDoc.append(UserManagementEnum.Active.toString(),1);
			dao.setCollection();
			//fetchTemplate = dao.fetch(fetchDoc);
			BasicDBObject query = new BasicDBObject();
			query.put(UserManagementEnum.Active.toString(), 1);
			fetchTemplate = dao.fetch(query);
			dao.closeDBConn();
		      if(fetchTemplate!=null&& fetchTemplate.size()>0) 
		      {
		    	  for(int i = 0; i<fetchTemplate.size();i++) {
		    		  ArrayList<String> subResult = new ArrayList<String>();
		    		  Document d = (Document) fetchTemplate.get(i);
		    		  String user = (String) d.get(UserManagementEnum.User.toString());
		    		  String DateTime = ((Date) d.get(UserManagementEnum.DateTime.toString())).toString();
		    		  Document viewD = (Document)d.get(UserManagementEnum.View.toString());
		    		  String Receive = ((boolean)viewD.get(TemplateTypeEnum.Receive.toString()))? "True":"False";
		    		  String Process = ((boolean)viewD.get(TemplateTypeEnum.Process.toString()))? "True":"False";
		    		  String Storage = ((boolean)viewD.get(TemplateTypeEnum.Storage.toString()))? "True":"False";
		    		  String Report = ((boolean)viewD.get(TemplateTypeEnum.Report.toString()))? "True":"False";
		    		  subResult.add(user);
		    		  subResult.add(DateTime);
		    		  subResult.add(Receive);
		    		  subResult.add(Process);
		    		  subResult.add(Storage);
		    		  subResult.add(Report);
		    		  result.add(subResult);
		    	  }
		      }

		}
		
		return result;
	}

}
