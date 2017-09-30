package UI;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class UIFrame extends JFrame{
	public UIFrame() {
		setTitle("LIMS");
		setResizable(false);
		setSize(765,475);
                //setIcon(new ImageIcon(UIFrame.class.getResource("/resources/logo.png")));
                
		getContentPane().setLayout(null);
                JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 688, 538);
		getContentPane().add(tabbedPane);
		tabbedPane.addTab("Receiving", new ReceivingUI());
		tabbedPane.addTab("Processing", new ProcessingUI());
		tabbedPane.addTab("Storage", new StorageUI());
		tabbedPane.addTab("Report", new ReportUI());
		
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
}	
