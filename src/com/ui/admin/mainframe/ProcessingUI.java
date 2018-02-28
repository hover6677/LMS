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

import com.db.mongodb.admin.helper.InsertAction;
import com.db.mongodb.DAO.TemplateDAO;
import com.document.enumeration.AttachmentKeyEnum;
import com.document.enumeration.ProcessKeyEnum;
import com.document.enumeration.TemplateKeyEnum;
import com.document.enumeration.TemplateTypeEnum;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Color;

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
    private ArrayList<File> arrayOfFiles;
    //private JPanel panel_1;

    public ProcessingUI() {
        super("Process(Admin)", "Process Name", "No. of Steps", "Labels", "Save", false);

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
            ArrayList<String> fileValue = new ArrayList<String>();
            System.out.println(arrayOfTxtBox.get(0).getText());
            for (int i = 0; i < arrayOfTxtBox.size(); i++) {
                if (arrayOfTxtBox.get(i).getText() == null || arrayOfTxtBox.get(i).getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please fill all textboxs Or remove unnecessary textboxs");
                    return;
                }
                value.add(arrayOfTxtBox.get(i).getText());

            }
            for (int i = 0; i < arrayOfFiles.size(); i++) {
            	if(arrayOfFiles.get(i)!=null) {
            		
            		
            	}

            }
            Document processDoc = new Document();
            processDoc.append(ProcessKeyEnum.Active.toString(), 1);
            processDoc.append(ProcessKeyEnum.DateTime.toString(), new Date());
            processDoc.append(ProcessKeyEnum.User.toString(), super.user);
            processDoc.append(ProcessKeyEnum.Type.toString(), TemplateTypeEnum.Process.toString());
            processDoc.append(ProcessKeyEnum.TID.toString(), tidText.getText());
            processDoc.append(ProcessKeyEnum.Count.toString(), value.size());
            processDoc.append(ProcessKeyEnum.Tags.toString(), value);
            InsertAction ia = new InsertAction("com.db.mongodb.DAO.TemplateDAO", processDoc);
            ia.action(TemplateDAO.getInstance(), this);
            cleanAll(arrayOfTxtBox);
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
		int yoffset = 45;
		int x = xinit;
		int y = yinit;
		int width = 90;
		int height = 25;
		int buttonWidth=20;
				
		panel_1.removeAll();
		arrayOfTxtBox  = new ArrayList<JTextField>();
		arrayOfFiles = new ArrayList<File>();
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
			y=y+height+10;
			File file;
			JTextField filetxt = new JTextField();
			filetxt.setBounds(x, y, width-buttonWidth, height);
			filetxt.setColumns(6);
			filetxt.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
			JButton fileBut = new JButton("...");
			fileBut.setBounds(x+width-buttonWidth, y, buttonWidth, height);
			FCActionListener fcal= new FCActionListener(filetxt,fc,fileBut);
			fileBut.addActionListener(fcal);
			x+=xoffset;
			if(x>=550) {
				x= xinit;
				y+=yoffset;
			}
			txtBox.setToolTipText("Label"+(i+1));
			arrayOfTxtBox.add(txtBox);
			arrayOfFiles.add(fcal.getFile());
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