/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.vehicle;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import controllers.VehicleBrandController;
import controllers.VehicleController;
import controllers.VehicleTypeController;
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
import includes.LoggerConfig;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;
import models.LoginModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Dinuka
 */
public class ServiceHistory extends javax.swing.JFrame {

    private static Logger logger = LoggerConfig.getLogger();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    LocalDateTime start = LocalDateTime.parse("2024-01-01 00:00:00", formatter);
    ;
    LocalDateTime end = LocalDateTime.parse("2050-01-01 00:00:00", formatter);
    ;

    String VehicleNumber = "";

    public ServiceHistory(String vehicleNumber) {
        initComponents();

        this.VehicleNumber = vehicleNumber;

        FromDatePicker.setEditor(FromField);
        ToDatePicker.setEditor(ToField);

        jVehicleNumberLabel.setText(vehicleNumber);

        Table1Render();
        Table2Render();
        loadServices();
    }

    private void Table1Render() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = jTable1.getTableHeader();

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Font headerFont = new Font("Verdana", Font.BOLD, 14);
                label.setBorder(BorderFactory.createEmptyBorder()); // Remove header borders
                label.setFont(headerFont);
                label.setBackground(new Color(214, 132, 13)); // Optional: Set header background color
                label.setForeground(Color.WHITE); // Optional: Set header text color
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return label;
            }
        });

        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));

        for (int i = 0; i < 5; i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void Table2Render() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = jTable2.getTableHeader();

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

        for (int i = 0; i < 2; i++) {
            jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void loadServices() {
        jTable2.clearSelection();
        DefaultTableModel jTable2dtm = (DefaultTableModel) jTable2.getModel();
        jTable2dtm.setRowCount(0);

        try {

            String query = "SELECT * FROM `service_invoice` WHERE `vehicle_number`='" + VehicleNumber + "'";

            if (FromDatePicker.isDateSelected()) {
                start = FromDatePicker.getSelectedDate().atStartOfDay();
                query += "  AND `date` > '" + start.format(formatter) + "'";
            }

            if (ToDatePicker.isDateSelected()) {
                end = ToDatePicker.getSelectedDate().atStartOfDay();
                query += "  AND `date` < '" + end.format(formatter) + "' ";
            }

            ResultSet resultSet = MySqlConnection.executeSearch(query);

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("date"));
                vector.add(resultSet.getString("total"));
                vector.add(resultSet.getString("odometer"));
                vector.add(resultSet.getString("next_service"));

                dtm.addRow(vector);
            }

            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loadServices() : " + e.getMessage());
        }
    }

    public void loadServiceItems(String invoiceID) {
        try {

            String query = "SELECT * FROM `service_invoice_items` "
                    + "INNER JOIN services ON service_invoice_items.services_id=services.id "
                    + "WHERE `service_invoice_id`='" + invoiceID + "' ";

            ResultSet resultSet = MySqlConnection.executeSearch(query);

            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("services.name"));
                vector.add(resultSet.getString("service_invoice_items.description"));

                dtm.addRow(vector);
            }

            jTable2.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loadServiceItems(String invoiceID) : " + e.getMessage());
        }
    }

    public JasperPrint makeReport() {

        String dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa").format(new Date());
        String subPath = "";
        String imgPath = "";
        String vehicleBrand = "";
        String vehicleModel = "";
        String vehicleType = "";

        try {
            ResultSet vehicleRs = new VehicleController().show(VehicleNumber);
            if (vehicleRs.next()) {
                vehicleModel = vehicleRs.getString("model");

                ResultSet brandRs = new VehicleBrandController().show(vehicleRs.getInt("vehicle_brand_id"));
                if (brandRs.next()) {
                    vehicleBrand = brandRs.getString("name");
                }

                ResultSet typeRs = new VehicleTypeController().show(vehicleRs.getInt("vehicle_type_id"));
                if (typeRs.next()) {
                    vehicleType = typeRs.getString("name");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while makeReport() vehicle detail Resultset : " + e.getMessage());
        }

        try {
            InputStream s = this.getClass().getResourceAsStream("/resources/reports/History_main_report.jasper");
            String s2 = new File(this.getClass().getResource("/resources/reports/History_sub_report.jasper").getFile()).getAbsolutePath();
            String img = new File(this.getClass().getResource("/resources/reports/dazzle_auto_tp.png").getFile()).getAbsolutePath();
            subPath = s2.replace("\\", "/");
            imgPath = img.replace("\\", "/");

            HashMap<String, Object> params = new HashMap<>();
            params.put("subPath", subPath);
            params.put("imgPath", imgPath);
            params.put("connection", MySqlConnection.connection);
            params.put("vNum", VehicleNumber);
            params.put("fromDate", start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            params.put("toDate", end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            params.put("vehicleModel", vehicleBrand+" "+vehicleModel);
            params.put("vType", vehicleType);
            params.put("date", dateTime);

            JasperPrint report = JasperFillManager.fillReport(s, params, MySqlConnection.connection);

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

        FromDatePicker = new raven.datetime.component.date.DatePicker();
        ToDatePicker = new raven.datetime.component.date.DatePicker();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jVehicleNumberLabel = new javax.swing.JLabel();
        FromField = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ToField = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        viewReportb = new javax.swing.JButton();
        printReportb = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1058, 629));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1058, 629));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/history-30.png"))); // NOI18N
        jLabel2.setText("  Service History");

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setText("Vehicle Number  :");

        jVehicleNumberLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jVehicleNumberLabel.setForeground(new java.awt.Color(255, 0, 0));
        jVehicleNumberLabel.setText("VEHICLE_ID_HERE");

        FromField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        FromField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                FromFieldPropertyChange(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("From :");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("To :");

        ToField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        ToField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ToFieldPropertyChange(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice ID", "Date", "Total Charge", "Odometer(Km)", "Next Service(Km)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setFocusable(false);
        jTable1.setRowHeight(30);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Service", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setFocusable(false);
        jTable2.setRowHeight(30);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);

        viewReportb.setBackground(new java.awt.Color(51, 51, 51));
        viewReportb.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        viewReportb.setForeground(new java.awt.Color(255, 255, 255));
        viewReportb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/analyze-30.png"))); // NOI18N
        viewReportb.setText(" View Report");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jVehicleNumberLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(FromField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(136, 136, 136))
                                    .addComponent(ToField)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(viewReportb, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(printReportb, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jVehicleNumberLabel)
                    .addComponent(ToField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FromField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(printReportb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewReportb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        String invoiceID = String.valueOf(jTable1.getValueAt(row, 0));

        loadServiceItems(invoiceID);

    }//GEN-LAST:event_jTable1MouseClicked

    private void FromFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_FromFieldPropertyChange
        loadServices();
    }//GEN-LAST:event_FromFieldPropertyChange

    private void ToFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ToFieldPropertyChange
        loadServices();
    }//GEN-LAST:event_ToFieldPropertyChange

    private void viewReportbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportbActionPerformed

        try {
            JasperPrint report = makeReport();
            JasperViewer.viewReport(report, false);

            logger.info("Vehicle : " + VehicleNumber + ", History Report Viewed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while viewReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_viewReportbActionPerformed

    private void printReportbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReportbActionPerformed
        try {
            JasperPrint report = makeReport();
            JasperPrintManager.printReport(report, false);

            logger.info("Vehicle : " + VehicleNumber + ", History Report Printed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while printReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_printReportbActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.datetime.component.date.DatePicker FromDatePicker;
    private javax.swing.JFormattedTextField FromField;
    private raven.datetime.component.date.DatePicker ToDatePicker;
    private javax.swing.JFormattedTextField ToField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel jVehicleNumberLabel;
    private javax.swing.JButton printReportb;
    private javax.swing.JButton viewReportb;
    // End of variables declaration//GEN-END:variables
}
