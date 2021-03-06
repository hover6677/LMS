package com.ui.admin.mainframe;
import java.awt.Frame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.GroupLayout.Alignment;


public abstract class AbstractUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private JLabel lblNewLabel;
//	private JTextField textField;
//	private JLabel lblNoOfSteps;
//	private JList<Integer> list;
//	private JScrollBar scrollBar;
//	private JPanel panel;
//	private JPanel panel_1;
	String title;
	String label1;
	String label2;
	String label3;
	String button1;
	public JTextField tidText;
	public final String user = "admin";
	
	private int totNum;
	private SpinnerListModel listModelLeft;
	protected JPanel panel_1;
	protected JButton btnSave;
	protected ArrayList<JTextField> arrayOfTxtBox;
	
	

	public AbstractUI(String t,String l1, String l2,String l3, String b1, boolean isStorage)
	{
		//setResizable(false);
		//super(title);
		title =t;
		label1 = l1;
		label2 = l2;
		label3 = l3;
		button1 = b1;
		//setTitle(title);
		setSize(719,338);  
		//getContentPane().setLayout(null);
		setLayout(null);
		

		
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.GRAY);
		panel.setBounds(6, 6, 701, 92);
		panel.setBorder(BorderFactory.createRaisedBevelBorder());
		add(panel);
		panel.setLayout(null);
		

		
		panel_1 = new JPanel();
		panel_1.setBounds(6, 111, 701, 189);
		panel_1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), label3));
		add(panel_1);
		panel_1.setLayout(null);
		//JScrollPane scrollPane = new JScrollPane(panel_1);
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//scrollPane.setBounds(6, 111, 701, 189);
		//add(scrollPane);
		
		JLabel lblNewLabel = new JLabel(label1);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel.setBounds(123, 13, 123, 27);
		if(!isStorage)
		panel.add(lblNewLabel);
		
		
		
		tidText = new JTextField();
		lblNewLabel.setLabelFor(tidText);
		tidText.setBounds(258, 15, 172, 26);
		tidText.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
		if(!isStorage)
		panel.add(tidText);
		tidText.setColumns(10);
		
		
		JLabel lblNoOfSteps = new JLabel(label2);
		lblNoOfSteps.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		if(!isStorage)
		lblNoOfSteps.setBounds(123, 53, 123, 27);
		else 
			lblNoOfSteps.setBounds(123, 33, 123, 27);
		panel.add(lblNoOfSteps);
		
		getLabelTotNum();
		
		JSpinner spinner = new JSpinner();
		lblNoOfSteps.setLabelFor(spinner);
		spinner.setToolTipText("Select No. of Labels");
		if(!isStorage)
			spinner.setBounds(258, 55, 89, 27);
		else 
			spinner.setBounds(258, 33, 89, 27);
		spinner.setModel(listModelLeft);
		//spinner.get.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
		spinner.addChangeListener(new ChangeListener(){

			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JSpinner sp =(JSpinner)e.getSource();
				int numOfTxtBox =Integer.parseInt(sp.getValue().toString());
				System.out.println(numOfTxtBox);
				addTxtBox(numOfTxtBox);
			}
			
		});
		panel.add(spinner);
		
		btnSave = new JButton(button1);
		btnSave.setBounds(530, 300, 117, 25);
		btnSave.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                                
                                
                                    buttonAction();
                               
			}
			
		});
		add(btnSave);
		
		
		
//		JScrollBar scrollBar_1 = new JScrollBar();
//		panel_1.add(scrollBar_1);
		
		setVisible(true);
		 
		
	}
	
	protected void getLabelTotNum(){
		totNum=getTotNum();
		listModelLeft=new SpinnerListModel();
		ArrayList<Integer> modelList = new ArrayList<Integer>();
		modelList.add(0);
		for(int i =1; i<=totNum;i++)
		{
			modelList.add(i);
			
		}
		listModelLeft.setList(modelList);
		listModelLeft.setValue(0);
		
	}

	// this function is to get totNum from database
	abstract protected int getTotNum();
	
	protected void addTxtBox(int numOfTxtBox){
		int xinit = 12;
		int yinit = 40;
		int xoffsetLabel = 55;
		int xoffset = 95;
		int yoffset = 45;
		int x = xinit;
		int y = yinit;
		int width = 90;
		int height = 25;
				
		panel_1.removeAll();
		arrayOfTxtBox  = new ArrayList<JTextField>();
		for(int i =0; i<numOfTxtBox;i++)
		{	
//			JLabel label;
//			if(i<9)
//			label = new JLabel("Label0"+(i+1));
//			else label = new JLabel("Label"+(i+1));
//			label.setBounds(x, y, width, height);
			x+=xoffsetLabel;
			JTextField txtBox = new JTextField();
			txtBox.setBounds(x, y, width, height);
			txtBox.setColumns(6);
			txtBox.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
			x+=xoffset;
			if(x>=550) {
				x= xinit;
				y+=yoffset;
			}
			txtBox.setToolTipText("Label"+(i+1));
			arrayOfTxtBox.add(txtBox);
			
			//panel_1.add(label);
			panel_1.add(txtBox);
		}
		panel_1.updateUI();
		return;
	}
	abstract void buttonAction();
	protected void cleanAll(ArrayList<JTextField> arrBox)
	{
		for(int i =0; i<arrBox.size();i++)
		{
			arrBox.get(i).setText("");
		}
		tidText.setText("");
		
		
	}
}


