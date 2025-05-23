/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.shop.stock;

import com.formdev.flatlaf.FlatClientProperties;
import includes.LoggerConfig;
import includes.MySqlConnection;
import includes.OnlyNumbersDocumentFilter;
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
import javax.swing.text.AbstractDocument;
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

        loadStock();
        StockTableRender();
        setDocumentFilters();

        jTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Product Name or Brand Name");
    }

    public Shop_StockJPanel(Frame parentFrame, JStockSelector stockSelector, String BaseFrame) {
        initComponents();

        loadStock();
        StockTableRender();

        this.StockSelectorFrame = stockSelector;
        this.From = "Selecter";
        this.BaseFrame = BaseFrame;

        if (BaseFrame.equals("ShopInvoice")) {
            this.shopInvoice = (ShopInvoice) parentFrame;
        }
    }

    public void StockTableRender() {

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
            StockViewTable.getColumnModel().getColumn(i).setCellRenderer(new QuantityRenderer(4));
        }
    }

    private void setDocumentFilters() {
        ((AbstractDocument) PriceFrom.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) PriceTo.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());

    }

    private void loadStock() {
        String searchText = jTextField1.getText();
        String query = "SELECT * FROM `stock` "
                + "INNER JOIN `product` ON `stock`.`product_id`=`product`.`id` "
                + "INNER JOIN `product_brand` ON `product`.`brand_id`=`product_brand`.`id` "
                + "WHERE `product`.`name` LIKE '%" + searchText + "%' OR `product_brand`.`name` LIKE '%" + searchText + "%' ";

        double min_price = 0;
        double max_price = 0;

        if (!PriceFrom.getText().isEmpty()) {
            min_price = Double.parseDouble(PriceFrom.getText());
        }

        if (!PriceTo.getText().isEmpty()) {
            max_price = Double.parseDouble(PriceTo.getText());
        }

        if (min_price > 0 && max_price == 0) {
            query += " AND `" + tableName + "`.`price` > '" + min_price + "' ";
        } else if (min_price == 0 && max_price > 0) {
            query += " AND `" + tableName + "`.`price` < '" + max_price + "' ";
        } else if (min_price > 0 && max_price > 0) {
            query += " AND `" + tableName + "`.`price` > '" + min_price + "' AND `" + tableName + "`.`price` <  '" + max_price + "'";
        }
        String sort = String.valueOf(jComboBox1.getSelectedItem());

        if (sort.equals("Product ID A-Z")) {
            query += " ORDER BY `product`.`id` ASC";
        } else if (sort.equals("Product ID Z-A")) {
            query += " ORDER BY `product`.`id` DESC";
        } else if (sort.equals("Brand A-Z")) {
            query += " ORDER BY `product_brand`.`id` ASC";
        } else if (sort.equals("Brand Z-A")) {
            query += " ORDER BY `product_brand`.`id` DESC";
        }

        try {
            ResultSet resultSet = MySqlConnection.executeSearch(query);

            DefaultTableModel tableModel = (DefaultTableModel) StockViewTable.getModel();
            tableModel.setRowCount(0);

            while (resultSet.next()) {

                Vector vector = new Vector();
                vector.add(resultSet.getString("stock.id"));
                vector.add(resultSet.getString("stock.product_id"));
                vector.add(resultSet.getString("product.name"));
                vector.add(resultSet.getString("product_brand.name"));
                vector.add(resultSet.getString("stock.qty"));
                vector.add(resultSet.getString("stock.price"));

                tableModel.addRow(vector);
            }

            StockViewTable.setModel(tableModel);
            StockTableRender();
        } catch (Exception ex) {
            ex.printStackTrace();
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
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
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

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel6.setText("Price");

        PriceTo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        PriceTo.setText("0");
        PriceTo.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        PriceTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriceToActionPerformed(evt);
            }
        });
        PriceTo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PriceToKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("TO");

        jComboBox1.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product ID A-Z", "Product ID Z-A", "Brand A-Z", "Brand Z-A" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel10.setText("Sort By ");

        PriceFrom.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        PriceFrom.setText("0");
        PriceFrom.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        PriceFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriceFromActionPerformed(evt);
            }
        });
        PriceFrom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PriceFromKeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(222, 123, 14));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/refresh-yellow.png"))); // NOI18N
        jButton1.setText("Refresh");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 123, 14)));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel1.setText("Search Products");

        jTextField1.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PriceFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PriceTo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriceTo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriceFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(9, Short.MAX_VALUE))
        );

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
        StockViewTable.setFocusable(false);
        StockViewTable.setRowHeight(30);
        StockViewTable.getTableHeader().setReorderingAllowed(false);
        StockViewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StockViewTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(StockViewTable);
        if (StockViewTable.getColumnModel().getColumnCount() > 0) {
            StockViewTable.getColumnModel().getColumn(2).setPreferredWidth(600);
            StockViewTable.getColumnModel().getColumn(2).setMaxWidth(550);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    }//GEN-LAST:event_PriceToActionPerformed

    private void PriceFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriceFromActionPerformed

    }//GEN-LAST:event_PriceFromActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        loadStock();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void StockViewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StockViewTableMouseClicked
        int row = StockViewTable.getSelectedRow();
        String stockID = String.valueOf(StockViewTable.getValueAt(row, 0));
        String productName = String.valueOf(StockViewTable.getValueAt(row, 2));
        String brand = String.valueOf(StockViewTable.getValueAt(row, 3));
        String qty = String.valueOf(StockViewTable.getValueAt(row, 4));
        String selling_price = String.valueOf(StockViewTable.getValueAt(row, 5));

        if (From.equals("Selecter")) {
            if (BaseFrame.equals("ShopInvoice")) {
                shopInvoice.setStockDetails(stockID, brand, productName, qty, selling_price);
            }

            StockSelectorFrame.dispose();
        }
    }//GEN-LAST:event_StockViewTableMouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        loadStock();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void PriceFromKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PriceFromKeyReleased
        // TODO add your handling code here:
        loadStock();
    }//GEN-LAST:event_PriceFromKeyReleased

    private void PriceToKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PriceToKeyReleased
        // TODO add your handling code here:
        loadStock();
    }//GEN-LAST:event_PriceToKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField PriceFrom;
    private javax.swing.JFormattedTextField PriceTo;
    private javax.swing.JTable StockViewTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
