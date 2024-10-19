/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.shop.dashboard;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.IntelliJTheme;
import controllers.AccessRoleController;
import includes.BDUtility;
import includes.LoggerConfig;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.ResultSet;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import models.LoginModel;
import views.shop.grn.shop_GRNJPanel;
import views.shop.items.Shop_ItemsView;
import views.shop.items.shop_ItemsJPanel;
import views.shop.payments.Shop_PaymentJPanel;
import views.shop.stock.Shop_StockJPanel;

/**
 *
 * @author Dinuka
 */
public class ShopDashboard extends javax.swing.JFrame {
    
    private static Logger logger = LoggerConfig.getLogger();
    
    private Color btnDefaultColor = new Color(246, 249, 255);
    private Color btnHoverColor = new Color(255, 248, 237);
    private Color btnSelectedColor = new Color(250, 238, 220);
    
    LoginModel loginModel;
    
    public ShopDashboard(LoginModel loginModel) {
        this.loginModel = loginModel;
        initComponents();
        
        roundPanels();
        dateTime();
        
        setLoggedUserDetails();
    }
    
    public void roundPanels() {
        jDashboardbtn.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPaymentsbtn.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jStockbtn.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jItemsbtn.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jGRNbtn.putClientProperty(FlatClientProperties.STYLE, "arc:15");
    }
    
    public void dateTime() {
        java.lang.Runnable runnable = new java.lang.Runnable() {
            @Override
            public void run() {
                
                while (true) {
                    java.util.Date date1 = new java.util.Date();
                    
                    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy.MM.dd '-' hh:mm:ss a");
                    String finaldate = dateFormat.format(date1);
                    jLabel49.setText(finaldate);
                    
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        logger.severe("Error in dateTime() " + ex.getMessage());
                    }
                }
                
            }
            
        };
        
        java.lang.Thread thread = new java.lang.Thread(runnable);
        thread.start();
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

        mainjPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jHeaderPanel = new javax.swing.JPanel();
        jDashboardbtn = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPaymentsbtn = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jItemsbtn = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jStockbtn = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jGRNbtn = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jEmployeeNameLabel = new javax.swing.JLabel();
        empImageLabel = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jEmployeeRoleLabel = new javax.swing.JLabel();
        jContentPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jDashboardPanel = new javax.swing.JPanel();
        jPaymentsPanel = new javax.swing.JPanel();
        jItemsPanel = new javax.swing.JPanel();
        jStockPanel = new javax.swing.JPanel();
        jGRNPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DASHBOARD");
        setMinimumSize(new java.awt.Dimension(1300, 700));

