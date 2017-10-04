package com.ui.admin;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import org.bson.Document;

import com.db.mongodb.ProcessDAO;
import com.db.mongodb.TemplateDAO;
import com.document.enumeration.ProcessKeyEnum;
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

	public void ReadData()
	{
		//Read Data from Database to data
//		ArrayList<String> dataRecorder = new ArrayList<String>();
//		dataRecorder.add("Tom");
//		dataRecorder.add("1313");
//		dataRecorder.add("36");
//		data.add(dataRecorder);
//		dataRecorder = new ArrayList<String>();
//		dataRecorder.add("Allen");
//		dataRecorder.add("2351");
//		dataRecorder.add("37");


	    
      
		
		
      Document processDoc = new Document();
      processDoc.append(ProcessKeyEnum.Active.toString(), 1);
      processDoc.append(ProcessKeyEnum.SID.toString(), "CocaCola");
      processDoc.append(ProcessKeyEnum.TID.toString(), "CocaTemp1");

      if (ProcessDAO.getInstance().connDAO()) {
          ProcessDAO.getInstance().setCollection();
          ArrayList fetchTemplate = ProcessDAO.getInstance().fetch(processDoc);
          System.out.println(fetchTemplate.toString());
      }
      ProcessDAO.getInstance().closeDBConn();
      
  
		
		
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
		ArrayList<String> processlabelArray = null;
		processlabelArray = ReadLabels(TemplateTypeEnum.Process.toString());
		ArrayList<String> receivelabelArray = null;
		receivelabelArray = ReadLabels(TemplateTypeEnum.Receive.toString());
		ArrayList<String> storagelabelArray = null;
		storagelabelArray = ReadLabels(TemplateTypeEnum.Storage.toString());
		ArrayList<String> finalArray = new ArrayList<String>();
		if(processlabelArray!=null)
			finalArray.addAll(processlabelArray);
		if(receivelabelArray!=null)
			finalArray.addAll(receivelabelArray);
		if(storagelabelArray!=null)
			finalArray.addAll(storagelabelArray);
		
		
		String[][] dataArray= {
				{"Tom","1313","36"},
				{"Allen","2351","37.5"}
		};
		panel_1.setLayout(null);
		
		add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 649, 255);
		panel_1.add(scrollPane);
		
		JTable table = new JTable(dataArray, finalArray.toArray());
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
    			  return tag;

    		  }
    	  }
    	  
      }
      return null;
	}
}
