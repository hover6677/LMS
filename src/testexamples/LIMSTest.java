/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testexamples;

import com.db.mongodb.SampleDAO;
import com.db.mongodb.TemplateDAO;
import com.document.enumeration.SampleKeyEnum;
import com.document.enumeration.TemplateKeyEnum;
import com.document.enumeration.UnitEnum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.bson.Document;

/**
 * Data Structure:
            Sample{
                    Active:1
                    DateTime:2017-09-24 10:27:35.855
                    SID:"CocaCola"  //unique key to link to Process
                    User:"hover"
                    Receive
                        {
                            "water":
                                    {
                                        Quantity: 20
                                        Unit: ml
                                    }
                            "sugar"
                                    {
                                        Quantity:12
                                        Unit: mg
                                    }
                            "Comments: sdsdsdsdsdsd"
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
         */

public class LIMSTest {

    public static void main(String args[]) {
        //testTemplateDAO();
        testSampleDAO();
        //testGetTemplate();
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
        templateDoc.append(TemplateKeyEnum.TID.toString(), "template3");
        templateDoc.append(TemplateKeyEnum.Tags.toString(), Arrays.asList("v3.2"));
        templateDoc.append(TemplateKeyEnum.Type.toString(), "Receive");
        templateDoc.append(TemplateKeyEnum.User.toString(), "admin");

        if (TemplateDAO.connTempDAO()) {
            TemplateDAO.setTemplateCollection();

            if (TemplateDAO.addOrUpdateTemp(templateDoc)) {
                TemplateDAO.closeDBConn();
            }

        }
    }
}
