/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.components.help;

//...............................................................
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import jnafilechooser.api.JnaFileChooser;
import org.apache.pdfbox.Loader;
import raven.toast.Notifications;

public class PDFViewer extends JFrame {

    File sourceFile;
    private JPanel pdfPanel; // Panel to hold all rendered pages
    private JScrollPane scrollPane; // Scroll pane for continuous scrolling
    private PDDocument document; // PDF document
    private float zoomFactor = 1.0f; // Default zoom factor

    public PDFViewer() {
        // Set up JFrame
        setTitle("PDF Viewer - Continuous Scrolling");
        setSize(1000, 800);
        setLocationRelativeTo(null);

        // UI Components
        pdfPanel = new JPanel();
        pdfPanel.setLayout(new BoxLayout(pdfPanel, BoxLayout.Y_AXIS)); // Stack pages vertically
        scrollPane = new JScrollPane(pdfPanel);

        JButton btnZoomIn = new JButton("+");
        JButton btnZoomOut = new JButton("-");
        JButton download = new JButton("Download");
        btnZoomIn.setPreferredSize(new Dimension(50, 40));
        btnZoomOut.setPreferredSize(new Dimension(50, 40));
        download.setPreferredSize(new Dimension(140, 40));
        btnZoomIn.setFont(new Font("Roboto", Font.BOLD, 20));
        btnZoomOut.setFont(new Font("Roboto", Font.BOLD, 20));
        download.setFont(new Font("Roboto", Font.BOLD, 16));

        btnZoomIn.setBackground(new java.awt.Color(83, 83, 89));
        btnZoomIn.setForeground(new java.awt.Color(255, 255, 255));
        btnZoomIn.setBorderPainted(false);
        btnZoomOut.setBackground(new java.awt.Color(83, 83, 89));
        btnZoomOut.setForeground(new java.awt.Color(255, 255, 255));
        btnZoomOut.setBorderPainted(false);
        download.setBackground(new java.awt.Color(87,110,87));
        download.setForeground(new java.awt.Color(255, 255, 255));
        download.setBorderPainted(false);
        download.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/download-30.png"))); // NOI18N

        btnZoomIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnZoomOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        download.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Add action listeners for zoom buttons
        btnZoomIn.addActionListener(e -> {
            zoomFactor += 0.1f; // Increase zoom factor
            renderAllPages();
        });
        btnZoomOut.addActionListener(e -> {
            if (zoomFactor > 0.2f) { // Ensure zoom factor doesn't go too small
                zoomFactor -= 0.1f;
                renderAllPages();
            }
        });

        download.addActionListener(e -> {
            downloadFile(sourceFile);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnZoomOut);
        buttonPanel.add(btnZoomIn);
        buttonPanel.add(download);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        Notifications.getInstance().setJFrame(null);
    }

    public void loadPDF(String filePath) {
        try {
            sourceFile = new File(filePath);
            document = Loader.loadPDF(sourceFile);
            renderAllPages(); // Render all pages for continuous scrolling
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load PDF: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void renderAllPages() {
        try {
            pdfPanel.removeAll(); // Clear existing pages
            PDFRenderer renderer = new PDFRenderer(document);
            int totalPages = document.getNumberOfPages();

            for (int i = 0; i < totalPages; i++) {
                // Render each page with zoom
                BufferedImage image = renderer.renderImageWithDPI(i, 150 * zoomFactor);
                JLabel pageLabel = new JLabel(new ImageIcon(image));
                pageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding between pages

                // Center the page image in the panel
                JPanel centeredPanel = new JPanel(new BorderLayout());
                centeredPanel.add(pageLabel, BorderLayout.CENTER); // Add the page image to the center

                pdfPanel.add(centeredPanel);
            }

            pdfPanel.revalidate();
            pdfPanel.repaint();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to render pages: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//        JnaFileChooser fileChooser = new JnaFileChooser();
//        fileChooser.addFilter("PNG JPG AND JPEG", "png", "jpg", "jpeg");
//        boolean result = fileChooser.showOpenDialog(this);
//
//        if (result) {
//    
//    else {
//            Notifications.getInstance().show(
//                    Notifications.Type.ERROR,
//                    Notifications.Location.TOP_RIGHT,
//                    "File Not Selected !");
//        }
//}
    public void downloadFile(File sourceFile) {
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.setMode(JnaFileChooser.Mode.Directories);
        fileChooser.setTitle("Choose Download Directory");

        boolean result = fileChooser.showOpenDialog(this);

        if (result) {
            // Get selected directory
            File downloadDirectory = fileChooser.getSelectedFile();

            try {
                // Create destination file in chosen directory
                File destinationFile = new File(downloadDirectory, sourceFile.getName());

                // Perform file copy
                copyFileWithOverwritePrompt(sourceFile, destinationFile);

                // Confirm successful download
                JOptionPane.showMessageDialog(null,
                        "File downloaded to: " + destinationFile.getAbsolutePath(),
                        "Download Complete",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ex) {
                // Handle potential file transfer errors
                JOptionPane.showMessageDialog(null,
                        "Download failed: " + ex.getMessage(),
                        "Download Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            Notifications.getInstance().show(
                    Notifications.Type.ERROR,
                    Notifications.Location.TOP_RIGHT,
                    "File Not Selected !");
        }
    }

    private void copyFileWithOverwritePrompt(File source, File destination) throws IOException {
        // Check if file already exists
        if (destination.exists()) {
            int overwriteChoice = JOptionPane.showConfirmDialog(
                    null,
                    "File already exists. Do you want to replace it?",
                    "Confirm Overwrite",
                    JOptionPane.YES_NO_OPTION
            );

            if (overwriteChoice != JOptionPane.YES_OPTION) {
                return; // Cancel download if user chooses not to overwrite
            }
        }

        // Perform file copy
        try (FileInputStream inputStream = new FileInputStream(source); FileOutputStream outputStream = new FileOutputStream(destination)) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        SwingUtilities.invokeLater(() -> new PDFViewer().setVisible(true));
    }
}
