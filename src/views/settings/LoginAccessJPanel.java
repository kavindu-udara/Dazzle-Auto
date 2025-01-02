/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.settings;

import com.formdev.flatlaf.FlatClientProperties;
import controllers.EmployeeController;
import controllers.EmployeeTypeController;
import controllers.LoginController;
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
import raven.toast.Notifications;
import includes.LoggerConfig;
import includes.MySqlConnection;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.EmployeeModel;
import models.LoginModel;
import views.components.loginAccessTableRender.DeleteActionCellEditor;
import views.components.loginAccessTableRender.DeleteActionEvent;
import views.components.loginAccessTableRender.DeleteCellRender;

/**
 *
 * @author Dinuka
 */
public class LoginAccessJPanel extends javax.swing.JPanel {
    Settings settings;
    private static Logger logger = LoggerConfig.getLogger();

    public LoginAccessJPanel(Settings settings) {
        initComponents();
        this.settings = settings;

        jNewAccessButton.putClientProperty(FlatClientProperties.STYLE, "arc:10");

        loginAccessTableTableRender();
        loadLoginUsers();
    }

    private void loginAccessTableTableRender() {

        DeleteActionEvent event = new DeleteActionEvent() {

            @Override
            public void onView(int row) {

                String loginID = String.valueOf(jLoginAccessTable.getValueAt(row, 3));

                try {

                    int showConfirm = JOptionPane.showConfirmDialog(settings, "Do You Want To Delete This Login Access ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (showConfirm == JOptionPane.YES_OPTION) {
                        new LoginController().delete(loginID);
                        
                        JOptionPane.showMessageDialog(settings, "LoginID : " + loginID +", Login Access Successfully Deleted", "Information", JOptionPane.INFORMATION_MESSAGE);
                        
                        loadLoginUsers();

                        logger.info("LoginID : " + loginID + ", Login Access Deleted");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    logger.warning("Error while loginAccessTableTableRender() : " + e.getMessage());
                }

            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader tableHeader = jLoginAccessTable.getTableHeader();

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
        jLoginAccessTable.getColumnModel().getColumn(5).setCellRenderer(new DeleteCellRender());
        jLoginAccessTable.getColumnModel().getColumn(5).setCellEditor(new DeleteActionCellEditor(event));

        for (int i = 0; i < 5; i++) {
            jLoginAccessTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void loadLoginUsers() {

        try {

            ResultSet resultSet = new LoginController().show();

            DefaultTableModel dtm = (DefaultTableModel) jLoginAccessTable.getModel();
            dtm.setRowCount(0);

            int row = 0;
            while (resultSet.next()) {
                row++;
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("employee.id"));
                vector.add(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
                vector.add(resultSet.getString("role"));
                vector.add(resultSet.getString("login.id"));
                vector.add(resultSet.getString("password"));

                dtm.addRow(vector);
            }

            jLoginAccessTable.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loadLoginUsers() : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLoginAccessTable = new javax.swing.JTable();
        jNewAccessButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(853, 575));
        setPreferredSize(new java.awt.Dimension(853, 575));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(853, 575));

        jLoginAccessTable.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLoginAccessTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "Employee Name", "Access Role", "Login ID", "Password", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jLoginAccessTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLoginAccessTable.setFocusable(false);
        jLoginAccessTable.setRowHeight(40);
        jLoginAccessTable.getTableHeader().setReorderingAllowed(false);
        jLoginAccessTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLoginAccessTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jLoginAccessTable);
        if (jLoginAccessTable.getColumnModel().getColumnCount() > 0) {
            jLoginAccessTable.getColumnModel().getColumn(5).setPreferredWidth(150);
            jLoginAccessTable.getColumnModel().getColumn(5).setMaxWidth(70);
        }

        jNewAccessButton.setBackground(new java.awt.Color(204, 0, 0));
        jNewAccessButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jNewAccessButton.setForeground(new java.awt.Color(255, 255, 255));
        jNewAccessButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/icons8-access-30.png"))); // NOI18N
        jNewAccessButton.setText("  Add New Access");
        jNewAccessButton.setBorderPainted(false);
        jNewAccessButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jNewAccessButton.setFocusPainted(false);
        jNewAccessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNewAccessButtonActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(5, 15, 76));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Print Report");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNewAccessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jNewAccessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
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

    private void jNewAccessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNewAccessButtonActionPerformed
        settings.dispose();
        new AddAndUpdateAccessJDialog(null, true, "ADD NEW ACCESS").setVisible(true);
    }//GEN-LAST:event_jNewAccessButtonActionPerformed

    private void jLoginAccessTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLoginAccessTableMouseClicked
        int row = jLoginAccessTable.getSelectedRow();
        String empID = String.valueOf(jLoginAccessTable.getValueAt(row, 0));
        String accessRole = String.valueOf(jLoginAccessTable.getValueAt(row, 2));
        String loginID = String.valueOf(jLoginAccessTable.getValueAt(row, 3));
        String password = String.valueOf(jLoginAccessTable.getValueAt(row, 4));

        String nic = "";
        String first_name = "";
        String last_name = "";
        String employeeType = "";

        if (evt.getClickCount() == 2) {
            try {
                ResultSet rs = new EmployeeController().show(empID);

                if (rs.next()) {
                    nic = rs.getString("nic");
                    first_name = rs.getString("first_name");
                    last_name = rs.getString("last_name");
                    int emp_type_id = rs.getInt("employee_type_id");

                    ResultSet type_rs = new EmployeeTypeController().show(emp_type_id);
                    if (type_rs.next()) {
                        employeeType = type_rs.getString("type");
                    }
                }

                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setId(empID);
                employeeModel.setNic(nic);
                employeeModel.setFirstName(first_name);
                employeeModel.setLastName(last_name);
                employeeModel.setEmployeeTypeName(employeeType);

                LoginModel loginModel = new LoginModel();
                loginModel.setEmployeeId(accessRole); //accessRole
                loginModel.setId(loginID);
                loginModel.setPassword(password);

                settings.dispose();
                new AddAndUpdateAccessJDialog(null, true, "UPDATE USER ACCESS", employeeModel, loginModel).setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
                logger.severe("Error while jLoginAccessTableMouseClicked : " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jLoginAccessTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JTable jLoginAccessTable;
    private javax.swing.JButton jNewAccessButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
