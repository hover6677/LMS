/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.DAO;

import com.document.enumeration.TemplateKeyEnum;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class TemplateDAO extends AbstractDAO {

    protected static TemplateDAO DAO;
    private static MongoCollection templateCollection = null;
    private static final String CollectionStr = "Template";
    private static DBConnection DBConn = new DBConnection();

    private TemplateDAO() {
        TemplateDAO.DBConn.dbConnection();
    }

    @Override
    public boolean connDAO() {
        return TemplateDAO.DBConn.dbConnection();
    }

    @Override
    public MongoCollection getCollection() {
        return templateCollection;
    }

    @Override
    public void setCollection() {
        if (null != DBConn.getDb()) {
            templateCollection = DBConn.getDb().getCollection(CollectionStr);
        } else {
            System.out.println("DB connection is not availbale ");
            System.out.println("reconnecting...");
            TemplateDAO.DBConn.dbConnection();
        }
    }

    @Override
    public boolean addOrUpdate(Document templateDoc) {
        Document tempDefined = isFound(templateDoc);

        try {
            if (null != tempDefined) {
                softDeleteTemplate(tempDefined);
            }
            TemplateDAO.templateCollection.insertOne(templateDoc);
            return true;
        } catch (Exception e) {
            revertSoftDeletion(tempDefined);
            System.out.println("insertion failed");
            System.out.println(e);
            return false;
        }
    }

    public boolean softDeleteTemplate(Document tempDefined) {

        try {
            Document newObj = new Document();
            newObj.put(TemplateKeyEnum.Active.toString(), 0);

            Document updateObj = new Document();
            updateObj.put("$set", newObj);

            templateCollection.updateOne(tempDefined, updateObj);

            return true;
        } catch (Exception e) {
            System.out.println("update template failed");
            System.out.println(e);
            return false;
        }
    }

    private boolean revertSoftDeletion(Document tempDefined) {
        try {
            tempDefined.put(TemplateKeyEnum.Active.toString(), 0);
            
            Document newObj = new Document();
            newObj.put(TemplateKeyEnum.Active.toString(), 1);

            Document updateObj = new Document();
            updateObj.put("$set", newObj);

            templateCollection.updateOne(tempDefined, updateObj);

            return true;
        } catch (Exception e) {
            System.out.println("update template failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Document isFound(Document templateDoc) {
        Document searchQuery = new Document();
        Document docFetched = null;
        try {
            int active = templateDoc.getInteger(TemplateKeyEnum.Active.toString(), 0);
            String user = templateDoc.getString(TemplateKeyEnum.User.toString());
            String type = templateDoc.getString(TemplateKeyEnum.Type.toString());
            String tid = templateDoc.getString(TemplateKeyEnum.TID.toString());

            searchQuery.put(TemplateKeyEnum.Active.toString(), active);
            searchQuery.put(TemplateKeyEnum.User.toString(), user);
            searchQuery.put(TemplateKeyEnum.Type.toString(), type);
            searchQuery.put(TemplateKeyEnum.TID.toString(), tid);

            BasicDBObject sortObject = new BasicDBObject().append("_id", -1);
            docFetched = (Document) templateCollection.find(searchQuery).sort(new Document("_id", -1)).limit(1);

        } catch (Exception e) {
            System.out.println("fetch error");
            System.out.println(e);
        } finally {
            return docFetched;
        }
    }

    @Override
    public void closeDBConn() {
        TemplateDAO.DBConn.closeDB();
    }

    @Override
    public ArrayList fetch(Document templateRequest) {
        ArrayList finds = new ArrayList();
        //templateCollection.find(templateRequest).sort(new Document("_id", -1)).limit(1).into(finds);
        templateCollection.find(templateRequest).sort(new Document("_id", -1)).into(finds);
        return finds;
    }

    public ArrayList fetchTemplate(String templateType) {
        Document templateRequest = new Document();
        templateRequest.append(TemplateKeyEnum.Type.toString(), templateType);
        templateRequest.append(TemplateKeyEnum.Active.toString(), 1);

        return fetch(templateRequest);
    }

    public ArrayList fetchTemplate(String admin, String templateType) {
        Document templateRequest = new Document();
        templateRequest.append(TemplateKeyEnum.Type.toString(), templateType);
        templateRequest.append(TemplateKeyEnum.Active.toString(), 1);
        templateRequest.append(TemplateKeyEnum.User.toString(), admin);

        return fetch(templateRequest);
    }

    @Override
    public boolean update(Document found, Document doc) {
        // TODO Auto-generated method stub
        try {
            softDeleteTemplate(found);
            if(doc.containsKey("_id"))
                doc.remove("_id");
            TemplateDAO.templateCollection.insertOne(doc);
            return true;
        } catch (Exception e) {
            revertSoftDeletion(found);
            System.out.println("update template failed");
            System.out.println(e);
            return false;
        }
    }

    public static AbstractDAO getInstance() {
        if (DAO == null) {
            DAO = new TemplateDAO();
        }
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
