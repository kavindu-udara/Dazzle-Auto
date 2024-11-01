/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Dialog.java to edit this template
 */
package views.financeAndHr;

import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.Webcam;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import controllers.AttendanceDateController;
import controllers.EmployeeAttendanceController;
import controllers.EmployeeController;
import includes.LoggerConfig;
import includes.TimestampsGenerator;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author USER
 */
public class MarkAttendance extends java.awt.Dialog implements Runnable, ThreadFactory {

    private static final Logger logger = LoggerConfig.getLogger();

    private WebcamPanel panel = null;
    private Webcam webcam = null;

    private ExecutorService executor = Executors.newSingleThreadExecutor(this);
    private volatile boolean running = true;

    private String action = "";

    private String todayDate = TimestampsGenerator.getTodayDate();

    private Runnable actionMethod;

    /**
     * Creates new form MarkAttendance
     *
     * @param action
     */
    public MarkAttendance(java.awt.Frame parent, boolean modal, String action, Runnable actionMethod) {
        super(parent, modal);
        this.action = action;
        this.actionMethod = actionMethod;
        initComponents();
        initwebcam();
        Timer timer = new Timer(1, e -> updateTime());
        timer.start();
    }

    private void updateTime() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
        lbltime2.setText(simpleDateFormat.format(new Date()));

    }

    Map<String, String> resultMap = new HashMap<String, String>();

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                logger.severe("Error while Thread Sleep : " + ex.getMessage());
            }
            try {
                Result result = null;
                BufferedImage image = null;
                if (webcam.isOpen()) {
                    if ((image = webcam.getImage()) == null) {
                        continue;
                    }
                }
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException ex) {
//                    logger.severe("NotFoundException : " + ex.getMessage());
                }
                if (result != null) {

                    JOptionPane.showMessageDialog(null, "User found : " + result.getText());

                    String jsonstring = result.getText();
                    Gson gson = new Gson();
                    java.lang.reflect.Type type = new TypeToken<Map<String, String>>() {
                    }.getType();

                    resultMap = gson.fromJson(jsonstring, type);

                    if (action != "") {
                        if (action == "checkin") {
                            checkInEmployer(resultMap);
                        } else if (action == "checkout") {
                            checkOutEmployer(resultMap);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } while (running);

    }

    private ResultSet getAttendanceDateResultSet() throws Exception {
        return new AttendanceDateController().show(todayDate);
    }

    private ResultSet gentEmployerResultSet() throws Exception {
        return new EmployeeController().showByIdAndNIC(resultMap.get("id"), resultMap.get("nic"));
    }

    private void checkInEmployer(Map<String, String> resultMap) {

        int dateId = 0;
        try {
            ResultSet dateResultSet = getAttendanceDateResultSet();
            if (dateResultSet.next()) {
                dateId = dateResultSet.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String currentTime = TimestampsGenerator.getCurrentTime();
        if (dateId != 0) {
            try {
                ResultSet employeeResultSet = gentEmployerResultSet();
                if (employeeResultSet.next()) {
                    try {
                        ResultSet checkInResultSet = new EmployeeAttendanceController().updateCheckInByUserId(currentTime, employeeResultSet.getString("id"), dateId);
                        JOptionPane.showMessageDialog(null, "mark attendance success");
                        actionMethod.run();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "employee not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void checkOutEmployer(Map<String, String> resultMap) {

        int dateId = 0;
        try {
            ResultSet dateResultSet = getAttendanceDateResultSet();
            if (dateResultSet.next()) {
                dateId = dateResultSet.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String currentTime = TimestampsGenerator.getCurrentTime();
        if (dateId != 0) {
            try {
                ResultSet employeeResultSet = gentEmployerResultSet();
                if (employeeResultSet.next()) {
                    try {
                        ResultSet checkInResultSet = new EmployeeAttendanceController().updateCheckOutByUserId(currentTime, employeeResultSet.getString("id"), dateId);
                        JOptionPane.showMessageDialog(null, "mark attendance success");
                        actionMethod.run();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "employee not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        webcampanel = new javax.swing.JPanel();
        lblcheckinchechout = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbltime2 = new javax.swing.JLabel();
        lblimage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        webcampanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        webcampanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblcheckinchechout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        webcampanel.add(lblcheckinchechout, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 6, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("DATE");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("TIME");

        lbltime2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbltime2.setText("TIME");

        lblimage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        lblimage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblimageMouseClicked(evt);
            }
        });

        jButton1.setText("close webcam");
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
                .addGap(36, 36, 36)
                .addComponent(webcampanel, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbltime2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(37, 37, 37))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblimage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))))
                .addGap(100, 100, 100))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(lbltime2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblimage, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(webcampanel, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void lblimageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblimageMouseClicked

    }//GEN-LAST:event_lblimageMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        stopwebcam();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblcheckinchechout;
    private javax.swing.JLabel lblimage;
    private javax.swing.JLabel lbltime2;
    private javax.swing.JPanel webcampanel;
    // End of variables declaration//GEN-END:variables

    private void initwebcam() {

        webcam = webcam.getDefault();

        if (webcam != null) {

            Dimension[] resolutions = webcam.getViewSizes();
            Dimension maxResolution = resolutions[resolutions.length - 1];

            if (webcam.isOpen()) {
                webcam.close();
            }

            webcam.setViewSize(maxResolution);
            webcam.open();

            panel = new WebcamPanel(webcam);
            panel.setPreferredSize(maxResolution);
            panel.setFPSDisplayed(true);

            webcampanel.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 689, 518));

            executor.execute(this);
            executor.shutdown();

        } else {
            JOptionPane.showMessageDialog(null, "Issue with webcam");
        }

    }

    private void stopwebcam() {
        if (webcam != null && webcam.isOpen()) {
            webcam.close();
        }
    }

    private BufferedImage image = null;

    private void showPopUpForCertainDuration(String popUpMesssage, String popUpHeader, Integer iconId) throws HeadlessException {

        final JOptionPane optionPane = new JOptionPane(popUpMesssage, iconId);
        final JDialog dialog = optionPane.createDialog(popUpHeader);
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                dialog.dispose();
                clearUserDetails();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    private void clearUserDetails() {

        lblcheckinchechout.setText("");
        lblcheckinchechout.setBackground(null);
        lblcheckinchechout.setForeground(null);
        lblcheckinchechout.setOpaque(false);
        lblimage.setIcon(null);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }
}