        mainjPanel.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jHeaderPanel.setBackground(new java.awt.Color(255, 255, 255));
        jHeaderPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDashboardbtn.setBackground(new java.awt.Color(246, 249, 255));
        jDashboardbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jDashboardbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDashboardbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jDashboardbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jDashboardbtnMouseExited(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Dashboard");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/dashboard-50.png"))); // NOI18N

        javax.swing.GroupLayout jDashboardbtnLayout = new javax.swing.GroupLayout(jDashboardbtn);
        jDashboardbtn.setLayout(jDashboardbtnLayout);
        jDashboardbtnLayout.setHorizontalGroup(
            jDashboardbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDashboardbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDashboardbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDashboardbtnLayout.setVerticalGroup(
            jDashboardbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDashboardbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jHeaderPanel.add(jDashboardbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 160, 70));

        jPaymentsbtn.setBackground(new java.awt.Color(246, 249, 255));
        jPaymentsbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPaymentsbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPaymentsbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPaymentsbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPaymentsbtnMouseExited(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/cash-register-35.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Payments");

        javax.swing.GroupLayout jPaymentsbtnLayout = new javax.swing.GroupLayout(jPaymentsbtn);
        jPaymentsbtn.setLayout(jPaymentsbtnLayout);
        jPaymentsbtnLayout.setHorizontalGroup(
            jPaymentsbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaymentsbtnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPaymentsbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPaymentsbtnLayout.setVerticalGroup(
            jPaymentsbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaymentsbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jHeaderPanel.add(jPaymentsbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 160, 70));

        jItemsbtn.setBackground(new java.awt.Color(246, 249, 255));
        jItemsbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jItemsbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jItemsbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jItemsbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jItemsbtnMouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Items");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/nft-share-40.png"))); // NOI18N

        javax.swing.GroupLayout jItemsbtnLayout = new javax.swing.GroupLayout(jItemsbtn);
        jItemsbtn.setLayout(jItemsbtnLayout);
        jItemsbtnLayout.setHorizontalGroup(
            jItemsbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jItemsbtnLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jItemsbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jItemsbtnLayout.setVerticalGroup(
            jItemsbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jItemsbtnLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addComponent(jLabel6))
        );

        jHeaderPanel.add(jItemsbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 160, 70));

        jStockbtn.setBackground(new java.awt.Color(246, 249, 255));
        jStockbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jStockbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jStockbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jStockbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jStockbtnMouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 51));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Stock");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/stock-35.png"))); // NOI18N

        javax.swing.GroupLayout jStockbtnLayout = new javax.swing.GroupLayout(jStockbtn);
        jStockbtn.setLayout(jStockbtnLayout);
        jStockbtnLayout.setHorizontalGroup(
            jStockbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jStockbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jStockbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jStockbtnLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jStockbtnLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jStockbtnLayout.setVerticalGroup(
            jStockbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jStockbtnLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel8))
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jHeaderPanel.add(jStockbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 160, 70));

        jGRNbtn.setBackground(new java.awt.Color(246, 249, 255));
        jGRNbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jGRNbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jGRNbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jGRNbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jGRNbtnMouseExited(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("GRN");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/invoice-35.png"))); // NOI18N

        javax.swing.GroupLayout jGRNbtnLayout = new javax.swing.GroupLayout(jGRNbtn);
        jGRNbtn.setLayout(jGRNbtnLayout);
        jGRNbtnLayout.setHorizontalGroup(
            jGRNbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jGRNbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jGRNbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jGRNbtnLayout.setVerticalGroup(
            jGRNbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jGRNbtnLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel10)
                .addGap(4, 4, 4)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
        );

        jHeaderPanel.add(jGRNbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 30, -1, 70));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Dashboard-img.png"))); // NOI18N
        jHeaderPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 35, 170, -1));

        jEmployeeNameLabel.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jEmployeeNameLabel.setForeground(new java.awt.Color(0, 102, 102));
        jEmployeeNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jEmployeeNameLabel.setText("Employee Name");
        jHeaderPanel.add(jEmployeeNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 40, 120, 30));

        empImageLabel.setBackground(new java.awt.Color(255, 255, 255));
        empImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/account-60.png"))); // NOI18N
        empImageLabel.setMaximumSize(new java.awt.Dimension(56, 56));
        empImageLabel.setMinimumSize(new java.awt.Dimension(56, 56));
        empImageLabel.setOpaque(true);
        empImageLabel.setPreferredSize(new java.awt.Dimension(56, 56));
        jHeaderPanel.add(empImageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 40, -1, 50));

        jLabel49.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 153));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText(" Date & Time");
        jLabel49.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jHeaderPanel.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -2, 1300, 30));

        jEmployeeRoleLabel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jEmployeeRoleLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jEmployeeRoleLabel.setText("Role");
        jHeaderPanel.add(jEmployeeRoleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 70, 120, 20));

        jPanel2.add(jHeaderPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 100));

        jContentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jDashboardPanel.setBackground(new java.awt.Color(0, 0, 102));
        jDashboardPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("tab1", jDashboardPanel);

        jPaymentsPanel.setBackground(new java.awt.Color(102, 0, 102));
        jPaymentsPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("tab2", jPaymentsPanel);

        jItemsPanel.setBackground(new java.awt.Color(0, 51, 0));
        jItemsPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("tab3", jItemsPanel);

        jStockPanel.setBackground(new java.awt.Color(153, 102, 0));
        jStockPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("tab4", jStockPanel);

        jGRNPanel.setBackground(new java.awt.Color(102, 102, 0));
        jGRNPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("tab5", jGRNPanel);

        jContentPanel.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 640));

        jPanel2.add(jContentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1300, 600));

        mainjPanel.add(jPanel2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jDashboardbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDashboardbtnMouseClicked
        jTabbedPane1.setSelectedIndex(0);
        
        jDashboardbtn.setBackground(new Color(250, 238, 220));
        jPaymentsbtn.setBackground(new Color(246, 249, 255));
        jItemsbtn.setBackground(new Color(246, 249, 255));
        jStockbtn.setBackground(new Color(246, 249, 255));
        jGRNbtn.setBackground(new Color(246, 249, 255));
        
        shop_DashboardJPanel shop_DashboardPanel = new shop_DashboardJPanel();
        jDashboardPanel.add(shop_DashboardPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jDashboardbtnMouseClicked

    private void jPaymentsbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPaymentsbtnMouseClicked
        jTabbedPane1.setSelectedIndex(1);
        
        jDashboardbtn.setBackground(new Color(246, 249, 255));
        jPaymentsbtn.setBackground(new Color(250, 238, 220));
        jItemsbtn.setBackground(new Color(246, 249, 255));
        jStockbtn.setBackground(new Color(246, 249, 255));
        jGRNbtn.setBackground(new Color(246, 249, 255));
        
        Shop_PaymentJPanel shop_PaymentJPanel = new Shop_PaymentJPanel();
        jPaymentsPanel.add(shop_PaymentJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jPaymentsbtnMouseClicked

    private void jItemsbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jItemsbtnMouseClicked
        jTabbedPane1.setSelectedIndex(2);
        
        jDashboardbtn.setBackground(new Color(246, 249, 255));
        jPaymentsbtn.setBackground(new Color(246, 249, 255));
        jItemsbtn.setBackground(new Color(250, 238, 220));
        jStockbtn.setBackground(new Color(246, 249, 255));
        jGRNbtn.setBackground(new Color(246, 249, 255));

        //shop_ItemsJPanel shop_itemjpanel = new shop_ItemsJPanel();
        Shop_ItemsView Shop_itemsView = new Shop_ItemsView();
        //jItemsPanel.add(shop_itemjpanel, BorderLayout.CENTER);
        jItemsPanel.add(Shop_itemsView, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jItemsbtnMouseClicked

    private void jStockbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jStockbtnMouseClicked
        jTabbedPane1.setSelectedIndex(3);
        
        jDashboardbtn.setBackground(new Color(246, 249, 255));
        jPaymentsbtn.setBackground(new Color(246, 249, 255));
        jItemsbtn.setBackground(new Color(246, 249, 255));
        jStockbtn.setBackground(new Color(250, 238, 220));
        jGRNbtn.setBackground(new Color(246, 249, 255));
        
        Shop_StockJPanel shop_StockJPanel = new Shop_StockJPanel();
        jStockPanel.add(shop_StockJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jStockbtnMouseClicked

    private void jGRNbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jGRNbtnMouseClicked
        jTabbedPane1.setSelectedIndex(4);
        
        jDashboardbtn.setBackground(new Color(246, 249, 255));
        jPaymentsbtn.setBackground(new Color(246, 249, 255));
        jItemsbtn.setBackground(new Color(246, 249, 255));
        jStockbtn.setBackground(new Color(246, 249, 255));
        jGRNbtn.setBackground(new Color(250, 238, 220));
        
        shop_GRNJPanel shop_grnjpanel = new shop_GRNJPanel();
        jGRNPanel.add(shop_grnjpanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jGRNbtnMouseClicked

    //Set Button Hover Color 
    private void jDashboardbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDashboardbtnMouseEntered
        if (jDashboardbtn.getBackground().equals(btnSelectedColor)) {
            jDashboardbtn.setBackground(btnSelectedColor);
        } else {
            jDashboardbtn.setBackground(btnHoverColor);
        }
    }//GEN-LAST:event_jDashboardbtnMouseEntered

    private void jPaymentsbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPaymentsbtnMouseEntered
        if (jPaymentsbtn.getBackground().equals(btnSelectedColor)) {
            jPaymentsbtn.setBackground(btnSelectedColor);
        } else {
            jPaymentsbtn.setBackground(btnHoverColor);
        }
    }//GEN-LAST:event_jPaymentsbtnMouseEntered

    private void jItemsbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jItemsbtnMouseEntered
        if (jItemsbtn.getBackground().equals(btnSelectedColor)) {
            jItemsbtn.setBackground(btnSelectedColor);
        } else {
            jItemsbtn.setBackground(btnHoverColor);
        }
    }//GEN-LAST:event_jItemsbtnMouseEntered

    private void jStockbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jStockbtnMouseEntered
        if (jStockbtn.getBackground().equals(btnSelectedColor)) {
            jStockbtn.setBackground(btnSelectedColor);
        } else {
            jStockbtn.setBackground(btnHoverColor);
        }
    }//GEN-LAST:event_jStockbtnMouseEntered

    private void jGRNbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jGRNbtnMouseEntered
        if (jGRNbtn.getBackground().equals(btnSelectedColor)) {
            jGRNbtn.setBackground(btnSelectedColor);
        } else {
            jGRNbtn.setBackground(btnHoverColor);
        }
    }//GEN-LAST:event_jGRNbtnMouseEntered

    //Set Button Default Color 
    private void jDashboardbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDashboardbtnMouseExited
        if (jDashboardbtn.getBackground().equals(btnHoverColor)) {
            jDashboardbtn.setBackground(btnDefaultColor);
        } else {
            jDashboardbtn.setBackground(btnSelectedColor);
        }

    }//GEN-LAST:event_jDashboardbtnMouseExited

    private void jPaymentsbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPaymentsbtnMouseExited
        if (jPaymentsbtn.getBackground().equals(btnHoverColor)) {
            jPaymentsbtn.setBackground(btnDefaultColor);
        } else {
            jPaymentsbtn.setBackground(btnSelectedColor);
        }
    }//GEN-LAST:event_jPaymentsbtnMouseExited

    private void jItemsbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jItemsbtnMouseExited
        if (jItemsbtn.getBackground().equals(btnHoverColor)) {
            jItemsbtn.setBackground(btnDefaultColor);
        } else {
            jItemsbtn.setBackground(btnSelectedColor);
        }
    }//GEN-LAST:event_jItemsbtnMouseExited

    private void jStockbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jStockbtnMouseExited
        if (jStockbtn.getBackground().equals(btnHoverColor)) {
            jStockbtn.setBackground(btnDefaultColor);
        } else {
            jStockbtn.setBackground(btnSelectedColor);
        }
    }//GEN-LAST:event_jStockbtnMouseExited

    private void jGRNbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jGRNbtnMouseExited
        if (jGRNbtn.getBackground().equals(btnHoverColor)) {
            jGRNbtn.setBackground(btnDefaultColor);
        } else {
            jGRNbtn.setBackground(btnSelectedColor);
        }
    }//GEN-LAST:event_jGRNbtnMouseExited

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        IntelliJTheme.setup(ShopDashboard.class.getResourceAsStream(
//                "/resources/themes/arc-theme.theme.json"));
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ShopDashboard().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empImageLabel;
    private javax.swing.JPanel jContentPanel;
    private javax.swing.JPanel jDashboardPanel;
    private javax.swing.JPanel jDashboardbtn;
    private javax.swing.JLabel jEmployeeNameLabel;
    private javax.swing.JLabel jEmployeeRoleLabel;
    private javax.swing.JPanel jGRNPanel;
    private javax.swing.JPanel jGRNbtn;
    private javax.swing.JPanel jHeaderPanel;
    private javax.swing.JPanel jItemsPanel;
    private javax.swing.JPanel jItemsbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPaymentsPanel;
    private javax.swing.JPanel jPaymentsbtn;
    private javax.swing.JPanel jStockPanel;
    private javax.swing.JPanel jStockbtn;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel mainjPanel;
    // End of variables declaration//GEN-END:variables
}
