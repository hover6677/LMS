/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.DAO;

import com.document.enumeration.EquipmentEnum;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class EquipmentDAO extends AbstractDAO {

    protected static EquipmentDAO DAO;
    private static MongoCollection equipCollection = null;
    private static final String CollectionStr = "Equipment";
    private static DBConnection DBConn = new DBConnection();

    public EquipmentDAO() {
        EquipmentDAO.DBConn.dbConnection();
    }

    @Override
    public boolean connDAO() {
        return EquipmentDAO.DBConn.dbConnection();
    }

    @Override
    public MongoCollection getCollection() {
        return equipCollection;
    }

    @Override
    public void setCollection() {
        if (null != DBConn.getDb()) {
            equipCollection = DBConn.getDb().getCollection(CollectionStr);
        } else {
            System.out.println("DB connection is not availbale ");
            System.out.println("reconnecting...");
            EquipmentDAO.DBConn.dbConnection();
        }
    }

    @Override
    public boolean addOrUpdate(Document eqDoc) {
        Document eqFound = isFound(eqDoc);

        try {
            if (null != eqFound) {
                update(eqFound, eqDoc);
                return true;
            } else {
                EquipmentDAO.equipCollection.insertOne(eqDoc);
                return true;
            }
        } catch (Exception e) {
            System.out.println("eqDoc insertion failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Document isFound(Document eqDoc) {
        Document searchQuery = new Document();
        Document docFetched = null;
        try {
            int active = eqDoc.getInteger(EquipmentEnum.Active.toString(), 1);
            String eid = eqDoc.getString(EquipmentEnum.EID.toString());

            searchQuery.put(EquipmentEnum.Active.toString(), active);
            searchQuery.put(EquipmentEnum.EID.toString(), eid);

            docFetched = (Document) equipCollection.find(searchQuery).first();

        } catch (Exception e) {
            System.out.println("fetch error");
            System.out.println(e);
        } finally {
            return docFetched;
        }
    }

    @Override
    public boolean update(Document eqFound, Document eqDoc) {

        try {
            softDeleteEQ(eqFound);
            EquipmentDAO.equipCollection.insertOne(eqDoc);
            return true;
        } catch (Exception e) {
            revertSoftDeletion(eqFound);
            System.out.println("update template failed");
            System.out.println(e);
            return false;
        }
    }

    private boolean softDeleteEQ(Document eqFound) {
        try {
            Document newObj = new Document();
            newObj.put(EquipmentEnum.Active.toString(), 0);
            Document updateObj = new Document();
            updateObj.put("$set", newObj);
            equipCollection.updateOne(eqFound, updateObj);
            return true;
        } catch (Exception e) {
            System.out.println("update softDeleteSample failed");
            System.out.println(e);
            return false;
        }
    }

    private boolean revertSoftDeletion(Document eqFound) {
        try {
            Document newObj = new Document();
            newObj.put(EquipmentEnum.Active.toString(), 1);
            Document updateObj = new Document();
            updateObj.put("$set", newObj);
            equipCollection.updateOne(eqFound, updateObj);
            return true;
        } catch (Exception e) {
            System.out.println("update softDeleteSample failed");
            System.out.println(e);
            return false;
        }

    }

    @Override
    public void closeDBConn() {
        EquipmentDAO.DBConn.closeDB();
    }

    @Override
    public ArrayList fetch(Document eqRequest) {
        ArrayList finds = new ArrayList();
        equipCollection.find(eqRequest).into(finds);
        return finds;
    }

    public static AbstractDAO getInstance() {
        // TODO Auto-generated method stub
        if (DAO == null) {
            DAO = new EquipmentDAO();
        }
        return DAO;
    }

    @Override
    public Document isLoginValid(Document userDoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList fetch(BasicDBObject sampleRequest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList fetchEQByUser(String user) {
        Document searchQuery = new Document();

        searchQuery.put(EquipmentEnum.Active.toString(), 1);
        searchQuery.put(EquipmentEnum.User.toString(), user);

        return fetch(searchQuery);

    }

    public ArrayList fetchAllEQ() {
        Document searchQuery = new Document();
        searchQuery.put(EquipmentEnum.Active.toString(), 1);
        return fetch(searchQuery);

    }

}
