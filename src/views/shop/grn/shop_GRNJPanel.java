/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.shop.grn;

import controllers.GrnController;
import controllers.GrnItemsController;
import controllers.StockController;
import includes.IDGenarator;
import includes.LoggerConfig;
import includes.RegexValidator;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import models.GrnItemsModel;
import models.LoginModel;
import models.ProductModel;
import models.SupplierModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import views.shop.items.SelectItems;
import views.supplier.SelectSuppliers;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import models.GrnModel;
import models.StockModel;

/**
 *
 * @author Dumindu
 */
public class shop_GRNJPanel extends javax.swing.JPanel {

    private static final Logger logger = LoggerConfig.getLogger();

    private shop_GRNJPanel shop_grnpanel;
    private ProductModel productModel;
    private SupplierModel supModel;

    HashMap<String, GrnItemsModel> GrnItemMap = new HashMap<>();

    public shop_GRNJPanel() {
        initComponents();
        GRNTableRender();
        GrnNumberField.setText(IDGenarator.GrnID());
        GrnNumberField.setEditable(false);
        EmployeeName.setText(LoginModel.getFirstName() + " " + LoginModel.getLastName());
        PaymenntField.setEditable(false);
        ProductIdField.setEditable(false);
        BrandNameField.setEditable(false);
    }

    public void GRNTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = GRNViewTable.getTableHeader();

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

