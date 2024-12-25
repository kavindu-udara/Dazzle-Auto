/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.shop.dashboard;

import com.formdev.flatlaf.FlatClientProperties;
import includes.LoggerConfig;
import includes.MySqlConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import views.components.charts.com.raven.chart.ModelChart;

/**
 *
 * @author Dinuka
 */
public class shop_DashboardJPanel extends javax.swing.JPanel {

    private static final Logger logger = LoggerConfig.getLogger();

    public shop_DashboardJPanel() {
        initComponents();
        roundPanels();
        reload();
    }

    public void reload() {
        setDailyIncome();
        setMonthlyIncome();
        setMonthlyPayment();
        setTodaySales();
        setRegisteredProducts();
        setRegisteredSuppliers();
        setRegisteredBrands();
        setMonthlyGrns();
        setTopSuppliers();
        stockOverview();
        bestSellings();
        setIssuedInvoices();

    }
    
    public void setIssuedInvoices() {
        chart.addLegend("Invoice Count", new Color(245, 189, 135));
        try {

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT * FROM( SELECT DATE_FORMAT(`date`, '%Y-%m-%d') AS `inv_date`, COUNT(id) AS `count` "
                    + "FROM `shop_invoice` GROUP BY `inv_date`ORDER BY `inv_date` DESC LIMIT 6) AS last_six ORDER BY `inv_date` ASC ");

            chart.model.clear();
            while (resultSet.next()) {
                String inv_date = resultSet.getString("inv_date");
                int count = resultSet.getInt("count");
                chart.addData(new ModelChart(inv_date, new double[]{count}));
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setIssuedInvoices() : " + e.getMessage());
        }      
    }

    public void bestSellings() {
        TableRender();

        // Format the current date to "YYYY-MM"
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM")); //WHERE `shop_invoice`.`date` LIKE '%" + month + "%'

        try {
            ResultSet resultSet = MySqlConnection.executeSearch("SELECT `product`.`name` AS `product`, SUM(shop_invoice_items.qty) AS `sell_qty`, stock.qty AS `stock_qty` "
                    + "FROM shop_invoice INNER JOIN shop_invoice_items ON shop_invoice_items.shop_invoice_id=shop_invoice.id "
                    + "INNER JOIN stock ON shop_invoice_items.stock_id=stock.id "
                    + "INNER JOIN product ON stock.product_id=product.id  "
                    + "GROUP BY `product`, `stock_qty` ORDER BY `sell_qty` DESC LIMIT 5 ");

            DefaultTableModel dtm = (DefaultTableModel) bestSellingTable.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("product"));
                vector.add(resultSet.getString("sell_qty"));
                vector.add(resultSet.getString("stock_qty"));

                dtm.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading bestSellings() : " + e.getMessage());
        }

    }

    public void stockOverview() {
        TableRender();

        try {
            ResultSet resultSet = MySqlConnection.executeSearch("SELECT `product`.`name`, `stock`.`price`, `stock`.`qty` FROM stock "
                    + "INNER JOIN product ON stock.product_id=product.id "
                    + "ORDER BY `stock`.`qty` DESC LIMIT 3  ");

            DefaultTableModel dtm = (DefaultTableModel) highStockTable.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("name"));
                vector.add(resultSet.getString("price"));
                vector.add(resultSet.getString("qty"));

                dtm.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading stockOverview() : " + e.getMessage());
        }

        try {
            ResultSet resultSet = MySqlConnection.executeSearch("SELECT `product`.`name`, `stock`.`price`, `stock`.`qty` FROM stock "
                    + "INNER JOIN product ON stock.product_id=product.id "
                    + "ORDER BY `stock`.`qty` ASC LIMIT 3  ");

            DefaultTableModel dtm = (DefaultTableModel) lowStockTable.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("name"));
                vector.add(resultSet.getString("price"));
                vector.add(resultSet.getString("qty"));

