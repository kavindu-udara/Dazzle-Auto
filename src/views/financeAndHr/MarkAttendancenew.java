/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.financeAndHr;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import java.awt.Color;
//import com.mysql.cj.xdevapi.Result;
import java.sql.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.RenderingHints;
import java.util.Date;
import java.text.SimpleDateFormat;
//import java.util.Timer;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

//import java.util.concurrent.Executor;
//import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import includes.MySqlConnection;
import includes.BDUtility;

public class MarkAttendancenew extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private WebcamPanel panel = null;
    private Webcam webcam = null;

    private ExecutorService executor = Executors.newSingleThreadExecutor(this);
    private volatile boolean running = true;

    public MarkAttendancenew() {
        initComponents();
        initwebcam();
        Timer timer = new Timer(1, e -> updateTime());
        timer.start();
    }

    private void updateTime() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
        lbltime2.setText(simpleDateFormat.format(new Date()));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        webcampanel = new javax.swing.JPanel();
        lblimage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblname = new javax.swing.JLabel();
        lblcheckinchechout = new javax.swing.JLabel();
        lbltime2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("MARK ATTENDANCE");

        webcampanel.setBackground(new java.awt.Color(255, 255, 255));
        webcampanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblimage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        lblimage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblimageMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("TIME");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("DATE");

        lblname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblname.setForeground(new java.awt.Color(204, 0, 51));

        lblcheckinchechout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lbltime2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbltime2.setText("TIME");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(webcampanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 803, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblname, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbltime2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(101, 101, 101))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addGap(138, 138, 138)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblcheckinchechout, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(223, 223, 223))
                                .addComponent(lblimage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(webcampanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(631, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(lbltime2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblimage, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lblcheckinchechout, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(104, 104, 104))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91))))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblimageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblimageMouseClicked


    }//GEN-LAST:event_lblimageMouseClicked

    public static void main(String args[]) {

        FlatMacDarkLaf.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MarkAttendancenew().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblcheckinchechout;
    private javax.swing.JLabel lblimage;
    private javax.swing.JLabel lblname;
    private javax.swing.JLabel lbltime2;
    private javax.swing.JPanel webcampanel;
    // End of variables declaration//GEN-END:variables

    Map<String, String> resultMap = new HashMap<String, String>();

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                // Handle interruption
                ex.printStackTrace();
            }

            try {
                Result result = null;
                BufferedImage image = null;

                // Check if the webcam is open and capture the image
                if (webcam.isOpen()) {
                    image = webcam.getImage();
                    if (image == null) {
                        continue;  // Skip iteration if no image is captured
                    }
                }

                // Process the image and attempt to decode a QR code
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                try {
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException ex) {
                    // No QR code found, continue with next iteration
                    continue;
                }

                // If QR code is successfully decoded
                if (result != null) {
                    String jsonstring = result.getText();
                    Gson gson = new Gson();
                    java.lang.reflect.Type type = new TypeToken<Map<String, String>>() {
                    }.getType();
                    resultMap = gson.fromJson(jsonstring, type);

                    // Construct the image path
                    String email = resultMap.get("email").replaceAll("[^a-zA-Z0-9]", "_").toLowerCase();
                    String finalpath = BDUtility.getPath("resources/employeeImages/" + email + ".jpg");

                    // Debugging: Print the resolved image path
                    System.out.println("Resolved image path: " + finalpath);

                    // Check if the image file exists
                    File imageFile = new File(finalpath);
                    if (imageFile.exists()) {
                        CircularImageFrame(finalpath);  // Load the image in a circular frame
                    } else {
                        System.out.println("Image file not found at: " + finalpath);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();  // Log any errors
            }
        } while (running);  // Continue while the running condition is true
    }

