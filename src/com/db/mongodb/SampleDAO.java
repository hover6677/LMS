/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb;

import com.document.enumeration.SampleKeyEnum;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class SampleDAO {

    private static MongoCollection sampleCollection = null;
    private static final String CollectionStr = "Sample";
    private static DBConnection DBConn = new DBConnection();

    public SampleDAO() {
        SampleDAO.DBConn.dbConnection();
    }

    public static boolean connSampleDAO() {
        return SampleDAO.DBConn.dbConnection();
    }

    public static MongoCollection getSampleCollection() {
        return sampleCollection;
    }

    public static void setSampleCollection() {
        if (null != DBConn.getDb()) {
            sampleCollection = DBConn.getDb().getCollection(CollectionStr);
        } else {
            System.out.println("DB connection is not availbale ");
            System.out.println("reconnecting...");
            SampleDAO.DBConn.dbConnection();
        }
    }

    public static boolean addOrUpdateSample(Document sampleDoc) {
        Document sampleFound = isSampleFound(sampleDoc);

        try {
            if (null != sampleFound) {
                updateSample(sampleFound, sampleDoc);
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

    public static Document isSampleFound(Document sampleDoc) {
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
    public static Document isSampleFound(String sid) {
        Document searchQuery = new Document();
        Document docFetched = null;
        try {
            searchQuery.put(SampleKeyEnum.Active.toString(), 1);
            searchQuery.put(SampleKeyEnum.SID.toString(), sid);

            docFetched = (Document) sampleCollection.find(searchQuery).first();

        } catch (Exception e) {
            System.out.println("fetch error");
            System.out.println(e);
        } finally {
            return docFetched;
        }
    }

    public static boolean updateSample(Document sampleFound, Document sampleDoc) {

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

    private static boolean softDeleteSample(Document sampleFound) {
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

    private static boolean revertSoftDeletion(Document sampleFound) {
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

    public static void closeDBConn() {
        SampleDAO.DBConn.closeDB();
    }
    
    public static ArrayList fetchSample(Document sampleRequest)
    {
        ArrayList finds = new ArrayList();
        sampleCollection.find(sampleRequest).into(finds);
        return finds;
    }

}
