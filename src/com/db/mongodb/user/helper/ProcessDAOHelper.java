/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.user.helper;

import com.db.mongodb.DAO.ProcessDAO;
import com.db.mongodb.DAO.TemplateDAO;
import com.document.enumeration.ProcessKeyEnum;
import com.document.enumeration.ProcessKeyEnum;
import com.document.enumeration.TemplateKeyEnum;
import com.document.enumeration.TemplateTypeEnum;
import com.ui.user.mainapp.UserMainFrameApp;
import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class ProcessDAOHelper {
    
    private static Document processDoc = new Document(); 

    public static Document getProcessDoc() {
        return processDoc;
    }

    public static void setProcessDoc(Document processDoc) {
        ProcessDAOHelper.processDoc = processDoc;
    }

    public static boolean prepareReceivedProcess(String sid, String tid, String remarks, Document objP) 
    {
        processDoc = new Document();
        processDoc.append(ProcessKeyEnum.Active.toString(), 1);
        processDoc.append(ProcessKeyEnum.DateTime.toString(), new Date());
        processDoc.append(ProcessKeyEnum.SID.toString(), sid);
        processDoc.append(ProcessKeyEnum.TID.toString(), tid);
        processDoc.append(ProcessKeyEnum.User.toString(), UserMainFrameApp.getUserName());
        processDoc.append(ProcessKeyEnum.Comments.toString(), remarks);
        processDoc.append(ProcessKeyEnum.Steps.toString(), objP);
        return true;
        
    }
    
    public static boolean fetchProcessBySID(String sid)
    {
    	Document d = new Document();
    	d.append(ProcessKeyEnum.Active.toString(), 1);
    	d.append(ProcessKeyEnum.SID.toString(), sid);
    	
	      if (ProcessDAO.getInstance().connDAO()) {
	          ProcessDAO.getInstance().setCollection();
	          
	          processDoc =  ProcessDAO.getInstance().isFound(d);
	      }
	      ProcessDAO.getInstance().closeDBConn();
        return (null!=processDoc);
    }
    
        
}
