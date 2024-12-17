/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package views.employee;

import controllers.AddressController;
import controllers.CityController;
import controllers.EmployeeController;
import controllers.EmployeeImageController;
import controllers.EmployeeTypeController;
import controllers.StatusController;
import includes.BDUtility;
import includes.LoggerConfig;
import includes.OnlyLettersDocumentFilter;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import includes.OnlyNumbersDocumentFilter;
import includes.RegexValidator;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import models.EmployeeImageModel;
import models.EmployeeModel;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import jnafilechooser.api.JnaFileChooser;
import models.AddressModel;
import raven.toast.Notifications;

/**
 *
 * @author USER nimsara
 */
public class EmployeeUpdate extends java.awt.Dialog {

    private static HashMap<String, String> employeeTypeMap = new HashMap<>();
    private static HashMap<String, String> StatusMap = new HashMap<>();
    private static HashMap<Integer, String> CityMap = new HashMap<>();
    private static final Logger logger = LoggerConfig.getLogger();

    private boolean isEmployerHaveAnImage = false;

    private EmployeeModel employeeModel;
    private EmployeeImageModel employeeImageModel = new EmployeeImageModel();

    private StaffJPanel staffJPanel;
    private AddressModel addressModel;

    public EmployeeUpdate(Frame parent, boolean modal, EmployeeModel employeeModel, StaffJPanel staffJPanel, AddressModel addressModel) {
        super(parent, modal);
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/icon2.png")));
        setDocumentFilters();
        loadEmployeeTypes();
        loadempStatus();
        loadCity();
        this.employeeModel = employeeModel;
        this.staffJPanel = staffJPanel;
        this.addressModel = addressModel;
        EmpIdField.setFocusable(false);

        setEmployeeData();
        loadAmployeeImage();

        employee_update_btn.grabFocus();
    }

    private void setEmployeeData() {
        EmpIdField.setText(employeeModel.getId());
        employee_firstname.setText(employeeModel.getFirstName());
        employee_lastname.setText(employeeModel.getLastName());
        employee_email.setText(employeeModel.getEmail());
        employee_nic.setText(employeeModel.getNic());
        employee_mobile.setText(employeeModel.getMobile());
        employee_type.setSelectedItem(employeeModel.getEmployeeTypeName());
        emp_status.setSelectedItem(employeeModel.getStatusName());

        if (addressModel != null) {
            Lane1Field.setText(addressModel.getLane1());
            lane2Field.setText(addressModel.getLane2());
            if (addressModel.getCity() != null) {
                cityComboBox.setSelectedItem(CityMap.get(Integer.parseInt(addressModel.getCity())));
            }
        }

        try {
            ResultSet resultSet = new StatusController().show(employeeModel.getStatusId());
            if (resultSet.next()) {
                emp_status.setSelectedItem(resultSet.getString("status"));
            }
        } catch (Exception e) {
            logger.severe("Error while setting status : " + e.getMessage());
        }

    }

