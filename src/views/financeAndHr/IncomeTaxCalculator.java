/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.financeAndHr;

import controllers.GrnItemsController;
import controllers.EmployeeSalaryController;
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
import raven.datetime.component.date.DatePicker;

/**
 *
 * @author Dumindu
 */
public class IncomeTaxCalculator extends javax.swing.JPanel {

    private static final Logger logger = LoggerConfig.getLogger();

    public IncomeTaxCalculator() {
        initComponents();
        setDocumentFilters();
        MethodCalling();

        monthComboBox.setEnabled(false);
        jYearChooser1.setEnabled(false);
        
        ServiceStationIncomeField.setEditable(false);
        ServiceStationIncomeField.setFocusable(false);
        GrossIncomeField.setEditable(false);
        GrossIncomeField.setFocusable(false);
        ShopIncomeField.setEditable(false);
        ShopIncomeField.setFocusable(false);
        EmployeeSalaryField.setEditable(false);
        EmployeeSalaryField.setFocusable(false);
        SupplierPaymentField.setEditable(false);
        SupplierPaymentField.setFocusable(false);
        TaxableIncomeField.setEditable(false);
        TaxableIncomeField.setFocusable(false);
        TaxRateField.setEditable(false);
        TaxRateField.setFocusable(false);
        FinalTaxField.setEditable(false);
        FinalTaxField.setFocusable(false);
        TotalExpencesField.setEditable(false);
        TotalExpencesField.setFocusable(false);

        TaxRateField.setText("30%");

    }

