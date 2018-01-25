package com.db.mongodb.admin.helper;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			Date date = new Date();
			String time = dateFormat.format(date);
			String fileName = filename+time+".xlsx";
			File dir = new File("C:\\output\\");
			dir.mkdirs();
			File f = new File(dir,fileName);
			
			
			try {
				if(!f.exists())f.createNewFile();
				
				outputStream = new FileOutputStream(f);
				workbook.write(outputStream);
				if(!Desktop.isDesktopSupported()){
		            System.out.println("Desktop is not supported");
		            return;
		        }
				Desktop desktop = Desktop.getDesktop();
		        if(f.exists()) desktop.open(f);
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