/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui.user.mainapp;

import com.db.mongodb.ProcessDAO;
import com.db.mongodb.SampleDAO;
import com.db.mongodb.TemplateDAO;
import com.mongodb.client.MongoCollection;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fileRW.FileRW;
import org.json.JSONObject;

/**
 *
 * @author admin1
 */
public class UserMainFrameApp {

    private static int tabCount = 4;
    private static ArrayList recordList;
    private static ArrayList<ArrayList> templateList;
    private static final String desktopPath = System.getProperty("user.home") + "\\" + "Desktop";
    private static final String configDir = "C:\\Customer accounts\\Sentinal demo\\Input folder";
    public  static String AdminName = "admin";
    public  static ArrayList labelList;
    public  static ArrayList textFiledList;
    public  static ArrayList dropDownList;
    private static ArrayList<String> dirList;
    private static String dir;
    private static FileRW fileRW;

    public static void MainFrameApp() {
        UserMainFrameApp.fileRW = new FileRW();
        /*if (dirExist(configDir)) {
            dirList = new ArrayList<String>(Arrays.asList(configDir + "\\Mailing.txt",
                    configDir + "\\Identity.txt"));
        } else {
            dirList = new ArrayList<String>(Arrays.asList(desktopPath + "\\Mailing.txt",
                    desktopPath + "\\Identity.txt"));
            System.out.println("dir NOT exsists");
        }
        MainFrameApp.recordList = new ArrayList();
        MainFrameApp.templateList = new ArrayList();

        for (int i = 0; i < tabCount; i++) {
            templateList.add(new ArrayList());
        }*/
        if (ProcessDAO.connProcessDAO()) {
            ProcessDAO.setProcessCollection();
        }
        if (SampleDAO.connSampleDAO()) {
            SampleDAO.setSampleCollection();
        }
        if (TemplateDAO.connTempDAO()) {
            TemplateDAO.setTemplateCollection();
        }
        
        labelList = new ArrayList();
        textFiledList = new ArrayList();
        dropDownList = new ArrayList();
    }
    
    public static void MainFrameAppClose()
    {
        System.out.println("closing DB...");
        ProcessDAO.closeDBConn();
        SampleDAO.closeDBConn();
        TemplateDAO.closeDBConn();
        System.out.println("DB closed");
    }

    private static boolean dirExist(String dir) {
        return new File(dir).exists() && new File(dir).isDirectory();
    }

    public static void readyToSave(int templateIndex) {
        if(null==templateList ||templateList.size()<=templateIndex)
            return;
        UserMainFrameApp.dir = (String) UserMainFrameApp.dirList.get(templateIndex);
        UserMainFrameApp.recordList.clear();
        ArrayList formatRecords = applyTemplateFormater(templateIndex, (ArrayList) templateList.get(templateIndex));
        UserMainFrameApp.recordList.addAll(formatRecords);
    }

    private static ArrayList applyTemplateFormater(int templateIndex, ArrayList list) {
        if (templateIndex == 0) {
        } else if (templateIndex == 1) {
        } else {
            return null;
        }
        return null;
    }

    public static boolean saveFile(boolean flag) throws IOException {
        if (!flag) {
            return flag;
        }
        if (null != recordList) {
            try {
                UserMainFrameApp.fileRW.writeFile(dir);
                for (int i = 0; i < recordList.size(); i++) {
                    UserMainFrameApp.fileRW.getWriter().println(recordList.get(i).toString());
                }
                return true;

            } catch (IOException ex) {
                Logger.getLogger(UserMainFrameApp.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("file not found error");
                return false;
            } finally {
                UserMainFrameApp.fileRW.closeWriter();
                clearRecordList();
            }
        } else {
            return false;
        }
    }

    public static String getDirList(int index) {
        return dirList.get(index);
    }

    public static ArrayList<ArrayList> getTemplateList() {
        return templateList;
    }

    public static void setTemplateList(ArrayList<ArrayList> templateList) {
        UserMainFrameApp.templateList = templateList;
    }

    public static int getTabCount() {
        return tabCount;
    }

    public static void setTabCount(int tabCount) {
        UserMainFrameApp.tabCount = tabCount;
    }

    public static FileRW getFileRW() {
        return fileRW;
    }

    public static void setFileRW(FileRW fileRW) {
        UserMainFrameApp.fileRW = fileRW;
    }

    public static String getDir() {
        return dir;
    }

    public static void setDir(String dir) {
        UserMainFrameApp.dir = dir;
    }
    private static final int MAX_COUNT = 1000;

    public static ArrayList getRecordList() {
        return recordList;
    }

    public static void setRecordList(ArrayList recordList) {
        UserMainFrameApp.recordList = recordList;
    }

    private static void clearRecordList() {
        if (null != UserMainFrameApp.recordList) {
            UserMainFrameApp.recordList.clear();
        }
    }

    public static void clearRecordListByTemplate(int index) {
        if (null == UserMainFrameApp.templateList || UserMainFrameApp.templateList.size()<=index) {
            return;
        }
        UserMainFrameApp.templateList.get(index).clear();
        UserMainFrameApp.clearRecordList();
    }

    public static int addRecord(JSONObject o, int selectedIndex) {
        templateList.get(selectedIndex).add(o);
        return templateList.get(selectedIndex).size();
    }

}
