/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.user;

import com.document.enumeration.ProcessKeyEnum;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class ProcessDAO {

    private static MongoCollection processCollection = null;
    private static final String CollectionStr = "Process";
    private static DBConnection DBConn = new DBConnection();

    public ProcessDAO() {
        ProcessDAO.DBConn.dbConnection();
    }
    
    public static boolean isDBConneced() {
        return ProcessDAO.DBConn.isDBConnected();
    }

    public static boolean connTempDAO() {
        if (!isDBConneced()) {
            return ProcessDAO.DBConn.dbConnection();
        } else {
            return true;
        }
    }
    
    private static void connectToCollection()
    {
        connTempDAO();
        setProcessCollection();
    }

    public static boolean connProcessDAO() {
        return ProcessDAO.DBConn.dbConnection();
    }

    public static MongoCollection getProcessCollection() {
        return processCollection;
    }

    public static void setProcessCollection() {
        if (null != DBConn.getDb()) {
            processCollection = DBConn.getDb().getCollection(CollectionStr);
        } else {
            System.out.println("DB connection is not availbale ");
            System.out.println("reconnecting...");
            ProcessDAO.DBConn.dbConnection();
            processCollection = DBConn.getDb().getCollection(CollectionStr);
        }
    }

    public static boolean addOrUpdateProcess(Document processDoc) {
        Document processFound = isProcessFound(processDoc);
        connectToCollection();
        try {
            if (null != processFound) {
                updateProcess(processFound, processDoc);
            } else {
                ProcessDAO.processCollection.insertOne(processDoc);
            }
            return true;
        } catch (Exception e) {
            System.out.println("processDoc insertion failed");
            System.out.println(e);
            return false;
        }
    }

    public static Document isProcessFound(Document processDoc) {
        connectToCollection();
        Document searchQuery = new Document();
        Document docFetched = null;
        try {
            int active = processDoc.getInteger(ProcessKeyEnum.Active.toString(), 1);
            String sid = processDoc.getString(ProcessKeyEnum.SID.toString());
            String tid = processDoc.getString(ProcessKeyEnum.TID.toString());

            searchQuery.put(ProcessKeyEnum.Active.toString(), active);
            searchQuery.put(ProcessKeyEnum.SID.toString(), sid);
            searchQuery.put(ProcessKeyEnum.TID.toString(), tid);

            docFetched = (Document) processCollection.find(searchQuery).first();

        } catch (Exception e) {
            System.out.println("fetch error");
            System.out.println(e);
        } finally {
            return docFetched;
        }
    }

    public static boolean updateProcess(Document processFound, Document processDoc) {
        connectToCollection();

        try {
            softDeleteProcess(processFound);
            ProcessDAO.processCollection.insertOne(processDoc);
            return true;
        } catch (Exception e) {
            revertSoftDeletion(processFound);
            System.out.println("update template failed");
            System.out.println(e);
            return false;
        }
    }

    private static boolean softDeleteProcess(Document processFound) {
        connectToCollection();
        try {
            Document newObj = new Document();
            newObj.put(ProcessKeyEnum.Active.toString(), 0);
            Document updateObj = new Document();
            updateObj.put("$set", newObj);
            processCollection.updateOne(processFound, updateObj);
            return true;
        } catch (Exception e) {
            System.out.println("update softDeleteProcess failed");
            System.out.println(e);
            return false;
        }
    }

    private static boolean revertSoftDeletion(Document processFound) {
        connectToCollection();
        try {
            Document newObj = new Document();
            newObj.put(ProcessKeyEnum.Active.toString(), 1);
            Document updateObj = new Document();
            updateObj.put("$set", newObj);
            processCollection.updateOne(processFound, updateObj);
            return true;
        } catch (Exception e) {
            System.out.println("update softDeleteProcess failed");
            System.out.println(e);
            return false;
        }

    }

    public static void closeDBConn() {
        ProcessDAO.DBConn.closeDB();
    }
    
    public static ArrayList fetchProcess(Document processRequest)
    {
        connectToCollection();
        ArrayList finds = new ArrayList();
        processCollection.find(processRequest).into(finds);
        return finds;
    }

}
