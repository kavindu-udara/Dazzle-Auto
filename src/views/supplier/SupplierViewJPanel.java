/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.supplier;

import views.shop.items.*;
import com.formdev.flatlaf.FlatClientProperties;
import controllers.AddressController;
import controllers.StatusController;
import controllers.SupplierController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import includes.LoggerConfig;
import includes.MySqlConnection;
import java.awt.event.ItemEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import models.AddressModel;
import models.SupplierModel;

/**
 *
 * @author Dinuka
 */
public class SupplierViewJPanel extends javax.swing.JPanel {

    private SupplierViewJPanel supplierViewJPanel;
    private static final Logger logger = LoggerConfig.getLogger();
    private static HashMap<String, String> StatusMap = new HashMap<>();

    /**
     * Creates new form shop_ItemsJPanel
     */
    public SupplierViewJPanel() {
        initComponents();
        loadsupplier();
        loadStatusToSortBtn();

        search_box.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Supplier Name / Id / Mobile or Email ");

        this.supplierViewJPanel = this;
        SupplierTableRender();

    }

    private void loadStatusToSortBtn() {
        try {
            ResultSet resultSet = new StatusController().show();

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("status"));
                StatusMap.put(resultSet.getString("status"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            StatusSortBtn.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading employee types : " + e.getMessage());
        }
    }

    private void loadsupplier() {

        try {

            ResultSet resultSet = new SupplierController().show();
            ResultSet resultSet2 = new StatusController().show();

            HashMap<Integer, String> statusMap = new HashMap<>();

            while (resultSet2.next()) {
                int statusId = resultSet2.getInt("id");
                String statusName = resultSet2.getString("status");
                statusMap.put(statusId, statusName);
            }

            DefaultTableModel model = (DefaultTableModel) SupplierViewTable.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("first_name"));
                vector.add(resultSet.getString("last_name"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("mobile"));

                int statusId = resultSet.getInt("status_id");

                String statusName = statusMap.getOrDefault(statusId, "Unknown Status");

                vector.add(statusName);

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading supplier : " + e.getMessage());
        }

    }

    private void fetchUser(String searchText) throws Exception {
        DefaultTableModel model = (DefaultTableModel) SupplierViewTable.getModel();
        model.setRowCount(0);

        try {
            ResultSet resultSet = new SupplierController().search(searchText);
            ResultSet resultSet2 = new StatusController().search("");

            HashMap<Integer, String> statusMap = new HashMap<>();

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
                int statusId = resultSet.getInt("status_id");
                String statusName = statusMap.getOrDefault(statusId, "Unknown Status");
                model.addRow(new Object[]{id, fname, lname, email, mobile, statusName});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while fetching supplier : " + ex.getMessage());
        }
    }

    public void SupplierTableRender() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = SupplierViewTable.getTableHeader();

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

        for (int i = 0; i < 6; i++) {
            SupplierViewTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void reloadTable() {
        loadsupplier();
    }

    private static HashMap<String, String> BrandMap = new HashMap<>();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        search_box = new javax.swing.JTextField();
        Supllier_Register_Button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        StatusSortBtn = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SupplierViewTable = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1300, 539));
        setPreferredSize(new java.awt.Dimension(1300, 539));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel1.setText("Search Suppliers");

