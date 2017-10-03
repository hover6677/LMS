/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.helper;

import com.db.mongodb.TemplateDAO;
import com.document.enumeration.TemplateKeyEnum;
import com.document.enumeration.TemplateTypeEnum;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class ProcessDAOHelper {
    
    public static ArrayList templateList = new ArrayList();
          
    public static void getTemplateListByType(String admin,String type)
    {
        ProcessDAOHelper.templateList = TemplateDAO.fetchTemplate(admin,type);
    }
    
    public static String[] fetchTIDList()
    {
        String[] TIDList = new String[ProcessDAOHelper.templateList.size()];
        
        for(int i = 0;i<ProcessDAOHelper.templateList.size();i++)
        {
            Document doc =  (Document) ProcessDAOHelper.templateList.get(i);
            TIDList[i] = doc.getString(TemplateKeyEnum.TID.toString());
        }
        
        return TIDList;
    }
    
    public static ArrayList fetchTagListByTID(int index)
    {
        Document doc = (Document) ProcessDAOHelper.templateList.get(index);
        ArrayList tagList = (ArrayList) doc.get(TemplateKeyEnum.Tags.toString());
        return tagList;
    }
        
}
