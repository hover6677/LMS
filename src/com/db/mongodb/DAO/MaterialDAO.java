/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.DAO;

import com.document.enumeration.MaterialKeyEnum;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class MaterialDAO extends AbstractDAO {

    protected static MaterialDAO DAO;
    private static MongoCollection materialCollection = null;
    private static final String CollectionStr = "Material";
    private static DBConnection DBConn = new DBConnection();

    public MaterialDAO() {
        MaterialDAO.DBConn.dbConnection();
    }

    @Override
    public boolean connDAO() {
        return MaterialDAO.DBConn.dbConnection();
    }

    @Override
    public MongoCollection getCollection() {
        return materialCollection;
    }

    @Override
    public void setCollection() {
        if (null != DBConn.getDb()) {
            materialCollection = DBConn.getDb().getCollection(CollectionStr);
        } else {
            System.out.println("DB connection is not availbale ");
            System.out.println("reconnecting...");
            MaterialDAO.DBConn.dbConnection();
        }
    }

    @Override
    public boolean addOrUpdate(Document materialDoc) {
        Document materialFound = isFound(materialDoc);

        try {
            if (null != materialFound) {
                update(materialFound, materialDoc);
            } else {
                MaterialDAO.materialCollection.insertOne(materialDoc);
            }
            return true;
        } catch (Exception e) {
            System.out.println("materialDoc insertion failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Document isFound(Document materialDoc) {
        Document searchQuery = new Document();
        Document docFetched = null;
        try {
            int active = materialDoc.getInteger(MaterialKeyEnum.Active.toString(), 1);
            String mid = materialDoc.getString(MaterialKeyEnum.MID.toString());

            searchQuery.put(MaterialKeyEnum.Active.toString(), active);
            searchQuery.put(MaterialKeyEnum.MID.toString(), mid);

            docFetched = (Document) materialCollection.find(searchQuery).first();

        } catch (Exception e) {
            System.out.println("fetch error");
            System.out.println(e);
        } finally {
            return docFetched;
        }
    }
    
    public boolean updateSampleList(String mid,String sid)
    {
        Document materialFound = isMaterialFound(mid);
        softDeleteMaterial(materialFound);
        Document materialUpdate = materialFound;
        materialUpdate = addSIDToList(materialUpdate,sid);
        update(materialFound,materialUpdate);
        return true;
    }
    
   private Document addSIDToList(Document doc, String sid)
   {
       ArrayList sidList = new ArrayList();
       if(null!=doc)
       {
           if(doc.containsKey(MaterialKeyEnum.SampleList.toString()))
           {
               sidList = addUniqueToList((ArrayList)doc.get(MaterialKeyEnum.SampleList.toString()),sid);
               doc.put(MaterialKeyEnum.SampleList.toString(), sidList);
           }
           else
           {
               sidList.add(sid);
               doc.put(MaterialKeyEnum.SampleList.toString(), sidList);
           }
       }
       return doc;
   }
   private ArrayList addUniqueToList(ArrayList alist, String str)
   {
       if(!alist.isEmpty() && alist.indexOf(str)<0)
       {
           alist.add(str);
       }
       return alist;
   }

    public Document isMaterialFound(String mid) {
        Document searchQuery = new Document();
        Document docFetched = null;
        try {
            searchQuery.put(MaterialKeyEnum.Active.toString(), 1);
            searchQuery.put(MaterialKeyEnum.MID.toString(), mid);

            docFetched = (Document) materialCollection.find(searchQuery).first();

        } catch (Exception e) {
            System.out.println("fetch error");
            System.out.println(e);
        } finally {
            return docFetched;
        }
    }

    @Override
    public boolean update(Document materialFound, Document materialDoc) {

        try {
            softDeleteMaterial(materialFound);
            if (!materialDoc.containsKey(MaterialKeyEnum.SampleList.toString())) {
                materialDoc.put(MaterialKeyEnum.SampleList.toString(), materialFound.get(MaterialKeyEnum.SampleList.toString()));
            }
            if (!materialDoc.containsKey(MaterialKeyEnum.Quantity.toString())) {
                materialDoc.put(MaterialKeyEnum.Quantity.toString(), materialFound.getDouble(MaterialKeyEnum.Quantity.toString()));
            }
            if (!materialDoc.containsKey(MaterialKeyEnum.Unit.toString())) {
                materialDoc.put(MaterialKeyEnum.Unit.toString(), materialFound.get(MaterialKeyEnum.Unit.toString()));
            }
            if (!materialDoc.containsKey(MaterialKeyEnum.User.toString())) {
                materialDoc.put(MaterialKeyEnum.User.toString(), materialFound.get(MaterialKeyEnum.User.toString()));
            }
            if (!materialDoc.containsKey(MaterialKeyEnum.Comments.toString())) {
                materialDoc.put(MaterialKeyEnum.Comments.toString(), materialFound.get(MaterialKeyEnum.Comments.toString()));
            }
            if (materialDoc.containsKey("_id")) {
                materialDoc.remove("_id");
            }
            MaterialDAO.materialCollection.insertOne(materialDoc);
            return true;
        } catch (Exception e) {
            revertSoftDeletion(materialFound);
            System.out.println("update template failed");
            System.out.println(e);
            return false;
        }
    }

    private boolean softDeleteMaterial(Document materialFound) {
        try {
            Document newObj = new Document();
            newObj.put(MaterialKeyEnum.Active.toString(), 0);
            Document updateObj = new Document();
            updateObj.put("$set", newObj);
            materialCollection.updateOne(materialFound, updateObj);
            return true;
        } catch (Exception e) {
            System.out.println("update softDeleteMaterial failed");
            System.out.println(e);
            return false;
        }
    }

    private boolean revertSoftDeletion(Document materialFound) {
        try {
            materialFound.put(MaterialKeyEnum.Active.toString(), 0);
            Document newObj = new Document();
            newObj.put(MaterialKeyEnum.Active.toString(), 1);
            Document updateObj = new Document();
            updateObj.put("$set", newObj);
            materialCollection.updateOne(materialFound, updateObj);
            return true;
        } catch (Exception e) {
            System.out.println("update softDeleteSample failed");
            System.out.println(e);
            return false;
        }

    }

    @Override
    public void closeDBConn() {
        MaterialDAO.DBConn.closeDB();
    }

    @Override
    public ArrayList fetch(Document materialRequest) {
        ArrayList finds = new ArrayList();
        materialCollection.find(materialRequest).into(finds);
        return finds;
    }

    public ArrayList fetch(BasicDBObject materialRequest) {
        ArrayList finds = new ArrayList();
        materialCollection.find(materialRequest).into(finds);
        return finds;
    }

    public static AbstractDAO getInstance() {
        // TODO Auto-generated method stub
        if (DAO == null) {
            DAO = new MaterialDAO();
        }
        return DAO;
    }

    @Override
    public Document isLoginValid(Document userDoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
