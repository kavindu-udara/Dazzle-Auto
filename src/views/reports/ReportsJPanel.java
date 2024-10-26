/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.reports;

import controllers.AppointmentController;
import controllers.CustomerController;
import controllers.EmployeeController;
import controllers.GrnController;
import controllers.ProductController;
import controllers.ServicesController;
import controllers.StockController;
import controllers.SupplierController;
import controllers.VehicleController;
import includes.MySqlConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import views.components.reportsTableRender.viewReportFrameActionCellEditor;
import views.components.reportsTableRender.viewReportFrameActionEvent;
import views.components.reportsTableRender.viewReportFrameCellRender;
import includes.LoggerConfig;
import java.util.logging.Logger;

/**
 *
 * @author Dinuka
 */
public class ReportsJPanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();

    public ReportsJPanel() {
        initComponents();

        reportsTableRender();
        loadReports();
    }

    private void reportsTableRender() {

        viewReportFrameActionEvent event = new viewReportFrameActionEvent() {

            @Override
            public void onView(int row) {

            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = jReportsTable.getTableHeader();

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Font headerFont = new Font("Verdana", Font.BOLD, 16);
                label.setBorder(BorderFactory.createEmptyBorder()); // Remove header borders
                label.setFont(headerFont);
                label.setBackground(new Color(214, 132, 13)); // Optional: Set header background color
                label.setForeground(Color.WHITE); // Optional: Set header text color
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return label;
            }
        });

        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 40));
        jReportsTable.getColumnModel().getColumn(2).setCellRenderer(new viewReportFrameCellRender());
        jReportsTable.getColumnModel().getColumn(2).setCellEditor(new viewReportFrameActionCellEditor(event));

        for (int i = 0; i < 2; i++) {
            jReportsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void loadReports() {
        int Count = 0;

        try {

            //Employees Report
            ResultSet employeeshow = new EmployeeController().show();
            while (employeeshow.next()) {
                Count++;
            }
            addNewReport("Employees Report", Count);
            Count = 0;

            //Vehicle Report
            ResultSet vehicleshow = new VehicleController().show();
            while (vehicleshow.next()) {
                Count++;
            }
            addNewReport("Vehicle Report", Count);
            Count = 0;

            //Customer Report
            ResultSet customershow = new CustomerController().show();
            while (customershow.next()) {
                Count++;
            }
            addNewReport("Customer Report", Count);
            Count = 0;

            //Appointment Report
            ResultSet Appointmentshow = new AppointmentController().show();
            while (Appointmentshow.next()) {
                Count++;
            }
            addNewReport("Appointment Report", Count);
            Count = 0;

            //Our Services Report
            ResultSet OurServicesshow = new ServicesController().show();
            while (OurServicesshow.next()) {
                Count++;
            }
            addNewReport("Our Services Report", Count);
            Count = 0;

            //Products Report
            ResultSet Productsshow = new ProductController().show();
            while (Productsshow.next()) {
                Count++;
            }
            addNewReport("Products Report", Count);
            Count = 0;

            //Suppliers Report
            ResultSet Suppliersshow = new SupplierController().show();
            while (Suppliersshow.next()) {
                Count++;
            }
            addNewReport("Suppliers Report", Count);
            Count = 0;

            //Stock Report
            ResultSet Stockshow = new StockController().show();
            while (Stockshow.next()) {
                Count++;
            }
            addNewReport("Stock Report", Count);
            Count = 0;

            //GRN Report
            ResultSet GRNshow = new GrnController().show();
            while (GRNshow.next()) {
                Count++;
            }
            addNewReport("GRN Report", Count);
            Count = 0;

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loadReports() : " + e.getMessage());
        }
    }

    public void addNewReport(String reportName, int recordCount) {

        DefaultTableModel dtm = (DefaultTableModel) jReportsTable.getModel();

        Vector<String> vector = new Vector<>();
        vector.add(reportName);
        vector.add(String.valueOf(recordCount));

        dtm.addRow(vector);

        jReportsTable.setModel(dtm);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jReportsTable = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1100, 610));
        setPreferredSize(new java.awt.Dimension(1100, 610));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/report-3.png"))); // NOI18N
        jLabel1.setText("  Reports");

        jReportsTable.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jReportsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Report Name", "All Num. Of Records", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jReportsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jReportsTable.setFocusable(false);
        jReportsTable.setRowHeight(42);
        jReportsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jReportsTable);
        if (jReportsTable.getColumnModel().getColumnCount() > 0) {
            jReportsTable.getColumnModel().getColumn(2).setPreferredWidth(300);
            jReportsTable.getColumnModel().getColumn(2).setMaxWidth(250);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1065, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(59, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        jReportsTable.clearSelection();
    }//GEN-LAST:event_jPanel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable jReportsTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
