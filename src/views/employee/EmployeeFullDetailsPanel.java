/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.employee;

import com.formdev.flatlaf.FlatClientProperties;
import com.google.gson.Gson;
import controllers.EmployeeController;
import controllers.EmployeeImageController;
import controllers.EmployeeTypeController;
import controllers.StatusController;
import includes.BDUtility;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.SwingUtilities;
import views.dashboard.Dashboard;
import includes.LoggerConfig;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import models.EmployeeImageModel;
import models.LoginModel;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Dinuka
 */
public class EmployeeFullDetailsPanel extends javax.swing.JPanel {

    private static Logger logger = LoggerConfig.getLogger();

    Dashboard Dashboard = null;
    ByteArrayOutputStream out = null;
    String empId;

    String empImgPath = "src/resources/DashboardIcons/account-60.png";
    File tempFile;

    public EmployeeFullDetailsPanel(Dashboard dashboard, String empID) {
        initComponents();
        this.Dashboard = dashboard;
        this.empId = empID;

        viewReportb.putClientProperty(FlatClientProperties.STYLE, "arc:15");
        printReportb.putClientProperty(FlatClientProperties.STYLE, "arc:15");

        jLabel1.setText("  Employee Profile - " + empID);
        employeeIDLabel.setText(empID);
        setEmployeeDetails();
    }

