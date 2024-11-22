/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.reports;

import controllers.AppointmentStatusController;
import controllers.EmployeeController;
import controllers.StatusController;
import controllers.StockController;
import controllers.SupplierController;
import includes.LoggerConfig;
import includes.MySqlConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import models.LoginModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import views.dashboard.Dashboard;

/**
 *
 * @author Dumindu
 */
public class GRNReportPanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();

    private static HashMap<String, String> SupplierHashMap = new HashMap<>();
    private static HashMap<String, String> EmployeeHashMap = new HashMap<>();

    Dashboard dashboard = null;

    public GRNReportPanel(Dashboard dashboard) {
        initComponents();
        GRNTableRender();
        LoadGrn();
        loadSuppliersToComboBox();
        loadEmployeesToComboBox();

        this.dashboard = dashboard;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        GRNViewTable = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        viewReportb = new javax.swing.JButton();
        printReportb = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        PriceFrom = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        PriceTo = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        QtyFrom = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        QtyTo = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        SupplierChooser = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        EmployeeChooser = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jFromDateField = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jToDateField = new javax.swing.JFormattedTextField();

        setMinimumSize(new java.awt.Dimension(1100, 610));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 11, 610));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 42, 1065, -1));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/icons8-reports-30.png"))); // NOI18N
        jLabel1.setText("  GRN Report");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 6, 968, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/back-35.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 6, -1, 26));

        GRNViewTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        GRNViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Grn ID", "Date", "Price Per Unit", "Quantity", "Paid Amount", "Supplier ID", "Employee ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        GRNViewTable.setRowHeight(30);
        GRNViewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GRNViewTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GRNViewTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(GRNViewTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 1050, 360));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel8.setText("Number Of Products :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 0));
        jLabel5.setText("00");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 560, -1, -1));

        viewReportb.setBackground(new java.awt.Color(51, 51, 51));
        viewReportb.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        viewReportb.setForeground(new java.awt.Color(255, 255, 255));
        viewReportb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/analyze-30.png"))); // NOI18N
        viewReportb.setText(" Save Report");
        viewReportb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewReportb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReportbActionPerformed(evt);
            }
        });
        jPanel1.add(viewReportb, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 550, -1, -1));

        printReportb.setBackground(new java.awt.Color(0, 102, 0));
        printReportb.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        printReportb.setForeground(new java.awt.Color(255, 255, 255));
        printReportb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/print-30.png"))); // NOI18N
        printReportb.setText(" Print Report");
        printReportb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printReportb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReportbActionPerformed(evt);
            }
        });
        jPanel1.add(printReportb, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 550, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel3.setText("Price From");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, -1, -1));

        PriceFrom.setForeground(new java.awt.Color(0, 0, 204));
        PriceFrom.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        PriceFrom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PriceFromKeyReleased(evt);
            }
        });
        jPanel1.add(PriceFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 130, 31));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel4.setText("To");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, -1, -1));

        PriceTo.setForeground(new java.awt.Color(0, 0, 204));
        PriceTo.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        PriceTo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PriceToKeyReleased(evt);
            }
        });
        jPanel1.add(PriceTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 120, 31));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel6.setText("Quantity");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, -1, -1));

        QtyFrom.setForeground(new java.awt.Color(0, 0, 204));
        QtyFrom.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        QtyFrom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                QtyFromKeyReleased(evt);
            }
        });
        jPanel1.add(QtyFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, 110, 31));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel2.setText("To");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 60, -1, -1));

        QtyTo.setForeground(new java.awt.Color(0, 0, 204));
        QtyTo.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        QtyTo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                QtyToKeyReleased(evt);
            }
        });
        jPanel1.add(QtyTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 80, 100, 31));

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel9.setText("Supplier");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 130, -1, -1));

        SupplierChooser.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        SupplierChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        SupplierChooser.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SupplierChooserItemStateChanged(evt);
            }
        });
        SupplierChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupplierChooserActionPerformed(evt);
            }
        });
        jPanel1.add(SupplierChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 130, 110, 30));

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel10.setText("Employee");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        EmployeeChooser.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        EmployeeChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        EmployeeChooser.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EmployeeChooserItemStateChanged(evt);
            }
        });
        EmployeeChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmployeeChooserActionPerformed(evt);
            }
        });
        jPanel1.add(EmployeeChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 110, 30));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel7.setText("Appointments From :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jFromDateField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jFromDateField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jFromDateFieldMouseExited(evt);
            }
        });
        jFromDateField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFromDateFieldPropertyChange(evt);
            }
        });
        jPanel1.add(jFromDateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 150, -1));

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel12.setText("To :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, -1, -1));

        jToDateField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jToDateField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToDateFieldMouseExited(evt);
            }
        });
        jToDateField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jToDateFieldPropertyChange(evt);
            }
        });
        jPanel1.add(jToDateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dashboard.jReportPanel.remove(this);
        SwingUtilities.updateComponentTreeUI(dashboard.jReportPanel);

        dashboard.reportsJPanel = new ReportsJPanel(dashboard);
        dashboard.jReportPanel.add(dashboard.reportsJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dashboard.jReportPanel);
    }//GEN-LAST:event_jButton1ActionPerformed

    public void GRNTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = GRNViewTable.getTableHeader();

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Font headerFont = new Font("Verdana", Font.BOLD, 14);
                label.setBorder(BorderFactory.createEmptyBorder()); // Remove header borders
                label.setFont(headerFont);
                label.setBackground(new Color(5, 15, 76)); // Optional: Set header background color
                label.setForeground(Color.WHITE); // Optional: Set header text color
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return label;
            }
        });

        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));

        for (int i = 0; i < 7; i++) {
            GRNViewTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void loadSuppliersToComboBox() {
        try {
            ResultSet resultSet = new SupplierController().show();

            Vector<String> vector = new Vector<>();
            vector.add("All");

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("first_name");

                vector.add(id);
                SupplierHashMap.put(id, name);
            }

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(vector);
            SupplierChooser.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading supplier IDs: " + e.getMessage());
        }
    }

    private void loadEmployeesToComboBox() {
        try {
            ResultSet resultSet = new EmployeeController().show();

            Vector<String> vector = new Vector<>();
            vector.add("All");

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("first_name");

                vector.add(id);
                EmployeeHashMap.put(id, name);
            }

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(vector);
            EmployeeChooser.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading supplier IDs: " + e.getMessage());
        }
    }

    public void loadtoGrnTable() {
        try {

            String query = "SELECT * FROM grn "
                    + "INNER JOIN grn_items ON grn.id=grn_items.grn_id  ";

            ResultSet resultSet = MySqlConnection.executeSearch(query);

            DefaultTableModel dtm = (DefaultTableModel) GRNViewTable.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                vector.add(dateFormat.format(dateFormat.parse(resultSet.getString("date"))));
                vector.add(resultSet.getString("grn_items.price"));
                vector.add(resultSet.getString("grn_items.qty"));
                vector.add(resultSet.getString("paid_amount"));
                vector.add(resultSet.getString("supplier_id"));
                vector.add(resultSet.getString("employee_id"));

                dtm.addRow(vector);
            }

            jLabel5.setText(String.valueOf(row));
            GRNViewTable.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Error while loadAppointments() : " + e.getMessage());
        }
    }

    private void LoadGrn() {
        try {
            loadtoGrnTable();

        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Error during LoadGrn initialization: " + e.getMessage());
        }
    }

    public JasperPrint makeReport() {

        String dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa").format(new Date());

        String headerImg;
        try {
            InputStream s = this.getClass().getResourceAsStream("/resources/reports/ProductReport.jasper");
            String img = new File(this.getClass().getResource("/resources/reports/dazzle_auto_tp.png").getFile()).getAbsolutePath();

            headerImg = img.replace("\\", "/");

            HashMap<String, Object> params = new HashMap<>();
            params.put("img", headerImg);
            //params.put("status", String.valueOf(Brand_chooser.getSelectedItem()));
            params.put("count", jLabel5.getText());
            params.put("employee", LoginModel.getFirstName() + " " + LoginModel.getLastName());
            params.put("reportDate", dateTime);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(GRNViewTable.getModel());

            JasperPrint report = JasperFillManager.fillReport(s, params, dataSource);

            return report;

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while makeReport() : " + e.getMessage());
        }
        return null;
    }

    private void GRNViewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GRNViewTableMouseClicked

    }//GEN-LAST:event_GRNViewTableMouseClicked

    private void GRNViewTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GRNViewTableMousePressed


    }//GEN-LAST:event_GRNViewTableMousePressed

    private void viewReportbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportbActionPerformed

        try {
            JasperPrint report = makeReport();
            JasperViewer.viewReport(report, false);

            logger.info("Product Report Viewed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while viewReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_viewReportbActionPerformed

    private void printReportbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReportbActionPerformed
        try {
            JasperPrint report = makeReport();
            JasperPrintManager.printReport(report, false);

            logger.info("Product Report Printed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while printReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_printReportbActionPerformed

    private void PriceFromKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PriceFromKeyReleased
        // TODO add your handling code here:
        SortTable();
    }//GEN-LAST:event_PriceFromKeyReleased

    private void PriceToKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PriceToKeyReleased
        // TODO add your handling code here:
        SortTable();
    }//GEN-LAST:event_PriceToKeyReleased

    private void QtyFromKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QtyFromKeyReleased
        // TODO add your handling code here:
        SortTable();
    }//GEN-LAST:event_QtyFromKeyReleased

    private void QtyToKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QtyToKeyReleased
        // TODO add your handling code here:
        SortTable();
    }//GEN-LAST:event_QtyToKeyReleased

    private void SupplierChooserItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SupplierChooserItemStateChanged

    }//GEN-LAST:event_SupplierChooserItemStateChanged

    private void SortTable() {
        try {
            String query = "SELECT * FROM grn INNER JOIN grn_items ON grn.id = grn_items.grn_id";
            StringBuilder whereClause = new StringBuilder();

            // Supplier filter
            String supplier = String.valueOf(SupplierChooser.getSelectedItem());
            if (!supplier.equals("All")) {
                whereClause.append("grn.supplier_id = '").append(supplier).append("' ");
            }

            // Employee filter
            String employee = String.valueOf(EmployeeChooser.getSelectedItem());
            if (!employee.equals("All")) {
                if (whereClause.length() > 0) {
                    whereClause.append("AND ");
                }
                whereClause.append("grn.employee_id = '").append(employee).append("' ");
            }

            // Price range filter
            double minPrice = PriceFrom.getText().isEmpty() ? 0 : Double.parseDouble(PriceFrom.getText());
            double maxPrice = PriceTo.getText().isEmpty() ? 0 : Double.parseDouble(PriceTo.getText());

            if (minPrice > 0 || maxPrice > 0) {
                if (whereClause.length() > 0) {
                    whereClause.append("AND ");
                }
                if (minPrice > 0 && maxPrice > 0) {
                    whereClause.append("grn_items.price BETWEEN ").append(minPrice).append(" AND ").append(maxPrice).append(" ");
                } else if (minPrice > 0) {
                    whereClause.append("grn_items.price >= ").append(minPrice).append(" ");
                } else {
                    whereClause.append("grn_items.price <= ").append(maxPrice).append(" ");
                }
            }

            // Quantity range filter
            int fromQty = QtyFrom.getText().isEmpty() ? 0 : Integer.parseInt(QtyFrom.getText());
            int toQty = QtyTo.getText().isEmpty() ? 0 : Integer.parseInt(QtyTo.getText());

            if (fromQty > 0 || toQty > 0) {
                if (whereClause.length() > 0) {
                    whereClause.append("AND ");
                }
                if (fromQty > 0 && toQty > 0) {
                    whereClause.append("grn_items.qty BETWEEN ").append(fromQty).append(" AND ").append(toQty).append(" ");
                } else if (fromQty > 0) {
                    whereClause.append("grn_items.qty >= ").append(fromQty).append(" ");
                } else {
                    whereClause.append("grn_items.qty <= ").append(toQty).append(" ");
                }
            }

            
            if (whereClause.length() > 0) {
                query += " WHERE " + whereClause.toString();
            }

            ResultSet resultSet = MySqlConnection.executeSearch(query);

            DefaultTableModel dtm = (DefaultTableModel) GRNViewTable.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                vector.add(dateFormat.format(dateFormat.parse(resultSet.getString("date"))));
                vector.add(resultSet.getString("grn_items.price"));
                vector.add(resultSet.getString("grn_items.qty"));
                vector.add(resultSet.getString("paid_amount"));
                vector.add(resultSet.getString("supplier_id"));
                vector.add(resultSet.getString("employee_id"));

                dtm.addRow(vector);
            }

            jLabel5.setText(String.valueOf(row));
            GRNViewTable.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Error while sorting table: " + e.getMessage());
        }
    }


    private void SupplierChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupplierChooserActionPerformed
        SortTable();
    }//GEN-LAST:event_SupplierChooserActionPerformed

    private void EmployeeChooserItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EmployeeChooserItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_EmployeeChooserItemStateChanged

    private void EmployeeChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmployeeChooserActionPerformed
        // TODO add your handling code here:
        SortTable();
    }//GEN-LAST:event_EmployeeChooserActionPerformed

    private void jFromDateFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFromDateFieldMouseExited
        loadtoGrnTable();
    }//GEN-LAST:event_jFromDateFieldMouseExited

    private void jFromDateFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFromDateFieldPropertyChange
//        if (fromdatePicker.isDateSelected()) {
//            loadtoGrnTable();
//        }
    }//GEN-LAST:event_jFromDateFieldPropertyChange

    private void jToDateFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToDateFieldMouseExited
        loadtoGrnTable();
    }//GEN-LAST:event_jToDateFieldMouseExited

    private void jToDateFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jToDateFieldPropertyChange
//        if (todatePicker.isDateSelected()) {
//            loadtoGrnTable();
//        }
    }//GEN-LAST:event_jToDateFieldPropertyChange

    public void reloadTable() {
        loadtoGrnTable();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> EmployeeChooser;
    private javax.swing.JTable GRNViewTable;
    private javax.swing.JFormattedTextField PriceFrom;
    private javax.swing.JFormattedTextField PriceTo;
    private javax.swing.JFormattedTextField QtyFrom;
    private javax.swing.JFormattedTextField QtyTo;
    private javax.swing.JComboBox<String> SupplierChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField jFromDateField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JFormattedTextField jToDateField;
    private javax.swing.JButton printReportb;
    private javax.swing.JButton viewReportb;
    // End of variables declaration//GEN-END:variables
}
