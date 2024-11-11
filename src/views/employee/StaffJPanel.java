/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.employee;

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
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import controllers.EmployeeController;
import controllers.EmployeeImageController;
import controllers.EmployeeTypeController;
import controllers.StatusController;
import includes.LoggerConfig;
import includes.MySqlConnection;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import models.EmployeeModel;
import raven.toast.Notifications;
import views.dashboard.Dashboard;
import views.settings.AddAndUpdateAccessJDialog;
import views.shop.payments.ShopInvoiceItemsPanel;

/**
 *
 * @author Dinuka
 */
public class StaffJPanel extends javax.swing.JPanel {

    private static final Logger logger = LoggerConfig.getLogger();

    private StaffJPanel staffJPanel;

    EmployeeSelector employeeSelectorFrame = null;
    AddAndUpdateAccessJDialog addAndUpdateAccessJDialog = null;

    String From = "";
    String BaseFrame = "";

    Dashboard Dashboard = null;

    public StaffJPanel(Dashboard dashboard) {
        initComponents();
        this.Dashboard = dashboard;
        loadEmployees();
        employeeViewTable.clearSelection();

        employeeFindField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Name/NIC");

        employeeViewTableRender();

        this.staffJPanel = this;
        Notifications.getInstance().setJFrame(dashboard);
    }

    //Constructer for EmployeeSelecter
    public StaffJPanel(Dialog parentFrame, EmployeeSelector employeeSelector, String BaseFrame) {
        initComponents();
        loadEmployees();

        this.employeeSelectorFrame = employeeSelector;
        this.From = "Selecter";
        this.BaseFrame = BaseFrame;

        if (BaseFrame.equals("AddAndUpdateAccessJDialog")) {
            this.addAndUpdateAccessJDialog = (AddAndUpdateAccessJDialog) parentFrame;
        }

        jRegNewEmployeeButton.setEnabled(false);

        employeeFindField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Name/NIC");

        employeeViewTableRender();

        this.staffJPanel = this;
    }

    private void sortEmployees() {
        try {
            String query = "SELECT * FROM employee";

            String searchText = employeeFindField.getText().trim();
            if (!searchText.isEmpty()) {
                query += " WHERE `first_name` LIKE '%" + searchText + "%' OR "
                        + "`last_name` LIKE '%" + searchText + "%' OR "
                        + "`email` LIKE '%" + searchText + "%' OR "
                        + "`mobile` LIKE '%" + searchText + "%'";
            }

            // Sorting logic
            String sort = String.valueOf(jSortComboBox.getSelectedItem());
            //System.out.println("Selected sort option: " + sort);

            // Append sorting conditions to the query
            if (sort.contains("First Name A-Z")) {
                query += " ORDER BY `first_name` ASC";
            } else if (sort.contains("First Name Z-A")) {
                query += " ORDER BY `first_name` DESC";
            } else if (sort.contains("Last Name A-Z")) {
                query += " ORDER BY `last_name` ASC";
            } else if (sort.contains("Last Name Z-A")) {
                query += " ORDER BY `last_name` DESC";
            } else if (sort.contains("Registered Date Oldest")) {
                query += " ORDER BY `registered_date` ASC";
            } else if (sort.contains("Registered Date Newest")) {
                query += " ORDER BY `registered_date` DESC";
            }

            ResultSet employeeResultSet = MySqlConnection.executeSearch(query);
            ResultSet resultSet2 = new StatusController().search("");
            ResultSet resultSet1 = new EmployeeTypeController().search("");

            DefaultTableModel model = (DefaultTableModel) employeeViewTable.getModel();
            model.setRowCount(0);

            HashMap<Integer, String> employeeTypeMap = new HashMap<>();
            HashMap<Integer, String> statusMap = new HashMap<>();

            while (resultSet1.next()) {
                int employeeTypeId = resultSet1.getInt("id");
                String employeeTypeName = resultSet1.getString("type");
                employeeTypeMap.put(employeeTypeId, employeeTypeName);
            }

            while (resultSet2.next()) {
                int statusId = resultSet2.getInt("id");
                String statusName = resultSet2.getString("status");
                statusMap.put(statusId, statusName);
            }

            while (employeeResultSet.next()) {
                String employeeId = employeeResultSet.getString("id");
                String nic = employeeResultSet.getString("nic");
                String firstName = employeeResultSet.getString("first_name");
                String lastName = employeeResultSet.getString("last_name");
                String email = employeeResultSet.getString("email");
                String mobile = employeeResultSet.getString("mobile");

                int employeeTypeId = employeeResultSet.getInt("employee_type_id");
                int statusId = employeeResultSet.getInt("status_id");

                String statusName = statusMap.getOrDefault(statusId, "Unknown Status");
                String employeeTypeName = employeeTypeMap.getOrDefault(employeeTypeId, "Unknown Type");

                model.addRow(new Object[]{employeeId, nic, firstName, lastName, email, mobile, employeeTypeName, statusName});
            }

            employeeViewTable.revalidate();
            employeeViewTable.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading employees: " + e.getMessage());
        }
    }

