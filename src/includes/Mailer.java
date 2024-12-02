/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package includes;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import io.github.cdimascio.dotenv.Dotenv;
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

    public void sendMail(String recipientEmail, String subject, String body) {

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
                message.setText(body); // Email body

                // Send the message
                Transport.send(message);
                logger.info("Email Sended : " + recipientEmail + ", Subject : " + subject + ", Body : " + body);
                JOptionPane.showMessageDialog(null, "Email sent successfully!");
            } catch (MessagingException e) {
                e.printStackTrace();
                logger.info("Email Send Failed : " + recipientEmail + ", Subject : " + subject + ", Body : " + body + ", Error : " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Failed to send email: " + e.getMessage());
            }
        };

    }

}
