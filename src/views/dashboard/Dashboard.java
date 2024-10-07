/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.dashboard;

import com.formdev.flatlaf.IntelliJTheme;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import views.customer.CustomerJPanel;
import views.employee.StaffJPanel;
import views.finance.FinanceJPanel;
import views.ourServices.ourServicesJPanel;
import views.payment.PaymentsPanel;
import views.reports.ReportsJPanel;
import views.vehicle.VehiclesJPanel;
import views.vehicleServiceAppointment.AppointmnetPanel;

/**
 *
 * @author Dinuka
 */
public class Dashboard extends javax.swing.JFrame {

    public Dashboard() {
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/icon2.png")));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        menuButtonsPanel = new javax.swing.JPanel();
        jStaffButton = new javax.swing.JButton();
        jDashboardButton1 = new javax.swing.JButton();
        jPaymentButton = new javax.swing.JButton();
        jAppointmentsButton = new javax.swing.JButton();
        jCustomerButton = new javax.swing.JButton();
        jOurServicesButton = new javax.swing.JButton();
        jFinanceButton = new javax.swing.JButton();
        jReportsButton = new javax.swing.JButton();
        jVehiclesButton = new javax.swing.JButton();
        dashboardMainPanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jDashboardPanel = new javax.swing.JPanel();
        jPaymentsPanel = new javax.swing.JPanel();
        jAppointmentPanel = new javax.swing.JPanel();
        jVehiclesPanel = new javax.swing.JPanel();
        jCustomerPanel = new javax.swing.JPanel();
        jOurServicesPanel = new javax.swing.JPanel();
        jFinancePanel = new javax.swing.JPanel();
        jReportPanel = new javax.swing.JPanel();
        jStaffPanel = new javax.swing.JPanel();
        HeaderPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        empImageLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DASHBOARD");
        setPreferredSize(new java.awt.Dimension(1316, 720));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuButtonsPanel.setBackground(new java.awt.Color(255, 255, 255));
        menuButtonsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jStaffButton.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jStaffButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/employee-3.png"))); // NOI18N
        jStaffButton.setText(" Staff");
        jStaffButton.setBorderPainted(false);
        jStaffButton.setFocusable(false);
        jStaffButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jStaffButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStaffButtonActionPerformed(evt);
            }
        });
        menuButtonsPanel.add(jStaffButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 200, 60));

        jDashboardButton1.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jDashboardButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/dashboard-3.png"))); // NOI18N
        jDashboardButton1.setText("  Dashboard");
        jDashboardButton1.setBorderPainted(false);
        jDashboardButton1.setFocusable(false);
        jDashboardButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jDashboardButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDashboardButton1ActionPerformed(evt);
            }
        });
        menuButtonsPanel.add(jDashboardButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 60));

        jPaymentButton.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jPaymentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/payment-3.png"))); // NOI18N
        jPaymentButton.setText("  Payments");
        jPaymentButton.setBorderPainted(false);
        jPaymentButton.setFocusable(false);
        jPaymentButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPaymentButtonActionPerformed(evt);
            }
        });
        menuButtonsPanel.add(jPaymentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 200, 60));

        jAppointmentsButton.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jAppointmentsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/appointment-30.png"))); // NOI18N
        jAppointmentsButton.setText("  Appointment");
        jAppointmentsButton.setBorderPainted(false);
        jAppointmentsButton.setFocusable(false);
        jAppointmentsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jAppointmentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAppointmentsButtonActionPerformed(evt);
            }
        });
        menuButtonsPanel.add(jAppointmentsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 200, 60));

        jCustomerButton.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jCustomerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/customers-30.png"))); // NOI18N
        jCustomerButton.setText("  Customers");
        jCustomerButton.setBorderPainted(false);
        jCustomerButton.setFocusable(false);
        jCustomerButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCustomerButtonActionPerformed(evt);
            }
        });
        menuButtonsPanel.add(jCustomerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 200, 60));

        jOurServicesButton.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jOurServicesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/services-30.png"))); // NOI18N
        jOurServicesButton.setText("  Our Services ");
        jOurServicesButton.setBorderPainted(false);
        jOurServicesButton.setFocusable(false);
        jOurServicesButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jOurServicesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOurServicesButtonActionPerformed(evt);
            }
        });
        menuButtonsPanel.add(jOurServicesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 200, 60));

        jFinanceButton.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jFinanceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/investment-30.png"))); // NOI18N
        jFinanceButton.setText("  Finance");
        jFinanceButton.setBorderPainted(false);
        jFinanceButton.setFocusable(false);
        jFinanceButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jFinanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFinanceButtonActionPerformed(evt);
            }
        });
        menuButtonsPanel.add(jFinanceButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 200, 60));

        jReportsButton.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jReportsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/report-3.png"))); // NOI18N
        jReportsButton.setText("  Reports");
        jReportsButton.setBorderPainted(false);
        jReportsButton.setFocusable(false);
        jReportsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jReportsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReportsButtonActionPerformed(evt);
            }
        });
        menuButtonsPanel.add(jReportsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 200, 60));

        jVehiclesButton.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jVehiclesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/car-30.png"))); // NOI18N
        jVehiclesButton.setText("  Vehicles");
        jVehiclesButton.setBorderPainted(false);
        jVehiclesButton.setFocusable(false);
        jVehiclesButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jVehiclesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVehiclesButtonActionPerformed(evt);
            }
        });
        menuButtonsPanel.add(jVehiclesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 200, 60));

        jPanel1.add(menuButtonsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 200, 610));

        dashboardMainPanel.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jDashboardPanel.setBackground(new java.awt.Color(153, 153, 255));
        jDashboardPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("tab1", jDashboardPanel);

        jPaymentsPanel.setBackground(new java.awt.Color(255, 153, 153));
        jPaymentsPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("tab2", jPaymentsPanel);

        jAppointmentPanel.setBackground(new java.awt.Color(0, 102, 0));
        jAppointmentPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("tab3", jAppointmentPanel);

        jVehiclesPanel.setBackground(new java.awt.Color(255, 204, 102));
        jVehiclesPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("tab4", jVehiclesPanel);

        jCustomerPanel.setBackground(new java.awt.Color(153, 153, 0));
        jCustomerPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("tab5", jCustomerPanel);

        jOurServicesPanel.setBackground(new java.awt.Color(0, 102, 102));
        jOurServicesPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("tab6", jOurServicesPanel);

        jFinancePanel.setBackground(new java.awt.Color(102, 0, 102));
        jFinancePanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("tab7", jFinancePanel);

        jReportPanel.setBackground(new java.awt.Color(102, 102, 0));
        jReportPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("tab8", jReportPanel);

        jStaffPanel.setBackground(new java.awt.Color(102, 0, 204));
        jStaffPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("tab9", jStaffPanel);

        jPanel7.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 0, 1150, 610));

        dashboardMainPanel.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel1.add(dashboardMainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 1100, 610));

        HeaderPanel.setBackground(new java.awt.Color(255, 255, 255));
        HeaderPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Dashboard-img.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Employee Name");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Role");

        empImageLabel.setBackground(new java.awt.Color(255, 255, 255));
        empImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/account-60.png"))); // NOI18N
        empImageLabel.setOpaque(true);

        javax.swing.GroupLayout HeaderPanelLayout = new javax.swing.GroupLayout(HeaderPanel);
        HeaderPanel.setLayout(HeaderPanelLayout);
        HeaderPanelLayout.setHorizontalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(886, 886, 886)
                .addGroup(HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(empImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        HeaderPanelLayout.setVerticalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addComponent(empImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(HeaderPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 70));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jStaffButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStaffButtonActionPerformed
        jTabbedPane1.setSelectedIndex(8);
        
        StaffJPanel staffJPanel = new StaffJPanel();
        jStaffPanel.add(staffJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jStaffButtonActionPerformed

    private void jDashboardButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDashboardButton1ActionPerformed
        jTabbedPane1.setSelectedIndex(0);

        DashboardPanel dashboardPanel = new DashboardPanel();
        jDashboardPanel.add(dashboardPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jDashboardButton1ActionPerformed

    private void jPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPaymentButtonActionPerformed
        jTabbedPane1.setSelectedIndex(1);

        PaymentsPanel paymentPanel = new PaymentsPanel();
        jPaymentsPanel.add(paymentPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);

    }//GEN-LAST:event_jPaymentButtonActionPerformed

    private void jAppointmentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAppointmentsButtonActionPerformed
        jTabbedPane1.setSelectedIndex(2);
        
        AppointmnetPanel appointmnetPanel = new AppointmnetPanel();
        jAppointmentPanel.add(appointmnetPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jAppointmentsButtonActionPerformed

    private void jCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCustomerButtonActionPerformed
        jTabbedPane1.setSelectedIndex(4);
        
        CustomerJPanel customerJPanel = new CustomerJPanel();
        jCustomerPanel.add(customerJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jCustomerButtonActionPerformed

    private void jOurServicesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOurServicesButtonActionPerformed
        jTabbedPane1.setSelectedIndex(5);
        
        ourServicesJPanel ourServices = new ourServicesJPanel();
        jOurServicesPanel.add(ourServices, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jOurServicesButtonActionPerformed

    private void jFinanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFinanceButtonActionPerformed
        jTabbedPane1.setSelectedIndex(6);
        
        FinanceJPanel financeJPanel = new FinanceJPanel();
        jFinancePanel.add(financeJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jFinanceButtonActionPerformed

    private void jReportsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReportsButtonActionPerformed
        jTabbedPane1.setSelectedIndex(7);
        
        ReportsJPanel reportsJPanel = new ReportsJPanel();
        jReportPanel.add(reportsJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jReportsButtonActionPerformed

    private void jVehiclesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVehiclesButtonActionPerformed
        jTabbedPane1.setSelectedIndex(3);
        
        VehiclesJPanel vehiclesJPanel = new VehiclesJPanel();
        jVehiclesPanel.add(vehiclesJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jVehiclesButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        IntelliJTheme.setup(Dashboard.class.getResourceAsStream(
                "/resources/themes/arc-theme.theme.json"));

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HeaderPanel;
    private javax.swing.JPanel dashboardMainPanel;
    private javax.swing.JLabel empImageLabel;
    private javax.swing.JPanel jAppointmentPanel;
    private javax.swing.JButton jAppointmentsButton;
    private javax.swing.JButton jCustomerButton;
    private javax.swing.JPanel jCustomerPanel;
    private javax.swing.JButton jDashboardButton1;
    private javax.swing.JPanel jDashboardPanel;
    private javax.swing.JButton jFinanceButton;
    private javax.swing.JPanel jFinancePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton jOurServicesButton;
    private javax.swing.JPanel jOurServicesPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JButton jPaymentButton;
    private javax.swing.JPanel jPaymentsPanel;
    private javax.swing.JPanel jReportPanel;
    private javax.swing.JButton jReportsButton;
    private javax.swing.JButton jStaffButton;
    private javax.swing.JPanel jStaffPanel;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jVehiclesButton;
    private javax.swing.JPanel jVehiclesPanel;
    private javax.swing.JPanel menuButtonsPanel;
    // End of variables declaration//GEN-END:variables
}
