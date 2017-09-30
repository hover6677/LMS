/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb;

import com.document.enumeration.TemplateKeyEnum;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class TemplateDAO {

    private static MongoCollection templateCollection = null;
    private static final String CollectionStr = "Template";
    private static DBConnection DBConn = new DBConnection();

    public TemplateDAO() {
        TemplateDAO.DBConn.dbConnection();
    }

    public static boolean connTempDAO() {
        return TemplateDAO.DBConn.dbConnection();
    }

    public static MongoCollection getTemplateCollection() {
        return templateCollection;
    }

    public static void setTemplateCollection() {
        if (null != DBConn.getDb()) {
            templateCollection = DBConn.getDb().getCollection(CollectionStr);
        } else {
            System.out.println("DB connection is not availbale ");
            System.out.println("reconnecting...");
            TemplateDAO.DBConn.dbConnection();
        }
    }

    public static boolean addOrUpdateTemp(Document templateDoc) {
        Document tempDefined = isTempDefined(templateDoc);

        try {
            if (null != tempDefined) {
                softDeleteTemplate(tempDefined);
            }
            TemplateDAO.templateCollection.insertOne(templateDoc);
            return true;
        } catch (Exception e) {
            revertSoftDelete(tempDefined);
            System.out.println("insertion failed");
            System.out.println(e);
            return false;
        }
    }

    public static boolean softDeleteTemplate(Document tempDefined) {

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

    private static boolean revertSoftDelete(Document tempDefined) {
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

    public static Document isTempDefined(Document templateDoc) {
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

            docFetched = (Document) templateCollection.find(searchQuery).first();

        } catch (Exception e) {
            System.out.println("fetch error");
            System.out.println(e);
        } finally {
            return docFetched;
        }
    }

    public static void closeDBConn() {
        TemplateDAO.DBConn.closeDB();
    }
    
    public static ArrayList fetchTemplate(Document templateRequest)
    {
        ArrayList finds = new ArrayList();
        templateCollection.find(templateRequest).into(finds);
        return finds;
    }
    
    public static ArrayList fetchTemplate(String templateType)
    {
        Document templateRequest = new Document();
        templateRequest.append(TemplateKeyEnum.Type.toString(),templateType);
        templateRequest.append(TemplateKeyEnum.Active.toString(),1);

        return fetchTemplate(templateRequest);
    }

}
