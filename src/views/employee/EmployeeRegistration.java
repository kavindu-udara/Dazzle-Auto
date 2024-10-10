/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package views.employee;

//import com.mysql.cj.protocol.Resultset;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import includes.OnlyNumbersDocumentFilter;
import includes.RegexValidator;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import includes.TimestampsGenerator;
import controllers.EmployeeTypeController;
import controllers.EmployeeController;
import includes.BDUtility;
import includes.IDGenarator;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import models.EmployeeModel;

import java.sql.ResultSet;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author USER nimsara
 */
public class EmployeeRegistration extends java.awt.Dialog {

    public EmployeeRegistration(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setDocumentFilters();
        loadTypes();

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
        }
    }

    private void setDocumentFilters() {
        ((AbstractDocument) employee_mobile.getDocument()).setDocumentFilter(new OnlyNumbersDocumentFilter());

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

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setText("EMPLOYEE REGISTRATION");

        employee_image.setBackground(new java.awt.Color(255, 255, 51));
        employee_image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employee_imageMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("First Name");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Last Name");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("Email");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setText("Mobile");

        employee_mobile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_mobileActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Nic");

        employee_register_btn.setBackground(new java.awt.Color(33, 43, 108));
        employee_register_btn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        employee_register_btn.setForeground(new java.awt.Color(255, 255, 255));
        employee_register_btn.setText("REGISTER");
        employee_register_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_register_btnActionPerformed(evt);
            }
        });

        employee_reset_btn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        employee_reset_btn.setForeground(new java.awt.Color(255, 0, 0));
        employee_reset_btn.setText("RESET");
        employee_reset_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        employee_reset_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_reset_btnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setText("Employee Type");

        employee_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", " " }));
        employee_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_typeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(employee_register_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(employee_reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(employee_nic, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                    .addComponent(employee_mobile, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                    .addComponent(employee_firstname, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                    .addComponent(employee_email, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                    .addComponent(employee_lastname, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                    .addComponent(employee_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(employee_image, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel1)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(employee_image, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(employee_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employee_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employee_email, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employee_nic, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employee_mobile, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(employee_type, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employee_register_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employee_reset_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
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

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void employee_register_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_register_btnActionPerformed

        String firstName = employee_firstname.getText();
        String lastName = employee_lastname.getText();
        String nic = employee_nic.getText();
        String email = employee_email.getText();
        String mobile = employee_mobile.getText();
        String employeeType = String.valueOf(employee_type.getSelectedItem());

        if (firstName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (lastName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your last name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your email", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!RegexValidator.isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid email", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (nic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your nic", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!RegexValidator.isValidSlNewNic(nic)) {
            JOptionPane.showMessageDialog(this, "Invalid NIC Number", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your mobile", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!RegexValidator.isValidSlPhone(mobile)) {
            JOptionPane.showMessageDialog(this, "Invalid mobile Number", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (employeeType.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a employee type", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            //store
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

                JOptionPane.showMessageDialog(this, "Employee Registration Successfully");
                reset();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

        }
    }//GEN-LAST:event_employee_register_btnActionPerformed


    private void employee_reset_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_reset_btnActionPerformed

        reset();

    }//GEN-LAST:event_employee_reset_btnActionPerformed

    private void employee_mobileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_mobileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_mobileActionPerformed

    private void employee_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_typeActionPerformed

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

            }
        }
    }//GEN-LAST:event_employee_imageMouseClicked

    private String saveImage(String email) {

        if (originalImage != null && selectedFile != null) {
            try {

                String savePath = BDUtility.getPath("resources\\employeeImages\\");
                String extension = BDUtility.getFileExtension(selectedFile.getName());
                String imageName = email + "." + extension;
                File saveFile = new File(savePath + imageName);
                BufferedImage scaledImage = BDUtility.scaleImage(originalImage, ImageIO.read(selectedFile));
                ImageIO.write(scaledImage, extension, saveFile);
                return imageName;

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EmployeeRegistration dialog = new EmployeeRegistration(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private void reset() {

        employee_firstname.setText("");
        employee_lastname.setText("");
        employee_mobile.setText("");
        employee_nic.setText("");
        employee_email.setText("");
        employee_type.setSelectedIndex(0);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
