/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.helper;

import com.db.mongodb.SampleDAO;
import com.db.mongodb.TemplateDAO;
import com.document.enumeration.SampleKeyEnum;
import com.document.enumeration.TemplateKeyEnum;
import com.document.enumeration.TemplateTypeEnum;
import com.document.enumeration.UnitEnum;
import com.ui.user.mainapp.UserMainFrameApp;
import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class SampleDAOHelper {
    
    private static Document sample = new Document();

    public static Document getSample() {
        return sample;
    }

    public static void setSample(Document sample) {
        SampleDAOHelper.sample = sample;
    }
          
    public static boolean fetchSampleBySID(String sid)
    {
        sample =  SampleDAO.isSampleFound(sid);
        return (null!=sample);
    }
    
    public static boolean prepareReceivedSample(String sid, double quatity,String tid,String unit,String type,String remarks,Document objR)
    {
        sample = new Document();
        sample.append(SampleKeyEnum.Active.toString(), 1);
        sample.append(SampleKeyEnum.DateTime.toString(), new Date());
        sample.append(SampleKeyEnum.SID.toString(), sid);
        sample.append(SampleKeyEnum.User.toString(), UserMainFrameApp.userName);
        sample.append(SampleKeyEnum.Quantity.toString(), quatity);
        sample.append(SampleKeyEnum.Unit.toString(), unit);
        sample.append(SampleKeyEnum.Type.toString(), type);
        sample.append(SampleKeyEnum.Comments.toString(), remarks);
        sample.append(SampleKeyEnum.Receive.toString(), objR);
        return true;
    }
        
}
