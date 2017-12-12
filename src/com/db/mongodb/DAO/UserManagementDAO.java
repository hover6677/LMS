/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.DAO;

import com.db.mongodb.user.DBConnection;
import com.document.enumeration.SampleKeyEnum;
import com.document.enumeration.UserManagementEnum;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class UserManagementDAO extends AbstractDAO {

    protected static UserManagementDAO DAO;
    private static MongoCollection userCollection = null;
    private static final String CollectionStr = "User";
    private static DBConnection DBConn = new DBConnection();

    public UserManagementDAO() {
        UserManagementDAO.DBConn.dbConnection();
    }

    @Override
    public boolean connDAO() {
        return UserManagementDAO.DBConn.dbConnection();
    }

    @Override
    public MongoCollection getCollection() {
        return userCollection;
    }

    @Override
    public void setCollection() {
        if (null != DBConn.getDb()) {
            userCollection = DBConn.getDb().getCollection(CollectionStr);
        } else {
            System.out.println("DB connection is not availbale ");
            System.out.println("reconnecting...");
            UserManagementDAO.DBConn.dbConnection();
        }
    }

    @Override
    public boolean addOrUpdate(Document userDoc) {
        Document userFound = isFound(userDoc);

        try {
            if (null != userFound) {
                update(userFound,userDoc);
                return true;
            } else {
                UserManagementDAO.userCollection.insertOne(userDoc);
                return true;
            }
        } catch (Exception e) {
            System.out.println("userDoc insertion failed");
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Document isFound(Document userDoc) {
        Document searchQuery = new Document();
        Document docFetched = null;
        try {
            int active = userDoc.getInteger(UserManagementEnum.Active.toString(), 1);
            String uid = userDoc.getString(UserManagementEnum.User.toString());

            searchQuery.put(UserManagementEnum.Active.toString(), active);
            searchQuery.put(UserManagementEnum.User.toString(), uid);

            docFetched = (Document) userCollection.find(searchQuery).first();

        } catch (Exception e) {
            System.out.println("fetch error");
            System.out.println(e);
        } finally {
            return docFetched;
        }
    }

    @Override
    public boolean update(Document userFound, Document userDoc) {
        
        try {
            softDeleteUser(userFound);
            UserManagementDAO.userCollection.insertOne(userDoc);
            return true;
        } catch (Exception e) {
            revertSoftDeletion(userFound);
            System.out.println("update template failed");
            System.out.println(e);
            return false;
        }
    }
    
    @Override
    public Document isLoginValid(Document userDoc) {
        Document searchQuery = new Document();
        Document docFetched = null;
        try {
            int active = userDoc.getInteger(UserManagementEnum.Active.toString(), 1);
            String uid = userDoc.getString(UserManagementEnum.User.toString());
            String pass = userDoc.getString(UserManagementEnum.Password.toString());

            searchQuery.put(UserManagementEnum.Active.toString(), active);
            searchQuery.put(UserManagementEnum.User.toString(), uid);
            searchQuery.put(UserManagementEnum.Password.toString(), pass);

            docFetched = (Document) userCollection.find(searchQuery).first();

        } catch (Exception e) {
            System.out.println("fetch error");
            System.out.println(e);
        } finally {
            return docFetched;
        }
    }

    private boolean softDeleteUser(Document userFound) {
        try {
            Document newObj = new Document();
            newObj.put(UserManagementEnum.Active.toString(), 0);
            Document updateObj = new Document();
            updateObj.put("$set", newObj);
            userCollection.updateOne(userFound, updateObj);
            return true;
        } catch (Exception e) {
            System.out.println("update softDeleteSample failed");
            System.out.println(e);
            return false;
        }
    }

    private boolean revertSoftDeletion(Document userFound) {
        try {
            Document newObj = new Document();
            newObj.put(UserManagementEnum.Active.toString(), 1);
            Document updateObj = new Document();
            updateObj.put("$set", newObj);
            userCollection.updateOne(userFound, updateObj);
            return true;
        } catch (Exception e) {
            System.out.println("update softDeleteSample failed");
            System.out.println(e);
            return false;
        }

    }

    @Override
    public void closeDBConn() {
        UserManagementDAO.DBConn.closeDB();
    }

    @Override
    public ArrayList fetch(Document userRequest) {
        ArrayList finds = new ArrayList();
        userCollection.find(userRequest).into(finds);
        return finds;
    }

    public ArrayList fetch(BasicDBObject userRequest) {
        ArrayList finds = new ArrayList();
        userCollection.find(userRequest).into(finds);
        return finds;
    }

    public static AbstractDAO getInstance() {
        // TODO Auto-generated method stub
        if (DAO == null) {
            DAO = new UserManagementDAO();
        }
        return DAO;
    }

    @Override
    public Document isProcessFound(String sid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
