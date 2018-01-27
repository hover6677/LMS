package com.ui.admin.mainframe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.bson.Document;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.db.mongodb.admin.helper.ExportToExcelAction;
import com.db.mongodb.admin.helper.JTablePrint;
import com.db.mongodb.admin.helper.LMSUtils;
import com.db.mongodb.admin.helper.ReportGenerator;
import com.db.mongodb.DAO.MaterialDAO;
import com.db.mongodb.DAO.ProcessDAO;
import com.db.mongodb.DAO.SampleDAO;
import com.db.mongodb.DAO.TemplateDAO;
import com.document.enumeration.MaterialKeyEnum;
import com.document.enumeration.ProcessKeyEnum;
import com.document.enumeration.SampleKeyEnum;
import com.document.enumeration.TemplateKeyEnum;
import com.document.enumeration.TemplateTypeEnum;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;

public class ReportUI extends JPanel {
	private ArrayList<String> labels;
	private ArrayList<ArrayList<String>> data;
	private JTextField textField;
	private JDatePickerImpl datePicker;
	private JDatePickerImpl datePicker2;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnExportToExcel;
	private JButton btnPrintButton;
	private JTextField text_Material;
	public ReportUI() {
		
		labels = new ArrayList<String>();
		data = new ArrayList<ArrayList<String>>();
		setLayout(null);
		setSize(740,400);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(12, 195, 693, 180);
		
		
		JLabel lblReport = new JLabel("Report");
		lblReport.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblReport.setBounds(12, 0, 115, 50);
		add(lblReport);
		

		panel_1.setLayout(new BorderLayout());
		
		add(panel_1);
		
		scrollPane = new JScrollPane();
		//scrollPane.setBounds(12, 13, 671, 122);
		panel_1.add(scrollPane,BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBorder(new EmptyBorder(1, 1, 0, 0));
		
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				TableModel tm =(TableModel)e.getSource();
				if(tm.getRowCount()>0) {
					btnExportToExcel.setEnabled(true);
				}else {
					btnExportToExcel.setEnabled(false);
				}
			}
			
		});
		
		JButton button = new JButton("Search");
		button.setToolTipText("Please fill Sample ID or Material ID. If both are provided, only Sample ID will be used.");
		button.setBounds(421, 58, 115, 30);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				upda();
			}
			
		});
		add(button);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(203, 27, 182, 23);
		add(textField);
		
		JLabel label = new JLabel("SID");
		label.setBounds(105, 30, 89, 16);
		add(label);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(105, 98, 89, 16);
		add(lblFrom);
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(203, 95, 213, 30);
		add(datePicker);
		
		JButton btnFilterByDate = new JButton("Filter By Date");
		btnFilterByDate.setBounds(421, 141, 115, 30);
		btnFilterByDate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updaDate();
			}
			
		});
		add(btnFilterByDate);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(105, 138, 89, 16);
		add(lblTo);
		
		
		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p);
		datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		datePicker2.setBounds(203, 141, 213, 30);
		add(datePicker2);
		
		btnExportToExcel = new JButton("Export To Excel");
		btnExportToExcel.setEnabled(false);
		btnExportToExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ExportToExcelAction eeAction = new ExportToExcelAction(table,"adminReport");
				
			}
		});
		btnExportToExcel.setBounds(544, 375, 154, 25);
		add(btnExportToExcel);
		
		btnPrintButton = new JButton("Print Table");
		btnPrintButton.setEnabled(false);
		btnPrintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTablePrint tPrint = new JTablePrint(table);
				tPrint.Execute();
			}
		});
		btnPrintButton.setBounds(382, 375, 154, 25);
		add(btnPrintButton);
		
		JLabel lblMaterialId = new JLabel("Material ID");
		lblMaterialId.setBounds(105, 65, 89, 16);
		add(lblMaterialId);
		
		text_Material = new JTextField();
		text_Material.setColumns(10);
		text_Material.setBounds(203, 62, 182, 23);
		add(text_Material);
			
		
	}

	public void getSampleData(ArrayList<String> resultList,ArrayList<String> labelList)
	{

		 ArrayList<String> result = null;
		  ArrayList fetchTemplate =null;
		  Document sampleDoc = new Document();
		  BasicDBObject ref = new BasicDBObject();
		  ref.put(SampleKeyEnum.Active.toString(), 1);
		  ref.put(SampleKeyEnum.SID.toString(), Pattern.compile(textField.getText() , Pattern.CASE_INSENSITIVE));
		  
//		  sampleDoc.append(SampleKeyEnum.Active.toString(), 1);
//		  sampleDoc.append(SampleKeyEnum.SID.toString(), "?i:"+textField.getText());

	      if (SampleDAO.getInstance().connDAO()) {
	    	  SampleDAO.getInstance().setCollection();
	          //fetchTemplate = SampleDAO.getInstance().fetch(sampleDoc);
	    	  fetchTemplate = SampleDAO.getInstance().fetch(ref);
	      }
	      
	      ProcessDAO.getInstance().closeDBConn();
	      
	      if(fetchTemplate!=null&& fetchTemplate.size()>0) {
	     	 
	    	  for(int i = 0; i<fetchTemplate.size();i++) {
	    		  result = new ArrayList<String>();
	    		  Document d=(Document)(fetchTemplate.get(i));
	    		  System.out.println(((Date)d.get(SampleKeyEnum.DateTime.toString())).getTime());
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
	
	private void getSampleData(Hashtable<String, ArrayList<String>> resultTable,
			Hashtable<String,ArrayList<String>> labelTable) {
		// TODO Auto-generated method stub
		ArrayList<String> result = null;
		ArrayList<String> label = null;
		  ArrayList fetchTemplate =null;
		  Document sampleDoc = new Document();
		  sampleDoc.append(SampleKeyEnum.Active.toString(), 1);
		  //sampleDoc.append(SampleKeyEnum.SID.toString(), textField.getText());
		  Date fromDate = (Date) datePicker.getModel().getValue();
		  Date toDate = (Date) datePicker2.getModel().getValue();
		  if(fromDate==null ||toDate == null )return;
		  fromDate = resetDate(fromDate);
		  toDate = resetDate(toDate);

		  if(fromDate.compareTo(toDate)==0)
			  toDate = AddOneDay(toDate);
		  else if(fromDate.compareTo(toDate)>0) {
			  JOptionPane.showMessageDialog(this, "From date must be before To date");
			  return;
		  }
		  System.out.println(fromDate);
		  System.out.println(toDate);
		  BasicDBObject query = new BasicDBObject();
		  query.put(SampleKeyEnum.DateTime.toString(), new BasicDBObject("$gt", fromDate).append("$lt", toDate));
		  query.put(SampleKeyEnum.Active.toString(), 1);
		  
		  
	      if (SampleDAO.getInstance().connDAO()) {
	    	  SampleDAO.getInstance().setCollection();
	          fetchTemplate = ((SampleDAO)SampleDAO.getInstance()).fetch(query);
	      }
	      ProcessDAO.getInstance().closeDBConn();
	      
	      if(fetchTemplate!=null&& fetchTemplate.size()>0) {
	     	 String SID=null;
	    	  for(int i = 0; i<fetchTemplate.size();i++) {
	    		  
	    		  result = new ArrayList<String>();
	    		  label = new ArrayList<String>();
	    		  Document d=(Document)(fetchTemplate.get(i));
	    		  if(d.containsKey(SampleKeyEnum.SID.toString())) {
	    			  SID =(String) d.get(SampleKeyEnum.SID.toString());
	    			  
	    		  }
	    		  System.out.println(((Date)d.get(SampleKeyEnum.DateTime.toString())).getTime());
	    		  if(d.containsKey(TemplateTypeEnum.Receive.toString()))
	    		  {
	    			 Document receivedoc= (Document) d.get(TemplateTypeEnum.Receive.toString());
	    			 Set<String> set = receivedoc.keySet();
	    			 for(String s: set) {
	    				 result.add((String) receivedoc.get(s));
	    				 label.add(s);
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
		    					 label.add(subS);
		    				 }
	    				 }
	    				 else if(storagedoc.get(s) instanceof String)
	    					 
	    				 {
	    					 result.add((String)storagedoc.get(s));
	    					 label.add(s);
	    				 }
	    				 
	    			 }
	
	    			 
	    		  }
	    		  resultTable.put(SID,result); 
	    		  labelTable.put(SID,label); 
	    	  }
	    	  
	      }
	}

	public void getProcessData(ArrayList<String> resultList,ArrayList<String> labelList) {

		
		 ArrayList<String> result = new ArrayList<String>();
		  ArrayList fetchTemplate =null;
		  BasicDBObject ref = new BasicDBObject();
		  ref.append(ProcessKeyEnum.Active.toString(), 1);
		  ref.put(SampleKeyEnum.SID.toString(), Pattern.compile(textField.getText() , Pattern.CASE_INSENSITIVE));
		  
//		  Document processDoc = new Document();
//		  processDoc.append(ProcessKeyEnum.Active.toString(), 1);
//		  processDoc.append(ProcessKeyEnum.SID.toString(), textField.getText());
	      if (ProcessDAO.getInstance().connDAO()) {
	          ProcessDAO.getInstance().setCollection();
	          fetchTemplate = ProcessDAO.getInstance().fetch(ref);
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
	private void getProcessData(Hashtable<String, ArrayList<String>> resultTable,
				Hashtable<String,ArrayList<String>> labelTable) {
			// TODO Auto-generated method stub
			String SID = null;
			  ArrayList fetchTemplate =null;
	//		  Document processDoc = new Document();
	//		  processDoc.append(ProcessKeyEnum.Active.toString(), 1);
	//		  processDoc.append(ProcessKeyEnum.SID.toString(), textField.getText());
			  BasicDBObject query = new BasicDBObject();
			  query.put(ProcessKeyEnum.Active.toString(), 1);
			  Set<String> keySet = resultTable.keySet();
			  
			  query.put(ProcessKeyEnum.SID.toString(),  new BasicDBObject("$in", keySet));
		      if (ProcessDAO.getInstance().connDAO()) {
		          ProcessDAO.getInstance().setCollection();
		          fetchTemplate = ((ProcessDAO)ProcessDAO.getInstance()).fetch(query);
		      }
		      ProcessDAO.getInstance().closeDBConn();
		      if(fetchTemplate!=null&& fetchTemplate.size()>0) {
		    	  for(int i = 0; i<fetchTemplate.size();i++) {
		    			 ArrayList<String> result = new ArrayList<String>();
		    			 ArrayList<String> label = new ArrayList<String>();
		    		  Document d=(Document)(fetchTemplate.get(i));
		    		  if(d.containsKey(ProcessKeyEnum.SID.toString())) {
		    			  SID = (String) d.get(ProcessKeyEnum.SID.toString());
		    		  }
		    		  if(d.containsKey(ProcessKeyEnum.Steps.toString())) {
		    			  Document stepDoc = (Document) d.get(ProcessKeyEnum.Steps.toString());
		    			  Set<String> set = stepDoc.keySet();
		    			  for(String s: set) {
		    				  result.add((String) stepDoc.get(s));
		    				  label.add(s);
		    			  }
		    		  }
		    	      // must be after sample
		    	      ArrayList ori = resultTable.get(SID);
		    	      if(ori!=null) {
		    	    	  ori.addAll(result);
		    	    	  
		    	      }
		    	      ori = labelTable.get(SID);
		    	      if(ori!=null) {
		    	    	  ori.addAll(label);
		    	      }
		    	  }
		      }
	
		}

	private void getMaterialData(ArrayList<String> resultList, ArrayList<String> labelList) {
		// TODO Auto-generated method stub
		  ArrayList<String> result = new ArrayList<String>();
		  ArrayList fetchMaterial =null;
		  BasicDBObject ref = new BasicDBObject();
		  ref.append(ProcessKeyEnum.Active.toString(), 1);
		  ref.put(MaterialKeyEnum.MID.toString(), Pattern.compile(text_Material.getText() , Pattern.CASE_INSENSITIVE));
		  
//		  Document processDoc = new Document();
//		  processDoc.append(ProcessKeyEnum.Active.toString(), 1);
//		  processDoc.append(ProcessKeyEnum.SID.toString(), textField.getText());
	      if (MaterialDAO.getInstance().connDAO()) {
	    	  MaterialDAO.getInstance().setCollection();
	          fetchMaterial = MaterialDAO.getInstance().fetch(ref);
	      }
	      MaterialDAO.getInstance().closeDBConn();
	      if(fetchMaterial!=null&& fetchMaterial.size()>0) {
	    	  for(int i = 0; i<fetchMaterial.size();i++) {
	    		  Document d=(Document)(fetchMaterial.get(i));
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
		
//		ArrayList<String> resultList = new ArrayList<String>();
//		ArrayList<String> labelList = new ArrayList<String>();
//		ReadData(resultList, labelList);
//		Object[][] dataArray = { (Object[]) resultList.toArray()};
//		Object[] labelArray =  labelList.toArray();
//		
//
//		if(LMSUtils.checkEmpty(dataArray,labelArray,this))return;
//		
//		
//		table = new JTable(dataArray, labelArray);
//		table.updateUI();
//		if(table.getModel().getRowCount()>0)
//		{
//				btnExportToExcel.setEnabled(true);
//				btnPrintButton.setEnabled(true);
//		}
//		scrollPane.setViewportView(table);
//		scrollPane.updateUI();
		ReportGenerator rg= new ReportGenerator(textField,text_Material,this,table,btnExportToExcel);
		//System.out.println(rg.getTableModel().getColumnCount());
		table.setModel(rg.getTableModel());
		table.updateUI();
		btnExportToExcel.setEnabled(true);
		this.updateUI();
	}
	public void updaDate() {
		ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>>labelList = new ArrayList<ArrayList<String>>();
		ReadDataFromDate(resultList,labelList);
		if(resultList==null||labelList==null||resultList.size()<=0||labelList.size()<=0) {
			//checkEmpty(null,null);
			LMSUtils.checkEmpty(null, null, this);
			return;
		}
			
		int size1 = resultList.size();
		int size2 = labelList.get(0).size();
		Object[][] dataArray = new Object[size1][size2];
		for(int i =0; i<size1;i++)
		{
			dataArray[i]=resultList.get(i).toArray();
		}
		Object[] labelArray = labelList.get(0).toArray();
		if(LMSUtils.checkEmpty(dataArray,labelArray,this))return;
		table= new JTable(dataArray, labelArray);
		table.updateUI();
		if(table.getModel().getRowCount()>0)
			btnExportToExcel.setEnabled(true);
		scrollPane.setViewportView(table);
		scrollPane.updateUI();
	
		this.updateUI();
		
		
	}

//	private boolean checkEmpty(Object[][] dataArray, Object[] labelArray) {
//		// TODO Auto-generated method stub
//		if(labelArray==null||dataArray == null||labelArray.length==0||dataArray.length==0)
//		{
//			JOptionPane.showMessageDialog(this, "There are no results that match your search");
//			return true;
//		}
//		return false;
//		
//	}

	public void ReadData(ArrayList<String> resultList,ArrayList<String> labelList)
	{
	    if((textField.getText()==null||textField.getText().equals(""))&&(text_Material.getText()==null||text_Material.getText().equals(""))) {
			  JOptionPane.showMessageDialog(this, "Please enter a SID or a Material ID");
			  return;
		}
	    if((textField.getText()!=null&&!textField.getText().equals(""))) {
			getSampleData(resultList,labelList);
			if(resultList.size()==0||labelList.size()==0)return;
			getProcessData(resultList,labelList);
			return;
	    }else
	    {
	    	getMaterialData(resultList,labelList);
	    }
		
	
	
		
		
	}


	private void ReadDataFromDate(ArrayList<ArrayList<String>> resultList, ArrayList<ArrayList<String>> labelList) {
		// TODO Auto-generated method stub
		Hashtable<String, ArrayList<String>> resultTable = new Hashtable<String, ArrayList<String>>();
		Hashtable<String, ArrayList<String>> labelTable = new Hashtable<String, ArrayList<String>>();
		getSampleData(resultTable,labelTable);
		if(resultTable.size()==0||labelTable.size()==0)return;
		getProcessData(resultTable,labelTable);
		Set<String> set = resultTable.keySet();
		for(String s:set) {
			resultList.add(resultTable.get(s));
			labelList.add(labelTable.get(s));
		}
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
	private Date resetDate(Date d)
	{
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(d);
	      cal.set(Calendar.HOUR_OF_DAY, 0);  
	      cal.set(Calendar.MINUTE, 0);  
	      cal.set(Calendar.SECOND, 0);  
	      cal.set(Calendar.MILLISECOND, 0);  
	      return cal.getTime(); 

		  
	}
	private Date AddOneDay(Date d)
	{
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(d);
	      cal.set(Calendar.HOUR_OF_DAY, 23);  
	      cal.set(Calendar.MINUTE, 59);  
	      cal.set(Calendar.SECOND, 59);  
	      cal.set(Calendar.MILLISECOND, 999);  
	      return cal.getTime(); 

		  
	}
}
class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}