        for (int i = 0; i < 8; i++) {
            GRNViewTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    //for Item Select
    public shop_GRNJPanel(java.awt.Frame parent, boolean modal, ProductModel ItemModel) {

        initComponents();
        this.productModel = ItemModel;
        ProductNameField.setText(ItemModel.getName());
        BrandNameField.setText(ItemModel.getbrandName());
        ProductIdField.setText(ItemModel.getItemId());
        ProductIdField.setEditable(false);

        GRNTableRender();
    }

    public void setSelectedProduct(ProductModel product) {
        // Set the fields in this panel with the product data
        this.productModel = product;
        ProductNameField.setText(product.getName());
        BrandNameField.setText(product.getbrandName());
        ProductIdField.setText(product.getItemId());
        ProductIdField.setEditable(false);
    }

    //for Supplier Select
    public shop_GRNJPanel(java.awt.Frame parent, boolean modal, SupplierModel supplierModel) {

        this.supModel = supplierModel;
        SupplierNameField.setText(supplierModel.getFirstName() + " " + supplierModel.getLastName());
        SupplierIdField.setText(supplierModel.getId());
        SupplierIdField.setEditable(false);

        GRNTableRender();
    }

    public void setSelectedSupplier(SupplierModel supplierModel) {
        // Set the fields in this panel with the Supplier data
        this.supModel = supplierModel;
        SupplierNameField.setText(supplierModel.getFirstName() + " " + supplierModel.getLastName());
        SupplierIdField.setText(supplierModel.getId());
        SupplierIdField.setEditable(false);
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
        jLabel1 = new javax.swing.JLabel();
        BrandNameField = new javax.swing.JTextField();
        ProductSelectBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        EmployeeName = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        GrnNumberField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        BuyingPriceField = new javax.swing.JTextField();
        SellingPriceField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        AddGRNBtn = new javax.swing.JButton();
        ClearAllBtn = new javax.swing.JButton();
        SupplierIdField = new javax.swing.JTextField();
        SupplierSelectBtn = new javax.swing.JButton();
        QtyField = new javax.swing.JTextField();
        ProductNameField = new javax.swing.JTextField();
        ProductIdField = new javax.swing.JTextField();
        SupplierNameField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        GRNViewTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        TotalField = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        PaymenntField = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        BalanceField = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        SaveGRN = new javax.swing.JButton();
        ClearEverythigbtn = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1300, 609));
        setPreferredSize(new java.awt.Dimension(1300, 609));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(250, 238, 220));
        jLabel4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/invoice-35.png"))); // NOI18N
        jLabel4.setText("GRN");
        jLabel4.setOpaque(true);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel1.setText("Product :");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 20));

        BrandNameField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(BrandNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 140, 31));

        ProductSelectBtn.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        ProductSelectBtn.setForeground(new java.awt.Color(33, 43, 108));
        ProductSelectBtn.setText("SELECT");
        ProductSelectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductSelectBtnActionPerformed(evt);
            }
        });
        jPanel2.add(ProductSelectBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 160, 31));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel2.setText("Product ID :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 20));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel5.setText("Brand Name :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 20));

        EmployeeName.setFont(new java.awt.Font("Roboto", 3, 16)); // NOI18N
        EmployeeName.setText("Emp_Name");
        jPanel2.add(EmployeeName, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 140, -1, -1));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel9.setText("Supplier ID :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, -1, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel10.setText("Quantity :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, -1, -1));

        GrnNumberField.setBackground(new java.awt.Color(255, 204, 51));
        GrnNumberField.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jPanel2.add(GrnNumberField, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, 160, 31));

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel11.setText("Buying Price :");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, -1, -1));
        jPanel2.add(BuyingPriceField, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 130, 31));
        jPanel2.add(SellingPriceField, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 130, 31));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel12.setText("Selling Price :");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, -1, -1));

        AddGRNBtn.setBackground(new java.awt.Color(33, 43, 108));
        AddGRNBtn.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        AddGRNBtn.setForeground(new java.awt.Color(255, 255, 255));
        AddGRNBtn.setText("ADD TO GRN");
        AddGRNBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddGRNBtnActionPerformed(evt);
            }
        });
        jPanel2.add(AddGRNBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 20, 190, 50));

        ClearAllBtn.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        ClearAllBtn.setForeground(new java.awt.Color(255, 0, 0));
        ClearAllBtn.setText("CLEAR ALL");
        ClearAllBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        ClearAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearAllBtnActionPerformed(evt);
            }
        });
        jPanel2.add(ClearAllBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 80, 190, 50));

        SupplierIdField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(SupplierIdField, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 150, 31));

        SupplierSelectBtn.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        SupplierSelectBtn.setForeground(new java.awt.Color(33, 43, 108));
        SupplierSelectBtn.setText("SELECT");
        SupplierSelectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupplierSelectBtnActionPerformed(evt);
            }
        });
        jPanel2.add(SupplierSelectBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, -1, 31));
        jPanel2.add(QtyField, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, 80, 31));

        ProductNameField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(ProductNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 260, 31));

        ProductIdField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(ProductIdField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 140, 31));

        SupplierNameField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(SupplierNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 150, 31));

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel13.setText("Supplier :");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, -1, -1));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel8.setText("GRN Number :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 70, -1, -1));

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel14.setText("Issued By :");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 140, -1, -1));

        GRNViewTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        GRNViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Brand", "Name", "Quantity", "Buying Price", "Selling Price", "Supplier ID", "Employee"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        GRNViewTable.setRowHeight(30);
        GRNViewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GRNViewTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(GRNViewTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel16.setText("Total");

        TotalField.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        TotalField.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TotalField.setText("0.00");

        jLabel17.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel17.setText("Payment");

        PaymenntField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        PaymenntField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        PaymenntField.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        PaymenntField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PaymenntFieldKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel19.setText("Balance");

        BalanceField.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        BalanceField.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BalanceField.setText("0.00");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        SaveGRN.setBackground(new java.awt.Color(33, 43, 108));
        SaveGRN.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        SaveGRN.setForeground(new java.awt.Color(255, 255, 255));
        SaveGRN.setText("SAVE GRN");
        SaveGRN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveGRNActionPerformed(evt);
            }
        });

        ClearEverythigbtn.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        ClearEverythigbtn.setForeground(new java.awt.Color(255, 0, 0));
        ClearEverythigbtn.setText("RESET ALL");
        ClearEverythigbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        ClearEverythigbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearEverythigbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TotalField, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PaymenntField, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ClearEverythigbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addGap(31, 31, 31)
                            .addComponent(BalanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(SaveGRN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(TotalField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PaymenntField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(26, 26, 26)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(BalanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(SaveGRN, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClearEverythigbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void ClearAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearAllBtnActionPerformed
        // TODO add your handling code here:
        resetInputs();

    }//GEN-LAST:event_ClearAllBtnActionPerformed

    private void PaymenntFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PaymenntFieldKeyReleased

        if (RegexValidator.isValidDecimal(PaymenntField.getText())) {

            jLabel17.setForeground(Color.BLACK);
            calculate();
//        
        }

    }//GEN-LAST:event_PaymenntFieldKeyReleased

    private double total = 0;
    private double payment = 0;
    private double balance = 0;

    private void calculate() {

        total = 0;
        for (int i = 0; i < GRNViewTable.getRowCount(); i++) {
            String BuyingPrice = String.valueOf(GRNViewTable.getValueAt(i, 4));
            String qty = String.valueOf(GRNViewTable.getValueAt(i, 3));

            double rowPrice = (Double.parseDouble(BuyingPrice)) * (Double.parseDouble(qty));
            total += rowPrice;

        }

        TotalField.setText(String.valueOf(total));

        if (PaymenntField.getText().isEmpty()) {
            payment = 0;
        } else {
            payment = Double.parseDouble(PaymenntField.getText());
        }

        total = Double.parseDouble(TotalField.getText());

        if (total < 0) {
            JOptionPane.showMessageDialog(this, "Enter a Valid Payment!", "Error", JOptionPane.ERROR_MESSAGE);
            PaymenntField.setText("0");
            BalanceField.setText("0");
        }

        balance = payment - total;
        PaymenntField.setEnabled(true);

        if (balance < 0) {
            SaveGRN.setEnabled(false);
        } else if (payment < total) {
            SaveGRN.setEnabled(false);
        } else {
            SaveGRN.setEnabled(true);
        }

        BalanceField.setText(String.valueOf(balance));

    }

    private void ProductSelectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductSelectBtnActionPerformed
        // TODO add your handling code here:

        SelectItems si = new SelectItems(null, true, this);
        si.setVisible(true);
        GrnNumberField.setText(IDGenarator.GrnID());
        GrnNumberField.setEditable(false);

    }//GEN-LAST:event_ProductSelectBtnActionPerformed

    private void SupplierSelectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupplierSelectBtnActionPerformed
        // TODO add your handling code here:
        SelectSuppliers selectSup = new SelectSuppliers(null, true, this);
        selectSup.setVisible(true);
    }//GEN-LAST:event_SupplierSelectBtnActionPerformed

    private void AddGRNBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddGRNBtnActionPerformed
        // TODO add your handling code here:
        String productId = ProductIdField.getText();
        String brand = BrandNameField.getText();
        String name = ProductNameField.getText();
        String qty = QtyField.getText();
        String buyingPrice = BuyingPriceField.getText();
        String sellingPrice = SellingPriceField.getText();
        String supplierId = SupplierIdField.getText();
        String employeeId = EmployeeName.getText();

        if (productId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select a Product !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (supplierId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select a Supplier !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (buyingPrice.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter a Buying Price !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (Double.parseDouble(buyingPrice) <= 0) {
            JOptionPane.showMessageDialog(this, "Please Enter a Valid Buying Price !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (sellingPrice.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter a Selling Price !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (Double.parseDouble(sellingPrice) <= 0) {
            JOptionPane.showMessageDialog(this, "Please Enter a Valid Selling Price !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if ((Double.parseDouble(sellingPrice)) < (Double.parseDouble(buyingPrice))) {
            JOptionPane.showMessageDialog(this, "Selling Price can't be lower than the Buying Price !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (qty.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter a Quantity !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (Double.parseDouble(qty) <= 0) {
            JOptionPane.showMessageDialog(this, "Please Enter a Valid Quantity !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            GrnItemsModel grnItem = new GrnItemsModel();
            grnItem.setProductId(productId);
            grnItem.setBrandName(brand);
            grnItem.setProductName(name);
            grnItem.setQty(Double.parseDouble(qty));
            grnItem.setBuyingPrice(Double.parseDouble(buyingPrice));
            grnItem.setSellingPrice(Double.parseDouble(sellingPrice));
            grnItem.setSupplierId(supplierId);
            grnItem.setEmployeeId(employeeId);

            if (GrnItemMap.get(productId + "" + supplierId + "" + brand) == null) {
                GrnItemMap.put(productId + "" + supplierId + "" + brand, grnItem);
            } else {

                GrnItemsModel foundGrn = GrnItemMap.get(productId + "" + supplierId + "" + brand);

                if (foundGrn.getProductId().equals(productId)) {
                    JOptionPane.showMessageDialog(this, "This GRN Record Already In Table", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    GrnItemMap.put(productId + "" + supplierId + "" + brand, grnItem);
                }

            }

            loadGrnItem();

        }

        PaymenntField.grabFocus();
        PaymenntField.setEditable(true);


    }//GEN-LAST:event_AddGRNBtnActionPerformed

    private void GRNViewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GRNViewTableMouseClicked


    }//GEN-LAST:event_GRNViewTableMouseClicked

    private int Stockid;

    private void SaveGRNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveGRNActionPerformed
        // TODO add your handling code here:

        try {
            String GrnID = GrnNumberField.getText();
            String supplierId = SupplierIdField.getText();
            String paidAmount = PaymenntField.getText();
            String dateTimeForDB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            try {

                GrnModel grnModel = new GrnModel();

                grnModel.setGrnId(GrnID);
                grnModel.setDate(dateTimeForDB);
                grnModel.setPaidAmount(Double.parseDouble(paidAmount));
                grnModel.setSupplierId(supplierId);
                grnModel.setEmployeeId(LoginModel.getEmployeeId());

                ResultSet resultSet = new GrnController().store(grnModel);

            } catch (Exception e) {
                e.printStackTrace();

            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while saving items to GRN table : " + e.getMessage());
        }

        try {

            String ProductId = ProductIdField.getText();
            String qty = QtyField.getText();
            String SellingPrice = SellingPriceField.getText();

            try {

                StockModel stockModel = new StockModel();

                stockModel.setPrice(Double.parseDouble(SellingPrice));
                stockModel.setQty(Double.parseDouble(qty));
                stockModel.setProductId(ProductId);

                ResultSet resultSet = new StockController().store(stockModel);
                //JOptionPane.showMessageDialog(this, "Add to Stock");

                if (resultSet.next()) {
                    int Stokid = resultSet.getInt(1);
                    Stockid = Stokid;
                }

            } catch (Exception e) {
                e.printStackTrace();

            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while saving items to Stock table : " + e.getMessage());
        }

        try {
            String GrnID = GrnNumberField.getText();
            String qty = QtyField.getText();
            String SellingPrice = SellingPriceField.getText();

            try {
//                   
                GrnItemsModel grnItemsModel = new GrnItemsModel();

                grnItemsModel.setGrnId(GrnID);
                grnItemsModel.setQty(Double.parseDouble(qty));
                grnItemsModel.setPrice(Double.parseDouble(SellingPrice));
                grnItemsModel.setStockId(Stockid);

                ResultSet resultSet = new GrnItemsController().store(grnItemsModel);
                JOptionPane.showMessageDialog(this, "Add to GRN ,GRN Items & Stock");
                reset();

            } catch (Exception e) {
                e.printStackTrace();

            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while saving items to GRN Item table : " + e.getMessage());

        }
        resetInputs();


    }//GEN-LAST:event_SaveGRNActionPerformed

    private void ClearEverythigbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearEverythigbtnActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(null, "Do you want to reset?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            reset();
        }
    }//GEN-LAST:event_ClearEverythigbtnActionPerformed

    public void loadGrnItem() {
        DefaultTableModel dtm = (DefaultTableModel) GRNViewTable.getModel();
        dtm.setRowCount(0);

        for (GrnItemsModel grnItem : GrnItemMap.values()) {

            Vector<String> vector = new Vector<>();
            vector.add(grnItem.getProductId());
            vector.add(grnItem.getBrandName());
            vector.add(grnItem.getProductName());
            vector.add(String.valueOf(grnItem.getQty()));
            vector.add(String.valueOf(grnItem.getBuyingPrice()));
            vector.add(String.valueOf(grnItem.getSellingPrice()));
            vector.add(grnItem.getSupplierId());
            vector.add(grnItem.getEmployeeId());

            dtm.addRow(vector);

            calculate();
        }
    }

    private void resetInputs() {
        ProductNameField.setText("");
        ProductIdField.setEditable(true);
        ProductIdField.setText("");
        BrandNameField.setText("");
        SupplierIdField.setEditable(true);
        SupplierIdField.setText("");
        BuyingPriceField.setText("");
        SellingPriceField.setText("");
        QtyField.setText("");
        GrnNumberField.setEditable(true);
        GrnNumberField.setText("");
        SupplierNameField.setText("");

    }

    private void reset() {
        ProductNameField.setText("");
        ProductIdField.setEditable(true);
        ProductIdField.setText("");
        BrandNameField.setText("");
        SupplierIdField.setEditable(true);
        SupplierIdField.setText("");
        BuyingPriceField.setText("");
        SellingPriceField.setText("");
        QtyField.setText("");
        GrnNumberField.setEditable(true);
        GrnNumberField.setText("");
        SupplierNameField.setText("");
        DefaultTableModel dtm = (DefaultTableModel) GRNViewTable.getModel();
        dtm.setRowCount(0);
        TotalField.setText("0.00");
        PaymenntField.setText("0.00");
        BalanceField.setText("0.00");
        GrnItemMap.clear();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddGRNBtn;
    private javax.swing.JLabel BalanceField;
    private javax.swing.JTextField BrandNameField;
    private javax.swing.JTextField BuyingPriceField;
    private javax.swing.JButton ClearAllBtn;
    private javax.swing.JButton ClearEverythigbtn;
    private javax.swing.JLabel EmployeeName;
    private javax.swing.JTable GRNViewTable;
    private javax.swing.JTextField GrnNumberField;
    private javax.swing.JFormattedTextField PaymenntField;
    private javax.swing.JTextField ProductIdField;
    private javax.swing.JTextField ProductNameField;
    private javax.swing.JButton ProductSelectBtn;
    private javax.swing.JTextField QtyField;
    private javax.swing.JButton SaveGRN;
    private javax.swing.JTextField SellingPriceField;
    private javax.swing.JTextField SupplierIdField;
    private javax.swing.JTextField SupplierNameField;
    private javax.swing.JButton SupplierSelectBtn;
    private javax.swing.JLabel TotalField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
