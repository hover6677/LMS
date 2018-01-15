package com.db.mongodb.admin.helper;

import java.awt.print.PrinterException;

import javax.swing.JTable;

public class JTablePrint {
	private JTable table;
	public JTablePrint(JTable t) {
		table = t;

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
