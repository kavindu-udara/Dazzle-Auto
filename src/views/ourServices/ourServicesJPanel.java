/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.ourServices;

import com.formdev.flatlaf.FlatClientProperties;

import controllers.ServicesController;
import controllers.VehicleTypeController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import views.mainInvoice.MainInvoice;
import includes.LoggerConfig;
import includes.OnlyLettersDocumentFilter;
import includes.OnlyNumbersDocumentFilter;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import models.ServicesModel;
import views.vehicleServiceAppointment.VehicleServiceAppointment;

/**
 *
 * @author Dinuka
 */
public class ourServicesJPanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();

    OurServicesSelecter ServiceSelecterFrame = null;

    private ourServicesJPanel thisPanel = this;

    MainInvoice mainInvoice = null;
    VehicleServiceAppointment vehicleServiceAppointment = null;
    String From = "";
    String BaseFrame = "";

    private static HashMap<String, String> vehicleTypesHashMap = new HashMap<>();

    public ourServicesJPanel() {
        initComponents();
        loadServices();
        serviceFindField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Service Name");
        setDocumentFilters();

        OurServiceTableRender();
        loadVehicleTypes();
    }

    private void setDocumentFilters() {
        ((AbstractDocument) serviceFindField.getDocument()).setDocumentFilter(new OnlyLettersDocumentFilter());

    }

    //Constructer for service selector
    public ourServicesJPanel(Frame parentFrame, OurServicesSelecter ourServicesSelecter, String BaseFrame, String VehicleTYPE) {
        this.ServiceSelecterFrame = ourServicesSelecter;
        this.From = "Selecter";
        this.BaseFrame = BaseFrame;

        if (BaseFrame.equals("MainInvoice")) {
            this.mainInvoice = (MainInvoice) parentFrame;
        } else if (BaseFrame.equals("VehicleServiceAppointment")) {
            this.vehicleServiceAppointment = (VehicleServiceAppointment) parentFrame;
        }

        initComponents();
        loadServices();

        serviceFindField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Service Name");
        jAddNewServiceButton.setEnabled(false);

        OurServiceTableRender();
        loadVehicleTypes();
        jVehicleTypeComboBox.setSelectedItem(VehicleTYPE);
        jVehicleTypeComboBox.setEnabled(false);
    }

    private void loadServices() {
        try {
            ResultSet resultSet = new ServicesController().show();

            DefaultTableModel tableModel = (DefaultTableModel) ourServicesViewTable.getModel();
            tableModel.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("name"));

                try {
                    ResultSet vehicleTypeResultSet = new VehicleTypeController().show(resultSet.getInt("vehicle_type_id"));
                    if (vehicleTypeResultSet.next()) {
                        vector.add(vehicleTypeResultSet.getString("name"));
                    } else {
                        vector.add("empty");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.severe("Error while showing vehicle types : " + e.getMessage());
                }

                vector.add(resultSet.getString("charge"));

                tableModel.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while showing services : " + e.getMessage());
        }
    }

    public void reloadTable() {
        loadServices();
    }

    public void OurServiceTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = ourServicesViewTable.getTableHeader();

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
            ourServicesViewTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void loadVehicleTypes() {

        try {
            ResultSet resultSet = new VehicleTypeController().show();

            Vector vector = new Vector();

            vector.add("  All");
            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                vehicleTypesHashMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            jVehicleTypeComboBox.setModel(comboBoxModel);

        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Error while loadVehicleTypes : " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ourServicesViewTable = new javax.swing.JTable();
        serviceFindField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jAddNewServiceButton = new javax.swing.JButton();
        jVehicleTypeComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1100, 610));
        setPreferredSize(new java.awt.Dimension(1100, 610));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/services-30.png"))); // NOI18N
        jLabel2.setText("  Our Services");

        ourServicesViewTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        ourServicesViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Service Name", "Vehicle Type ", "Service Charge"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ourServicesViewTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ourServicesViewTable.setFocusable(false);
        ourServicesViewTable.setRowHeight(30);
        ourServicesViewTable.getTableHeader().setReorderingAllowed(false);
        ourServicesViewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ourServicesViewTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ourServicesViewTable);
        if (ourServicesViewTable.getColumnModel().getColumnCount() > 0) {
            ourServicesViewTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            ourServicesViewTable.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        serviceFindField.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        serviceFindField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                serviceFindFieldKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel1.setText("Find Service :");

        jAddNewServiceButton.setBackground(new java.awt.Color(199, 232, 199));
        jAddNewServiceButton.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jAddNewServiceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/add-3.png"))); // NOI18N
        jAddNewServiceButton.setText("  ADD NEW SERVICE");
        jAddNewServiceButton.setBorderPainted(false);
        jAddNewServiceButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jAddNewServiceButton.setFocusPainted(false);
        jAddNewServiceButton.setFocusable(false);
        jAddNewServiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddNewServiceButtonActionPerformed(evt);
            }
        });

        jVehicleTypeComboBox.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jVehicleTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVehicleTypeComboBoxActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/filter-30.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(serviceFindField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jVehicleTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 302, Short.MAX_VALUE)
                        .addComponent(jAddNewServiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(jScrollPane2)
                    .addGap(18, 18, 18)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serviceFindField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jAddNewServiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jVehicleTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(131, 131, 131)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(32, Short.MAX_VALUE)))
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

    private void jAddNewServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddNewServiceButtonActionPerformed
        new ServiceRegistration(null, true, thisPanel).setVisible(true);
    }//GEN-LAST:event_jAddNewServiceButtonActionPerformed

    private void ourServicesViewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ourServicesViewTableMouseClicked

        int row = ourServicesViewTable.getSelectedRow();
        
        if(row < 0){
            return;
        }
        
        String serviceID = String.valueOf(ourServicesViewTable.getValueAt(row, 0));
        String serviceName = String.valueOf(ourServicesViewTable.getValueAt(row, 1));
        String vehicleType = String.valueOf(ourServicesViewTable.getValueAt(row, 2));
        String serviceCharge = String.valueOf(ourServicesViewTable.getValueAt(row, 3));

        ServicesModel servicesModel = new ServicesModel();
        servicesModel.setId(Integer.parseInt(serviceID));
        servicesModel.setVehicleTypeId(Integer.parseInt(vehicleTypesHashMap.get(vehicleType)));
        servicesModel.setCharge(Double.parseDouble(serviceCharge));
        servicesModel.setName(serviceName);
        servicesModel.setVehicleTypeName(vehicleType);

        if (From.equals("Selecter")) {

            if (BaseFrame.equals("MainInvoice")) {
                mainInvoice.setServiceDetails(serviceID, serviceName, vehicleType, serviceCharge);
            } else if (BaseFrame.equals("VehicleServiceAppointment")) {
                vehicleServiceAppointment.setServiceDetails(serviceID, serviceName, serviceCharge);
            }

            ServiceSelecterFrame.dispose();

        } else if (evt.getClickCount() == 2) {
            new UpdateService(null, true, servicesModel, thisPanel).setVisible(true);
        }
    }//GEN-LAST:event_ourServicesViewTableMouseClicked

    private void jVehicleTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVehicleTypeComboBoxActionPerformed
        searchTable();
    }//GEN-LAST:event_jVehicleTypeComboBoxActionPerformed

    private void serviceFindFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serviceFindFieldKeyReleased
        searchTable();
    }//GEN-LAST:event_serviceFindFieldKeyReleased

    private void searchTable() {
        // search process
        String searchText = serviceFindField.getText();
        String vehicleTypeId = "";
        if (!jVehicleTypeComboBox.getSelectedItem().equals("  All")) {
            vehicleTypeId = vehicleTypesHashMap.get(jVehicleTypeComboBox.getSelectedItem());
        }

        loadSearchedTable(searchText, vehicleTypeId);

    }

    private void loadSearchedTable(String searchText, String vehicleTypeId) {
        try {
            ResultSet resultSet;
            if (vehicleTypeId.equals("")) {
                resultSet = new ServicesController().search(searchText);
            } else {
                resultSet = new ServicesController().search(searchText, vehicleTypeId);
            }

            DefaultTableModel tableModel = (DefaultTableModel) ourServicesViewTable.getModel();
            tableModel.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("name"));

                try {
                    ResultSet vehicleTypeResultSet = new VehicleTypeController().show(resultSet.getInt("vehicle_type_id"));
                    if (vehicleTypeResultSet.next()) {
                        vector.add(vehicleTypeResultSet.getString("name"));
                    } else {
                        vector.add("empty");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.severe("Error while searching services : " + e.getMessage());
                }

                vector.add(resultSet.getString("charge"));

                tableModel.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while showing vehicle types : " + e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddNewServiceButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> jVehicleTypeComboBox;
    private javax.swing.JTable ourServicesViewTable;
    private javax.swing.JTextField serviceFindField;
    // End of variables declaration//GEN-END:variables
}
