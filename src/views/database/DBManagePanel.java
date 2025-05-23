/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.database;

import includes.LoggerConfig;
import includes.MySqlConnection;
import includes.TimestampsGenerator;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author E
 */
public class DBManagePanel extends javax.swing.JPanel {
    
    private static final Logger logger = LoggerConfig.getLogger();

    /**
     * Creates new form DBManagePanel
     */
    public DBManagePanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outPathField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        restoreDbSqlFilePathField = new javax.swing.JTextField();
        sqlSelectButton = new javax.swing.JButton();
        restoreButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(853, 575));

        outPathField.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N

        jButton1.setBackground(new java.awt.Color(68, 68, 73));
        jButton1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Select Path");
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 153));
        jButton2.setText("Backup Database");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 153), 1, true));
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(242, 242, 255));
        jLabel2.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Backup Database");
        jLabel2.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("Sql File Path");

        sqlSelectButton.setBackground(new java.awt.Color(68, 68, 73));
        sqlSelectButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        sqlSelectButton.setForeground(new java.awt.Color(255, 255, 255));
        sqlSelectButton.setText("Select SQL File");
        sqlSelectButton.setBorderPainted(false);
        sqlSelectButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sqlSelectButton.setFocusPainted(false);
        sqlSelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sqlSelectButtonActionPerformed(evt);
            }
        });

        restoreButton.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        restoreButton.setForeground(new java.awt.Color(255, 102, 0));
        restoreButton.setText("Restore Database");
        restoreButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        restoreButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        restoreButton.setFocusPainted(false);
        restoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreButtonActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setBackground(new java.awt.Color(255, 243, 232));
        jLabel4.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Restore Database");
        jLabel4.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(restoreDbSqlFilePathField, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sqlSelectButton, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                            .addComponent(restoreButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(outPathField, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(71, 71, 71))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(outPathField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sqlSelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(restoreDbSqlFilePathField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(restoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String date = TimestampsGenerator.getTodayDate();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("SQL", "sql"));
        fileChooser.setSelectedFile(new java.io.File("DB_backup" + date + ".sql"));
        int result = fileChooser.showSaveDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getPath();
            outPathField.setText(path);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        if (outPathField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Output Path is Required !");
        } else {
            try {

                // Read serialized database connection information
                FileInputStream fileInputStream = new FileInputStream("dbinfo.ser");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                
                MySqlConnection mySqlConnection = (MySqlConnection) objectInputStream.readObject();
                objectInputStream.close();

                // Define backup file path
                String path = outPathField.getText();

                // Create ProcessBuilder with correct mysqldump arguments
                ProcessBuilder processBuilder = new ProcessBuilder(
                        "mysqldump",
                        "-u" + mySqlConnection.USERNAME,
                        "-p" + mySqlConnection.PASSWORD,
                        "-P" + mySqlConnection.PORT,
                        "--add-drop-database",
                        "-B", mySqlConnection.DBNAME,
                        "-r", path
                );

                // Start the process
                Process process = processBuilder.start();

                // Wait for the process to complete
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    JOptionPane.showMessageDialog(null, "Backup successful: " + path);
                    logger.info("Backup Created : " + path);
                } else {
                    JOptionPane.showMessageDialog(null, "Backup Failed with exit code: " + exitCode);
                    logger.severe("Backup Failed with exit code : " + exitCode);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void sqlSelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sqlSelectButtonActionPerformed
        // TODO add your handling code here:

        String date = TimestampsGenerator.getTodayDate();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("SQL", "sql"));
        fileChooser.setSelectedFile(new java.io.File("DB_backup" + date + ".sql"));
        int result = fileChooser.showSaveDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getPath();
            restoreDbSqlFilePathField.setText(path);
        }

    }//GEN-LAST:event_sqlSelectButtonActionPerformed

    private void restoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreButtonActionPerformed
        // TODO add your handling code here:

        if (restoreDbSqlFilePathField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "SQL file is required!");
        } else {
            try {
                // Read serialized database connection information
                FileInputStream fileInputStream = new FileInputStream("dbinfo.ser");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                
                MySqlConnection mySqlConnection = (MySqlConnection) objectInputStream.readObject();
                objectInputStream.close();

                // SQL file path
                String sqlFilePath = restoreDbSqlFilePathField.getText();

                // Create ProcessBuilder with mysql command
                ProcessBuilder processBuilder = new ProcessBuilder(
                        "mysql",
                        "-u" + mySqlConnection.USERNAME,
                        "-p" + mySqlConnection.PASSWORD,
                        "-P" + mySqlConnection.PORT,
                        mySqlConnection.DBNAME
                );

                // Start the process
                Process process = processBuilder.start();

                // Write SQL file content to process input
                try (BufferedReader reader = new BufferedReader(new FileReader(sqlFilePath)); OutputStreamWriter writer = new OutputStreamWriter(process.getOutputStream())) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.write(System.lineSeparator());
                    }
                    writer.flush();
                }

                // Wait for the process to complete
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    JOptionPane.showMessageDialog(null, "Restore successful: " + sqlFilePath);
                    logger.info("Database Restore Success: " + sqlFilePath);
                } else {
                    // Log error output
                    try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                        StringBuilder errorOutput = new StringBuilder();
                        String errorLine;
                        while ((errorLine = errorReader.readLine()) != null) {
                            errorOutput.append(errorLine).append(System.lineSeparator());
                        }
                        JOptionPane.showMessageDialog(null, "Restore failed:\n" + errorOutput);
                        logger.severe("Backup Restore Failed: " + errorOutput);
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
                logger.severe("While DB restore process : " + e.getMessage());
            }
        }
    }//GEN-LAST:event_restoreButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField outPathField;
    private javax.swing.JButton restoreButton;
    private javax.swing.JTextField restoreDbSqlFilePathField;
    private javax.swing.JButton sqlSelectButton;
    // End of variables declaration//GEN-END:variables
}
