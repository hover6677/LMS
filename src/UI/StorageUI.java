package UI;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StorageUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultComboBoxModel<Integer> cbm;
	public StorageUI() {
		//System.out.println("Storageui");
		//setTitle("Storage (Admin)");
		setSize(723,330);
		//setResizable(false);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 13, 701, 92);
		add(panel);
		panel.setBorder(BorderFactory.createRaisedBevelBorder());
		panel.setLayout(null);
		
		final JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(40);
		flowLayout.setVgap(20);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.setBounds(6, 105, 701, 212);
		panel_1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Storages"));
		add(panel_1);
		panel.setLayout(null);
		
		JLabel lblNoOfStorage = new JLabel("No. of Storage");
		lblNoOfStorage.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNoOfStorage.setBounds(123, 14, 113, 20);
		panel.add(lblNoOfStorage);
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		comboBox.setBounds(287, 13, 85, 27);
		cbm = new DefaultComboBoxModel<Integer>();
		setComboBoxModel();
		comboBox.setModel(cbm);
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox cb = (JComboBox)e.getSource();
				int n = Integer.parseInt(cb.getSelectedItem().toString());
				addTxtBox(n,panel_1);
			}
			
		});
		
		panel.add(comboBox);
		
		setVisible(true);
		
		
		
		
		
	}
	protected int getNumOfStorage()
	{
		return 8;
	}
	protected void setComboBoxModel(){
		int n = getNumOfStorage();
		for(int i = 0; i<=n;i++)
		{
			cbm.addElement(i);
		}
		cbm.setSelectedItem(0);
	}
	protected void addTxtBox(int numOfTxtBox,JPanel panel_1){
		panel_1.removeAll();
		ArrayList<JTextField> arrayOfTxtBox  = new ArrayList<JTextField>();
		for(int i =0; i<numOfTxtBox;i++)
		{	
//			JLabel label;
//			if(i<9)
//			label = new JLabel("Label0"+(i+1));
//			else label = new JLabel("Label"+(i+1));
			JTextField txtBox = new JTextField();
			txtBox.setColumns(6);
			txtBox.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
			arrayOfTxtBox.add(txtBox);
			txtBox.setToolTipText("Label"+(i+1));
//			panel_1.add(label);
			panel_1.add(txtBox);
		}
		panel_1.updateUI();
		return;
	}
}
