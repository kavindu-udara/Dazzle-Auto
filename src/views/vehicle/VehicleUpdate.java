/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package views.vehicle;

import views.customer.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import controllers.CustomerController;
import controllers.DriveTypesController;
import controllers.PowertrainTypesController;
import controllers.VehicleBrandController;
import controllers.VehicleController;
import controllers.VehicleTypeController;
import includes.LoggerConfig;
import includes.RegexValidator;
import java.awt.Dialog;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.VehicleModel;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import models.CustomerModel;

/**
 *
 * @author USER Nimsara
 */
public class VehicleUpdate extends java.awt.Dialog {

    private static final Logger logger = LoggerConfig.getLogger();

    private static final HashMap<String, String> vehicleTypesHashMap = new HashMap<>();
    private static HashMap<String, String> vehicleBrandHashMap = new HashMap<>();
    private static HashMap<String, String> powertrainTypesHashMap = new HashMap<>();
    private static HashMap<String, String> driveTypesHashMap = new HashMap<>();

    Dialog vehicleUpdate = this;

    private VehicleModel vehicleModel;
    private CustomerModel customerModel = new CustomerModel();
    
    private VehiclesJPanel vehiclesJPanel;

    public VehicleUpdate(java.awt.Frame parent, boolean modal, VehicleModel vehicleModel, VehiclesJPanel vehiclesJPanel) {
        super(parent, modal);
        this.vehicleModel = vehicleModel;
        
        this.vehiclesJPanel = vehiclesJPanel;
        
        initComponents();
        loadVehicleTypes();
        loadVehicleBrand();
        loadPowertrainTypes();
        loadDriveTypes();

        loadVehicleDetails();
        loadCustomerDetails();

        fillFields();
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

    private void loadVehicleDetails() {
        try {
            ResultSet resultSet = new VehicleController().show(vehicleModel.getVehicleNumber());
            if (resultSet.next()) {
                vehicleModel.setCustomerId(resultSet.getString("customer_id"));
                vehicleModel.setVehicleBrandId(resultSet.getInt("vehicle_brand_id"));
                vehicleModel.setModel(resultSet.getString("model"));
                vehicleModel.setChassisNumber(resultSet.getString("chassis_no"));
                vehicleModel.setEngineNumber(resultSet.getString("engine_no"));
                vehicleModel.setEngineName(resultSet.getString("engine_name"));
                vehicleModel.setVehicleTypeId(resultSet.getInt("vehicle_type_id"));
                vehicleModel.setPowertrainTypeId(resultSet.getInt("powertrain_types_id"));
                vehicleModel.setDriveTypesId(resultSet.getInt("drive_types_id"));
            } else {
                JOptionPane.showMessageDialog(null, "Can't find vehicle");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading vehicle details : " + e.getMessage());
        }
    }

    private void loadCustomerDetails() {
        try {
            ResultSet resultSet = new CustomerController().show(Integer.parseInt(vehicleModel.getCustomerId()));
            if (resultSet.next()) {
                customerModel.setId(resultSet.getInt("id"));
                customerModel.setFirstName(resultSet.getString("first_name"));
                customerModel.setLastName(resultSet.getString("last_name"));
                customerModel.setMobile(resultSet.getString("last_name"));
            }
        } catch (Exception e) {

        }
    }

    private void fillFields() {
        vehicle_number.setText(vehicleModel.getVehicleNumber());
        vehicleModelTextField.setText(vehicleModel.getModel());
        engineNumberField.setText(vehicleModel.getEngineNumber());
        chassisNumberField.setText(vehicleModel.getChassisNumber());
        engineNameField.setText(vehicleModel.getEngineName());

        // customer details
        jLabel2.setText(customerModel.getFirstName() + customerModel.getLastName());
        jLabel7.setText(String.valueOf(customerModel.getId()));

        setVehicleType();
        setVehicleBrand();
        setPowerTrainType();
        setDriveType();
    }

    private void setPowerTrainType() {
        try {
            ResultSet resultSet = new PowertrainTypesController().show(vehicleModel.getPowertrainTypeId());
            if (resultSet.next()) {
                powertrainTypesComboBox.setSelectedItem(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setting powertrain type : " + e.getMessage());
        }
    }

    private void setDriveType() {
        try {
            ResultSet resultSet = new DriveTypesController().show(vehicleModel.getDriveTypesId());
            if (resultSet.next()) {
                driveTypesComboBox.setSelectedItem(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setting drive type : " + e.getMessage());
        }
    }

    private void setVehicleType() {
        try {
            ResultSet resultSet = new VehicleTypeController().show(vehicleModel.getVehicleTypeId());
            if (resultSet.next()) {
                vehicle_type.setSelectedItem(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setting vehicle type : " + e.getMessage());
        }
    }

    private void setVehicleBrand() {
        try {
            ResultSet resultSet = new VehicleBrandController().show(vehicleModel.getVehicleBrandId());
            if (resultSet.next()) {
                vehicle_brand.setSelectedItem(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while setting vehicle brand : " + e.getMessage());
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
        vehicle_type = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        powertrainTypesComboBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        driveTypesComboBox = new javax.swing.JComboBox<>();
        vehicleModelTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        engineNumberField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        chassisNumberField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        engineNameField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(826, 522));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(826, 540));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("Vehicle Number");

        vehicle_number.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Vehicle Brand");

        vehicle_register_btn.setBackground(new java.awt.Color(33, 43, 108));
        vehicle_register_btn.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        vehicle_register_btn.setForeground(new java.awt.Color(255, 255, 255));
        vehicle_register_btn.setText("UPDATE");
        vehicle_register_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicle_register_btnActionPerformed(evt);
            }
        });

        vehicle_reset_btn.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        vehicle_reset_btn.setForeground(new java.awt.Color(255, 0, 0));
        vehicle_reset_btn.setText("RESET");
        vehicle_reset_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        vehicle_reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicle_reset_btnActionPerformed(evt);
            }
        });

        vehicle_brand.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Tata", "Honda" }));

        vehicle_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Bike", "Lorry", "Three - Wheel" }));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Vehicle Type");

        jButton3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jButton3.setText("Select Customer");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setText("Customer Name");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setText("Customer ID");

        powertrainTypesComboBox.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        powertrainTypesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel12.setText("Drive Types");

        driveTypesComboBox.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        driveTypesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setText("Engine Number");

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel9.setText("Chassis Number");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("Vehicle Model");

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel10.setText("Engine Name");

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel11.setText("Powertrain Types");

        jPanel2.setBackground(new java.awt.Color(229, 229, 229));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setText(" VEHICLE DETAILS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(309, 309, 309)
                .addComponent(jLabel1)
                .addContainerGap(311, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel3)
                        .addGap(35, 35, 35)
                        .addComponent(vehicle_number, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(jLabel5)
                        .addGap(40, 40, 40)
                        .addComponent(vehicleModelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel6)
                        .addGap(36, 36, 36)
                        .addComponent(vehicle_type, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel8)
                        .addGap(40, 40, 40)
                        .addComponent(engineNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel4)
                        .addGap(19, 19, 19)
                        .addComponent(vehicle_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(jLabel9)
                        .addGap(40, 40, 40)
                        .addComponent(chassisNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(powertrainTypesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(jLabel10)
                        .addGap(40, 40, 40)
                        .addComponent(engineNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(driveTypesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jButton3)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(594, 594, 594)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addComponent(vehicle_register_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(vehicle_reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehicle_number, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vehicleModelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehicle_type, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(engineNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(vehicle_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel9))
                    .addComponent(chassisNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(powertrainTypesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(engineNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel12))
                    .addComponent(driveTypesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(jLabel7)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(vehicle_register_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(vehicle_reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
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

        String VehicleNumber = vehicle_number.getText();
        String VehicleType = String.valueOf(vehicle_type.getSelectedItem());

        String VehicleBrand = String.valueOf(vehicle_brand.getSelectedItem());
        String vehicleModelValue = vehicleModelTextField.getText();

        String engineNumber = engineNumberField.getText();
        String chassisNumber = chassisNumberField.getText();

        String engineName = engineNameField.getText();

        String powertrainType = String.valueOf(powertrainTypesComboBox.getSelectedItem());
        String driveType = String.valueOf(driveTypesComboBox.getSelectedItem());

        if (VehicleNumber.isEmpty()) {
            showWarningMessage("Please enter your Vehicle Number");
        } else if (!RegexValidator.isValidVehicleNumber(VehicleNumber)) {
            showWarningMessage("Wrong Vehicle Number type");
        } else if (VehicleType.equals("Select")) {
            showWarningMessage("Please select a Vehicle Type");
        } else if (VehicleBrand.equals("Select")) {
            showWarningMessage("Please select a Vehicle Brand");
        } else if (vehicleModelValue.isEmpty()) {
            showWarningMessage("VehicleModel is required !");
        } else if (engineNumber.isEmpty()) {
            showWarningMessage("Engine number is required !");
        } else if (chassisNumber.isEmpty()) {
            showWarningMessage("Chassis number is required !");
        } else if (engineName.isEmpty()) {
            showWarningMessage("Engine name is required !");
        } else if (powertrainType.equals("Select")) {
            showWarningMessage("Powertrain type is required !");
        } else if (driveType.equals("Select")) {
            showWarningMessage("Drive type is required !");
        } else if (jLabel2.getText().equals("Customer Name") || jLabel7.getText().equals("Customer ID")) {
            showWarningMessage("Please select a customer");
        } else {
            //
            vehicleModel.setVehicleTypeId(Integer.parseInt(vehicleTypesHashMap.get(VehicleType)));
            vehicleModel.setVehicleBrandId(Integer.parseInt(vehicleBrandHashMap.get(VehicleBrand)));
            vehicleModel.setModel(vehicleModelValue);
            vehicleModel.setEngineNumber(engineNumber);
            vehicleModel.setEngineName(engineName);
            vehicleModel.setChassisNumber(chassisNumber);
            vehicleModel.setPowertrainTypeId(Integer.parseInt(powertrainTypesHashMap.get(powertrainType)));
            vehicleModel.setDriveTypesId(Integer.parseInt(driveTypesHashMap.get(driveType)));

            vehicleModel.setCustomerId(String.valueOf(jLabel7.getText()));

            try {
                new VehicleController().update(vehicleModel);
                JOptionPane.showMessageDialog(this, "Vehicle updated !", "Warning", JOptionPane.INFORMATION_MESSAGE);
                
                vehiclesJPanel.reloadTable();
            } catch (Exception e) {
                e.printStackTrace();
                logger.severe("Error while updating vehicle : " + e.getMessage());
            }

        }

    }//GEN-LAST:event_vehicle_register_btnActionPerformed

    private void vehicle_reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehicle_reset_btnActionPerformed

        reset();

    }//GEN-LAST:event_vehicle_reset_btnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new CustomerSelector(null, true, vehicleUpdate, "vehicleUpdate").setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//
//        FlatMacDarkLaf.setup();
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                VehicleUpdate dialog = new VehicleUpdate(new java.awt.Frame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
    private void reset() {

        vehicle_number.setText("");
        vehicle_type.setSelectedIndex(0);
        vehicle_brand.setSelectedIndex(0);
        vehicleModelTextField.setText("");
        jLabel2.setText("Customer Name");
        jLabel7.setText("Customer ID");
        engineNumberField.setText("");
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
    private javax.swing.JButton jButton3;
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
