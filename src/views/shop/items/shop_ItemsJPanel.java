/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.shop.items;

import java.awt.BorderLayout;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dinuka
 */
public class shop_ItemsJPanel extends javax.swing.JPanel {

    /**
     * Creates new form shop_ItemsJPanel
     */
    public shop_ItemsJPanel() {
        initComponents();
        
        Shop_ItemsView Shop_itemsView = new Shop_ItemsView();
        jtemsTabPanel.add(Shop_itemsView, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainjPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jtemsTabPanel = new javax.swing.JPanel();
        jSuppliersTabPanel = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(1300, 609));
        setPreferredSize(new java.awt.Dimension(1300, 609));

        mainjPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setMinimumSize(new java.awt.Dimension(1300, 570));
        jPanel2.setPreferredSize(new java.awt.Dimension(1300, 570));

        jTabbedPane1.setBackground(new java.awt.Color(250, 238, 220));
        jTabbedPane1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jTabbedPane1.setOpaque(true);

        jtemsTabPanel.setBackground(new java.awt.Color(51, 0, 51));
        jtemsTabPanel.setMinimumSize(new java.awt.Dimension(1300, 539));
        jtemsTabPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Items       ", jtemsTabPanel);

        jSuppliersTabPanel.setBackground(new java.awt.Color(0, 0, 102));
        jSuppliersTabPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Suppliers         ", jSuppliersTabPanel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainjPanelLayout = new javax.swing.GroupLayout(mainjPanel);
        mainjPanel.setLayout(mainjPanelLayout);
        mainjPanelLayout.setHorizontalGroup(
            mainjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainjPanelLayout.setVerticalGroup(
            mainjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainjPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jSuppliersTabPanel;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jtemsTabPanel;
    private javax.swing.JPanel mainjPanel;
    // End of variables declaration//GEN-END:variables
}
