/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.mainInvoice;

import controllers.PaymentMethodController;
import controllers.ServiceInvoiceController;
import controllers.ServiceInvoiceItemsController;
import includes.IDGenarator;
import includes.LoggerConfig;
import includes.OnlyDoubleDocumentFilter;
import includes.RegexValidator;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import models.LoginModel;
import models.MainInvoiceItemModel;
import models.ServiceInvoiceItemsModel;
import models.ServiceInvoiceModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import views.ourServices.OurServicesSelecter;
import views.payment.PaymentsPanel;
import views.vehicle.VehicleSelecter;

/**
 *
 * @author Dinuka
 */
public class MainInvoice extends javax.swing.JFrame {

    private static final Logger logger = LoggerConfig.getLogger();

    HashMap<String, MainInvoiceItemModel> invoiceItemMap = new HashMap<>();
    HashMap<String, String> paymentMethodmMap = new HashMap<>();

    PaymentsPanel paymentsPanel;

    public MainInvoice(PaymentsPanel parent) {
        this.paymentsPanel = parent;

        initComponents();

        setDocumentFilters();
        invoiceTableRender();
        loadPaymentMethods();
        jInvoiceIDTextField.setText(IDGenarator.invoiceID());
        jEmployeeNameLabel.setText(LoginModel.getFirstName() + " " + LoginModel.getLastName());

        jButton4.setEnabled(false);
        discountField.setEditable(false);
        paymentField.setEditable(false);

        jLabel19.setVisible(false);
        discountField.setVisible(false);
    }

    private void setDocumentFilters() {
        ((AbstractDocument) jServiceChargeField.getDocument()).setDocumentFilter(new OnlyDoubleDocumentFilter());
        ((AbstractDocument) discountField.getDocument()).setDocumentFilter(new OnlyDoubleDocumentFilter());
        ((AbstractDocument) paymentField.getDocument()).setDocumentFilter(new OnlyDoubleDocumentFilter());
    }

