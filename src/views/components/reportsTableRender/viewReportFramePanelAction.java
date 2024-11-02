/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.components.reportsTableRender;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author E
 */
public class viewReportFramePanelAction extends javax.swing.JPanel {

    /**
     * Creates new form PanelAction
     */
    public viewReportFramePanelAction() {
        initComponents();
    }

    public void initEvent(viewReportFrameActionEvent event, int row) {
        cmdDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.onView(row);
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdDelete = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        cmdDelete.setBackground(new java.awt.Color(255, 227, 213));
        cmdDelete.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/analysis-30.png"))); // NOI18N
        cmdDelete.setText("  Genarate");
        cmdDelete.setToolTipText("Change Status");
        cmdDelete.setBorderPainted(false);
        cmdDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdDelete.setFocusPainted(false);
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdDelete;
    // End of variables declaration//GEN-END:variables
}