                dtm.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading stockOverview() : " + e.getMessage());
        }
    }

    public void setTopSuppliers() {
        ArrayList<String> supplierArry = new ArrayList<>();

        try {
            // Format the current date to "YYYY-MM"
            String month = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT CONCAT(supplier.first_name,' ', supplier.last_name) AS `fullname`,COUNT(grn.id) AS `grn_count`,`supplier_id` "
                    + "FROM grn INNER JOIN supplier ON grn.supplier_id=supplier.id WHERE `grn`.`date` LIKE '%" + month + "%' "
                    + "GROUP BY `supplier_id` ORDER BY `grn_count` DESC ");

            int row = 1;
            while (resultSet.next()) {
                supplierArry.add(row + ". " + resultSet.getString("fullname"));
                row++;
            }

            DefaultListModel<String> suppliermodel = new DefaultListModel<>();

            for (String supplliers : supplierArry) {
                suppliermodel.addElement(supplliers);
            }

            topSuppliersList.setModel(suppliermodel);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setTopSuppliers() : " + e.getMessage());
        }
    }

    public void setMonthlyGrns() {
        try {
            // Format the current date to "YYYY-MM"
            String month = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT COUNT(`id`) AS `grn_count` FROM `grn` WHERE `date` LIKE '%" + month + "%' ");

            String grn_count = "0";
            if (resultSet.next()) {
                grn_count = resultSet.getString("grn_count");
            }
            grnLabel.setText(grn_count);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setMonthlyGrns() : " + e.getMessage());
        }
    }

    public void setRegisteredBrands() {
        try {
            ResultSet resultSet = MySqlConnection.executeSearch("SELECT COUNT(`id`) AS `brand_count` FROM `product_brand` ");

            int brand_count = 0;
            if (resultSet.next()) {
                brand_count = resultSet.getInt("brand_count");
            }
            brandsLabel.setText(String.valueOf(brand_count));
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setRegisteredBrands() : " + e.getMessage());
        }
    }

    public void setRegisteredSuppliers() {
        try {
            ResultSet resultSet = MySqlConnection.executeSearch("SELECT COUNT(`id`) AS `sup_count` FROM `supplier` ");

            int sup_count = 0;
            if (resultSet.next()) {
                sup_count = resultSet.getInt("sup_count");
            }
            suppliersLabel.setText(String.valueOf(sup_count));
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setRegisteredSuppliers() : " + e.getMessage());
        }
    }

    public void setRegisteredProducts() {
        try {
            ResultSet resultSet = MySqlConnection.executeSearch("SELECT COUNT(`id`) AS `pcount` FROM `product` ");

            int pcount = 0;
            if (resultSet.next()) {
                pcount = resultSet.getInt("pcount");
            }
            productLabel.setText(String.valueOf(pcount));
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setRegisteredProducts() : " + e.getMessage());
        }
    }

    public void setMonthlyIncome() {
        try {
            // Format the current date to "YYYY-MM"
            String month = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT SUM(`total`) AS `total` FROM `shop_invoice` WHERE `date` LIKE '%" + month + "%'");

            String total = "0";
            if (resultSet.next()) {
                total = resultSet.getString("total");
                if (total == null) {
                    total = " 0.00";
                }
            }
            monthlyIncomeLabel.setText("Rs." + total);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setMonthlyIncome() : " + e.getMessage());
        }
    }

    public void setDailyIncome() {
        try {
            // Format the current date to "YYYY-MM"
            String day = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT SUM(`total`) AS `total` FROM `shop_invoice` WHERE `date` LIKE '%" + day + "%' ");

            String total = "0";
            if (resultSet.next()) {
                total = resultSet.getString("total");
                if (total == null) {
                    total = " 0.00";
                }
            }
            dailyIncomeLabel.setText("Rs." + total);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setDailyIncome() : " + e.getMessage());
        }
    }

    public void setMonthlyPayment() {
        try {
            // Format the current date to "YYYY-MM"
            String month = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT SUM(`paid_amount`) AS `total` FROM `grn` WHERE `date` LIKE '%" + month + "%' ");

            String total = "0";
            if (resultSet.next()) {
                total = resultSet.getString("total");
                if (total == null) {
                    total = " 0.00";
                }
            }
            paymentLabel.setText("Rs." + total);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setMonthlyPayment() : " + e.getMessage());
        }
    }

    public void setTodaySales() {
        try {
            // Format the current date to "YYYY-MM"
            String day = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            ResultSet resultSet = MySqlConnection.executeSearch("SELECT SUM(shop_invoice_items.qty) AS `sales` FROM shop_invoice INNER JOIN shop_invoice_items ON shop_invoice_items.shop_invoice_id=shop_invoice.id WHERE `shop_invoice`.`date` LIKE '%" + day + "%'");

            int sales = 0;
            if (resultSet.next()) {
                sales = resultSet.getInt("sales");
            }
            salesLabel.setText(String.valueOf(sales));
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setTodaySales() : " + e.getMessage());
        }
    }

    public void roundPanels() {
        cardPanel1.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        cardPanel2.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        cardPanel3.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        cardPanel4.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel3.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel4.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel6.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel7.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel8.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel9.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel10.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel11.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        jPanel12.putClientProperty(FlatClientProperties.STYLE, "arc:15");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        cardPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        monthlyIncomeLabel = new javax.swing.JLabel();
        cardPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        paymentLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lowStockTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        highStockTable = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cardPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dailyIncomeLabel = new javax.swing.JLabel();
        cardPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        salesLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        productLabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        suppliersLabel = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        chart = new views.components.charts.com.raven.chart.Chart();
        jPanel9 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        brandsLabel = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        grnLabel = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        topSuppliersList = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        bestSellingTable = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1300, 609));
        setPreferredSize(new java.awt.Dimension(1300, 609));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(250, 238, 220));
        jLabel4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/dashboard-40.png"))); // NOI18N
        jLabel4.setText("  Dashboard");
        jLabel4.setOpaque(true);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setBackground(new java.awt.Color(241, 239, 236));

        cardPanel4.setBackground(new java.awt.Color(97, 175, 20));
        cardPanel4.setPreferredSize(new java.awt.Dimension(255, 114));
        cardPanel4.setLayout(null);

        jLabel5.setBackground(new java.awt.Color(105, 185, 24));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/income-64.png"))); // NOI18N
        jLabel5.setOpaque(true);
        cardPanel4.add(jLabel5);
        jLabel5.setBounds(160, 0, 78, 90);

        jLabel6.setBackground(new java.awt.Color(105, 185, 24));
        jLabel6.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("   Monthly Income");
        jLabel6.setOpaque(true);
        cardPanel4.add(jLabel6);
        jLabel6.setBounds(0, 10, 250, 30);

        monthlyIncomeLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        monthlyIncomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        monthlyIncomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        monthlyIncomeLabel.setText("Rs. 1540000");
        cardPanel4.add(monthlyIncomeLabel);
        monthlyIncomeLabel.setBounds(6, 44, 159, 30);

        cardPanel1.setBackground(new java.awt.Color(70, 127, 239));
        cardPanel1.setLayout(null);

        jLabel11.setBackground(new java.awt.Color(79, 137, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/icons8-payment-64.png"))); // NOI18N
        jLabel11.setOpaque(true);
        cardPanel1.add(jLabel11);
        jLabel11.setBounds(160, -6, 78, 90);

        jLabel1.setBackground(new java.awt.Color(79, 137, 255));
        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("   Monthly Payments");
        jLabel1.setOpaque(true);
        cardPanel1.add(jLabel1);
        jLabel1.setBounds(-4, 8, 270, 30);

        paymentLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        paymentLabel.setForeground(new java.awt.Color(255, 255, 255));
        paymentLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        paymentLabel.setText("10");
        cardPanel1.add(paymentLabel);
        paymentLabel.setBounds(6, 44, 159, 30);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel52.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 67, 133));
        jLabel52.setText("Stock Overview");

        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        lowStockTable.setBackground(new java.awt.Color(242, 248, 255));
        lowStockTable.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        lowStockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Price", "Qty"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lowStockTable.setEnabled(false);
        lowStockTable.setFocusable(false);
        lowStockTable.setGridColor(new java.awt.Color(255, 255, 255));
        lowStockTable.setRowHeight(19);
        lowStockTable.setShowHorizontalLines(true);
        lowStockTable.setShowVerticalLines(true);
        lowStockTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(lowStockTable);
        if (lowStockTable.getColumnModel().getColumnCount() > 0) {
            lowStockTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            lowStockTable.getColumnModel().getColumn(1).setMaxWidth(90);
            lowStockTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            lowStockTable.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        jScrollPane3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        highStockTable.setBackground(new java.awt.Color(242, 248, 255));
        highStockTable.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        highStockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Price", "Qty"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        highStockTable.setEnabled(false);
        highStockTable.setFocusable(false);
        highStockTable.setGridColor(new java.awt.Color(255, 255, 255));
        highStockTable.setRowHeight(19);
        highStockTable.setShowHorizontalLines(true);
        highStockTable.setShowVerticalLines(true);
        highStockTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(highStockTable);
        if (highStockTable.getColumnModel().getColumnCount() > 0) {
            highStockTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            highStockTable.getColumnModel().getColumn(1).setMaxWidth(90);
            highStockTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            highStockTable.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        jLabel13.setBackground(new java.awt.Color(255, 234, 234));
        jLabel13.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Lowest");
        jLabel13.setOpaque(true);

        jLabel14.setBackground(new java.awt.Color(231, 255, 231));
        jLabel14.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 153, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Highest");
        jLabel14.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        cardPanel3.setBackground(new java.awt.Color(225, 170, 6));
        cardPanel3.setLayout(null);

        jLabel8.setBackground(new java.awt.Color(234, 176, 58));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/day-64.png"))); // NOI18N
        jLabel8.setOpaque(true);
        cardPanel3.add(jLabel8);
        jLabel8.setBounds(160, 0, 78, 90);

        jLabel3.setBackground(new java.awt.Color(234, 176, 58));
        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("   Today Income");
        jLabel3.setOpaque(true);
        cardPanel3.add(jLabel3);
        jLabel3.setBounds(-4, 8, 250, 30);

        dailyIncomeLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        dailyIncomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        dailyIncomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dailyIncomeLabel.setText("Rs. 154000");
        cardPanel3.add(dailyIncomeLabel);
        dailyIncomeLabel.setBounds(6, 44, 159, 30);

        cardPanel2.setBackground(new java.awt.Color(166, 76, 255));
        cardPanel2.setLayout(null);

        jLabel10.setBackground(new java.awt.Color(173, 93, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/sales-64.png"))); // NOI18N
        jLabel10.setOpaque(true);
        cardPanel2.add(jLabel10);
        jLabel10.setBounds(160, 0, 78, 90);

        jLabel2.setBackground(new java.awt.Color(173, 93, 255));
        jLabel2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("   Today Sales");
        jLabel2.setOpaque(true);
        cardPanel2.add(jLabel2);
        jLabel2.setBounds(-4, 8, 260, 30);

        salesLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        salesLabel.setForeground(new java.awt.Color(255, 255, 255));
        salesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salesLabel.setText("30");
        cardPanel2.add(salesLabel);
        salesLabel.setBounds(6, 44, 159, 30);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 67, 133));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Products Count");

        productLabel.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        productLabel.setForeground(new java.awt.Color(0, 60, 151));
        productLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productLabel.setText("00");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productLabel)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        suppliersLabel.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        suppliersLabel.setForeground(new java.awt.Color(0, 60, 151));
        suppliersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        suppliersLabel.setText("00");

        jLabel26.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 67, 133));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Registered Suppliers");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(suppliersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(suppliersLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel35.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 67, 133));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Issued Invoices");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel36.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 67, 133));
        jLabel36.setText("Top Customers");

        jLabel37.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(182, 182, 182));
        jLabel37.setText("This Month");

        jLabel38.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel38.setText("Customer 1");

        jLabel39.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel39.setText("Customer 2");

        jLabel40.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel40.setText("Customer 4");

        jLabel41.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel41.setText("Customer 3");

        jLabel42.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel42.setText("Customer 5");

        jLabel43.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel43.setText("0771234567");

        jLabel44.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("0771234567");

        jLabel45.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText("0771234567");

        jLabel46.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel46.setText("0771234567");

        jLabel47.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel47.setText("0771234567");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel47)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jLabel43))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(jLabel44))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(jLabel45))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(jLabel46))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel42)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        brandsLabel.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        brandsLabel.setForeground(new java.awt.Color(0, 60, 151));
        brandsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        brandsLabel.setText("00");

        jLabel28.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 67, 133));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Product Brands");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(brandsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brandsLabel)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        grnLabel.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        grnLabel.setForeground(new java.awt.Color(0, 60, 151));
        grnLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        grnLabel.setText("000");

        jLabel29.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 67, 133));
        jLabel29.setText("Issued GRNs");

        jLabel31.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(182, 182, 182));
        jLabel31.setText("This Month");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grnLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(grnLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel31)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 67, 133));
        jLabel33.setText(" Top Suppliers");

        jLabel34.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(182, 182, 182));
        jLabel34.setText("This Month");

        jScrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        topSuppliersList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        topSuppliersList.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        topSuppliersList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        topSuppliersList.setFixedCellHeight(30);
        topSuppliersList.setFocusable(false);
        jScrollPane6.setViewportView(topSuppliersList);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel53.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 67, 133));
        jLabel53.setText("Best Sellings");

        jScrollPane5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        bestSellingTable.setBackground(new java.awt.Color(242, 248, 255));
        bestSellingTable.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        bestSellingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Sold", "Left In Stock"
            }
        ));
        bestSellingTable.setEnabled(false);
        bestSellingTable.setFocusable(false);
        bestSellingTable.setGridColor(new java.awt.Color(255, 255, 255));
        bestSellingTable.setRowHeight(30);
        bestSellingTable.setShowHorizontalLines(true);
        bestSellingTable.setShowVerticalLines(true);
        bestSellingTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(bestSellingTable);
        if (bestSellingTable.getColumnModel().getColumnCount() > 0) {
            bestSellingTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            bestSellingTable.getColumnModel().getColumn(1).setMaxWidth(100);
            bestSellingTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            bestSellingTable.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        jLabel54.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(182, 182, 182));
        jLabel54.setText("This Month");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(cardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cardPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cardPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(cardPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(127, 127, 127))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(35, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cardPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 486, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE))
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
    private javax.swing.JTable bestSellingTable;
    private javax.swing.JLabel brandsLabel;
    private javax.swing.JPanel cardPanel1;
    private javax.swing.JPanel cardPanel2;
    private javax.swing.JPanel cardPanel3;
    private javax.swing.JPanel cardPanel4;
    private views.components.charts.com.raven.chart.Chart chart;
    private javax.swing.JLabel dailyIncomeLabel;
    private javax.swing.JLabel grnLabel;
    private javax.swing.JTable highStockTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable lowStockTable;
    private javax.swing.JLabel monthlyIncomeLabel;
    private javax.swing.JLabel paymentLabel;
    private javax.swing.JLabel productLabel;
    private javax.swing.JLabel salesLabel;
    private javax.swing.JLabel suppliersLabel;
    private javax.swing.JList<String> topSuppliersList;
    // End of variables declaration//GEN-END:variables

    private void TableRender() {

        //highStockTable
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = highStockTable.getTableHeader();

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Font headerFont = new Font("Verdana", Font.BOLD, 12);
                label.setBorder(BorderFactory.createEmptyBorder()); // Remove header borders
                label.setFont(headerFont);
                label.setBackground(new Color(0, 60, 151)); // Optional: Set header background color
                label.setForeground(Color.WHITE); // Optional: Set header text color
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return label;
            }
        });

        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 25));

        for (int i = 0; i < 3; i++) {
            highStockTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //highStockTable
        JTableHeader tableHeader2 = lowStockTable.getTableHeader();
        tableHeader2.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Font headerFont = new Font("Verdana", Font.BOLD, 12);
                label.setBorder(BorderFactory.createEmptyBorder()); // Remove header borders
                label.setFont(headerFont);
                label.setBackground(new Color(0, 60, 151)); // Optional: Set header background color
                label.setForeground(Color.WHITE); // Optional: Set header text color
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return label;
            }
        });
        tableHeader2.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 25));

        for (int i = 0; i < 3; i++) {
            lowStockTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // bestSellingTable customization
        JTableHeader tableHeader3 = bestSellingTable.getTableHeader();

        // Customize table header
        tableHeader3.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Font headerFont = new Font("Verdana", Font.BOLD, 12);
                label.setBorder(BorderFactory.createEmptyBorder()); // Remove header borders
                label.setFont(headerFont);
                label.setBackground(new Color(0, 60, 151)); // Optional: Set header background color
                label.setForeground(Color.WHITE); // Optional: Set header text color
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return label;
            }
        });

        // Set the preferred size for the table header
        tableHeader3.setPreferredSize(new Dimension(tableHeader3.getPreferredSize().width, 25));

        // Center alignment renderer for other columns
        DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
        centerRenderer2.setHorizontalAlignment(SwingConstants.CENTER);

        // Apply center alignment to all columns
        for (int i = 0; i < 3; i++) {
            bestSellingTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer2);
        }

        // Custom renderer for a single column (e.g., the second column, index 1)
        TableCellRenderer customColumnRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Customize background and foreground colors for this column
                if (!isSelected) {
//                    label.setBackground(new Color(150, 230, 140));
                    label.setForeground(new Color(0, 128, 6)); // Text color
                } else {
                    label.setBackground(table.getSelectionBackground()); // Keep selection color
                    label.setForeground(table.getSelectionForeground());
                }

                label.setFont(new Font("Roboto", Font.BOLD, 16));
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text

                return label;
            }
        };

        // Apply the custom renderer to a specific column (e.g., column index 1)
        bestSellingTable.getColumnModel().getColumn(1).setCellRenderer(customColumnRenderer);

    }

    // Custom Renderer Class
    static class CustomColumnRenderer extends DefaultTableCellRenderer {

        private final Color backgroundColor;

        public CustomColumnRenderer(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Set custom background color only if not selected
            if (!isSelected) {
                c.setBackground(backgroundColor);
            } else {
                c.setBackground(table.getSelectionBackground());
            }
            return c;
        }
    }

}
