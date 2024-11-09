/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.financeAndHr;

import controllers.ServiceInvoiceController;
import controllers.ShopInoviceController;
import includes.LoggerConfig;
import includes.OnlyNumbersDocumentFilter;
import java.sql.ResultSet;
import java.util.logging.Logger;
import javax.management.StringValueExp;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author Dumindu
 */
public class IncomeTaxCalculator extends javax.swing.JPanel {

    private static final Logger logger = LoggerConfig.getLogger();

    public IncomeTaxCalculator() {
        initComponents();
        setDocumentFilters();
        loadStationIncome();
        loadShopIncome();

        monthComboBox.setEnabled(false);

        ServiceStationIncomeField.setEditable(false);
        ShopIncomeField.setEditable(false);
        EmployeeSalaryField.setEditable(false);
        SupplierPaymentField.setEditable(false);
        TaxableIncomeField.setEditable(false);
        TaxRateField.setEditable(false);
        FinalTaxField.setEditable(false);

        String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };

        for (String month : months) {
            monthComboBox.addItem(month);
        }

    }

    private void setDocumentFilters() {
        ((AbstractDocument) ServiceStationIncomeField.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) ShopIncomeField.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) EmployeeSalaryField.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) SupplierPaymentField.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) BillsField.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) OtherField.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) TaxableIncomeField.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) TaxRateField.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) FinalTaxField.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());

    }

    private void loadShopIncome() {
        try {
            int month = monthComboBox.getSelectedIndex() + 1; 
            int year = java.time.Year.now().getValue(); 

            ShopInoviceController shopInvoiceController = new ShopInoviceController();
            ResultSet resultSet = null;

            if (monthlyRadioButton.isSelected()) {
                resultSet = shopInvoiceController.getMonthlyTotal(month, year);
            } else if (yearlyRadioButton.isSelected()) {
                resultSet = shopInvoiceController.getYearlyTotal(year);
            }

            if (resultSet != null && resultSet.next()) {
                int totalIncome = resultSet.getInt("total_income");
                ShopIncomeField.setText(String.valueOf(totalIncome));
                //double totalIncome = resultSet.getDouble("total_income");
                //ShopIncomeField.setText(String.format("%.2f", totalIncome)); 
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading shop income: " + e.getMessage());
        }
    }

    private void loadStationIncome() {
        try {
            int month = monthComboBox.getSelectedIndex() + 1;
            int year = java.time.Year.now().getValue();

            ServiceInvoiceController serviceInvoiceController = new ServiceInvoiceController();
            ResultSet resultSet = null;

            if (monthlyRadioButton.isSelected()) {
                resultSet = serviceInvoiceController.getMonthlyTotal(month, year);
            } else if (yearlyRadioButton.isSelected()) {
                resultSet = serviceInvoiceController.getYearlyTotal(year);
            }

            if (resultSet != null && resultSet.next()) {
                int totalIncome = resultSet.getInt("total_income");
                ServiceStationIncomeField.setText(String.valueOf(totalIncome));

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading station income: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField9 = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        yearlyRadioButton = new javax.swing.JRadioButton();
        monthlyRadioButton = new javax.swing.JRadioButton();
        monthComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        TaxableIncomeField = new javax.swing.JTextField();
        TaxRateField = new javax.swing.JTextField();
        FinalTaxField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        OtherField = new javax.swing.JTextField();
        ServiceStationIncomeField = new javax.swing.JTextField();
        ShopIncomeField = new javax.swing.JTextField();
        EmployeeSalaryField = new javax.swing.JTextField();
        SupplierPaymentField = new javax.swing.JTextField();
        BillsField = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(1089, 579));
        setPreferredSize(new java.awt.Dimension(1089, 579));

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(206, 235, 239));

        buttonGroup1.add(yearlyRadioButton);
        yearlyRadioButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        yearlyRadioButton.setText("Yealy");
        yearlyRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearlyRadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(monthlyRadioButton);
        monthlyRadioButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        monthlyRadioButton.setText("Monthly");
        monthlyRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthlyRadioButtonActionPerformed(evt);
            }
        });

        monthComboBox.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        monthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(yearlyRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(monthlyRadioButton)
                .addGap(42, 42, 42)
                .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(650, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearlyRadioButton)
                    .addComponent(monthlyRadioButton)
                    .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setText("other");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, -1, 20));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel4.setText("Expences");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setText("Gross Income");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, 20));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setText("Service Station Income");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, -1, 20));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel9.setText("Spare Parts Shop Income");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, -1, 20));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel10.setText("Employee Salaries");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, -1, 20));

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel11.setText("Bills");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, -1, 20));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel12.setText("Supplier Payments");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, -1, 20));

        jPanel3.setBackground(new java.awt.Color(234, 234, 234));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel5.setText("Taxable Income");

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel6.setText("Tax Rate");

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setText("Final Tax Owed");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        TaxableIncomeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TaxableIncomeFieldActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(33, 43, 108));
        jButton1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("PRINT");

        jButton2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 0));
        jButton2.setText("CLEAR ALL");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TaxableIncomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TaxRateField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(35, 35, 35)
                                    .addComponent(FinalTaxField, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TaxableIncomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TaxRateField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(FinalTaxField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 480, 480));
        jPanel1.add(OtherField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 460, 240, 30));

        ServiceStationIncomeField.setFont(new java.awt.Font("Roboto", 3, 18)); // NOI18N
        ServiceStationIncomeField.setForeground(new java.awt.Color(33, 43, 108));
        jPanel1.add(ServiceStationIncomeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 240, 30));

        ShopIncomeField.setFont(new java.awt.Font("Roboto", 3, 18)); // NOI18N
        ShopIncomeField.setForeground(new java.awt.Color(33, 43, 108));
        jPanel1.add(ShopIncomeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 240, 30));
        jPanel1.add(EmployeeSalaryField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 240, 30));
        jPanel1.add(SupplierPaymentField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 240, 30));
        jPanel1.add(BillsField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, 240, 30));

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

    private void TaxableIncomeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TaxableIncomeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TaxableIncomeFieldActionPerformed

    private void monthlyRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthlyRadioButtonActionPerformed
        // TODO add your handling code here:
        monthComboBox.setEnabled(true);
        loadStationIncome();
        loadShopIncome();
    }//GEN-LAST:event_monthlyRadioButtonActionPerformed

    private void yearlyRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearlyRadioButtonActionPerformed
        // TODO add your handling code here:
        monthComboBox.setEnabled(false);
        loadStationIncome();
        loadShopIncome();
    }//GEN-LAST:event_yearlyRadioButtonActionPerformed

    private void monthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthComboBoxActionPerformed
        // TODO add your handling code here:
        loadStationIncome();
        loadShopIncome();
    }//GEN-LAST:event_monthComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BillsField;
    private javax.swing.JTextField EmployeeSalaryField;
    private javax.swing.JTextField FinalTaxField;
    private javax.swing.JTextField OtherField;
    private javax.swing.JTextField ServiceStationIncomeField;
    private javax.swing.JTextField ShopIncomeField;
    private javax.swing.JTextField SupplierPaymentField;
    private javax.swing.JTextField TaxRateField;
    private javax.swing.JTextField TaxableIncomeField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JComboBox<String> monthComboBox;
    private javax.swing.JRadioButton monthlyRadioButton;
    private javax.swing.JRadioButton yearlyRadioButton;
    // End of variables declaration//GEN-END:variables
}
