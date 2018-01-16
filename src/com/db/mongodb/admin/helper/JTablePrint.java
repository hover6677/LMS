package com.db.mongodb.admin.helper;

import java.awt.print.PrinterException;
import java.util.ArrayList;

import javax.swing.JTable;

public class JTablePrint {
	private JTable table;
	public JTablePrint(JTable t) {
		table = t;

	}
	public JTablePrint(ArrayList<String> labels, ArrayList<String> values ) {
		
		Object[] l =  (Object[]) labels.toArray();
		Object[][]  v = {(Object[]) values.toArray()};
		if(LMSUtils.checkEmpty(v,l,null))return;
		table = new JTable(v, l);
		table.updateUI();
	}
	public void Execute () {
		try {
			table.print();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
}
