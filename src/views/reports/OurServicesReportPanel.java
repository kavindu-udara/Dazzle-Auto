/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.reports;

import com.formdev.flatlaf.FlatClientProperties;
import controllers.ServicesController;
import controllers.VehicleTypeController;
import includes.LoggerConfig;
import includes.MySqlConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.InputStream;
import java.util.Vector;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import models.LoginModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import views.dashboard.Dashboard;

/**
 *
 * @author Nimsara
 */
public class OurServicesReportPanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();

    Dashboard dashboard = null;
    private static HashMap<String, String> vehicleTypesHashMap = new HashMap<>();
    private static HashMap<String, String> serviceNameHashMap = new HashMap<>();
    private static HashMap<String, String> serviceChargeHashMap = new HashMap<>();

    public OurServicesReportPanel(Dashboard dashboard) {
        initComponents();
        loadServices();
        loadVehicleTypes();
        loadServiceNames();
        loadServiceCharge();
        ourserviceTableRender();
        this.dashboard = dashboard;
        serviceFindField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter service...");
    }

    private void loadServices() {
        try {
            ResultSet resultSet = new ServicesController().show();

            DefaultTableModel tableModel = (DefaultTableModel) ourServicesViewTable2.getModel();
            tableModel.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("name"));

                try {
                    ResultSet vehicleTypeResultSet = new VehicleTypeController().show(resultSet.getInt("vehicle_type_id"));
                    if (vehicleTypeResultSet.next()) {
                        vector.add(vehicleTypeResultSet.getString("name"));
                    } else {
                        vector.add("empty");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.severe("Error while showing vehicle types : " + e.getMessage());
                }

                vector.add(resultSet.getString("charge"));

                tableModel.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while showing services : " + e.getMessage());
        }
    }

    public void ourserviceTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = ourServicesViewTable2.getTableHeader();

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Font headerFont = new Font("Verdana", Font.BOLD, 14);
                label.setBorder(BorderFactory.createEmptyBorder());
                label.setFont(headerFont);
                label.setBackground(new Color(5, 15, 76));
                label.setForeground(Color.WHITE); // Optional: Set header text color
                label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
                return label;
            }
        });

        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));

        for (int i = 0; i < 4; i++) {
            ourServicesViewTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        ourServicesViewTable2 = new javax.swing.JTable();
        viewReportb = new javax.swing.JButton();
        printReportb = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        serviceFindField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jVehicleTypeComboBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jServiceNameComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jServiceChargeComboBox2 = new javax.swing.JComboBox<>();

        setMinimumSize(new java.awt.Dimension(1100, 610));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/icons8-reports-30.png"))); // NOI18N
        jLabel1.setText("  Services Report");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/back-35.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ourServicesViewTable2.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        ourServicesViewTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Service Name", "Vehicle Type ", "Service Charge"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ourServicesViewTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ourServicesViewTable2.setFocusable(false);
        ourServicesViewTable2.setRowHeight(30);
        ourServicesViewTable2.getTableHeader().setReorderingAllowed(false);
        ourServicesViewTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ourServicesViewTable2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(ourServicesViewTable2);

        viewReportb.setBackground(new java.awt.Color(51, 51, 51));
        viewReportb.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        viewReportb.setForeground(new java.awt.Color(255, 255, 255));
        viewReportb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/analyze-30.png"))); // NOI18N
        viewReportb.setText(" Save Report");
        viewReportb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewReportb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReportbActionPerformed(evt);
            }
        });

        printReportb.setBackground(new java.awt.Color(0, 102, 0));
        printReportb.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        printReportb.setForeground(new java.awt.Color(255, 255, 255));
        printReportb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/print-30.png"))); // NOI18N
        printReportb.setText(" Print Report");
        printReportb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printReportb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReportbActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel2.setText("Find Service :");

        serviceFindField.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        serviceFindField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                serviceFindFieldKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/filter-30.png"))); // NOI18N

        jVehicleTypeComboBox.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jVehicleTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVehicleTypeComboBoxActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/filter-30.png"))); // NOI18N

        jServiceNameComboBox1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jServiceNameComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jServiceNameComboBox1ItemStateChanged(evt);
            }
        });
        jServiceNameComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jServiceNameComboBox1MouseClicked(evt);
            }
        });
        jServiceNameComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServiceNameComboBox1ActionPerformed(evt);
            }
        });
        jServiceNameComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jServiceNameComboBox1PropertyChange(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/filter-30.png"))); // NOI18N

        jServiceChargeComboBox2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jServiceChargeComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jServiceChargeComboBox2ItemStateChanged(evt);
            }
        });
        jServiceChargeComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jServiceChargeComboBox2MouseClicked(evt);
            }
        });
        jServiceChargeComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jServiceChargeComboBox2ActionPerformed(evt);
            }
        });
        jServiceChargeComboBox2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jServiceChargeComboBox2PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 981, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(12, 12, 12)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2)
                                                .addComponent(serviceFindField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(32, 32, 32)
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jVehicleTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(33, 33, 33)
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jServiceNameComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jServiceChargeComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(viewReportb)
                                        .addGap(27, 27, 27)
                                        .addComponent(printReportb))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1085, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serviceFindField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jVehicleTypeComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jServiceNameComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jServiceChargeComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewReportb)
                    .addComponent(printReportb))
                .addGap(20, 20, 20))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dashboard.jReportPanel.remove(this);
        SwingUtilities.updateComponentTreeUI(dashboard.jReportPanel);

        dashboard.reportsJPanel = new ReportsJPanel(dashboard);
        dashboard.jReportPanel.add(dashboard.reportsJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(dashboard.jReportPanel);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ourServicesViewTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ourServicesViewTable2MouseClicked


    }//GEN-LAST:event_ourServicesViewTable2MouseClicked
    public JasperPrint makeReport() {

        String headerImg;
        try {
            InputStream s = this.getClass().getResourceAsStream("/resources/reports/OurService.jasper");
            String img = new File(this.getClass().getResource("/resources/reports/dazzle_auto_tp.png").getFile()).getAbsolutePath();

            headerImg = img.replace("\\", "/");

            HashMap<String, Object> params = new HashMap<>();
            params.put("img", headerImg);

            params.put("employee", LoginModel.getFirstName() + " " + LoginModel.getLastName());

            JRTableModelDataSource dataSource = new JRTableModelDataSource(ourServicesViewTable2.getModel());

            JasperPrint report = JasperFillManager.fillReport(s, params, dataSource);

            return report;

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while makeReport() : " + e.getMessage());
        }
        return null;
    }
    private void viewReportbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportbActionPerformed

        try {
            JasperPrint report = makeReport();
            JasperViewer.viewReport(report, false);

            logger.info("Our Service Report Viewed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while viewReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_viewReportbActionPerformed

    private void printReportbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReportbActionPerformed
        try {
            JasperPrint report = makeReport();
            JasperPrintManager.printReport(report, false);

            logger.info("Our Service Report Printed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while printReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_printReportbActionPerformed

    private void serviceFindFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serviceFindFieldKeyReleased
        searchTable();
    }//GEN-LAST:event_serviceFindFieldKeyReleased

    private void jVehicleTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVehicleTypeComboBoxActionPerformed
        searchTable();
    }//GEN-LAST:event_jVehicleTypeComboBoxActionPerformed


    private void jServiceNameComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServiceNameComboBox1ActionPerformed

    }//GEN-LAST:event_jServiceNameComboBox1ActionPerformed

    private void jServiceNameComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jServiceNameComboBox1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jServiceNameComboBox1MouseClicked

    private void jServiceNameComboBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jServiceNameComboBox1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jServiceNameComboBox1PropertyChange

    private void jServiceNameComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jServiceNameComboBox1ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            filterAndSortByService();
        }
    }//GEN-LAST:event_jServiceNameComboBox1ItemStateChanged

    private void jServiceChargeComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jServiceChargeComboBox2ItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            filterAndSortByService2();
        }

    }//GEN-LAST:event_jServiceChargeComboBox2ItemStateChanged

    private void jServiceChargeComboBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jServiceChargeComboBox2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jServiceChargeComboBox2MouseClicked

    private void jServiceChargeComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jServiceChargeComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jServiceChargeComboBox2ActionPerformed

    private void jServiceChargeComboBox2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jServiceChargeComboBox2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jServiceChargeComboBox2PropertyChange
    private void loadVehicleTypes() {

        try {
            ResultSet resultSet = new VehicleTypeController().show();

            Vector vector = new Vector();

            vector.add("Vehicle Type");
            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                vehicleTypesHashMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(vector);
            jVehicleTypeComboBox.setModel(comboBoxModel);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loadVehicleTypes : " + e.getMessage());
        }

    }

    private void loadServiceNames() {
        try {
            ResultSet resultSet = new ServicesController().show();

            Vector<String> serviceNames = new Vector<>();

            serviceNames.add("Select a Service");

            while (resultSet.next()) {
                String serviceName = resultSet.getString("name");
                String serviceId = resultSet.getString("id");
                serviceNames.add(serviceName);

                serviceNameHashMap.put(serviceName, serviceId);
            }

            // Update the combo box with the new model
            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(serviceNames);
            jServiceNameComboBox1.setModel(comboBoxModel);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading service names: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Failed to load services. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadServiceCharge() {
        try {
            ResultSet resultSet = new ServicesController().show();

            Vector<String> serviceNames = new Vector<>();

            serviceNames.add("Select a Service Charge");

            while (resultSet.next()) {
                String serviceCharge = resultSet.getString("charge");
                String serviceId = resultSet.getString("id");
                serviceNames.add(serviceCharge);

                serviceChargeHashMap.put(serviceCharge, serviceId);
            }

            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(serviceNames);
            jServiceChargeComboBox2.setModel(comboBoxModel);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading service charge: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Failed to load services. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchTable() {
        String searchText = serviceFindField.getText();
        String vehicleTypeId = "";
        if (!jVehicleTypeComboBox.getSelectedItem().equals("Vehicle Type")) {
            vehicleTypeId = vehicleTypesHashMap.get(jVehicleTypeComboBox.getSelectedItem());
        }

        loadSearchedTable(searchText, vehicleTypeId);

    }

    private void loadSearchedTable(String searchText, String vehicleTypeId) {
        try {
            ResultSet resultSet;
            if (vehicleTypeId.equals("")) {
                resultSet = new ServicesController().search(searchText);
            } else {
                resultSet = new ServicesController().search(searchText, vehicleTypeId);
            }

            DefaultTableModel tableModel = (DefaultTableModel) ourServicesViewTable2.getModel();
            tableModel.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("name"));

                try {
                    ResultSet vehicleTypeResultSet = new VehicleTypeController().show(resultSet.getInt("vehicle_type_id"));
                    if (vehicleTypeResultSet.next()) {
                        vector.add(vehicleTypeResultSet.getString("name"));
                    } else {
                        vector.add("empty");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.severe("Error while searching services : " + e.getMessage());
                }

                vector.add(resultSet.getString("charge"));

                tableModel.addRow(vector);

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while showing vehicle types : " + e.getMessage());
        }
    }

    private void filterAndSortByService() {
        try {
            String selectedService = String.valueOf(jServiceNameComboBox1.getSelectedItem());
            String sortOption = String.valueOf(jServiceNameComboBox1.getSelectedItem());

            String query = "SELECT services.id, services.name, vehicle_type.name AS vehicle_type_name, services.charge "
                    + "FROM services "
                    + "INNER JOIN vehicle_type ON services.vehicle_type_id = vehicle_type.id";

            if (!selectedService.equals("All")) {
                query += " WHERE services.name = '" + selectedService + "'";
            }

            if (sortOption.contains("Service Name A-Z")) {
                query += " ORDER BY services.name ASC";
            } else if (sortOption.contains("Service Name Z-A")) {
                query += " ORDER BY services.name DESC";
            }

            ResultSet serviceResultSet = MySqlConnection.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) ourServicesViewTable2.getModel();
            model.setRowCount(0);

            int row = 0;
            while (serviceResultSet.next()) {
                row++;
                String id = serviceResultSet.getString("id");
                String name = serviceResultSet.getString("name");
                String vehicleTypeName = serviceResultSet.getString("vehicle_type_name"); // Fetch the vehicle type name
                String charge = serviceResultSet.getString("charge");

                model.addRow(new Object[]{id, name, vehicleTypeName, charge});
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while filtering and sorting services: " + e.getMessage());
        }
    }

    private void filterAndSortByService2() {
        try {
            String selectedService = String.valueOf(jServiceChargeComboBox2
                    .getSelectedItem());
            String sortOption = String.valueOf(jServiceChargeComboBox2.getSelectedItem());

            String query = "SELECT services.id, services.name, vehicle_type.name AS vehicle_type_name, services.charge "
                    + "FROM services "
                    + "INNER JOIN vehicle_type ON services.vehicle_type_id = vehicle_type.id";

            if (!selectedService.equals("All")) {
                query += " WHERE services.charge = '" + selectedService + "'";
            }

            query += " ORDER BY services.charge";

            ResultSet serviceResultSet = MySqlConnection.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) ourServicesViewTable2.getModel();
            model.setRowCount(0);

            int row = 0;
            while (serviceResultSet.next()) {
                row++;
                String id = serviceResultSet.getString("id");
                String name = serviceResultSet.getString("name");
                String vehicleTypeName = serviceResultSet.getString("vehicle_type_name");
                String charge = serviceResultSet.getString("charge");

                model.addRow(new Object[]{id, name, vehicleTypeName, charge});
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while filtering and sorting services: " + e.getMessage());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> jServiceChargeComboBox2;
    private javax.swing.JComboBox<String> jServiceNameComboBox1;
    private javax.swing.JComboBox<String> jVehicleTypeComboBox;
    private javax.swing.JTable ourServicesViewTable2;
    private javax.swing.JButton printReportb;
    private javax.swing.JTextField serviceFindField;
    private javax.swing.JButton viewReportb;
    // End of variables declaration//GEN-END:variables
}
