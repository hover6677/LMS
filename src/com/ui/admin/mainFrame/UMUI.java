package com.ui.admin.mainFrame;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import com.db.mongodb.admin.helper.LMSUtils;
import com.db.mongodb.admin.helper.UMController;

import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class UMUI extends JPanel {
	private JTextField txtUserName;
	private JTextField txtPassword;
	private UMController uMController;
	JRadioButton rdbtnReceive;
	JRadioButton rdbtnProcess;
	JRadioButton rdbtnStorage;
	JRadioButton rdbtnReport;
	JPanel tablePanel;
	private JScrollPane scrollPane;
	private JTable table;
	private static int IndexOfUserName=0;
	private static int IndexOfReceive=2;
	private static int IndexOfProcess=3;
	private static int IndexOfStorage=4;
	private static int IndexOfReport=5;
	
	public UMUI() {
		setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(136, 58, 116, 22);
		add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblUserName = new JLabel("UserName");
		lblUserName.setLabelFor(txtUserName);
		lblUserName.setBounds(56, 61, 70, 16);
		add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(299, 61, 80, 16);
		add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(391, 58, 116, 22);
		add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnSet = new JButton("Set");
		btnSet.setBounds(585, 57, 97, 25);
		btnSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean isRev = rdbtnReceive.isSelected();
				boolean isPro = rdbtnProcess.isSelected();
				boolean isSto = rdbtnStorage.isSelected();
				boolean isRpt = rdbtnReport.isSelected();
				String username = txtUserName.getText();
				String password = txtPassword.getText();
				uMController.setAction(username, password, isRev, isPro, isSto, isRpt);
			}
			
		});
		add(btnSet);
		uMController = new UMController(this);
		
		rdbtnReceive = new JRadioButton("Receive");
		rdbtnReceive.setBounds(56, 102, 127, 25);
		rdbtnReceive.setSelected(true);
		add(rdbtnReceive);
		
		rdbtnProcess = new JRadioButton("Process");
		rdbtnProcess.setBounds(212, 102, 127, 25);
		rdbtnProcess.setSelected(true);
		add(rdbtnProcess);
		
		rdbtnStorage = new JRadioButton("Storage");
		rdbtnStorage.setBounds(366, 102, 127, 25);
		rdbtnStorage.setSelected(true);
		add(rdbtnStorage);
		
		rdbtnReport = new JRadioButton("Report");
		rdbtnReport.setBounds(523, 102, 127, 25);
		rdbtnReport.setSelected(true);
		add(rdbtnReport);
		
		tablePanel = new JPanel();
		tablePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tablePanel.setBounds(56, 138, 594, 249);
		add(tablePanel);
		tablePanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 5, 570, 231);
		tablePanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	public void refresh() {
		// TODO Auto-generated method stub
		TableModel tModel;
		ArrayList<ArrayList<String>> result = uMController.getData();
		Object[] labelArray = {"User","Date","Receive","Process","Storage","Report"};
		Object[][] resultArray = new Object[result.size()][];

		int i = 0;
		for (List<String> nestedList : result) {
			resultArray[i++] = nestedList.toArray(new String[nestedList.size()]);
		}
  
		//Object[][] resultArray = (Object[][]) result.toArray();
		LMSUtils.checkEmpty(resultArray,labelArray,this);
		table = new JTable(resultArray, labelArray);
		tModel = table.getModel();
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JTable target = (JTable)e.getSource();
				int row =target.getSelectedRow();
				txtUserName.setText(target.getValueAt(row, UMUI.IndexOfUserName).toString());
				rdbtnReceive.setSelected(target.getValueAt(row, UMUI.IndexOfReceive).toString().equalsIgnoreCase("true")? true: false);
				rdbtnProcess.setSelected(target.getValueAt(row, UMUI.IndexOfProcess).toString().equalsIgnoreCase("true")? true: false);
				rdbtnStorage.setSelected(target.getValueAt(row, UMUI.IndexOfStorage).toString().equalsIgnoreCase("true")? true: false);
				rdbtnReport.setSelected(target.getValueAt(row, UMUI.IndexOfReport).toString().equalsIgnoreCase("true")? true: false);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		scrollPane.setViewportView(table);
		table.updateUI();
		scrollPane.updateUI();
		scrollPane.setVisible(true);
		tablePanel.updateUI();
		this.updateUI();
	}

	//public static void main(String[] args) {
	//UMUI umui = new UMUI();
	//umui.refresh();
	//JFrame frame = new JFrame();
	//frame.getContentPane().add(umui);
	//frame.setSize(300, 300);
	//
	//frame.setVisible(true);
	//
	//}

}

