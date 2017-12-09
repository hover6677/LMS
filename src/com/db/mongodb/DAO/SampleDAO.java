/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.DAO;

import com.db.mongodb.user.DBConnection;
import com.document.enumeration.SampleKeyEnum;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class SampleDAO extends AbstractDAO {

	protected static SampleDAO DAO ;
    private static MongoCollection sampleCollection = null;
    private static final String CollectionStr = "Sample";
    private static DBConnection DBConn = new DBConnection();

    
    public SampleDAO() {
        SampleDAO.DBConn.dbConnection();
    }

    @Override
    public boolean connDAO() {
        return SampleDAO.DBConn.dbConnection();
    }

    @Override
    public  MongoCollection getCollection() {
        return sampleCollection;
    }

    @Override
    public void setCollection() {
        if (null != DBConn.getDb()) {
            sampleCollection = DBConn.getDb().getCollection(CollectionStr);
        } else {
            System.out.println("DB connection is not availbale ");
            System.out.println("reconnecting...");
            SampleDAO.DBConn.dbConnection();
        }
    }

    @Override
    public boolean addOrUpdate(Document sampleDoc) {
        Document sampleFound = isFound(sampleDoc);

        try {
            if (null != sampleFound) {
                update(sampleFound, sampleDoc);
            } else {
                SampleDAO.sampleCollection.insertOne(sampleDoc);
            }
            return true;
        } catch (Exception e) {
            System.out.println("sampleDoc insertion failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Document isFound(Document sampleDoc) {
        Document searchQuery = new Document();
        Document docFetched = null;
        try {
            int active = sampleDoc.getInteger(SampleKeyEnum.Active.toString(), 1);
            String sid = sampleDoc.getString(SampleKeyEnum.SID.toString());

            searchQuery.put(SampleKeyEnum.Active.toString(), active);
            searchQuery.put(SampleKeyEnum.SID.toString(), sid);

            docFetched = (Document) sampleCollection.find(searchQuery).first();

        } catch (Exception e) {
            System.out.println("fetch error");
            System.out.println(e);
        } finally {
            return docFetched;
        }
    }

    @Override
    public boolean update(Document sampleFound, Document sampleDoc) {

        try {
            softDeleteSample(sampleFound);
            if (!sampleDoc.containsKey(SampleKeyEnum.Receive.toString())) {
                sampleDoc.put(SampleKeyEnum.Receive.toString(), sampleFound.get(SampleKeyEnum.Receive.toString()));
            }
            if (!sampleDoc.containsKey(SampleKeyEnum.Storage.toString())) {
                sampleDoc.put(SampleKeyEnum.Storage.toString(), sampleFound.get(SampleKeyEnum.Storage.toString()));
            }
            SampleDAO.sampleCollection.insertOne(sampleDoc);
            return true;
        } catch (Exception e) {
            revertSoftDeletion(sampleFound);
            System.out.println("update template failed");
            System.out.println(e);
            return false;
        }
    }

    private boolean softDeleteSample(Document sampleFound) {
        try {
            Document newObj = new Document();
            newObj.put(SampleKeyEnum.Active.toString(), 0);
            Document updateObj = new Document();
            updateObj.put("$set", newObj);
            sampleCollection.updateOne(sampleFound, updateObj);
            return true;
        } catch (Exception e) {
            System.out.println("update softDeleteSample failed");
            System.out.println(e);
            return false;
        }
    }

    private boolean revertSoftDeletion(Document sampleFound) {
        try {
            Document newObj = new Document();
            newObj.put(SampleKeyEnum.Active.toString(), 1);
            Document updateObj = new Document();
            updateObj.put("$set", newObj);
            sampleCollection.updateOne(sampleFound, updateObj);
            return true;
        } catch (Exception e) {
            System.out.println("update softDeleteSample failed");
            System.out.println(e);
            return false;
        }

    }

    @Override
    public void closeDBConn() {
        SampleDAO.DBConn.closeDB();
    }
    
    @Override
    public ArrayList fetch(Document sampleRequest)
    {
        ArrayList finds = new ArrayList();
        sampleCollection.find(sampleRequest).into(finds);
        return finds;
    }

    public ArrayList fetch(BasicDBObject sampleRequest)
    {
        ArrayList finds = new ArrayList();
        sampleCollection.find(sampleRequest).into(finds);
        return finds;
    }
    
    public static AbstractDAO getInstance() {
		// TODO Auto-generated method stub
		if(DAO==null)
			DAO = new SampleDAO();
		return DAO;
	}

    @Override
    public Document isProcessFound(String sid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Document isLoginValid(Document userDoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
