/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views.settings;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import views.dashboard.Dashboard;
import views.database.DBManagePanel;

/**
 *
 * @author Dinuka
 */
public class Settings extends javax.swing.JDialog {

    Settings settings = this;

    public Settings(java.awt.Frame parent, boolean modal, String clickedPath) {
        super(parent, modal);
        initComponents();
        addJPanels();

        if (clickedPath.equals("jLoginAccessMenu")) {
            jTabbedPane1.setSelectedIndex(0);
        } else if (clickedPath.equals("jDatabaseMenuItem")) {
            jTabbedPane1.setSelectedIndex(1);
        }

        if (Dashboard.loginModel.getAccessRoleId() != 1) {
            
            jTabbedPane1.setEnabledAt(0, false);
            
            // Add a ChangeListener to restrict tab selection
            jTabbedPane1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    int selectedIndex = jTabbedPane1.getSelectedIndex();

                    if (selectedIndex == 0) { // Restrict Tab 1
                        JOptionPane.showMessageDialog(null, "Login Access is restricted !");
                        jTabbedPane1.setSelectedIndex(1); // Force back to Tab 2
                    }
                }
            });
        }
    }

    private void addJPanels() {
        LoginAccessJPanel loginAccessJPanel = new LoginAccessJPanel(this);
        loginAccessTab.add(loginAccessJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);

        DBManagePanel dBManagePanel = new DBManagePanel();
        dbBackupTab.add(dBManagePanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
        
        LoginRecordPanel loginRecordPanel = new LoginRecordPanel();
        loginRecordTab.add(loginRecordPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        loginAccessTab = new javax.swing.JPanel();
        dbBackupTab = new javax.swing.JPanel();
        loginRecordTab = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Settings");
        setMinimumSize(new java.awt.Dimension(853, 606));

        jTabbedPane1.setBackground(new java.awt.Color(243, 248, 255));
        jTabbedPane1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jTabbedPane1.setOpaque(true);

        loginAccessTab.setBackground(new java.awt.Color(255, 255, 255));
        loginAccessTab.setMinimumSize(new java.awt.Dimension(853, 575));
        loginAccessTab.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Login Access    ", loginAccessTab);

        dbBackupTab.setBackground(new java.awt.Color(255, 255, 255));
        dbBackupTab.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Dump Database    ", dbBackupTab);

        loginRecordTab.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Login Record     ", loginRecordTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel dbBackupTab;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel loginAccessTab;
    private javax.swing.JPanel loginRecordTab;
    // End of variables declaration//GEN-END:variables
}
