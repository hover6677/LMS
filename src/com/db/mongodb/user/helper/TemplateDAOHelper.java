/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.user.helper;

import com.db.mongodb.user.TemplateDAO;
import com.document.enumeration.TemplateKeyEnum;
import com.document.enumeration.TemplateTypeEnum;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class TemplateDAOHelper {
    
    public static ArrayList templateList = new ArrayList();
          
    public static void getTemplateListByType(String admin,String type)
    {
        TemplateDAOHelper.templateList = TemplateDAO.fetchTemplate(admin,type);
    }
    
    public static String[] fetchTIDList()
    {
        String[] TIDList = new String[TemplateDAOHelper.templateList.size()];
        
        for(int i = 0;i<TemplateDAOHelper.templateList.size();i++)
        {
            Document doc =  (Document) TemplateDAOHelper.templateList.get(i);
            TIDList[i] = doc.getString(TemplateKeyEnum.TID.toString());
        }
        
        return TIDList;
    }
    
    public static ArrayList fetchTagListByTID(int index)
    {
        Document doc = (Document) TemplateDAOHelper.templateList.get(index);
        ArrayList tagList = (ArrayList) doc.get(TemplateKeyEnum.Tags.toString());
        return tagList;
    }
        
}
