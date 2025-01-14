/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package includes;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.File;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kavindu
 */
public class Mailer {

    private static final Logger logger = LoggerConfig.getLogger();
    private static Dotenv dotenv = Dotenv.load();

    private String host = dotenv.get("MAILER_HOST");
    private int port = Integer.parseInt(dotenv.get("MAILER_PORT"));
    private String username = dotenv.get("MAILER_USERNAME");
    private String password = dotenv.get("MAILER_PASSWORD");

    // Set up properties
    private Properties properties = new Properties();

    public Mailer() {
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);        
    }

    /**
     * Sends an email with given recipient email, subject, body, and attachment
     * path. If the attachment path is null, the email is sent without
     * attachment.
     *
     * @param recipientEmail the email address of the recipient
     * @param subject the subject of the email
     * @param body the body of the email
     * @param attachmentPath the path to the attachment file, or null if no
     * @param isHtml the body is html
     */
    public void sendMail(String recipientEmail, String subject, String body, String attachmentPath, boolean isHtml) {
        if (host != null && username != null && password != null && dotenv.get("MAILER_PORT") != null) {
            // Create a session
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                // Create the message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username)); // From address
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); // To address
                message.setSubject(subject); // Subject

                // Create Message Body
                BodyPart messageBodyPart = new MimeBodyPart();
                if (isHtml) {
                    messageBodyPart.setContent(body, "text/html"); // Set HTML content
                } else {
                    messageBodyPart.setText(body); // Set plain text content
                }

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                if (attachmentPath != null) {
                    MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                    attachmentBodyPart.attachFile(new File(attachmentPath));

                    multipart.addBodyPart(attachmentBodyPart);
                }

                message.setContent(multipart);

                // Send the message
                Transport.send(message);               
                logger.info("Email Sended : " + recipientEmail + ", Subject : " + subject);
                JOptionPane.showMessageDialog(null, "Email sent successfully!");
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("Email Send Failed : " + recipientEmail + ", Subject : " + subject + ", Body : " + body
                        + ", Error : " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Failed to send email: " + e.getMessage());
            }
        }
        ;

    }
      
}
