package com.ui.admin.mainframe;

import java.awt.Frame;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.bson.Document;
import org.fileRW.FileRW;

import com.db.mongodb.admin.helper.InsertAction;
import com.db.mongodb.DAO.AttachmentDAO;
import com.db.mongodb.DAO.DBConnection;
import com.db.mongodb.DAO.TemplateDAO;
import com.document.enumeration.AttachmentKeyEnum;
import com.document.enumeration.ProcessKeyEnum;
import com.document.enumeration.TemplateKeyEnum;
import com.document.enumeration.TemplateTypeEnum;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import org.apache.commons.io.*;

public class ProcessingUI extends AbstractUI {

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

    private int totNum;
    private SpinnerListModel listModelLeft;
    private HashMap<JTextField,JTextField> mapOfFiles;
    private final static String config = "config/config.txt";
    private final static String attachmentDIR = "AttachmentDIR";
    private final static String IP = "IP";
    //private JPanel panel_1;

    public ProcessingUI() {
        super("Process(Admin)", "Process Name", "No. of Steps", "Labels", "Save", false);
        panel_1.setBounds(6, 111, 701, 300);
        btnSave.setBounds(530, 415, 117, 25);
    }

    // this function is to get totNum from database
    @Override
    protected int getTotNum() {
        return 12;
    }

    @Override
    protected void buttonAction() {

        if (null == super.tidText.getText() || "".equals(super.tidText.getText())) {
            JOptionPane.showMessageDialog(this, "Please fill in the template id or name");
            return;
        } else if (null == arrayOfTxtBox || arrayOfTxtBox.size() < 1) {
            JOptionPane.showMessageDialog(this, "Please define the Steps");
            return;
        } else {

            ArrayList<String> value = new ArrayList<String>();
            
            System.out.println(arrayOfTxtBox.get(0).getText());
            for (int i = 0; i < arrayOfTxtBox.size(); i++) {
                if (arrayOfTxtBox.get(i).getText() == null || arrayOfTxtBox.get(i).getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please fill all textboxs Or remove unnecessary textboxs");
                    return;
                }
                value.add(arrayOfTxtBox.get(i).getText());

            }
            for (JTextField fTxt:mapOfFiles.keySet()) {
            	if(fTxt.getText()!=null &&fTxt.getText()!="") {
            		
            		File srcFile =new File(fTxt.getText());
            		File destDir=readDIRFromFile();
            		File destFile=new File(destDir.getAbsolutePath()+"/"+srcFile.getName());
            		try {
            			FileUtils.copyFile(srcFile, destFile);
            		}catch (IOException e) {
            		    e.printStackTrace();
            		}
            		Document attachmentDoc = new Document();
            		attachmentDoc.append(AttachmentKeyEnum.DIR.toString(), destDir.getAbsolutePath());
            		attachmentDoc.append(AttachmentKeyEnum.IP.toString(), readIPFromFile());
            		attachmentDoc.append(AttachmentKeyEnum.FileName.toString(), destFile.getName());
            		attachmentDoc.append(AttachmentKeyEnum.User.toString(), "admin");
            		attachmentDoc.append(AttachmentKeyEnum.TID.toString(), tidText.getText());
            		attachmentDoc.append(AttachmentKeyEnum.Tag.toString(), mapOfFiles.get(fTxt).getText());
            		attachmentDoc.append(AttachmentKeyEnum.Active.toString(), 1);
            		attachmentDoc.append(AttachmentKeyEnum.DateTime.toString(), new Date());
            		InsertAction ia = new InsertAction("com.db.mongodb.DAO.AttachmentDAO",attachmentDoc);
            		ia.action(AttachmentDAO.getInstance(), this);
            		
            		
            	}

            }
            Document processDoc = new Document();
            processDoc.append(TemplateKeyEnum.Active.toString(), 1);
            processDoc.append(TemplateKeyEnum.DateTime.toString(), new Date());
            processDoc.append(TemplateKeyEnum.User.toString(), super.user);
            processDoc.append(TemplateKeyEnum.Type.toString(), TemplateTypeEnum.Process.toString());
            processDoc.append(TemplateKeyEnum.TID.toString(), tidText.getText());
            processDoc.append(TemplateKeyEnum.Count.toString(), value.size());
            processDoc.append(TemplateKeyEnum.Tags.toString(), value);
            InsertAction ia = new InsertAction("com.db.mongodb.DAO.TemplateDAO", processDoc);
            ia.action(TemplateDAO.getInstance(), this);
            cleanAll(arrayOfTxtBox);
            mapOfFiles=null;
        }

    }
    @Override
    protected void addTxtBox(int numOfTxtBox){
    	final JFileChooser fc = new JFileChooser();
    	fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int xinit = 12;
		int yinit = 40;
		int xoffsetLabel = 55;
		int xoffset = 95;
		int yoffset = 90;
		int x = xinit;
		int y = yinit;
		int y2=y;
		int width = 90;
		int height = 25;
		int buttonWidth=20;
				
		panel_1.removeAll();
		arrayOfTxtBox  = new ArrayList<JTextField>();
		mapOfFiles = new HashMap<JTextField,JTextField>();
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
			y2=y+height+10;
			File file;
			JTextField filetxt = new JTextField();
			filetxt.setEditable(false);
			filetxt.setBounds(x, y2, width-buttonWidth, height);
			filetxt.setColumns(6);
			filetxt.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
			JButton fileBut = new JButton("...");
			fileBut.setBounds(x+width-buttonWidth, y2, buttonWidth, height);
			FCActionListener fcal= new FCActionListener(filetxt,fc,fileBut);
			fileBut.addActionListener(fcal);
			x+=xoffset;
			if(x>=550) {
				x= xinit;
				y+=yoffset;
			}
			txtBox.setToolTipText("Label"+(i+1));
			arrayOfTxtBox.add(txtBox);
			mapOfFiles.put(filetxt, txtBox);
			//panel_1.add(label);
			panel_1.add(txtBox);
			panel_1.add(filetxt);
			panel_1.add(fileBut);
		}
		panel_1.updateUI();
		return;
	}

