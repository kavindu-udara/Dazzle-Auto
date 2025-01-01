/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views.employee;

import controllers.EmployeeController;
import controllers.EmployeeSalaryController;
import controllers.MonthsController;
import includes.LoggerConfig;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.InputStream;
import javax.swing.table.DefaultTableModel;
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
import javax.swing.table.JTableHeader;
import models.LoginModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author kavindu
 */
public class SingleEmployeeSallary extends javax.swing.JDialog {

    private static final Logger logger = LoggerConfig.getLogger();
    private HashMap<String, String> monthsHashMap = new HashMap<>();

    private String empId;

    /**
     * Creates new form SingleEmployeeSallary
     */
    public SingleEmployeeSallary(java.awt.Frame parent, boolean modal, String empId) {
        super(parent, modal);
        initComponents();

        this.empId = empId;
        fillEmployeeDetailLabels();
        loadMonthsComboBoxData();
        loadTableData();

    }

    private void fillEmployeeDetailLabels() {
        try (ResultSet employeeResultSet = new EmployeeController().show(empId)) {
            if (employeeResultSet.next()) {
                idValueLabel.setText(empId);
                nameValueLabel.setText(employeeResultSet.getString("first_name") + " " + employeeResultSet.getString("last_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading employee data : " + e.getMessage());
        }
    }

    public JasperPrint makeReport() {

        String headerImg;
        try {
            InputStream s = this.getClass().getResourceAsStream("/resources/reports/SingleEmployeeSallaryy.jasper");
            String img = new File(this.getClass().getResource("/resources/reports/dazzle_auto_tp.png").getFile()).getAbsolutePath();

            headerImg = img.replace("\\", "/");

            HashMap<String, Object> params = new HashMap<>();
            params.put("img", headerImg);

            params.put("employee", LoginModel.getFirstName() + " " + LoginModel.getLastName());

            JRTableModelDataSource dataSource = new JRTableModelDataSource(salaryTable.getModel());

            JasperPrint report = JasperFillManager.fillReport(s, params, dataSource);

            return report;

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while makeReport() : " + e.getMessage());
        }
        return null;
    }

    private void loadMonthsComboBoxData() {
        try (ResultSet monthsResultSet = new MonthsController().show()) {
            Vector vector = new Vector();
            vector.add("All");
            while (monthsResultSet.next()) {
                String month = monthsResultSet.getString("name");
                monthsHashMap.put(month, monthsResultSet.getString("id"));
                vector.add(month);
            }
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            monthsComboBox.setModel(comboBoxModel);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while load months : " + e.getMessage());
        }
    }

    private void loadTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) salaryTable.getModel();
        tableModel.setRowCount(0);

        try {

            String selectedMonth = String.valueOf(monthsComboBox.getSelectedItem());

            ResultSet employeeSalaryResultSet = null;

            if (selectedMonth.equals("All")) {
                // All method not working
                employeeSalaryResultSet = getResultSetByEmpId();
            } else {
                String monthId = monthsHashMap.get(selectedMonth);
                employeeSalaryResultSet = getResultSetByEmpIdAndMonthId(monthId);
            }

//            ResultSet employeeSalaryResultSet = new EmployeeSalaryController().show(empId, monthId);
            while (employeeSalaryResultSet.next()) {
                Vector vector = new Vector();
                vector.add(employeeSalaryResultSet.getString("id"));
                vector.add(employeeSalaryResultSet.getString("date"));
                vector.add(employeeSalaryResultSet.getString("salary"));
                tableModel.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while load table data : " + e.getMessage());
        }

    }

    private ResultSet getResultSetByEmpIdAndMonthId(String monthId) throws Exception {
        ResultSet resultSet = new EmployeeSalaryController().show(empId, monthId);
        return resultSet;
    }

    private ResultSet getResultSetByEmpId() throws Exception {
        ResultSet resultSet = new EmployeeSalaryController().showByEmployeeId(empId);
        return resultSet;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        salaryTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        idValueLabel = new javax.swing.JLabel();
        nameValueLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        monthsComboBox = new javax.swing.JComboBox<>();
        printButton = new javax.swing.JButton();
        printButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Single Employee Salary");

        salaryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Payed Date", "Salary"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(salaryTable);

        jLabel1.setText("Employee ID :");

        jLabel2.setText("Employee Name :");

        idValueLabel.setText("ID");

        nameValueLabel.setText("name");

        jLabel3.setText("Select a month");

        monthsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        monthsComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                monthsComboBoxItemStateChanged(evt);
            }
        });

        printButton.setText("print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        printButton1.setText("Save");
        printButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(35, 35, 35)
                            .addComponent(monthsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameValueLabel)
                            .addComponent(idValueLabel)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(printButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(printButton)
                        .addGap(12, 12, 12))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nameValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(monthsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(printButton)
                    .addComponent(printButton1))
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

    private void monthsComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_monthsComboBoxItemStateChanged
        // TODO add your handling code here:
        loadTableData();
    }//GEN-LAST:event_monthsComboBoxItemStateChanged

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed

        try {
            JasperPrint report = makeReport();
            JasperPrintManager.printReport(report, false);

            logger.info("Single Employee Salary Report Printed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while printReportbActionPerformed : " + e.getMessage());
        }

    }//GEN-LAST:event_printButtonActionPerformed

    private void printButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButton1ActionPerformed

        try {
            JasperPrint report = makeReport();
            JasperViewer.viewReport(report, false);

            logger.info("Single Employee Salary Report Viewed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while viewReportbActionPerformed : " + e.getMessage());
        }


    }//GEN-LAST:event_printButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel idValueLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> monthsComboBox;
    private javax.swing.JLabel nameValueLabel;
    private javax.swing.JButton printButton;
    private javax.swing.JButton printButton1;
    private javax.swing.JTable salaryTable;
    // End of variables declaration//GEN-END:variables
}
