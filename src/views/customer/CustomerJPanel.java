/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.customer;

import com.formdev.flatlaf.FlatClientProperties;
import controllers.CustomerController;
import includes.LoggerConfig;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import models.CustomerModel;
import views.vehicle.VehicleRegistration;
import views.vehicle.VehicleUpdate;

/**
 *
 * @author Dinuka
 */
public class CustomerJPanel extends javax.swing.JPanel {

    private static final Logger logger = LoggerConfig.getLogger();

    private CustomerJPanel customerJPanel;

    CustomerSelector CustomerSelecterFrame = null;

    VehicleRegistration vehicleRegistration = null;
    VehicleUpdate VehicleUpdate = null;
    String From = "";
    String BaseDialog = "";

    public CustomerJPanel() {
        initComponents();

        loadCustomer();

        customerFindField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Name/Mobile");

        customerViewTableRender();

        this.customerJPanel = this;

    }

    //constructor for selector
    public CustomerJPanel(Dialog parentDialog, CustomerSelector customerSelector, String baseDialog) {
        this.CustomerSelecterFrame = customerSelector;
        this.From = "Selecter";
        this.BaseDialog = baseDialog;

        if (BaseDialog.equals("vehicleRegistration")) {
            this.vehicleRegistration = (VehicleRegistration) parentDialog;
        } else if (BaseDialog.equals("vehicleUpdate")) {
            this.VehicleUpdate = (VehicleUpdate) parentDialog;
        }

        initComponents();

        jRegNewCustomerButton.setEnabled(false);

        loadCustomer();
        customerFindField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Name/Mobile");
        customerViewTableRender();

    }

    private void loadCustomer() {
        try {

            ResultSet customerResultSet = new CustomerController().show();

            DefaultTableModel model = (DefaultTableModel) customerViewTable.getModel();
            model.setRowCount(0);

            while (customerResultSet.next()) {
                Vector<String> vector = new Vector<>();

                String employeeId = customerResultSet.getString("id");
                vector.add(employeeId);

                vector.add(customerResultSet.getString("first_name"));
                vector.add(customerResultSet.getString("last_name"));
                vector.add(customerResultSet.getString("mobile"));
                vector.add(customerResultSet.getString("registered_date"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading customer : " + e.getMessage());
        }

    }

    private void fetchUser(String searchText) throws Exception {
        DefaultTableModel model = (DefaultTableModel) customerViewTable.getModel();
        model.setRowCount(0);

        try {
            ResultSet resultSet = new CustomerController().search(searchText);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String mobile = resultSet.getString("mobile");

                String registeredDate = resultSet.getString("registered_date");

                model.addRow(new Object[]{id, fname, lname, mobile, registeredDate});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while fetching user : " + ex.getMessage());
        }
    }

    public void customerViewTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = customerViewTable.getTableHeader();

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Font headerFont = new Font("Verdana", Font.BOLD, 14);
                label.setBorder(BorderFactory.createEmptyBorder()); // Remove header borders
                label.setFont(headerFont);
                label.setBackground(new Color(214, 132, 13)); // Optional: Set header background color
                label.setForeground(Color.WHITE); // Optional: Set header text color
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return label;
            }
        });

        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));

        for (int i = 0; i < 5; i++) {
            customerViewTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        customerFindField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        customerViewTable = new javax.swing.JTable();
        jRegNewCustomerButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1100, 610));
        setPreferredSize(new java.awt.Dimension(1100, 610));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setMinimumSize(new java.awt.Dimension(1100, 610));
        mainPanel.setPreferredSize(new java.awt.Dimension(1100, 610));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        customerFindField.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        customerFindField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerFindFieldActionPerformed(evt);
            }
        });
        customerFindField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                customerFindFieldKeyReleased(evt);
            }
        });

        customerViewTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        customerViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name ", "Mobile ", "Registered Date "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customerViewTable.setRowHeight(30);
        customerViewTable.getTableHeader().setReorderingAllowed(false);
        customerViewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerViewTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(customerViewTable);
        if (customerViewTable.getColumnModel().getColumnCount() > 0) {
            customerViewTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            customerViewTable.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        jRegNewCustomerButton.setBackground(new java.awt.Color(199, 232, 199));
        jRegNewCustomerButton.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jRegNewCustomerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/add-user-30.png"))); // NOI18N
        jRegNewCustomerButton.setText(" REGISTER  CUSTOMER");
        jRegNewCustomerButton.setBorderPainted(false);
        jRegNewCustomerButton.setFocusPainted(false);
        jRegNewCustomerButton.setFocusable(false);
        jRegNewCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRegNewCustomerButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel1.setText("Find Customer :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(customerFindField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRegNewCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1065, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerFindField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRegNewCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/customers-30.png"))); // NOI18N
        jLabel2.setText("  Customers");
        jLabel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jLabel2ComponentShown(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void customerFindFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerFindFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerFindFieldActionPerformed

    private void jRegNewCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRegNewCustomerButtonActionPerformed

        new CustomerRegistration(null, true, customerJPanel).setVisible(true);

    }//GEN-LAST:event_jRegNewCustomerButtonActionPerformed

    private void customerViewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerViewTableMouseClicked
        int row = customerViewTable.getSelectedRow();

        int customerId = Integer.parseInt(customerViewTable.getValueAt(row, 0).toString());
        String firstName = String.valueOf(customerViewTable.getValueAt(row, 1));
        String lastName = String.valueOf(customerViewTable.getValueAt(row, 2));
        String mobile = String.valueOf(customerViewTable.getValueAt(row, 3));

        if (From.equals("Selecter")) {

            if (BaseDialog.equals("vehicleRegistration")) {
                vehicleRegistration.setCustomerDetails(String.valueOf(customerId), firstName, lastName);
            } else if (BaseDialog.equals("vehicleUpdate")) {
                VehicleUpdate.setCustomerDetails(String.valueOf(customerId), firstName, lastName);
            }
            CustomerSelecterFrame.dispose();
        }

        if (evt.getClickCount() == 2 && row != -1) {

            CustomerModel customerModel = new CustomerModel();
            customerModel.setId(customerId);
            customerModel.setFirstName(firstName);
            customerModel.setLastName(lastName);

            customerModel.setMobile(mobile);

            try {
                Frame CustomerJPanel = null;
                CustomerUpdate customerUpdate = new CustomerUpdate(CustomerJPanel, true, customerModel);
                customerUpdate.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                logger.warning("Error while opening customer update dialog : " + e.getMessage());
            }

            loadCustomer();
        }

    }//GEN-LAST:event_customerViewTableMouseClicked
    public void reloadTable() {
        loadCustomer();
    }

    private void jLabel2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jLabel2ComponentShown

        try {
            fetchUser(null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Error while jLabel2ComponentShown : " + e.getMessage());
        }

    }//GEN-LAST:event_jLabel2ComponentShown

    private void customerFindFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customerFindFieldKeyReleased

        try {
            fetchUser(customerFindField.getText().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("Error while customerFindFieldKeyReleased : " + ex.getMessage());
        }

    }//GEN-LAST:event_customerFindFieldKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField customerFindField;
    private javax.swing.JTable customerViewTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jRegNewCustomerButton;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
