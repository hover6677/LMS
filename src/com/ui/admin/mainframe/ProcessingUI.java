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
    private JPanel panel_1;

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
            System.out.println(arrayOfTxtBox.get(0).getText());
            for (int i = 0; i < arrayOfTxtBox.size(); i++) {
                if (arrayOfTxtBox.get(i).getText() == null || arrayOfTxtBox.get(i).getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please fill all textboxs Or remove unnecessary textboxs");
                    return;
                }
                value.add(arrayOfTxtBox.get(i).getText());

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
        }

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