//	public static void main(String args[])
//	{
//		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
//		try {
//			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//		        if ("Nimbus".equals(info.getName())) {
//		            UIManager.setLookAndFeel(info.getClassName());
//		            break;
//		        }
//		    }
//			ProcessingUI pui = new ProcessingUI();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//ReceivingUI rui = new ReceivingUI();
//		//StorageUI sui = new StorageUI();
//		
//		
//	}
    private static File readDIRFromFile() {
        FileRW file = new FileRW();
        String l = "";
        try {
            file.readFile(config);
            BufferedReader buf_r = file.getBuf_r();

            while (null != (l = buf_r.readLine())) {
                String[] key = l.split("=");
                if(attachmentDIR.equals(key[0]))
                	return new File(key[1]);
            }

        } catch (Exception ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return null;
    }
    private static String readIPFromFile() {
        FileRW file = new FileRW();
        String l = "";
        try {
            file.readFile(config);
            BufferedReader buf_r = file.getBuf_r();

            while (null != (l = buf_r.readLine())) {
                String[] key = l.split("=");
                if(IP.equals(key[0]))
                	return key[1];
            }

        } catch (Exception ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return null;
    }
}
class FCActionListener implements ActionListener{

	private JTextField txt;
	private JFileChooser fc;
	private JButton button;
	private File file;
	public FCActionListener(JTextField t,JFileChooser f,JButton b) {
		txt= t;
		fc= f;
		button = b;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==button) {
			int returnVal = fc.showOpenDialog(((JButton)e.getSource()).getParent());
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				txt.setText(file.getAbsolutePath());
			}
		}
		
	}
	public File getFile() {
		return file;
	}
	
	
}