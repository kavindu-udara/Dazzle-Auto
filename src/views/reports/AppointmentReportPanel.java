/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.reports;

import controllers.AppointmentController;
import controllers.AppointmentStatusController;
import includes.LoggerConfig;
import includes.MySqlConnection;
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
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import views.dashboard.Dashboard;

/**
 *
 * @author Dinuka
 */
public class AppointmentReportPanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();

    private static HashMap<String, Integer> appointmentStatusHashMap = new HashMap<>();

    Dashboard dashboard = null;

    public AppointmentReportPanel(Dashboard dashboard) {
        initComponents();
        this.dashboard = dashboard;

        fromdatePicker.setEditor(jFromDateField);
        todatePicker.setEditor(jToDateField);

        AppintmentTableRender();
        loadAppointmentStatus();
        loadAppointments();
    }

    private void AppintmentTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = jAppointmentTable.getTableHeader();

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
            jAppointmentTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void loadAppointmentStatus() {

        try {
            ResultSet resultSet = new AppointmentStatusController().show();

            Vector vector = new Vector();

            vector.add("All");

            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                appointmentStatusHashMap.put(resultSet.getString("status"), resultSet.getInt("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            jStatusComboBox.setModel(comboBoxModel);

        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Error while loadAppointmentStatus() : " + e.getMessage());
        }

    }

    public void loadAppointments() {
        try {

            String query = "SELECT * FROM appointment "
                    + "INNER JOIN services ON appointment.services_id=services.id "
                    + "INNER JOIN appointment_status ON appointment.appointment_status_id=appointment_status.id "
                    + "WHERE ";

            int statusID = 0;
            String status = String.valueOf(jStatusComboBox.getSelectedItem());
            if (!status.equals("All")) {
                statusID = appointmentStatusHashMap.get(status);
                query += " `appointment_status_id`LIKE'%" + statusID + "%'";
            } else {
                query += " `appointment_status_id`LIKE'%%'";
            }

            String fromDate = "";
            String toDate = "";
            if (fromdatePicker.isDateSelected()) {
                fromDate = String.valueOf(fromdatePicker.getSelectedDate());
                query += " AND `appointment`.`date` > '" + fromDate + "'";
            }
            if (todatePicker.isDateSelected()) {
                toDate = String.valueOf(todatePicker.getSelectedDate());
                query += " AND `appointment`.`date` < '" + toDate + "'";
            }

            query += " ORDER BY `appointment`.`date` ASC ";

            ResultSet resultSet = MySqlConnection.executeSearch(query);

            DefaultTableModel dtm = (DefaultTableModel) jAppointmentTable.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("vehicle_number"));
                vector.add(resultSet.getString("services.name"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                vector.add(dateFormat.format(dateFormat.parse(resultSet.getString("appointment.date"))));
                vector.add(resultSet.getString("note"));
                vector.add(resultSet.getString("appointment_status.status"));

                dtm.addRow(vector);
            }

            jLabel5.setText(String.valueOf(row));
            jAppointmentTable.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Error while loadAppointments() : " + e.getMessage());
        }
    }

    public JasperPrint makeReport() {

        String dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa").format(new Date());

        String headerImg;
        try {
            InputStream s = this.getClass().getResourceAsStream("/resources/reports/AppointmentReport.jasper");
            String img = new File(this.getClass().getResource("/resources/reports/dazzle_auto_tp.png").getFile()).getAbsolutePath();

            headerImg = img.replace("\\", "/");

            HashMap<String, Object> params = new HashMap<>();
            params.put("img", headerImg);
            params.put("status", String.valueOf(jStatusComboBox.getSelectedItem()));
            params.put("count", jLabel5.getText());
            params.put("employee", LoginModel.getFirstName() + " " + LoginModel.getLastName());
            params.put("reportDate", dateTime);

            if (fromdatePicker.isDateSelected()) {
                params.put("fromDate", String.valueOf(fromdatePicker.getSelectedDate()));
            }else{
                params.put("fromDate", "--/--/----");
            }
            if (todatePicker.isDateSelected()) {
                params.put("toDate", String.valueOf(todatePicker.getSelectedDate()));
            }else{
                params.put("toDate", "--/--/----");
            }

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jAppointmentTable.getModel());

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

        fromdatePicker = new raven.datetime.component.date.DatePicker();
        todatePicker = new raven.datetime.component.date.DatePicker();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jAppointmentTable = new javax.swing.JTable();
        jStatusComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jFromDateField = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jToDateField = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        printReportb = new javax.swing.JButton();
        viewReportb = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1100, 610));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1100, 610));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/icons8-reports-30.png"))); // NOI18N
        jLabel1.setText("  Appointment Report");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/back-35.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jAppointmentTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jAppointmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Appointment No.", "Vehicle", "Service", "Service Date", "Note ", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jAppointmentTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jAppointmentTable.setFocusable(false);
        jAppointmentTable.setRowHeight(40);
        jAppointmentTable.getTableHeader().setReorderingAllowed(false);
        jAppointmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jAppointmentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jAppointmentTable);

        jStatusComboBox.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jStatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Car", "Van" }));
        jStatusComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jStatusComboBox.setFocusable(false);
        jStatusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStatusComboBoxActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/filter-30.png"))); // NOI18N

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

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel3.setText("To :");

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

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel4.setText("Appointments From :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("-");

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

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 0));
        jLabel5.setText("00");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setText("Num. Of Appointments  :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1065, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFromDateField)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jToDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jStatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(325, 325, 325)
                        .addComponent(viewReportb, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(printReportb, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFromDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jStatusComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(printReportb, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewReportb, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

    private void jAppointmentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAppointmentTableMouseClicked

    }//GEN-LAST:event_jAppointmentTableMouseClicked

    private void jStatusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStatusComboBoxActionPerformed
        loadAppointments();
    }//GEN-LAST:event_jStatusComboBoxActionPerformed

    private void jFromDateFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFromDateFieldPropertyChange
        if (fromdatePicker.isDateSelected()) {
            loadAppointments();
        }
    }//GEN-LAST:event_jFromDateFieldPropertyChange

    private void jToDateFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jToDateFieldPropertyChange
        if (todatePicker.isDateSelected()) {
            loadAppointments();
        }
    }//GEN-LAST:event_jToDateFieldPropertyChange

    private void jFromDateFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFromDateFieldMouseExited
        loadAppointments();
    }//GEN-LAST:event_jFromDateFieldMouseExited

    private void jToDateFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToDateFieldMouseExited
        loadAppointments();
    }//GEN-LAST:event_jToDateFieldMouseExited

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.datetime.component.date.DatePicker fromdatePicker;
    private javax.swing.JTable jAppointmentTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField jFromDateField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> jStatusComboBox;
    private javax.swing.JFormattedTextField jToDateField;
    private javax.swing.JButton printReportb;
    private raven.datetime.component.date.DatePicker todatePicker;
    private javax.swing.JButton viewReportb;
    // End of variables declaration//GEN-END:variables
}