    private void loadCity() {

        try {
            ResultSet resultSet = new CityController().show();

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                int cityId = resultSet.getInt("id");
                String cityName = resultSet.getString("name");

                vector.add(cityName);
                CityMap.put(cityId, cityName);
            }

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(vector);
            cityComboBox.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading cities: " + e.getMessage());
        }
    }

    private void loadempStatus() {
        try {
            ResultSet resultSet = new StatusController().show();

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                StatusMap.put(resultSet.getString("status"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            emp_status.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading employee types : " + e.getMessage());
        }
    }

    private void loadEmployeeTypes() {
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
            logger.severe("Error while loading employee types : " + e.getMessage());
        }
    }

    private void setDocumentFilters() {
        ((AbstractDocument) employee_mobile.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());
        ((AbstractDocument) employee_firstname.getDocument()).setDocumentFilter(new OnlyLettersDocumentFilter());
        ((AbstractDocument) employee_lastname.getDocument()).setDocumentFilter(new OnlyLettersDocumentFilter());
    }

    private void loadAmployeeImage() {
        try {
            ResultSet resultSet = new EmployeeImageController().show(employeeModel.getId());

            // show image
            if (resultSet.next()) {
                isEmployerHaveAnImage = true;

                employeeImageModel.setPath(resultSet.getString("path"));
                employeeImageModel.setId(resultSet.getInt("id"));
                employeeImageModel.setEmployeeId(resultSet.getString("employee_id"));

                String imagePath = BDUtility.getPath("resources/employeeImages/" + resultSet.getString("path"));
                File imageFile = new File(imagePath);

                if (imageFile.exists()) {

                    BufferedImage image = ImageIO.read(imageFile);

                    int imageWidth = image.getWidth();
                    int imageHeight = image.getHeight();

                    int labelWidth = employee_image.getWidth();
                    int labelHeight = employee_image.getHeight();

                    double scalex = (double) labelWidth / imageWidth;
                    double scaleY = (double) labelHeight / imageHeight;

                    double scale = Math.min(scalex, scaleY);

                    int scaleWidth = (int) (imageWidth * scale);
                    int scaleHeight = (int) (imageHeight * scale);

                    Image scaledImage = image.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);

                    ImageIcon resizedIcon = new ImageIcon(scaledImage);
                    employee_image.setIcon(resizedIcon);

                } else {
                    employee_image.setIcon(null);
                }
            } else {
                isEmployerHaveAnImage = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading employee image : " + e.getMessage());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
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
        employee_update_btn = new javax.swing.JButton();
        employee_reset_btn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        employee_type = new javax.swing.JComboBox<>();
        employee_status = new javax.swing.JLabel();
        emp_status = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        cityComboBox = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        lane2Field = new javax.swing.JTextField();
        Lane1Field = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        EmpIdField = new javax.swing.JTextField();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EMPLOYEE DETAILS");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 188, 30));

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
        jPanel3.add(employee_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 123, 141));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setText("First Name");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 118, 34));

        employee_firstname.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jPanel3.add(employee_firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 218, 34));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setText("Last Name");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 118, 32));

        employee_lastname.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jPanel3.add(employee_lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 218, 34));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel5.setText("Email");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 118, 32));

        employee_email.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        employee_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_emailActionPerformed(evt);
            }
        });
        jPanel3.add(employee_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 218, 34));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel7.setText("Mobile");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 80, 34));

        employee_mobile.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        employee_mobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_mobileActionPerformed(evt);
            }
        });
        jPanel3.add(employee_mobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 220, 34));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setText("Nic");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 124, 34));

        employee_nic.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jPanel3.add(employee_nic, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 218, 34));

        employee_update_btn.setBackground(new java.awt.Color(33, 43, 108));
        employee_update_btn.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        employee_update_btn.setForeground(new java.awt.Color(255, 255, 255));
        employee_update_btn.setText("UPDATE");
        employee_update_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        employee_update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_update_btnActionPerformed(evt);
            }
        });
        jPanel3.add(employee_update_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 470, 170, 50));

        employee_reset_btn.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        employee_reset_btn.setForeground(new java.awt.Color(255, 0, 0));
        employee_reset_btn.setText("RESET");
        employee_reset_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        employee_reset_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        employee_reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_reset_btnActionPerformed(evt);
            }
        });
        jPanel3.add(employee_reset_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 470, 170, 50));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setText("Employee Type");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 140, 32));

        employee_type.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        employee_type.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        employee_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_typeActionPerformed(evt);
            }
        });
        jPanel3.add(employee_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 220, 35));

        employee_status.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        employee_status.setText("Status");
        jPanel3.add(employee_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 120, 34));

        emp_status.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        emp_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        emp_status.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(emp_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 220, 37));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, -1));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel12.setText("City");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 400, -1, -1));

        cityComboBox.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        cityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cityComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityComboBoxActionPerformed(evt);
            }
        });
        jPanel3.add(cityComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 390, 210, 34));

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel11.setText("Lane 2");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, -1, -1));

        lane2Field.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jPanel3.add(lane2Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 340, 210, 34));

        Lane1Field.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jPanel3.add(Lane1Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, 210, 34));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel10.setText("Lane 1");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, -1, -1));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel9.setText("Address (Optional)");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, -1, -1));

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel14.setText("Employee ID");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, -1, -1));

        EmpIdField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        EmpIdField.setBorder(null);
        jPanel3.add(EmpIdField, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 100, 34));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void employee_mobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_mobileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_mobileActionPerformed

    BufferedImage originalImage = null;
    File selectedFile = null;
    private void employee_update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_update_btnActionPerformed

        String firstName = employee_firstname.getText();
        String lastName = employee_lastname.getText();
        String nic = employee_nic.getText();
        String email = employee_email.getText();
        String mobile = employee_mobile.getText();
        String employeeType = String.valueOf(employee_type.getSelectedItem());
        String status = String.valueOf(emp_status.getSelectedItem());
        String sssid = EmpIdField.getText();

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
        } else if (status.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Supplier Status", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                employeeModel.setFirstName(firstName);
                employeeModel.setLastName(lastName);
                employeeModel.setNic(nic);
                employeeModel.setEmail(email);
                employeeModel.setMobile(mobile);
                employeeModel.setEmployeeTypeId(Integer.parseInt(employeeTypeMap.get(employee_type.getSelectedItem())));
                employeeModel.setStatusId(Integer.parseInt(StatusMap.get(emp_status.getSelectedItem())));

                new EmployeeController().update(employeeModel);

                String lane1 = Lane1Field.getText().trim();
                String lane2 = lane2Field.getText().trim();
                String cityName = (String) cityComboBox.getSelectedItem();

                if (!lane1.isEmpty() || !lane2.isEmpty() || (cityName != null && !cityName.equals("Select"))) {
                    updateAddressData(sssid);

                } else {
                    if (selectedFile != null) {
                        String imagePath = saveImage(employeeModel.getId() + nic + firstName + lastName);
                        if (imagePath != null) {
                            employeeImageModel.setPath(imagePath);

                            // check user had an image before
                            if (isEmployerHaveAnImage) {
                                new EmployeeImageController().update(employeeImageModel);
                            } else {
                                EmployeeImageModel employeeImageModel = new EmployeeImageModel();
                                employeeImageModel.setPath(imagePath);
                                employeeImageModel.setEmployeeId(employeeModel.getId());

                                new EmployeeImageController().store(employeeImageModel);
                            }

                        } else {
                            JOptionPane.showMessageDialog(this, "Image not saved correctly.", "Warning", JOptionPane.WARNING_MESSAGE);
                            this.dispose();
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Employee details updated successfully");
                    this.dispose();
                    staffJPanel.reloadTable();
                }

            } catch (Exception e) {
                e.printStackTrace();
                logger.severe("Error while updating employee : " + e.getMessage());
            }
        }
    }//GEN-LAST:event_employee_update_btnActionPerformed

    private void updateAddressData(String employeeId) {
        String lane1 = Lane1Field.getText().isEmpty() ? null : Lane1Field.getText();
        String lane2 = lane2Field.getText().isEmpty() ? null : lane2Field.getText();
        String cityName = (String) cityComboBox.getSelectedItem();
        String cityId = null;

        try {
            if (cityName != null && !cityName.equals("Select")) {
                for (Map.Entry<Integer, String> entry : CityMap.entrySet()) {
                    if (entry.getValue().equals(cityName)) {
                        cityId = String.valueOf(entry.getKey());
                        break;
                    }
                }
            }

            if ((lane1 == null || lane1.isEmpty()) || (lane2 == null || lane2.isEmpty())) {
                JOptionPane.showMessageDialog(this, "Please fill in both Address Lanes.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (cityName == null || cityName.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Address requires a city. Please select a city.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            AddressModel addresszModel = new AddressModel();
            addresszModel.setLane1(lane1);
            addresszModel.setLane2(lane2);
            addresszModel.setCity(cityId);
            addresszModel.setEmpId(employeeId);

            String addressId = new AddressController().retrieveeEmpAddressId(employeeId);
            if (addressId != null) {
                addresszModel.setEmpId(addressId);

                new AddressController().update2(addresszModel);
            } else {
                new AddressController().create2(addresszModel);
            }
            JOptionPane.showMessageDialog(this, "Employee address updated successfully.");
            this.dispose();

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while updating address: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error updating address: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

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
                logger.severe("Error while saving Image : " + ex.getMessage());
            }
        }
        return null;
    }

    private void employee_reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_reset_btnActionPerformed

        setEmployeeData();

    }//GEN-LAST:event_employee_reset_btnActionPerformed

    private void employee_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_typeActionPerformed

    private void employee_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_emailActionPerformed

    private void employee_imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employee_imageMouseClicked
        // TODO add your handling code here:
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.addFilter("PNG JPG AND JPEG", "png", "jpg", "jpeg");
        boolean result = fileChooser.showOpenDialog(this);

        if (result) {

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
                logger.severe("Error while reading selected File : " + ex.getMessage());

            }
        } else {
            Notifications.getInstance().show(
                    Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT,
                    "File Not Selected !");
        }
    }//GEN-LAST:event_employee_imageMouseClicked

    private void cityComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cityComboBoxActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        FlatMacDarkLaf.setup();
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                EmployeeUpdate dialog = new EmployeeUpdate(new java.awt.Frame(), true, firstName, lastName, email, mobile);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EmpIdField;
    private javax.swing.JTextField Lane1Field;
    private javax.swing.JComboBox<String> cityComboBox;
    private javax.swing.JComboBox<String> emp_status;
    private javax.swing.JTextField employee_email;
    private javax.swing.JTextField employee_firstname;
    private javax.swing.JLabel employee_image;
    private javax.swing.JTextField employee_lastname;
    private javax.swing.JTextField employee_mobile;
    private javax.swing.JTextField employee_nic;
    private javax.swing.JButton employee_reset_btn;
    private javax.swing.JLabel employee_status;
    private javax.swing.JComboBox<String> employee_type;
    private javax.swing.JButton employee_update_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField lane2Field;
    // End of variables declaration//GEN-END:variables

}