    private void setDocumentFilters() {

        ((AbstractDocument) BillsField.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) OtherField.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());

    }

    public void MethodCalling() {
        loadStationIncome();
        loadShopIncome();
        calculateGrossIncome();
        loadSupplierPayments();
        totalExpences();
        taxableIncome();
        FinalTaxCalculation();
        loadSalaries();
        totalExpences();

    }

    private void loadShopIncome() {
        try {
            int month = monthComboBox.getSelectedIndex() + 1;
            int year = jYearChooser1.getYear();

            ShopInoviceController shopInvoiceController = new ShopInoviceController();
            ResultSet resultSet = null;

            if (monthlyRadioButton.isSelected()) {
                resultSet = shopInvoiceController.getMonthlyTotal(month, year);
            } else if (yearlyRadioButton.isSelected()) {
                resultSet = shopInvoiceController.getYearlyTotal(year);
            }

            if (resultSet != null && resultSet.next()) {
                double totalIncome = resultSet.getDouble("total_income");
                ShopIncomeField.setText(String.format("%.2f", totalIncome));
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading shop income: " + e.getMessage());
        }
    }

    private void loadStationIncome() {
        try {
            int month = monthComboBox.getSelectedIndex() + 1;
            int year = jYearChooser1.getYear();

            ServiceInvoiceController serviceInvoiceController = new ServiceInvoiceController();
            ResultSet resultSet = null;

            if (monthlyRadioButton.isSelected()) {
                resultSet = serviceInvoiceController.getMonthlyTotal(month, year);
            } else if (yearlyRadioButton.isSelected()) {
                resultSet = serviceInvoiceController.getYearlyTotal(year);
            }

            if (resultSet != null && resultSet.next()) {
                double totalIncome = resultSet.getDouble("total_income");
                ServiceStationIncomeField.setText(String.format("%.2f", totalIncome));

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading station income: " + e.getMessage());
        }
    }

    private double grossIncome = 0;
    private double totalExpences = 0;
    private double taxableIncome = 0;
    private double FinalTax = 0;

    private void calculateGrossIncome() {
        grossIncome = 0;

        String service = ServiceStationIncomeField.getText().isEmpty() ? "0" : ServiceStationIncomeField.getText();
        String shop = ShopIncomeField.getText().isEmpty() ? "0" : ShopIncomeField.getText();

        try {
            double incomeCal = Double.parseDouble(service) + Double.parseDouble(shop);
            grossIncome += incomeCal;

            GrossIncomeField.setText(String.format("%.2f", grossIncome));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void loadSalaries() {
        
        try {
            int month = monthComboBox.getSelectedIndex() + 1;
            int year = jYearChooser1.getYear();

            EmployeeSalaryController salaryController = new EmployeeSalaryController();
            ResultSet resultSet = null;

            if (monthlyRadioButton.isSelected()) {
                resultSet = salaryController.getMonthlyTotal(month, year);
            } else if (yearlyRadioButton.isSelected()) {
                resultSet = salaryController.getYearlyTotal(year);
            }

            if (resultSet != null && resultSet.next()) {
                double totalSalary = resultSet.getDouble("total_salary");
                EmployeeSalaryField.setText(String.format("%.2f", totalSalary));

            }

           
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading Salary Payments: " + e.getMessage());
        }
         
    }

    private void loadSupplierPayments() {
        try {
            int month = monthComboBox.getSelectedIndex() + 1;
            int year = jYearChooser1.getYear();

            GrnItemsController grnItemsController = new GrnItemsController();
            ResultSet resultSet = null;

            if (monthlyRadioButton.isSelected()) {
                resultSet = grnItemsController.getMonthlyTotal(month, year);
            } else if (yearlyRadioButton.isSelected()) {
                resultSet = grnItemsController.getYearlyTotal(year);
            }

            if (resultSet != null && resultSet.next()) {
                double totalIncome = resultSet.getDouble("total_income");
                SupplierPaymentField.setText(String.format("%.2f", totalIncome));

            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading supplier Payments: " + e.getMessage());
        }
    }

    private void totalExpences() {
        
        totalExpences = 0;

        String salary = EmployeeSalaryField.getText().isEmpty() ? "0" : EmployeeSalaryField.getText();
        String supplierPayments = SupplierPaymentField.getText().isEmpty() ? "0" : SupplierPaymentField.getText();
        String bills = BillsField.getText().isEmpty() ? "0" : BillsField.getText();
        String other = OtherField.getText().isEmpty() ? "0" : OtherField.getText();

        try {
            double expencesCal = Double.parseDouble(salary)+ Double.parseDouble(supplierPayments)
                    + Double.parseDouble(bills) + Double.parseDouble(other);

            totalExpences += expencesCal;
            TotalExpencesField.setText(String.format("%.2f", totalExpences));
        } catch (NumberFormatException e) {
            e.printStackTrace();

        }
    }

    private void taxableIncome() {
        taxableIncome = 0;

        String grossIncome = GrossIncomeField.getText().isEmpty() ? "0" : GrossIncomeField.getText();
        String toatalExpences = TotalExpencesField.getText().isEmpty() ? "0" : TotalExpencesField.getText();

        try {
            double TaxableIncomeCal = Double.parseDouble(grossIncome) - Double.parseDouble(toatalExpences);
            taxableIncome += TaxableIncomeCal;

            if (taxableIncome < 0) {
                TaxableIncomeField.setText(String.valueOf("Insuficiant Income"));
            } else {
                TaxableIncomeField.setText(String.format("%.2f", taxableIncome));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void FinalTaxCalculation() {
        FinalTax = 0;

        FinalTaxField.setText("");
        if (!TaxableIncomeField.getText().equals("Insuficiant Income")) {
            String taxableIncome = TaxableIncomeField.getText().isEmpty() ? "0" : TaxableIncomeField.getText();

            try {
                double finalTaxCal = Double.parseDouble(taxableIncome) * 0.3;
                FinalTax += finalTaxCal;

                FinalTaxField.setText(String.format("%.2f", FinalTax));

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
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
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
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
        jLabel13 = new javax.swing.JLabel();
        GrossIncomeField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        TotalExpencesField = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();

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
        monthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        monthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthComboBoxActionPerformed(evt);
            }
        });

        jYearChooser1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(631, Short.MAX_VALUE)
                .addComponent(yearlyRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(monthlyRadioButton)
                .addGap(18, 18, 18)
                .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(yearlyRadioButton)
                        .addComponent(monthlyRadioButton)
                        .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 70));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setText("other");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, -1, 20));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel4.setText("Expences");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setText("Gross Income");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, 20));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setText("Service Station Income");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, 20));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel9.setText("Spare Parts Shop Income");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, -1, 20));

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

        TaxableIncomeField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        TaxableIncomeField.setForeground(new java.awt.Color(0, 204, 0));
        TaxableIncomeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TaxableIncomeFieldActionPerformed(evt);
            }
        });

        TaxRateField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        TaxRateField.setForeground(new java.awt.Color(153, 153, 153));

        FinalTaxField.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 480, 510));

        OtherField.setFont(new java.awt.Font("Roboto", 3, 18)); // NOI18N
        OtherField.setForeground(new java.awt.Color(255, 0, 0));
        OtherField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                OtherFieldKeyReleased(evt);
            }
        });
        jPanel1.add(OtherField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 460, 240, 30));

        ServiceStationIncomeField.setFont(new java.awt.Font("Roboto", 3, 18)); // NOI18N
        ServiceStationIncomeField.setForeground(new java.awt.Color(33, 43, 108));
        jPanel1.add(ServiceStationIncomeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 240, 30));

        ShopIncomeField.setFont(new java.awt.Font("Roboto", 3, 18)); // NOI18N
        ShopIncomeField.setForeground(new java.awt.Color(33, 43, 108));
        jPanel1.add(ShopIncomeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 240, 30));

        EmployeeSalaryField.setFont(new java.awt.Font("Roboto", 3, 18)); // NOI18N
        EmployeeSalaryField.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(EmployeeSalaryField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 310, 240, 30));

        SupplierPaymentField.setFont(new java.awt.Font("Roboto", 3, 18)); // NOI18N
        SupplierPaymentField.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(SupplierPaymentField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 240, 30));

        BillsField.setFont(new java.awt.Font("Roboto", 3, 18)); // NOI18N
        BillsField.setForeground(new java.awt.Color(255, 0, 0));
        BillsField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BillsFieldKeyReleased(evt);
            }
        });
        jPanel1.add(BillsField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, 240, 30));

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel13.setText("Gross Income");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, -1, 20));

        GrossIncomeField.setFont(new java.awt.Font("Roboto", 3, 18)); // NOI18N
        GrossIncomeField.setForeground(new java.awt.Color(33, 43, 108));
        jPanel1.add(GrossIncomeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 240, 30));

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel14.setText("Total Expences");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 520, -1, 20));

        TotalExpencesField.setFont(new java.awt.Font("Roboto", 3, 18)); // NOI18N
        TotalExpencesField.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(TotalExpencesField, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, 240, 30));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, -1));

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
        jYearChooser1.setEnabled(false);
        MethodCalling();
    }//GEN-LAST:event_monthlyRadioButtonActionPerformed

    private void yearlyRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearlyRadioButtonActionPerformed
        // TODO add your handling code here:
        monthComboBox.setEnabled(false);
        jYearChooser1.setEnabled(true);
        MethodCalling();
    }//GEN-LAST:event_yearlyRadioButtonActionPerformed

    private void monthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthComboBoxActionPerformed
        // TODO add your handling code here:
        MethodCalling();

    }//GEN-LAST:event_monthComboBoxActionPerformed

    private void BillsFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BillsFieldKeyReleased
        // TODO add your handling code here:
        totalExpences();
        taxableIncome();
        FinalTaxCalculation();

    }//GEN-LAST:event_BillsFieldKeyReleased

    private void OtherFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OtherFieldKeyReleased
        // TODO add your handling code here:
        totalExpences();
        taxableIncome();
        FinalTaxCalculation();
    }//GEN-LAST:event_OtherFieldKeyReleased

    private void jYearChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jYearChooser1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jYearChooser1MouseClicked

    private void jYearChooser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jYearChooser1KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jYearChooser1KeyPressed

    private void jYearChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jYearChooser1PropertyChange
        // TODO add your handling code here:
        MethodCalling();
    }//GEN-LAST:event_jYearChooser1PropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BillsField;
    private javax.swing.JTextField EmployeeSalaryField;
    private javax.swing.JTextField FinalTaxField;
    private javax.swing.JTextField GrossIncomeField;
    private javax.swing.JTextField OtherField;
    private javax.swing.JTextField ServiceStationIncomeField;
    private javax.swing.JTextField ShopIncomeField;
    private javax.swing.JTextField SupplierPaymentField;
    private javax.swing.JTextField TaxRateField;
    private javax.swing.JTextField TaxableIncomeField;
    private javax.swing.JTextField TotalExpencesField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField9;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JComboBox<String> monthComboBox;
    private javax.swing.JRadioButton monthlyRadioButton;
    private javax.swing.JRadioButton yearlyRadioButton;
    // End of variables declaration//GEN-END:variables
}
