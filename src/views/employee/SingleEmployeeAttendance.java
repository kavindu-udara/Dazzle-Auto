/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views.employee;

import controllers.AttendanceDateController;
import controllers.AttendanceStatusController;
import controllers.EmployeeAttendanceController;
import controllers.EmployeeController;
import includes.LoggerConfig;
import includes.TimestampsGenerator;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import models.LoginModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author kavindu
 */
public class SingleEmployeeAttendance extends javax.swing.JFrame {

    private String empId;
    private static Logger logger = LoggerConfig.getLogger();

    /**
     * Creates new form SingleEmployeeAttendance
     */
    public SingleEmployeeAttendance(java.awt.Frame parent, boolean modal, String empId) {
        //super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/icon2.png")));

        this.empId = empId;
        loadTableData();
        SingleEmployeeTableRender();

        fromDatePicker.setEditor(jFromTextField);
        toDatePicker.setEditor(jToTextField);
    }

    private void loadTableData() {
        employeeIdValueLabel.setText(empId);
        DefaultTableModel tableModel = (DefaultTableModel) attendanceTable.getModel();
        tableModel.setRowCount(0);

        try {

            ResultSet attendanceResultSet = new EmployeeAttendanceController().showByEmployeeId(empId);

            while (attendanceResultSet.next()) {

                Vector vector = new Vector();
                vector.add(attendanceResultSet.getString("id"));

                try {
                    ResultSet employeeResultSet = getEmployeeResultSet(attendanceResultSet.getString("employee_id"));
                    if (employeeResultSet.next()) {
                        employeeNameValueLabel.setText(employeeResultSet.getString("first_name") + " " + employeeResultSet.getString("last_name"));
                    } else {
                        vector.add("-");
                    }
                } catch (Exception ex3) {
                    ex3.printStackTrace();
                    logger.severe("Error while getting employee : " + ex3.getMessage());
                }

                if (attendanceResultSet.getString("checkin") != null) {
                    vector.add(attendanceResultSet.getString("checkin"));
                } else {
                    vector.add("-");
                }

                if (attendanceResultSet.getString("checkout") != null) {
                    vector.add(attendanceResultSet.getString("checkout"));
                } else {
                    vector.add("-");
                }

                try {
                    ResultSet dateResultSet = getDateResultSet(attendanceResultSet.getInt("attendance_date_id"));
                    if (dateResultSet.next()) {
                        vector.add(dateResultSet.getString("date"));
                    } else {
                        vector.add("-");
                    }
                } catch (Exception ex4) {
                    ex4.printStackTrace();
                    logger.severe("Error while loading attendance date : " + ex4.getMessage());
                }

                try {
                    ResultSet statusResultSet = getStatusResultSet(attendanceResultSet.getInt("attendance_status_id"));
                    if (statusResultSet.next()) {
                        vector.add(statusResultSet.getString("status"));
                    }
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                    logger.severe("Error while getting employee attendance status : " + ex2.getMessage());
                }

                if (attendanceResultSet.getString("checkin") != null && attendanceResultSet.getString("checkout") != null) {
                    int workingHrs = Integer.parseInt(attendanceResultSet.getString("checkout").split(":")[0]) - Integer.parseInt(attendanceResultSet.getString("checkin").split(":")[0]);
                    vector.add(String.valueOf(workingHrs));
                } else {
                    vector.add("-");
                }
                tableModel.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        attendanceTable.setModel(tableModel);
    }

    private ResultSet getEmployeeResultSet(String id) throws Exception {
        return new EmployeeController().show(id);
    }

    private ResultSet getDateResultSet(int id) throws Exception {
        return new AttendanceDateController().show(id);
    }

    private ResultSet getStatusResultSet(int id) throws Exception {
        return new AttendanceStatusController().show(id);
    }

    public JasperPrint makeReport() {

        String headerImg;
        try {
            InputStream s = this.getClass().getResourceAsStream("/resources/reports/DazzleAutoSingleEmployeeAttendaceReport.jasper");
            String img = new File(this.getClass().getResource("/resources/reports/dazzle_auto_tp.png").getFile()).getAbsolutePath();

            headerImg = img.replace("\\", "/");

            HashMap<String, Object> params = new HashMap<>();
            params.put("imageParam", headerImg);
            params.put("timeStampParam", String.valueOf(TimestampsGenerator.getFormattedDateTime()));
            params.put("employeeIdParm", empId);
            params.put("EmployeeNameParam", String.valueOf(employeeNameValueLabel.getText()));

            JRTableModelDataSource dataSource = new JRTableModelDataSource(attendanceTable.getModel());

            JasperPrint report = JasperFillManager.fillReport(s, params, dataSource);

            return report;

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while makeReport() : " + e.getMessage());
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fromDatePicker = new raven.datetime.component.date.DatePicker();
        toDatePicker = new raven.datetime.component.date.DatePicker();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        attendanceTable = new javax.swing.JTable();
        employeeIdValueLabel = new javax.swing.JLabel();
        employeeNameValueLabel = new javax.swing.JLabel();
        printButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jFromTextField = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jToTextField = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Employee Attendance");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(736, 534));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel1.setText("Employee Name");

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel2.setText("Employee Id");

        attendanceTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        attendanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Checkin", "Checkout", "Date", "Status", "Working Hrs"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        attendanceTable.setFocusable(false);
        attendanceTable.setRowHeight(30);
        jScrollPane1.setViewportView(attendanceTable);

        employeeIdValueLabel.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        employeeIdValueLabel.setText("employeeIdValueLabel");

        employeeNameValueLabel.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        employeeNameValueLabel.setText("employeeNameValueLabel");

        printButton.setBackground(new java.awt.Color(0, 102, 0));
        printButton.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        printButton.setForeground(new java.awt.Color(255, 255, 255));
        printButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/print-30.png"))); // NOI18N
        printButton.setText("PRINT");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setText(":");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setText(":");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("From");

        jFromTextField.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jFromTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jFromTextFieldMouseExited(evt);
            }
        });
        jFromTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFromTextFieldPropertyChange(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("to");

        jToTextField.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jToTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToTextFieldMouseExited(evt);
            }
        });
        jToTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jToTextFieldPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2)
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(employeeIdValueLabel))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(employeeNameValueLabel))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(590, 590, 590)
                        .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(employeeIdValueLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(employeeNameValueLabel)
                    .addComponent(jLabel4))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SingleEmployeeTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = attendanceTable.getTableHeader();

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
            attendanceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here:
        try {
            JasperPrint report = makeReport();
            JasperViewer.viewReport(report, false);
            logger.info("Attendance Report Viewed By : " + LoginModel.getEmployeeId());
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while view Report : " + e.getMessage());
        }
    }//GEN-LAST:event_printButtonActionPerformed

    private void jFromTextFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFromTextFieldMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jFromTextFieldMouseExited

    private void jToTextFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToTextFieldMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jToTextFieldMouseExited

    private void jFromTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFromTextFieldPropertyChange
        // TODO add your handling code here:
        sortTableDataByDate();
    }//GEN-LAST:event_jFromTextFieldPropertyChange

    private void jToTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jToTextFieldPropertyChange
        // TODO add your handling code here:
        sortTableDataByDate();
    }//GEN-LAST:event_jToTextFieldPropertyChange

    private void sortTableDataByDate(){
        if (!fromDatePicker.isDateSelected() && !toDatePicker.isDateSelected()) {
            loadTableData();
        } else {
            String query = "SELECT * FROM `emp_attendance` INNER JOIN `attendance_date` ON `attendance_date`.`id`=`emp_attendance`.`attendance_date_id` WHERE `emp_attendance`.`employee_id`='" + empId + "' ";

            String fromDate = "";
            String toDate = "";
            if (fromDatePicker.isDateSelected()) {
                fromDate = String.valueOf(fromDatePicker.getSelectedDate());
                query += " AND `attendance_date`.`date` > '" + fromDate + "'";
            }
            if (toDatePicker.isDateSelected()) {
                toDate = String.valueOf(toDatePicker.getSelectedDate());
                query += " AND `attendance_date`.`date` < '" + toDate + "'";
            }
            query += " ORDER BY `attendance_date`.`date` ASC ";

            DefaultTableModel tableModel = (DefaultTableModel) attendanceTable.getModel();
            tableModel.setRowCount(0);

            try {

                ResultSet attendanceResultSet = new EmployeeAttendanceController().showByCustomQuery(query);

                while (attendanceResultSet.next()) {

                    Vector vector = new Vector();
                    vector.add(attendanceResultSet.getString("id"));

                    try {
                        ResultSet employeeResultSet = getEmployeeResultSet(attendanceResultSet.getString("employee_id"));
                        if (employeeResultSet.next()) {
                            employeeNameValueLabel.setText(employeeResultSet.getString("first_name") + " " + employeeResultSet.getString("last_name"));
                        } else {
                            vector.add("-");
                        }
                    } catch (Exception ex3) {
                        ex3.printStackTrace();
                        logger.severe("Error while getting employee : " + ex3.getMessage());
                    }

                    if (attendanceResultSet.getString("checkin") != null) {
                        vector.add(attendanceResultSet.getString("checkin"));
                    } else {
                        vector.add("-");
                    }

                    if (attendanceResultSet.getString("checkout") != null) {
                        vector.add(attendanceResultSet.getString("checkout"));
                    } else {
                        vector.add("-");
                    }

                    try {
                        ResultSet dateResultSet = getDateResultSet(attendanceResultSet.getInt("attendance_date_id"));
                        if (dateResultSet.next()) {
                            vector.add(dateResultSet.getString("date"));
                        } else {
                            vector.add("-");
                        }
                    } catch (Exception ex4) {
                        ex4.printStackTrace();
                        logger.severe("Error while loading attendance date : " + ex4.getMessage());
                    }

                    try {
                        ResultSet statusResultSet = getStatusResultSet(attendanceResultSet.getInt("attendance_status_id"));
                        if (statusResultSet.next()) {
                            vector.add(statusResultSet.getString("status"));
                        }
                    } catch (Exception ex2) {
                        ex2.printStackTrace();
                        logger.severe("Error while getting employee attendance status : " + ex2.getMessage());
                    }

                    if (attendanceResultSet.getString("checkin") != null && attendanceResultSet.getString("checkout") != null) {
                        int workingHrs = Integer.parseInt(attendanceResultSet.getString("checkout").split(":")[0]) - Integer.parseInt(attendanceResultSet.getString("checkin").split(":")[0]);
                        vector.add(String.valueOf(workingHrs));
                    } else {
                        vector.add("-");
                    }
                    tableModel.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            attendanceTable.setModel(tableModel);

        }

    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable attendanceTable;
    private javax.swing.JLabel employeeIdValueLabel;
    private javax.swing.JLabel employeeNameValueLabel;
    private raven.datetime.component.date.DatePicker fromDatePicker;
    private javax.swing.JFormattedTextField jFromTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField jToTextField;
    private javax.swing.JButton printButton;
    private raven.datetime.component.date.DatePicker toDatePicker;
    // End of variables declaration//GEN-END:variables
}
