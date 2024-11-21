/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.reports;

import controllers.ProductBrandController;
import controllers.ProductController;
import controllers.StockController;
import includes.LoggerConfig;
import includes.OnlyNumbersDocumentFilter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import models.LoginModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import views.dashboard.Dashboard;

/**
 *
 * @author Dumindu
 */
public class StockReportPanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();

    Dashboard dashboard = null;

    private String executeQuery;

    public StockReportPanel(Dashboard dashboard) {
        initComponents();
        LoadStock();
        StockTableRender();
        setDocumentFilters();
        this.dashboard = dashboard;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        StockTable = new javax.swing.JTable();
        viewReportb = new javax.swing.JButton();
        printReportb = new javax.swing.JButton();
        PriceFrom = new javax.swing.JFormattedTextField();
        PriceTo = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        QtyFrom = new javax.swing.JFormattedTextField();
        QtyTo = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Brand_chooser = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1100, 610));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 11, 610));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 42, 1065, -1));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/icons8-reports-30.png"))); // NOI18N
        jLabel1.setText("  Stock Report");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 6, 969, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/back-35.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 6, -1, 26));

        StockTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        StockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Product Id", "Product Name", "Brand", "Quantity", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        StockTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        StockTable.setFocusable(false);
        StockTable.setRowHeight(40);
        StockTable.getTableHeader().setReorderingAllowed(false);
        StockTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StockTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(StockTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 130, 1063, 400));

        viewReportb.setBackground(new java.awt.Color(51, 51, 51));
        viewReportb.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        viewReportb.setForeground(new java.awt.Color(255, 255, 255));
        viewReportb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/analyze-30.png"))); // NOI18N
        viewReportb.setText(" Save Report");
        viewReportb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewReportb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReportbActionPerformed(evt);
            }
        });
        jPanel1.add(viewReportb, new org.netbeans.lib.awtextra.AbsoluteConstraints(607, 542, 226, 45));

        printReportb.setBackground(new java.awt.Color(0, 102, 0));
        printReportb.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        printReportb.setForeground(new java.awt.Color(255, 255, 255));
        printReportb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/print-30.png"))); // NOI18N
        printReportb.setText(" Print Report");
        printReportb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printReportb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReportbActionPerformed(evt);
            }
        });
        jPanel1.add(printReportb, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 542, 237, 45));

        PriceFrom.setForeground(new java.awt.Color(0, 0, 204));
        PriceFrom.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        PriceFrom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PriceFromKeyReleased(evt);
            }
        });
        jPanel1.add(PriceFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 152, 31));

        PriceTo.setForeground(new java.awt.Color(0, 0, 204));
        PriceTo.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        PriceTo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PriceToKeyReleased(evt);
            }
        });
        jPanel1.add(PriceTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 152, 31));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel2.setText("To");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setText("Price From");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        QtyFrom.setForeground(new java.awt.Color(0, 0, 204));
        QtyFrom.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        QtyFrom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                QtyFromKeyReleased(evt);
            }
        });
        jPanel1.add(QtyFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 80, 90, 31));

        QtyTo.setForeground(new java.awt.Color(0, 0, 204));
        QtyTo.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        QtyTo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                QtyToKeyReleased(evt);
            }
        });
        jPanel1.add(QtyTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 80, 90, 31));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel4.setText("To");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(37, 142, 1));
        jLabel5.setText("00");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 560, -1, -1));

        Brand_chooser.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Brand_chooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Brand_chooser.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Brand_chooserItemStateChanged(evt);
            }
        });
        Brand_chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Brand_chooserActionPerformed(evt);
            }
        });
        jPanel1.add(Brand_chooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 110, 40));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setText("Number of Products :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, -1, -1));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel8.setText("Brand");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel6.setText("Quantity");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, -1, -1));

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dashboard.jReportPanel.remove(this);
        SwingUtilities.updateComponentTreeUI(dashboard.jReportPanel);

        dashboard.reportsJPanel = new ReportsJPanel(dashboard);
        dashboard.jReportPanel.add(dashboard.reportsJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dashboard.jReportPanel);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void StockTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StockTableMouseClicked

    }//GEN-LAST:event_StockTableMouseClicked

    private void setDocumentFilters() {
        ((AbstractDocument) PriceFrom.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) PriceTo.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) QtyFrom.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) QtyTo.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());

    }

    public void loadStockItems() {
        try {
            String query = "SELECT stock.id, stock.product_id, product.name AS product_name, "
                    + "product_brand.name AS brand_name, stock.qty, stock.price "
                    + "FROM `stock` "
                    + "INNER JOIN `product` ON `stock`.`product_id` = `product`.`id` "
                    + "INNER JOIN `product_brand` ON `product_brand`.`id` = `product`.`brand_id` ";

            String whereClause = "";
            String selectedBrand = Brand_chooser.getSelectedItem() != null ? String.valueOf(Brand_chooser.getSelectedItem()) : "All";

            double min_price = PriceFrom.getText().isEmpty() ? 0 : Double.parseDouble(PriceFrom.getText());
            double max_price = PriceTo.getText().isEmpty() ? 0 : Double.parseDouble(PriceTo.getText());

            int FromQty = QtyFrom.getText().isEmpty() ? 0 : Integer.parseInt(QtyFrom.getText());
            int ToQty = QtyTo.getText().isEmpty() ? 0 : Integer.parseInt(QtyTo.getText());

            if (!selectedBrand.equals("All")) {
                whereClause += "product_brand.name = '" + selectedBrand + "' ";
            }

            if (min_price > 0 && max_price == 0) {
                whereClause += (!whereClause.isEmpty() ? "AND " : "") + "`stock`.`price` > " + min_price + " ";
            } else if (min_price == 0 && max_price > 0) {
                whereClause += (!whereClause.isEmpty() ? "AND " : "") + "`stock`.`price` < " + max_price + " ";
            } else if (min_price > 0 && max_price > 0) {
                whereClause += (!whereClause.isEmpty() ? "AND " : "") + "`stock`.`price` > " + min_price + " AND `stock`.`price` < " + max_price + " ";
            }

            if (FromQty > 0 && FromQty == 0) {
                whereClause += (!whereClause.isEmpty() ? "AND " : "") + "`stock`.`qty` > " + FromQty + " ";
            } else if (FromQty == 0 && ToQty > 0) {
                whereClause += (!whereClause.isEmpty() ? "AND " : "") + "`stock`.`qty` < " + ToQty + " ";
            } else if (FromQty > 0 && ToQty > 0) {
                whereClause += (!whereClause.isEmpty() ? "AND " : "") + "`stock`.`qty` > " + FromQty + " AND `stock`.`qty` < " + ToQty + "";
            }
            if (!whereClause.isEmpty()) {
                query += "WHERE " + whereClause;
            }

            ResultSet resultSet = new StockController().customQuery(query);

            DefaultTableModel dtm = (DefaultTableModel) StockTable.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("product_id"));
                vector.add(resultSet.getString("product_name"));
                vector.add(resultSet.getString("brand_name"));
                vector.add(resultSet.getString("qty"));
                vector.add(resultSet.getString("price"));
                dtm.addRow(vector);
            }
            jLabel5.setText(String.valueOf(row));
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Error while loading stock items: " + e.getMessage());
        }
    }

    private void StockTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = StockTable.getTableHeader();

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
            StockTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private static HashMap<String, String> BrandMap = new HashMap<>();

    //load brands to sort button
    private void loadBrands() {

        try {
            ResultSet resultSet = new ProductBrandController().show();

            Vector<String> vector = new Vector<>();
            vector.add("All");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                BrandMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            Brand_chooser.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loding brands to sort button in Shop Items : " + e.getMessage());
        }
    }

    private void fetchBrands(String selectedBrand) {
        DefaultTableModel model = (DefaultTableModel) StockTable.getModel();
        model.setRowCount(0);

        try {
            String query = "SELECT stock.id, stock.product_id, product.name AS product_name, "
                    + "product_brand.name AS brand_name, stock.qty, stock.price "
                    + "FROM `stock` "
                    + "INNER JOIN `product` ON `stock`.`product_id` = `product`.`id` "
                    + "INNER JOIN `product_brand` ON `product_brand`.`id` = `product`.`brand_id` ";

            if (!selectedBrand.equals("All")) {
                query += "WHERE product_brand.name = '" + selectedBrand + "'";
            }

            ResultSet resultSet = new StockController().customQuery(query);

            int row = 0;
            while (resultSet.next()) {
                row++;
                String id = resultSet.getString("id");
                String productId = resultSet.getString("product_id");
                String productName = resultSet.getString("product_name");
                String brandName = resultSet.getString("brand_name");
                int qty = resultSet.getInt("qty");
                double price = resultSet.getDouble("price");

                model.addRow(new Object[]{id, productId, productName, brandName, qty, price});
            }

            jLabel5.setText(String.valueOf(row));

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while fetching brands: " + ex.getMessage());
        }
        loadStockItems();
    }

    public JasperPrint makeReport() {

        String dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa").format(new Date());

        String headerImg;
        try {
            InputStream s = this.getClass().getResourceAsStream("/resources/reports/StockReport.jasper");
            String img = new File(this.getClass().getResource("/resources/reports/dazzle_auto_tp.png").getFile()).getAbsolutePath();

            headerImg = img.replace("\\", "/");

            HashMap<String, Object> params = new HashMap<>();
            params.put("img", headerImg);
            params.put("status", String.valueOf(Brand_chooser.getSelectedItem()));
            params.put("employee", LoginModel.getFirstName() + " " + LoginModel.getLastName());
            params.put("reportDate", dateTime);

            if (!PriceFrom.getText().isEmpty()) {
                params.put("fromPrice", String.valueOf(PriceFrom.getText()));
            } else {
                params.put("fromPrice", "0.00");
            }
            if (!PriceTo.getText().isEmpty()) {
                params.put("toPrice", String.valueOf(PriceTo.getText()));
            } else {
                params.put("toPrice", "0.00");
            }

            if (!QtyFrom.getText().isEmpty()) {
                params.put("qtyFrom", String.valueOf(QtyFrom.getText()));
            } else {
                params.put("qtyFrom", "0");
            }
            if (!QtyTo.getText().isEmpty()) {
                params.put("qtyTo", String.valueOf(QtyTo.getText()));
            } else {
                params.put("qtyTo", "0");
            }

            JRTableModelDataSource dataSource = new JRTableModelDataSource(StockTable.getModel());

            JasperPrint report = JasperFillManager.fillReport(s, params, dataSource);

            return report;

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while makeReport() : " + e.getMessage());
        }
        return null;
    }

    private void viewReportbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportbActionPerformed

        try {
            JasperPrint report = makeReport();
            JasperViewer.viewReport(report, false);

            logger.info("Stock Report Viewed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while viewReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_viewReportbActionPerformed

    private void printReportbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReportbActionPerformed
        try {
            JasperPrint report = makeReport();
            JasperPrintManager.printReport(report, false);

            logger.info("Stock Report Printed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while printReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_printReportbActionPerformed

    private void PriceFromKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PriceFromKeyReleased
        // TODO add your handling code here:
        loadStockItems();
    }//GEN-LAST:event_PriceFromKeyReleased

    private void PriceToKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PriceToKeyReleased
        // TODO add your handling code here:
        loadStockItems();
    }//GEN-LAST:event_PriceToKeyReleased

    private void QtyFromKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QtyFromKeyReleased
        // TODO add your handling code here:
        loadStockItems();
    }//GEN-LAST:event_QtyFromKeyReleased

    private void QtyToKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QtyToKeyReleased
        // TODO add your handling code here:
        loadStockItems();
    }//GEN-LAST:event_QtyToKeyReleased

    private void Brand_chooserItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Brand_chooserItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String selectedBrand = String.valueOf(Brand_chooser.getSelectedItem());

            try {
                if (selectedBrand.equals("All")) {
                    reloadTable();
                } else {
                    fetchBrands(selectedBrand);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.severe("Error while sorting brands: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_Brand_chooserItemStateChanged

    public void reloadTable() {
        loadStockItems();
        loadBrands();
    }

    private void LoadStock() {
        try {
            loadBrands();
            loadStockItems();
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Error during LoadStock initialization: " + e.getMessage());
        }
    }
    private void Brand_chooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Brand_chooserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Brand_chooserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Brand_chooser;
    private javax.swing.JFormattedTextField PriceFrom;
    private javax.swing.JFormattedTextField PriceTo;
    private javax.swing.JFormattedTextField QtyFrom;
    private javax.swing.JFormattedTextField QtyTo;
    private javax.swing.JTable StockTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton printReportb;
    private javax.swing.JButton viewReportb;
    // End of variables declaration//GEN-END:variables
}
