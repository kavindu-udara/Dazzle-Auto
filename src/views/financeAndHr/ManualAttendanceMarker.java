/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views.financeAndHr;

import controllers.AttendanceDateController;
import controllers.EmployeeAttendanceController;
import controllers.EmployeeController;
import includes.LoggerConfig;
import includes.TimestampsGenerator;
import java.sql.ResultSet;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kavindu
 */
public class ManualAttendanceMarker extends javax.swing.JDialog {
    
    private String action = "";
    private static final Logger logger = LoggerConfig.getLogger();
    
    private String todayDate = TimestampsGenerator.getTodayDate();
    private String employeeId;
    private Runnable actionMethod;
    
    private boolean isUserAlreadyCheckin = false;

    /**
     * Creates new form ManualAttendanceMarker
     */
    public ManualAttendanceMarker(java.awt.Frame parent, boolean modal, String employeeId, Runnable actionMethod) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.action = action;
        this.employeeId = employeeId;
        this.actionMethod = actionMethod;
        checkingData();
    }
    
    private void checkingData() {
        try {
            ResultSet dateResultSet = getAttendanceDateResultSet();
            if (dateResultSet.next()) {
                ResultSet employeeAttendanceResultSet = new EmployeeAttendanceController().show(employeeId, dateResultSet.getInt("id"));
                if (employeeAttendanceResultSet.next()) {
                    if (employeeAttendanceResultSet.getString("checkin") != null) {
                        jButton1.setEnabled(false);
                    } else {
                        jButton2.setEnabled(false);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while checking data: " + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setBackground(new java.awt.Color(33, 43, 108));
        jButton1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("CHECKIN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(33, 43, 108));
        jButton2.setText("CHECKOUT");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 43, 108)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        checkInEmployer();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        checkOutEmployer();
    }//GEN-LAST:event_jButton2ActionPerformed
    private ResultSet getAttendanceDateResultSet() throws Exception {
        return new AttendanceDateController().show(todayDate);
    }
    
    private ResultSet getEmployerResultSet(String id) throws Exception {
        return new EmployeeController().show(id);
    }
    
    private void checkInEmployer() {
        int dateId = 0;
        try {
            ResultSet dateResultSet = getAttendanceDateResultSet();
            if (dateResultSet.next()) {
                dateId = dateResultSet.getInt("id");
            } else {
                JOptionPane.showMessageDialog(null, "Schedule First");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while checking employee: " + e.getMessage());
        }
        
        String currentTime = TimestampsGenerator.getCurrentTime();
        if (dateId != 0) {
            try {
                ResultSet employeeResultSet = getEmployerResultSet(employeeId);
                if (employeeResultSet.next()) {
                    try {
                        ResultSet checkInResultSet = new EmployeeAttendanceController().updateCheckInByUserId(currentTime, employeeResultSet.getString("id"), dateId);
                        JOptionPane.showMessageDialog(null, "mark attendance success");
                        actionMethod.run();
                        dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        logger.severe("Error while update checking : " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "employee not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.severe("Error while getting employee data : " + e.getMessage());
            }
        }
    }
    
    private void checkOutEmployer() {
        int dateId = 0;
        try {
            ResultSet dateResultSet = getAttendanceDateResultSet();
            if (dateResultSet.next()) {
                dateId = dateResultSet.getInt("id");
            } else {
                JOptionPane.showMessageDialog(null, "Schedule First");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while checkout user : " + e.getMessage());
        }
        
        String currentTime = TimestampsGenerator.getCurrentTime();
        if (dateId != 0) {
            try {
                ResultSet employeeResultSet = getEmployerResultSet(employeeId);
                if (employeeResultSet.next()) {
                    ResultSet employeeAttendanceResultSet = new EmployeeAttendanceController().showByDateIdAndEmpId(employeeResultSet.getString("id"), dateId);
                    if (employeeAttendanceResultSet.next()) {
                        if (employeeAttendanceResultSet.getString("checkin") != null) {
                            try {
                                ResultSet checkInResultSet = new EmployeeAttendanceController().updateCheckOutByUserId(currentTime, employeeResultSet.getString("id"), dateId);
                                JOptionPane.showMessageDialog(null, "mark attendance success");
                                actionMethod.run();
                                this.dispose();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                logger.severe("Error while checkout : " + ex.getMessage());
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Check in first !");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "employee not found");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.severe("Error while getting emp data: " + e.getMessage());
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables
}
