/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui.login.mainapp;

import com.db.mongodb.DAO.DBConnection;
import com.db.mongodb.DAO.UserManagementDAO;
import com.db.mongodb.user.helper.EquipmentDAOHelper;
import com.document.enumeration.UserManagementEnum;
import com.ui.admin.mainframe.AdminMainFrame;
import com.ui.login.mainframe.LoginMainFrame;
import com.ui.user.mainframe.UserMainFrame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.fileRW.FileRW;

/**
 *
 * @author admin1
 */
public class LoginMainFrameApp {

    private static String errorLog = "";
    private static Document userLogined = null;
    private static UserMainFrame userMainFrame;
    private static AdminMainFrame uiFrame;
    private static Document Parameters = new Document();
    private final static String config = "src/config/config.txt";

    public static UserMainFrame getUserMainFrame() {
        return userMainFrame;
    }

    public static AdminMainFrame getUiFrame() {
        return uiFrame;
    }

    private static boolean readParaFromFile() {
        FileRW file = new FileRW();
        String l = "";
        try {
            file.readFile(config);
            BufferedReader buf_r = file.getBuf_r();

            while (null != (l = buf_r.readLine())) {
                String[] key = l.split("=");
                String[] values = key[1].trim().split(",");
                Parameters.append(key[0].trim(), values);
            }

        } catch (Exception ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    public static Document getUserLogined() {
        return userLogined;
    }

    public static String getErrorLog() {
        return errorLog;
    }

    public static void setErrorLog(String errorLog) {
        LoginMainFrameApp.errorLog = errorLog;
    }

    public static boolean isValidInput(String input) {
        if (input.trim().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean userLogin(String uid, String pass) {
        Document inputDoc = new Document();
        inputDoc.append(UserManagementEnum.Active.toString(), 1);
        inputDoc.append(UserManagementEnum.User.toString(), uid);
        inputDoc.append(UserManagementEnum.Password.toString(), pass);

        if (UserManagementDAO.getInstance().connDAO()) {
            UserManagementDAO.getInstance().setCollection();
            userLogined = UserManagementDAO.getInstance().isLoginValid(inputDoc);
            if (null != userLogined) {
                errorLog = "";
                return true;
            } else {
                errorLog = "User ID or Password error.";
                return false;
            }
        } else {
            errorLog = "DB Coonection lost. Please check DB configuration.";
            return false;
        }
    }

    public static void displayView() {
        String uid = userLogined.getString(UserManagementEnum.User.toString());
        readParaFromFile();
        refreshEquipment();
        if (uid.equals(UserManagementEnum.admin.toString())) {
            uiFrame = new AdminMainFrame(Parameters);
            uiFrame.setVisible(true);
        } else {
            userMainFrame = new UserMainFrame(userLogined,Parameters);
            userMainFrame.setVisible(true);
        }

    }
    private static boolean refreshEquipment()
    {
        EquipmentDAOHelper eq = new EquipmentDAOHelper();
        return eq.updateEQListByPara(Parameters);
    }
}
