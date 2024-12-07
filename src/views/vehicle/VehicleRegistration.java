/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package views.vehicle;

import controllers.DriveTypesController;
import controllers.PowertrainTypesController;
import views.customer.*;
import controllers.VehicleBrandController;
import controllers.VehicleController;
import controllers.VehicleTypeController;
import includes.RegexValidator;
import java.awt.Dialog;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import models.VehicleModel;
import includes.LoggerConfig;
import java.util.logging.Logger;

/**
 *
 * @author USER Nimsara
 */
public class VehicleRegistration extends java.awt.Dialog {

    private static final Logger logger = LoggerConfig.getLogger();

    private static HashMap<String, String> vehicleTypesHashMap = new HashMap<>();
    private static HashMap<String, String> vehicleBrandHashMap = new HashMap<>();
    private static HashMap<String, String> powertrainTypesHashMap = new HashMap<>();
    private static HashMap<String, String> driveTypesHashMap = new HashMap<>();

    Dialog vehicleRegistration = this;

    private VehiclesJPanel vehicleJPanel;

    public VehicleRegistration(java.awt.Frame parent, boolean modal, VehiclesJPanel vehiclesJPanel) {
        super(parent, modal);

        this.vehicleJPanel = vehiclesJPanel;

        initComponents();
        loadVehicleTypes();
        loadVehicleBrand();
        loadPowertrainTypes();
        loadDriveTypes();
    }

    private void loadVehicleTypes() {

        try {
            ResultSet resultSet = new VehicleTypeController().show();

            Vector vector = new Vector();

            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                vehicleTypesHashMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            vehicle_type.setModel(comboBoxModel);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading vehicle Types : " + e.getMessage());
        }

    }

    private void loadVehicleBrand() {

        try {
            ResultSet resultSet = new VehicleBrandController().show();

            Vector vector = new Vector();

            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                vehicleBrandHashMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            vehicle_brand.setModel(comboBoxModel);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading vehicle Brands : " + e.getMessage());
        }
    }

    private void loadPowertrainTypes() {
        try {
            ResultSet resultSet = new PowertrainTypesController().show();
            Vector vector = new Vector();

            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                powertrainTypesHashMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            powertrainTypesComboBox.setModel(comboBoxModel);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading powerTrainTypes : " + e.getMessage());
        }
    }

    private void loadDriveTypes() {
        try {
            ResultSet resultSet = new DriveTypesController().show();
            Vector vector = new Vector();

            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                driveTypesHashMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            driveTypesComboBox.setModel(comboBoxModel);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading powerTrainTypes : " + e.getMessage());
        }
    }