        search_box.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        search_box.setFocusCycleRoot(true);
        search_box.setSelectionColor(new java.awt.Color(214, 132, 13));
        search_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_boxKeyReleased(evt);
            }
        });

        Supllier_Register_Button.setBackground(new java.awt.Color(199, 232, 199));
        Supllier_Register_Button.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        Supllier_Register_Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/add-3.png"))); // NOI18N
        Supllier_Register_Button.setText(" REGISTER SUPPLIER");
        Supllier_Register_Button.setBorderPainted(false);
        Supllier_Register_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Supllier_Register_Button.setFocusPainted(false);
        Supllier_Register_Button.setFocusable(false);
        Supllier_Register_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Supllier_Register_ButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel2.setText("Status");

        StatusSortBtn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        StatusSortBtn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                StatusSortBtnItemStateChanged(evt);
            }
        });
        StatusSortBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusSortBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(search_box, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(StatusSortBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(386, 386, 386)
                .addComponent(Supllier_Register_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(StatusSortBtn))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(jLabel1)
                            .addGap(6, 6, 6)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(search_box, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(Supllier_Register_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );

        SupplierViewTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        SupplierViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier ID", "First Name", "Last Name", "Email", "Mobile", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SupplierViewTable.setRowHeight(30);
        SupplierViewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SupplierViewTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(SupplierViewTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Supllier_Register_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Supllier_Register_ButtonActionPerformed

        // TODO: need to fix this
        new SupplierRegistration(null, true, supplierViewJPanel).setVisible(true);
    }//GEN-LAST:event_Supllier_Register_ButtonActionPerformed

    private void search_boxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_boxKeyReleased
        // TODO add your handling code here:
        try {
            fetchUser(search_box.getText().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while search Suppliers : " + ex.getMessage());
        }
    }//GEN-LAST:event_search_boxKeyReleased

    private void SupplierViewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupplierViewTableMouseClicked

        int row = SupplierViewTable.getSelectedRow();

        if (evt.getClickCount() == 2 && row != -1) {
            String supplierId = String.valueOf(SupplierViewTable.getValueAt(row, 0));
            String firstName = String.valueOf(SupplierViewTable.getValueAt(row, 1));
            String lastName = String.valueOf(SupplierViewTable.getValueAt(row, 2));
            String email = String.valueOf(SupplierViewTable.getValueAt(row, 3));
            String mobile = String.valueOf(SupplierViewTable.getValueAt(row, 4));
            String status = String.valueOf(SupplierViewTable.getValueAt(row, 5));

            SupplierModel supplierModel = new SupplierModel();
            supplierModel.setId(supplierId);
            supplierModel.setFirstName(firstName);
            supplierModel.setLastName(lastName);
            supplierModel.setEmail(email);
            supplierModel.setMobile(mobile);
            supplierModel.setStatusName(status);

            AddressModel addressModel = new AddressModel();

            try {
                String addressId = new AddressController().retrieveAddressId(supplierId);

                if (addressId != null) {
                    String query = "SELECT * FROM address WHERE supplier_id = '" + addressId + "'";
                    ResultSet rs = MySqlConnection.executeSearch(query);

                    if (rs.next()) {
                        addressModel.setSupId(supplierId);
                        addressModel.setLane1(rs.getString("lane1"));
                        addressModel.setLane2(rs.getString("lane2"));
                        addressModel.setCity(rs.getString("city_id"));
                    }
                }

                SupplierUpdate supUpdate = new SupplierUpdate(null, true, supplierModel, addressModel);
                supUpdate.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
            loadsupplier();
        }
    }//GEN-LAST:event_SupplierViewTableMouseClicked

    private void StatusSortBtnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_StatusSortBtnItemStateChanged
        // TODO add your handling code here:


    }//GEN-LAST:event_StatusSortBtnItemStateChanged

    private void StatusSortBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusSortBtnActionPerformed
        // TODO add your handling code here:
        String selectedStatus = StatusSortBtn.getSelectedItem().toString();
        SortStatus(selectedStatus);

    }//GEN-LAST:event_StatusSortBtnActionPerformed

    private void SortStatus(String searchText) {
        try {
            DefaultTableModel model = (DefaultTableModel) SupplierViewTable.getModel();
            model.setRowCount(0);

            ResultSet resultSet;

            if (searchText.equals("Select")) {
                resultSet = new SupplierController().searchAll();
            } else {

                ResultSet statusResultSet = new StatusController().search(searchText);
                int statusId = -1;
                if (statusResultSet.next()) {
                    statusId = statusResultSet.getInt("id");
                }

                if (statusId != -1) {
                    resultSet = new SupplierController().getSuppliersByStatusId(statusId);
                } else {
                    model.setRowCount(0);
                    return;
                }
            }

            HashMap<Integer, String> statusMap = new HashMap<>();
            ResultSet allStatuses = new StatusController().search("");
            while (allStatuses.next()) {
                int id = allStatuses.getInt("id");
                String name = allStatuses.getString("status");
                statusMap.put(id, name);
            }

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String mobile = resultSet.getString("mobile");
                int statusId = resultSet.getInt("status_id");

                String statusName = statusMap.getOrDefault(statusId, "Unknown Status");

                model.addRow(new Object[]{id, fname, lname, email, mobile, statusName});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> StatusSortBtn;
    private javax.swing.JButton Supllier_Register_Button;
    private javax.swing.JTable SupplierViewTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search_box;
    // End of variables declaration//GEN-END:variables
}
