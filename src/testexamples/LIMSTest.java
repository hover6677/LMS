/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testexamples;

import com.db.mongodb.user.ProcessDAO;
import com.db.mongodb.user.SampleDAO;
import com.db.mongodb.user.TemplateDAO;
import com.document.enumeration.ProcessKeyEnum;
import com.document.enumeration.ReceiveTypeEnum;
import com.document.enumeration.SampleKeyEnum;
import com.document.enumeration.TemplateKeyEnum;
import com.document.enumeration.TemplateTypeEnum;
import com.document.enumeration.UnitEnum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.bson.Document;

/**
 * mongodump -h localhost -d LIMS -o C:\Users\admin1\Desktop\LIMS\
 * mongorestore -h localhost  -d LIMS --dir /home/mongodump/itcast/
 * 
 * Data Structure:
            Sample{
                    Active:1
                    DateTime:2017-09-24 10:27:35.855
                    SID:"CocaCola"  //unique key to link to Process
                    User:"hover"
                    Quantity:20
                    Unit:kg
                    Type:Solution
                    Comments: sdsdsdsdsdsd
                    Receive
                        {
                            vendor: asd
                            value: 153
                        }
                    
                    Storage
                        {
                            "S1"
                                    {
                                        Quantity:12
                                        Unit: mg
                                    }
                            "S2"
                                    {
                                        Quantity:10
                                        Unit: ml
                                    }
                            "Comments: sdsdsdsdsdsd"
                            "User" : hover 
                        }
                    }
                    ***************************************************
            Process{
                    Active:1
                    DateTime:2017-09-24 10:27:35.855
                    SID:"CocaCola"    //key   
                    TID:"template1"   //key
                    Comments : "sdsdsdsds"
                    User:"hover"
                    Steps
                        {
                            Step1 : abc
                            Step2 : sdsd
                        }
                    }
         */

public class LIMSTest {

    public static void main(String args[]) {
        testTemplateDAO();
        //testSampleDAO();
        //testGetTemplate();
        //testInsertProcess();
        //testGetProcess();
        //System.out.println(TemplateTypeEnum.values()[0].toString());
        //System.out.println(ReceiveTypeEnum.names());
    }

    
    private static void testGetProcess() {
        
        Document processDoc = new Document();
        processDoc.append(ProcessKeyEnum.Active.toString(), 1);
        processDoc.append(ProcessKeyEnum.SID.toString(), "CocaCola");
        processDoc.append(ProcessKeyEnum.TID.toString(), "CocaTemp1");

        if (ProcessDAO.connProcessDAO()) {
            ProcessDAO.setProcessCollection();
            ArrayList fetchTemplate = ProcessDAO.fetchProcess(processDoc);
            System.out.println(fetchTemplate.toString());
        }
        ProcessDAO.closeDBConn();
        
    }
    
    private static void testInsertProcess()
    {
        Document processDoc = new Document();
        processDoc.append(ProcessKeyEnum.Active.toString(), 1);
        processDoc.append(ProcessKeyEnum.SID.toString(), "CocaCola");
        processDoc.append(ProcessKeyEnum.TID.toString(), "CocaTemp1");
        processDoc.append(ProcessKeyEnum.User.toString(), "hover");
        processDoc.append(ProcessKeyEnum.Comments.toString(), "comments");
        processDoc.append(ProcessKeyEnum.DateTime.toString(), new Date());
        
        Document stepObj = new Document();
        stepObj.append("STEP1", "1skdaskdas");
        processDoc.append(ProcessKeyEnum.Steps.toString(), stepObj);
        
         if (ProcessDAO.connProcessDAO()) {
            ProcessDAO.setProcessCollection();
            ProcessDAO.addOrUpdateProcess(processDoc);
        }
        ProcessDAO.closeDBConn();
        
    }
    
    private static void testGetTemplate() {
        Document templateDoc = new Document();
        templateDoc.append(TemplateKeyEnum.Active.toString(), 1);
        templateDoc.append(TemplateKeyEnum.Type.toString(), "Receive");
        templateDoc.append(TemplateKeyEnum.User.toString(), "admin");

        if (TemplateDAO.connTempDAO()) {
            TemplateDAO.setTemplateCollection();
            ArrayList fetchTemplate = TemplateDAO.fetchTemplate(templateDoc);
            System.out.println(fetchTemplate.toString());
        }
        TemplateDAO.closeDBConn();

    }

    private static void testSampleDAO() {
        

        Document templateDoc = new Document();
        templateDoc.append(SampleKeyEnum.Active.toString(), 1);
        templateDoc.append(SampleKeyEnum.DateTime.toString(), new Date());
        templateDoc.append(SampleKeyEnum.SID.toString(), "CocaCola");
        templateDoc.append(SampleKeyEnum.User.toString(), "hover");

        Document objR = new Document();
        Document subObjR1 = new Document();
        subObjR1.append(SampleKeyEnum.Quantity.toString(), 20);
        subObjR1.append(SampleKeyEnum.Unit.toString(), UnitEnum.ml.toString());
        objR.append("water", subObjR1);

        Document subObjR2 = new Document();
        subObjR2.append(SampleKeyEnum.Quantity.toString(), 120);
        subObjR2.append(SampleKeyEnum.Unit.toString(), UnitEnum.ml.toString());
        objR.append("sugar", subObjR2);
        objR.append(SampleKeyEnum.Comments.toString(), "Red");
        
        templateDoc.append(SampleKeyEnum.Receive.toString(), objR);

        Document objS = new Document();
        objS.append(SampleKeyEnum.Comments.toString(), "blue");
        objS.append(SampleKeyEnum.User.toString(), "hover");
        Document subObjS1 = new Document();
        subObjS1.append(SampleKeyEnum.Quantity.toString(), 120);
        subObjS1.append(SampleKeyEnum.Unit.toString(), UnitEnum.ml.toString());
        objS.append("Storage1", subObjS1);

        templateDoc.append(SampleKeyEnum.Storage.toString(), objS);

        if (SampleDAO.connSampleDAO()) {
            SampleDAO.setSampleCollection();

            if (SampleDAO.addOrUpdateSample(templateDoc)) {
                SampleDAO.closeDBConn();
            }

        }
    }

    private static void testTemplateDAO() {
        Document templateDoc = new Document();
        templateDoc.append(TemplateKeyEnum.Active.toString(), 1);
        templateDoc.append(TemplateKeyEnum.Count.toString(), 3);
        templateDoc.append(TemplateKeyEnum.DateTime.toString(), new Date());
        templateDoc.append(TemplateKeyEnum.TID.toString(), "Storage");
        templateDoc.append(TemplateKeyEnum.Tags.toString(), Arrays.asList("Fridge","LAB1","Storage Room"));
        templateDoc.append(TemplateKeyEnum.Type.toString(), "Storage");
        templateDoc.append(TemplateKeyEnum.User.toString(), "admin");

        if (TemplateDAO.connTempDAO()) {
            TemplateDAO.setTemplateCollection();

            if (TemplateDAO.addOrUpdateTemp(templateDoc)) {
                TemplateDAO.closeDBConn();
            }

        }
    }

}