    private void loadEmployees() {
        try {

            ResultSet employeeResultSet = new EmployeeController().show();
            ResultSet employeeTypeResultSet = new EmployeeTypeController().show();
            ResultSet statusResultSet = new StatusController().show();
            ResultSet employeeImageResultSet = new EmployeeImageController().show();

            HashMap<Integer, String> employeeTypeMap = new HashMap<>();
            HashMap<Integer, String> statusMap = new HashMap<>();
            HashMap<String, String> imagePathMap = new HashMap<>();

            while (employeeTypeResultSet.next()) {
                int employeeTypeId = employeeTypeResultSet.getInt("id");
                String employeeTypeName = employeeTypeResultSet.getString("type");
                employeeTypeMap.put(employeeTypeId, employeeTypeName);
            }

            while (statusResultSet.next()) {
                int statusId = statusResultSet.getInt("id");
                String statusName = statusResultSet.getString("status");
                statusMap.put(statusId, statusName);
            }

            while (employeeImageResultSet.next()) {
                String employeeId = employeeImageResultSet.getString("employee_id");
                String imagePath = employeeImageResultSet.getString("path");
                imagePathMap.put(employeeId, imagePath);
            }

            DefaultTableModel model = (DefaultTableModel) employeeViewTable.getModel();
            model.setRowCount(0);

            while (employeeResultSet.next()) {
                Vector<String> vector = new Vector<>();

                String employeeId = employeeResultSet.getString("id");
                vector.add(employeeId);
                vector.add(employeeResultSet.getString("nic"));

                vector.add(employeeResultSet.getString("first_name"));
                vector.add(employeeResultSet.getString("last_name"));
                vector.add(employeeResultSet.getString("email"));
                vector.add(employeeResultSet.getString("mobile"));
                //vector.add(employeeResultSet.getString("registered_date"));

                int employeeTypeId = employeeResultSet.getInt("employee_type_id");
                int statusId = employeeResultSet.getInt("status_id");

                String employeeTypeName = employeeTypeMap.getOrDefault(employeeTypeId, "Unknown Employee Type");
                String statusName = statusMap.getOrDefault(statusId, "Unknown Status");

                vector.add(employeeTypeName);
                vector.add(statusName);
                String imagePath = imagePathMap.get(employeeId);
                if (imagePath == null || imagePath.trim().isEmpty()) {
                    imagePath = "No Image";
                }
                vector.add(imagePath);
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading Employee : " + e.getMessage());
        }

    }

    private void fetchUser(String searchText) throws Exception {
        DefaultTableModel model = (DefaultTableModel) employeeViewTable.getModel();
        model.setRowCount(0);

        try {
            ResultSet resultSet = new EmployeeController().search(searchText);
            ResultSet resultSet2 = new StatusController().search("");
            ResultSet resultSet1 = new EmployeeTypeController().search("");

            HashMap<Integer, String> employeeTypeMap = new HashMap<>();
            HashMap<Integer, String> statusMap = new HashMap<>();

            while (resultSet1.next()) {
                int employeeTypeId = resultSet1.getInt("id");
                String employeeTypeName = resultSet1.getString("type");
                employeeTypeMap.put(employeeTypeId, employeeTypeName);
            }

            while (resultSet2.next()) {
                int statusId = resultSet2.getInt("id");
                String statusName = resultSet2.getString("status");
                statusMap.put(statusId, statusName);
            }

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String mobile = resultSet.getString("mobile");
                String nic = resultSet.getString("nic");

                String registeredDate = resultSet.getString("registered_date");
                int employeeTypeId = resultSet.getInt("employee_type_id");

                int statusId = resultSet.getInt("status_id");

                String statusName = statusMap.getOrDefault(statusId, "Unknown Status");
                String employeeTypeName = employeeTypeMap.getOrDefault(employeeTypeId, "Unknown Type");

                model.addRow(new Object[]{id, nic, fname, lname, email, mobile, employeeTypeName, statusName});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while fetching Employee : " + ex.getMessage());
        }
    }

    public void employeeViewTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = employeeViewTable.getTableHeader();

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

        for (int i = 0; i < 8; i++) {
            employeeViewTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void reloadTable() {
        loadEmployees();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mainPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        employeeViewTable = new javax.swing.JTable();
        jRegNewEmployeeButton = new javax.swing.JButton();
        employeeFindField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSortComboBox = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();

        jMenuItem1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/details-35.png"))); // NOI18N
        jMenuItem1.setText(" View Profile");
        jMenuItem1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setMinimumSize(new java.awt.Dimension(1100, 610));
        setPreferredSize(new java.awt.Dimension(1100, 610));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        employeeViewTable.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        employeeViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "NIC", "First Name ", "Last Name ", "Email ", "Mobile", "Employee Type", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employeeViewTable.setToolTipText("Right Click Employee to view Profile");
        employeeViewTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        employeeViewTable.setFocusable(false);
        employeeViewTable.setRowHeight(30);
        employeeViewTable.getTableHeader().setReorderingAllowed(false);
        employeeViewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeViewTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                employeeViewTableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(employeeViewTable);

        jRegNewEmployeeButton.setBackground(new java.awt.Color(199, 232, 199));
        jRegNewEmployeeButton.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jRegNewEmployeeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/add-user-30.png"))); // NOI18N
        jRegNewEmployeeButton.setText(" REGISTER EMPLOYEE");
        jRegNewEmployeeButton.setBorderPainted(false);
        jRegNewEmployeeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRegNewEmployeeButton.setFocusPainted(false);
        jRegNewEmployeeButton.setFocusable(false);
        jRegNewEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRegNewEmployeeButtonActionPerformed(evt);
            }
        });