    public void setCustomerDetails(String customerID, String firstName, String lastName) {
        jLabel2.setText(firstName + " " + lastName);
        jLabel7.setText(customerID);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        vehicle_number = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        vehicle_register_btn = new javax.swing.JButton();
        vehicle_reset_btn = new javax.swing.JButton();
        vehicle_brand = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        vehicle_type = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        engineNumberField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        vehicleModelTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        chassisNumberField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        engineNameField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        powertrainTypesComboBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        driveTypesComboBox = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel3.setText("Vehicle Number");

        vehicle_number.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel4.setText("Vehicle Brand");

        vehicle_register_btn.setBackground(new java.awt.Color(33, 43, 108));
        vehicle_register_btn.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        vehicle_register_btn.setForeground(new java.awt.Color(255, 255, 255));
        vehicle_register_btn.setText("REGISTER");
        vehicle_register_btn.setBorderPainted(false);
        vehicle_register_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vehicle_register_btn.setFocusPainted(false);
        vehicle_register_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicle_register_btnActionPerformed(evt);
            }
        });

        vehicle_reset_btn.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        vehicle_reset_btn.setForeground(new java.awt.Color(255, 0, 0));
        vehicle_reset_btn.setText("RESET");
        vehicle_reset_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        vehicle_reset_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vehicle_reset_btn.setFocusPainted(false);
        vehicle_reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicle_reset_btnActionPerformed(evt);
            }
        });

        vehicle_brand.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        vehicle_brand.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel5.setText("Vehicle Model");

        vehicle_type.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        vehicle_type.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel6.setText("Vehicle Type");

        jButton1.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 153));
        jButton1.setText("Select Customer");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 153), 1, true));
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel2.setText("Customer Name");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setText("Customer ID");

        engineNumberField.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel8.setText("Engine Number");

        vehicleModelTextField.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel9.setText("Chassis Number");

        chassisNumberField.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel10.setText("Engine Name");

        engineNameField.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel11.setText("Powertrain Types");

        powertrainTypesComboBox.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        powertrainTypesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        powertrainTypesComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel12.setText("Drive Types");

        driveTypesComboBox.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        driveTypesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        driveTypesComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel2.setBackground(new java.awt.Color(229, 229, 229));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setText("VEHICLE REGISTRATION");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(273, 273, 273))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(powertrainTypesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(vehicle_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(driveTypesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(vehicle_type, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(vehicle_number, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(85, 85, 85)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel5)))
                                .addComponent(jLabel9)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(vehicleModelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chassisNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(engineNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(engineNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(vehicle_reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(vehicle_register_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3))
                    .addComponent(vehicle_number, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vehicleModelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vehicle_type, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(engineNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(vehicle_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chassisNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(powertrainTypesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(engineNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTypesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vehicle_reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vehicle_register_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
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

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void vehicle_register_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehicle_register_btnActionPerformed

        String vehicleNumberValue = vehicle_number.getText();
        String vehicleType = String.valueOf(vehicle_type.getSelectedItem());
        String vehicleBrand = String.valueOf(vehicle_brand.getSelectedItem());
        String vehicleModelValue = engineNumberField.getText();

        String engineNumber = String.valueOf(vehicleModelTextField.getText());
        String chassisNumber = String.valueOf(chassisNumberField.getText());
        String engineName = String.valueOf(engineNameField.getText());
        String powertrainType = String.valueOf(powertrainTypesComboBox.getSelectedItem());
        String driveType = String.valueOf(driveTypesComboBox.getSelectedItem());

        if (vehicleNumberValue.isEmpty()) {
            showWarningMessage("Please enter your Vehicle Number");
        } else if (!RegexValidator.isValidVehicleNumber(vehicleNumberValue)) {
            showWarningMessage("Wrong Vehicle Number ! (CAA-0000/000-0000)");
        } else if (vehicleType.equals("Select")) {
            showWarningMessage("Please select a Vehicle Type");
        } else if (vehicleBrand.equals("Select")) {
            showWarningMessage("Please select a Vehicle Brand");
        } else if (vehicleModelValue.isEmpty()) {
            showWarningMessage("Vehicle Model is Required");
        } else if (jLabel2.getText().equals("Customer Name") || jLabel7.getText().equals("Customer ID")) {
            showWarningMessage("Please select a customer");
        } else if (engineNumber.isEmpty()) {
            showWarningMessage("Please enter an engine number");
        } else if (chassisNumber.isEmpty()) {
            showWarningMessage("Please enter a chassis number");
        } else if (engineName.isEmpty()) {
            showWarningMessage("Plese enter an engine name");
        } else if (powertrainType.equals("Select")) {
            showWarningMessage("Plese select a powertrain type");
        } else if (driveType.equals("Select")) {
            showWarningMessage("Please select a drive type");
        } else {

            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel.setVehicleNumber(vehicleNumberValue);
            vehicleModel.setCustomerId(String.valueOf(jLabel7.getText()));
            vehicleModel.setVehicleBrandId(Integer.parseInt(vehicleBrandHashMap.get(vehicleBrand)));
            vehicleModel.setModel(vehicleModelValue);
            vehicleModel.setVehicleTypeId(Integer.parseInt(vehicleTypesHashMap.get(vehicleType)));

            vehicleModel.setEngineNumber(engineNumber);
            vehicleModel.setChassisNumber(chassisNumber);
            vehicleModel.setEngineName(engineName);
            vehicleModel.setPowertrainTypeId(Integer.parseInt(powertrainTypesHashMap.get(powertrainType)));
            vehicleModel.setDriveTypesId(Integer.parseInt(driveTypesHashMap.get(driveType)));

            // check vehicle number
            if (!isThisVehicleNumberAlreadyExist(vehicleModel.getVehicleNumber())) {
                // store vehicle
                try {
                    ResultSet resultSet = new VehicleController().store(vehicleModel);
                    JOptionPane.showMessageDialog(this, "Vehicle Registred Success !", "Success", JOptionPane.INFORMATION_MESSAGE);
                    reset();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.severe("Error while registering a vehicle : " + e.getMessage());
                }

                vehicleJPanel.loadVehicles();
            } else {
                showWarningMessage("This vehicle number is already registered !");
            }
        }
    }//GEN-LAST:event_vehicle_register_btnActionPerformed

    private boolean isThisVehicleNumberAlreadyExist(String vehicleNumber) {
        try {
            ResultSet resultSet = new VehicleController().show(vehicleNumber);
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            logger.severe("Error while load vehicle by number : " + e.getMessage());
        }
        return false;
    }

    private void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    private void vehicle_reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehicle_reset_btnActionPerformed

        reset();
    }//GEN-LAST:event_vehicle_reset_btnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new CustomerSelector(null, true, vehicleRegistration, "vehicleRegistration").setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void reset() {

        vehicle_number.setText("");
        vehicle_type.setSelectedIndex(0);
        vehicle_brand.setSelectedIndex(0);
        engineNumberField.setText("");
        jLabel2.setText("Customer Name");
        jLabel7.setText("Customer ID");
        vehicleModelTextField.setText("");
        chassisNumberField.setText("");
        engineNameField.setText("");
        powertrainTypesComboBox.setSelectedIndex(0);
        driveTypesComboBox.setSelectedIndex(0);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField chassisNumberField;
    private javax.swing.JComboBox<String> driveTypesComboBox;
    private javax.swing.JTextField engineNameField;
    private javax.swing.JTextField engineNumberField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> powertrainTypesComboBox;
    private javax.swing.JTextField vehicleModelTextField;
    private javax.swing.JComboBox<String> vehicle_brand;
    private javax.swing.JTextField vehicle_number;
    private javax.swing.JButton vehicle_register_btn;
    private javax.swing.JButton vehicle_reset_btn;
    private javax.swing.JComboBox<String> vehicle_type;
    // End of variables declaration//GEN-END:variables
}
