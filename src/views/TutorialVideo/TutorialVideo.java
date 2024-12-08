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

        javax.swing.GroupLayout videoPanelLayout = new javax.swing.GroupLayout(videoPanel);
        videoPanel.setLayout(videoPanelLayout);
        videoPanelLayout.setHorizontalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 893, Short.MAX_VALUE)
        );
        videoPanelLayout.setVerticalGroup(
            videoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(videoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(videoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
