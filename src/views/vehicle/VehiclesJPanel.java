/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.vehicle;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import views.vehicle.vehicleTableRender.HistoryActionCellEditor;
import views.vehicle.vehicleTableRender.HistoryActionEvent;
import views.vehicle.vehicleTableRender.HistoryCellRender;

/**
 *
 * @author Dinuka
 */
public class VehiclesJPanel extends javax.swing.JPanel {

    /**
     * Creates new form VehiclesJPanel
     */
    public VehiclesJPanel() {
        initComponents();
        
        vehicleFindField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "CAA-0000");
        
        vehicleViewTableRender();
    }

    
    public void vehicleViewTableRender() {
        
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

        vehicleViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"ede", "ded", "dede", "dede", null, null}
            },
            new String [] {
                "Vehicle Number", "Owner", "Brand", "Model ", "Vehicle Type ", ""
            }
        ));
        vehicleViewTable.setFocusable(false);
        vehicleViewTable.setRowHeight(40);
        vehicleViewTable.getTableHeader().setReorderingAllowed(false);
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

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/car-30.png"))); // NOI18N
        jLabel1.setText("  Vehicles");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setText("Sort By :");

        jSortComboBox.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jSortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Brand A-Z", "Brand Z-A", "Model A-Z", "Model A-Z" }));

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
                                .addComponent(jRegNewVehicleBotton, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        new VehicleRegistration(null,true).setVisible(true);
    }//GEN-LAST:event_jRegNewVehicleBottonActionPerformed


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
