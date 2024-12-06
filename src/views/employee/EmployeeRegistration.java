/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package views.employee;

import controllers.AddressController;
import controllers.CityController;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import includes.OnlyNumbersDocumentFilter;
import includes.RegexValidator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import includes.TimestampsGenerator;
import controllers.EmployeeTypeController;
import controllers.EmployeeController;
import controllers.EmployeeImageController;
import includes.BDUtility;
import includes.IDGenarator;
import includes.LoggerConfig;
import includes.OnlyLettersDocumentFilter;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import models.EmployeeModel;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import models.AddressModel;
import models.EmployeeImageModel;

/**
 *
 * @author USER nimsara
 */
public class EmployeeRegistration extends java.awt.Dialog {

    private static final Logger logger = LoggerConfig.getLogger();

    private StaffJPanel staffJPanel;
    private static HashMap<Integer, String> CityMap = new HashMap<>();

    public EmployeeRegistration(java.awt.Frame parent, boolean modal, StaffJPanel staffJPanel) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/icon2.png")));
        setDocumentFilters();
        loadTypes();
        loadCity();

        this.staffJPanel = staffJPanel;
    }

    private void loadCity() {

        try {
            ResultSet resultSet = new CityController().show();

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                int cityId = resultSet.getInt("id");
                String cityName = resultSet.getString("name");

                vector.add(cityName); // Add city names to the combo box
                CityMap.put(cityId, cityName); // Map city IDs to city names
            }

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(vector);
            cityComboBox.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading cities: " + e.getMessage());
        }
    }
    private static HashMap<String, String> employeeTypeMap = new HashMap<>();

    private void loadTypes() {

        try {
            ResultSet resultSet = new EmployeeTypeController().show();

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("type"));
                employeeTypeMap.put(resultSet.getString("type"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            employee_type.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading types : " + e.getMessage());
        }
    }

    private void setDocumentFilters() {
        ((AbstractDocument) employee_mobile.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) employee_firstname.getDocument()).setDocumentFilter(new OnlyLettersDocumentFilter());
        ((AbstractDocument) employee_lastname.getDocument()).setDocumentFilter(new OnlyLettersDocumentFilter());
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        employee_image = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        employee_firstname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        employee_lastname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        employee_email = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        employee_mobile = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        employee_nic = new javax.swing.JTextField();
        employee_register_btn = new javax.swing.JButton();
        employee_reset_btn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        employee_type = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Lane1Field = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lane2Field = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cityComboBox = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        PostalCodeField = new javax.swing.JTextField();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EMPLOYEE REGISTRATION");

        employee_image.setBackground(new java.awt.Color(255, 255, 51));
        employee_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        employee_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/select-100.png"))); // NOI18N
        employee_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        employee_image.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        employee_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employee_imageMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setText("First Name");

        employee_firstname.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setText("Last Name");

        employee_lastname.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel5.setText("Email");

        employee_email.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel7.setText("Mobile");

        employee_mobile.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        employee_mobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_mobileActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setText("Nic");

        employee_nic.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        employee_register_btn.setBackground(new java.awt.Color(33, 43, 108));
        employee_register_btn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        employee_register_btn.setForeground(new java.awt.Color(255, 255, 255));
        employee_register_btn.setText("REGISTER");
        employee_register_btn.setBorderPainted(false);
        employee_register_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        employee_register_btn.setFocusPainted(false);
        employee_register_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_register_btnActionPerformed(evt);
            }
        });

        employee_reset_btn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        employee_reset_btn.setForeground(new java.awt.Color(255, 0, 0));
        employee_reset_btn.setText("RESET");
        employee_reset_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        employee_reset_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        employee_reset_btn.setFocusPainted(false);
        employee_reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_reset_btnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setText("Employee Type");

        employee_type.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        employee_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", " " }));
        employee_type.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        employee_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_typeActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel9.setText("Address ");

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel10.setText("Lane 1");

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel11.setText("Lane 2");

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel12.setText("City");

        cityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cityComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityComboBoxActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel13.setText("Postal Code");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(employee_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(employee_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Lane1Field, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(PostalCodeField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lane2Field, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(employee_reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(employee_register_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(employee_image, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(249, 249, 249)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(employee_mobile))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)
                                        .addComponent(employee_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(employee_nic, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(employee_email, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(346, 346, 346)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(employee_image, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(employee_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(employee_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(employee_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(employee_mobile, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lane1Field, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lane2Field, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PostalCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(employee_nic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(employee_type, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(employee_reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(employee_register_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    BufferedImage originalImage = null;
    File selectedFile = null;
    private void employee_imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employee_imageMouseClicked
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            selectedFile = fileChooser.getSelectedFile();
            try {
                originalImage = ImageIO.read(selectedFile);

                int originalWidth = originalImage.getWidth();
                int originalHeight = originalImage.getHeight();

                int labelWidth = employee_image.getWidth();
                int labelHeight = employee_image.getHeight();

                double scalex = (double) labelWidth / originalWidth;
                double scaleY = (double) labelHeight / originalHeight;

                double scale = Math.min(scalex, scaleY);

                int scaleWidth = (int) (originalWidth * scale);
                int scaleHeight = (int) (originalHeight * scale);

                Image scaledImage = originalImage.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);

                ImageIcon icon = new ImageIcon(scaledImage);
                employee_image.setIcon(icon);

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.severe("Error while reading selected image : " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_employee_imageMouseClicked

    private void employee_reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_reset_btnActionPerformed

        reset();
    }//GEN-LAST:event_employee_reset_btnActionPerformed

    private void employee_register_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_register_btnActionPerformed
String firstName = employee_firstname.getText();
String lastName = employee_lastname.getText();
String nic = employee_nic.getText();
String email = employee_email.getText();
String mobile = employee_mobile.getText();
String employeeType = String.valueOf(employee_type.getSelectedItem());
String lane1 = Lane1Field.getText();
String lane2 = lane2Field.getText();
String postalCode = PostalCodeField.getText();
String cityName = String.valueOf(cityComboBox.getSelectedItem());

if (firstName.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);
} else if (lastName.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter your last name", "Warning", JOptionPane.WARNING_MESSAGE);
} else if (email.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter your email", "Warning", JOptionPane.WARNING_MESSAGE);
} else if (!RegexValidator.isValidEmail(email)) {
    JOptionPane.showMessageDialog(this, "Invalid email", "Warning", JOptionPane.WARNING_MESSAGE);
} else if (nic.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter your NIC", "Warning", JOptionPane.WARNING_MESSAGE);
} else if (!RegexValidator.isValidSlNic(nic) && !RegexValidator.isValidSlNewNic(nic)) {
    JOptionPane.showMessageDialog(this, "Invalid NIC Number", "Warning", JOptionPane.WARNING_MESSAGE);
} else if (mobile.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please enter your mobile number", "Warning", JOptionPane.WARNING_MESSAGE);
} else if (!RegexValidator.isValidSlPhone(mobile)) {
    JOptionPane.showMessageDialog(this, "Invalid mobile number", "Warning", JOptionPane.WARNING_MESSAGE);
} else if (employeeType.equals("Select")) {
    JOptionPane.showMessageDialog(this, "Please select an employee type", "Warning", JOptionPane.WARNING_MESSAGE);
} else {
    try {
        String generatedId = IDGenarator.employeeID();

        EmployeeModel employeeModel = new EmployeeModel();
        AddressModel addressModel = new AddressModel();

        employeeModel.setId(generatedId);
        employeeModel.setFirstName(firstName);
        employeeModel.setLastName(lastName);
        employeeModel.setEmail(email);
        employeeModel.setNic(nic);
        employeeModel.setMobile(mobile);

        // Map employee type ID
        employeeModel.setEmployeeTypeId(Integer.parseInt(employeeTypeMap.get(employee_type.getSelectedItem())));
        employeeModel.setStatusId(1);

        // Set registration date
        String registerDateTime = TimestampsGenerator.getFormattedDateTime();
        employeeModel.setRegisteredDate(registerDateTime);

        addressModel.setLane1(lane1);
        addressModel.setLane2(lane2);
        addressModel.setPostalCode(postalCode);

        // Map city ID
        if (!CityMap.containsValue(cityName)) {
            JOptionPane.showMessageDialog(this, "City not found", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int cityId = CityMap.entrySet().stream()
            .filter(entry -> entry.getValue().equals(cityName))
            .map(Map.Entry::getKey)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("City not found"));
        
        addressModel.setCity(cityName);

        // Store employee and address
        ResultSet resultSet = new EmployeeController().store(employeeModel);
        ResultSet resultSet2 = new AddressController().storeSupplierAddress(addressModel);

        // Save image
        String imagePath = saveImage(generatedId + nic + firstName + lastName);
        if (imagePath != null) {
            EmployeeImageModel employeeImageModel = new EmployeeImageModel();
            employeeImageModel.setPath(imagePath);
            employeeImageModel.setEmployeeId(generatedId);

            new EmployeeImageController().store(employeeImageModel);
        }

        JOptionPane.showMessageDialog(this, "Employee Registration Successful");

        // Reload view table
        staffJPanel.reloadTable();

        reset();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        logger.severe("Error while storing employee: " + e.getMessage());
    }
}

//        String firstName = employee_firstname.getText();
//        String lastName = employee_lastname.getText();
//        String nic = employee_nic.getText();
//        String email = employee_email.getText();
//        String mobile = employee_mobile.getText();
//        String employeeType = String.valueOf(employee_type.getSelectedItem());
//        String lane1 = Lane1Field.getText();
//        String lane2 = lane2Field.getText();
//        String postalCode = PostalCodeField.getText();
//        String cityName =  String.valueOf(cityComboBox.getSelectedItem());
//
//        if (firstName.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else if (lastName.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter your last name", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else if (email.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter your email", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else if (!RegexValidator.isValidEmail(email)) {
//            JOptionPane.showMessageDialog(this, "Invalid email", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else if (nic.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter your NIC", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else if (!RegexValidator.isValidSlNic(nic) && !RegexValidator.isValidSlNewNic(nic)) {
//            JOptionPane.showMessageDialog(this, "Invalid NIC Number", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else if (mobile.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter your mobile number", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else if (!RegexValidator.isValidSlPhone(mobile)) {
//            JOptionPane.showMessageDialog(this, "Invalid mobile number", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else if (employeeType.equals("Select")) {
//            JOptionPane.showMessageDialog(this, "Please select an employee type", "Warning", JOptionPane.WARNING_MESSAGE);
//        } else {
//
//            // Store employee information
//            try {
//                String generatedId = IDGenarator.employeeID();
//
//                EmployeeModel employeeModel = new EmployeeModel();
//                AddressModel addressModel = new AddressModel();
//
//                employeeModel.setId(generatedId);
//                employeeModel.setFirstName(firstName);
//                employeeModel.setLastName(lastName);
//                employeeModel.setEmail(email);
//                employeeModel.setNic(nic);
//                employeeModel.setMobile(mobile);
//
//                employeeModel.setEmployeeTypeId(Integer.parseInt(employeeTypeMap.get(employee_type.getSelectedItem())));
//                employeeModel.setStatusId(1);
//
//                String registerDateTime = TimestampsGenerator.getFormattedDateTime();
//                employeeModel.setRegisteredDate(registerDateTime);
//
//                addressModel.setLane1(lane1);
//                addressModel.setLane2(lane2);
//                addressModel.setPostalCode(postalCode);
//                addressModel.setCity(Integer.parseInt(CityMap.get(cityComboBox.getSelectedItem())));
//
////                int cityId = -1;
////                for (Map.Entry<Integer, String> entry : CityMap.entrySet()) {
////                    if (entry.getValue().equals(cityName)) {
////                        cityId = entry.getKey();
////                        break;
////                    }
////                }
////
////                if (cityId == -1) {
////                    JOptionPane.showMessageDialog(this, "City not found", "Warning", JOptionPane.WARNING_MESSAGE);
////                } else {
////                    addressModel.setCity(String.valueOf(cityId));
////                }
//                ResultSet resultSet = new EmployeeController().store(employeeModel);
//                ResultSet resultSet2 = new AddressController().storeSupplierAddress(addressModel);
//
//                String imagePath = saveImage(generatedId + nic + firstName + lastName);
//                if (imagePath != null) {
//                    EmployeeImageModel employeeImageModel = new EmployeeImageModel();
//                    employeeImageModel.setPath(imagePath);
//                    employeeImageModel.setEmployeeId(generatedId);
//
//                    new EmployeeImageController().store(employeeImageModel);
//                } else {
//                    //                    JOptionPane.showMessageDialog(this, "Image not saved correctly.", "Warning", JOptionPane.WARNING_MESSAGE);
//                }
//
//                JOptionPane.showMessageDialog(this, "Employee Registration Successful");
//
//                // Reload view table
//                staffJPanel.reloadTable();
//
//                reset();
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, e.getMessage());
//                logger.severe("Error while storing employee : " + e.getMessage());
//            }
//        }
    }//GEN-LAST:event_employee_register_btnActionPerformed

    private void employee_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_typeActionPerformed

    private void employee_mobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_mobileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_mobileActionPerformed

    private void cityComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cityComboBoxActionPerformed

    // Save image in resources package
    private String saveImage(String email) {
        if (originalImage != null && selectedFile != null) {
            try {
                String savePath = BDUtility.getPath("resources/employeeImages/" + File.separator);

                File directory = new File(savePath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String extension = BDUtility.getFileExtension(selectedFile.getName());

                String imageName = email + "." + extension;

                File saveFile = new File(savePath + imageName);

                BufferedImage scaledImage = BDUtility.scaleImage(originalImage, ImageIO.read(selectedFile));

                ImageIO.write(scaledImage, extension.replace(".", ""), saveFile);

                return imageName;

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.severe("Error while saving selected image : " + ex.getMessage());
            }
        }
        return null;
    }

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                EmployeeRegistration dialog = new EmployeeRegistration(new java.awt.Frame(), true);
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

        employee_firstname.setText("");
        employee_lastname.setText("");
        employee_mobile.setText("");
        employee_nic.setText("");
        employee_email.setText("");
        employee_type.setSelectedIndex(0);
        employee_image.setIcon(null);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Lane1Field;
    private javax.swing.JTextField PostalCodeField;
    private javax.swing.JComboBox<String> cityComboBox;
    private javax.swing.JTextField employee_email;
    private javax.swing.JTextField employee_firstname;
    private javax.swing.JLabel employee_image;
    private javax.swing.JTextField employee_lastname;
    private javax.swing.JTextField employee_mobile;
    private javax.swing.JTextField employee_nic;
    private javax.swing.JButton employee_register_btn;
    private javax.swing.JButton employee_reset_btn;
    private javax.swing.JComboBox<String> employee_type;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField lane2Field;
    // End of variables declaration//GEN-END:variables
}
