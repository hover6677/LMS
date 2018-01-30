package com.db.mongodb.admin.helper;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.bson.Document;
import org.jdatepicker.impl.JDatePickerImpl;

import com.db.mongodb.DAO.ProcessDAO;
import com.db.mongodb.DAO.SampleDAO;
import com.db.mongodb.user.helper.MaterialDAOHelper;
import com.db.mongodb.user.helper.ProcessDAOHelper;
import com.db.mongodb.user.helper.SampleDAOHelper;
import com.document.enumeration.MaterialKeyEnum;
import com.document.enumeration.ProcessKeyEnum;
import com.document.enumeration.SampleKeyEnum;
import com.document.enumeration.TemplateTypeEnum;
import com.mongodb.BasicDBObject;

public class ReportGenerator {
	private JTextField sIDTextField;
	private Component parentFrame;
	private JTable table;
	private JButton excelBtn;
	private JTextField mIDTextField;
	private Boolean isSIDSearch;
	private JDatePickerImpl datePickerFrom;
	private JDatePickerImpl datePickerTo;
	public ReportGenerator(JTextField sid,JTextField mid, Component p, JTable t, JButton b) {
		sIDTextField=sid;
		mIDTextField = mid;
		parentFrame=p;
		table=t;
		excelBtn=b;
		
		if(textBoxVerify())
		{
			updateSingleRow();
		}
		
	}
	public ReportGenerator(JDatePickerImpl From, JDatePickerImpl To, Component p, JTable t, JButton b) {
		parentFrame=p;
		table=t;
		excelBtn=b;
		datePickerFrom = From;
		datePickerTo = To;
		ArrayList<String> sampleList =this.getSIDListFromSampleDataDate();
		updateRowbyDate(sampleList);
		
	}
	private void updateRowbyDate(ArrayList<String> sampleList) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> resultData = new ArrayList<ArrayList<String>>();
		ArrayList<String> labelData = new ArrayList<String>();
		getDataFromSIDList(resultData,labelData,sampleList);
		Object[][] dataArray= new Object[resultData.size()][resultData.get(0).size()];
        for(int i=0;i<resultData.size();i++) {
        	dataArray[i]=resultData.get(i).toArray();
        }
        Object[] labelArray =  labelData.toArray();
        
