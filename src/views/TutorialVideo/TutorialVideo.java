/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.TutorialVideo;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Dumindu
 */
public class TutorialVideo extends javax.swing.JFrame {

    /**
     * Creates new form TutorialVideo
     */
    public TutorialVideo() {
        initComponents();
        NativeInterface.open();
        addBrowser();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        videoPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dazzle Auto Tutorial");
        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout videoPanelLayout = new javax.swing.GroupLayout(videoPanel);
        videoPanel.setLayout(videoPanelLayout);
        videoPanelLayout.setHorizontalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );
        videoPanelLayout.setVerticalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );

        getContentPane().add(videoPanel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addBrowser() {
        JPanel browserPanel = new JPanel(new BorderLayout());
        JWebBrowser webBrowser = new JWebBrowser();
        webBrowser.setBarsVisible(false);
        webBrowser.navigate("https://www.youtube.com/watch_popup?v=E2Rj2gQAyPA&ab_channel=LeagueofLegends");

        browserPanel.add(webBrowser, BorderLayout.CENTER);
        videoPanel.setLayout(new BorderLayout());
        videoPanel.add(browserPanel, BorderLayout.CENTER);

        videoPanel.revalidate();
        videoPanel.repaint();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new TutorialVideo().setVisible(true);
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> NativeInterface.close()));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel videoPanel;
    // End of variables declaration//GEN-END:variables
}
