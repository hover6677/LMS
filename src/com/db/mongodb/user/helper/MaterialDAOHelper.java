/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.user.helper;

import com.db.mongodb.DAO.MaterialDAO;
import com.document.enumeration.MessageEnum;
import com.document.enumeration.MaterialKeyEnum;
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
public class MaterialDAOHelper {
    
    private static Document material = new Document();

    public static Document getMaterial() {
        return material;
    }

    public static void setMaterial(Document material) {
        MaterialDAOHelper.material = material;
    }
          
    public static boolean fetchMaterialByMID(String mid)
    {
        material =  ((MaterialDAO)MaterialDAO.getInstance()).isMaterialFound(mid);
        return (null!=material);
    }
    
    public static boolean prepareReceivedMaterial(String mid, double quatity,String tid,String unit,String type,String remarks,Document objR,Document objS)
    {
        material = new Document();
        material.append(MaterialKeyEnum.Active.toString(), 1);
        material.append(MaterialKeyEnum.DateTime.toString(), new Date());
        material.append(MaterialKeyEnum.MID.toString(), mid);
        material.append(MaterialKeyEnum.User.toString(), UserMainFrameApp.getUserName());
        material.append(MaterialKeyEnum.Quantity.toString(), quatity);
        material.append(MaterialKeyEnum.Unit.toString(), unit);
        material.append(MaterialKeyEnum.Type.toString(), type);
        material.append(MaterialKeyEnum.Comments.toString(), remarks);
        material.append(MaterialKeyEnum.Receive.toString(), objR);
        material.append(MaterialKeyEnum.Storage.toString(), objS);
        return true;
    }

    public static MessageEnum  prepareStorage(String mid, String remarks, Document objS, boolean inFlag) {
        if(!fetchMaterialByMID(mid))
        {
            return MessageEnum.MaterialNotFound;
        }
        else
        {
            if(material.containsKey(MaterialKeyEnum.Storage.toString()))
            {
                Document storageDoc = (Document) material.get(MaterialKeyEnum.Storage.toString());
                storageDoc = (null!=storageDoc)?storageDoc : new Document();
                
                Iterator<String> it = objS.keySet().iterator();
                while(it.hasNext())
                {
                    String key = it.next().toString();
                    if(storageDoc.containsKey(key))
                    {
                        if(((Document) objS.get(key)).getString(MaterialKeyEnum.Unit.toString()).equals(((Document) storageDoc.get(key)).getString(MaterialKeyEnum.Unit.toString())))
                        {
                            Document pair = new Document();
                            pair.append(MaterialKeyEnum.Quantity.toString(), ((Document)objS.get(key)).getDouble(MaterialKeyEnum.Quantity.toString()));
                            pair.append(MaterialKeyEnum.Unit.toString(), ((Document) objS.get(key)).getString(MaterialKeyEnum.Unit.toString()));
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
                material.put(MaterialKeyEnum.Storage.toString(),storageDoc);
            }
            else
            {
                objS.append(MaterialKeyEnum.Comments.toString(), remarks);
                objS.append(MaterialKeyEnum.User.toString(),UserMainFrameApp.getUserName());
                material.append(MaterialKeyEnum.Storage.toString(), objS);
            }
            return MessageEnum.RecordSaved;
        }
    }
/*
    private static Double updateQuality(boolean inFlag,String key, Document objS, Document storageDoc) {
        
        Double delta = (inFlag?1:-1)*((Document) objS.get(key)).getDouble(MaterialKeyEnum.Quantity.toString());
        return((Document) storageDoc.get(key)).getDouble(MaterialKeyEnum.Quantity.toString())+delta;
    }
*/
    
    public static Document getStorages()
    {
        Document storage = new Document();
        if(material.containsKey(MaterialKeyEnum.Storage.toString()))
        {
            storage =(Document) material.get(MaterialKeyEnum.Storage.toString());
        }
        return storage;
    }
    public static double getMaterialQuality(String mid)
    {
        if(fetchMaterialByMID(mid))
        {
            return material.getDouble(MaterialKeyEnum.Quantity.toString());
        }
        else
        {
            return -1;
        }
        
    }
    public static boolean updateMaterialQuality(String mid,double q)
    {
        if(fetchMaterialByMID(mid))
        {
            material.put(MaterialKeyEnum.Quantity.toString(),q);
            return true;
        }
        else
        {
            return false;
        }
        
    }
}
