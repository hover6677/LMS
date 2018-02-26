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
        EquipmentDAO.getInstance().setCollection();
        eqList = ((EquipmentDAO) EquipmentDAO.getInstance()).fetchAllEQ();
        return eqList;
    }

    private ArrayList initEquipByPara(Document para) {
        ArrayList alist = new ArrayList();
        if (para.containsKey(EquipmentEnum.Equipment.toString())) {
            String[] list = (String[]) para.get(EquipmentEnum.Equipment.toString());
            for (int i = 0; i < list.length; i++) {
                Document doc = new Document();
                doc.append(EquipmentEnum.Active.toString(), 1);
                doc.append(EquipmentEnum.EID.toString(), list[i]);
                doc.append(EquipmentEnum.Status.toString(), 1);
                doc.append(EquipmentEnum.DateTime.toString(), new Date());
                doc.append(EquipmentEnum.User.toString(), UserManagementEnum.admin.toString());
                alist.add(doc);
            }
        }
        return alist;
    }

    public void updateEQStatusByUser(String user, int status,String eid) {
        Document doc = new Document();
        doc.append(EquipmentEnum.Active.toString(),1);
        doc.append(EquipmentEnum.DateTime.toString(), new Date());
        doc.append(EquipmentEnum.EID.toString(), eid);
        doc.append(EquipmentEnum.Status.toString(), status);
        doc.append(EquipmentEnum.User.toString(), user);
        ((EquipmentDAO) EquipmentDAO.getInstance()).addOrUpdate(doc);
    }

    public boolean updateEQListByPara(Document para) {

        ArrayList<Document> newList = initEquipByPara(para);
        ArrayList<Document> fetchedList = fetchAllEQDoc();

        if (fetchedList.size() == 0) {
            for (int i = 0; i < newList.size(); i++) {
                ((EquipmentDAO) EquipmentDAO.getInstance()).addOrUpdate(newList.get(i));
            }
            return true;
        } else {
            for (int i = 0; i < newList.size(); i++) {
                for (int j = 0; j < fetchedList.size(); j++) {
                    if (newList.get(i).getString(EquipmentEnum.EID.toString()).equals(fetchedList.get(j).getString(EquipmentEnum.EID.toString()))) {
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
        }
        return true;
    }

}
