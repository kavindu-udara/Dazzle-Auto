/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.myaccount;

import com.formdev.flatlaf.FlatClientProperties;
import controllers.AccessRoleController;
import controllers.LoginController;
import includes.BDUtility;
import includes.LoggerConfig;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.sql.ResultSet;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import static views.dashboard.Dashboard.loginModel;

/**
 *
 * @author E
 */
public class AccountInterface extends javax.swing.JPanel {

    private static final Logger logger = LoggerConfig.getLogger();
    MyAccount MYACCOUNT;

    public AccountInterface(MyAccount myAccount) {
        initComponents();
        MYACCOUNT = myAccount;
        jPanel2.putClientProperty(FlatClientProperties.STYLE, "arc:18");
        setLoggedUserDetails();
    }

    public void setLoggedUserDetails() {
        jEmployeeIDLabel.setText(loginModel.getEmployeeId());
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
                Image image = icon.getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH);
                // Create the resized icon
                ImageIcon resizedIcon = new ImageIcon(image);
                // Set it to the label
                empImageLabel.setIcon(resizedIcon);

            } else {

            }
            // Image setting
            ResultSet loginDeatilsRs = new LoginController().showLoginDeatils(loginModel.getEmployeeId());
            if (loginDeatilsRs.next()) {
                usernameField.setText(loginDeatilsRs.getString("id"));
                passwordField.setText(loginDeatilsRs.getString("password"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while setting logged user details : " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jEmployeeIDLabel = new javax.swing.JLabel();
        empImageLabel = new javax.swing.JLabel();
        jEmployeeNameLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        usernameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jEmployeeRoleLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(510, 534));
        setPreferredSize(new java.awt.Dimension(510, 534));

        jLabel1.setBackground(new java.awt.Color(5, 15, 76));
        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/account-30.png"))); // NOI18N
        jLabel1.setText("  MY ACCOUNT");
        jLabel1.setOpaque(true);

        jEmployeeIDLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jEmployeeIDLabel.setForeground(new java.awt.Color(0, 102, 0));
        jEmployeeIDLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jEmployeeIDLabel.setText("ID");

        empImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/account-60.png"))); // NOI18N
        empImageLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), null));

        jEmployeeNameLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jEmployeeNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jEmployeeNameLabel.setText("ID");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        usernameField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        usernameField.setFocusable(false);
        jPanel2.add(usernameField);
        usernameField.setBounds(190, 20, 220, 39);

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Username");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(60, 20, 100, 40);

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Password");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(60, 80, 100, 40);

        passwordField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        passwordField.setFocusable(false);
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });
        jPanel2.add(passwordField);
        passwordField.setBounds(190, 80, 180, 40);

        jButton2.setBackground(new java.awt.Color(5, 15, 76));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/eye.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton2MouseReleased(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(360, 80, 50, 40);

        jButton1.setBackground(new java.awt.Color(232, 122, 1));
        jButton1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/icons8-arrow-30.png"))); // NOI18N
        jButton1.setText("Change Password           ");
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(80, 160, 330, 40);

        jEmployeeRoleLabel.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jEmployeeRoleLabel.setForeground(new java.awt.Color(0, 0, 153));
        jEmployeeRoleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jEmployeeRoleLabel.setText("Role");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(198, 198, 198)
                                .addComponent(empImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jEmployeeIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jEmployeeNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jEmployeeRoleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jEmployeeIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(empImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jEmployeeNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jEmployeeRoleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        passwordField.setEchoChar('\u0000');
        Icon icon = new ImageIcon("src/resources/icons/icons8-hide-20.png");
        jButton2.setIcon(icon);
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseReleased
        passwordField.setEchoChar('\u2022');
        Icon icon = new ImageIcon("src/resources/icons/eye.png");
        jButton2.setIcon(icon);
    }//GEN-LAST:event_jButton2MouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         MYACCOUNT.jPanel1.remove(this);
        SwingUtilities.updateComponentTreeUI(MYACCOUNT.jPanel1);

        MYACCOUNT.passwordChange = new PasswordChange(MYACCOUNT);
        MYACCOUNT.jPanel1.add(MYACCOUNT.passwordChange, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(MYACCOUNT.jPanel1);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empImageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jEmployeeIDLabel;
    private javax.swing.JLabel jEmployeeNameLabel;
    private javax.swing.JLabel jEmployeeRoleLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
