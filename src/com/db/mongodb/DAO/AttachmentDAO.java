/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.DAO;

import com.document.enumeration.AttachmentKeyEnum;
import com.document.enumeration.UserManagementEnum;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class AttachmentDAO extends AbstractDAO {

    protected static AttachmentDAO DAO;
    private static MongoCollection attachmentCollection = null;
    private static final String CollectionStr = "Attachment";
    private static DBConnection DBConn = new DBConnection();

    private AttachmentDAO() {
        AttachmentDAO.DBConn.dbConnection();
    }

    @Override
    public boolean connDAO() {
        return AttachmentDAO.DBConn.dbConnection();
    }

    @Override
    public MongoCollection getCollection() {
        return attachmentCollection;
    }

    @Override
    public void setCollection() {
        if (null != DBConn.getDb()) {
            attachmentCollection = DBConn.getDb().getCollection(CollectionStr);
        } else {
            System.out.println("DB connection is not availbale ");
            System.out.println("reconnecting...");
            AttachmentDAO.DBConn.dbConnection();
        }
    }

    @Override
    public boolean addOrUpdate(Document attachDoc) {
        Document attachDefined = isFound(attachDoc);

        try {
            if (null != attachDefined) {
                softDeleteAttachment(attachDefined);
            }
            AttachmentDAO.attachmentCollection.insertOne(attachDoc);
            return true;
        } catch (Exception e) {
            revertSoftDeletion(attachDefined);
            System.out.println("insertion failed");
            System.out.println(e);
            return false;
        }
    }

    public boolean softDeleteAttachment(Document attachDefined) {

        try {
            Document newObj = new Document();
            newObj.put(AttachmentKeyEnum.Active.toString(), 0);

            Document updateObj = new Document();
            updateObj.put("$set", newObj);

            attachmentCollection.updateOne(attachDefined, updateObj);

            return true;
        } catch (Exception e) {
            System.out.println("update template failed");
            System.out.println(e);
            return false;
        }
    }

    private boolean revertSoftDeletion(Document attachDefined) {
        try {
            attachDefined.put(AttachmentKeyEnum.Active.toString(), 0);

            Document newObj = new Document();
            newObj.put(AttachmentKeyEnum.Active.toString(), 1);

            Document updateObj = new Document();
            updateObj.put("$set", newObj);

            attachmentCollection.updateOne(attachDefined, updateObj);

            return true;
        } catch (Exception e) {
            System.out.println("update template failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Document isFound(Document attachDoc) {
        Document searchQuery = new Document();
        Document docFetched = null;
        try {
            String ip = attachDoc.getString(AttachmentKeyEnum.IP.toString());
            String dir = attachDoc.getString(AttachmentKeyEnum.DIR.toString());
            String file = attachDoc.getString(AttachmentKeyEnum.FileName.toString());
            String tid = attachDoc.getString(AttachmentKeyEnum.TID.toString());
            String tag = attachDoc.getString(AttachmentKeyEnum.Tag.toString());

            searchQuery.put(AttachmentKeyEnum.Active.toString(), 1);
            searchQuery.put(AttachmentKeyEnum.DIR.toString(), dir);
            searchQuery.put(AttachmentKeyEnum.FileName.toString(), file);
            searchQuery.put(AttachmentKeyEnum.IP.toString(), ip);
            searchQuery.put(AttachmentKeyEnum.Tag.toString(), tag);
            searchQuery.put(AttachmentKeyEnum.TID.toString(), tid);

            docFetched = (Document) attachmentCollection.find(searchQuery).first();

        } catch (Exception e) {
            System.out.println("fetch error");
            System.out.println(e);
        } finally {
            return docFetched;
        }
    }

    @Override
    public void closeDBConn() {
        AttachmentDAO.DBConn.closeDB();
    }

    @Override
    public ArrayList fetch(Document attachRequest) {
        ArrayList finds = new ArrayList();
        attachmentCollection.find(attachRequest).sort(new Document("_id", -1)).into(finds);
        return finds;
    }

    public ArrayList fetchAttachments(Document attachDoc) {
        attachDoc.put(AttachmentKeyEnum.Active.toString(), 1);
        return fetch(attachDoc);
    }

    @Override
    public boolean update(Document found, Document doc) {
        // TODO Auto-generated method stub
        /*try {
            softDeleteAttachment(found);
            if (doc.containsKey("_id")) {
                doc.remove("_id");
            }
            AttachmentDAO.attachmentCollection.insertOne(doc);
            return true;
        } catch (Exception e) {
            revertSoftDeletion(found);
            System.out.println("update template failed");
            System.out.println(e);
            return false;
        }*/
        return false;
    }

    public static AbstractDAO getInstance() {
        if (DAO == null) {
            DAO = new AttachmentDAO();
        }
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

    @Override
    public ArrayList fetch(BasicDBObject attachRequest) {
        // TODO Auto-generated method stub
        ArrayList finds = new ArrayList();
        //templateCollection.find(attachRequest).sort(new Document("_id", -1)).limit(1).into(finds);
        attachmentCollection.find(attachRequest).sort(new Document("_id", -1)).into(finds);
        return finds;

    }

}
