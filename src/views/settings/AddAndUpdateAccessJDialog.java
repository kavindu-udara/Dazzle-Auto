/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views.settings;

import controllers.AccessRoleController;
import controllers.LoginController;
import includes.IDGenarator;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import views.employee.EmployeeSelector;
import includes.LoggerConfig;
import includes.RegexValidator;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.LoginModel;

/**
 *
 * @author Dinuka
 */
public class AddAndUpdateAccessJDialog extends javax.swing.JDialog {

    private static Logger logger = LoggerConfig.getLogger();

    private static HashMap<String, Integer> accessRoleMap = new HashMap<>();

    public AddAndUpdateAccessJDialog(java.awt.Frame parent, boolean modal, String process) {
        super(parent, modal);
        initComponents();

        loadAccessRoleTypes();
        jUpdateButton.setEnabled(false);
    }

    public void setEmployeeDetails(String empID, String nic, String fname, String lname, String type) {
        jempIDLabel.setText(empID);
        jNICTextField.setText(nic);
        jFnameTextField.setText(fname);
        jLnameTextField.setText(lname);
        jEMPTypeTextField.setText(type);

        jLoginIDTextField.setText(IDGenarator.loginID(empID, fname));
    }

    private void loadAccessRoleTypes() {

        try {
            ResultSet resultSet = new AccessRoleController().show();

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("role"));
                accessRoleMap.put(resultSet.getString("role"), resultSet.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jAccesRoleComboBox.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loadAccessRoleTypes() : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jEmployeeSelectButton = new javax.swing.JButton();
        jFnameTextField = new javax.swing.JTextField();
        jNICTextField = new javax.swing.JTextField();
        jLnameTextField = new javax.swing.JTextField();
        jEMPTypeTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jAccesRoleComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPasswordTextField = new javax.swing.JTextField();
        jLoginIDTextField = new javax.swing.JTextField();
        jUpdateButton = new javax.swing.JButton();
        jSaveButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jempIDLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ADD OR UPDATE ACCESS");

        jEmployeeSelectButton.setBackground(new java.awt.Color(204, 0, 0));
        jEmployeeSelectButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jEmployeeSelectButton.setForeground(new java.awt.Color(255, 255, 255));
        jEmployeeSelectButton.setText("Select Employee");
        jEmployeeSelectButton.setBorderPainted(false);
        jEmployeeSelectButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jEmployeeSelectButton.setFocusPainted(false);
        jEmployeeSelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEmployeeSelectButtonActionPerformed(evt);
            }
        });

        jFnameTextField.setEditable(false);
        jFnameTextField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N

        jNICTextField.setEditable(false);
        jNICTextField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N

        jLnameTextField.setEditable(false);
        jLnameTextField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N

        jEMPTypeTextField.setEditable(false);
        jEMPTypeTextField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setText("First Name");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setText("Employee NIC");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setText("First Name");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setText("Employee Type");

        jAccesRoleComboBox.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jAccesRoleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setText("Select Access Role");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel7.setText("Login ID");

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel8.setText("Password");

        jPasswordTextField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N

        jLoginIDTextField.setEditable(false);
        jLoginIDTextField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N

        jUpdateButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jUpdateButton.setForeground(new java.awt.Color(33, 43, 108));
        jUpdateButton.setText("Update Access");
        jUpdateButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(33, 43, 108), 1, true));
        jUpdateButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jUpdateButton.setFocusPainted(false);

        jSaveButton.setBackground(new java.awt.Color(33, 43, 108));
        jSaveButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jSaveButton.setForeground(new java.awt.Color(255, 255, 255));
        jSaveButton.setText("Save New Access");
        jSaveButton.setBorderPainted(false);
        jSaveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSaveButton.setFocusPainted(false);
        jSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSaveButtonActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 0, 0));
        jButton4.setText("RESET");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/back-30.png"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setFocusPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jempIDLabel.setText("Emp ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jUpdateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jEMPTypeTextField)
                                        .addComponent(jFnameTextField)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jEmployeeSelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jempIDLabel))
                                        .addComponent(jLoginIDTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jNICTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                            .addComponent(jLnameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                            .addComponent(jAccesRoleComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jSaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jNICTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jEmployeeSelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jempIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jAccesRoleComboBox)
                    .addComponent(jEMPTypeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLoginIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
        new Settings(null, true).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jEmployeeSelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEmployeeSelectButtonActionPerformed
        new EmployeeSelector(this, true, "AddAndUpdateAccessJDialog").setVisible(true);
    }//GEN-LAST:event_jEmployeeSelectButtonActionPerformed

    private void jSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSaveButtonActionPerformed

        String empID = jempIDLabel.getText();
        String loginID = jLoginIDTextField.getText();
        String password = jPasswordTextField.getText();

        if (loginID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select Employee", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jAccesRoleComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please Select Access Role", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Password", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!RegexValidator.isValidPassword(password)) {
            JOptionPane.showMessageDialog(this, "The password must contain at least 8 characters, at least one lowercase letter, at least one uppercase letter, at least one digit, at least one special character", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            int accessRoleId = accessRoleMap.get(String.valueOf(jAccesRoleComboBox.getSelectedItem()));

            try {

                ResultSet rs = new LoginController().show(loginID);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Already Have Access For This Employee", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    LoginModel loginModel = new LoginModel();
                    loginModel.setId(loginID);
                    loginModel.setPassword(password);
                    loginModel.setEmployeeId(empID);
                    loginModel.setAccessRoleId(accessRoleId);

                    new LoginController().store(loginModel);

                    reset();

                    logger.info("New Login Access Added For : " + empID + " | LoginID " + loginID);

                }

            } catch (Exception e) {
                logger.warning("Error while jSaveButtonActionPerformed : " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jSaveButtonActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        reset();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jAccesRoleComboBox;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JTextField jEMPTypeTextField;
    private javax.swing.JButton jEmployeeSelectButton;
    private javax.swing.JTextField jFnameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jLnameTextField;
    private javax.swing.JTextField jLoginIDTextField;
    private javax.swing.JTextField jNICTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jPasswordTextField;
    private javax.swing.JButton jSaveButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jUpdateButton;
    private javax.swing.JLabel jempIDLabel;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jempIDLabel.setText("Emp ID");
        jNICTextField.setText("");
        jFnameTextField.setText("");
        jLnameTextField.setText("");
        jEMPTypeTextField.setText("");
        jAccesRoleComboBox.setSelectedIndex(0);
        jLoginIDTextField.setText("");
        jPasswordTextField.setText("");
    }
}
