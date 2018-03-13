/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.mongodb.DAO;

import com.db.mongodb.admin.helper.LMSUtils;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.fileRW.FileRW;

/**
 *
 * @author admin1
 */
public class DBConnection {

    private static String IPString = "localhost";
    private final static int port = 27017;
    private final static String dbName = "LIMS";
    //private final static String config = LMSUtils.getConfigFolderPath()+"/config.txt";
    private final static String config = "config/config.txt";

    private MongoClient mongoClient = null;
    private MongoDatabase db = null;

    public boolean dbConnection() {

        String ip = readIPFromFile();
        for (int i = 0; i < 1; i++) {
            setIPString(ip);
            if (this.setMongoClient()) {
                if (this.setDb()) {
                    break;
                }
            }
            if (i == 0) {
                System.out.println("Can not connect to DB. Please retry later");
                return false;
            }
        }
        return true;
    }

    private String readIPFromFile() {
        String l = "LOCALHOST";
        FileRW file = new FileRW();
        try {
            file.readFile(config);
            BufferedReader buf_r = file.getBuf_r();

            while (null != (l = buf_r.readLine())) {
                l = l.split("=")[1].trim();
                break;
            }

        } catch (FileNotFoundException fileNotFoundException) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, fileNotFoundException);
        } catch (IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return l;
        }
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

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public boolean setMongoClient() {

        Builder o = MongoClientOptions.builder().serverSelectionTimeout(1000);
        this.mongoClient = new MongoClient(new ServerAddress(IPString, port), o.build());

        try {
            this.mongoClient.getAddress();
        } catch (Exception e) {
            System.out.println("Cannot Connect to DB:" + IPString + ":" + port);
            this.mongoClient.close();
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

    public boolean closeDB() {
        if (null != mongoClient) {
            this.mongoClient.close();
            return true;
        } else {
            return false;
        }
    }

    public boolean isDBConnected() {
        try {
            this.mongoClient.getAddress();
        } catch (Exception e) {
            System.out.println("Mongo is down");
            return false;
        }
        return true;
    }

}
