/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.reports;

import controllers.EmployeeAttendanceController;
import controllers.EmployeeController;
import controllers.MonthsController;
import includes.LoggerConfig;
import includes.TimestampsGenerator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.InputStream;
import javax.swing.SwingUtilities;
import views.dashboard.Dashboard;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import models.LoginModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Dinuka
 */
public class EmployeeOverViewPanel extends javax.swing.JPanel {

    private HashMap<String, String> monthsHashMap = new HashMap<>();
    private static final Logger logger = LoggerConfig.getLogger();

    Dashboard dashboard = null;

    public EmployeeOverViewPanel(Dashboard dashboard) {
        initComponents();
        this.dashboard = dashboard;
        loadMonthsComboBox();
        loadTableData();
        EmployeeTableRender();
    }

    private void loadMonthsComboBox() {
        try (ResultSet monthsResultSet = new MonthsController().show()) {
            Vector vector = new Vector();
            while (monthsResultSet.next()) {
                String month = monthsResultSet.getString("name");
                monthsHashMap.put(month, monthsResultSet.getString("id"));
                vector.add(month);
            }
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            monthSelectorComboBox.setModel(comboBoxModel);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading months : " + e.getMessage());
        }
    }

    private void loadTableData() {

        String monthId = monthsHashMap.get(String.valueOf(monthSelectorComboBox.getSelectedItem()));

        try {
            DefaultTableModel tableModel = (DefaultTableModel) employeesTable.getModel();
            tableModel.setRowCount(0);

            ResultSet employeeResultSet = new EmployeeController().show();

            while (employeeResultSet.next()) {

                Vector vector = new Vector();
                vector.add(employeeResultSet.getString("id"));
                vector.add(employeeResultSet.getString("first_name") + " " + employeeResultSet.getString("last_name"));
                vector.add(employeeResultSet.getString("email"));
                vector.add(employeeResultSet.getString("mobile"));

                ResultSet attendanceResultSet = new EmployeeAttendanceController().showCountByEmployeeId(employeeResultSet.getString("id"), Integer.parseInt(monthId), 1);
                if (attendanceResultSet.next()) {
                    vector.add(attendanceResultSet.getString("row_count"));
                }
                tableModel.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading table data : " + e.getMessage());
        }

    }

    private void EmployeeTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = employeesTable.getTableHeader();

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

        for (int i = 0; i < 5; i++) {
            employeesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeesTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        monthSelectorComboBox = new javax.swing.JComboBox<>();
        printReportButton = new javax.swing.JButton();
        saveReportButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1100, 610));
        setPreferredSize(new java.awt.Dimension(1100, 610));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/icons8-reports-30.png"))); // NOI18N
        jLabel1.setText("  Employee Overview Report");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/back-35.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        employeesTable.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        employeesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Mobile", "Attendance Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employeesTable.setRowHeight(27);
        jScrollPane1.setViewportView(employeesTable);

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setText("Select a month");

        monthSelectorComboBox.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        monthSelectorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        monthSelectorComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthSelectorComboBoxActionPerformed(evt);
            }
        });

        printReportButton.setBackground(new java.awt.Color(0, 102, 0));
        printReportButton.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        printReportButton.setForeground(new java.awt.Color(255, 255, 255));
        printReportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/print-30.png"))); // NOI18N
        printReportButton.setText("Print Report");
        printReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReportButtonActionPerformed(evt);
            }
        });

        saveReportButton.setBackground(new java.awt.Color(51, 51, 51));
        saveReportButton.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        saveReportButton.setForeground(new java.awt.Color(255, 255, 255));
        saveReportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/analyze-30.png"))); // NOI18N
        saveReportButton.setText(" Save Report");
        saveReportButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveReportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 62, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(monthSelectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveReportButton)
                        .addGap(18, 18, 18)
                        .addComponent(printReportButton)
                        .addGap(17, 17, 17))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(monthSelectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(printReportButton)
                    .addComponent(saveReportButton))
                .addGap(31, 31, 31))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dashboard.jReportPanel.remove(this);
        SwingUtilities.updateComponentTreeUI(dashboard.jReportPanel);

        dashboard.reportsJPanel = new ReportsJPanel(dashboard);
        dashboard.jReportPanel.add(dashboard.reportsJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dashboard.jReportPanel);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void monthSelectorComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthSelectorComboBoxActionPerformed
        // TODO add your handling code here:
        loadTableData();
    }//GEN-LAST:event_monthSelectorComboBoxActionPerformed

    public JasperPrint makeReport() {

        String headerImg;
        try {
            InputStream s = this.getClass().getResourceAsStream("/resources/reports/dazzleauto-emp-overview.jasper");
            String img = new File(this.getClass().getResource("/resources/reports/dazzle_auto_tp.png").getFile()).getAbsolutePath();

            headerImg = img.replace("\\", "/");

            HashMap<String, Object> params = new HashMap<>();
            params.put("imageParam", headerImg);
            params.put("timestampParam", String.valueOf(TimestampsGenerator.getFormattedDateTime()));

            JRTableModelDataSource dataSource = new JRTableModelDataSource(employeesTable.getModel());

            JasperPrint report = JasperFillManager.fillReport(s, params, dataSource);

            return report;

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while makeReport() : " + e.getMessage());
        }
        return null;
    }

    private void printReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReportButtonActionPerformed
        // TODO add your handling code here:
        try {
            JasperPrint report = makeReport();
            JasperPrintManager.printReport(report, false);

            logger.info("Vehicle Report Printed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while jButton3ActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_printReportButtonActionPerformed

    private void saveReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveReportButtonActionPerformed
        // TODO add your handling code here:
        try {
            JasperPrint report = makeReport();
            JasperViewer.viewReport(report, false);

            logger.info("Employee Overview Report Viewed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while viewReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_saveReportButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable employeesTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> monthSelectorComboBox;
    private javax.swing.JButton printReportButton;
    private javax.swing.JButton saveReportButton;
    // End of variables declaration//GEN-END:variables
}
