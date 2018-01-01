/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.user.helper;

import com.db.mongodb.DAO.SampleDAO;
import com.document.enumeration.MessageEnum;
import com.document.enumeration.SampleKeyEnum;
import com.ui.user.mainapp.UserMainFrameApp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
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
        sample =  ((SampleDAO)SampleDAO.getInstance()).isSampleFound(sid);
        return (null!=sample);
    }
    
    public static boolean prepareReceivedSample(String sid, double quatity,String tid,String unit,String type,String remarks,Document objR)
    {
        sample = new Document();
        sample.append(SampleKeyEnum.Active.toString(), 1);
        sample.append(SampleKeyEnum.DateTime.toString(), new Date());
        sample.append(SampleKeyEnum.SID.toString(), sid);
        sample.append(SampleKeyEnum.User.toString(), UserMainFrameApp.getUserName());
        sample.append(SampleKeyEnum.Quantity.toString(), quatity);
        sample.append(SampleKeyEnum.Unit.toString(), unit);
        sample.append(SampleKeyEnum.Type.toString(), type);
        sample.append(SampleKeyEnum.Comments.toString(), remarks);
        sample.append(SampleKeyEnum.Receive.toString(), objR);
        return true;
    }

    public static MessageEnum  prepareStorage(String sid, String remarks, Document objS, boolean inFlag) {
        if(!fetchSampleBySID(sid))
        {
            return MessageEnum.SampleNotFound;
        }
        else
        {
            if(sample.containsKey(SampleKeyEnum.Storage.toString()))
            {
                Document storageDoc = (Document) sample.get(SampleKeyEnum.Storage.toString());
                storageDoc = (null!=storageDoc)?storageDoc : new Document();
                
                Iterator<String> it = objS.keySet().iterator();
                while(it.hasNext())
                {
                    String key = it.next().toString();
                    if(storageDoc.containsKey(key))
                    {
                        if(((Document) objS.get(key)).getString(SampleKeyEnum.Unit.toString()).equals(((Document) storageDoc.get(key)).getString(SampleKeyEnum.Unit.toString())))
                        {
                            Document pair = new Document();
                            pair.append(SampleKeyEnum.Quantity.toString(), ((Document)objS.get(key)).getDouble(SampleKeyEnum.Quantity.toString()));
                            pair.append(SampleKeyEnum.Unit.toString(), ((Document) objS.get(key)).getString(SampleKeyEnum.Unit.toString()));
                            storageDoc.put(key,pair);
                        }
                        else
                        {
                           return MessageEnum.UnitMisMatch;
                        }
                    }
                    else
                    {
                        storageDoc.append(key, objS.get(key));
                    }
                }
                sample.put(SampleKeyEnum.Storage.toString(),storageDoc);
            }
            else
            {
                objS.append(SampleKeyEnum.Comments.toString(), remarks);
                objS.append(SampleKeyEnum.User.toString(),UserMainFrameApp.getUserName());
                sample.append(SampleKeyEnum.Storage.toString(), objS);
            }
            return MessageEnum.RecordSaved;
        }
    }

    private static Double updateQuality(boolean inFlag,String key, Document objS, Document storageDoc) {
        
        Double delta = (inFlag?1:-1)*((Document) objS.get(key)).getDouble(SampleKeyEnum.Quantity.toString());
        return((Document) storageDoc.get(key)).getDouble(SampleKeyEnum.Quantity.toString())+delta;
    }
    
    public static Document getStorages()
    {
        Document storage = new Document();
        if(sample.containsKey(SampleKeyEnum.Storage.toString()))
        {
            storage =(Document) sample.get(SampleKeyEnum.Storage.toString());
        }
        return storage;
    }
}
