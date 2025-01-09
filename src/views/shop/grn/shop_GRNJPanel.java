/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.shop.grn;

import controllers.GrnController;
import controllers.GrnItemsController;
import controllers.PaymentMethodController;
import controllers.StockController;
import includes.IDGenarator;
import includes.LoggerConfig;
import includes.OnlyNumbersDocumentFilter;
import includes.RegexValidator;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.InputStream;
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
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
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
    HashMap<String, String> paymentMethodmMap = new HashMap<>();

    public shop_GRNJPanel() {
        initComponents();
        GRNTableRender();
        setDocumentFilters();
        loadPaymentMethods();

        GrnNumberField.setText(IDGenarator.GrnID());
        SaveGRN.setEnabled(false);

        EmployeeName.setText(LoginModel.getFirstName() + " " + LoginModel.getLastName());
        PaymenntField.setEditable(false);
        ProductIdField.setFocusable(false);
        BrandNameField.setFocusable(false);
        ProductNameField.setEditable(false);
        SupplierNameField.setFocusable(false);
        SupplierIdField.setFocusable(false);
    }

    private void setDocumentFilters() {
        ((AbstractDocument) BuyingPriceField.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) SellingPriceField.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) QtyField.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());

    }

    private void loadPaymentMethods() {
        try {
            ResultSet reseltset = new PaymentMethodController().show();

            Vector<String> vector = new Vector<>();

            while (reseltset.next()) {
                vector.add(reseltset.getString("method"));
                paymentMethodmMap.put(reseltset.getString("method"), reseltset.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel<>(vector);
            PaymentMethods.setModel(comboBoxModel);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading payment methods : " + e.getMessage());
        }
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

        GRNTableRender();
    }

    public void setSelectedProduct(ProductModel product) {
        // Set the fields in this panel with the product data
        this.productModel = product;
        ProductNameField.setText(product.getName());
        BrandNameField.setText(product.getbrandName());
        ProductIdField.setText(product.getItemId());
    }

    //for Supplier Select
    public shop_GRNJPanel(java.awt.Frame parent, boolean modal, SupplierModel supplierModel) {

        this.supModel = supplierModel;
        SupplierNameField.setText(supplierModel.getFirstName() + " " + supplierModel.getLastName());
        SupplierIdField.setText(supplierModel.getId());

        GRNTableRender();
    }

    public void setSelectedSupplier(SupplierModel supplierModel) {
        // Set the fields in this panel with the Supplier data
        this.supModel = supplierModel;
        SupplierNameField.setText(supplierModel.getFirstName() + " " + supplierModel.getLastName());
        SupplierIdField.setText(supplierModel.getId());
        SupplierNameField.setEditable(false);
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
        jLabel5 = new javax.swing.JLabel();
        EmployeeName = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        GrnNumberField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        BuyingPriceField = new javax.swing.JTextField();
        SellingPriceField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        AddGRNBtn = new javax.swing.JButton();
        ClearAllBtn = new javax.swing.JButton();
        SupplierSelectBtn = new javax.swing.JButton();
        QtyField = new javax.swing.JTextField();
        ProductNameField = new javax.swing.JTextField();
        SupplierNameField = new javax.swing.JTextField();
        pendingPayment = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ProductIdField = new javax.swing.JLabel();
        SupplierIdField = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
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
        PaymentMethods = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();

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
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 20));

        BrandNameField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(BrandNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 150, 31));

        ProductSelectBtn.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        ProductSelectBtn.setForeground(new java.awt.Color(33, 43, 108));
        ProductSelectBtn.setText("SELECT");
        ProductSelectBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProductSelectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductSelectBtnActionPerformed(evt);
            }
        });
        jPanel2.add(ProductSelectBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 31));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel5.setText("Brand Name :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 30));

        EmployeeName.setFont(new java.awt.Font("Roboto", 3, 16)); // NOI18N
        EmployeeName.setText("Emp_Name");
        jPanel2.add(EmployeeName, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 140, 110, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel10.setText("Quantity :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, -1, 30));

        GrnNumberField.setEditable(false);
        GrnNumberField.setBackground(new java.awt.Color(255, 204, 51));
        GrnNumberField.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        GrnNumberField.setFocusable(false);
        jPanel2.add(GrnNumberField, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, 160, 31));

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel11.setText("Buying Price :");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, -1, 30));

        BuyingPriceField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jPanel2.add(BuyingPriceField, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 140, 31));

        SellingPriceField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jPanel2.add(SellingPriceField, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 130, 31));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel12.setText("Selling Price :");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, -1, 30));

        AddGRNBtn.setBackground(new java.awt.Color(33, 43, 108));
        AddGRNBtn.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        AddGRNBtn.setForeground(new java.awt.Color(255, 255, 255));
        AddGRNBtn.setText("ADD TO GRN");
        AddGRNBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddGRNBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddGRNBtnActionPerformed(evt);
            }
        });
        jPanel2.add(AddGRNBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 20, 190, 50));

        ClearAllBtn.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        ClearAllBtn.setForeground(new java.awt.Color(255, 0, 0));
        ClearAllBtn.setText("CLEAR INPUTS");
        ClearAllBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        ClearAllBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ClearAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearAllBtnActionPerformed(evt);
            }
        });
        jPanel2.add(ClearAllBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 80, 190, 50));

        SupplierSelectBtn.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        SupplierSelectBtn.setForeground(new java.awt.Color(33, 43, 108));
        SupplierSelectBtn.setText("SELECT");
        SupplierSelectBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SupplierSelectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupplierSelectBtnActionPerformed(evt);
            }
        });
        jPanel2.add(SupplierSelectBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, -1, 31));

        QtyField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jPanel2.add(QtyField, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 80, 31));

        ProductNameField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        ProductNameField.setBorder(null);
        ProductNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductNameFieldActionPerformed(evt);
            }
        });
        jPanel2.add(ProductNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 390, 31));

        SupplierNameField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        SupplierNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupplierNameFieldActionPerformed(evt);
            }
        });
        jPanel2.add(SupplierNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 270, 31));

        pendingPayment.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        pendingPayment.setText("Pending Payment:");
        jPanel2.add(pendingPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, -1, 30));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel8.setText("GRN Number :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, -1, -1));

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel14.setText("Issued By :");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 140, -1, -1));

        ProductIdField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jPanel2.add(ProductIdField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 110, 20));

        SupplierIdField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jPanel2.add(SupplierIdField, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 110, 20));

        jLabel15.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel15.setText("Supplier :");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel20.setText("Pending Payment:");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, -1, 30));

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GRNViewTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(GRNViewTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
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
        SaveGRN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SaveGRN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveGRNActionPerformed(evt);
            }
        });

        ClearEverythigbtn.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        ClearEverythigbtn.setForeground(new java.awt.Color(255, 0, 0));
        ClearEverythigbtn.setText("CLEAR ALL");
        ClearEverythigbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        ClearEverythigbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ClearEverythigbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearEverythigbtnActionPerformed(evt);
            }
        });

        PaymentMethods.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        PaymentMethods.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentMethodsActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel18.setText("Payment Method");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ClearEverythigbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(37, 37, 37)
                                .addComponent(BalanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(SaveGRN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TotalField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PaymenntField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PaymentMethods, javax.swing.GroupLayout.Alignment.TRAILING, 0, 158, Short.MAX_VALUE))))
                .addGap(17, 17, 17))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(TotalField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PaymentMethods, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        }

    }//GEN-LAST:event_PaymenntFieldKeyReleased

    private double total = 0;
    private double payment = 0;
    private double balance = 0;
    private String paymentMethod = "Cash";

    private void calculate() {
        SaveGRN.setEnabled(true);
        total = 0;
        balance = 0;
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
        paymentMethod = String.valueOf(PaymentMethods.getSelectedItem());

        if (total < 0) {
            JOptionPane.showMessageDialog(this, "Enter a Valid Payment!", "Error", JOptionPane.ERROR_MESSAGE);
            PaymenntField.setText("0");
            BalanceField.setText("0");
            SaveGRN.setEnabled(false);
        }

        balance = payment - total;
        PaymenntField.setEnabled(true);

//        if (balance < 0) {
//            SaveGRN.setEnabled(false);
//        } else if (payment < total) {
//            SaveGRN.setEnabled(false);
//        } else {
//            SaveGRN.setEnabled(true);
//        }
        if (paymentMethod.equals("Cash")) {
            balance = payment - total;
            PaymenntField.setEnabled(true);

//            if (balance < 0) {
//
//                SaveGRN.setEnabled(false);
//            } else {
//                SaveGRN.setEnabled(true);
//            }
        } else {
            //card

            payment = total;
            balance = 0;
            PaymenntField.setText(String.valueOf(payment));
            PaymenntField.setEnabled(false);
            SaveGRN.setEnabled(true);
        }

        BalanceField.setText(String.valueOf(balance));

        if (GRNViewTable.getRowCount() <= 0) {
            SaveGRN.setEnabled(false);
        }
    }

    private void ProductSelectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductSelectBtnActionPerformed
        // TODO add your handling code here:

        SelectItems si = new SelectItems(null, true, this);
        si.setVisible(true);


    }//GEN-LAST:event_ProductSelectBtnActionPerformed

    private void SupplierSelectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupplierSelectBtnActionPerformed
        // TODO add your handling code here:

        String SupName = SupplierNameField.getText();
        String SupId = SupplierIdField.getText();

        if (SupName.isEmpty() || SupId.isEmpty()) {

            SelectSuppliers selectSup = new SelectSuppliers(null, true, this);
            selectSup.setVisible(true);
        } else {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to Select a New Supplier? Unsaved GRN Records Will be lost", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                reset();
                SelectSuppliers selectSup = new SelectSuppliers(null, true, this);
                selectSup.setVisible(true);

            }
        }

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
                    resetInputs();
                    SaveGRN.setEnabled(true);
                }
            }
            loadGrnItem();

        }

        PaymenntField.grabFocus();
        PaymenntField.setEditable(true);


    }//GEN-LAST:event_AddGRNBtnActionPerformed

    private void GRNViewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GRNViewTableMouseClicked

    }//GEN-LAST:event_GRNViewTableMouseClicked

    //to store Stock IDs by Product IDs
    private HashMap<String, Integer> productStockIds = new HashMap<>();

    private void SaveGRNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveGRNActionPerformed
        String payment = PaymenntField.getText();
        String balance = BalanceField.getText();

        if ((Double.parseDouble(balance) < 0)) {
            int response = JOptionPane.showConfirmDialog(null, "Your Payment is lower than the Total.Add the rest amount as a pending payment?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                loadToTable();
            } else {
                JOptionPane.showMessageDialog(this, "Payment did not proceed", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            loadToTable();
        }

    }//GEN-LAST:event_SaveGRNActionPerformed

    private void loadToTable() {
        //loading to GRN Table
        try {

            String GrnID = GrnNumberField.getText();
            String supplierId = SupplierIdField.getText();
            String paidAmount = PaymenntField.getText();
            String balanceAmount = BalanceField.getText();
            String dateTimeForDB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            try {

                GrnModel grnModel = new GrnModel();

                grnModel.setGrnId(GrnID);
                grnModel.setDate(dateTimeForDB);
                grnModel.setPaidAmount(Double.parseDouble(paidAmount));
                grnModel.setSupplierId(supplierId);
                grnModel.setBalance(Double.parseDouble(balanceAmount));
                grnModel.setEmployeeId(LoginModel.getEmployeeId());

                ResultSet resultSet = new GrnController().store(grnModel);

            } catch (Exception e) {
                e.printStackTrace();

            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while saving items to GRN table : " + e.getMessage());

        }

// Loading to Stock Table
        try {
            for (int i = 0; i < GRNViewTable.getRowCount(); i++) {
                String productId = String.valueOf(GRNViewTable.getValueAt(i, 0));
                String qty = String.valueOf(GRNViewTable.getValueAt(i, 3));
                String sellingPrice = String.valueOf(GRNViewTable.getValueAt(i, 5));

                try {
                    StockController stockController = new StockController();
                    double quantityToAdd = Double.parseDouble(qty);
                    double price = Double.parseDouble(sellingPrice);

                    // Check if the same product with the same price already exists
                    ResultSet existingStock = stockController.findByProductIdAndPrice(productId, price);

                    if (existingStock.next()) {
                        int stockId = existingStock.getInt("id");
                        double currentQty = existingStock.getDouble("qty");
                        double newQty = currentQty + quantityToAdd;

                        stockController.updateQuantity(stockId, newQty);

                        productStockIds.put(productId, stockId);

                    } else {
                        // Product with the same price does not exist
                        StockModel stockModel = new StockModel();
                        stockModel.setPrice(price);
                        stockModel.setQty(quantityToAdd);
                        stockModel.setProductId(productId);

                        ResultSet resultSet = stockController.store(stockModel);

                        if (resultSet.next()) {
                            int stockId = resultSet.getInt(1);
                            productStockIds.put(productId, stockId);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.severe("Error on row " + i + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while saving items to Stock table: " + e.getMessage());
        }

// Loading to GRN Items Table
        try {
            for (int i = 0; i < GRNViewTable.getRowCount(); i++) {
                String grnID = GrnNumberField.getText();
                String productId = String.valueOf(GRNViewTable.getValueAt(i, 0));
                String qty = String.valueOf(GRNViewTable.getValueAt(i, 3));
                String buyingPrice = String.valueOf(GRNViewTable.getValueAt(i, 4));

                Integer stockId = productStockIds.get(productId);

                if (stockId != null) {
                    try {
                        GrnItemsModel grnItemsModel = new GrnItemsModel();
                        grnItemsModel.setGrnId(grnID);
                        grnItemsModel.setQty(Double.parseDouble(qty));
                        grnItemsModel.setPrice(Double.parseDouble(buyingPrice));
                        grnItemsModel.setStockId(stockId);

                        ResultSet resultSet = new GrnItemsController().store(grnItemsModel);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.severe("Error on row " + i + ": " + e.getMessage());
                    }
                } else {
                    logger.warning("Stock ID not found for Product ID: " + productId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while saving items to GRN Item table: " + e.getMessage());
        }

//Report  
        try {
            String dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa").format(new Date());
            String imgPath = "";
            //View or print grn
            InputStream s = this.getClass().getResourceAsStream("/resources/reports/Shop_Grn.jasper");
            String img = new File(this.getClass().getResource("/resources/reports/dazzle_auto_tp.png").getFile()).getAbsolutePath();
            imgPath = img.replace("\\", "/");

            HashMap<String, Object> params = new HashMap<>();
            params.put("img", imgPath);
            params.put("date", dateTime);
            params.put("grnNo", GrnNumberField.getText());
            params.put("empName", EmployeeName.getText());
            params.put("supplier", SupplierNameField.getText());

            params.put("total", TotalField.getText());
            params.put("payment", PaymenntField.getText());
            params.put("balance", BalanceField.getText());

            JRTableModelDataSource dataSource = new JRTableModelDataSource(GRNViewTable.getModel());

            JasperPrint report = JasperFillManager.fillReport(s, params, dataSource);
            JasperViewer.viewReport(report, false);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while grn printing : " + e.getMessage());
        }

        reset();

        GrnNumberField.setText(IDGenarator.GrnID());
        GrnNumberField.setEditable(false);
    }
    private void ClearEverythigbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearEverythigbtnActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(null, "Do you want to reset?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            reset();
        }
    }//GEN-LAST:event_ClearEverythigbtnActionPerformed


    private void GRNViewTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GRNViewTableMousePressed

        if (SwingUtilities.isRightMouseButton(evt) && GRNViewTable.getSelectedRow() != -1) {
            int row = GRNViewTable.rowAtPoint(evt.getPoint());
            GRNViewTable.setRowSelectionInterval(row, row);
            showContextMenu(evt, row);
        }

    }//GEN-LAST:event_GRNViewTableMousePressed

    private void ProductNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductNameFieldActionPerformed

    private void SupplierNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupplierNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SupplierNameFieldActionPerformed

    private void PaymentMethodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentMethodsActionPerformed
        // TODO add your handling code here:
        calculate();
    }//GEN-LAST:event_PaymentMethodsActionPerformed

    private void showContextMenu(MouseEvent evt, int row) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete");

        deleteItem.addActionListener(e -> deleteRow(row));
        popupMenu.add(deleteItem);

        popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
    }

    private void deleteRow(int row) {

        DefaultTableModel dtm = (DefaultTableModel) GRNViewTable.getModel();

        for (int i = 0; i < GRNViewTable.getRowCount(); i++) {

            String productId = (String) dtm.getValueAt(i, 0);
            String supplierId = (String) dtm.getValueAt(i, 6);
            String brand = (String) dtm.getValueAt(i, 1);

            String compositeKey = productId + "" + supplierId + "" + brand;

            // Remove from HashMap
            GrnItemMap.remove(compositeKey);

            // Remove from table model
            dtm.removeRow(row);
        }

        JOptionPane.showMessageDialog(null, "Row deleted successfully.");

    }

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
        ProductIdField.setText("");
        BrandNameField.setText("");
        BuyingPriceField.setText("");
        SellingPriceField.setText("");
        QtyField.setText("");

    }

    private void reset() {
        ProductNameField.setText("");
        ProductIdField.setText("");
        BrandNameField.setText("");
        SupplierIdField.setText("");
        BuyingPriceField.setText("");
        SellingPriceField.setText("");
        QtyField.setText("");
        SupplierNameField.setText("");
        DefaultTableModel dtm = (DefaultTableModel) GRNViewTable.getModel();
        dtm.setRowCount(0);
        TotalField.setText("0.00");
        PaymenntField.setText("0.00");
        BalanceField.setText("0.00");
        GrnItemMap.clear();
        GrnNumberField.setText(IDGenarator.GrnID());
        GrnNumberField.setEditable(false);

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
    private javax.swing.JComboBox<String> PaymentMethods;
    private javax.swing.JLabel ProductIdField;
    private javax.swing.JTextField ProductNameField;
    private javax.swing.JButton ProductSelectBtn;
    private javax.swing.JTextField QtyField;
    private javax.swing.JButton SaveGRN;
    private javax.swing.JTextField SellingPriceField;
    private javax.swing.JLabel SupplierIdField;
    private javax.swing.JTextField SupplierNameField;
    private javax.swing.JButton SupplierSelectBtn;
    private javax.swing.JLabel TotalField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel pendingPayment;
    // End of variables declaration//GEN-END:variables
}
