/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.reports;

import com.formdev.flatlaf.FlatClientProperties;
import controllers.EmployeeController;
import controllers.EmployeeImageController;
import controllers.EmployeeTypeController;
import controllers.StatusController;
import includes.LoggerConfig;
import includes.MySqlConnection;
import includes.TimestampsGenerator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
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
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import views.dashboard.Dashboard;

/**
 *
 * @author Dinuka
 */
public class EmployeeReportPanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();

    Dashboard dashboard = null;

    public EmployeeReportPanel(Dashboard dashboard) {
        initComponents();
        this.dashboard = dashboard;
        loadEmployees();
        EmployeeTableRender();
        
        employeeFindField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Name/Mobile/NIC/Email");
    }

     private void EmployeeTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = employeeViewTable.getTableHeader();

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
            employeeViewTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void sortEmployees() {
        try {
            String query = "SELECT * FROM employee";

            String searchText = employeeFindField.getText().trim();
            if (!searchText.isEmpty()) {
                query += " WHERE `first_name` LIKE '%" + searchText + "%' OR "
                        + "`last_name` LIKE '%" + searchText + "%' OR "
                        + "`email` LIKE '%" + searchText + "%' OR "
                        + "`mobile` LIKE '%" + searchText + "%'";
            }

            // Sorting logic
            String sort = String.valueOf(jSortComboBox.getSelectedItem());

            // Append sorting conditions to the query
            if (sort.contains("First Name A-Z")) {
                query += " ORDER BY `first_name` ASC";
            } else if (sort.contains("First Name Z-A")) {
                query += " ORDER BY `first_name` DESC";
            } else if (sort.contains("Last Name A-Z")) {
                query += " ORDER BY `last_name` ASC";
            } else if (sort.contains("Last Name Z-A")) {
                query += " ORDER BY `last_name` DESC";
            } else if (sort.contains("Registered Date Oldest")) {
                query += " ORDER BY `registered_date` ASC";
            } else if (sort.contains("Registered Date Newest")) {
                query += " ORDER BY `registered_date` DESC";
            }

            ResultSet employeeResultSet = MySqlConnection.executeSearch(query);
            ResultSet resultSet2 = new StatusController().search("");
            ResultSet resultSet1 = new EmployeeTypeController().search("");

            DefaultTableModel model = (DefaultTableModel) employeeViewTable.getModel();
            model.setRowCount(0);

            HashMap<Integer, String> employeeTypeMap = new HashMap<>();
            HashMap<Integer, String> statusMap = new HashMap<>();

            while (resultSet1.next()) {
                int employeeTypeId = resultSet1.getInt("id");
                String employeeTypeName = resultSet1.getString("type");
                employeeTypeMap.put(employeeTypeId, employeeTypeName);
            }

            while (resultSet2.next()) {
                int statusId = resultSet2.getInt("id");
                String statusName = resultSet2.getString("status");
                statusMap.put(statusId, statusName);
            }

            while (employeeResultSet.next()) {
                String employeeId = employeeResultSet.getString("id");
                String nic = employeeResultSet.getString("nic");
                String firstName = employeeResultSet.getString("first_name");
                String lastName = employeeResultSet.getString("last_name");
                String email = employeeResultSet.getString("email");
                String mobile = employeeResultSet.getString("mobile");

                int employeeTypeId = employeeResultSet.getInt("employee_type_id");
                int statusId = employeeResultSet.getInt("status_id");

                String statusName = statusMap.getOrDefault(statusId, "Unknown Status");
                String employeeTypeName = employeeTypeMap.getOrDefault(employeeTypeId, "Unknown Type");

                model.addRow(new Object[]{employeeId, nic, firstName, lastName, email, mobile, employeeTypeName, statusName});
            }

            employeeViewTable.revalidate();
            employeeViewTable.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading employees: " + e.getMessage());
        }
    }

    private void loadEmployees() {
        try {

            ResultSet employeeResultSet = new EmployeeController().show();
            ResultSet employeeTypeResultSet = new EmployeeTypeController().show();
            ResultSet statusResultSet = new StatusController().show();
            ResultSet employeeImageResultSet = new EmployeeImageController().show();

            HashMap<Integer, String> employeeTypeMap = new HashMap<>();
            HashMap<Integer, String> statusMap = new HashMap<>();
            HashMap<String, String> imagePathMap = new HashMap<>();

            while (employeeTypeResultSet.next()) {
                int employeeTypeId = employeeTypeResultSet.getInt("id");
                String employeeTypeName = employeeTypeResultSet.getString("type");
                employeeTypeMap.put(employeeTypeId, employeeTypeName);
            }

            while (statusResultSet.next()) {
                int statusId = statusResultSet.getInt("id");
                String statusName = statusResultSet.getString("status");
                statusMap.put(statusId, statusName);
            }

            while (employeeImageResultSet.next()) {
                String employeeId = employeeImageResultSet.getString("employee_id");
                String imagePath = employeeImageResultSet.getString("path");
                imagePathMap.put(employeeId, imagePath);
            }

            DefaultTableModel model = (DefaultTableModel) employeeViewTable.getModel();
            model.setRowCount(0);

            while (employeeResultSet.next()) {
                Vector<String> vector = new Vector<>();

                String employeeId = employeeResultSet.getString("id");
                vector.add(employeeId);
                vector.add(employeeResultSet.getString("nic"));

                vector.add(employeeResultSet.getString("first_name"));
                vector.add(employeeResultSet.getString("last_name"));
                vector.add(employeeResultSet.getString("email"));
                vector.add(employeeResultSet.getString("mobile"));
                //vector.add(employeeResultSet.getString("registered_date"));

                int employeeTypeId = employeeResultSet.getInt("employee_type_id");
                int statusId = employeeResultSet.getInt("status_id");

                String employeeTypeName = employeeTypeMap.getOrDefault(employeeTypeId, "Unknown Employee Type");
                String statusName = statusMap.getOrDefault(statusId, "Unknown Status");

                vector.add(employeeTypeName);
                vector.add(statusName);
                String imagePath = imagePathMap.get(employeeId);
                if (imagePath == null || imagePath.trim().isEmpty()) {
                    imagePath = "No Image";
                }
                vector.add(imagePath);
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading Employee : " + e.getMessage());
        }

    }

    private void fetchUser(String searchText) throws Exception {
        DefaultTableModel model = (DefaultTableModel) employeeViewTable.getModel();
        model.setRowCount(0);

        try {
            ResultSet resultSet = new EmployeeController().search(searchText);
            ResultSet resultSet2 = new StatusController().search("");
            ResultSet resultSet1 = new EmployeeTypeController().search("");

            HashMap<Integer, String> employeeTypeMap = new HashMap<>();
            HashMap<Integer, String> statusMap = new HashMap<>();

            while (resultSet1.next()) {
                int employeeTypeId = resultSet1.getInt("id");
                String employeeTypeName = resultSet1.getString("type");
                employeeTypeMap.put(employeeTypeId, employeeTypeName);
            }

            while (resultSet2.next()) {
                int statusId = resultSet2.getInt("id");
                String statusName = resultSet2.getString("status");
                statusMap.put(statusId, statusName);
            }

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String mobile = resultSet.getString("mobile");
                String nic = resultSet.getString("nic");

                String registeredDate = resultSet.getString("registered_date");
                int employeeTypeId = resultSet.getInt("employee_type_id");

                int statusId = resultSet.getInt("status_id");

                String statusName = statusMap.getOrDefault(statusId, "Unknown Status");
                String employeeTypeName = employeeTypeMap.getOrDefault(employeeTypeId, "Unknown Type");

                model.addRow(new Object[]{id, nic, fname, lname, email, mobile, employeeTypeName, statusName});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while fetching Employee : " + ex.getMessage());
        }
    }

    public JasperPrint makeReport() {

        String headerImg;
        try {
            InputStream s = this.getClass().getResourceAsStream("/resources/reports/DazzleAutoEmployeeReport.jasper");
            String img = new File(this.getClass().getResource("/resources/reports/dazzle_auto_tp.png").getFile()).getAbsolutePath();

            headerImg = img.replace("\\", "/");

            HashMap<String, Object> params = new HashMap<>();
            params.put("imageParam", headerImg);
            params.put("timestampParam", String.valueOf(TimestampsGenerator.getFormattedDateTime()));

            JRTableModelDataSource dataSource = new JRTableModelDataSource(employeeViewTable.getModel());

            JasperPrint report = JasperFillManager.fillReport(s, params, dataSource);

            return report;

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while makeReport() : " + e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        employeeFindField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSortComboBox = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        employeeViewTable = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1100, 610));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/icons8-reports-30.png"))); // NOI18N
        jLabel1.setText("  Employees Report");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/back-35.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel3.setText("Find Employee :");

        employeeFindField.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        employeeFindField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeFindFieldActionPerformed(evt);
            }
        });
        employeeFindField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                employeeFindFieldKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setText("Sort By :");

        jSortComboBox.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jSortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "First Name A-Z", "First Name Z-A", "Last Name A-Z", "Last Name Z-A", "Registered Date Oldest", "Registered Date Newest" }));
        jSortComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jSortComboBoxItemStateChanged(evt);
            }
        });
        jSortComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSortComboBoxActionPerformed(evt);
            }
        });

        employeeViewTable.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        employeeViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "NIC", "First Name ", "Last Name ", "Email ", "Mobile", "Employee Type", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employeeViewTable.setToolTipText("Right Click Employee to view Profile");
        employeeViewTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        employeeViewTable.setFocusable(false);
        employeeViewTable.setRowHeight(30);
        employeeViewTable.getTableHeader().setReorderingAllowed(false);
        employeeViewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeViewTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                employeeViewTableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(employeeViewTable);

        jButton2.setBackground(new java.awt.Color(0, 102, 0));
        jButton2.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Print Report");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(employeeFindField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(2, 2, 2)
                        .addComponent(jSortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(31, 31, 31))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 19, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(employeeFindField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addGap(31, 31, 31))
            .addComponent(jSeparator2)
        );

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


    private void employeeFindFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeFindFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeFindFieldActionPerformed

    private void employeeFindFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_employeeFindFieldKeyReleased

        try {
            fetchUser(employeeFindField.getText().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while employeeFindFieldKeyReleased : " + ex.getMessage());
        }
    }//GEN-LAST:event_employeeFindFieldKeyReleased

    private void jSortComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jSortComboBoxItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            sortEmployees();
        }
    }//GEN-LAST:event_jSortComboBoxItemStateChanged

    private void jSortComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSortComboBoxActionPerformed
        //
    }//GEN-LAST:event_jSortComboBoxActionPerformed

    private void employeeViewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeViewTableMouseClicked
//
    }//GEN-LAST:event_employeeViewTableMouseClicked

    private void employeeViewTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeViewTableMouseReleased

    }//GEN-LAST:event_employeeViewTableMouseReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            JasperPrint report = makeReport();
            JasperViewer.viewReport(report, false);
            logger.info("Employee Report Viewed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while view Report : " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField employeeFindField;
    private javax.swing.JTable employeeViewTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> jSortComboBox;
    // End of variables declaration//GEN-END:variables
}
