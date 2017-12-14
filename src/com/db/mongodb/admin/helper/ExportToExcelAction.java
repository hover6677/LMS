package com.db.mongodb.admin.helper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sun.rowset.internal.Row;

import javafx.scene.control.Cell;

public class ExportToExcelAction {
	private JTable table;
	private TableModel tModel;
	private ArrayList<ArrayList<String>> dataList;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private String filename;
	public ExportToExcelAction(JTable t, String f) {
		table  =t;
		tModel = table.getModel();
		dataList = new ArrayList<ArrayList<String>>();
		workbook = new XSSFWorkbook();
		filename = f;
		sheet = workbook.createSheet(filename);
		processData();
		setTitle();
		setData();
		exportToExcel();
	}
	public void processData() {
		for (int i = 0; i < tModel.getRowCount(); i++){
			ArrayList<String> list = new ArrayList<String>();
			for(int j=0; j<tModel.getColumnCount();j++)
			{
				list.add(tModel.getValueAt(i, j).toString());
				
			}
			dataList.add(list);
		}
		
	}
	public void exportToExcel() {
		
			FileOutputStream outputStream;
			try {
				outputStream = new FileOutputStream(filename+".xlsx");
				workbook.write(outputStream);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        
	}
	private void setTitle() {
		ArrayList<String> labelList = new ArrayList<String>();
		for(int i=0; i< tModel.getColumnCount();i++) {
			labelList.add(tModel.getColumnName(i));
			
		}
		
		XSSFRow row = sheet.createRow(0);
		for(int i = 0; i<labelList.size();i++) {
			XSSFCell cell =row.createCell(i);
			cell.setCellValue(labelList.get(i));
		}
		
	}
	private void setData() {
		for(int i=0; i< dataList.size();i++) {
			XSSFRow row =sheet.createRow(sheet.getLastRowNum()+1);
			for(int j=0; j<dataList.get(i).size();j++) {
				XSSFCell cell =row.createCell(j);
				cell.setCellValue((dataList.get(i)).get(j));
			}
		}
	}
}