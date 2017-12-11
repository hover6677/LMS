package com.ui.admin.mainFrame;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import com.Action.admin.LMSUtils;
import com.Action.admin.UMController;

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

public class UMUI extends JPanel {
	private JTextField txtUserName;
	private JTextField txtPassword;
	private UMController uMController;
	JRadioButton rdbtnReceive;
	JRadioButton rdbtnProcess;
	JRadioButton rdbtnStorage;
	JRadioButton rdbtnReport;
	private JScrollPane scrollPane;
	private JTable table;
	
	public UMUI() {
		setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(136, 58, 116, 22);
		add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblUserName = new JLabel("UserName");
		lblUserName.setLabelFor(txtUserName);
		lblUserName.setBounds(56, 61, 68, 16);
		add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(366, 61, 56, 16);
		add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(457, 58, 116, 22);
		add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnSet = new JButton("Set");
		btnSet.setBounds(641, 57, 97, 25);
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
		add(rdbtnReceive);
		
		rdbtnProcess = new JRadioButton("Process");
		rdbtnProcess.setBounds(212, 102, 127, 25);
		add(rdbtnProcess);
		
		rdbtnStorage = new JRadioButton("Storage");
		rdbtnStorage.setBounds(366, 102, 127, 25);
		add(rdbtnStorage);
		
		rdbtnReport = new JRadioButton("Report");
		rdbtnReport.setBounds(523, 102, 127, 25);
		add(rdbtnReport);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 161, 684, 333);
		add(scrollPane);
		
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
				txtUserName.setText(target.getValueAt(row, 0).toString());
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
		this.updateUI();
	}
	public static void main(String[] args) {
		UMUI umui = new UMUI();
		umui.refresh();
		JFrame frame = new JFrame();
		frame.getContentPane().add(umui);
		frame.setSize(300, 300);
		
		frame.setVisible(true);
		
	}
}