//    public void run() {
//
//        do {
//            try {
//                Thread.sleep(1000);
//
//            } catch (InterruptedException ex) {
//
//            }
//
//            try {
//
//                Result result = null;
//                BufferedImage image = null;
//                if (webcam.isOpen()) {
//
//                    if ((image = webcam.getImage()) == null) {
//
//                        continue;
//
//                    }
//
//                }
//
//                LuminanceSource source = new BufferedImageLuminanceSource(image);
//                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
//
//                try {
//                    result = new MultiFormatReader().decode(bitmap);
//                } catch (NotFoundException ex) {
//
//                }
//                if (result != null) {
//
//                    String jsonstring = result.getText();
//                    Gson gson = new Gson();
//                    java.lang.reflect.Type type = new TypeToken<Map<String, String>>() {
//                    }.getType();
//
//                    resultMap = gson.fromJson(jsonstring, type);
//
//                    String finalpath = BDUtility.getPath("resources/employeeImages/" + resultMap.get("email") + ".jpg");
//                    CircularImageFrame(finalpath);
//
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//
//        } while (running);
//
//    }
    @Override
    public Thread newThread(Runnable r) {

        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;

    }

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

            System.out.println("Issue with webcam");

        }

    }

    private void stopwebcam() {

        if (webcam != null && webcam.isOpen()) {

            webcam.close();

        }

    }
    private BufferedImage image = null;

    private void CircularImageFrame(String imagepath) {

        try {

            MySqlConnection.createConnection(); // Ensure the connection is created
            Connection connection = MySqlConnection.connection; // Access the static connection variable directly

//            Connection connection = MySqlConnection.getCon();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from employee where email='" + resultMap.get("email") + "';");
            if (!rs.next()) {

                showPopUpForCertainDuration("User is not Registered or Deleted", "Invalid Qr", JOptionPane.ERROR_MESSAGE);
                return;
            }

            image = null;
            File imageFile = new File(imagepath);

            if (imageFile.exists()) {

                try {

                    image = ImageIO.read(new File(imagepath));
                    image = createCircularImage(image);
                    ImageIcon icon = new ImageIcon(image);

                    lblimage.setIcon(icon);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {

                BufferedImage imageee = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = imageee.createGraphics();

                g2d.setColor(Color.BLACK);
                g2d.fillOval(25, 25, 250, 250);

                g2d.setFont(new Font("Serif", Font.BOLD, 250));
                g2d.setColor(Color.WHITE);
                g2d.drawString(String.valueOf(resultMap.get("first_name").charAt(0)).toUpperCase(), 75, 225);
                g2d.dispose();

                ImageIcon imageIcon = new ImageIcon(imageee);
                lblimage.setIcon(imageIcon);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.pack();
                this.setLocationRelativeTo(null);
                this.setVisible(true);

            }

            lblname.setHorizontalAlignment(JLabel.CENTER);
            lblname.setText(resultMap.get("first_name"));
            if (!checkInCheckout()) {

                return;

            }

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

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
        lblname.setText("");
        lblimage.setIcon(null);

    }

    private BufferedImage createCircularImage(BufferedImage image) {

        int diameter = 285;
        BufferedImage resizedImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, diameter, diameter, null);
        g2.dispose();
        BufferedImage circularImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        g2 = circularImage.createGraphics();
        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, diameter, diameter);
        g2.setClip(circle);
        g2.drawImage(resizedImage, 0, 0, null);
        g2.dispose();
        return circularImage;

    }

    private boolean checkInCheckout() throws HeadlessException, SQLException, Exception {

        String popUpHeader = null;
        String popUpMessage = null;
        Color color = null;

        MySqlConnection.createConnection(); // Ensure the connection is created
        Connection connection = MySqlConnection.connection; // Access the static connection variable directly

//        Connection con = connectionprovider.getCon();
        Statement st = connection.createStatement();

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ResultSet rs = st.executeQuery("select * from emp_attendance where date='" + currentDate.format(dateFormatter)
                + "' and employee_id=" + Integer.valueOf(resultMap.get("id")) + ";");

//        Connection connection = MySqlConnection.connection();
        if (rs.next()) {

            String checkoutdateTime = rs.getString(4);
            if (checkoutdateTime != null) {

                popUpMessage = "Already Checkout For the Day";
                popUpHeader = "Invalid Chechout";
                showPopUpForCertainDuration(popUpMessage, popUpHeader, JOptionPane.ERROR_MESSAGE);
                return false;

            }

            String checkIndatetime = rs.getString(3);
            LocalDateTime checkInLocalDateTime = LocalDateTime.parse(checkIndatetime, dateTimeFormatter);
            Duration duration = Duration.between(checkInLocalDateTime, currentDateTime);

            long hours = duration.toHours();
            long minutes = duration.minusHours(hours).toMinutes();
            long seconds = duration.minusHours(hours).minusMinutes(minutes).getSeconds();

            if (!(hours > 0 || (hours == 0 && minutes >= 1))) {

                long remainingMinutes = 1 - minutes;
                long remaininSeconds = 60 - seconds;

                popUpMessage = String.format("Your work duration is less than 5 minutes\nYou can check out after: %d minutes and %d seconds", remainingMinutes, remaininSeconds);

                popUpHeader = "Duration Warning";

                showPopUpForCertainDuration(popUpMessage, popUpHeader, JOptionPane.WARNING_MESSAGE);
                return false;
            }

            String updateQuery = "update emp_attendance set checkout=?,workduration=? where date=? and employee_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, currentDateTime.format(dateTimeFormatter));
            preparedStatement.setString(2, "" + hours + "Hours and" + minutes + "minutes");
            preparedStatement.setString(3, currentDate.format(dateFormatter));
            preparedStatement.setString(4, resultMap.get("id"));

            preparedStatement.executeUpdate();
            popUpHeader = "CheckOut";
            popUpMessage = "Cheched Out at" + currentDateTime.format(dateTimeFormatter) + "\nWork Duration " + hours + " Hours and " + minutes + "Minutes";
            color = color.RED;

        } else {
//        ChekIn

            String insertQuery = "INSERT INTO emp_attendance (employee_id,date,checkin) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, resultMap.get("id"));
            preparedStatement.setString(2, currentDate.format(dateFormatter));
            preparedStatement.setString(3, currentDateTime.format(dateTimeFormatter));
            preparedStatement.executeUpdate();
            popUpHeader = "CheckIn";
            popUpMessage = "Check In at " + currentDateTime.format(dateTimeFormatter);
            color = color.GREEN;
        }

        lblcheckinchechout.setHorizontalAlignment(JLabel.CENTER);
        lblcheckinchechout.setText(popUpHeader);
        lblcheckinchechout.setForeground(color);
        lblcheckinchechout.setBackground(color.DARK_GRAY);
        lblcheckinchechout.setOpaque(true);
        showPopUpForCertainDuration(popUpMessage, popUpHeader, JOptionPane.INFORMATION_MESSAGE);
        return Boolean.TRUE;
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        if (image != null) {

            g.drawImage(image, 0, 0, null);

        }

    }

}
