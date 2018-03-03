/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this attach file, choose Tools | Attachments
 * and open the attach in the editor.
 */
package com.db.mongodb.user.helper;

import com.db.mongodb.DAO.AbstractDAO;
import com.db.mongodb.DAO.AttachmentDAO;
import com.document.enumeration.AttachmentKeyEnum;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author admin1
 */
public class AttachmentDAOHelper {

    public ArrayList attachList = new ArrayList();

    public AttachmentDAOHelper() {

        ((AttachmentDAO) AttachmentDAO.getInstance()).connDAO();
        ((AttachmentDAO) AttachmentDAO.getInstance()).setCollection();
    }

    private String[] fetchTIDList() {
        String[] TIDList = new String[this.attachList.size()];

        for (int i = 0; i < this.attachList.size(); i++) {
            Document doc = (Document) this.attachList.get(i);
            TIDList[i] = doc.getString(AttachmentKeyEnum.TID.toString());
        }

        return TIDList;
    }

    public ArrayList fetchAttachList(String tid, ArrayList tags) {
        ArrayList attches = new ArrayList();
        for (int i = 0; i < tags.size(); i++) {
            Document attachDoc = new Document();
            attachDoc.append(AttachmentKeyEnum.TID.toString(), tid);
            attachDoc.append(AttachmentKeyEnum.Tag.toString(), tags.get(i).toString());
            attachDoc.append(AttachmentKeyEnum.Active.toString(), 1);;
            attches.add(((AttachmentDAO) AttachmentDAO.getInstance()).fetchFirstAttachment(attachDoc));
        }

        return attches;
    }

    public Document fetchAttach(String tid, String tag) {
        Document attachDoc = new Document();
        attachDoc.append(AttachmentKeyEnum.TID.toString(), tid);
        attachDoc.append(AttachmentKeyEnum.Tag.toString(), tag);
        attachDoc.append(AttachmentKeyEnum.Active.toString(), 1);;
        return ((AttachmentDAO) AttachmentDAO.getInstance()).fetchFirstAttachment(attachDoc);
    }

    private String fetchAttachmentID(int index) {
        Document doc = (Document) this.attachList.get(index);
        return doc.getString(AttachmentKeyEnum.TID.toString());
    }

}
