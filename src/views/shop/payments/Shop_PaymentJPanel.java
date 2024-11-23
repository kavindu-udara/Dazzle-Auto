/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.shop.payments;

import com.formdev.flatlaf.FlatClientProperties;
import controllers.ShopInoviceController;
import includes.LoggerConfig;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import views.shop.shopInvoice.ShopInvoice;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import views.shop.dashboard.ShopDashboard;

/**
 *
 * @author Dinuka
 */
public class Shop_PaymentJPanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();

    ShopDashboard ShopDashboard = null;

    public Shop_PaymentJPanel(ShopDashboard dashboard) {
        initComponents();
        this.ShopDashboard = dashboard;

        jInvoiceSerachField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter INVOICE ID");

        PaidInvoiceTableRender();
        loadInvoices();
    }

    private void PaidInvoiceTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = jPaidInvoiceTable.getTableHeader();

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

        for (int i = 0; i < 7; i++) {
            jPaidInvoiceTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void loadInvoices() {
        try {
            ResultSet resultSet = new ShopInoviceController().search(jInvoiceSerachField.getText());

            DefaultTableModel dtm = (DefaultTableModel) jPaidInvoiceTable.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("date"));
                vector.add(resultSet.getString("total"));
                vector.add(resultSet.getString("paid_amount"));
                vector.add(resultSet.getString("balance"));
                vector.add(resultSet.getString("method"));
                vector.add(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));

                dtm.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading invoices : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        invoiceAddButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPaidInvoiceTable = new javax.swing.JTable();
        jInvoiceSerachField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1300, 609));
        setPreferredSize(new java.awt.Dimension(1300, 609));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(250, 238, 220));
        jLabel4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/cash-register-30.png"))); // NOI18N
        jLabel4.setText("  Payments");
        jLabel4.setOpaque(true);

        invoiceAddButton.setBackground(new java.awt.Color(199, 236, 199));
        invoiceAddButton.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        invoiceAddButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/add-3.png"))); // NOI18N
        invoiceAddButton.setText("  Add Invoice");
        invoiceAddButton.setBorderPainted(false);
        invoiceAddButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        invoiceAddButton.setFocusable(false);
        invoiceAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceAddButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel5.setText("Paid Invoices");

        jPaidInvoiceTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jPaidInvoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INVOICE ID", "Date", "Total", "Paid Amount", "Balance", "Payment Method", "Issued By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jPaidInvoiceTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPaidInvoiceTable.setFocusable(false);
        jPaidInvoiceTable.setRowHeight(30);
        jPaidInvoiceTable.getTableHeader().setReorderingAllowed(false);
        jPaidInvoiceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPaidInvoiceTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jPaidInvoiceTable);

        jInvoiceSerachField.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jInvoiceSerachField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInvoiceSerachFieldActionPerformed(evt);
            }
        });
        jInvoiceSerachField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jInvoiceSerachFieldKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Search INVOICE :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(invoiceAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jInvoiceSerachField, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1257, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(invoiceAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jInvoiceSerachField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
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

    private void invoiceAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceAddButtonActionPerformed
        ShopInvoice shopInvoice = new ShopInvoice(this);
        shopInvoice.setVisible(true);
    }//GEN-LAST:event_invoiceAddButtonActionPerformed

    private void jInvoiceSerachFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jInvoiceSerachFieldKeyReleased
        loadInvoices();
    }//GEN-LAST:event_jInvoiceSerachFieldKeyReleased

    private void jPaidInvoiceTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPaidInvoiceTableMouseClicked
        int row = jPaidInvoiceTable.getSelectedRow();

        if (row != -1) {
            String invoiceID = String.valueOf(jPaidInvoiceTable.getValueAt(row, 0));

            ShopDashboard.jPaymentsPanel.remove(this);
            SwingUtilities.updateComponentTreeUI(ShopDashboard.jPaymentsPanel);

            ShopInvoiceItemsPanel invoiceItemsPanel = new ShopInvoiceItemsPanel(ShopDashboard, invoiceID);
            ShopDashboard.jPaymentsPanel.add(invoiceItemsPanel, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }//GEN-LAST:event_jPaidInvoiceTableMouseClicked

    private void jInvoiceSerachFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInvoiceSerachFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jInvoiceSerachFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton invoiceAddButton;
    private javax.swing.JTextField jInvoiceSerachField;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTable jPaidInvoiceTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
