package com.ui.admin.mainframe;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.bson.Document;

import com.db.mongodb.admin.helper.InsertAction;
import com.db.mongodb.DAO.TemplateDAO;
import com.document.enumeration.TemplateKeyEnum;
import com.document.enumeration.TemplateTypeEnum;

public class ReceivingUI extends AbstractUI {

    protected ProcessingUI puiToBeDecorated;

    public ReceivingUI() {
        super("Receiving(Admin)", "Template ID", "No. of Tags", "Labels", "Save", false);

    }

    @Override
    protected int getTotNum() {
        // TODO Auto-generated method stub
        return 8;
    }

    @Override
    void buttonAction() {
        // TODO Auto-generated method stub
        if (null == super.tidText.getText() || "".equals(super.tidText.getText())) {
            JOptionPane.showMessageDialog(this, "Please fill in the template id or name");
            return;
        }else if(null==arrayOfTxtBox || arrayOfTxtBox.size()<1)
        {
            JOptionPane.showMessageDialog(this, "Please define the Tags");
            return;
        }
        else {

            ArrayList<String> value = new ArrayList<String>();
            for (int i = 0; i < arrayOfTxtBox.size(); i++) {
                if ((arrayOfTxtBox.get(i).getText() == null || arrayOfTxtBox.get(i).getText().equals(""))) {
                    JOptionPane.showMessageDialog(this, "Please fill all textboxs Or remove unnecessary textboxs");
                    return;
                }
                value.add(arrayOfTxtBox.get(i).getText());

            }
            Document processDoc = new Document();
            processDoc.append(TemplateKeyEnum.Active.toString(), 1);
            processDoc.append(TemplateKeyEnum.DateTime.toString(), new Date());
            processDoc.append(TemplateKeyEnum.User.toString(), super.user);
            processDoc.append(TemplateKeyEnum.Type.toString(), TemplateTypeEnum.Receive.toString());
            processDoc.append(TemplateKeyEnum.TID.toString(), tidText.getText());
            processDoc.append(TemplateKeyEnum.Count.toString(), value.size());
            processDoc.append(TemplateKeyEnum.Tags.toString(), value);
            InsertAction ia = new InsertAction("com.db.mongodb.DAO.TemplateDAO", processDoc);
            ia.action(TemplateDAO.getInstance(), this);
            cleanAll(arrayOfTxtBox);
        }
    }
}
