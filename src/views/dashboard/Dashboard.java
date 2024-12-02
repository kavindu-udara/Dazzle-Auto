/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.dashboard;

import controllers.AccessRoleController;
import includes.BDUtility;
import includes.LoggerConfig;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.ResultSet;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import models.LoginModel;
import views.LoginChooser;
import views.settings.Settings;
import views.customer.CustomerJPanel;
import views.employee.StaffJPanel;
import views.financeAndHr.FinanceAndHrJPanel;
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

    private static final Logger logger = LoggerConfig.getLogger();

    LoginModel loginModel;

    public StaffJPanel staffJPanel = null;
    DashboardPanel dashboardPanel = null;
    public PaymentsPanel paymentPanel = null;
    AppointmnetPanel appointmnetPanel = null;
    CustomerJPanel customerJPanel = null;
    ourServicesJPanel ourServices = null;
    FinanceAndHrJPanel financeJPanel = null;
    public ReportsJPanel reportsJPanel = null;
    VehiclesJPanel vehiclesJPanel = null;

    public Dashboard(LoginModel loginModel) {
        this.loginModel = loginModel;
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/icon2.png")));

        setLoggedUserDetails();

        jTabbedPane1.setSelectedIndex(0);

        dashboardPanel = new DashboardPanel();
        jDashboardPanel.add(dashboardPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void setLoggedUserDetails() {
        jEmployeeNameLabel.setText(loginModel.getFirstName() + " " + loginModel.getLastName());
        try {
            ResultSet rs = new AccessRoleController().show(loginModel.getAccessRoleId());

            if (rs.next()) {
                jEmployeeRoleLabel.setText(rs.getString("role"));
            }

            // Image setting
            String imagepath = BDUtility.getPath(loginModel.getImage());
            File imageFile = new File(imagepath);

            if (imageFile.exists()) {
                // Initialize ImageIcon with the image path
                ImageIcon icon = new ImageIcon(imagepath);
                // Get the image and scale it
                Image image = icon.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH);
                // Create the resized icon
                ImageIcon resizedIcon = new ImageIcon(image);
                // Set it to the label
                empImageLabel.setIcon(resizedIcon);

            } else {

            }
            // Image setting

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while setting logged user details : " + ex.getMessage());
        }
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
        jEmployeeNameLabel = new javax.swing.JLabel();
        jEmployeeRoleLabel = new javax.swing.JLabel();
        empImageLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jLoginAccessMenuItem = new javax.swing.JMenuItem();
        jDatabaseMenuItem = new javax.swing.JMenuItem();

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
        jStaffButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jDashboardButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jPaymentButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jAppointmentsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jCustomerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jOurServicesButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jOurServicesButton.setFocusable(false);
        jOurServicesButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jOurServicesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOurServicesButtonActionPerformed(evt);
            }
        });
        menuButtonsPanel.add(jOurServicesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 210, 60));

        jFinanceButton.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jFinanceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/resume-30.png"))); // NOI18N
        jFinanceButton.setText("  Finance & HR");
        jFinanceButton.setBorderPainted(false);
        jFinanceButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jFinanceButton.setFocusable(false);
        jFinanceButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jFinanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFinanceButtonActionPerformed(evt);
            }
        });
        menuButtonsPanel.add(jFinanceButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 210, 60));

        jReportsButton.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jReportsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/report-3.png"))); // NOI18N
        jReportsButton.setText("  Reports");
        jReportsButton.setBorderPainted(false);
        jReportsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jVehiclesButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jEmployeeNameLabel.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jEmployeeNameLabel.setForeground(new java.awt.Color(0, 0, 51));
        jEmployeeNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jEmployeeNameLabel.setText("Employee Name");

        jEmployeeRoleLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jEmployeeRoleLabel.setForeground(new java.awt.Color(0, 0, 51));
        jEmployeeRoleLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jEmployeeRoleLabel.setText("Role");

        empImageLabel.setBackground(new java.awt.Color(255, 255, 255));
        empImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/account-60.png"))); // NOI18N
        empImageLabel.setMaximumSize(new java.awt.Dimension(56, 56));
        empImageLabel.setMinimumSize(new java.awt.Dimension(56, 56));
        empImageLabel.setOpaque(true);
        empImageLabel.setPreferredSize(new java.awt.Dimension(56, 56));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/log-out-40.png"))); // NOI18N
        jButton1.setToolTipText("LogOut");
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout HeaderPanelLayout = new javax.swing.GroupLayout(HeaderPanel);
        HeaderPanel.setLayout(HeaderPanelLayout);
        HeaderPanelLayout.setHorizontalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 820, Short.MAX_VALUE)
                .addGroup(HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jEmployeeNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jEmployeeRoleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(empImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jButton1)
                .addGap(7, 7, 7))
        );
        HeaderPanelLayout.setVerticalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addGroup(HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(empImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(HeaderPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(HeaderPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jEmployeeNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jEmployeeRoleLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator1)
        );

        jPanel1.add(HeaderPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 70));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/settings-15.png"))); // NOI18N
        jMenu3.setText("Settings");
        jMenu3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLoginAccessMenuItem.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLoginAccessMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/access-20.png"))); // NOI18N
        jLoginAccessMenuItem.setText("Manage Login Access");
        jLoginAccessMenuItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLoginAccessMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLoginAccessMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(jLoginAccessMenuItem);

        jDatabaseMenuItem.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jDatabaseMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/database-20.png"))); // NOI18N
        jDatabaseMenuItem.setText("Manage Database");
        jDatabaseMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDatabaseMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(jDatabaseMenuItem);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jStaffButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStaffButtonActionPerformed
        jTabbedPane1.setSelectedIndex(8);

        if (staffJPanel == null) {
            staffJPanel = new StaffJPanel(this);
            jStaffPanel.add(staffJPanel, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this);
        }

    }//GEN-LAST:event_jStaffButtonActionPerformed

    private void jDashboardButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDashboardButton1ActionPerformed
            
        jTabbedPane1.setSelectedIndex(0);

        if (dashboardMainPanel == null) {
            dashboardPanel = new DashboardPanel();
            jDashboardPanel.add(dashboardPanel, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this);
        }else{
            dashboardPanel.reload();
        }
    }//GEN-LAST:event_jDashboardButton1ActionPerformed

    private void jPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPaymentButtonActionPerformed
        jTabbedPane1.setSelectedIndex(1);

        if (paymentPanel == null) {
            paymentPanel = new PaymentsPanel(this);
            jPaymentsPanel.add(paymentPanel, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this);
        }

    }//GEN-LAST:event_jPaymentButtonActionPerformed

    private void jAppointmentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAppointmentsButtonActionPerformed
        jTabbedPane1.setSelectedIndex(2);
        if (appointmnetPanel == null) {
            appointmnetPanel = new AppointmnetPanel(this);
            jAppointmentPanel.add(appointmnetPanel, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }//GEN-LAST:event_jAppointmentsButtonActionPerformed

    private void jCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCustomerButtonActionPerformed
        jTabbedPane1.setSelectedIndex(4);
        if (customerJPanel == null) {
            customerJPanel = new CustomerJPanel();
            jCustomerPanel.add(customerJPanel, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }//GEN-LAST:event_jCustomerButtonActionPerformed

    private void jOurServicesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOurServicesButtonActionPerformed
        jTabbedPane1.setSelectedIndex(5);
        if (ourServices == null) {
            ourServices = new ourServicesJPanel();
            jOurServicesPanel.add(ourServices, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }//GEN-LAST:event_jOurServicesButtonActionPerformed

    private void jFinanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFinanceButtonActionPerformed
        jTabbedPane1.setSelectedIndex(6);
        if (financeJPanel == null) {
            financeJPanel = new FinanceAndHrJPanel();
            jFinancePanel.add(financeJPanel, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }//GEN-LAST:event_jFinanceButtonActionPerformed

    private void jReportsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReportsButtonActionPerformed
        jTabbedPane1.setSelectedIndex(7);
        if (reportsJPanel == null) {
            reportsJPanel = new ReportsJPanel(this);
            jReportPanel.add(reportsJPanel, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }//GEN-LAST:event_jReportsButtonActionPerformed

    private void jVehiclesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVehiclesButtonActionPerformed
        jTabbedPane1.setSelectedIndex(3);
        if (vehiclesJPanel == null) {
            vehiclesJPanel = new VehiclesJPanel();
            jVehiclesPanel.add(vehiclesJPanel, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }//GEN-LAST:event_jVehiclesButtonActionPerformed

    private void jLoginAccessMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLoginAccessMenuItemActionPerformed
        new Settings(this, true, "jLoginAccessMenu").setVisible(true);
    }//GEN-LAST:event_jLoginAccessMenuItemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int showConfirm = JOptionPane.showConfirmDialog(this, "Do You Want To Log Out ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (showConfirm == JOptionPane.YES_OPTION) {
            this.dispose();
            new LoginChooser().setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jDatabaseMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDatabaseMenuItemActionPerformed
        new Settings(this, true, "jDatabaseMenuItem").setVisible(true);
    }//GEN-LAST:event_jDatabaseMenuItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HeaderPanel;
    private javax.swing.JPanel dashboardMainPanel;
    private javax.swing.JLabel empImageLabel;
    private javax.swing.JPanel jAppointmentPanel;
    private javax.swing.JButton jAppointmentsButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jCustomerButton;
    private javax.swing.JPanel jCustomerPanel;
    private javax.swing.JButton jDashboardButton1;
    private javax.swing.JPanel jDashboardPanel;
    private javax.swing.JMenuItem jDatabaseMenuItem;
    private javax.swing.JLabel jEmployeeNameLabel;
    private javax.swing.JLabel jEmployeeRoleLabel;
    private javax.swing.JButton jFinanceButton;
    private javax.swing.JPanel jFinancePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jLoginAccessMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton jOurServicesButton;
    private javax.swing.JPanel jOurServicesPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JButton jPaymentButton;
    public javax.swing.JPanel jPaymentsPanel;
    public javax.swing.JPanel jReportPanel;
    private javax.swing.JButton jReportsButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jStaffButton;
    public javax.swing.JPanel jStaffPanel;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jVehiclesButton;
    private javax.swing.JPanel jVehiclesPanel;
    private javax.swing.JPanel menuButtonsPanel;
    // End of variables declaration//GEN-END:variables
}
