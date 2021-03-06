/*
 * Copyright (c) 2010, Oracle. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of Oracle nor the names of its contributors
 *   may be used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.ui.user.mainframe;

import com.db.mongodb.DAO.MaterialDAO;
import com.db.mongodb.DAO.ProcessDAO;
import com.db.mongodb.DAO.SampleDAO;
import com.db.mongodb.DAO.TemplateDAO;
import com.db.mongodb.admin.helper.ExportToExcelAction;
import com.db.mongodb.admin.helper.Printer;
import com.db.mongodb.user.helper.MaterialDAOHelper;
import com.db.mongodb.user.helper.ProcessDAOHelper;
import com.db.mongodb.user.helper.SampleDAOHelper;
import com.db.mongodb.user.helper.TemplateDAOHelper;
import com.document.enumeration.*;
import com.mongodb.BasicDBObject;
import com.ui.admin.mainframe.AdminMainFrame;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.ui.user.mainapp.UserMainFrameApp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.lang.Thread.sleep;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;
import com.db.mongodb.admin.helper.Printer;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import com.db.mongodb.admin.helper.TableColumnAdjuster;
import com.db.mongodb.user.helper.EquipmentDAOHelper;
import java.util.Arrays;

public class UserMainFrame extends javax.swing.JFrame {

    /**
     * Creates new form Antenna
     */
    public UserMainFrame() {
        UserMainFrameApp.MainFrameApp();
        initComponents();
        this.setLocationRelativeTo(null);
        UserMainFrameApp.setTabCount(this.jTabbedPane1.getTabCount());
    }

    public UserMainFrame(Document userDoc, Document paraDoc) {
        UserMainFrameApp.MainFrameApp();
        initComponents();
        this.setLocationRelativeTo(null);
        this.jComboBoxTags1.setModel(new javax.swing.DefaultComboBoxModel((Object[]) paraDoc.get("Unit")));
        displayUserView(userDoc);
        UserMainFrameApp.setUserName(userDoc.getString(UserManagementEnum.User.toString()));
        UserMainFrameApp.setTabCount(this.jTabbedPane1.getTabCount());
        UserMainFrameApp.setParaDoc(paraDoc);
    }

    private void displayUserView(Document userDoc) {
        if (null != userDoc) {
            Document userView = (Document) userDoc.get(UserManagementEnum.View.toString());

            if (!userView.getBoolean(TemplateTypeEnum.Report.toString())) {
                this.jTabbedPane1.removeTabAt(3);
            }
            if (!userView.getBoolean(TemplateTypeEnum.Storage.toString())) {
                this.jTabbedPane1.removeTabAt(2);
            }
            if (!userView.getBoolean(TemplateTypeEnum.Process.toString())) {
                this.jTabbedPane1.removeTabAt(1);
            }
            if (!userView.getBoolean(TemplateTypeEnum.Receive.toString())) {
                this.jTabbedPane1.removeTabAt(0);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jComboBoxTags = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxTags1 = new javax.swing.JComboBox<>();
        jComboBoxTags3 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        searchBtn2 = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxTags2 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        excelBtn = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        clearBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        msgLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LMS");
        setLocation(new java.awt.Point(320, 200));
        setMinimumSize(new java.awt.Dimension(742, 650));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel7.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));

        jTabbedPane1.setBackground(java.awt.SystemColor.controlHighlight);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTabbedPane1.setName("Tabs"); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel2.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel2.setMaximumSize(new java.awt.Dimension(480, 203));
        jPanel2.setName("Receive"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Material");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField1.setMinimumSize(new java.awt.Dimension(6, 25));
        jTextField1.setName("ID"); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(6, 25));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Template");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jComboBoxTags.setModel(new javax.swing.DefaultComboBoxModel(UserMainFrameApp.getTemplateDAO().fetchTIDList())  );
        jComboBoxTags.setToolTipText("select a Template");
        jComboBoxTags.setMinimumSize(new java.awt.Dimension(120, 25));
        jComboBoxTags.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTagsItemStateChanged(evt);
            }
        });
        jComboBoxTags.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBoxTagsFocusGained(evt);
            }
        });
        jComboBoxTags.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTagsActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(2000, 230));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(450, 230));

        jPanel6.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel6.setMaximumSize(new java.awt.Dimension(32767, 20000));
        jPanel6.setPreferredSize(new java.awt.Dimension(450, 230));

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 450, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 230, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel6);
        jPanel6.getAccessibleContext().setAccessibleParent(jPanel2);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);
        jTextArea1.getAccessibleContext().setAccessibleParent(jPanel2);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Quantity");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Type");
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jComboBoxTags1.setMinimumSize(new java.awt.Dimension(120, 25));
        jComboBoxTags1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTags1ActionPerformed(evt);
            }
        });

        jComboBoxTags3.setModel(new javax.swing.DefaultComboBoxModel(ReceiveTypeEnum.names()));
        jComboBoxTags3.setMinimumSize(new java.awt.Dimension(120, 25));
        jComboBoxTags3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTags3ActionPerformed(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField4.setMinimumSize(new java.awt.Dimension(6, 25));
        jTextField4.setName("ID"); // NOI18N
        jTextField4.setPreferredSize(new java.awt.Dimension(6, 25));

        jScrollPane8.setBorder(null);
        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane8.setPreferredSize(new java.awt.Dimension(200, 230));

        jPanel5.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 230));

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 264, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 230, Short.MAX_VALUE)
        );

        jScrollPane8.setViewportView(jPanel5);

        jLabel17.setText("Remarks");

        jButton6.setText("Print Receiving Label");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(24, 24, 24)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel2Layout.createSequentialGroup()
                                        .add(238, 238, 238)
                                        .add(filler1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(jPanel2Layout.createSequentialGroup()
                                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(jPanel2Layout.createSequentialGroup()
                                                .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                                .add(jComboBoxTags3, 0, 177, Short.MAX_VALUE))
                                            .add(jPanel2Layout.createSequentialGroup()
                                                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(23, 23, 23)
                                                .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .add(30, 30, 30)
                                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(jPanel2Layout.createSequentialGroup()
                                                .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 64, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .add(43, 43, 43)
                                                .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 81, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jComboBoxTags1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                            .add(jScrollPane8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 264, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .add(18, 18, 18)
                                .add(jComboBoxTags, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 270, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(jLabel17)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jScrollPane4))))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jButton6)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 428, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .add(43, 43, 43))
        );

        jPanel2Layout.linkSize(new java.awt.Component[] {jLabel17, jLabel9}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(filler1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(jLabel3)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jComboBoxTags, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jComboBoxTags1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jComboBoxTags3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jButton6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(new java.awt.Component[] {jComboBoxTags, jComboBoxTags1, jComboBoxTags3, jTextField1, jTextField4}, org.jdesktop.layout.GroupLayout.VERTICAL);

        jPanel2Layout.linkSize(new java.awt.Component[] {jLabel3, jLabel4, jLabel5}, org.jdesktop.layout.GroupLayout.VERTICAL);

        jComboBoxTags.getAccessibleContext().setAccessibleName("Select a Template");
        jComboBoxTags.getAccessibleContext().setAccessibleDescription("Select a Template");

        jTabbedPane1.addTab("Receiving", null, jPanel2, "");
        jPanel2.getAccessibleContext().setAccessibleName("Receive");

        jPanel4.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel4.setMaximumSize(new java.awt.Dimension(480, 203));
        jPanel4.setName("Process"); // NOI18N

        searchBtn2.setText("Search");
        searchBtn2.setMaximumSize(new java.awt.Dimension(66, 25));
        searchBtn2.setMinimumSize(new java.awt.Dimension(66, 25));
        searchBtn2.setPreferredSize(new java.awt.Dimension(66, 25));
        searchBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtn2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Material ID");
        jLabel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField3.setMinimumSize(new java.awt.Dimension(6, 25));
        jTextField3.setName("ID"); // NOI18N
        jTextField3.setPreferredSize(new java.awt.Dimension(6, 25));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Template");
        jLabel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jComboBoxTags2.setModel(new javax.swing.DefaultComboBoxModel(UserMainFrameApp.getTemplateDAO().fetchTIDList()));
        jComboBoxTags2.setMinimumSize(new java.awt.Dimension(120, 25));
        jComboBoxTags2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTags2ItemStateChanged(evt);
            }
        });
        jComboBoxTags2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBoxTags2FocusGained(evt);
            }
        });
        jComboBoxTags2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTags2ActionPerformed(evt);
            }
        });

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setMaximumSize(new java.awt.Dimension(32767, 230));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(970, 232));

        jPanel8.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel8.setMaximumSize(new java.awt.Dimension(32767, 230));
        jPanel8.setName("process"); // NOI18N
        jPanel8.setPreferredSize(new java.awt.Dimension(970, 230));

        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 981, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 230, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel8);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Remarks");
        jLabel15.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane5.setViewportView(jTextArea2);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Sample ID");
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField5.setEnabled(false);
        jTextField5.setMinimumSize(new java.awt.Dimension(6, 25));
        jTextField5.setName("ID"); // NOI18N
        jTextField5.setPreferredSize(new java.awt.Dimension(6, 25));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jButton5.setText("Print Process Label");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jButton5))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(33, 33, 33)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel4Layout.createSequentialGroup()
                                .add(jLabel6)
                                .add(36, 36, 36)
                                .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 199, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(searchBtn2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jLabel7)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jTextField5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jLabel8)
                                .add(18, 18, 18)
                                .add(jComboBoxTags2, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(jPanel4Layout.createSequentialGroup()
                                .add(filler2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(0, 0, Short.MAX_VALUE))
                            .add(jPanel4Layout.createSequentialGroup()
                                .add(jLabel15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(jScrollPane5)))))
                .add(45, 45, 45))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(filler2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(24, 24, 24)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(jComboBoxTags2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(searchBtn2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(23, 23, 23)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 52, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(187, 187, 187))
        );

        jPanel4Layout.linkSize(new java.awt.Component[] {jComboBoxTags2, jLabel6, jLabel7, jLabel8, jTextField3, jTextField5, searchBtn2}, org.jdesktop.layout.GroupLayout.VERTICAL);

        jTabbedPane1.addTab("Processing", jPanel4);
        jPanel4.getAccessibleContext().setAccessibleName("Process");

        jPanel3.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel3.setName("Storage"); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Sample ID");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jTextField10.setMaximumSize(new java.awt.Dimension(800, 2147483647));
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setMaximumSize(new java.awt.Dimension(32767, 195));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(920, 195));

        jPanel9.setBackground(java.awt.SystemColor.controlHighlight);

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1479, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 195, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(jPanel9);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane6.setViewportView(jTextArea3);

        jLabel2.setText("Remarks");

        jRadioButton1.setBackground(java.awt.SystemColor.controlHighlight);
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText(" IN");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(java.awt.SystemColor.controlHighlight);
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText(" OUT");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Print Storage Lable");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jButton3))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(56, 56, 56)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3Layout.createSequentialGroup()
                                .add(jLabel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jTextField10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 440, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 35, Short.MAX_VALUE)
                                .add(jLabel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(111, 111, 111)
                                .add(jRadioButton1)
                                .add(18, 18, 18)
                                .add(jRadioButton2))
                            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jScrollPane6)))))
                .add(55, 55, 55))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(28, 28, 28)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(jTextField10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jRadioButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jRadioButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel10))
                .add(24, 24, 24)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 195, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(" Storage ", jPanel3);
        jPanel3.getAccessibleContext().setAccessibleName("Storage");

        jPanel1.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel1.setName("Report"); // NOI18N

        jLabel11.setText("Sample ID");

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setText("From");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("To");

        jButton4.setText("Filter By Date");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jScrollPane7.setBackground(java.awt.SystemColor.controlHighlight);
        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(java.awt.SystemColor.controlHighlight);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SID", "MID", "UserID", "Creation_Time", "Comments", "Storage", "Storage_UserID", "Storage_Comments", "Process_ID", "Processed_By", "Process_time", "Process_Commnets", "Steps"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setDragEnabled(true);
        jTable1.setGridColor(new java.awt.Color(51, 51, 51));
        jTable1.setRowHeight(20);
        jScrollPane7.setViewportView(jTable1);

        excelBtn.setText("Export To Excel");
        excelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelBtnActionPerformed(evt);
            }
        });

        jLabel16.setText("Material ID");

        jTextField6.setEnabled(false);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane7)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(99, 99, 99)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel11)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel12))
                .add(24, 24, 24)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField2)
                    .add(jXDatePicker1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                .add(61, 61, 61)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel16, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(27, 27, 27)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jXDatePicker2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .add(jTextField6))
                .add(40, 40, 40)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jButton4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .add(jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(excelBtn))
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {excelBtn, jButton2, jButton4}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel11)
                    .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel16)
                    .add(jTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jXDatePicker1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel14)
                    .add(jXDatePicker2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(excelBtn)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {jButton2, jLabel11, jTextField2}, org.jdesktop.layout.GroupLayout.VERTICAL);

        jPanel1Layout.linkSize(new java.awt.Component[] {excelBtn, jButton4, jLabel14, jXDatePicker2}, org.jdesktop.layout.GroupLayout.VERTICAL);

        jPanel1Layout.linkSize(new java.awt.Component[] {jLabel12, jXDatePicker1}, org.jdesktop.layout.GroupLayout.VERTICAL);

        jTabbedPane1.addTab("  Report  ", jPanel1);

        clearBtn.setText("Reset All");
        clearBtn.setMaximumSize(new java.awt.Dimension(75, 30));
        clearBtn.setMinimumSize(new java.awt.Dimension(75, 30));
        clearBtn.setPreferredSize(new java.awt.Dimension(75, 28));
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        saveBtn.setText("Save");
        saveBtn.setMaximumSize(new java.awt.Dimension(40, 30));
        saveBtn.setMinimumSize(new java.awt.Dimension(40, 30));
        saveBtn.setPreferredSize(new java.awt.Dimension(40, 30));
        saveBtn.setRequestFocusEnabled(false);
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logo.png"))); // NOI18N

        msgLabel.setForeground(new java.awt.Color(255, 0, 0));

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .add(37, 37, 37)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTabbedPane1)
                    .add(jPanel7Layout.createSequentialGroup()
                        .add(jLabel1)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jPanel7Layout.createSequentialGroup()
                        .add(clearBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 125, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(27, 27, 27)
                        .add(msgLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(18, 18, 18)
                        .add(saveBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 135, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(30, 30, 30))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(13, 13, 13)
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 501, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(msgLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(clearBtn, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                    .add(saveBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Templates");
        saveBtn.getAccessibleContext().setAccessibleParent(jTabbedPane1);
        msgLabel.getAccessibleContext().setAccessibleName("msgLabel");

        getContentPane().add(jPanel7, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void drawCheckBoxTextDlist(JPanel jPanel, ArrayList tags, Document storages, String unit) {
        UserMainFrameApp.labelList.clear();
        UserMainFrameApp.textFiledList.clear();
        UserMainFrameApp.deltaTextFiledList.clear();
        UserMainFrameApp.dropDownList.clear();

        boolean inFlag = (this.jRadioButton1.getSelectedObjects() != null);

        int xinit = 80;
        int yinit = 20;
        int xoffset = 160;
        int xoffset2 = 120;
        int yoffset = 40;
        int labelWidth = 160;
        int textWidth = 80;
        int dListWidth = 60;
        int height = 30;

        jPanel.removeAll();

        for (int i = 0; i < tags.size(); i++) {
            int x = xinit;
            int y = yinit;

            JRadioButton jRadioButton = new javax.swing.JRadioButton();
            JTextField jTextFieldD = new JTextField();
            JLabel operator = new JLabel();
            JTextField jTextFieldDelta = new JTextField();
            JComboBox jComboBox = new javax.swing.JComboBox();

            jRadioButton.setText(tags.get(i).toString());
            jRadioButton.setBounds(x, y + i * yoffset, labelWidth, height);

            x += xoffset;
            jTextFieldD.setBounds(x, y + i * yoffset, textWidth, height);
            jTextFieldD.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
            jTextFieldD.setEnabled(false);
            jTextFieldD.setText("0");

            x += xoffset2;
            operator.setBounds(x, y + i * yoffset, height, height);
            operator.setIcon(new ImageIcon(UserMainFrame.class.getResource(inFlag ? UserMainFrameApp.PlusIcon : UserMainFrameApp.MinusIcon)));
            x += xoffset2 / 2 + height / 2;

            jTextFieldDelta.setBounds(x, y + i * yoffset, textWidth, height);
            jTextFieldDelta.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));

            x += xoffset2;
            jComboBox.setBounds(x, y + i * yoffset, textWidth, height);
            jComboBox.setModel(new javax.swing.DefaultComboBoxModel(UnitEnum.names()));

            if (null != storages && storages.containsKey(jRadioButton.getText().trim())) {
                jRadioButton.setSelected(true);
                jTextFieldD.setText(((Document) storages.get(jRadioButton.getText().trim())).getDouble(SampleKeyEnum.Quantity.toString()).toString());
                jComboBox.setSelectedItem(((Document) storages.get(jRadioButton.getText().trim())).get(SampleKeyEnum.Unit.toString()));
                jComboBox.setEnabled(false);
            } else {
                jComboBox.setSelectedItem(unit);
                jComboBox.setEnabled(false);
            }

            jPanel.add(jRadioButton);
            jPanel.add(jTextFieldD);
            jPanel.add(operator);
            jPanel.add(jTextFieldDelta);
            jPanel.add(jComboBox);

            UserMainFrameApp.labelList.add(jRadioButton);
            UserMainFrameApp.textFiledList.add(jTextFieldD);
            UserMainFrameApp.deltaTextFiledList.add(jTextFieldDelta);
            UserMainFrameApp.dropDownList.add(jComboBox);
        }
        jPanel.updateUI();

    }

    private void drawCheckBoxTextDlist(JPanel jPanel, ArrayList tags) {
        UserMainFrameApp.radioLabelList.clear();
        UserMainFrameApp.radioTextList.clear();

        int xinit = 1;
        int yinit = 1;
        int xoffset = 110;
        int yoffset = 40;
        int labelWidth = 100;
        int textWidth = 150;
        int height = 30;

        jPanel.removeAll();

        for (int i = 0; i < tags.size(); i++) {
            int x = xinit;
            int y = yinit;

            JRadioButton jRadioButton = new javax.swing.JRadioButton();
            JTextField jTextFieldD = new JTextField();

            jRadioButton.setText(tags.get(i).toString());
            jRadioButton.setBounds(x, y + i * yoffset, labelWidth, height);

            x += xoffset;
            jTextFieldD.setBounds(x, y + i * yoffset, textWidth, height);
            jTextFieldD.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
            jTextFieldD.setEnabled(true);

            jPanel.add(jRadioButton);
            jPanel.add(jTextFieldD);

            UserMainFrameApp.radioLabelList.add(jRadioButton);
            UserMainFrameApp.radioTextList.add(jTextFieldD);
        }
        jPanel.updateUI();
    }

    private void drawLabelRadioText(JPanel jPanel, ArrayList tags,String tid, JScrollPane sPanel, ArrayList<Document> eqDocs,
            int xoffset, int yoffset,int gap, int labelWidth, int align, int textWidth, int height) {
        UserMainFrameApp.labelList.clear();
        UserMainFrameApp.textFiledList.clear();
        UserMainFrameApp.radioLabelList.clear();

        int xinit = 2;
        int yinit = 1;
        int yfinal = yinit;

        jPanel.removeAll();

        for (int i = 0; i < tags.size(); i++) {
            int x = xinit;
            int y = yinit;

            JLabel jLabelD = new JLabel();
            JTextField jTextFieldD = new JTextField();

            jLabelD.setHorizontalAlignment(align);
            jLabelD.setText(tags.get(i).toString());
            jLabelD.setBounds(x, y + i * yoffset, labelWidth, height);
            jPanel.add(jLabelD);
            x += labelWidth+gap;

            jTextFieldD.setBounds(x, y + i * yoffset, textWidth, height);
            jTextFieldD.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED));
            jPanel.add(jTextFieldD);
            x += textWidth;

            UserMainFrameApp.labelList.add(jLabelD);
            UserMainFrameApp.textFiledList.add(jTextFieldD);

            if(tid != null)
            {
                x += xoffset; 
                Document fetchAttach = UserMainFrameApp.getAttachmentDAO().fetchAttach(tid, tags.get(i).toString());
                if(fetchAttach==null)
                {
                    continue;
                }
                JButton attachBtn = new JButton();
                attachBtn.setBounds(x, y + i * yoffset, 80, height);
                attachBtn.setText("Open");
                attachBtn.setName(fetchAttach.getString(AttachmentKeyEnum.DIR.toString())+fetchAttach.getString(AttachmentKeyEnum.FileName.toString()));
                attachBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
                            // TODO Auto-generated method stub
                            boolean opened = UserMainFrameApp.openAttachFile((JButton)e.getSource());
			}
		});
                jPanel.add(attachBtn);
                UserMainFrameApp.attachList.add(attachBtn);
            }
            
            yfinal = y + (i + 1) * yoffset;
        }
        if (null != eqDocs) {
            int x = xinit + labelWidth + xoffset + textWidth + xoffset+80+xoffset*7;
            for (int i = 0; i < eqDocs.size(); i++) {
                int y = yinit;
                if ("process".equals(jPanel.getName())) {
                    JRadioButton jRadioButton = new javax.swing.JRadioButton();
                    jRadioButton.setBounds(x, y + i * yoffset, labelWidth, height);
                    jRadioButton.setText(eqDocs.get(i).getString(EquipmentEnum.EID.toString()));
                    if (eqDocs.get(i).getInteger(EquipmentEnum.Status.toString()) > 0) {
                        jRadioButton.setEnabled(true);
                        jRadioButton.setSelected(false);
                    } else {
                        if (UserMainFrameApp.getUserName().equals(eqDocs.get(i).getString(EquipmentEnum.User.toString()))) {
                            jRadioButton.setEnabled(true);
                            jRadioButton.setSelected(true);
                        } else {
                            jRadioButton.setSelected(false);
                            jRadioButton.setEnabled(false);
                        }
                    }
                    jPanel.add(jRadioButton);
                    UserMainFrameApp.radioLabelList.add(jRadioButton);
                }
            }
        }
        jPanel.setPreferredSize(new Dimension(970, yfinal));
        sPanel.setPreferredSize(new Dimension((int) jPanel.getPreferredSize().getWidth(),
                (int) (jPanel.getPreferredSize().getHeight() + 30)));
        jPanel.setBorder(BorderFactory.createEmptyBorder());
        sPanel.setBorder(BorderFactory.createEmptyBorder());
        sPanel.updateUI();
    }
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        UserMainFrameApp.MainFrameAppClose();
    }//GEN-LAST:event_formWindowClosing

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try {
            this.msgLabel.setText("");
            if (!isAllFilled()) {
                this.msgLabel.setText(MessageEnum.NotAllFilled.getMsg());
                return;
            } else if (!isAllSelected()) {
                this.msgLabel.setText(MessageEnum.NotAllSelected.getMsg());
                return;
            } else {
                if (saveRecord()) {
                    resetBtnActionPerformed();
                    this.msgLabel.setText(MessageEnum.RecordSaved.getMsg());
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(UserMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Save Error");
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // TODO add your handling code here:
        resetBtnActionPerformed();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        //String dir = MainFrameApp.getDirList(this.jTabbedPane1.getSelectedIndex());
        //MainFrameApp.setDir(dir);
        if (this.jTabbedPane1.getSelectedIndex() < 3) {

            UserMainFrameApp.getTemplateDAO().getTemplateListByType(UserMainFrameApp.AdminName, this.jTabbedPane1.getSelectedComponent().getName());
            resetBtnActionPerformed();
            TemplateTypeEnum selectedTab = TemplateTypeEnum.valueOf(this.jTabbedPane1.getSelectedComponent().getName());
            switch (selectedTab) {
                case Receive:
                    this.jComboBoxTags.setModel(new javax.swing.DefaultComboBoxModel(UserMainFrameApp.getTemplateDAO().fetchTIDList()));
                    this.jComboBoxTags.setSelectedIndex(-1);
                    this.jComboBoxTags3.setSelectedIndex(-1);
                    this.jButton6.setVisible(false);
                    this.saveBtn.setEnabled(true);
                    break;
                case Process:
                    this.jComboBoxTags2.setModel(new javax.swing.DefaultComboBoxModel(UserMainFrameApp.getTemplateDAO().fetchTIDList()));
                    this.jComboBoxTags2.setSelectedIndex(-1);
                    this.saveBtn.setEnabled(false);
                    this.jButton5.setVisible(false);
                    this.resetBtnActionPerformed();
                    break;
                case Storage:
                    this.jRadioButton1.setSelected(true);
                    this.jTextField10.setText("");
                    this.saveBtn.setEnabled(false);
                    this.jButton3.setVisible(false);
                    this.resetBtnActionPerformed();
                    break;
                case Report:

                    this.resetBtnActionPerformed();
                    break;
                default:
                    this.msgLabel.setText("");
                    break;
            }
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jComboBoxTags2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTags2ActionPerformed
        // TODO add your handling code here:
        if (this.jComboBoxTags2.getSelectedIndex() < 0) {
            this.jPanel8.removeAll();

        } else {
            UserMainFrameApp.getTemplateDAO().getTemplateListByType(UserMainFrameApp.AdminName, TemplateTypeEnum.values()[this.jTabbedPane1.getSelectedIndex()].toString());
            ArrayList tags = new ArrayList();
            tags = UserMainFrameApp.getTemplateDAO().fetchTagListByTID(this.jComboBoxTags2.getSelectedIndex());
            String tid = UserMainFrameApp.getTemplateDAO().fetchTemplateID(this.jComboBoxTags2.getSelectedIndex());

            ArrayList<Document> eqDocs = new ArrayList();
            eqDocs = UserMainFrameApp.getEquipmentDAO().fetchAllEQDoc();
            if (null != tags && !tags.isEmpty()) {
                drawLabelRadioText(jPanel8, tags,tid, this.jScrollPane2, eqDocs, 20, 40,0 ,100, javax.swing.SwingConstants.LEFT, 280, 30);
            }
        }
    }//GEN-LAST:event_jComboBoxTags2ActionPerformed

    private void jComboBoxTags2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTags2ItemStateChanged
        // TODO add your handling code here:
        if (this.jComboBoxTags2.getSelectedIndex() < 0) {
            this.jPanel8.removeAll();
            this.jPanel8.updateUI();
        }
    }//GEN-LAST:event_jComboBoxTags2ItemStateChanged

    private void searchBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtn2ActionPerformed
        this.msgLabel.setText("");
        String materialID = this.jTextField3.getText().replaceAll(" ", "");
        if (materialID.length() == 0) {
            resetBtnActionPerformed();
            this.msgLabel.setText(MessageEnum.MaterialNotFound.getMsg());
            return;
        }
        if (!MaterialDAOHelper.fetchMaterialByMID(materialID)) {
            this.msgLabel.setText(MessageEnum.MaterialNotFound.getMsg());
            this.jTextField5.setText("");
            this.jTextField5.setEnabled(false);
            this.jComboBoxTags2.setSelectedIndex(-1);
            this.jComboBoxTags2.setEnabled(false);
            this.jButton5.setVisible(false);
            this.saveBtn.setEnabled(false);
        } else {
            this.jTextField5.setEnabled(true);
            this.jComboBoxTags2.setEnabled(true);
            this.saveBtn.setEnabled(true);
            this.jButton5.setVisible(true);
        }
    }//GEN-LAST:event_searchBtn2ActionPerformed

    private void jComboBoxTags3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTags3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTags3ActionPerformed

    private void jComboBoxTags1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTags1ActionPerformed
        // TODO add your handling code here:
        this.msgLabel.setText("");

        UserMainFrameApp.getStorageTemplateDAO().getTemplateListByType(UserMainFrameApp.AdminName, TemplateTypeEnum.Storage.toString());
        ArrayList tags = UserMainFrameApp.getStorageTemplateDAO().fetchTagListByTID(0);
        if (null != tags) {
            drawCheckBoxTextDlist(jPanel5, tags);
        }
    }//GEN-LAST:event_jComboBoxTags1ActionPerformed

    private void jComboBoxTags2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxTags2FocusGained
        // TODO add your handling code here:
        UserMainFrameApp.getTemplateDAO().getTemplateListByType(UserMainFrameApp.AdminName, TemplateTypeEnum.values()[this.jTabbedPane1.getSelectedIndex()].toString());
    }//GEN-LAST:event_jComboBoxTags2FocusGained

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (this.jXDatePicker1.getDate() == null || this.jXDatePicker2.getDate() == null
                || this.jXDatePicker2.getDate().before(this.jXDatePicker1.getDate())) {
            JOptionPane.showMessageDialog(this, "Please select a valid date range");
            return;
        }

        if (!SampleDAOHelper.fetchSampleBySID(this.jTextField2.getText().trim())) {
            JOptionPane.showMessageDialog(this, "SID Not Found");
            return;
        }
        updateSingleRow();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (this.jTextField2.getText().trim() == null || this.jTextField2.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter a SID");
            return;
        }

        if (!SampleDAOHelper.fetchSampleBySID(this.jTextField2.getText().replaceAll(" ", ""))) {
            JOptionPane.showMessageDialog(this, "SID Not Fount");
            return;
        }
        updateSingleRow();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        refreshOperator(this.jPanel9, true);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        this.msgLabel.setText("");
        String sampleID = this.jTextField10.getText().replaceAll(" ", "");
        if (sampleID.length() == 0) {
            resetBtnActionPerformed();
            return;
        }

        if (!SampleDAOHelper.fetchSampleBySID(sampleID)) {
            if (this.jPanel9.getComponentCount() > 0) {
                this.jPanel9.removeAll();
                this.jPanel9.updateUI();
            }
            this.msgLabel.setText(MessageEnum.SampleNotFound.getMsg());
            this.saveBtn.setEnabled(false);
            this.jButton3.setVisible(false);

        } else {
            this.jButton3.setVisible(true);
            this.saveBtn.setEnabled(true);
            UserMainFrameApp.getTemplateDAO().getTemplateListByType(UserMainFrameApp.AdminName, TemplateTypeEnum.values()[this.jTabbedPane1.getSelectedIndex()].toString());
            ArrayList tags = UserMainFrameApp.getTemplateDAO().fetchTagListByTID(0);
            String unit = SampleDAOHelper.getUnitByMid(SampleDAOHelper.getMidBySample());
            if (null != tags) {
                drawCheckBoxTextDlist(jPanel9, tags, SampleDAOHelper.getStorages(), unit);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        refreshOperator(this.jPanel9, false);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void excelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelBtnActionPerformed
        // TODO add your handling code here:
        ExportToExcelAction eeAction = new ExportToExcelAction(jTable1, "Report");
    }//GEN-LAST:event_excelBtnActionPerformed

    private void jComboBoxTagsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTagsActionPerformed
        // TODO add your handling code here:

        if (this.jComboBoxTags.getSelectedIndex() < 0) {
            this.jPanel6.removeAll();
            this.jButton6.setVisible(false);

        } else {
            UserMainFrameApp.getTemplateDAO().getTemplateListByType(UserMainFrameApp.AdminName, TemplateTypeEnum.values()[this.jTabbedPane1.getSelectedIndex()].toString());
            ArrayList tags = new ArrayList();
            tags = UserMainFrameApp.getTemplateDAO().fetchTagListByTID(this.jComboBoxTags.getSelectedIndex());
            if (null != tags && !tags.isEmpty()) {
                drawLabelRadioText(jPanel6, tags,null, this.jScrollPane1, null, 165, 40,30,130, javax.swing.SwingConstants.RIGHT, 230, 30);
            }
            this.jButton6.setVisible(true);
        }
    }//GEN-LAST:event_jComboBoxTagsActionPerformed

    private void jComboBoxTagsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxTagsFocusGained
        // TODO add your handling code here:
        UserMainFrameApp.getTemplateDAO().getTemplateListByType(UserMainFrameApp.AdminName, TemplateTypeEnum.values()[this.jTabbedPane1.getSelectedIndex()].toString());
    }//GEN-LAST:event_jComboBoxTagsFocusGained

    private void jComboBoxTagsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTagsItemStateChanged
        // TODO add your handling code here:
        if (this.jComboBoxTags.getSelectedIndex() < 0) {
            this.jPanel6.removeAll();
            this.jPanel6.updateUI();
        }
    }//GEN-LAST:event_jComboBoxTagsItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    /*private void clearCountLabel() {
        JLabel selectedCountLabel = getSelectedCountLabel();
        if (null != selectedCountLabel) {
            selectedCountLabel.setText(Integer.toString(0));
        } else {
            System.out.println("no count label selected");
        }
    }*/
    private void resetBtnActionPerformed() {
        ArrayList<JTextField> selectedJTextField = getAllJTextFieldOnTab();
        for (int i = 0; i < selectedJTextField.size(); i++) {
            selectedJTextField.get(i).setText("");
        }
        ArrayList<JComboBox> allDListOnTab = getAllDListOnTab();
        for (int i = 0; i < allDListOnTab.size(); i++) {
            allDListOnTab.get(i).setSelectedIndex(-1);
        }
        resetOthersOnTab();
        this.msgLabel.setText("");
        UserMainFrameApp.clearArrayList();

    }

    private void addBtnActionPerformed() {
        JSONObject o = new JSONObject();

        ArrayList selectedJTextField = getAllJTextFieldOnTab();

        int selectedIndex = this.jTabbedPane1.getSelectedIndex();

        for (int i = 0; i < selectedJTextField.size(); i++) {
            try {
                System.out.println(((JTextField) selectedJTextField.get(i)).getName());
                System.out.println(((JTextField) selectedJTextField.get(i)).getText());
                o.put(((JTextField) selectedJTextField.get(i)).getName(), ((JTextField) selectedJTextField.get(i)).getText());
                ((JTextField) selectedJTextField.get(i)).setText("");

            } catch (JSONException ex) {
                Logger.getLogger(UserMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (isAllFilled(o)) {
            int count = UserMainFrameApp.addRecord(o, selectedIndex);
            /*JLabel selectedCountLabel = getSelectedCountLabel();
            if (null != selectedCountLabel) {
                selectedCountLabel.setText(Integer.toString(count));
            } else {
                System.out.println("no count label selected");
            }*/
        }
    }

    private ArrayList<JTextField> getAllJTextFieldOnTab() {
        ArrayList<JTextField> list = new ArrayList();
        int selectedIndex = this.jTabbedPane1.getSelectedIndex();
        Component[] components = ((JPanel) this.jTabbedPane1.getComponents()[selectedIndex]).getComponents();

        for (int i = 0; i < components.length; i++) {
            if (components[i].getClass().isInstance(new JTextField())) {
                list.add((JTextField) components[i]);
            }
        }
        return list;
    }

    private void refreshOperator(JPanel panel, boolean inFlag) {
        Component[] components = panel.getComponents();

        for (int i = 0; i < components.length; i++) {
            if (components[i].getClass().isInstance(new JLabel())) {
                ((JLabel) components[i]).setIcon(new ImageIcon(UserMainFrame.class.getResource(inFlag ? UserMainFrameApp.PlusIcon : UserMainFrameApp.MinusIcon)));
                ((JLabel) components[i]).repaint();
            }
        }
    }

    private void resetOthersOnTab() {
        TemplateTypeEnum selectedTab = TemplateTypeEnum.valueOf(this.jTabbedPane1.getSelectedComponent().getName());
        switch (selectedTab) {
            case Receive:
                this.jTextArea1.setText("");
                this.jButton6.setVisible(false);
                break;
            case Process:
                this.jTextField5.setEnabled(false);
                this.jTextArea2.setText("");
                this.jComboBoxTags2.setEnabled(false);
                this.jButton5.setVisible(false);
                this.saveBtn.setEnabled(false);
                break;
            case Storage:
                this.jTextArea3.setText("");
                this.jButton3.setVisible(false);
                this.jPanel9.removeAll();
                this.jPanel9.updateUI();
                this.saveBtn.setEnabled(false);
                break;
            case Report:
                this.saveBtn.setEnabled(false);
                break;
            default:
                break;
        }

    }

    private ArrayList<JComboBox> getAllDListOnTab() {
        ArrayList<JComboBox> list = new ArrayList();
        int selectedIndex = this.jTabbedPane1.getSelectedIndex();
        Component[] components = ((JPanel) this.jTabbedPane1.getComponents()[selectedIndex]).getComponents();

        for (int i = 0; i < components.length; i++) {
            if (components[i].getClass().isInstance(new JComboBox())) {
                list.add((JComboBox) components[i]);
            }
        }
        return list;
    }

    private boolean isAllFilled(JSONObject o) {
        Iterator keys = o.keys();
        while (keys.hasNext()) {
            try {
                String next = keys.next().toString();
                System.out.println(o.get(next));
                if (o.get(next) == null || "".equals(o.get(next).toString())) {
                    System.out.println(o.get(next));
                    return false;
                }
            } catch (JSONException ex) {
                Logger.getLogger(UserMainFrame.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }

    private boolean isAllFilled() {
        ArrayList selectedJTextField = getAllJTextFieldOnTab();

        if(this.jTabbedPane1.getSelectedComponent().getName()=="Process")
            return true;
        
        for (int i = 0; i < selectedJTextField.size(); i++) {
            if (((JTextField) selectedJTextField.get(i)).getText() == null
                    || "".equals(((JTextField) selectedJTextField.get(i)).getText())) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllSelected() {
        ArrayList<JComboBox> allDListOnTab = getAllDListOnTab();
        for (int i = 0; i < allDListOnTab.size(); i++) {
            if (((JComboBox) allDListOnTab.get(i)).getSelectedIndex() < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllEmpty() {
        ArrayList selectedJTextField = getAllJTextFieldOnTab();

        for (int i = 0; i < selectedJTextField.size(); i++) {
            if (((JTextField) selectedJTextField.get(i)).getText() != null
                    || !"".equals(((JTextField) selectedJTextField.get(i)).getText())) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels = javax.swing.UIManager.getInstalledLookAndFeels();
            for (int idx = 0; idx < installedLookAndFeels.length; idx++) {
                if ("Nimbus".equals(installedLookAndFeels[idx].getName())) {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton excelBtn;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBoxTags;
    private javax.swing.JComboBox<String> jComboBoxTags1;
    private javax.swing.JComboBox<String> jComboBoxTags2;
    private javax.swing.JComboBox<String> jComboBoxTags3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private javax.swing.JLabel msgLabel;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton searchBtn2;
    // End of variables declaration//GEN-END:variables

    private boolean saveRecord() {
        Document doc = null;
        boolean flag = true;
        this.msgLabel.setText("");
        TemplateTypeEnum selectedTab = TemplateTypeEnum.valueOf(this.jTabbedPane1.getSelectedComponent().getName());
        switch (selectedTab) {
            case Receive:
                doc = prepareReceiveDoc();
                MaterialDAO.getInstance().addOrUpdate(doc);
                break;
            case Process:
                if("".equals(this.jTextField5.getText().replaceAll(" ", "")))
                {
                     blockEQByUser();
                }
                else if (null != ((SampleDAO) SampleDAO.getInstance()).isSampleFound(this.jTextField5.getText().replaceAll(" ", ""))) {
                    this.msgLabel.setText(MessageEnum.SampleDuplFound.getMsg());
                    flag = false;
                } else {
                    String mid = this.jTextField3.getText().replaceAll(" ", "");
                    doc = prepareProcessDoc();
                    ProcessDAO.getInstance().addOrUpdate(doc);
                    doc.remove(ProcessKeyEnum.Steps.toString());
                    doc.append(SampleKeyEnum.MID.toString(), mid);
                    SampleDAO.getInstance().addOrUpdate(doc);
                    updateMaterialDoc(doc);
                    blockEQByUser();
                }
                break;
            case Storage:
                doc = prepareStorageDoc();
                if (null == doc) {
                    return false;
                }
                SampleDAO.getInstance().addOrUpdate(doc);
                break;
            default:
                this.msgLabel.setText("");
                break;
        }
        return flag;
    }

    private boolean blockEQByUser() {
        for (int i = 0; i < UserMainFrameApp.radioLabelList.size(); i++) {
            JRadioButton jb = (JRadioButton) UserMainFrameApp.radioLabelList.get(i);
            if (!jb.isEnabled()) {
                continue;
            } else {
                int Status = jb.isSelected() ? 0 : 1;
                UserMainFrameApp.getEquipmentDAO().updateEQStatusByUser(UserMainFrameApp.getUserName(), Status, jb.getText());
            }
        }
        return true;
    }

    private Document prepareReceiveDoc() {
        String mid = this.jTextField1.getText().replaceAll(" ", "");
        Double quantity = 0.0;
        try {
            quantity = Double.parseDouble(this.jTextField4.getText().replaceAll(" ", ""));
        } catch (NumberFormatException numberFormatException) {
            this.msgLabel.setText(MessageEnum.NumberRequired.getMsg());
            this.jTextField4.setText("");
            return null;
        }
        String remarks = this.jTextArea1.getText();
        String unit = this.jComboBoxTags1.getSelectedItem().toString();
        String tid = this.jComboBoxTags.getSelectedItem().toString();
        String type = this.jComboBoxTags3.getSelectedItem().toString();

        Document objR = new Document();
        for (int i = 0; i < UserMainFrameApp.labelList.size() && i < UserMainFrameApp.textFiledList.size(); i++) {
            objR.append(((JLabel) UserMainFrameApp.labelList.get(i)).getText().trim(),
                    ((JTextField) UserMainFrameApp.textFiledList.get(i)).getText().trim());
        }

        Document objS = new Document();
        for (int i = 0; i < UserMainFrameApp.radioLabelList.size()
                && i < UserMainFrameApp.radioTextList.size(); i++) {

            if (null != ((JRadioButton) UserMainFrameApp.radioLabelList.get(i)).getSelectedObjects()) {
                Document subObj = new Document();
                subObj.append(SampleKeyEnum.Quantity.toString(), caculateQuantity(((JTextField) UserMainFrameApp.radioTextList.get(i)).getText().trim(), "0", true));
                subObj.append(SampleKeyEnum.Unit.toString(), unit);
                objS.append(((JRadioButton) UserMainFrameApp.radioLabelList.get(i)).getText().trim(), subObj);
            }
        }
        MaterialDAOHelper.prepareReceivedMaterial(mid, quantity, tid, unit, type, remarks, objR, objS);

        return MaterialDAOHelper.getMaterial();
    }

    private void updateMaterialDoc(Document doc) {
        ((MaterialDAO) MaterialDAO.getInstance()).updateSampleList(doc.getString(SampleKeyEnum.MID.toString()), doc.getString(SampleKeyEnum.SID.toString()));
        return;
    }

    private Document prepareProcessDoc() {
        String sid = this.jTextField5.getText().replaceAll(" ", "");

        String remarks = this.jTextArea2.getText();
        String tid = this.jComboBoxTags2.getSelectedItem().toString();

        Document objP = new Document();
        for (int i = 0; i < UserMainFrameApp.labelList.size() && i < UserMainFrameApp.textFiledList.size(); i++) {
            objP.append(((JLabel) UserMainFrameApp.labelList.get(i)).getText().trim(),
                    ((JTextField) UserMainFrameApp.textFiledList.get(i)).getText().trim());
        }
        ProcessDAOHelper.prepareReceivedProcess(sid, tid, remarks, objP);

        return ProcessDAOHelper.getProcessDoc();

    }

    private Document prepareStorageDoc() {
        String sid = this.jTextField10.getText().replaceAll(" ", "");
        String remarks = this.jTextArea3.getText();
        boolean inFlag = (this.jRadioButton1.getSelectedObjects() != null) ? true : false;

        Document objS = new Document();
        //Document objdeltaS = new Document();
        Double deltaQuantity = 0.0;
        for (int i = 0; i < UserMainFrameApp.labelList.size()
                && i < UserMainFrameApp.textFiledList.size()
                && i < UserMainFrameApp.deltaTextFiledList.size()
                && i < UserMainFrameApp.dropDownList.size(); i++) {

            if (null != ((JRadioButton) UserMainFrameApp.labelList.get(i)).getSelectedObjects()) {
                Document subObj = new Document();
                double updatedQ = caculateQuantity(((JTextField) UserMainFrameApp.textFiledList.get(i)).getText().trim(), ((JTextField) UserMainFrameApp.deltaTextFiledList.get(i)).getText().trim(), inFlag);
                if (updatedQ < 0) {
                    this.msgLabel.setText(MessageEnum.QuantityMisMatch.getMsg());
                    return null;
                }
                subObj.append(SampleKeyEnum.Quantity.toString(), updatedQ);
                subObj.append(SampleKeyEnum.Unit.toString(), ((JComboBox) UserMainFrameApp.dropDownList.get(i)).getSelectedItem().toString());
                objS.append(((JRadioButton) UserMainFrameApp.labelList.get(i)).getText().trim(), subObj);

                double delta = getDeltaQuatity(((JTextField) UserMainFrameApp.deltaTextFiledList.get(i)).getText().trim(), inFlag);
                if (delta < 0) {
                    SampleDAOHelper.setSample(null);
                    break;
                } else {
                    deltaQuantity += delta;
                }
            }
        }

        MessageEnum prepareStorage = SampleDAOHelper.prepareStorage(sid, remarks, objS, deltaQuantity);
        this.msgLabel.setText(prepareStorage.getMsg());
        return SampleDAOHelper.getSample();
    }

    private double getDeltaQuatity(String delta, boolean inFlag) {
        double parseDouble = 0.0;
        if ("".equals(delta)) {
            return parseDouble;
        }
        try {
            parseDouble = inFlag ? Double.parseDouble(delta) : 0.0;
        } catch (NumberFormatException numberFormatException) {
            parseDouble = -1.0;
        } finally {
            return parseDouble;
        }
    }

    private double caculateQuantity(String current, String delta, boolean inflag) {
        return UserMainFrameApp.getDoubleFromString(current) + (((inflag) ? 1 : -1) * UserMainFrameApp.getDoubleFromString(delta));
    }

    /**
     * ****************
     */
    public void updateSingleRow() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        jTable1.setGridColor(Color.BLACK);
        ArrayList<String> resultList = new ArrayList<String>();
        ArrayList<String> labelList = new ArrayList<String>();
        ReadData(resultList, labelList);
        Object[] rArray = resultList.toArray();
        model.insertRow(0, rArray);

        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnAdjuster tca = new TableColumnAdjuster(jTable1);
        tca.adjustColumns();

        this.jTable1.updateUI();
        if (this.jTable1.getModel().getRowCount() > 0) {
            this.excelBtn.setEnabled(true);

        }
    }

    public void ReadData(ArrayList<String> resultList, ArrayList<String> labelList) {

        getSampleData(resultList, labelList);
        if (resultList.isEmpty() || labelList.isEmpty()) {
            return;
        }

        getProcessData(resultList, labelList);

        if (resultList.size() != labelList.size()) {
            JOptionPane.showMessageDialog(this, "Records Display could be error");
        }

    }

    private void ReadDataFromDate(ArrayList<ArrayList<String>> resultList, ArrayList<ArrayList<String>> labelList) {
        // TODO Auto-generated method stub
        Hashtable<String, ArrayList<String>> resultTable = new Hashtable<String, ArrayList<String>>();
        Hashtable<String, ArrayList<String>> labelTable = new Hashtable<String, ArrayList<String>>();
        //getSampleData(resultTable, labelTable);
        if (resultTable.size() == 0 || labelTable.size() == 0) {
            return;
        }
        //getProcessData(resultTable, labelTable);
        Set<String> set = resultTable.keySet();
        for (String s : set) {
            resultList.add(resultTable.get(s));
            labelList.add(labelTable.get(s));
        }
    }

    public void getSampleData(ArrayList<String> resultList, ArrayList<String> labelList) {

        labelList.add(SampleKeyEnum.SID.toString());
        resultList.add(SampleDAOHelper.getSample().getString(SampleKeyEnum.SID.toString()));
        labelList.add(SampleKeyEnum.MID.toString());
        resultList.add(SampleDAOHelper.getSample().getString(SampleKeyEnum.MID.toString()));
        labelList.add(SampleKeyEnum.User.toString());
        resultList.add(SampleDAOHelper.getSample().getString(SampleKeyEnum.User.toString()));
        labelList.add(SampleKeyEnum.DateTime.toString());
        resultList.add(SampleDAOHelper.getSample().getDate(SampleKeyEnum.DateTime.toString()).toString());
        //labelList.add(SampleKeyEnum.Quantity.toString());
        //resultList.add(SampleDAOHelper.getSample().getDouble(SampleKeyEnum.Quantity.toString()).toString());
        //labelList.add(SampleKeyEnum.Unit.toString());
        //resultList.add(SampleDAOHelper.getSample().getString(SampleKeyEnum.Unit.toString()));
        labelList.add(SampleKeyEnum.Comments.toString());
        resultList.add(SampleDAOHelper.getSample().getString(SampleKeyEnum.Comments.toString()));

        //labelList.add(SampleKeyEnum.Receive.toString());
        /*if (SampleDAOHelper.getSample().containsKey(TemplateTypeEnum.Receive.toString())) {
            Document doc = (Document) SampleDAOHelper.getSample().get(TemplateTypeEnum.Receive.toString());
            String strRecv = printTagsToString(doc);
            resultList.add(strRecv);
        } else {
            resultList.add(" ");
        }*/
        labelList.add(SampleKeyEnum.Storage.toString());
        labelList.add(SampleKeyEnum.Storage.toString() + "_" + SampleKeyEnum.User.toString());
        labelList.add(SampleKeyEnum.Storage.toString() + "_" + SampleKeyEnum.User.toString() + "_" + SampleKeyEnum.Comments.toString());

        if (SampleDAOHelper.getSample().containsKey(TemplateTypeEnum.Storage.toString())) {
            Document doc = (Document) SampleDAOHelper.getSample().get(TemplateTypeEnum.Storage.toString());
            String strStorg = printStorageToString(doc);
            resultList.add(strStorg);
            resultList.add(doc.getString(SampleKeyEnum.User.toString()));
            resultList.add(doc.getString(SampleKeyEnum.Comments.toString()));
        } else {
            resultList.add(" ");
            resultList.add(" ");
            resultList.add(" ");
        }
        return;
    }

    private String printTagsToString(Document doc) {
        String tagsStr = "";
        if (null == doc || doc.isEmpty()) {

        } else {
            Iterator<String> it = doc.keySet().iterator();
            while (it.hasNext()) {
                String tag = it.next().toString();
                tagsStr += tag;
                tagsStr += " : ";
                tagsStr += doc.getString(tag);
                tagsStr += it.hasNext() ? " | " : "";
            }
        }

        return tagsStr;
    }

    private String printStorageToString(Document doc) {
        String storageStr = "";
        if (null == doc || doc.isEmpty()) {

        } else {
            Iterator<String> it = doc.keySet().iterator();
            while (it.hasNext()) {
                String place = it.next().toString();
                if (place.equals(SampleKeyEnum.Comments.toString()) || place.equals(SampleKeyEnum.User.toString())) {
                    continue;
                }
                Document unit = (Document) doc.get(place);
                storageStr += place;
                storageStr += " : ";
                storageStr += unit.getDouble(SampleKeyEnum.Quantity.toString());
                storageStr += " ";
                storageStr += unit.getString(SampleKeyEnum.Unit.toString());
                storageStr += " | ";
            }

        }

        return storageStr;
    }

    public void getProcessData(ArrayList<String> resultList, ArrayList<String> labelList) {

        if (!ProcessDAOHelper.fetchProcessBySID(this.jTextField2.getText().trim())) {
            return;
        }

        labelList.add(SampleKeyEnum.Process.toString() + "_" + ProcessKeyEnum.TID.toString());
        resultList.add(ProcessDAOHelper.getProcessDoc().getString(ProcessKeyEnum.TID.toString()));
        labelList.add(SampleKeyEnum.Process.toString() + " " + ProcessKeyEnum.User.toString());
        resultList.add(ProcessDAOHelper.getProcessDoc().getString(ProcessKeyEnum.User.toString()));
        labelList.add(SampleKeyEnum.Process.toString() + "_" + ProcessKeyEnum.DateTime.toString());
        resultList.add(ProcessDAOHelper.getProcessDoc().getDate(ProcessKeyEnum.DateTime.toString()).toString());
        labelList.add(SampleKeyEnum.Process.toString() + "_" + ProcessKeyEnum.Comments.toString());
        resultList.add(ProcessDAOHelper.getProcessDoc().getString(ProcessKeyEnum.Comments.toString()));

        labelList.add(SampleKeyEnum.Process.toString() + "_" + ProcessKeyEnum.Steps.toString());
        if (ProcessDAOHelper.getProcessDoc().containsKey(ProcessKeyEnum.Steps.toString())) {
            Document doc = (Document) ProcessDAOHelper.getProcessDoc().get(ProcessKeyEnum.Steps.toString());
            String stepsRecv = printTagsToString(doc);
            resultList.add(stepsRecv);
        } else {
            resultList.add(" ");
        }
    }

    private boolean exportToExcel(JTable t, String filename) {
        ExportToExcelAction etea = new ExportToExcelAction(t, filename);
        return true;
    }

}
