/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui.user.mainapp;

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
public class MainFrameApp {

    private static int tabCount = 2;
    private static ArrayList recordList;
    private static ArrayList<ArrayList> templateList;
    private static final String desktopPath = System.getProperty("user.home") + "\\" + "Desktop";
    private static final String targetDir = "C:\\Customer accounts\\Sentinal demo\\Input folder";
    private static ArrayList<String> dirList;
    private static String dir;
    private static FileRW fileRW;

    public static void MainFrameApp() {
        MainFrameApp.fileRW = new FileRW();
        if (dirExist(targetDir)) {
            dirList = new ArrayList<String>(Arrays.asList(targetDir + "\\Mailing.txt",
                    targetDir + "\\Identity.txt"));
        } else {
            dirList = new ArrayList<String>(Arrays.asList(desktopPath + "\\Mailing.txt",
                    desktopPath + "\\Identity.txt"));
            System.out.println("dir NOT exsists");
        }
        MainFrameApp.recordList = new ArrayList();
        MainFrameApp.templateList = new ArrayList();

        for (int i = 0; i < tabCount; i++) {
            templateList.add(new ArrayList());
        }
    }

    private static boolean dirExist(String dir) {
        return new File(dir).exists() && new File(dir).isDirectory();
    }

    public static void readyToSave(int templateIndex) {
        if(null==templateList ||templateList.size()<=templateIndex)
            return;
        MainFrameApp.dir = (String) MainFrameApp.dirList.get(templateIndex);
        MainFrameApp.recordList.clear();
        ArrayList formatRecords = applyTemplateFormater(templateIndex, (ArrayList) templateList.get(templateIndex));
        MainFrameApp.recordList.addAll(formatRecords);
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
                MainFrameApp.fileRW.writeFile(dir);
                for (int i = 0; i < recordList.size(); i++) {
                    MainFrameApp.fileRW.getWriter().println(recordList.get(i).toString());
                }
                return true;

            } catch (IOException ex) {
                Logger.getLogger(MainFrameApp.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("file not found error");
                return false;
            } finally {
                MainFrameApp.fileRW.closeWriter();
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
        MainFrameApp.templateList = templateList;
    }

    public static int getTabCount() {
        return tabCount;
    }

    public static void setTabCount(int tabCount) {
        MainFrameApp.tabCount = tabCount;
    }

    public static FileRW getFileRW() {
        return fileRW;
    }

    public static void setFileRW(FileRW fileRW) {
        MainFrameApp.fileRW = fileRW;
    }

    public static String getDir() {
        return dir;
    }

    public static void setDir(String dir) {
        MainFrameApp.dir = dir;
    }
    private static final int MAX_COUNT = 1000;

    public static ArrayList getRecordList() {
        return recordList;
    }

    public static void setRecordList(ArrayList recordList) {
        MainFrameApp.recordList = recordList;
    }

    private static void clearRecordList() {
        if (null != MainFrameApp.recordList) {
            MainFrameApp.recordList.clear();
        }
    }

    public static void clearRecordListByTemplate(int index) {
        if (null == MainFrameApp.templateList || MainFrameApp.templateList.size()<=index) {
            return;
        }
        MainFrameApp.templateList.get(index).clear();
        MainFrameApp.clearRecordList();
    }

    public static int addRecord(JSONObject o, int selectedIndex) {
        templateList.get(selectedIndex).add(o);
        return templateList.get(selectedIndex).size();
    }

}
