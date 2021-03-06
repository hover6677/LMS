/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.DAO;

import com.document.enumeration.ProcessKeyEnum;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.bson.Document;

/**
 *
 * @author admin1
 */
public class ProcessDAO extends AbstractDAO {

    protected static ProcessDAO DAO ;
    private static MongoCollection processCollection = null;
    private static final String CollectionStr = "Process";
    private static DBConnection DBConn = new DBConnection();
    
    private ProcessDAO() {
        ProcessDAO.DBConn.dbConnection();
    }
    
    @Override
    public boolean connDAO() {
        return ProcessDAO.DBConn.dbConnection();
    }
    
    @Override
    public MongoCollection getCollection() {
    	if(processCollection==null)
    		setCollection();
        return processCollection;
    }

    @Override
    public void setCollection() {
        if (null != DBConn.getDb()) {
            processCollection = DBConn.getDb().getCollection(CollectionStr);
        } else {
            System.out.println("DB connection is not availbale ");
            System.out.println("reconnecting...");
            ProcessDAO.DBConn.dbConnection();
            processCollection = DBConn.getDb().getCollection(CollectionStr);
        }
    }

    @Override
    public boolean addOrUpdate(Document processDoc) {
        Document processFound = isFound(processDoc);

        try {
            if (null != processFound) {
                update(processFound, processDoc);
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

    @Override
    public Document isFound(Document processDoc) {
    	BasicDBObject searchQuery = new BasicDBObject();
        Document docFetched = null;
        try {
            int active = processDoc.getInteger(ProcessKeyEnum.Active.toString(), 1);
            String sid = processDoc.getString(ProcessKeyEnum.SID.toString());
            String tid = processDoc.getString(ProcessKeyEnum.TID.toString());

            searchQuery.put(ProcessKeyEnum.Active.toString(), active);
            searchQuery.put(ProcessKeyEnum.SID.toString(), Pattern.compile("^"+sid.trim()+"$" , Pattern.CASE_INSENSITIVE));
            if(tid!=null)
            	searchQuery.put(ProcessKeyEnum.TID.toString(), tid);

            BasicDBObject sortObject = new BasicDBObject().append("_id", -1);
            FindIterable iter=(FindIterable) processCollection.find(searchQuery).sort(sortObject).limit(1);
            docFetched = (Document)iter.first();
            //docFetched = (FindIterableImpl) processCollection.find(searchQuery).sort(sortObject).limit(1);

        } catch (Exception e) {
            System.out.println("fetch error");
            System.out.println(e);
        } finally {
            return docFetched;
        }
    }

    @Override
    public boolean update(Document processFound, Document processDoc) {

        try {
            softDeleteProcess(processFound);
            if(processDoc.containsKey("_id"))
                processDoc.remove("_id");
            ProcessDAO.processCollection.insertOne(processDoc);
            return true;
        } catch (Exception e) {
            revertSoftDeletion(processFound);
            System.out.println("update template failed");
            System.out.println(e);
            return false;
        }
    }

    private boolean softDeleteProcess(Document processFound) {
        try {
            processFound.put(ProcessKeyEnum.Active.toString(), 0);
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

    private boolean revertSoftDeletion(Document processFound) {
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

    @Override
    public void closeDBConn() {
        ProcessDAO.DBConn.closeDB();
    }
    
    @Override
    public ArrayList fetch(Document processRequest)
    {
        ArrayList finds = new ArrayList();
        processCollection.find(processRequest).into(finds);
        return finds;
    }

    public ArrayList fetch(BasicDBObject processRequest)
    {
        ArrayList finds = new ArrayList();
        processCollection.find(processRequest).into(finds);
        return finds;
    }

	public static AbstractDAO getInstance() {
		// TODO Auto-generated method stub
		if(DAO==null)
			DAO = new ProcessDAO();
		if(DAO.processCollection==null)DAO.setCollection();
		return DAO;
	}

//    @Override
//    public Document isProcessFound(String sid) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public Document isLoginValid(Document userDoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
