/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.vehicle;

import com.formdev.flatlaf.FlatClientProperties;
import controllers.VehicleTypeController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import views.components.vehicleTableRender.HistoryActionCellEditor;
import views.components.vehicleTableRender.HistoryActionEvent;
import views.components.vehicleTableRender.HistoryCellRender;
import views.mainInvoice.MainInvoice;
import views.vehicleServiceAppointment.VehicleServiceAppointment;
import includes.LoggerConfig;
import includes.MySqlConnection;
import java.util.logging.Logger;

/**
 *
 * @author Dinuka
 */
public class VehiclesJPanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();

    VehiclesJPanel vehiclesJPanel = this;

    VehicleSelecter VehicleSelecterFrame = null;

    MainInvoice mainInvoice = null;
    VehicleServiceAppointment vehicleServiceAppointment = null;
    String From = "";
    String BaseFrame = "";

    private static HashMap<String, String> vehicleTypesHashMap = new HashMap<>();

    public VehiclesJPanel() {
        initComponents();

        vehicleFindField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "CAA-0000");

        vehicleViewTableRender();
        loadVehicleTypes();
        loadVehicles();
    }

    //Constructer for VehicleSelecter
    public VehiclesJPanel(Frame parentFrame, VehicleSelecter vehicleSelecter, String BaseFrame) {
        this.VehicleSelecterFrame = vehicleSelecter;
        this.From = "Selecter";
        this.BaseFrame = BaseFrame;

        if (BaseFrame.equals("MainInvoice")) {
            this.mainInvoice = (MainInvoice) parentFrame;
        } else if (BaseFrame.equals("VehicleServiceAppointment")) {
            this.vehicleServiceAppointment = (VehicleServiceAppointment) parentFrame;
        }

        initComponents();

        vehicleFindField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "CAA-0000");
        jRegNewVehicleBotton.setEnabled(false);

        vehicleViewTableRender();
        loadVehicleTypes();
        loadVehicles();
    }

    private void vehicleViewTableRender() {

        HistoryActionEvent event = new HistoryActionEvent() {

            @Override
            public void onView(int row) {

            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = vehicleViewTable.getTableHeader();

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Font headerFont = new Font("Verdana", Font.BOLD, 14);
                label.setBorder(BorderFactory.createEmptyBorder()); // Remove header borders
                label.setFont(headerFont);
                label.setBackground(new Color(214, 132, 13)); // Optional: Set header background color
                label.setForeground(Color.WHITE); // Optional: Set header text color
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return label;
            }
        });

        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));
        vehicleViewTable.getColumnModel().getColumn(5).setCellRenderer(new HistoryCellRender());
        vehicleViewTable.getColumnModel().getColumn(5).setCellEditor(new HistoryActionCellEditor(event));

        for (int i = 0; i < 5; i++) {
            vehicleViewTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void loadVehicles() {

        try {

            String query = "SELECT * FROM vehicle "
                    + "INNER JOIN customer ON vehicle.customer_id=customer.id "
                    + "INNER JOIN vehicle_brand ON vehicle.vehicle_brand_id=vehicle_brand.id "
                    + "INNER JOIN vehicle_type ON vehicle.vehicle_type_id=vehicle_type.id "
                    + "WHERE `vehicle`.`vehicle_number` LIKE '%" + vehicleFindField.getText() + "%' ";

            String vehicleType = String.valueOf(jVehicleTypeComboBox.getSelectedItem());
            if (vehicleType.equals("  All")) {
                query += " AND `vehicle_type_id`LIKE'%%'";
            } else {
                String typeID = vehicleTypesHashMap.get(vehicleType);

                query += " AND `vehicle_type_id`LIKE'%" + typeID + "%'";
            }

            String sort = String.valueOf(jSortComboBox.getSelectedItem());
            if (sort.contains("Brand A-Z")) {
                query += " ORDER BY `vehicle_brand`.`name` ASC";

            } else if (sort.contains("Brand Z-A")) {
                query += " ORDER BY `vehicle_brand`.`name` DESC";

            } else if (sort.contains("Model A-Z")) {
                query += " ORDER BY `vehicle`.`model` ASC";

            } else if (sort.contains("Model Z-A")) {
                query += " ORDER BY `vehicle`.`model` DESC";

            }

            ResultSet resultSet = MySqlConnection.executeSearch(query);

            DefaultTableModel dtm = (DefaultTableModel) vehicleViewTable.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("vehicle_number"));
                vector.add(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
                vector.add(resultSet.getString("vehicle_brand.name"));
                vector.add(resultSet.getString("vehicle.model"));
                vector.add(resultSet.getString("vehicle_type.name"));

                dtm.addRow(vector);
            }

            vehicleViewTable.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Error while loadVehicles : " + e.getMessage());
        }
    }

    private void loadVehicleTypes() {

        try {
            ResultSet resultSet = new VehicleTypeController().show();

            Vector vector = new Vector();

            vector.add("  All");
            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                vehicleTypesHashMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            jVehicleTypeComboBox.setModel(comboBoxModel);

        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Error while loadVehicleTypes : " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        vehicleFindField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        vehicleViewTable = new javax.swing.JTable();
        jRegNewVehicleBotton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jVehicleTypeComboBox = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSortComboBox = new javax.swing.JComboBox<>();

        setMinimumSize(new java.awt.Dimension(1100, 610));
        setPreferredSize(new java.awt.Dimension(1100, 610));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setMinimumSize(new java.awt.Dimension(1100, 610));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        vehicleFindField.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        vehicleFindField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Find Vehicle By Vehicle No.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 0, 14))); // NOI18N
        vehicleFindField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vehicleFindFieldKeyReleased(evt);
            }
        });

        vehicleViewTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        vehicleViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vehicle Number", "Owner", "Brand", "Model ", "Vehicle Type ", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        vehicleViewTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vehicleViewTable.setFocusable(false);
        vehicleViewTable.setRowHeight(40);
        vehicleViewTable.getTableHeader().setReorderingAllowed(false);
        vehicleViewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vehicleViewTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(vehicleViewTable);
        if (vehicleViewTable.getColumnModel().getColumnCount() > 0) {
            vehicleViewTable.getColumnModel().getColumn(5).setPreferredWidth(200);
            vehicleViewTable.getColumnModel().getColumn(5).setMaxWidth(150);
        }

        jRegNewVehicleBotton.setBackground(new java.awt.Color(199, 232, 199));
        jRegNewVehicleBotton.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jRegNewVehicleBotton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/add-car-30.png"))); // NOI18N
        jRegNewVehicleBotton.setText(" REGISTER NEW VEHICLE");
        jRegNewVehicleBotton.setBorderPainted(false);
        jRegNewVehicleBotton.setFocusPainted(false);
        jRegNewVehicleBotton.setFocusable(false);
        jRegNewVehicleBotton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRegNewVehicleBottonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/filter-30.png"))); // NOI18N

        jVehicleTypeComboBox.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jVehicleTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Car", "Van" }));
        jVehicleTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVehicleTypeComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/car-30.png"))); // NOI18N
        jLabel1.setText("  Vehicles");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setText("Sort By :");

        jSortComboBox.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jSortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Brand A-Z", "Brand Z-A", "Model A-Z", "Model Z-A" }));
        jSortComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSortComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(vehicleFindField, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jVehicleTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRegNewVehicleBotton, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRegNewVehicleBotton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vehicleFindField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jVehicleTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRegNewVehicleBottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRegNewVehicleBottonActionPerformed

        new VehicleRegistration(null, true, vehiclesJPanel).setVisible(true);
    }//GEN-LAST:event_jRegNewVehicleBottonActionPerformed

    private void vehicleViewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vehicleViewTableMouseClicked
        int row = vehicleViewTable.getSelectedRow();
        String vehicleNumber = String.valueOf(vehicleViewTable.getValueAt(row, 0));
        String vehicleOwner = String.valueOf(vehicleViewTable.getValueAt(row, 1));
        String vehicleBrand = String.valueOf(vehicleViewTable.getValueAt(row, 2));
        String vehicleModel = String.valueOf(vehicleViewTable.getValueAt(row, 3));
        String vehicleType = String.valueOf(vehicleViewTable.getValueAt(row, 4));

        if (From.equals("Selecter")) {

            if (BaseFrame.equals("MainInvoice")) {
                mainInvoice.setVehicleDetails(vehicleNumber, vehicleOwner, vehicleBrand, vehicleModel, vehicleType);
            } else if (BaseFrame.equals("VehicleServiceAppointment")) {
                vehicleServiceAppointment.setVehicleDetails(vehicleNumber, vehicleOwner, vehicleBrand, vehicleModel, vehicleType);
            }

            VehicleSelecterFrame.dispose();
        }
    }//GEN-LAST:event_vehicleViewTableMouseClicked

    private void vehicleFindFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vehicleFindFieldKeyReleased
        loadVehicles();
    }//GEN-LAST:event_vehicleFindFieldKeyReleased

    private void jVehicleTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVehicleTypeComboBoxActionPerformed
        loadVehicles();
    }//GEN-LAST:event_jVehicleTypeComboBoxActionPerformed

    private void jSortComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSortComboBoxActionPerformed
        loadVehicles();
    }//GEN-LAST:event_jSortComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jRegNewVehicleBotton;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> jSortComboBox;
    private javax.swing.JComboBox<String> jVehicleTypeComboBox;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField vehicleFindField;
    private javax.swing.JTable vehicleViewTable;
    // End of variables declaration//GEN-END:variables
}
