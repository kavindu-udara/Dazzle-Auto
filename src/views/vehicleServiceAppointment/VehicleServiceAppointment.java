/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.vehicleServiceAppointment;

import controllers.AppointmentController;
import includes.IDGenarator;
import views.vehicle.VehicleSelecter;
import javax.swing.JOptionPane;
import models.AppointmentModel;
import views.ourServices.OurServicesSelecter;
import includes.LoggerConfig;
import includes.Mailer;
import java.sql.ResultSet;
import java.util.logging.Logger;

/**
 *
 * @author ILMA
 */
public class VehicleServiceAppointment extends javax.swing.JFrame {

    private static Logger logger = LoggerConfig.getLogger();

    AppointmnetPanel AppointmnetPanel = null;

    public VehicleServiceAppointment(AppointmnetPanel appointmnetPanel) {
        initComponents();
        this.AppointmnetPanel = appointmnetPanel;

        datePicker1.setEditor(dateField);

        jServiceIdLabel.setVisible(false);
    }

    public void setVehicleDetails(String vehicleNumber, String owner, String brand, String model, String type) {
        jVehicleNoLabel.setText(vehicleNumber);
        jBrandModelLabel.setText(brand + " " + model);
        jTypeLabel.setText(type);
    }

    public void setServiceDetails(String id, String name, String charge) {
        jServiceNameLabel.setText(name);
        jServiceIdLabel.setText(id);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datePicker1 = new raven.datetime.component.date.DatePicker();
        jPanel1 = new javax.swing.JPanel();
        jServiceNameLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        appointment_btn = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        additional_issues = new javax.swing.JTextArea();
        dateField = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jVehicleNoLabel = new javax.swing.JLabel();
        jBrandModelLabel = new javax.swing.JLabel();
        existing_vehicle_btn = new javax.swing.JButton();
        jServiceSelectorButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jServiceIdLabel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTypeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jServiceNameLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jServiceNameLabel.setForeground(new java.awt.Color(204, 0, 0));
        jServiceNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jServiceNameLabel.setText("Selected Service");
        jServiceNameLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 1, true));

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel10.setText("Any Additional Note or Issues");

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" Set Up New Appointment");

        appointment_btn.setBackground(new java.awt.Color(33, 43, 108));
        appointment_btn.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        appointment_btn.setForeground(new java.awt.Color(255, 255, 255));
        appointment_btn.setText("MAKE APPOINTMENT");
        appointment_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        appointment_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointment_btnActionPerformed(evt);
            }
        });

        reset.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        reset.setForeground(new java.awt.Color(255, 0, 0));
        reset.setText("RESET");
        reset.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reset.setPreferredSize(new java.awt.Dimension(72, 35));
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        additional_issues.setColumns(20);
        additional_issues.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        additional_issues.setRows(5);
        jScrollPane1.setViewportView(additional_issues);

        dateField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Vehicle Number");

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Vehicle Type");

        jVehicleNoLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jVehicleNoLabel.setText("...............................................");

        jBrandModelLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jBrandModelLabel.setText("...............................................");

        existing_vehicle_btn.setBackground(new java.awt.Color(241, 145, 8));
        existing_vehicle_btn.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        existing_vehicle_btn.setForeground(new java.awt.Color(255, 255, 255));
        existing_vehicle_btn.setText("Select Vehicle");
        existing_vehicle_btn.setBorderPainted(false);
        existing_vehicle_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        existing_vehicle_btn.setPreferredSize(new java.awt.Dimension(157, 35));
        existing_vehicle_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                existing_vehicle_btnActionPerformed(evt);
            }
        });

        jServiceSelectorButton.setBackground(new java.awt.Color(36, 53, 156));
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

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Select Date");

        jServiceIdLabel.setText("0");

        jLabel15.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Vehicle Model ");

        jTypeLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jTypeLabel.setText("...............................................");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(existing_vehicle_btn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jVehicleNoLabel)
                            .addComponent(jBrandModelLabel)
                            .addComponent(jTypeLabel)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(appointment_btn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jServiceSelectorButton, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jServiceNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(dateField)))))
                        .addGap(18, 18, 18)
                        .addComponent(jServiceIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(existing_vehicle_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jVehicleNoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jBrandModelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jServiceIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jServiceSelectorButton, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jServiceNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appointment_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void existing_vehicle_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_existing_vehicle_btnActionPerformed
        new VehicleSelecter(this, true, "VehicleServiceAppointment").setVisible(true);
    }//GEN-LAST:event_existing_vehicle_btnActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed

        reset();

    }//GEN-LAST:event_resetActionPerformed

    private void appointment_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointment_btnActionPerformed

        String appointmentID = IDGenarator.appointmentID();
        String vehicleNumber = jVehicleNoLabel.getText();
        String vehicleModel = jBrandModelLabel.getText();
        String vehicleType = jTypeLabel.getText();
        String serviceName = jServiceNameLabel.getText();
        int serviceID = Integer.valueOf(jServiceIdLabel.getText());
        String date = String.valueOf(datePicker1.getSelectedDate());
        String note = additional_issues.getText();

        if (vehicleNumber.equals("...............................................")) {
            JOptionPane.showMessageDialog(this, "Please Select Vehicle", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jServiceNameLabel.getText().equals("Selected Service")) {
            JOptionPane.showMessageDialog(this, "Please Select Service", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!datePicker1.isDateSelected()) {
            JOptionPane.showMessageDialog(this, "Please Select Appointment Date ", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (note.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Make a note of something !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
                AppointmentModel appointmentModel = new AppointmentModel();
                appointmentModel.setId(appointmentID);
                appointmentModel.setVehicleNumber(vehicleNumber);
                appointmentModel.setVehicleModel(vehicleModel);
                appointmentModel.setVehicleType(vehicleType);
                appointmentModel.setServiceName(serviceName);
                appointmentModel.setServiceId(serviceID);
                appointmentModel.setDate(date);
                appointmentModel.setNote(note);
                appointmentModel.setAppointmentStatusId(1);

                ResultSet rs = new AppointmentController().show(vehicleNumber, serviceID, date);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "This Appointment Already Scheduled !", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    new AppointmentController().store(appointmentModel);

                    logger.info("New Appointment Scheduled : "
                            + "" + appointmentID + " | For : "
                            + "" + vehicleNumber + " | Service : "
                            + "" + serviceName + " | Date : "
                            + "" + date + " | Note : "
                            + "" + note);

                    reset();

                    this.dispose();
                    new AppointmentSuccessDialog(this, true, appointmentModel).setVisible(true);

                    AppointmnetPanel.loadAppointments();

                    
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.warning("Error while appointment_btnActionPerformed : " + ex.getMessage());
            }

        }
    }//GEN-LAST:event_appointment_btnActionPerformed

    private void jServiceSelectorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServiceSelectorButtonActionPerformed
        String vehicleNo = jVehicleNoLabel.getText();
        String vehicleType = jTypeLabel.getText();

        if (vehicleNo.equals("...............................................")) {
            JOptionPane.showMessageDialog(this, "Please Select Vehicle First !", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            new OurServicesSelecter(this, true, "VehicleServiceAppointment", vehicleType).setVisible(true);
        }
    }//GEN-LAST:event_jServiceSelectorButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    //public static void main(String args[]) {
//        FlatMacDarkLaf.setup();
    //    IntelliJTheme.setup(Dashboard.class.getResourceAsStream(
    //           "/resources/themes/arc-theme.theme.json"));
    //   java.awt.EventQueue.invokeLater(new Runnable() {
    //      public void run() {
    //           new VehicleServiceAppointment().setVisible(true);
    //       }
    //   });
//    }
    private void reset() {
        additional_issues.setText("");
        jVehicleNoLabel.setText("...............................................");
        jBrandModelLabel.setText("...............................................");
        jTypeLabel.setText("...............................................");
        jServiceNameLabel.setText("Selected Service");
        jServiceIdLabel.setText("0");
        datePicker1.clearSelectedDate();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea additional_issues;
    private javax.swing.JButton appointment_btn;
    private javax.swing.JFormattedTextField dateField;
    private raven.datetime.component.date.DatePicker datePicker1;
    private javax.swing.JButton existing_vehicle_btn;
    private javax.swing.JLabel jBrandModelLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jServiceIdLabel;
    private javax.swing.JLabel jServiceNameLabel;
    private javax.swing.JButton jServiceSelectorButton;
    private javax.swing.JLabel jTypeLabel;
    private javax.swing.JLabel jVehicleNoLabel;
    private javax.swing.JButton reset;
    // End of variables declaration//GEN-END:variables
}
