/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui.login.mainapp;

import com.db.mongodb.DAO.UserManagementDAO;
import com.document.enumeration.UserManagementEnum;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class LoginMainFrameApp {
    private static String errorLog = "";
    private static Document userLogined = null;

    public static Document getUserLogined() {
        return userLogined;
    }

    public static String getErrorLog() {
        return errorLog;
    }

    public static void setErrorLog(String errorLog) {
        LoginMainFrameApp.errorLog = errorLog;
    }
    
    public static boolean isValidInput(String input)
    {
        if(input.trim().length()>0)
            return true;
        else
            return false;
    }

    public static boolean userLogin(String uid, String pass) {
        Document inputDoc = new Document();
        inputDoc.append(UserManagementEnum.Active.toString(), 1);
        inputDoc.append(UserManagementEnum.User.toString(),uid);
        inputDoc.append(UserManagementEnum.Password.toString(), pass);
        
        if (UserManagementDAO.getInstance().connDAO()) {
            UserManagementDAO.getInstance().setCollection();
            userLogined = UserManagementDAO.getInstance().isLoginValid(inputDoc);
            if(null!=userLogined)
            {
                errorLog = "";
                return true;
            }
            else
            {
                errorLog = "User ID or Password error.";
                return false;
            }
        }
        else
        {
            errorLog = "DB Coonection lost. Please check DB configuration.";
            return false;
        }
    }
}
