/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views.myaccount;

import java.awt.BorderLayout;
import javax.swing.SwingUtilities;
import views.signIn.ForgotPassword;

/**
 *
 * @author E
 */
public class MyAccount extends javax.swing.JDialog {

    AccountInterface accountInterface = null;
    PasswordChange passwordChange = null;
    ForgotPassword forgotPassword = null;

    public MyAccount(java.awt.Frame parent, boolean modal, String From, String empID) {
        super(parent, modal);
        initComponents();

        if (From.equals("Account")) {
            if (accountInterface == null) {
                accountInterface = new AccountInterface(this);
                jPanel1.add(accountInterface, BorderLayout.CENTER);
                SwingUtilities.updateComponentTreeUI(jPanel1);
            }
        } else if (From.equals("Signin")) {
            if (forgotPassword == null) {
                forgotPassword = new ForgotPassword(this, empID);
                jPanel1.add(forgotPassword, BorderLayout.CENTER);
                SwingUtilities.updateComponentTreeUI(jPanel1);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(510, 534));
        jPanel1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
