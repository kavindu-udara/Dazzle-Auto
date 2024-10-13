/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.employee;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import controllers.EmployeeController;
import controllers.EmployeeImageController;
import controllers.EmployeeTypeController;
import controllers.StatusController;
import java.util.HashMap;
import models.EmployeeModel;

/**
 *
 * @author USER
 */
public class EmployeeView extends javax.swing.JFrame {

    /**
     * Creates new form Employee_View
     */
    public EmployeeView() {
        initComponents();
        loadEmployees();
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

            DefaultTableModel model = (DefaultTableModel) employee_view_tbl.getModel();
            model.setRowCount(0);

            while (employeeResultSet.next()) {
                Vector<String> vector = new Vector<>();

                String employeeId = employeeResultSet.getString("id");
                vector.add(employeeId);
                vector.add(employeeResultSet.getString("first_name"));
                vector.add(employeeResultSet.getString("last_name"));
                vector.add(employeeResultSet.getString("email"));
                vector.add(employeeResultSet.getString("mobile"));
                vector.add(employeeResultSet.getString("nic"));
                vector.add(employeeResultSet.getString("registered_date"));

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
        }

    }

    private void fetchUser(String searchText) throws Exception {
        DefaultTableModel model = (DefaultTableModel) employee_view_tbl.getModel();
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

                model.addRow(new Object[]{id, fname, lname, email, mobile, nic, registeredDate, employeeTypeName, statusName});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        employee_search_bar = new javax.swing.JTextField();
        employee_search_btn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        employee_view_tbl = new javax.swing.JTable();
        employee_register_new_employee_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentShown(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("EMPLOYEE");

        employee_search_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_search_barActionPerformed(evt);
            }
        });

        employee_search_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        employee_search_btn.setText("SEARCH");
        employee_search_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employee_search_btnMouseClicked(evt);
            }
        });

        employee_view_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "employee Id", "first name", "last name", "email", "mobile", "nic", "registered date", "employee type", "status", "image name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employee_view_tbl.getTableHeader().setReorderingAllowed(false);
        employee_view_tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employee_view_tblMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(employee_view_tbl);

        employee_register_new_employee_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        employee_register_new_employee_btn.setText("REGISTER NEW EMPLOYEE");
        employee_register_new_employee_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employee_register_new_employee_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(employee_search_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(employee_search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(employee_register_new_employee_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employee_search_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employee_search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employee_register_new_employee_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void employee_search_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_search_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employee_search_barActionPerformed

    private void employee_register_new_employee_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employee_register_new_employee_btnActionPerformed

        new EmployeeRegistration(null, true).show();

    }//GEN-LAST:event_employee_register_new_employee_btnActionPerformed


    private void employee_view_tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employee_view_tblMouseClicked

        int row = employee_view_tbl.getSelectedRow();

        if (evt.getClickCount() == 2 && row != -1) {

            String employeeId = String.valueOf(employee_view_tbl.getValueAt(row, 0));
            String firstName = String.valueOf(employee_view_tbl.getValueAt(row, 1));
            String lastName = String.valueOf(employee_view_tbl.getValueAt(row, 2));
            String email = String.valueOf(employee_view_tbl.getValueAt(row, 3));
            String nic = String.valueOf(employee_view_tbl.getValueAt(row, 5));
            String mobile = String.valueOf(employee_view_tbl.getValueAt(row, 4)); // Ensure this is correct
            String employeeType = String.valueOf(employee_view_tbl.getValueAt(row, 7)); // Ensure this is correct
            
            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setId(employeeId);
            employeeModel.setFirstName(firstName);
            employeeModel.setLastName(lastName);
            employeeModel.setEmail(email);
            employeeModel.setNic(nic);
            employeeModel.setMobile(mobile);
            employeeModel.setEmployeeTypeName(employeeType);

            EmployeeUpdate employeeUpdate = new EmployeeUpdate(this, true, employeeModel);
            employeeUpdate.setVisible(true);

            loadEmployees();
        }

    }//GEN-LAST:event_employee_view_tblMouseClicked

    private void jPanel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentShown

        try {
            fetchUser(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_jPanel1ComponentShown

    private void employee_search_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employee_search_btnMouseClicked

        try {
            fetchUser(employee_search_bar.getText().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_employee_search_btnMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        FlatMacDarkLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton employee_register_new_employee_btn;
    private javax.swing.JTextField employee_search_bar;
    private javax.swing.JButton employee_search_btn;
    private javax.swing.JTable employee_view_tbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
