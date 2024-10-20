/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.settings;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import raven.toast.Notifications;
import includes.LoggerConfig;
import java.util.logging.Logger;
import views.components.loginAccessTableRender.DeleteActionCellEditor;
import views.components.loginAccessTableRender.DeleteActionEvent;
import views.components.loginAccessTableRender.DeleteCellRender;

/**
 *
 * @author Dinuka
 */
public class LoginAccessJPanel extends javax.swing.JPanel {
    
    private static Logger logger = LoggerConfig.getLogger();

    public LoginAccessJPanel() {
        initComponents();
        
        jNewAccessButton.putClientProperty(FlatClientProperties.STYLE, "arc:10");
        
        loginAccessTableTableRender();
    }

    
    private void loginAccessTableTableRender() {

        DeleteActionEvent event = new DeleteActionEvent() {

            @Override
            public void onView(int row) {

                String loginID = String.valueOf(jLoginAccessTable.getValueAt(row, 3));

                try {
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.warning("Error while loginAccessTableTableRender() : " + e.getMessage());
                }

            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = jLoginAccessTable.getTableHeader();

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Font headerFont = new Font("Verdana", Font.BOLD, 14);
                label.setBorder(BorderFactory.createEmptyBorder()); // Remove header borders
                label.setFont(headerFont);
                label.setBackground(new Color(5, 15, 76)); // Optional: Set header background color
                label.setForeground(Color.WHITE); // Optional: Set header text color
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return label;
            }
        });

        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));
        jLoginAccessTable.getColumnModel().getColumn(5).setCellRenderer(new DeleteCellRender());
        jLoginAccessTable.getColumnModel().getColumn(5).setCellEditor(new DeleteActionCellEditor(event));

        for (int i = 0; i < 5; i++) {
            jLoginAccessTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLoginAccessTable = new javax.swing.JTable();
        jNewAccessButton = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(853, 575));
        setPreferredSize(new java.awt.Dimension(853, 575));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(853, 575));

        jLoginAccessTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLoginAccessTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "bbnb", "bnbnbn", "bnbn", "bnbnbn", ""}
            },
            new String [] {
                "Employee ID", "Employee Name", "Access Role", "Login ID", "Password", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jLoginAccessTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLoginAccessTable.setFocusable(false);
        jLoginAccessTable.setRowHeight(40);
        jLoginAccessTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jLoginAccessTable);
        if (jLoginAccessTable.getColumnModel().getColumnCount() > 0) {
            jLoginAccessTable.getColumnModel().getColumn(5).setPreferredWidth(150);
            jLoginAccessTable.getColumnModel().getColumn(5).setMaxWidth(70);
        }

        jNewAccessButton.setBackground(new java.awt.Color(204, 0, 0));
        jNewAccessButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jNewAccessButton.setForeground(new java.awt.Color(255, 255, 255));
        jNewAccessButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/icons8-access-30.png"))); // NOI18N
        jNewAccessButton.setText("  Add New Access");
        jNewAccessButton.setBorderPainted(false);
        jNewAccessButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jNewAccessButton.setFocusPainted(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNewAccessButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jNewAccessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable jLoginAccessTable;
    private javax.swing.JButton jNewAccessButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
