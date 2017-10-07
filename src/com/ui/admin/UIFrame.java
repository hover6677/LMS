package com.ui.admin;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class UIFrame extends JFrame{
	/**
	 * 
	 */
	public UIFrame() {
		setTitle("LIMS");
		setSize(740,600);
		setMinimumSize(new Dimension(740,600));
                //setIcon(new ImageIcon(UIFrame.class.getResource("/resources/logo.png")));
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);       
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255,255,204));
		//panel.setBounds(0, 0, 710, 540);
		panel.setPreferredSize(new Dimension(740,600));
		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(UIFrame.class.getResource("/resources/logo.png")));
		panel.add(label,BorderLayout.NORTH);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		panel.add(tabbedPane,BorderLayout.CENTER);
		final ReportUI rpUI = new ReportUI();
		tabbedPane.addTab("Receiving", new ReceivingUI());
		tabbedPane.addTab("Processing", new ProcessingUI());
		tabbedPane.addTab("Storage", new StorageUI2());
		tabbedPane.addTab("Report", rpUI);
		tabbedPane.addChangeListener(new ChangeListener() {
		        public void stateChanged(ChangeEvent e) {
		        	JTabbedPane me =(JTabbedPane)e.getSource();
		        	if(me.getSelectedIndex()==3) {
		        		//rpUI.upda();
		        	}
		        }


		    });
		
		JPanel imagePanel = new JPanel();
		imagePanel.setBounds(712, 13, 207, 538);
		imagePanel.setLayout(null);
		/*
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(UIFrame.class.getResource("/resources/logo.png")));
		lblNewLabel.setBounds(12, 13, 183, 124);
		imagePanel.add(lblNewLabel);
		JLabel label = new JLabel(new ImageIcon());
		label.setBounds(170, 13, -1, -1);
		imagePanel.add(label);
		getContentPane().add(imagePanel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(UIFrame.class.getResource("/resources/Barcode.jpg")));
		lblNewLabel_1.setBounds(12, 150, 183, 270);
		imagePanel.add(lblNewLabel_1);
                */
		setVisible(true);
                
	}
	public static void main(String args[])
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
			UIFrame frame = new UIFrame();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
            }
	});
	}
}
