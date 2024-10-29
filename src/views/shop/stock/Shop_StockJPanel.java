/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.shop.stock;

import controllers.ProductBrandController;
import controllers.ProductController;
import controllers.StockController;
import includes.LoggerConfig;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.Vector;
import java.sql.ResultSet;
import views.shop.shopInvoice.ShopInvoice;

/**
 *
 * @author Dinuka
 */
public class Shop_StockJPanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();

    Shop_StockJPanel thisPanel = this;
    JStockSelector StockSelectorFrame = null;

    ShopInvoice shopInvoice = null;
    String From = "";
    String BaseFrame = "";

    private String tableName = "stock";
    private String executeQuery;
    private boolean isFinded = false;

    public Shop_StockJPanel() {
        initComponents();
        StockTableRender();
        sortby();
        searchByPrice();
    }

    public Shop_StockJPanel(Frame parentFrame, JStockSelector stockSelector, String BaseFrame) {
        initComponents();
        StockTableRender();
        sortby();
        searchByPrice();

        this.StockSelectorFrame = stockSelector;
        this.From = "Selecter";
        this.BaseFrame = BaseFrame;
        
        if (BaseFrame.equals("ShopInvoice")) {
            this.shopInvoice = (ShopInvoice) parentFrame;
        }
    }

    public void StockTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = StockViewTable.getTableHeader();

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

        for (int i = 0; i < 6; i++) {
            StockViewTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
    //sort by option

    public void sortby() {
        try {

            String query = "SELECT * FROM `" + tableName + "` "
                    + "INNER JOIN product ON `" + tableName + "`.product_id =product.id "
                    + "INNER JOIN product_brand ON product.brand_id =product_brand.id ";

            String sort = String.valueOf(jComboBox1.getSelectedItem());

            if (sort.equals("Product ID A-Z")) {
                query += " ORDER BY`product`.`id` ASC";
            } else if (sort.equals("Product ID Z-A")) {
                query += " ORDER BY `product`.`id` DESC";
            } else if (sort.equals("Brand A-Z")) {
                query += " ORDER BY `product_brand`.`id` ASC";
            } else if (sort.equals("Brand Z-A")) {
                query += " ORDER BY `product_brand`.`id` DESC";
            }
            executeQuery = query;
            isFinded = false;

            loadTableData(query);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void searchByPrice() {
        try {

            String query = "SELECT * FROM `" + tableName + "` INNER JOIN `product`"
                    + "ON `" + tableName + "`.`product_id` = `product`.`id` "
                    + "INNER JOIN `product_brand` ON `product_brand`.`id` = `product`.`brand_id` WHERE ";

            double min_price = 0;
            double max_price = 0;

            if (!PriceFrom.getText().isEmpty()) {
                min_price = Double.parseDouble(PriceFrom.getText());
            }

            if (!PriceTo.getText().isEmpty()) {
                max_price = Double.parseDouble(PriceTo.getText());
            }

            if (min_price > 0 && max_price == 0) {
                query += "`" + tableName + "`.`price` > '" + min_price + "' ";
            } else if (min_price == 0 && max_price > 0) {
                query += "`" + tableName + "`.`price` < '" + max_price + "' ";
            } else if (min_price > 0 && max_price > 0) {
                query += "`" + tableName + "`.`price` > '" + min_price + "' AND `" + tableName + "`.`price` <  '" + max_price + "'";

                executeQuery = query;

                loadTableData(query);

                isFinded = true;

            } else if (min_price == 0.00 && max_price == 0.00) {
                sortby();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTableData(String query) {
        try {
            ResultSet resultSet = new StockController().customQuery(query);

            DefaultTableModel model = (DefaultTableModel) StockViewTable.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("product_id"));
                ResultSet product_rs = new ProductController().show(resultSet.getString("product_id"));
                if (product_rs.next()) {
                    vector.add(product_rs.getString("name"));
                }
                ResultSet brand_rs = new ProductBrandController().show(product_rs.getInt("brand_id"));
                if (brand_rs.next()) {
                    vector.add(brand_rs.getString("name"));
                }
                vector.add(resultSet.getString("qty"));
                vector.add(resultSet.getString("price"));
                model.addRow(vector);

            }
            StockViewTable.setModel(model);
        } catch (Exception e) {
            logger.severe("Error while loading table : " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        PriceTo = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        PriceFrom = new javax.swing.JFormattedTextField();
        PriceFindBtn2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StockViewTable = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1300, 609));
        setPreferredSize(new java.awt.Dimension(1300, 609));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(250, 238, 220));
        jLabel4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/stock-30.png"))); // NOI18N
        jLabel4.setText("  Stock");
        jLabel4.setOpaque(true);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel6.setText("Price");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, 32));

        PriceTo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        PriceTo.setText("0");
        PriceTo.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        PriceTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriceToActionPerformed(evt);
            }
        });
        jPanel2.add(PriceTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 92, 34));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel7.setText("TO");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, 32));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product ID A-Z", "Product ID Z-A", "Brand A-Z", "Brand Z-A" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 135, 39));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel10.setText("Sort By ID");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 32));

        PriceFrom.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        PriceFrom.setText("0");
        PriceFrom.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        PriceFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriceFromActionPerformed(evt);
            }
        });
        jPanel2.add(PriceFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 92, 34));

        PriceFindBtn2.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        PriceFindBtn2.setForeground(new java.awt.Color(5, 15, 76));
        PriceFindBtn2.setText("FIND");
        PriceFindBtn2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(5, 15, 76)));
        PriceFindBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriceFindBtn2ActionPerformed(evt);
            }
        });
        jPanel2.add(PriceFindBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 99, 36));

        jButton1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(222, 123, 14));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/refresh-yellow.png"))); // NOI18N
        jButton1.setText("Refresh");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 123, 14)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 20, 100, 40));

        StockViewTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        StockViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Product ID", "Product Name", "Brand", "Quantity", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        StockViewTable.setRowHeight(30);
        StockViewTable.getTableHeader().setReorderingAllowed(false);
        StockViewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StockViewTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(StockViewTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1309, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void PriceToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriceToActionPerformed
        // TODO add your handling code here:
        searchByPrice();
    }//GEN-LAST:event_PriceToActionPerformed

    private void PriceFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriceFromActionPerformed
        // TODO add your handling code here:
        searchByPrice();
    }//GEN-LAST:event_PriceFromActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        if (isFinded) {
            String sort = String.valueOf(jComboBox1.getSelectedItem());
            String orderBy = "ORDER BY";
           if (sort.equals("Product ID A-Z")) {
                orderBy = " ORDER BY`product`.`id` ASC";
            } else if (sort.equals("Product ID Z-A")) {
                orderBy = " ORDER BY `product`.`id` DESC";
            } else if (sort.equals("Brand A-Z")) {
                orderBy = " ORDER BY `product_brand`.`id` ASC";
            } else if (sort.equals("Brand Z-")) {
                orderBy = " ORDER BY `product_brand`.`id` DESC";
            }
            loadTableData(executeQuery + orderBy);
        } else {
            sortby();
        }


    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void PriceFindBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriceFindBtn2ActionPerformed
        // TODO add your handling code here:
        searchByPrice();
    }//GEN-LAST:event_PriceFindBtn2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        sortby();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void StockViewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StockViewTableMouseClicked
        int row = StockViewTable.getSelectedRow();
        String stockID = String.valueOf(StockViewTable.getValueAt(row, 0));
        String productName = String.valueOf(StockViewTable.getValueAt(row, 2));
        String brand = String.valueOf(StockViewTable.getValueAt(row, 3));
        String qty = String.valueOf(StockViewTable.getValueAt(row, 4));
        String sellingPrice = String.valueOf(StockViewTable.getValueAt(row, 5));

        if (From.equals("Selecter")) {
            if (BaseFrame.equals("ShopInvoice")) {
                shopInvoice.setStockDetails(stockID, brand, productName, qty, sellingPrice);
            }

            StockSelectorFrame.dispose();
        }
    }//GEN-LAST:event_StockViewTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PriceFindBtn2;
    private javax.swing.JFormattedTextField PriceFrom;
    private javax.swing.JFormattedTextField PriceTo;
    private javax.swing.JTable StockViewTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