    private void setEmployeeDetails() {
        try {

            ResultSet employeeResultSet = new EmployeeController().show(empId);
            if (employeeResultSet.next()) {
                ResultSet employeeTypeResultSet = new EmployeeTypeController().show(employeeResultSet.getInt("employee_type_id"));
                if (employeeTypeResultSet.next()) {
                    String employeeTypeName = employeeTypeResultSet.getString("type");
                    String employeeSalary = employeeTypeResultSet.getString("basic_salary");
                    empTypeLabel.setText(employeeTypeName);
                    employeeTypeInId.setText(employeeTypeName);

                    salaryLable.setText(employeeSalary);
                }

                ResultSet statusResultSet = new StatusController().show(employeeResultSet.getInt("status_id"));
                if (statusResultSet.next()) {
                    String statusName = statusResultSet.getString("status");
                    statusLabel.setText(statusName);
                }

                nicLabel.setText(employeeResultSet.getString("nic"));
                nicInId.setText(employeeResultSet.getString("nic"));

                nameLabel.setText(employeeResultSet.getString("first_name") + " " + employeeResultSet.getString("last_name"));
                nameInId.setText(employeeResultSet.getString("first_name") + " " + employeeResultSet.getString("last_name"));

                emailLabel.setText(employeeResultSet.getString("email"));

                mobileLabel.setText(employeeResultSet.getString("mobile"));
                mobileInId.setText(employeeResultSet.getString("mobile"));

                regDateLabel.setText(employeeResultSet.getString("registered_date"));

                loadAmployeeImage();
                loadQr(empId, employeeResultSet.getString("nic"), employeeResultSet.getString("mobile"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading Employee : " + e.getMessage());
        }
    }

    private boolean isEmployerHaveAnImage = false;
    private EmployeeImageModel employeeImageModel = new EmployeeImageModel();

    private void loadAmployeeImage() {
        try {
            ResultSet resultSet = new EmployeeImageController().show(employeeIDLabel.getText());

            // show image
            if (resultSet.next()) {
                isEmployerHaveAnImage = true;

                employeeImageModel.setPath(resultSet.getString("path"));
                employeeImageModel.setId(resultSet.getInt("id"));
                employeeImageModel.setEmployeeId(resultSet.getString("employee_id"));

                // Image setting
                empImgPath = BDUtility.getPath("resources/employeeImages/" + resultSet.getString("path"));
                File imageFile = new File(empImgPath);

                if (imageFile.exists()) {
                    // Initialize ImageIcon with the image path
                    ImageIcon icon = new ImageIcon(empImgPath);
                    // Get the image and scale it
                    Image image = icon.getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH);
                    // Create the resized icon
                    ImageIcon resizedIcon = new ImageIcon(image);
                    // Set it to the label
                    employeeImgLabel.setIcon(resizedIcon);

                } else {

                }
                // Image setting
            } else {
                isEmployerHaveAnImage = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while loading employee image : " + e.getMessage());
        }

    }

    private void loadQr(String id, String nic, String mobile) {

        Map<String, String> data = new HashMap<>();
        data.put("id", id);
        data.put("nic", nic);
        data.put("mobile", mobile);

        Gson gson = new Gson();
        String jsonData = gson.toJson(data);

        out = QRCode.from(jsonData).withSize(140, 140).to(ImageType.PNG).stream();

        try {
            // Save the image to a temporary file
            tempFile = File.createTempFile("qrcode", ".png");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(out.toByteArray());
            }

            byte[] imageData = out.toByteArray();
            ImageIcon icon = new ImageIcon(imageData);
            qrLabel.setIcon(icon);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.severe("Error while loading loadQr : " + ex.getMessage());
        }
    }

    public JasperPrint makeReport() {

        String dateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa").format(new Date());

        String headerImg;
        String idFront;
        String idBack;
        String empImg;
        String qr;
        try {
            InputStream s = this.getClass().getResourceAsStream("/resources/reports/Employee_Profile.jasper");
            String img = new File(this.getClass().getResource("/resources/reports/dazzle_auto_tp.png").getFile()).getAbsolutePath();
            String front = new File(this.getClass().getResource("/resources/reports/FRONT-01.png").getFile()).getAbsolutePath();
            String back = new File(this.getClass().getResource("/resources/reports/FRONT-02.png").getFile()).getAbsolutePath();
            String qrPath = tempFile.getAbsolutePath();

            headerImg = img.replace("\\", "/");
            idFront = front.replace("\\", "/");
            idBack = back.replace("\\", "/");
            empImg = empImgPath.replace("\\", "/");
            qr = qrPath.replace("\\", "/");

            HashMap<String, Object> params = new HashMap<>();
            params.put("headerImg", headerImg);
            params.put("idFront", idFront);
            params.put("IdBack", idBack);
            params.put("empImg", empImg);
            params.put("qr", qr);
            
            params.put("employeeID", empId);
            params.put("issuedBy", LoginModel.getFirstName()+" "+LoginModel.getLastName());
            params.put("nic", nicLabel.getText());
            params.put("fullName", nameLabel.getText());
            params.put("email", emailLabel.getText());
            params.put("mobile", mobileLabel.getText());
            params.put("regDate", regDateLabel.getText());
            params.put("empType", empTypeLabel.getText());
            params.put("curruntStatus", statusLabel.getText());
            params.put("salary", salaryLable.getText());
            params.put("date", dateTime);

            JREmptyDataSource emptyDataSource = new JREmptyDataSource();

            JasperPrint report = JasperFillManager.fillReport(s, params, emptyDataSource);

            return report;

        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while makeReport() : " + e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        employeeIDLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        nicLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        mobileLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        regDateLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        empTypeLabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        salaryLable = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        employeeImgLabel = new javax.swing.JLabel();
        nicInId = new javax.swing.JLabel();
        nameInId = new javax.swing.JLabel();
        mobileInId = new javax.swing.JLabel();
        employeeTypeInId = new javax.swing.JLabel();
        qrLabel = new javax.swing.JLabel();
        idFront = new javax.swing.JLabel();
        idBack = new javax.swing.JLabel();
        viewReportb = new javax.swing.JButton();
        printReportb = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1100, 610));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel3.setBackground(new java.awt.Color(241, 245, 255));

        jButton1.setBackground(new java.awt.Color(241, 245, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/back-35.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/details-35.png"))); // NOI18N
        jLabel1.setText("  Employee Profile");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Employee ID :");

        employeeIDLabel.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        employeeIDLabel.setForeground(new java.awt.Color(0, 102, 0));
        employeeIDLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employeeIDLabel.setText("EMP00");

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel9.setText("NIC :");

        nicLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        nicLabel.setText(".............................................");

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel8.setText("Full Name:");

        nameLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        nameLabel.setText(".............................................");

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel10.setText("Email :");

        emailLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        emailLabel.setText(".............................................");

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel11.setText("Mobile  :");

        mobileLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        mobileLabel.setText(".............................................");

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel12.setText("Registered Date  :");

        regDateLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        regDateLabel.setText(".............................................");

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel13.setText("Employee Type :");

        empTypeLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        empTypeLabel.setText(".............................................");

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel14.setText("Currunt Status :");

        statusLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        statusLabel.setText(".............................................");

        jLabel15.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel15.setText("Basic Salary :");

        salaryLable.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        salaryLable.setText(".............................................");

        jPanel1.setBackground(new java.awt.Color(241, 245, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        employeeImgLabel.setBackground(new java.awt.Color(255, 255, 255));
        employeeImgLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        employeeImgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DashboardIcons/account-60.png"))); // NOI18N
        employeeImgLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 142, 0), 1, true));
        employeeImgLabel.setOpaque(true);
        jPanel1.add(employeeImgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 120, 140));

        nicInId.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        nicInId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nicInId.setText("nicInId");
        jPanel1.add(nicInId, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 210, -1));

        nameInId.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        nameInId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameInId.setText("nameInId");
        jPanel1.add(nameInId, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 210, -1));

