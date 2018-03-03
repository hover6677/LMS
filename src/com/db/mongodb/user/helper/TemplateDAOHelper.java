/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.user.helper;

import com.db.mongodb.DAO.AbstractDAO;
import com.db.mongodb.DAO.TemplateDAO;
import com.document.enumeration.TemplateKeyEnum;
import com.document.enumeration.TemplateTypeEnum;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class TemplateDAOHelper {
    
    public ArrayList templateList = new ArrayList();
          
    public void getTemplateListByType(String admin,String type)
    {
        templateList.clear();
        this.templateList = ((TemplateDAO)TemplateDAO.getInstance()).fetchTemplate(admin,type);
    }
    
    public String[] fetchTIDList()
    {
        String[] TIDList = new String[this.templateList.size()];
        
        for(int i = 0;i<this.templateList.size();i++)
        {
            Document doc =  (Document) this.templateList.get(i);
            TIDList[i] = doc.getString(TemplateKeyEnum.TID.toString());
        }
        
        return TIDList;
    }
    
    public  ArrayList fetchTagListByTID(int index)
    {
        Document doc = (Document) this.templateList.get(index);
        ArrayList tagList = (ArrayList) doc.get(TemplateKeyEnum.Tags.toString());
        return tagList;
    }
    public String fetchTemplateID(int index)
    {
        Document doc = (Document) this.templateList.get(index);
        return doc.getString(TemplateKeyEnum.TID.toString());
    }
        
}
