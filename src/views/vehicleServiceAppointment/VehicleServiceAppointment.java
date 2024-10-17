/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.vehicleServiceAppointment;

import controllers.ServicesController;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import views.vehicle.VehicleSelecter;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author ILMA
 */
public class VehicleServiceAppointment extends javax.swing.JFrame {

    private static HashMap<String, String> servicesNameHashMap = new HashMap<>();

    public VehicleServiceAppointment() {
        initComponents();
        loadServicesTypes();
    }

    private void loadServicesTypes() {

        try {
            ResultSet resultSet = new ServicesController().show();

            Vector vector = new Vector();

            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                servicesNameHashMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            services_name.setModel(comboBoxModel);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setVehicleDetails(String vehicleNumber, String owner, String brand, String model, String type) {
        jVehicleNoLabel.setText(vehicleNumber);
        jBrandModelLabel.setText(brand + " " + model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        services_name = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        appointment_btn = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        additional_issues = new javax.swing.JTextArea();
        date = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jVehicleNoLabel = new javax.swing.JLabel();
        jBrandModelLabel = new javax.swing.JLabel();
        existing_vehicle_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        services_name.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        services_name.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular maintenance ", "Specific repair " }));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setText("Type of Service");

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel9.setText("Select Date");

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel10.setText("Any Additional Comments or Issues");

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

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel13.setText("Vehicle Number");

        jLabel14.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel14.setText("Vehicle Model ");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(appointment_btn)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(jLabel8))
                            .addGap(73, 73, 73)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(services_name, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jVehicleNoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(existing_vehicle_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBrandModelLabel)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(110, 110, 110)
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addComponent(existing_vehicle_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jVehicleNoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jBrandModelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(services_name, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appointment_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        String servicesName = String.valueOf(services_name.getSelectedItem());
        String additionalIssues = additional_issues.getText();

        if (servicesName.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a service type", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (additionalIssues.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter any additional issues", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            // TODO : make empoinment store precess
        }
    }//GEN-LAST:event_appointment_btnActionPerformed

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
        services_name.setSelectedIndex(0);
        additional_issues.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea additional_issues;
    private javax.swing.JButton appointment_btn;
    private javax.swing.JFormattedTextField date;
    private javax.swing.JButton existing_vehicle_btn;
    private javax.swing.JLabel jBrandModelLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jVehicleNoLabel;
    private javax.swing.JButton reset;
    private javax.swing.JComboBox<String> services_name;
    // End of variables declaration//GEN-END:variables
}
