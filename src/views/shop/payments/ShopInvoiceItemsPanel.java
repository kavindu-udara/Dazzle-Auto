/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.shop.payments;

import controllers.ProductBrandController;
import controllers.ProductController;
import controllers.ShopInvoiceItemsController;
import controllers.StockController;
import includes.LoggerConfig;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import views.shop.dashboard.ShopDashboard;

/**
 *
 * @author Dinuka
 */
public class ShopInvoiceItemsPanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();

    ShopDashboard shopDashboard = null;
    String invoiceID = "";

    public ShopInvoiceItemsPanel(ShopDashboard dashboard, String invoiceid) {
        initComponents();
        this.shopDashboard = dashboard;
        this.invoiceID = invoiceid;
        
        invoiceItemLabel.setText(invoiceid);

        InvoiceItemTableRender();
        loadInvoiceItems();
    }

    private void InvoiceItemTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = jInvoiceItemsTable.getTableHeader();

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

        for (int i = 0; i < 4; i++) {
            jInvoiceItemsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void loadInvoiceItems() {
        String product = "";
        String brand = "";

        try {
            ResultSet invoiceItemRs = new ShopInvoiceItemsController().show(invoiceID);

            DefaultTableModel dtm = (DefaultTableModel) jInvoiceItemsTable.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (invoiceItemRs.next()) {
                row++;
                Vector<String> vector = new Vector<>();

                ResultSet stockRs = new StockController().show(invoiceItemRs.getInt("stock_id"));
                if (stockRs.next()) {
                    ResultSet productRs = new ProductController().show(stockRs.getString("product_id"));
                    if (productRs.next()) {
                        product = productRs.getString("name");

                        ResultSet brandRs = new ProductBrandController().show(productRs.getInt("brand_id"));
                        if (brandRs.next()) {
                            brand = brandRs.getString("name");
                        }
                    }

                    vector.add(product + "-" + brand);
                    vector.add(String.valueOf(stockRs.getDouble("price")));
                }

                vector.add(String.valueOf(invoiceItemRs.getDouble("qty")));
                vector.add(invoiceItemRs.getString("description"));

                dtm.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loadInvoiceItems() : " + e.getMessage());
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
        jInvoiceItemsTable = new javax.swing.JTable();
        jInvoiceSerachField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        invoiceItemLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1300, 609));

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
        invoiceAddButton.setEnabled(false);
        invoiceAddButton.setFocusable(false);
        invoiceAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceAddButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel5.setText("Paid Invoices");

        jInvoiceItemsTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jInvoiceItemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Price", "Qty", "Additional Notes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jInvoiceItemsTable.setRowHeight(30);
        jInvoiceItemsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jInvoiceItemsTable);
        if (jInvoiceItemsTable.getColumnModel().getColumnCount() > 0) {
            jInvoiceItemsTable.getColumnModel().getColumn(1).setPreferredWidth(300);
            jInvoiceItemsTable.getColumnModel().getColumn(1).setMaxWidth(250);
            jInvoiceItemsTable.getColumnModel().getColumn(2).setPreferredWidth(300);
            jInvoiceItemsTable.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        jInvoiceSerachField.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jInvoiceSerachField.setEnabled(false);
        jInvoiceSerachField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jInvoiceSerachFieldKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Search INVOICE :");

        invoiceItemLabel.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        invoiceItemLabel.setForeground(new java.awt.Color(0, 102, 0));
        invoiceItemLabel.setText("INV-00000000");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setText("INVOICE NUMBER :");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/back-35.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(invoiceItemLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addGap(23, 23, 23)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(invoiceAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jInvoiceSerachField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(invoiceItemLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
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

    }//GEN-LAST:event_invoiceAddButtonActionPerformed

    private void jInvoiceSerachFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jInvoiceSerachFieldKeyReleased

    }//GEN-LAST:event_jInvoiceSerachFieldKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        shopDashboard.jPaymentsPanel.remove(this);
        SwingUtilities.updateComponentTreeUI(shopDashboard.jPaymentsPanel);

        shopDashboard.shop_PaymentJPanel = new Shop_PaymentJPanel(shopDashboard);
        shopDashboard.jPaymentsPanel.add(shopDashboard.shop_PaymentJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton invoiceAddButton;
    private javax.swing.JLabel invoiceItemLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JTable jInvoiceItemsTable;
    private javax.swing.JTextField jInvoiceSerachField;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
