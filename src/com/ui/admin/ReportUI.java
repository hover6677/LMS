package com.ui.admin;

import java.util.ArrayList;
import java.util.Set;

import javax.swing.*;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import org.bson.Document;

import com.db.mongodb.ProcessDAO;
import com.db.mongodb.SampleDAO;
import com.db.mongodb.TemplateDAO;
import com.document.enumeration.ProcessKeyEnum;
import com.document.enumeration.SampleKeyEnum;
import com.document.enumeration.TemplateKeyEnum;
import com.document.enumeration.TemplateTypeEnum;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;

public class ReportUI extends JPanel {
	private ArrayList<String> labels;
	private ArrayList<ArrayList<String>> data;
	private JTable table_1;
	public ReportUI() {
		
		upda();
		
		
		
	}

	public void ReadData(ArrayList<String> resultList,ArrayList<String> labelList)
	{

		getSampleData(resultList,labelList);
		getProcessData(resultList,labelList);
		
      
  
		
		
	}
	public void getSampleData(ArrayList<String> resultList,ArrayList<String> labelList)
	{

		 ArrayList<String> result = null;
		  ArrayList fetchTemplate =null;
		  Document sampleDoc = new Document();
		  sampleDoc.append(SampleKeyEnum.Active.toString(), 1);
		  sampleDoc.append(SampleKeyEnum.SID.toString(), "coca-classic");
	      if (SampleDAO.getInstance().connDAO()) {
	    	  SampleDAO.getInstance().setCollection();
	          fetchTemplate = SampleDAO.getInstance().fetch(sampleDoc);
	      }
	      ProcessDAO.getInstance().closeDBConn();
	      
	      if(fetchTemplate!=null&& fetchTemplate.size()>0) {
	     	 
	    	  for(int i = 0; i<fetchTemplate.size();i++) {
	    		  result = new ArrayList<String>();
	    		  Document d=(Document)(fetchTemplate.get(i));
	    		  if(d.containsKey(TemplateTypeEnum.Receive.toString()))
	    		  {
	    			 Document receivedoc= (Document) d.get(TemplateTypeEnum.Receive.toString());
	    			 Set<String> set = receivedoc.keySet();
	    			 for(String s: set) {
	    				 result.add((String) receivedoc.get(s));
	    				 labelList.add(s);
	    			 }

	    			 
	    		  }
	    		  if(d.containsKey(TemplateTypeEnum.Storage.toString()))
	    		  {
	    			 Document storagedoc= (Document) d.get(TemplateTypeEnum.Storage.toString());
	    			 Set<String> set = storagedoc.keySet();
	    			 for(String s: set) {
	    				 if(storagedoc.get(s) instanceof Document) {
		    				 Document subStoragedoc = (Document) storagedoc.get(s);
		    				 Set<String> subSet = subStoragedoc.keySet();
		    				 for(String subS: subSet) {
		    					 result.add( subStoragedoc.get(subS).toString());
		    					 labelList.add(subS);
		    				 }
	    				 }
	    				 else if(storagedoc.get(s) instanceof String)
	    					 
	    				 {
	    					 result.add((String)storagedoc.get(s));
	    					 labelList.add(s);
	    				 }
	    				 
	    			 }

	    			 
	    		  }
	    		 resultList.addAll(result); 
	    	  }
	    	  
	      }
	      //processDoc.append(ProcessKeyEnum.TID.toString(), "CocaTemp1");
	}
	
