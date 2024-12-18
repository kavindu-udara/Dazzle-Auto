/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.reports;

import controllers.ServiceInvoiceController;

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
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import views.dashboard.Dashboard;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.BorderFactory;
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
public class ServiceStationIncomePanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();

    Dashboard dashboard = null;

    public ServiceStationIncomePanel(Dashboard dashboard) {
        initComponents();
        loadInvoices();
        this.dashboard = dashboard;
        IncomeTableRender();
    }
    
    private void IncomeTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = jPaidInvoiceTable.getTableHeader();

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

        for (int i = 0; i < 8; i++) {
            jPaidInvoiceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPaidInvoiceTable = new javax.swing.JTable();
        viewReportb = new javax.swing.JButton();
        printReportb = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jSortComboBox = new javax.swing.JComboBox<>();

        setMinimumSize(new java.awt.Dimension(1100, 610));
        setPreferredSize(new java.awt.Dimension(1100, 610));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/icons8-reports-30.png"))); // NOI18N
        jLabel1.setText("  Service Station Income Report");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/back-35.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPaidInvoiceTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jPaidInvoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INVOICE ID", "Vehicle No", "Date", "Total", "Paid Amount", "Balance", "Payment Method", "Issued By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jPaidInvoiceTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPaidInvoiceTable.setFocusable(false);
        jPaidInvoiceTable.setRowHeight(30);
        jPaidInvoiceTable.getTableHeader().setReorderingAllowed(false);
        jPaidInvoiceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPaidInvoiceTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jPaidInvoiceTable);

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

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setText("Sort By :");

        jSortComboBox.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jSortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vehicle No A-Z", "Vehicle No Z-A", "Paid Amount A-Z", "Paid Amount Z-A", "Registered Date Oldest", "Registered Date Newest" }));
        jSortComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(viewReportb)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(printReportb))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jSortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(printReportb)
                    .addComponent(viewReportb))
                .addGap(18, 18, 18))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dashboard.jReportPanel.remove(this);
        SwingUtilities.updateComponentTreeUI(dashboard.jReportPanel);

        dashboard.reportsJPanel = new ReportsJPanel(dashboard);
        dashboard.jReportPanel.add(dashboard.reportsJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dashboard.jReportPanel);
    }//GEN-LAST:event_jButton1ActionPerformed
    public void loadInvoices() {
        try {
            ResultSet resultSet = new ServiceInvoiceController().search("");

            DefaultTableModel dtm = (DefaultTableModel) jPaidInvoiceTable.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("vehicle_number"));
                vector.add(resultSet.getString("date"));
                vector.add(resultSet.getString("total"));
                vector.add(resultSet.getString("paid_amount"));
                vector.add(resultSet.getString("balance"));
                vector.add(resultSet.getString("method"));
                vector.add(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));

                dtm.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading invoices : " + e.getMessage());
        }

    }
    public ResultSet search(String searchText, String sortOption) throws Exception {
        // Base query

        String tableName = "service_invoice";
        String query = "SELECT * FROM `" + tableName + "` "
                + "INNER JOIN payment_method ON service_invoice.payment_method_id = payment_method.id "
                + "INNER JOIN employee ON service_invoice.employee_id = employee.id";

        // Add search filters if `searchText` is not empty
        if (searchText != null && !searchText.trim().isEmpty()) {
            query += " WHERE `vehicle_number` LIKE '%" + searchText + "%' OR "
                    + "`service_invoice`.`id` LIKE '%" + searchText + "%' OR "
                    + "`employee`.`first_name` LIKE '%" + searchText + "%' OR "
                    + "`employee`.`last_name` LIKE '%" + searchText + "%'";
        }

        // Add sorting conditions based on the selected option
        if (sortOption != null && !sortOption.trim().isEmpty()) {
            if (sortOption.equals("Vehicle No A-Z")) {
                query += " ORDER BY `vehicle_number` ASC";
            } else if (sortOption.equals("Vehicle No Z-A")) {
                query += " ORDER BY `vehicle_number` DESC";
            } else if (sortOption.equals("Paid Amount Oldest")) {
                query += " ORDER BY `service_invoice`.`paid_amount` ASC";
            } else if (sortOption.equals("Paid Amount Newest")) {
                query += " ORDER BY `service_invoice`.`paid_amount` DESC";
            } else if (sortOption.equals("Registered Date Oldest")) {
                query += " ORDER BY `service_invoice`.`date` ASC";
            } else if (sortOption.equals("Registered Date Newest")) {
                query += " ORDER BY `service_invoice`.`date` DESC";
            }
        } else {
            // Default sorting if no specific sort option is provided
            query += " ORDER BY `service_invoice`.`date` DESC";
        }

        // Execute the query
        return MySqlConnection.executeSearch(query);
    }

    private void jPaidInvoiceTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPaidInvoiceTableMouseClicked

    }//GEN-LAST:event_jPaidInvoiceTableMouseClicked
    public JasperPrint makeReport() {

        String headerImg;
        try {
            InputStream s = this.getClass().getResourceAsStream("/resources/reports/Service_Station_Income.jasper");
            String img = new File(this.getClass().getResource("/resources/reports/dazzle_auto_tp.png").getFile()).getAbsolutePath();

            headerImg = img.replace("\\", "/");

            HashMap<String, Object> params = new HashMap<>();
            params.put("img", headerImg);

            params.put("employee", LoginModel.getFirstName() + " " + LoginModel.getLastName());

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jPaidInvoiceTable.getModel());

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

            logger.info("Shop Report Viewed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while viewReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_viewReportbActionPerformed

    private void printReportbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReportbActionPerformed
        try {
            JasperPrint report = makeReport();
            JasperPrintManager.printReport(report, false);

            logger.info("Shop Report Printed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while printReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_printReportbActionPerformed

    private void jSortComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jSortComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Call the method to sort and display invoices
            try {
                // Fetch search text and selected sort option
                String searchText = "";
                String sortOption = String.valueOf(jSortComboBox.getSelectedItem());

                // Execute the search with sorting
                ResultSet resultSet = search(searchText, sortOption);

                // Get the table model
                DefaultTableModel model = (DefaultTableModel) jPaidInvoiceTable.getModel();
                model.setRowCount(0); // Clear the table

                // Populate the table with data from ResultSet
                while (resultSet.next()) {
                    String invoiceId = resultSet.getString("id");
                    String vehicleNo = resultSet.getString("vehicle_number");
                    String date = resultSet.getString("date");
                    double total = resultSet.getDouble("total"); // Total Amount
                    double paidAmount = resultSet.getDouble("paid_amount");
                    double balance = resultSet.getDouble("balance");
                    String paymentMethod = resultSet.getString("method"); // Payment Method
                    String issuedBy = resultSet.getString("first_name") + " " + resultSet.getString("last_name");

                    // Add the row to the table model
                    model.addRow(new Object[]{
                        invoiceId, // Invoice ID
                        vehicleNo, // Vehicle Number
                        date, // Date
                        total, // Total Amount
                        paidAmount, // Paid Amount
                        balance, // Balance
                        paymentMethod, // Payment Method
                        issuedBy // Issued By
                    });
                }

                // Revalidate and repaint the table to reflect updates
                jPaidInvoiceTable.revalidate();
                jPaidInvoiceTable.repaint();

            } catch (Exception e) {
                e.printStackTrace();
                logger.severe("Error while sorting invoices: " + e.getMessage());
            }
        }


    }//GEN-LAST:event_jSortComboBoxItemStateChanged

    private void jSortComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSortComboBoxActionPerformed
        //
    }//GEN-LAST:event_jSortComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTable jPaidInvoiceTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> jSortComboBox;
    private javax.swing.JButton printReportb;
    private javax.swing.JButton viewReportb;
    // End of variables declaration//GEN-END:variables
}
