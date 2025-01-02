/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.reports;

import controllers.EmployeeSalaryController;
import controllers.GrnItemsController;
import java.awt.BorderLayout;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.SwingUtilities;
import models.LoginModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import includes.LoggerConfig;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.DateFormatSymbols;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import views.dashboard.Dashboard;

/**
 *
 * @author Dinuka
 */
public class ExpencesReportPanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();
    Dashboard dashboard = null;

    public ExpencesReportPanel(Dashboard dashboard) {
        initComponents();
        ExpensesTableRender();
        this.dashboard = dashboard;

        yearlyRadioButton.setSelected(true);
        monthComboBox.setEnabled(false);
        jYearChooser1.setEnabled(true);

        // Load yearly data by default
        loadExpenses();

    }

    private void loadExpenses() {
        try {
            DefaultTableModel model = (DefaultTableModel) ExpensesTable.getModel();
            model.setRowCount(0);

            double grandTotal = 0.0;

            GrnItemsController grnItemsController = new GrnItemsController();
            EmployeeSalaryController salaryController = new EmployeeSalaryController();

            if (monthlyRadioButton.isSelected()) {
                int month = monthComboBox.getSelectedIndex() + 1;
                int year = jYearChooser1.getYear();

                ResultSet incomeResultSet = grnItemsController.getMonthlyTotal(month, year);
                ResultSet salaryResultSet = salaryController.getMonthlyTotal(month, year);

                double totalIncome = incomeResultSet != null && incomeResultSet.next()
                        ? incomeResultSet.getDouble("total_income")
                        : 0.00;

                double totalSalary = salaryResultSet != null && salaryResultSet.next()
                        ? salaryResultSet.getDouble("total_salary")
                        : 0.00;

                double total = totalSalary + totalIncome;
                grandTotal += total;

                String monthName = new DateFormatSymbols().getMonths()[month - 1];
                model.addRow(new Object[]{String.valueOf(totalSalary), String.valueOf(totalIncome), String.valueOf(total), monthName});

            } else if (yearlyRadioButton.isSelected()) {
                int year = jYearChooser1.getYear();

                for (int month = 1; month <= 12; month++) {
                    ResultSet incomeResultSet = grnItemsController.getMonthlyTotal(month, year);
                    ResultSet salaryResultSet = salaryController.getMonthlyTotal(month, year);

                    double totalIncome = incomeResultSet != null && incomeResultSet.next()
                            ? incomeResultSet.getDouble("total_income")
                            : 0.00;

                    double totalSalary = salaryResultSet != null && salaryResultSet.next()
                            ? salaryResultSet.getDouble("total_salary")
                            : 0.00;

                    double total = totalSalary + totalIncome;
                    grandTotal += total;

                    String monthName = new DateFormatSymbols().getMonths()[month - 1];
                    model.addRow(new Object[]{String.valueOf(totalSalary), String.valueOf(totalIncome), String.valueOf(total), monthName});
                }
            }

            jLabel5.setText(String.format("%.2f", grandTotal));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }
    }

    public void ExpensesTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = ExpensesTable.getTableHeader();

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

        for (int i = 0; i < 4; i++) {
            ExpensesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void reloadTable() {
        loadExpenses();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ExpensesTable = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        viewReportb = new javax.swing.JButton();
        printReportb = new javax.swing.JButton();
        monthComboBox = new javax.swing.JComboBox<>();
        monthlyRadioButton = new javax.swing.JRadioButton();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        yearlyRadioButton = new javax.swing.JRadioButton();

        setMinimumSize(new java.awt.Dimension(1100, 610));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/icons8-reports-30.png"))); // NOI18N
        jLabel1.setText("  Expenses Report");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/back-35.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ExpensesTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        ExpensesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee Salaries", "Supplier Payments", "Total", "Month"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ExpensesTable.setRowHeight(30);
        ExpensesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExpensesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ExpensesTable);

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel8.setText("Total Expenses :");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 0));
        jLabel5.setText("00");

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

        monthComboBox.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        monthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        monthComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                monthComboBoxItemStateChanged(evt);
            }
        });
        monthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthComboBoxActionPerformed(evt);
            }
        });

        buttonGroup1.add(monthlyRadioButton);
        monthlyRadioButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        monthlyRadioButton.setText("Monthly");
        monthlyRadioButton.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        monthlyRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthlyRadioButtonActionPerformed(evt);
            }
        });

        jYearChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jYearChooser1MouseClicked(evt);
            }
        });
        jYearChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jYearChooser1PropertyChange(evt);
            }
        });
        jYearChooser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jYearChooser1KeyPressed(evt);
            }
        });

        buttonGroup1.add(yearlyRadioButton);
        yearlyRadioButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        yearlyRadioButton.setText("Yearly");
        yearlyRadioButton.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        yearlyRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearlyRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1060, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(viewReportb)
                        .addGap(22, 22, 22)
                        .addComponent(printReportb)))
                .addGap(16, 16, 16))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(yearlyRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monthlyRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jButton1)
                            .addGap(6, 6, 6)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(monthlyRadioButton)
                        .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(yearlyRadioButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(viewReportb)
                        .addComponent(printReportb)))
                .addGap(22, 22, 22))
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

    public JasperPrint makeReport() {

        String dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa").format(new Date());

        String headerImg;
        try {
            InputStream s = this.getClass().getResourceAsStream("/resources/reports/ExpensesReport.jasper");
            String img = new File(this.getClass().getResource("/resources/reports/dazzle_auto_tp.png").getFile()).getAbsolutePath();

            headerImg = img.replace("\\", "/");

            HashMap<String, Object> params = new HashMap<>();
            params.put("img", headerImg);
            params.put("month", String.valueOf(monthComboBox.getSelectedItem()));
            params.put("year", String.valueOf(jYearChooser1.getYear()));
            params.put("total", jLabel5.getText());
            params.put("employee", LoginModel.getFirstName() + " " + LoginModel.getLastName());
            params.put("reportDate", dateTime);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(ExpensesTable.getModel());
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

            logger.info("Expenses Report Viewed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while viewReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_viewReportbActionPerformed

    private void printReportbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReportbActionPerformed
        try {
            JasperPrint report = makeReport();
            JasperPrintManager.printReport(report, false);

            logger.info("Expenses Report Printed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while printReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_printReportbActionPerformed

    private void monthComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_monthComboBoxItemStateChanged
        // TODO add your handling code here:
        loadExpenses();

    }//GEN-LAST:event_monthComboBoxItemStateChanged

    private void monthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthComboBoxActionPerformed

    private void monthlyRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthlyRadioButtonActionPerformed
        // TODO add your handling code here:
        monthComboBox.setEnabled(true);
        jYearChooser1.setEnabled(false);
        loadExpenses();


    }//GEN-LAST:event_monthlyRadioButtonActionPerformed

    private void jYearChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jYearChooser1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jYearChooser1MouseClicked

    private void jYearChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jYearChooser1PropertyChange
        loadExpenses();
    }//GEN-LAST:event_jYearChooser1PropertyChange

    private void jYearChooser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jYearChooser1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jYearChooser1KeyPressed

    private void yearlyRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearlyRadioButtonActionPerformed
        // TODO add your handling code here:
        monthComboBox.setEnabled(false);
        jYearChooser1.setEnabled(true);
        loadExpenses();
    }//GEN-LAST:event_yearlyRadioButtonActionPerformed

    private void ExpensesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExpensesTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ExpensesTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ExpensesTable;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JComboBox<String> monthComboBox;
    private javax.swing.JRadioButton monthlyRadioButton;
    private javax.swing.JButton printReportb;
    private javax.swing.JButton viewReportb;
    private javax.swing.JRadioButton yearlyRadioButton;
    // End of variables declaration//GEN-END:variables
}