        employeeFindField.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        employeeFindField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeFindFieldActionPerformed(evt);
            }
        });
        employeeFindField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                employeeFindFieldKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel3.setText("Find Employee :");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel7.setText("Sort By :");

        jSortComboBox.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jSortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "First Name A-Z", "First Name Z-A", "Last Name A-Z", "Last Name Z-A", "Registered Date Oldest", "Registered Date Newest" }));
        jSortComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jSortComboBoxItemStateChanged(evt);
            }
        });
        jSortComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSortComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(employeeFindField, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                .addComponent(jRegNewEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jRegNewEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(employeeFindField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/employee-3.png"))); // NOI18N
        jLabel4.setText("  Staff");
        jLabel4.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jLabel4ComponentShown(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jRegNewEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRegNewEmployeeButtonActionPerformed

        // TODO: need to fix this
        new EmployeeRegistration(null, true, staffJPanel).setVisible(true);
    }//GEN-LAST:event_jRegNewEmployeeButtonActionPerformed

    private void employeeFindFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeFindFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeFindFieldActionPerformed

    private void employeeViewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeViewTableMouseClicked

        int row = employeeViewTable.getSelectedRow();

        if (row != -1) {
            String employeeId = String.valueOf(employeeViewTable.getValueAt(row, 0));
            String nic = String.valueOf(employeeViewTable.getValueAt(row, 1));
            String firstName = String.valueOf(employeeViewTable.getValueAt(row, 2));
            String lastName = String.valueOf(employeeViewTable.getValueAt(row, 3));
            String email = String.valueOf(employeeViewTable.getValueAt(row, 4));
            String mobile = String.valueOf(employeeViewTable.getValueAt(row, 5));
            String employeeType = String.valueOf(employeeViewTable.getValueAt(row, 6));
            String employeeStatus = String.valueOf(employeeViewTable.getValueAt(row, 7));

            if (From.equals("Selecter")) {

                if (BaseFrame.equals("AddAndUpdateAccessJDialog")) {
                    addAndUpdateAccessJDialog.setEmployeeDetails(employeeId, nic, firstName, lastName, employeeType);
                }

                employeeSelectorFrame.dispose();

            } else if (evt.getClickCount() == 2) {

                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setId(employeeId);
                employeeModel.setFirstName(firstName);
                employeeModel.setLastName(lastName);
                employeeModel.setEmail(email);
                employeeModel.setNic(nic);
                employeeModel.setMobile(mobile);
                employeeModel.setEmployeeTypeName(employeeType);

                try {
                    ResultSet employeeStatusResultSet = new StatusController().show(employeeStatus);
                    if (employeeStatusResultSet.next()) {
                        employeeModel.setStatusId(employeeStatusResultSet.getInt("id"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.severe("Error while finding Status : " + ex.getMessage());
                }

                try {
                    Frame staffJPanel = null;
                    EmployeeUpdate employeeUpdate = new EmployeeUpdate(staffJPanel, true, employeeModel, this.staffJPanel);
                    employeeUpdate.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.severe("Error while showing employee update dialog : " + e.getMessage());
                }

                loadEmployees();
            }

        }

    }//GEN-LAST:event_employeeViewTableMouseClicked

    private void jLabel4ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jLabel4ComponentShown

        try {
            fetchUser(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while jLabel4ComponentShown : " + ex.getMessage());
        }

    }//GEN-LAST:event_jLabel4ComponentShown

    private void employeeFindFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_employeeFindFieldKeyReleased

        try {
            fetchUser(employeeFindField.getText().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while employeeFindFieldKeyReleased : " + ex.getMessage());
        }

    }//GEN-LAST:event_employeeFindFieldKeyReleased

    private void jSortComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSortComboBoxActionPerformed
//
    }//GEN-LAST:event_jSortComboBoxActionPerformed

    private void jSortComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jSortComboBoxItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            sortEmployees();
        }

    }//GEN-LAST:event_jSortComboBoxItemStateChanged

    private void employeeViewTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeViewTableMouseReleased
        if (evt.getButton() == MouseEvent.BUTTON3) {
            int row = employeeViewTable.getSelectedRow();
            if (row != -1) {
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_employeeViewTableMouseReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        int row = employeeViewTable.getSelectedRow();

        if (row != -1) {
            String empID = String.valueOf(employeeViewTable.getValueAt(row, 0));

            Dashboard.jStaffPanel.remove(this);
            SwingUtilities.updateComponentTreeUI(Dashboard.jStaffPanel);

            EmployeeFullDetailsPanel employeeFullDetailsPanel = new EmployeeFullDetailsPanel(Dashboard, empID);
            Dashboard.jStaffPanel.add(employeeFullDetailsPanel, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(Dashboard.jStaffPanel);
        } else {
            Notifications.getInstance().show(
                    Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT,
                    "Select User For View Profile");
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField employeeFindField;
    private javax.swing.JTable employeeViewTable;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JButton jRegNewEmployeeButton;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> jSortComboBox;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
