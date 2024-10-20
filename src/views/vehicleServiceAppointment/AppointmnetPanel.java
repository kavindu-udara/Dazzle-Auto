/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.vehicleServiceAppointment;

import com.formdev.flatlaf.FlatClientProperties;
import controllers.AppointmentController;
import controllers.AppointmentStatusController;
import includes.MySqlConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import includes.LoggerConfig;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import raven.toast.Notifications;
import views.components.appointmentTableRender.ChangeStatusActionCellEditor;
import views.components.appointmentTableRender.ChangeStatusActionEvent;
import views.components.appointmentTableRender.ChangeStatusCellRender;
import views.dashboard.Dashboard;

/**
 *
 * @author Dinuka
 */
public class AppointmnetPanel extends javax.swing.JPanel {

    AppointmnetPanel appointmnetPanel = this;
    Dashboard Dashboard;

    private static Logger logger = LoggerConfig.getLogger();

    private static HashMap<String, Integer> appointmentStatusHashMap = new HashMap<>();

    public AppointmnetPanel(Dashboard dashboard) {
        this.Dashboard = dashboard;
        initComponents();

        datePicker1.setEditor(jDateTextField);
        vehicleSearchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "CAA-0000");
        jAppointmentNoField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "AP000000");

        AppintmentTableRender();
        loadAppointmentStatus();
        loadAppointments();

        datePicker1.now();
        Notifications.getInstance().setJFrame(Dashboard);
    }

    private void AppintmentTableRender() {

        ChangeStatusActionEvent event = new ChangeStatusActionEvent() {

            @Override
            public void onView(int row) {

                String appointmentNumber = String.valueOf(jAppointmentTable.getValueAt(row, 0));
                String status = String.valueOf(jAppointmentTable.getValueAt(row, 5));

                try {
                    if (status.equals("Pending")) {
                        new AppointmentController().updateStatus(appointmentNumber, 2);

                        Notifications.getInstance().show(
                                Notifications.Type.INFO,
                                Notifications.Location.TOP_RIGHT,
                                " Scheduled Appointment " + appointmentNumber + " Ongoing ");

                    } else if (status.equals("Ongoing")) {
                        new AppointmentController().updateStatus(appointmentNumber, 3);

                        Notifications.getInstance().show(
                                Notifications.Type.INFO,
                                Notifications.Location.TOP_RIGHT,
                                " Scheduled Appointment " + appointmentNumber + " Closed ");

                    } else if (status.equals("Closed")) {
                        //
                    }

                    loadAppointments();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.warning("Error while AppintmentTableRender() : " + e.getMessage());
                }

            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = jAppointmentTable.getTableHeader();

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Font headerFont = new Font("Verdana", Font.BOLD, 14);
                label.setBorder(BorderFactory.createEmptyBorder()); // Remove header borders
                label.setFont(headerFont);
                label.setBackground(new Color(5, 15, 76)); // Optional: Set header background color
                label.setForeground(Color.WHITE); // Optional: Set header text color
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return label;
            }
        });

        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));
        jAppointmentTable.getColumnModel().getColumn(6).setCellRenderer(new ChangeStatusCellRender());
        jAppointmentTable.getColumnModel().getColumn(6).setCellEditor(new ChangeStatusActionCellEditor(event));

        for (int i = 0; i < 6; i++) {
            jAppointmentTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void loadAppointmentStatus() {

        try {
            ResultSet resultSet = new AppointmentStatusController().show();

            Vector vector = new Vector();

            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                appointmentStatusHashMap.put(resultSet.getString("status"), resultSet.getInt("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            jStatusComboBox.setModel(comboBoxModel);

        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Error while loadVehicleTypes : " + e.getMessage());
        }

    }

    public void loadAppointments() {

        String date = "";
        if (datePicker1.isDateSelected()) {
            date = String.valueOf(datePicker1.getSelectedDate());
        }
        String vehicleNumber = vehicleSearchField.getText();
        String appointmentNumber = jAppointmentNoField.getText();

        try {

            String query = "SELECT * FROM appointment "
                    + "INNER JOIN services ON appointment.services_id=services.id "
                    + "INNER JOIN appointment_status ON appointment.appointment_status_id=appointment_status.id "
                    + "WHERE `appointment`.`vehicle_number` LIKE '%" + vehicleNumber + "%' "
                    + "AND  `appointment`.`id` LIKE '%" + appointmentNumber + "%'"
                    + "AND `appointment`.`date` LIKE '%" + date + "%' ";

            String status = String.valueOf(jStatusComboBox.getSelectedItem());
            int statusID = appointmentStatusHashMap.get(status);

            query += " AND `appointment_status_id`LIKE'%" + statusID + "%'";

            query += " ORDER BY `appointment`.`date` ASC ";

            ResultSet resultSet = MySqlConnection.executeSearch(query);

            DefaultTableModel dtm = (DefaultTableModel) jAppointmentTable.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("vehicle_number"));
                vector.add(resultSet.getString("services.name"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                vector.add(dateFormat.format(dateFormat.parse(resultSet.getString("appointment.date"))));
                vector.add(resultSet.getString("note"));
                vector.add(resultSet.getString("appointment_status.status"));

                dtm.addRow(vector);
            }

            jAppointmentTable.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Error while loadAppointments() : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datePicker1 = new raven.datetime.component.date.DatePicker();
        jPanel1 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jAppointmentTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jAddAppointmentButton = new javax.swing.JButton();
        jDateTextField = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        vehicleSearchField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jStatusComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jAppointmentNoField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1100, 610));
        setPreferredSize(new java.awt.Dimension(1100, 610));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jAppointmentTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jAppointmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Appointment No.", "Vehicle", "Service", "Service Date", "Note ", "Status", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jAppointmentTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jAppointmentTable.setFocusable(false);
        jAppointmentTable.setRowHeight(40);
        jAppointmentTable.getTableHeader().setReorderingAllowed(false);
        jAppointmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jAppointmentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jAppointmentTable);
        if (jAppointmentTable.getColumnModel().getColumnCount() > 0) {
            jAppointmentTable.getColumnModel().getColumn(6).setPreferredWidth(150);
            jAppointmentTable.getColumnModel().getColumn(6).setMaxWidth(80);
        }

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/appointment-30.png"))); // NOI18N
        jLabel1.setText("   Appointments");

        jAddAppointmentButton.setBackground(new java.awt.Color(199, 232, 199));
        jAddAppointmentButton.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jAddAppointmentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/add-user-30.png"))); // NOI18N
        jAddAppointmentButton.setText(" ADD APPOINTMENT");
        jAddAppointmentButton.setBorderPainted(false);
        jAddAppointmentButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jAddAppointmentButton.setFocusPainted(false);
        jAddAppointmentButton.setFocusable(false);
        jAddAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddAppointmentButtonActionPerformed(evt);
            }
        });

        jDateTextField.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jDateTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateTextFieldPropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel2.setText("Appointment No. :");

        vehicleSearchField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        vehicleSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vehicleSearchFieldKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel3.setText("Select Date :");

        jStatusComboBox.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jStatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Car", "Van" }));
        jStatusComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jStatusComboBox.setFocusable(false);
        jStatusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStatusComboBoxActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/filter-30.png"))); // NOI18N

        jAppointmentNoField.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jAppointmentNoField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jAppointmentNoFieldKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel4.setText("Vehicle No. :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1042, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(67, 67, 67)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(jAppointmentNoField))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(vehicleSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(8, 8, 8)
                                .addComponent(jStatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(jAddAppointmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 27, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jStatusComboBox)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vehicleSearchField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jAddAppointmentButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jAppointmentNoField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
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
    }// </editor-fold>//GEN-END:initComponents

    private void jAddAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddAppointmentButtonActionPerformed

        VehicleServiceAppointment vehicleServiceAppointment = new VehicleServiceAppointment(appointmnetPanel);
        vehicleServiceAppointment.setVisible(true);

    }//GEN-LAST:event_jAddAppointmentButtonActionPerformed

    private void jStatusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStatusComboBoxActionPerformed
        loadAppointments();
    }//GEN-LAST:event_jStatusComboBoxActionPerformed

    private void vehicleSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vehicleSearchFieldKeyReleased
        loadAppointments();
    }//GEN-LAST:event_vehicleSearchFieldKeyReleased

    private void jAppointmentNoFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAppointmentNoFieldKeyReleased
        loadAppointments();
    }//GEN-LAST:event_jAppointmentNoFieldKeyReleased

    private void jDateTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateTextFieldPropertyChange
        if (datePicker1.isDateSelected()) {
            loadAppointments();
        }

    }//GEN-LAST:event_jDateTextFieldPropertyChange

    private void jAppointmentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAppointmentTableMouseClicked

        if (evt.getClickCount() == 2) {
            int row = jAppointmentTable.getSelectedRow();
            String appointmentNumber = String.valueOf(jAppointmentTable.getValueAt(row, 0));
            String vehicleNumber = String.valueOf(jAppointmentTable.getValueAt(row, 1));
            String serviceName = String.valueOf(jAppointmentTable.getValueAt(row, 2));
            String serviceDate = String.valueOf(jAppointmentTable.getValueAt(row, 3));
            String note = String.valueOf(jAppointmentTable.getValueAt(row, 4));

            new AppointmentSuccessDialog(null, true, appointmentNumber, vehicleNumber, serviceName, serviceDate, note).setVisible(true);
        }
    }//GEN-LAST:event_jAppointmentTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.datetime.component.date.DatePicker datePicker1;
    private javax.swing.JButton jAddAppointmentButton;
    private javax.swing.JTextField jAppointmentNoField;
    private javax.swing.JTable jAppointmentTable;
    private javax.swing.JFormattedTextField jDateTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> jStatusComboBox;
    private javax.swing.JTextField vehicleSearchField;
    // End of variables declaration//GEN-END:variables
}