        table=new JTable(dataArray,labelArray);
        table.setGridColor(Color.BLACK);
        this.table.updateUI();
		if(this.table.getModel().getRowCount()>0)
		{
				this.excelBtn.setEnabled(true);
				
		}
	}
	public TableModel getTableModel() {
		
		return table.getModel();
	}
	public boolean textBoxVerify()
	{
        if ((this.sIDTextField.getText().trim() == null || this.sIDTextField.getText().trim().equals(""))&&(this.mIDTextField.getText().trim() == null || this.mIDTextField.getText().trim().equals(""))) {
            JOptionPane.showMessageDialog(sIDTextField, "Please enter a SID or a MID");
            return false;
        }
        if(this.sIDTextField.getText().trim() != null && !this.sIDTextField.getText().trim().equals("")) {
        	isSIDSearch=true;
	        if (!SampleDAOHelper.fetchSampleBySID(this.sIDTextField.getText().replaceAll(" ", ""))) {
	            JOptionPane.showMessageDialog(sIDTextField, "SID Not Found");
	            return false;
	        }
        }else {
        	isSIDSearch=false;
        	if (!MaterialDAOHelper.fetchMaterialByMID(this.mIDTextField.getText().replaceAll(" ", ""))) {
	            JOptionPane.showMessageDialog(sIDTextField, "MID Not Found");
	            return false;
	        }
        }
        return true;
	}
    public void updateSingleRow() {

        //DefaultTableModel model = (DefaultTableModel) table.getModel();
        //model.setRowCount(0);
       
        ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
        ArrayList<String> labelList = new ArrayList<String>();
        ReadData(resultList, labelList);
        //Object[] rArray = resultList.toArray();
        //model.insertRow(0, rArray);
        //Object[][] dataArray = { (Object[]) resultList.toArray()};
        Object[][] dataArray= new Object[resultList.size()][resultList.get(0).size()];
        for(int i=0;i<resultList.size();i++) {
        	dataArray[i]=resultList.get(i).toArray();
        }
        Object[] labelArray =  labelList.toArray();
        
        table=new JTable(dataArray,labelArray);
        table.setGridColor(Color.BLACK);
        this.table.updateUI();
		if(this.table.getModel().getRowCount()>0)
		{
				this.excelBtn.setEnabled(true);
				
		}
    }
    public void ReadData(ArrayList<ArrayList<String>> resultList, ArrayList<String> labelList) {
    	if(isSIDSearch) {
    		labelList.addAll(getSampleData(resultList));
	        if (resultList.isEmpty() || labelList.isEmpty()) {
	            return;
	        }
	
	        labelList.addAll(getProcessData(resultList,sIDTextField.getText().trim()));
	
	        if (resultList.get(resultList.size()-1).size() != labelList.size()) {
	            JOptionPane.showMessageDialog(parentFrame, "Records Display could be error");
	        }
    	}else {
    		getMaterailData(resultList, labelList);
    	}
    }
    private void getMaterailData(ArrayList<ArrayList<String>> resultList, ArrayList<String> labelList) {
		// TODO Auto-generated method stub
    	ArrayList<String> sampleList = new ArrayList<String>();
		if(MaterialDAOHelper.getMaterial().containsKey(MaterialKeyEnum.SampleList.toString())) {
			
			sampleList = (ArrayList<String>) MaterialDAOHelper.getMaterial().get(MaterialKeyEnum.SampleList.toString());	            
			
		}
		else {
			JOptionPane.showMessageDialog(parentFrame, "Sample list is empty. No matched sid found");
			return;
		}
		getDataFromSIDList(resultList,labelList,sampleList);
//		for(int i = 0; i<sampleList.size();i++) {
//			 if (!SampleDAOHelper.fetchSampleBySID(sampleList.get(i))) {
//				 JOptionPane.showMessageDialog(parentFrame, "SID "+sampleList.get(0)+ "is not found");
//				 return;
//			 }
//			if(i ==0) {
//
//				labelList.addAll(getSampleData(resultList));
//			
//		        if (resultList.isEmpty() || labelList.isEmpty()) {
//		            return;
//		        }
//		        	labelList.addAll(getProcessData(resultList,sampleList.get(i)));
//			}
//			else {
//					getSampleData(resultList);
//				
//					if (resultList.isEmpty() || labelList.isEmpty()) {
//						return;
//					}
//		        	getProcessData(resultList,sampleList.get(i));
//			}
//		}
	}
    private void getDataFromSIDList(ArrayList<ArrayList<String>> resultList, ArrayList<String> labelList,ArrayList<String>sampleList) {
    	for(int i = 0; i<sampleList.size();i++) {
			 if (!SampleDAOHelper.fetchSampleBySID(sampleList.get(i))) {
				 JOptionPane.showMessageDialog(parentFrame, "SID "+sampleList.get(0)+ "is not found");
				 return;
			 }
			if(i ==0) {

				labelList.addAll(getSampleData(resultList));
			
		        if (resultList.isEmpty() || labelList.isEmpty()) {
		            return;
		        }
		        	labelList.addAll(getProcessData(resultList,sampleList.get(i)));
			}
			else {
					getSampleData(resultList);
				
					if (resultList.isEmpty() || labelList.isEmpty()) {
						return;
					}
		        	getProcessData(resultList,sampleList.get(i));
			}
		}
    }
	public ArrayList<String> getSampleData(ArrayList<ArrayList<String>> resultList) {
		ArrayList<String> labelList = new ArrayList<String>();
		ArrayList<String> list = new ArrayList<String>();
        labelList.add(SampleKeyEnum.SID.toString());
        list.add(SampleDAOHelper.getSample().getString(SampleKeyEnum.SID.toString()));
        labelList.add(SampleKeyEnum.Type.toString());
        list.add(SampleDAOHelper.getSample().getString(SampleKeyEnum.Type.toString()));
        labelList.add(SampleKeyEnum.User.toString());
        list.add(SampleDAOHelper.getSample().getString(SampleKeyEnum.User.toString()));
        labelList.add(SampleKeyEnum.DateTime.toString());
        list.add(SampleDAOHelper.getSample().getDate(SampleKeyEnum.DateTime.toString()).toString());
        labelList.add(SampleKeyEnum.Quantity.toString());
        list.add(SampleDAOHelper.getSample().getDouble(SampleKeyEnum.Quantity.toString()).toString());
        labelList.add(SampleKeyEnum.Unit.toString());
        list.add(SampleDAOHelper.getSample().getString(SampleKeyEnum.Unit.toString()));
        labelList.add(SampleKeyEnum.Comments.toString());
        list.add(SampleDAOHelper.getSample().getString(SampleKeyEnum.Comments.toString()));

        //labelList.add(SampleKeyEnum.Receive.toString());
        if (SampleDAOHelper.getSample().containsKey(TemplateTypeEnum.Receive.toString())) {
            Document doc = (Document) SampleDAOHelper.getSample().get(TemplateTypeEnum.Receive.toString());
            String strRecv = printTagsToString(doc);
            list.add(strRecv);
        } else {
        	list.add(" ");
        }

        labelList.add(SampleKeyEnum.Storage.toString());
        labelList.add(SampleKeyEnum.Storage.toString() + "_" + SampleKeyEnum.User.toString());
        labelList.add(SampleKeyEnum.Storage.toString() + "_" + SampleKeyEnum.User.toString() + "_" + SampleKeyEnum.Comments.toString());

        if (SampleDAOHelper.getSample().containsKey(TemplateTypeEnum.Storage.toString())) {
            Document doc = (Document) SampleDAOHelper.getSample().get(TemplateTypeEnum.Storage.toString());
            String strStorg = printStorageToString(doc);
            list.add(strStorg);
            list.add(doc.getString(SampleKeyEnum.User.toString()));
            list.add(doc.getString(SampleKeyEnum.Comments.toString()));
        } else {
        	list.add(" ");
        	list.add(" ");
        	list.add(" ");
        }
        resultList.add(list);
        return labelList;
    }
    public ArrayList<String> getProcessData(ArrayList<ArrayList<String>> resultList, String sID) {

    	ArrayList<String> labelList = new ArrayList<String>();
    	ArrayList<String> list = new ArrayList<String>();
    	
        if (!ProcessDAOHelper.fetchProcessBySID(sID)) {
        	labelList.add(SampleKeyEnum.Process.toString() + "_" + ProcessKeyEnum.TID.toString());
        	labelList.add(SampleKeyEnum.Process.toString() + " " + ProcessKeyEnum.User.toString());
        	labelList.add(SampleKeyEnum.Process.toString() + "_" + ProcessKeyEnum.DateTime.toString());
        	labelList.add(SampleKeyEnum.Process.toString() + "_" + ProcessKeyEnum.Comments.toString());
        	labelList.add(SampleKeyEnum.Process.toString() + "_" + ProcessKeyEnum.Steps.toString());
        	for(int i = 0; i<5;i++) {
        		list.add("");
        	}
        	resultList.get(resultList.size()-1).addAll(list);
        	return labelList;
        }

        labelList.add(SampleKeyEnum.Process.toString() + "_" + ProcessKeyEnum.TID.toString());
        list.add(ProcessDAOHelper.getProcessDoc().getString(ProcessKeyEnum.TID.toString()));
        labelList.add(SampleKeyEnum.Process.toString() + " " + ProcessKeyEnum.User.toString());
        list.add(ProcessDAOHelper.getProcessDoc().getString(ProcessKeyEnum.User.toString()));
        labelList.add(SampleKeyEnum.Process.toString() + "_" + ProcessKeyEnum.DateTime.toString());
        list.add(ProcessDAOHelper.getProcessDoc().getDate(ProcessKeyEnum.DateTime.toString()).toString());
        labelList.add(SampleKeyEnum.Process.toString() + "_" + ProcessKeyEnum.Comments.toString());
        list.add(ProcessDAOHelper.getProcessDoc().getString(ProcessKeyEnum.Comments.toString()));

        labelList.add(SampleKeyEnum.Process.toString() + "_" + ProcessKeyEnum.Steps.toString());
        if (ProcessDAOHelper.getProcessDoc().containsKey(ProcessKeyEnum.Steps.toString())) {
            Document doc = (Document) ProcessDAOHelper.getProcessDoc().get(ProcessKeyEnum.Steps.toString());
            String stepsRecv = printTagsToString(doc);
            list.add(stepsRecv);
        } else {
        	list.add(" ");
        }
        resultList.get(resultList.size()-1).addAll(list);
        return labelList;
    }
	
    private String printTagsToString(Document doc) {
        String tagsStr = "";
        if (null == doc || doc.isEmpty()) {

        } else {
            Iterator<String> it = doc.keySet().iterator();
            while (it.hasNext()) {
                String tag = it.next().toString();
                tagsStr += tag;
                tagsStr += " : ";
                tagsStr += doc.getString(tag);
                tagsStr += it.hasNext() ? " | " : "";
            }
        }

        return tagsStr;
    }
    private String printStorageToString(Document doc) {
        String storageStr = "";
        if (null == doc || doc.isEmpty()) {

        } else {
            Iterator<String> it = doc.keySet().iterator();
            while (it.hasNext()) {
                String place = it.next().toString();
                if (place.equals(SampleKeyEnum.Comments.toString()) || place.equals(SampleKeyEnum.User.toString())) {
                    continue;
                }
                Document unit = (Document) doc.get(place);
                storageStr += place;
                storageStr += " : ";
                storageStr += unit.getDouble(SampleKeyEnum.Quantity.toString());
                storageStr += " ";
                storageStr += unit.getString(SampleKeyEnum.Unit.toString());
                storageStr += " | ";
            }

        }

        return storageStr;
    }
	private ArrayList<String> getSIDListFromSampleDataDate()
	{
		ArrayList<String> SIDList = new ArrayList<String>();
		ArrayList fetchTemplate =null;
		  Date fromDate = (Date) datePickerFrom.getModel().getValue();
		  Date toDate = (Date) datePickerTo.getModel().getValue();
		  if(fromDate==null ||toDate == null )return null;
		  fromDate = resetDate(fromDate);
		  toDate = resetDate(toDate);

		  if(fromDate.compareTo(toDate)==0)
			  toDate = AddOneDay(toDate);
		  else if(fromDate.compareTo(toDate)>0) {
			  JOptionPane.showMessageDialog(parentFrame, "From date must be before To date");
			  return null;
		  }
		  BasicDBObject query = new BasicDBObject();
		  query.put(SampleKeyEnum.DateTime.toString(), new BasicDBObject("$gt", fromDate).append("$lt", toDate));
		  query.put(SampleKeyEnum.Active.toString(), 1);
		  
		  
	      if (SampleDAO.getInstance().connDAO()) {
	    	  SampleDAO.getInstance().setCollection();
	          fetchTemplate = ((SampleDAO)SampleDAO.getInstance()).fetch(query);
	      }
	      ProcessDAO.getInstance().closeDBConn();
	      
	      if(fetchTemplate!=null&& fetchTemplate.size()>0) {
	    	  for(int i=0;i<fetchTemplate.size();i++) {
		    	  Document d=(Document)(fetchTemplate.get(i));
	    		  if(d.containsKey(ProcessKeyEnum.SID.toString())) {
	    			  String SID = (String) d.get(ProcessKeyEnum.SID.toString());
	    			  SIDList.add(SID);
	    		  }
	    	  }
	      }
	      System.out.println(SIDList.toString());
	      return SIDList;
		
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
