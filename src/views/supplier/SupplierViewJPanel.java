/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.supplier;

import views.shop.items.*;
import com.formdev.flatlaf.FlatClientProperties;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import models.SupplierModel;

/**
 *
 * @author Dinuka
 */
public class SupplierViewJPanel extends javax.swing.JPanel {

    private SupplierViewJPanel supplierViewJPanel;
    private static final Logger logger = LoggerConfig.getLogger();
    private static HashMap<Integer, String> StatusMap = new HashMap<>();

    /**
     * Creates new form shop_ItemsJPanel
     */
    public SupplierViewJPanel() {
        initComponents();
        loadsupplier();

        search_box.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter Supplier Name / Id / Mobile or Email ");

        this.supplierViewJPanel = this;
        SupplierTableRender();

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(search_box, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Supllier_Register_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(Supllier_Register_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_box)
                .addGap(12, 12, 12))
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
                false, false, false, false, false, true
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
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
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
        // TODO add your handling code here:
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
            //supplierModel.setStatusId(Integer.parseInt(StatusMap.get(status)));

            try {
                Frame supplierViewJPanel = null;
                SupplierUpdate supUpdate = new SupplierUpdate(supplierViewJPanel, true, supplierModel);
                supUpdate.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
            loadsupplier();
        }

    }//GEN-LAST:event_SupplierViewTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Supllier_Register_Button;
    private javax.swing.JTable SupplierViewTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search_box;
    // End of variables declaration//GEN-END:variables
}
