/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.reports;

import com.formdev.flatlaf.FlatClientProperties;
import controllers.StatusController;
import controllers.SupplierController;
import includes.LoggerConfig;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
public class SuppliersReportPanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();
    private static HashMap<String, String> StatusMap = new HashMap<>();
    Dashboard dashboard = null;

    public SuppliersReportPanel(Dashboard dashboard) {
        initComponents();
        loadsupplier();
        SupplierTableRender();
        loadStatusToSortBtn();
        this.dashboard = dashboard;

        search_box.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Supplier Name / Id / Mobile or Email");
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
        SupplierViewTable = new javax.swing.JTable();
        viewReportb = new javax.swing.JButton();
        printReportb = new javax.swing.JButton();
        search_box = new javax.swing.JTextField();
        StatusSortBtn = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

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
        jLabel1.setText("  Suppliers Report");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 6, 975, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/back-35.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 6, -1, 30));

        SupplierViewTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        SupplierViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier ID", "First Name", "Last Name", "Email", "Mobile", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SupplierViewTable.setRowHeight(30);
        jScrollPane1.setViewportView(SupplierViewTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 1060, -1));

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
        jPanel1.add(viewReportb, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 550, -1, -1));

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

        search_box.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        search_box.setFocusCycleRoot(true);
        search_box.setSelectionColor(new java.awt.Color(214, 132, 13));
        search_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_boxKeyReleased(evt);
            }
        });
        jPanel1.add(search_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 300, 40));

        StatusSortBtn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        StatusSortBtn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                StatusSortBtnItemStateChanged(evt);
            }
        });
        StatusSortBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusSortBtnActionPerformed(evt);
            }
        });
        jPanel1.add(StatusSortBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 60, 140, 40));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 0));
        jLabel5.setText("00");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 560, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setText("Status");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 70, -1, -1));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel8.setText("Number Of Suppliers  :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, -1, -1));

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

    private void loadStatusToSortBtn() {
        try {
            ResultSet resultSet = new StatusController().show();

            Vector<String> vector = new Vector<>();
            vector.add("All");

            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                StatusMap.put(resultSet.getString("status"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            StatusSortBtn.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading employee types : " + e.getMessage());
        }
    }

    private void loadsupplier() {

        try {

            ResultSet resultSet = new SupplierController().show();
            ResultSet resultSet2 = new StatusController().show();

            HashMap<Integer, String> statusMap = new HashMap<>();

            while (resultSet2.next()) {
                int statusId = resultSet2.getInt("id");
                String statusName = resultSet2.getString("status");
                statusMap.put(statusId, statusName);
            }

            DefaultTableModel model = (DefaultTableModel) SupplierViewTable.getModel();
            model.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("first_name"));
                vector.add(resultSet.getString("last_name"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("mobile"));

                int statusId = resultSet.getInt("status_id");

                String statusName = statusMap.getOrDefault(statusId, "Unknown Status");

                vector.add(statusName);

                model.addRow(vector);
            }

            jLabel5.setText(String.valueOf(row));
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading supplier : " + e.getMessage());
        }

    }

    private void fetchUser(String searchText) throws Exception {
        DefaultTableModel model = (DefaultTableModel) SupplierViewTable.getModel();
        model.setRowCount(0);

        try {
            ResultSet resultSet = new SupplierController().search(searchText);
            ResultSet resultSet2 = new StatusController().search("");

            HashMap<Integer, String> statusMap = new HashMap<>();

            while (resultSet2.next()) {
                int statusId = resultSet2.getInt("id");
                String statusName = resultSet2.getString("status");
                statusMap.put(statusId, statusName);
            }

            int row = 0;
            while (resultSet.next()) {
                row++;
                String id = resultSet.getString("id");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String mobile = resultSet.getString("mobile");
                int statusId = resultSet.getInt("status_id");
                String statusName = statusMap.getOrDefault(statusId, "Unknown Status");
                model.addRow(new Object[]{id, fname, lname, email, mobile, statusName});
            }

            jLabel5.setText(String.valueOf(row));
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while fetching supplier : " + ex.getMessage());
        }
    }

    public void SupplierTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = SupplierViewTable.getTableHeader();

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

        for (int i = 0; i < 6; i++) {
            SupplierViewTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void reloadTable() {
        loadsupplier();
    }

    public JasperPrint makeReport() {

        String dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa").format(new Date());

        String headerImg;
        try {
            InputStream s = this.getClass().getResourceAsStream("/resources/reports/SupplierReport.jasper");
            String img = new File(this.getClass().getResource("/resources/reports/dazzle_auto_tp.png").getFile()).getAbsolutePath();

            headerImg = img.replace("\\", "/");

            HashMap<String, Object> params = new HashMap<>();
            params.put("img", headerImg);
            params.put("status", String.valueOf(StatusSortBtn.getSelectedItem()));
            params.put("count", jLabel5.getText());
            params.put("employee", LoginModel.getFirstName() + " " + LoginModel.getLastName());
            params.put("reportDate", dateTime);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(SupplierViewTable.getModel());

            JasperPrint report = JasperFillManager.fillReport(s, params, dataSource);

            return report;

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while makeReport() : " + e.getMessage());
        }
        return null;
    }

    private void viewReportbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportbActionPerformed

        try {
            JasperPrint report = makeReport();
            JasperViewer.viewReport(report, false);

            logger.info("Appointments Report Viewed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while viewReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_viewReportbActionPerformed

    private void printReportbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReportbActionPerformed
        try {
            JasperPrint report = makeReport();
            JasperPrintManager.printReport(report, false);

            logger.info("Appointments Report Printed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while printReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_printReportbActionPerformed

    private void search_boxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_boxKeyReleased
        // TODO add your handling code here:
        try {
            fetchUser(search_box.getText().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while search Suppliers : " + ex.getMessage());
        }
    }//GEN-LAST:event_search_boxKeyReleased

    private void StatusSortBtnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_StatusSortBtnItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_StatusSortBtnItemStateChanged

    private void StatusSortBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusSortBtnActionPerformed
        // TODO add your handling code here:
        String selectedStatus = StatusSortBtn.getSelectedItem().toString();
        SortStatus(selectedStatus);
    }//GEN-LAST:event_StatusSortBtnActionPerformed

    private void SortStatus(String searchText) {
        try {
            DefaultTableModel model = (DefaultTableModel) SupplierViewTable.getModel();
            model.setRowCount(0);

            ResultSet resultSet;

            if (searchText.equals("All")) {
                resultSet = new SupplierController().searchAll();
            } else {

                ResultSet statusResultSet = new StatusController().search(searchText);
                int statusId = -1;
                if (statusResultSet.next()) {
                    statusId = statusResultSet.getInt("id");
                }

                if (statusId != -1) {
                    resultSet = new SupplierController().getSuppliersByStatusId(statusId);
                } else {
                    model.setRowCount(0);
                    return;
                }
            }

            HashMap<Integer, String> statusMap = new HashMap<>();
            ResultSet allStatuses = new StatusController().search("");
            while (allStatuses.next()) {
                int id = allStatuses.getInt("id");
                String name = allStatuses.getString("status");
                statusMap.put(id, name);
            }

            int row = 0;
            while (resultSet.next()) {
                row++;
                String id = resultSet.getString("id");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String mobile = resultSet.getString("mobile");
                int statusId = resultSet.getInt("status_id");

                String statusName = statusMap.getOrDefault(statusId, "Unknown Status");

                model.addRow(new Object[]{id, fname, lname, email, mobile, statusName});
            }

            jLabel5.setText(String.valueOf(row));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> StatusSortBtn;
    private javax.swing.JTable SupplierViewTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton printReportb;
    private javax.swing.JTextField search_box;
    private javax.swing.JButton viewReportb;
    // End of variables declaration//GEN-END:variables
}
