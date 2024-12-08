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
import jnafilechooser.api.JnaFileChooser;
import models.AddressModel;
import models.EmployeeImageModel;
import raven.toast.Notifications;
import views.dashboard.Dashboard;

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
        Notifications.getInstance().setJFrame(null);

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
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EMPLOYEE REGISTRATION");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 255, -1));

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
        jPanel1.add(employee_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 49, 123, 141));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setText("First Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 150, 34));

        employee_firstname.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jPanel1.add(employee_firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 225, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setText("Last Name");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 150, 34));

        employee_lastname.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jPanel1.add(employee_lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 228, 34));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel5.setText("Email");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 152, 34));

        employee_email.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jPanel1.add(employee_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 228, 30));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel7.setText("Mobile");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 150, 34));

        employee_mobile.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        employee_mobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_mobileActionPerformed(evt);
            }
        });
        jPanel1.add(employee_mobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 230, 34));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setText("Nic");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 146, 34));

        employee_nic.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jPanel1.add(employee_nic, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 228, 34));

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
        jPanel1.add(employee_register_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(522, 460, 160, 50));

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
        jPanel1.add(employee_reset_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 460, 160, 50));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setText("Employee Type");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, 150, 41));

        employee_type.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        employee_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", " " }));
        employee_type.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        employee_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_typeActionPerformed(evt);
            }
        });
        jPanel1.add(employee_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 228, 35));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 956, -1));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel9.setText("Address (Optional)");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, -1, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel10.setText("Lane 1");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, -1, -1));
        jPanel1.add(Lane1Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, 210, 34));

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel11.setText("Lane 2");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, -1, -1));
        jPanel1.add(lane2Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 280, 210, 34));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel12.setText("City");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, -1, -1));

        cityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cityComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(cityComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 210, 34));

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel13.setText("Postal Code");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 390, -1, -1));
        jPanel1.add(PostalCodeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 380, 210, 34));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
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
                logger.severe("Error while reading selected image : " + ex.getMessage());
            }
        } else {
            Notifications.getInstance().show(
                    Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT,
                    "File Not Selected !");
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

        if (firstName.isEmpty()) {
            showWarningMessage("Please enter your first name");
        } else if (lastName.isEmpty()) {
            showWarningMessage("Please enter your last name");
        } else if (email.isEmpty()) {
            showWarningMessage("Please enter your email");
        } else if (!RegexValidator.isValidEmail(email)) {
            showWarningMessage("Invalid email");
        } else if (nic.isEmpty()) {
            showWarningMessage("Please enter your NIC");
        } else if (!RegexValidator.isValidSlNic(nic) && !RegexValidator.isValidSlNewNic(nic)) {
            showWarningMessage("Invalid NIC Number");
        } else if (mobile.isEmpty()) {
            showWarningMessage("Please enter your mobile number");
        } else if (!RegexValidator.isValidSlPhone(mobile)) {
            showWarningMessage("Invalid mobile number");
        } else if (employeeType.equals("Select")) {
            showWarningMessage("Please select an employee type");
        } else {
            if (!isThisEmployeeAlreadyExists(nic, email)) {
                try {
                    String generatedId = IDGenarator.employeeID();

                    EmployeeModel employeeModel = new EmployeeModel();
                    employeeModel.setId(generatedId);
                    employeeModel.setFirstName(firstName);
                    employeeModel.setLastName(lastName);
                    employeeModel.setEmail(email);
                    employeeModel.setNic(nic);
                    employeeModel.setMobile(mobile);
                    employeeModel.setEmployeeTypeId(Integer.parseInt(employeeTypeMap.get(employee_type.getSelectedItem())));
                    employeeModel.setStatusId(1);
                    String registerDateTime = TimestampsGenerator.getFormattedDateTime();
                    employeeModel.setRegisteredDate(registerDateTime);

                    ResultSet resultSet = new EmployeeController().store(employeeModel);

                    String imagePath = saveImage(generatedId + nic + firstName + lastName);
                    if (imagePath != null) {
                        EmployeeImageModel employeeImageModel = new EmployeeImageModel();
                        employeeImageModel.setPath(imagePath);
                        employeeImageModel.setEmployeeId(generatedId);
                        new EmployeeImageController().store(employeeImageModel);
                    }

                    JOptionPane.showMessageDialog(this, "Employee Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);

                    staffJPanel.reloadTable();
                    reset();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    logger.severe("Error while storing employee: " + e.getMessage());
                }
            } else {
                showWarningMessage("This employee already exists (NIC or Email)");
            }
        }

    }//GEN-LAST:event_employee_register_btnActionPerformed

    private void regAddressData(String supplierId) {
        String lane1 = Lane1Field.getText();
        String lane2 = lane2Field.getText();
        String cityName = (String) cityComboBox.getSelectedItem();

        try {
            AddressModel addressModel = new AddressModel();

            addressModel.setLane1(lane1);
            addressModel.setLane2(lane2);
            addressModel.setSupId(supplierId);

            int cityId = -1;
            for (Map.Entry<Integer, String> entry : CityMap.entrySet()) {
                if (entry.getValue().equals(cityName)) {
                    cityId = entry.getKey();
                    break;
                }
            }

            if (cityId == -1) {
                JOptionPane.showMessageDialog(this, "City not found", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                addressModel.setCity(String.valueOf(cityId));
            }

            new AddressController().create(addressModel);
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while storing address : " + e.getMessage());
        }
    }
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

    private void showWarningMessage(String message) {

        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);

    }

    private boolean isThisEmployeeAlreadyExists(String nic, String email) {

        try {
            ResultSet resultSet = new EmployeeController().show();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            logger.severe("Error while load vehicle by number : " + e.getMessage());
        }
        return false;

    }
}
