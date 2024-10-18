/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views.ourServices;

import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dinuka
 */
public class OurServicesSelecter extends javax.swing.JDialog {

    Frame parentFrame = null;
    String BaseFrame = "";
    
    public OurServicesSelecter(java.awt.Frame parent, boolean modal, String parentFrameName, String vehicleType) {
        super(parent, modal);
        this.parentFrame = parent;
        this.BaseFrame = parentFrameName;
        initComponents();
        
        ourServicesJPanel ourServices = new ourServicesJPanel(parent, this, parentFrameName, vehicleType);
        jPanel1.add(ourServices, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1100, 644));
        setPreferredSize(new java.awt.Dimension(1100, 644));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1100, 644));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 644));
        jPanel1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
