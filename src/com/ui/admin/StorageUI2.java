package com.ui.admin;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.bson.Document;

import com.Action.admin.InsertAction;
import com.db.mongodb.TemplateDAO;
import com.document.enumeration.TemplateKeyEnum;
import com.document.enumeration.TemplateTypeEnum;

public class StorageUI2 extends AbstractUI {

    public StorageUI2() {
        super("Storage(Admin)", "", "No. of Storage", "Storages", "Save", true);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected int getTotNum() {
        // TODO Auto-generated method stub
        return 4;
    }

    @Override
    void buttonAction() {
        // TODO Auto-generated method stub
        if (null == arrayOfTxtBox || arrayOfTxtBox.size() < 1) {
            JOptionPane.showMessageDialog(this, "Please define the Storages");
            return;
        } else {
            ArrayList<String> value = new ArrayList<String>();
            for (int i = 0; i < arrayOfTxtBox.size(); i++) {
                if ((arrayOfTxtBox.get(i).getText() == null) || arrayOfTxtBox.get(i).getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please fill all textboxs Or remove unnecessary textboxs");
                    return;
                }
                value.add(arrayOfTxtBox.get(i).getText());

            }
            Document processDoc = new Document();
            processDoc.append(TemplateKeyEnum.Active.toString(), 1);
            processDoc.append(TemplateKeyEnum.DateTime.toString(), new Date());
            processDoc.append(TemplateKeyEnum.User.toString(), super.user);
            processDoc.append(TemplateKeyEnum.Type.toString(), TemplateTypeEnum.Storage.toString());
            processDoc.append(TemplateKeyEnum.TID.toString(), TemplateTypeEnum.Storage.toString());
            processDoc.append(TemplateKeyEnum.Count.toString(), value.size());
            processDoc.append(TemplateKeyEnum.Tags.toString(), value);
            InsertAction ia = new InsertAction("com.db.mongodb.TemplateDAO", processDoc);
            ia.action(TemplateDAO.getInstance(), this);
            cleanAll(arrayOfTxtBox);
        }
    }

}
