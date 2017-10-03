/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.helper;

import com.db.mongodb.SampleDAO;
import com.db.mongodb.TemplateDAO;
import com.document.enumeration.TemplateKeyEnum;
import com.document.enumeration.TemplateTypeEnum;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class SampleDAOHelper {
    
    public static Document sample = new Document();
          
    public static boolean fetchSampleBySID(String sid)
    {
        sample =  SampleDAO.isSampleFound(sid);
        return (null!=sample);
    }
        
}
