/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.supplier;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.mysql.cj.MysqlConnection;
import controllers.EmployeeController;
import controllers.EmployeeTypeController;
import controllers.StatusController;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import controllers.SupplierController;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import controllers.SupplierController;
import includes.LoggerConfig;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;
import models.EmployeeModel;
import models.SupplierModel;
import views.employee.EmployeeUpdate;

/**
 *
 * @author USER
 */
public class SupplierView extends javax.swing.JFrame {

    private static final Logger logger = LoggerConfig.getLogger();

    private Object search;

    /**
     * Creates new form SupplierView
     */
    public SupplierView() {
        initComponents();
        loadsupplier();

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

            DefaultTableModel model = (DefaultTableModel) supplier_view_tbl.getModel();
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
        DefaultTableModel model = (DefaultTableModel) supplier_view_tbl.getModel();
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

                System.out.println("Supplier Status ID: " + statusId);

                String statusName = statusMap.getOrDefault(statusId, "Unknown Status");

                System.out.println("Retrieved Status Name: " + statusName);

                model.addRow(new Object[]{id, fname, lname, email, mobile, statusName});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while fetching supplier : " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        supplier_search_bar = new javax.swing.JTextField();
        employee_search_btn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        supplier_view_tbl = new javax.swing.JTable();
        supplier_register_new_employee_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentShown(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("SUPPLIER");

        supplier_search_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplier_search_barActionPerformed(evt);
            }
        });
        supplier_search_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                supplier_search_barKeyReleased(evt);
            }
        });

        employee_search_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        employee_search_btn.setText("SEARCH");
        employee_search_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employee_search_btnMouseClicked(evt);
            }
        });

        supplier_view_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "supplier Id", "first name", "last name", "email", "mobile", "status Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        supplier_view_tbl.getTableHeader().setReorderingAllowed(false);
        supplier_view_tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supplier_view_tblMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(supplier_view_tbl);

        supplier_register_new_employee_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        supplier_register_new_employee_btn.setText("REGISTER NEW SUPPLIER");
        supplier_register_new_employee_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplier_register_new_employee_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(supplier_search_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(employee_search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 595, Short.MAX_VALUE)
                        .addComponent(supplier_register_new_employee_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplier_search_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employee_search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplier_register_new_employee_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void supplier_search_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplier_search_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplier_search_barActionPerformed

    private void supplier_register_new_employee_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplier_register_new_employee_btnActionPerformed

        new SupplierRegistration(null, true).show();
    }//GEN-LAST:event_supplier_register_new_employee_btnActionPerformed

    private void supplier_search_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_supplier_search_barKeyReleased

//

    }//GEN-LAST:event_supplier_search_barKeyReleased

    private void jPanel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentShown

        try {

            fetchUser(null);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error in jPanel1ComponentShown : " + ex.getMessage());
        }

    }//GEN-LAST:event_jPanel1ComponentShown

    private void employee_search_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employee_search_btnMouseClicked

        try {
            fetchUser(supplier_search_bar.getText().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error in employee_search_btnMouseClicked : " + ex.getMessage());
        }

    }//GEN-LAST:event_employee_search_btnMouseClicked

    private void supplier_view_tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplier_view_tblMouseClicked

        int row = supplier_view_tbl.getSelectedRow();

        if (evt.getClickCount() == 2 && row != -1) {

            String supplierId = String.valueOf(supplier_view_tbl.getValueAt(row, 0));
            String firstName = String.valueOf(supplier_view_tbl.getValueAt(row, 1));
            String lastName = String.valueOf(supplier_view_tbl.getValueAt(row, 2));
            String email = String.valueOf(supplier_view_tbl.getValueAt(row, 3));
            String mobile = String.valueOf(supplier_view_tbl.getValueAt(row, 4));

            SupplierModel supplierModel = new SupplierModel();
            supplierModel.setId(supplierId);
            supplierModel.setFirstName(firstName);
            supplierModel.setLastName(lastName);
            supplierModel.setEmail(email);
            supplierModel.setMobile(mobile);

            SupplierUpdate supplierUpdate = new SupplierUpdate(this, true, supplierModel);
            supplierUpdate.setVisible(true);

            loadsupplier();

        }


    }//GEN-LAST:event_supplier_view_tblMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatMacDarkLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SupplierView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton employee_search_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton supplier_register_new_employee_btn;
    private javax.swing.JTextField supplier_search_bar;
    private javax.swing.JTable supplier_view_tbl;
    // End of variables declaration//GEN-END:variables
}