        mobileInId.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        mobileInId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mobileInId.setText("mobileInId");
        jPanel1.add(mobileInId, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 210, -1));

        employeeTypeInId.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        employeeTypeInId.setForeground(new java.awt.Color(0, 69, 0));
        employeeTypeInId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        employeeTypeInId.setText("employeeTypeInId");
        jPanel1.add(employeeTypeInId, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 210, -1));

        qrLabel.setBackground(new java.awt.Color(255, 255, 255));
        qrLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 142, 0), 1, true));
        qrLabel.setOpaque(true);
        jPanel1.add(qrLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, 140, 140));

        idFront.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idFront.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/empIdCard/empid-front.png"))); // NOI18N
        idFront.setOpaque(true);
        jPanel1.add(idFront, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 370, 240));

        idBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/empIdCard/empid-back.png"))); // NOI18N
        idBack.setOpaque(true);
        jPanel1.add(idBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 370, 240));

        viewReportb.setBackground(new java.awt.Color(51, 51, 51));
        viewReportb.setFont(new java.awt.Font("Courier New", 1, 20)); // NOI18N
        viewReportb.setForeground(new java.awt.Color(255, 255, 255));
        viewReportb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btn_icons/analyze-30.png"))); // NOI18N
        viewReportb.setText(" Save Profile");
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
        printReportb.setText(" Print Profile");
        printReportb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printReportb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReportbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(salaryLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(regDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(mobileLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(nicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(empTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(employeeIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(viewReportb, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(printReportb, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(employeeIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mobileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(regDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(empTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salaryLable, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(printReportb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewReportb, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Dashboard.jStaffPanel.remove(this);
        SwingUtilities.updateComponentTreeUI(Dashboard.jStaffPanel);

        Dashboard.staffJPanel = new StaffJPanel(Dashboard);
        Dashboard.jStaffPanel.add(Dashboard.staffJPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(Dashboard.jStaffPanel);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void viewReportbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportbActionPerformed

        try {
            JasperPrint report = makeReport();
            JasperViewer.viewReport(report, false);

            logger.info("Employee : " + empId + ", Profile Viewed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while viewReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_viewReportbActionPerformed

    private void printReportbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReportbActionPerformed
        try {
            JasperPrint report = makeReport();
            JasperPrintManager.printReport(report, false);

            logger.info("Employee : " + empId + ", Profile Printed By : " + LoginModel.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("Error while printReportbActionPerformed : " + e.getMessage());
        }
    }//GEN-LAST:event_printReportbActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel empTypeLabel;
    private javax.swing.JLabel employeeIDLabel;
    private javax.swing.JLabel employeeImgLabel;
    private javax.swing.JLabel employeeTypeInId;
    private javax.swing.JLabel idBack;
    private javax.swing.JLabel idFront;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel mobileInId;
    private javax.swing.JLabel mobileLabel;
    private javax.swing.JLabel nameInId;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nicInId;
    private javax.swing.JLabel nicLabel;
    private javax.swing.JButton printReportb;
    private javax.swing.JLabel qrLabel;
    private javax.swing.JLabel regDateLabel;
    private javax.swing.JLabel salaryLable;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JButton viewReportb;
    // End of variables declaration//GEN-END:variables

}