	public void getProcessData(ArrayList<String> resultList,ArrayList<String> labelList) {

		
		 ArrayList<String> result = new ArrayList<String>();
		  ArrayList fetchTemplate =null;
		  Document processDoc = new Document();
		  processDoc.append(ProcessKeyEnum.Active.toString(), 1);
		  processDoc.append(ProcessKeyEnum.SID.toString(), "coca-classic");
	      if (ProcessDAO.getInstance().connDAO()) {
	          ProcessDAO.getInstance().setCollection();
	          fetchTemplate = ProcessDAO.getInstance().fetch(processDoc);
	      }
	      ProcessDAO.getInstance().closeDBConn();
	      if(fetchTemplate!=null&& fetchTemplate.size()>0) {
	    	  for(int i = 0; i<fetchTemplate.size();i++) {
	    		  Document d=(Document)(fetchTemplate.get(i));
	    		  if(d.containsKey(ProcessKeyEnum.Steps.toString())) {
	    			  Document stepDoc = (Document) d.get(ProcessKeyEnum.Steps.toString());
	    			  Set<String> set = stepDoc.keySet();
	    			  for(String s: set) {
	    				  result.add((String) stepDoc.get(s));
	    				  labelList.add(s);
	    			  }
	    		  }
	    	  }
	      }
	      resultList.addAll(result);
	      
	}
	public void upda() {
		labels = new ArrayList<String>();
		data = new ArrayList<ArrayList<String>>();
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(12, 45, 673, 281);
		
		
		JLabel lblReport = new JLabel("Report");
		lblReport.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblReport.setBounds(12, 0, 115, 50);
		add(lblReport);
		
		//ReadLabels();
		//ReadData();
//		ArrayList<String> processlabelArray = null;
//		processlabelArray = ReadLabels(TemplateTypeEnum.Process.toString());
//		ArrayList<String> receivelabelArray = null;
//		receivelabelArray = ReadLabels(TemplateTypeEnum.Receive.toString());
//		ArrayList<String> storagelabelArray = null;
//		storagelabelArray = ReadLabels(TemplateTypeEnum.Storage.toString());
//		ArrayList<String> finalArray = new ArrayList<String>();
//		if(processlabelArray!=null)
//			finalArray.addAll(processlabelArray);
//		if(receivelabelArray!=null)
//			finalArray.addAll(receivelabelArray);
//		if(storagelabelArray!=null)
//			finalArray.addAll(storagelabelArray);
		ArrayList<String> resultList = new ArrayList<String>();
		ArrayList<String> labelList = new ArrayList<String>();
		ReadData(resultList, labelList);
		Object[][] dataArray = { (Object[]) resultList.toArray()};
		Object[] labelArray =  labelList.toArray();
//		String[][] dataArray= {
//				{"Tom","1313","36","36"},
//				{"Allen","2351","37.5","36"},
//				{"Allen","2351","37.5","36"},
//				{"Allen","2351","37.5","36"}
//		};
		panel_1.setLayout(null);
		
		add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 649, 255);
		panel_1.add(scrollPane);
		
		JTable table = new JTable(dataArray, labelArray);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBorder(new EmptyBorder(1, 1, 0, 0));
		
		this.updateUI();
	}
	public ArrayList<String> ReadLabels(String type)
	{
      Document templateDoc = new Document();
      templateDoc.append(TemplateKeyEnum.Active.toString(), 1);
      templateDoc.append(TemplateKeyEnum.Type.toString(), type);
      templateDoc.append(TemplateKeyEnum.User.toString(), "Admin");
      ArrayList fetchTemplate = null;
      if (TemplateDAO.getInstance().connDAO()) {
          TemplateDAO.getInstance().setCollection();
          fetchTemplate = TemplateDAO.getInstance().fetch(templateDoc);
          //System.out.println(fetchTemplate.get(0).getClass());
          
      }
      TemplateDAO.getInstance().closeDBConn();
      if(fetchTemplate!=null&& fetchTemplate.size()>0) {
    	 
    	  for(int i = 0; i<fetchTemplate.size();i++) {
    		  Document d=(Document)(fetchTemplate.get(i));
    		  if(d.containsKey("Tags"))
    		  {
    			  ArrayList<String> tag = (ArrayList<String>) d.get("Tags");
    			  if(type.equals(TemplateTypeEnum.Storage.toString()))
    			  {
    				  for(int j =0; j<tag.size();j++)
    				  {
    					  if(tag.get(j).equals("Comments")||tag.get(j).equals("User"))
    						  break;
    					  if(++j<=tag.size()) {
    						  tag.add(j, "Unit");
    					  }
    				  }
    			  }
    			  return tag;

    		  }
    	  }
    	  
      }
      return null;
	}
}