    private void invoiceTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = jTable1.getTableHeader();

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Font headerFont = new Font("Verdana", Font.BOLD, 14);
                label.setBorder(BorderFactory.createEmptyBorder()); // Remove header borders
                label.setFont(headerFont);
                label.setBackground(new Color(241, 145, 8)); // Optional: Set header background color
                label.setForeground(Color.WHITE); // Optional: Set header text color
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return label;
            }
        });

        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));

        for (int i = 0; i < 4; i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
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
            paymentMethodComboBox.setModel(comboBoxModel);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading payment methods : " + e.getMessage());
        }
    }

    public void setVehicleDetails(String vehicleNumber, String owner, String brand, String model, String type) {
        jVehicleNoLabel.setText(vehicleNumber);
        jVehicleTypeLabel.setText(type);
        jBrandModelLabel.setText(brand + " " + model);
    }

    public void setServiceDetails(String serviceID, String serviceName, String vehicleType, String serviceCharge) {
        jServiceNameLabel.setText(serviceName);
        serviceIDLabel.setText(serviceID);
        jServiceChargeField.setText(serviceCharge);
        jServiceVehicleType.setText(vehicleType);
    }

    public void loadInvoiceItem() {
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);

        for (MainInvoiceItemModel invoiceItem : invoiceItemMap.values()) {

            Vector<String> vector = new Vector<>();
            vector.add(invoiceItem.getServiceID());
            vector.add(invoiceItem.getServiceName());
            vector.add(invoiceItem.getServiceDescription());
            vector.add(invoiceItem.getServiceCharge());

            dtm.addRow(vector);

        }

        calculate();
    }

    private double total = 0;
    private double discount = 0;
    private double payment = 0;
    private double balance = 0;
    private String paymentMethod = "Cash";

    private void calculate() {

        total = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            String tablePrice = String.valueOf(jTable1.getValueAt(i, 3));

            double rowPrice = Double.parseDouble(tablePrice);

            total += rowPrice;
        }

        totalField.setText(String.valueOf(total));

        if (discountField.getText().isEmpty()) {
            discount = 0;
        } else {
            discount = Double.parseDouble(discountField.getText());
        }

        if (paymentField.getText().isEmpty()) {
            payment = 0;
        } else {
            payment = Double.parseDouble(paymentField.getText());
        }

        total = Double.parseDouble(totalField.getText());

        paymentMethod = String.valueOf(paymentMethodComboBox.getSelectedItem());

        total -= discount;

        if (total < 0) {
            JOptionPane.showMessageDialog(this, "Something Wrong in Discount !", "Error", JOptionPane.ERROR_MESSAGE);
            discountField.setText("0");
            paymentField.setText("0");
            balanceField.setText("0");
        } else {
            //discount ok

        }

        if (paymentMethod.equals("Cash")) {
            balance = payment - total;
            paymentField.setEnabled(true);

            if (balance < 0) {
                jButton4.setEnabled(false);
            } else {
                jButton4.setEnabled(true);
            }

        } else {
            //card

            payment = total;
            balance = 0;
            paymentField.setText(String.valueOf(payment));
            paymentField.setEnabled(false);
            jButton4.setEnabled(true);
        }

        balanceField.setText(String.valueOf(balance));

    }

    public void reset() {
        jInvoiceIDTextField.setText(IDGenarator.invoiceID());
        jVehicleNoLabel.setText("Vehicle No.");
        jBrandModelLabel.setText("Brand");
        jVehicleTypeLabel.setText("Vehicle Type");
        jDescriptionTextArea.setText("-");
        jServiceNameLabel.setText("Selected Service");
        jServiceChargeField.setText("0");
        serviceIDLabel.setText("0");
        jServiceVehicleType.setText("Vehicle Type");

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        totalField.setText("0.00");
        discountField.setText("0.00");
        paymentField.setText("0.00");
        balanceField.setText("0.00");
        paymentMethodComboBox.setSelectedIndex(0);
        invoiceItemMap.clear();
        jButton4.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jVehicleNoLabel = new javax.swing.JLabel();
        jVehicleSelectButton = new javax.swing.JButton();
        jServiceSelectorButton = new javax.swing.JButton();
        jServiceNameLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDescriptionTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jEmployeeNameLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jInvoiceIDTextField = new javax.swing.JTextField();
        jAddInvoiceButton = new javax.swing.JButton();
        jServiceChargeField = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        serviceIDLabel = new javax.swing.JLabel();
        jVehicleTypeLabel = new javax.swing.JLabel();
        jBrandModelLabel = new javax.swing.JLabel();
        jServiceVehicleType = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        totalField = new javax.swing.JFormattedTextField();
        discountField = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        balanceField = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        paymentMethodComboBox = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        paymentField = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INVOICE");
        setMinimumSize(new java.awt.Dimension(1300, 703));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INVOICE");

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jSeparator1.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(231, 245, 255));

        jVehicleNoLabel.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jVehicleNoLabel.setForeground(new java.awt.Color(255, 0, 0));
        jVehicleNoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jVehicleNoLabel.setText("Vehicle No.");
        jVehicleNoLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));

        jVehicleSelectButton.setBackground(new java.awt.Color(33, 43, 108));
        jVehicleSelectButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jVehicleSelectButton.setForeground(new java.awt.Color(255, 255, 255));
        jVehicleSelectButton.setText("Select Vehicle");
        jVehicleSelectButton.setBorderPainted(false);
        jVehicleSelectButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jVehicleSelectButton.setFocusPainted(false);
        jVehicleSelectButton.setFocusable(false);
        jVehicleSelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVehicleSelectButtonActionPerformed(evt);
            }
        });

        jServiceSelectorButton.setBackground(new java.awt.Color(206, 121, 0));
        jServiceSelectorButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jServiceSelectorButton.setForeground(new java.awt.Color(255, 255, 255));
        jServiceSelectorButton.setText("Select Service");
        jServiceSelectorButton.setBorderPainted(false);
        jServiceSelectorButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jServiceSelectorButton.setFocusPainted(false);
        jServiceSelectorButton.setFocusable(false);
        jServiceSelectorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServiceSelectorButtonActionPerformed(evt);
            }
        });

        jServiceNameLabel.setBackground(new java.awt.Color(255, 255, 255));
        jServiceNameLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jServiceNameLabel.setText("Selected Service");
        jServiceNameLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Service", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Yu Gothic UI Semibold", 1, 14))); // NOI18N
        jServiceNameLabel.setOpaque(true);

        jDescriptionTextArea.setColumns(20);
        jDescriptionTextArea.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jDescriptionTextArea.setRows(5);
        jScrollPane1.setViewportView(jDescriptionTextArea);

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel4.setText("Description");

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel2.setText("Issued By :");

        jEmployeeNameLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jEmployeeNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jEmployeeNameLabel.setText("employee name");

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel6.setText("INVOICE ID :");

        jInvoiceIDTextField.setEditable(false);
        jInvoiceIDTextField.setBackground(new java.awt.Color(231, 245, 255));
        jInvoiceIDTextField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jInvoiceIDTextField.setForeground(new java.awt.Color(0, 102, 51));
        jInvoiceIDTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jInvoiceIDTextField.setText("INV-0000000");
        jInvoiceIDTextField.setFocusable(false);

        jAddInvoiceButton.setBackground(new java.awt.Color(187, 235, 203));
        jAddInvoiceButton.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jAddInvoiceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/add-3.png"))); // NOI18N
        jAddInvoiceButton.setText("  Add Invoice");
        jAddInvoiceButton.setBorderPainted(false);
        jAddInvoiceButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jAddInvoiceButton.setFocusPainted(false);
        jAddInvoiceButton.setFocusable(false);
        jAddInvoiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddInvoiceButtonActionPerformed(evt);
            }
        });

        jServiceChargeField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jServiceChargeField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jServiceChargeField.setText("0");
        jServiceChargeField.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setText("Service Charge :");

        serviceIDLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        serviceIDLabel.setText("0");

        jVehicleTypeLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jVehicleTypeLabel.setForeground(new java.awt.Color(0, 51, 153));
        jVehicleTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jVehicleTypeLabel.setText("Vehicle Type");

        jBrandModelLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jBrandModelLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBrandModelLabel.setText("Brand");

        jServiceVehicleType.setText("Vehicle Type");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jVehicleSelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jVehicleNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jVehicleTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBrandModelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jServiceChargeField, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jServiceSelectorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jServiceVehicleType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jServiceNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serviceIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jEmployeeNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(jInvoiceIDTextField)))
                    .addComponent(jAddInvoiceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jEmployeeNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jInvoiceIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(jAddInvoiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jServiceNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jServiceSelectorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jVehicleNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jVehicleSelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(serviceIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(2, 2, 2)
                        .addComponent(jServiceVehicleType)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jServiceChargeField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(3, 3, 3)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jBrandModelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jVehicleTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                " ID", "Service", "Description", "Service Charge"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jTable1.setRowHeight(30);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        jPanel3.setBackground(new java.awt.Color(200, 232, 232));

        jLabel18.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Total");

        totalField.setEditable(false);
        totalField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        totalField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalField.setText("0");
        totalField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 24)); // NOI18N

        discountField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        discountField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        discountField.setText("0");
        discountField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        discountField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                discountFieldKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Discount");

        balanceField.setEditable(false);
        balanceField.setForeground(new java.awt.Color(204, 0, 0));
        balanceField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        balanceField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        balanceField.setText("0");
        balanceField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Blalance");

        paymentMethodComboBox.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 16)); // NOI18N
        paymentMethodComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        paymentMethodComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        paymentMethodComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paymentMethodComboBoxItemStateChanged(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Payment Method");

        paymentField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        paymentField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        paymentField.setText("0");
        paymentField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        paymentField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 24)); // NOI18N
        paymentField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentFieldKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Payment");

        jButton4.setBackground(new java.awt.Color(242, 242, 242));
        jButton4.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 0, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/print-30.png"))); // NOI18N
        jButton4.setText("  Print Invoice");
        jButton4.setBorderPainted(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setFocusPainted(false);
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(paymentMethodComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(balanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paymentField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(discountField)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paymentMethodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(paymentField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(balanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(discountField)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void discountFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discountFieldKeyReleased
        if (RegexValidator.isValidDecimal(discountField.getText())) {
            jLabel19.setText("Discount");
            jLabel19.setForeground(Color.BLACK);
            calculate();
        } else {
            jLabel19.setText("Error");
            jLabel19.setForeground(Color.red);
        }
    }//GEN-LAST:event_discountFieldKeyReleased

    private void paymentMethodComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paymentMethodComboBoxItemStateChanged
        calculate();
    }//GEN-LAST:event_paymentMethodComboBoxItemStateChanged

    private void paymentFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentFieldKeyReleased
        if (RegexValidator.isValidDecimal(paymentField.getText())) {
            jLabel21.setText("Payment");
            jLabel21.setForeground(Color.BLACK);
            calculate();
        } else {
            jLabel21.setText("Error");
            jLabel21.setForeground(Color.red);
        }
    }//GEN-LAST:event_paymentFieldKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (jLabel19.getText().equals("Error") || jLabel21.getText().equals("Error")) {
            JOptionPane.showMessageDialog(this, "Error In Fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String invoiceID = jInvoiceIDTextField.getText();
                String vehicleNumber = jVehicleNoLabel.getText();
                String vehicleName = jBrandModelLabel.getText();
                String cashierName = jEmployeeNameLabel.getText();
                String dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa").format(new Date());
                String dateTimeForDB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                String total = totalField.getText();
                String paymentMethodID = paymentMethodmMap.get(String.valueOf(paymentMethodComboBox.getSelectedItem()));
                String paymentMethod = String.valueOf(paymentMethodComboBox.getSelectedItem());
                String paidAmount = paymentField.getText();
                String balance = balanceField.getText();

                //insert to invoice
                ServiceInvoiceModel serviceInvoiceModel = new ServiceInvoiceModel();
                serviceInvoiceModel.setId(invoiceID);
                serviceInvoiceModel.setVehicleNumber(vehicleNumber);
                serviceInvoiceModel.setDate(dateTimeForDB);
                serviceInvoiceModel.setTotal(Double.parseDouble(total));
                serviceInvoiceModel.setPaidAmount(Double.parseDouble(paidAmount));
                serviceInvoiceModel.setBalance(Double.parseDouble(balance));
                serviceInvoiceModel.setPaymentMethodId(Integer.valueOf(paymentMethodID));
                serviceInvoiceModel.setEmployeeId(LoginModel.getEmployeeId());

                new ServiceInvoiceController().store(serviceInvoiceModel);

                logger.info("New Invoice Added : " + invoiceID + " | For : " + vehicleNumber);

                for (MainInvoiceItemModel invoiceItem : invoiceItemMap.values()) {
                    //insert to invoiceItem
                    ServiceInvoiceItemsModel serviceInvoiceItemsModel = new ServiceInvoiceItemsModel();
                    serviceInvoiceItemsModel.setServiceInvoiceId(invoiceID);
                    serviceInvoiceItemsModel.setServiceId(Integer.valueOf(invoiceItem.getServiceID()));
                    serviceInvoiceItemsModel.setDescription(invoiceItem.getServiceDescription());

                    new ServiceInvoiceItemsController().store(serviceInvoiceItemsModel);
                }

                //View or print invoice
                InputStream s = this.getClass().getResourceAsStream("/resources/reports/service_station_invoice.jasper");

                HashMap<String, Object> params = new HashMap<>();
                params.put("dateParameter", dateTime);
                params.put("invoiceNoPara", invoiceID);
                params.put("vehicleName", vehicleName);
                params.put("vehicleNumber", vehicleNumber);
                params.put("cashierPara", cashierName);

                params.put("totalPara", total);
                params.put("paymentPara", paidAmount);
                params.put("paymentMethodPara", paymentMethod);
                params.put("BalancePara", balance);

                JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

                JasperPrint report = JasperFillManager.fillReport(s, params, dataSource);
                JasperViewer.viewReport(report, false);

                reset();
                paymentsPanel.loadInvoices();

            } catch (Exception e) {
                e.printStackTrace();
                logger.severe("Error while loading invoice : " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jVehicleSelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVehicleSelectButtonActionPerformed
        new VehicleSelecter(this, true, "MainInvoice").setVisible(true);
    }//GEN-LAST:event_jVehicleSelectButtonActionPerformed

    private void jServiceSelectorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServiceSelectorButtonActionPerformed
        String vehicleNo = jVehicleNoLabel.getText();
        String vehicleType = jVehicleTypeLabel.getText();

        if (vehicleNo.equals("Vehicle No.")) {
            JOptionPane.showMessageDialog(this, "Please Select Vehicle First !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            new OurServicesSelecter(this, true, "MainInvoice", vehicleType).setVisible(true);
        }

    }//GEN-LAST:event_jServiceSelectorButtonActionPerformed

    private void jAddInvoiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddInvoiceButtonActionPerformed

        String serviceID = serviceIDLabel.getText();
        String serviceName = jServiceNameLabel.getText();
        String serviceDescription = jDescriptionTextArea.getText();
        String serviceCharge = jServiceChargeField.getText();

        if (jVehicleNoLabel.getText().equals("Vehicle No.")) {
            JOptionPane.showMessageDialog(this, "Please Select Vehicle !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jServiceNameLabel.getText().equals("Selected Service")) {
            JOptionPane.showMessageDialog(this, "Please Select Service !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (serviceCharge.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Service Charge !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (Double.parseDouble(serviceCharge) <= 0) {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Service Charge !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            if (serviceDescription.isBlank()) {

                int showConfirm = JOptionPane.showConfirmDialog(this, "Description is empty ! Do You Want To Continue ?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (showConfirm == JOptionPane.YES_OPTION) {
                    
                    serviceDescription = "-";

                    MainInvoiceItemModel mainInvoiceItem = new MainInvoiceItemModel();
                    mainInvoiceItem.setServiceID(serviceID);
                    mainInvoiceItem.setServiceName(serviceName);
                    mainInvoiceItem.setServiceDescription(serviceDescription);
                    mainInvoiceItem.setServiceCharge(serviceCharge);

                    if (invoiceItemMap.get(serviceID + "" + serviceName) == null) {
                        invoiceItemMap.put(serviceID + "" + serviceName, mainInvoiceItem);
                    } else {

                        MainInvoiceItemModel foundService = invoiceItemMap.get(serviceID + "" + serviceName);

                        if (foundService.getServiceName().equals(serviceName)) {
                            JOptionPane.showMessageDialog(this, "This Invoice Already In Table", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            invoiceItemMap.put(serviceID + "" + serviceName, mainInvoiceItem);
                        }

                    }

                    loadInvoiceItem();              

                paymentField.grabFocus();
                discountField.setEditable(true);
                paymentField.setEditable(true);
                
                }
            }
        }
    }//GEN-LAST:event_jAddInvoiceButtonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {

            int row = jTable1.getSelectedRow();
            String serviceId = String.valueOf(jTable1.getValueAt(row, 0));
            String serviceName = String.valueOf(jTable1.getValueAt(row, 1));

            if (invoiceItemMap.get(serviceId + "" + serviceName) != null) {
                invoiceItemMap.remove(serviceId + "" + serviceName);
            }

            loadInvoiceItem();
            calculate();
        }

    }//GEN-LAST:event_jTable1MouseClicked

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        IntelliJTheme.setup(MainInvoice.class
//                .getResourceAsStream(
//                        "/resources/themes/arc-theme.theme.json"));
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainInvoice().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField balanceField;
    private javax.swing.JFormattedTextField discountField;
    private javax.swing.JButton jAddInvoiceButton;
    private javax.swing.JLabel jBrandModelLabel;
    private javax.swing.JButton jButton4;
    private javax.swing.JTextArea jDescriptionTextArea;
    private javax.swing.JLabel jEmployeeNameLabel;
    private javax.swing.JTextField jInvoiceIDTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JFormattedTextField jServiceChargeField;
    private javax.swing.JLabel jServiceNameLabel;
    private javax.swing.JButton jServiceSelectorButton;
    private javax.swing.JLabel jServiceVehicleType;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jVehicleNoLabel;
    private javax.swing.JButton jVehicleSelectButton;
    private javax.swing.JLabel jVehicleTypeLabel;
    private javax.swing.JFormattedTextField paymentField;
    private javax.swing.JComboBox<String> paymentMethodComboBox;
    private javax.swing.JLabel serviceIDLabel;
    private javax.swing.JFormattedTextField totalField;
    // End of variables declaration//GEN-END:variables
}
