/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this eq file, choose Tools | Templates
 * and open the eq in the editor.
 */
package com.db.mongodb.user.helper;

import com.db.mongodb.DAO.EquipmentDAO;
import com.document.enumeration.EquipmentEnum;
import com.document.enumeration.UserManagementEnum;
import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class EquipmentDAOHelper {

    
    public ArrayList eqList = new ArrayList();

    public EquipmentDAOHelper() {
        
        ((EquipmentDAO) EquipmentDAO.getInstance()).connDAO();
    }

    
    public void fetchEQListByUser(String user) {
        eqList.clear();
        eqList = ((EquipmentDAO) EquipmentDAO.getInstance()).fetchEQByUser(user);
    }

    public ArrayList fetchAllEQDoc() {
        eqList.clear();
        eqList = ((EquipmentDAO) EquipmentDAO.getInstance()).fetchAllEQ();
        return eqList;
    }

    private ArrayList initEquipByPara(Document para) {
        ArrayList alist = new ArrayList();
        if (para.containsKey(EquipmentEnum.Equipment.toString())) {
            for (int i=0;i<alist.size();i++) 
            {
                Document doc = new Document();
                doc.append(EquipmentEnum.Active.toString(), 1);
                doc.append(EquipmentEnum.EID.toString(), alist.get(i));
                doc.append(EquipmentEnum.Status.toString(), 1);
                doc.append(EquipmentEnum.DateTime.toString(), new Date());
                doc.append(EquipmentEnum.User.toString(), UserManagementEnum.admin.toString());
                alist.add(doc);
            }
        }
        return alist;
    }

    public boolean updateEQListByPara(Document para) {
        if (para.getString(UserManagementEnum.User) != UserManagementEnum.admin.toString()) {
            return false;
        } else {

            ArrayList<Document> newList = initEquipByPara(para);
            ArrayList<Document> fetchedList = fetchAllEQDoc();
            for (int i = 0; i < newList.size(); i++) {
                for (int j = 0; j < fetchedList.size(); j++) {
                    if (newList.get(i).getString(EquipmentEnum.EID) == fetchedList.get(j).getString(EquipmentEnum.EID)) {
                        break;
                    } else if (j == fetchedList.size() - 1) {
                        fetchedList.add(newList.get(i));
                         ((EquipmentDAO) EquipmentDAO.getInstance()).addOrUpdate(newList.get(i));
                        break;
                    } else {
                        continue;
                    }
                }

            }
            return true;
        }
    }

}
