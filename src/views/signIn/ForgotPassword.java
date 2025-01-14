/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.signIn;

import com.formdev.flatlaf.FlatClientProperties;
import controllers.EmployeeController;
import controllers.LoginController;
import includes.Mailer;
import includes.MySqlConnection;
import includes.RegexValidator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import resources.emailTemplates.MailTemplates;
import views.myaccount.MyAccount;

/**
 *
 * @author Dinuka
 */
public class ForgotPassword extends javax.swing.JPanel {

    MyAccount MYACCOUNT;
    String EMPID = "";

    public ForgotPassword(MyAccount myAccount, String empID) {
        initComponents();
        MYACCOUNT = myAccount;
        empIDField.setText(empID);
        EMPID = empID;
        passwordPanel.putClientProperty(FlatClientProperties.STYLE, "arc:18");
        verificationPanel.putClientProperty(FlatClientProperties.STYLE, "arc:18");
        emailField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Email");
        verifyField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter OTP Code");
        passwordPanel.setVisible(false);
        verificationPanel.setVisible(false);

        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Block the spacebar
                if (e.getKeyChar() == ' ') {
                    e.consume(); // Prevent space input
                }
            }
        });

        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Block the spacebar
                if (e.getKeyChar() == ' ') {
                    e.consume(); // Prevent space input
                }
            }
        });

        verifyField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Block the spacebar
                if (e.getKeyChar() == ' ') {
                    e.consume(); // Prevent space input
                }
            }
        });

        passwordField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Block the spacebar
                if (e.getKeyChar() == ' ') {
                    e.consume(); // Prevent space input
                }
            }
        });

        try {
            ResultSet empRs = new EmployeeController().show(EMPID);
            if (empRs.next()) {
                emailField.setText(empRs.getString("email"));               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        jButton2.grabFocus();
    }

    private void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public String generateOTP() {
        // Get the current time in milliseconds
        long currentTimeMillis = System.currentTimeMillis();
        // Convert to a string and take the last 6 digits
        String code = Long.toString(currentTimeMillis);
        if (code.length() > 6) {
            code = code.substring(code.length() - 6); // Take the last 6 digits
        }
        return code;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        passwordPanel = new javax.swing.JPanel();
        passwordField2 = new javax.swing.JPasswordField();
        passwordField1 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        verificationPanel = new javax.swing.JPanel();
        verifyField = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        empIDField = new javax.swing.JLabel();
        title1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        emailField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(510, 534));

        jLabel1.setBackground(new java.awt.Color(5, 15, 76));
        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/forgot-password-35.png"))); // NOI18N
        jLabel1.setText(" FORGOT PASSWORD ?");
        jLabel1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(241, 241, 248));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        passwordPanel.setBackground(new java.awt.Color(255, 255, 255));

        passwordField2.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        passwordField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        passwordField1.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        passwordField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel2.setText("Enter New Password");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel3.setText("Re-Enter Password");

        jButton4.setBackground(new java.awt.Color(0, 102, 0));
        jButton4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Change Password");
        jButton4.setBorderPainted(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setFocusPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout passwordPanelLayout = new javax.swing.GroupLayout(passwordPanel);
        passwordPanel.setLayout(passwordPanelLayout);
        passwordPanelLayout.setHorizontalGroup(
            passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, passwordPanelLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(passwordPanelLayout.createSequentialGroup()
                        .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(passwordField2))))
                .addGap(29, 29, 29))
        );
        passwordPanelLayout.setVerticalGroup(
            passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(passwordPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1.add(passwordPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, 160));

        verificationPanel.setBackground(new java.awt.Color(255, 255, 255));

        verifyField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        verifyField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton3.setBackground(new java.awt.Color(80, 80, 86));
        jButton3.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Verify Code");
        jButton3.setBorderPainted(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout verificationPanelLayout = new javax.swing.GroupLayout(verificationPanel);
        verificationPanel.setLayout(verificationPanelLayout);
        verificationPanelLayout.setHorizontalGroup(
            verificationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, verificationPanelLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(verificationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(verifyField)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        verificationPanelLayout.setVerticalGroup(
            verificationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(verificationPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(verifyField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.add(verificationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, 130));

        empIDField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        empIDField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empIDField.setText("Id");
        jPanel1.add(empIDField, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 200, 30));

        title1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        title1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title1.setText("Employee ID :");
        jPanel1.add(title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 130, 30));

        jButton2.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jButton2.setText("Send OTP");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 345, 30));

        emailField.setEditable(false);
        emailField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        emailField.setFocusable(false);
        jPanel1.add(emailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 345, 40));

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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String password = String.valueOf(passwordField1.getPassword()).trim();
        String password2 = String.valueOf(passwordField2.getPassword()).trim();

        if (password.isEmpty()) {
            showWarningMessage("Please Enter New Password");
        } else if (!RegexValidator.isValidPassword(password)) {
            showWarningMessage("The password must contain at least 8 characters, at least one lowercase letter, at least one uppercase letter, at least one digit, at least one special character");
        } else if (password2.isEmpty()) {
            showWarningMessage("Please Re-Enter Password");
        } else if (!RegexValidator.isValidPassword(password2)) {
            showWarningMessage("The password must contain at least 8 characters, at least one lowercase letter, at least one uppercase letter, at least one digit, at least one special character");
        } else {
            if (password.equals(password2)) {
                try {
                    MySqlConnection.executeIUD("UPDATE `login` SET `password`='" + password2 + "', `otp_code`='" + generateOTP() + "' WHERE `employee_id`='" + EMPID + "' ");

                    JOptionPane.showMessageDialog(this, "Password Changed !", "Success", JOptionPane.INFORMATION_MESSAGE);
                    MYACCOUNT.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                showWarningMessage("Please Enter Same Password");
                passwordField1.setText("");
                passwordField2.setText("");
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String typedOTP = verifyField.getText().trim();

        if (typedOTP.isEmpty()) {
            showWarningMessage("Please enter OTP Code");
        } else {
            try {
                ResultSet loginDeatilsRs = new LoginController().showLoginDeatils(EMPID);

                if (loginDeatilsRs.next()) {
                    if (typedOTP.equals(loginDeatilsRs.getString("otp_code"))) {
                        passwordPanel.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid OTP Code", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        passwordPanel.setVisible(false);
        verificationPanel.setVisible(false);
        String email = emailField.getText().trim();

        if (email.isEmpty()) {
            showWarningMessage("Please enter your email");
        } else if (!RegexValidator.isValidEmail(email)) {
            showWarningMessage("Invalid email");
        } else {
            String generatedOTP = generateOTP();
            new Mailer().sendMail(email, "Dazzle Auto - Password Change Request", new MailTemplates().otpSendMailTemplate(generatedOTP), null, true);

            try {
                MySqlConnection.executeIUD("UPDATE `login` SET `otp_code`='" + generatedOTP + "' WHERE `employee_id`='" + EMPID + "' ");
                verificationPanel.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel empIDField;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField passwordField1;
    private javax.swing.JPasswordField passwordField2;
    public javax.swing.JPanel passwordPanel;
    private javax.swing.JLabel title1;
    public javax.swing.JPanel verificationPanel;
    private javax.swing.JTextField verifyField;
    // End of variables declaration//GEN-END:variables
}
