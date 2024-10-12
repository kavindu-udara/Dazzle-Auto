/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.IntelliJTheme;
import java.awt.Color;
import java.awt.Toolkit;
import views.shop.dashboard.ShopDashboard;
import views.signIn.SignIn;

/**
 *
 * @author Dinuka
 */
public class LoginChooser extends javax.swing.JFrame {

    private final Color btnDefaultColor = new Color(246, 249, 255);
    private final Color textHoverColor = new Color(255, 115, 0);
    private final Color textDefaultColor = new Color(0, 0, 0);
    private final Color btnSelectedColor = new Color(250, 238, 220);

    public LoginChooser() {
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/icon2.png")));

        roundPanels();
    }

    public void roundPanels() {
        jShopButton.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jServiceStationButton.putClientProperty(FlatClientProperties.STYLE, "arc:20");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jShopButton = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jServiceStationButton = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jShopButton.setBackground(new java.awt.Color(246, 249, 255));
        jShopButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jShopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jShopButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jShopButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jShopButtonMouseExited(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/shop-256.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 28)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DazzleAuto Shop");

        javax.swing.GroupLayout jShopButtonLayout = new javax.swing.GroupLayout(jShopButton);
        jShopButton.setLayout(jShopButtonLayout);
        jShopButtonLayout.setHorizontalGroup(
            jShopButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jShopButtonLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jShopButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jShopButtonLayout.setVerticalGroup(
            jShopButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jShopButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(22, 22, 22))
        );

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Choose Login");

        jServiceStationButton.setBackground(new java.awt.Color(246, 249, 255));
        jServiceStationButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jServiceStationButton.setPreferredSize(new java.awt.Dimension(334, 336));
        jServiceStationButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jServiceStationButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jServiceStationButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jServiceStationButtonMouseExited(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/car-workshop.png"))); // NOI18N
        jLabel4.setText("jLabel2");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 28)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;DazzleAuto <br/> Service Station</html>");

        javax.swing.GroupLayout jServiceStationButtonLayout = new javax.swing.GroupLayout(jServiceStationButton);
        jServiceStationButton.setLayout(jServiceStationButtonLayout);
        jServiceStationButtonLayout.setHorizontalGroup(
            jServiceStationButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jServiceStationButtonLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jServiceStationButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jServiceStationButtonLayout.setVerticalGroup(
            jServiceStationButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jServiceStationButtonLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jShopButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(jServiceStationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jShopButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jServiceStationButton, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jShopButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jShopButtonMouseEntered
        jShopButton.setBackground(btnSelectedColor);
        jLabel3.setForeground(textHoverColor);
    }//GEN-LAST:event_jShopButtonMouseEntered

    private void jServiceStationButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jServiceStationButtonMouseEntered
        jServiceStationButton.setBackground(btnSelectedColor);
        jLabel5.setForeground(textHoverColor);
    }//GEN-LAST:event_jServiceStationButtonMouseEntered

    private void jShopButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jShopButtonMouseExited
        jShopButton.setBackground(btnDefaultColor);
        jLabel3.setForeground(textDefaultColor);
    }//GEN-LAST:event_jShopButtonMouseExited

    private void jServiceStationButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jServiceStationButtonMouseExited
        jServiceStationButton.setBackground(btnDefaultColor);
        jLabel5.setForeground(textDefaultColor);
    }//GEN-LAST:event_jServiceStationButtonMouseExited

    private void jShopButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jShopButtonMouseClicked
       
        new SignIn("Shop").setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jShopButtonMouseClicked

    private void jServiceStationButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jServiceStationButtonMouseClicked
        new SignIn("ServiceStation").setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jServiceStationButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        IntelliJTheme.setup(LoginChooser.class.getResourceAsStream(
                "/resources/themes/arc-theme.theme.json"));

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginChooser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jServiceStationButton;
    private javax.swing.JPanel jShopButton;
    // End of variables declaration//GEN-END:variables
}
