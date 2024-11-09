/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package views.employee;

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
import java.awt.image.BufferedImage;
import java.io.File;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import models.EmployeeImageModel;
import models.EmployeeModel;
import java.util.logging.Logger;

/**
 *
 * @author USER nimsara
 */
public class EmployeeUpdate extends java.awt.Dialog {

    private static HashMap<String, String> employeeTypeMap = new HashMap<>();
    private static HashMap<String, String> StatusMap = new HashMap<>();

    private static final Logger logger = LoggerConfig.getLogger();

    private boolean isEmployerHaveAnImage = false;

    private EmployeeModel employeeModel;
    private EmployeeImageModel employeeImageModel = new EmployeeImageModel();

    private StaffJPanel staffJPanel;

    public EmployeeUpdate(Frame parent, boolean modal, EmployeeModel employeeModel, StaffJPanel staffJPanel) {
        super(parent, modal);
        initComponents();
        setDocumentFilters();
        loadEmployeeTypes();
        loadempStatus();

        this.employeeModel = employeeModel;
        this.staffJPanel = staffJPanel;

        setEmployeeData();
        loadAmployeeImage();

    }

    private void setEmployeeData() {
        employee_firstname.setText(employeeModel.getFirstName());
        employee_lastname.setText(employeeModel.getLastName());
        employee_email.setText(employeeModel.getEmail());
        employee_nic.setText(employeeModel.getNic());
        employee_mobile.setText(employeeModel.getMobile());
        employee_type.setSelectedItem(employeeModel.getEmployeeTypeName());
        emp_status.setSelectedItem(employeeModel.getStatusName());

        try {
            ResultSet resultSet = new StatusController().show(employeeModel.getStatusId());
            if (resultSet.next()) {
                emp_status.setSelectedItem(resultSet.getString("status"));
            }
        } catch (Exception e) {
            logger.severe("Error while setting status : " + e.getMessage());
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

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("EMPLOYEE DETAILS");

        employee_image.setBackground(new java.awt.Color(255, 255, 51));
        employee_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        employee_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employee_imageMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("First Name");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Last Name");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Email");

        employee_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_emailActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Mobile");

        employee_mobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_mobileActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Nic");

        employee_update_btn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        employee_update_btn.setText("UPDATE");
        employee_update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_update_btnActionPerformed(evt);
            }
        });

        employee_reset_btn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        employee_reset_btn.setText("RESET");
        employee_reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_reset_btnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setText("Employee Type");

        employee_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_typeActionPerformed(evt);
            }
        });

        employee_status.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        employee_status.setText("Status");

        emp_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(employee_reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(36, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(employee_update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(employee_email, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel7)
                                                    .addComponent(employee_status))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(emp_status, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(employee_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(employee_mobile, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(employee_nic, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(264, 264, 264)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(229, 229, 229)))))
                .addGap(84, 84, 84))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(199, 199, 199)
                            .addComponent(employee_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(199, 199, 199)
                            .addComponent(employee_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(175, 175, 175)
                            .addComponent(employee_image, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(84, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(46, 46, 46)
                .addComponent(jLabel4)
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employee_email, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employee_nic, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employee_mobile, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employee_type, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emp_status, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employee_status))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employee_update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employee_reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(65, 65, 65)
                    .addComponent(employee_image, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(49, 49, 49)
                    .addComponent(employee_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25)
                    .addComponent(employee_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(320, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    }
                }
                JOptionPane.showMessageDialog(this, "Employee details updated successfully");
                staffJPanel.reloadTable();
            } catch (Exception e) {
                e.printStackTrace();
                logger.severe("Error while updating employee : " + e.getMessage());
            }
        }
    }//GEN-LAST:event_employee_update_btnActionPerformed

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

        reset();

    }//GEN-LAST:event_employee_reset_btnActionPerformed

    private void employee_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_typeActionPerformed

    private void employee_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_emailActionPerformed

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
                logger.severe("Error while reading selected File : " + ex.getMessage());

            }
        }
    }//GEN-LAST:event_employee_imageMouseClicked

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
    private void reset() {

        employee_firstname.setText("");
        employee_lastname.setText("");
        employee_mobile.setText("");
        employee_nic.setText("");
        employee_email.setText("");
        employee_type.setSelectedIndex(0);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

}
