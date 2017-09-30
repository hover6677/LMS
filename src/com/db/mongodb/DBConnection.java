/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author admin1
 */
public class DBConnection {

    private static String IPString = "localhost";
    private static int port = 27017;
    private final static String dbName = "LIMS";

    private MongoClient mongoClient = null;
    private MongoDatabase db = null;

    public boolean dbConnection() {
        for (int i = 0; i < 5; i++) {
            if (this.setMongoClient()) {
                if (this.setDb()) {
                    break;
                }
            }
            if (i == 4) {
                System.out.println("Can not connect to DB. Please retry later");
                return false;
            }
        }
        return true;
    }

    public static String getDbName() {
        return dbName;
    }

    public static String getIPString() {
        return IPString;
    }

    public static void setIPString(String IPString) {
        DBConnection.IPString = IPString;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        DBConnection.port = port;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public boolean setMongoClient() {
        try {
            this.mongoClient = new MongoClient(IPString, port);
        } catch (Exception e) {
            System.out.println("Cannot Connect to DB:" + IPString + ":" + port);
            System.out.println(e);
            return false;
        }
        return true;
    }

    public MongoDatabase getDb() {
        return db;
    }

    public boolean setDb() {
        if (null != mongoClient) {
            this.db = mongoClient.getDatabase(dbName);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean closeDB()
    {
        if (null != mongoClient) {
            this.mongoClient.close();
            return true;
        } else {
            return false;
        }
    }

}
