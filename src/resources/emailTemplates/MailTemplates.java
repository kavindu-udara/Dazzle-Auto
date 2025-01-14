/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.emailTemplates;

/**
 *
 * @author kavindu
 */
public class MailTemplates {

    StringBuilder mailerBody = new StringBuilder();

    String mainColor = "#f19108";

    public MailTemplates() {
        //
    }

    /**
     * Generates an HTML email body for an appointment notification.
     *
     * @param name the name of the recipient
     * @param appointmentNumber the appointment number
     * @param vehicleNumber the vehicle's number
     * @param vehicleType the type of the vehicle
     * @param appointmentDate the date of the appointment
     * @param service the service scheduled
     * @param additionalNote any additional notes for the appointment
     * @return a String containing the HTML content for the appointment email
     */
    public String appointmentScheduledMail(String name, String appointmentNumber, String vehicleNumber, String vehicleModel, String vehicleType, String appointmentDate, String timeSlot, String service, String additionalNote) {
        StringBuilder mailerBody = new StringBuilder();
        mailerBody.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        mailerBody.append("<html lang='en'>");
        mailerBody.append("<head>");
        mailerBody.append("<meta charset='UTF-8'>");
        mailerBody.append("<title>Appointment Scheduled</title>");
        mailerBody.append("</head>");
        mailerBody.append("<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0;'>");

        mailerBody.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");

        mailerBody.append("<tr><td style='padding: 20px; text-align: center;'>");
        mailerBody.append("<table width='600' border='0' cellspacing='0' cellpadding='0' style='background-color: #ffffff; border: 1px solid #dddddd; margin: 0 auto;'>");

        mailerBody.append("<tr><td style='background-color: #ffffff; color: #ffffff; padding: 20px; text-align: center;'>");
        mailerBody.append("<img src=\"https://raw.githubusercontent.com/kavindu-udara/dazzle-auto-images/refs/heads/main/dazzle_auto0.png\" width=\"60%\" alt=\"brand-image\">");
        mailerBody.append("</td></tr>");
        mailerBody.append("<tr><td style='padding: 20px;'>");
        mailerBody.append("<h2 style='color: #333333;'>A new Appointment has been Scheduled</h2>");

        // User name
        mailerBody.append("<p style='color: #666666;'>Hi " + name + ", This is your Appointment number:</p>");
        // Appointment number
        mailerBody.append("<p style='text-align: center;'><strong style='color: #ffffff; background-color: #4CAF50; padding: 10px; border-radius: 5px;'>" + appointmentNumber + "</strong></p>");
        mailerBody.append("</td></tr>");

        mailerBody.append("<tr><td style='padding: 10px;'><table width='100%' border='0' cellspacing='0' cellpadding='0'>");

        // Vehicle number
        mailerBody.append("<tr><td width='50%' style='padding: 10px; border-bottom: 1px solid #dddddd;'>Vehicle Number</td>");
        mailerBody.append("<td width='50%' style='padding: 10px; font-weight: bold; border-bottom: 1px solid #dddddd;'>" + vehicleNumber + "</td></tr>");

        mailerBody.append("<tr><td width='50%' style='padding: 10px; border-bottom: 1px solid #dddddd;'>Vehicle Model</td>");
        //Vehicle Model
        mailerBody.append("<td width='50%' style='padding: 10px; font-weight: bold; border-bottom: 1px solid #dddddd;'>" + vehicleModel + "</td></tr>");

        mailerBody.append("<tr><td width='50%' style='padding: 10px; border-bottom: 1px solid #dddddd;'>Vehicle Type</td>");
//        vehicle Type
        mailerBody.append("<td width='50%' style='padding: 10px; font-weight: bold; border-bottom: 1px solid #dddddd;'>" + vehicleType + "</td></tr>");

        mailerBody.append("<tr><td width='50%' style='padding: 10px; border-bottom: 1px solid #dddddd;'>Appoinment Date</td>");
//        Appointment Date
        mailerBody.append("<td width='50%' style='padding: 10px; font-weight: bold; border-bottom: 1px solid #dddddd;'>" + appointmentDate + "</td></tr>");

        mailerBody.append("<tr><td width='50%' style='padding: 10px; border-bottom: 1px solid #dddddd;'>Time Slot</td>");
//        Appointment Date
        mailerBody.append("<td width='50%' style='padding: 10px; font-weight: bold; border-bottom: 1px solid #dddddd;'>" + timeSlot + "</td></tr>");

        mailerBody.append("<tr><td width='50%' style='padding: 10px; border-bottom: 1px solid #dddddd;'>Service</td>");
//        Service
        mailerBody.append("<td width='50%' style='padding: 10px; font-weight: bold; border-bottom: 1px solid #dddddd;'>" + service + "</td></tr>");

        mailerBody.append("<tr><td width='50%' style='padding: 10px; border-bottom: 1px solid #dddddd;'>Additional Note</td>");
//        Additional Note
        mailerBody.append("<td width='50%' style='padding: 10px; font-weight: bold; border-bottom: 1px solid #dddddd;'>" + additionalNote + "</td></tr>");
        mailerBody.append("</table></td></tr>");

        mailerBody.append("<tr><td style='padding: 10px; text-align: center; background-color: #f4f4f4;'>");
        mailerBody.append("<p style='color: #aaaaaa; font-size: 12px;'>&copy; 2024 Dazzle Auto. All rights reserved.</p>");
        mailerBody.append("</td></tr>");
        mailerBody.append("</table></td></tr>");
        mailerBody.append("</table></body></html>");
        return mailerBody.toString();
    }

    /**
     * Generates an HTML email body for an invoice notification.
     *
     * @param name the name of the recipient
     * @return a String containing the HTML content for the invoice email
     */
    public String invoiceMailTemplate(String name) {
        StringBuilder mailerBody = new StringBuilder();
        mailerBody.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        mailerBody.append("<html lang='en'>");
        mailerBody.append("<head>");
        mailerBody.append("<meta charset='UTF-8'>");
        mailerBody.append("<title>Your Invoice</title>");
        mailerBody.append("</head>");
        mailerBody.append("<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0;'>");

        mailerBody.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");

        mailerBody.append("<tr><td style='padding: 20px; text-align: center;'>");
        mailerBody.append("<table width='600' border='0' cellspacing='0' cellpadding='0' style='background-color: #ffffff; border: 1px solid #dddddd; margin: 0 auto;'>");

        mailerBody.append("<tr><td style='background-color: #ffffff; color: #ffffff; padding: 20px; text-align: center;'>");
        mailerBody.append("<img src=\"https://raw.githubusercontent.com/kavindu-udara/dazzle-auto-images/refs/heads/main/dazzle_auto0.png\" width=\"60%\" alt=\"brand-image\">");
        mailerBody.append("</td></tr>");

        mailerBody.append("<tr><td style='padding: 20px;'>");
        mailerBody.append("<h2 style='color: #333333;'>A new Appointment has been Scheduled</h2>");

        // User name
        mailerBody.append("<p style='color: #666666;'>Hi " + name + ", This is your invoice</p>");

        mailerBody.append("</td></tr>");

        mailerBody.append("<tr><td style='padding: 10px;'><table width='100%' border='0' cellspacing='0' cellpadding='0'>");

        mailerBody.append("<tr><td style='padding: 10px; text-align: center; background-color: #f4f4f4;'>");
        mailerBody.append("<p style='color: #aaaaaa; font-size: 12px;'>&copy; 2024 Dazzle Auto. All rights reserved.</p>");
        mailerBody.append("</td></tr>");
        mailerBody.append("</table></td></tr>");
        mailerBody.append("</table></body></html>");
        return mailerBody.toString();
    }

    public String accessConfirmMailTemplate(String employeeName, String Username, String Password, String accessRole) {
        StringBuilder mailerBody = new StringBuilder();
        mailerBody.append("<!DOCTYPE html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append("    <meta charset=\"UTF-8\">\n")
                .append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
                .append("    <title>New Operator Welcome Email</title>\n")
                .append("    <style>\n")
                .append("        * {\n")
                .append("            margin: 0;\n")
                .append("            padding: 0;\n")
                .append("            box-sizing: border-box;\n")
                .append("        }\n")
                .append("        .container {\n")
                .append("            max-width: 600px;\n")
                .append("            margin: 0 auto;\n")
                .append("            padding: 20px;\n")
                .append("            font-family: Arial, sans-serif;\n")
                .append("            line-height: 1.6;\n")
                .append("        }\n")
                .append("        .header {\n")
                .append("            background-color: rgb(214, 132, 13);\n")
                .append("            color: white;\n")
                .append("            padding: 20px;\n")
                .append("            text-align: center;\n")
                .append("            border-radius: 5px 5px 0 0;\n")
                .append("        }\n")
                .append("        .header-image {\n")
                .append("            width: 100%;\n")
                .append("            max-width: 300px;\n")
                .append("            height: auto;\n")
                .append("            margin-bottom: 5px;\n")
                .append("        }\n")
                .append("        .content {\n")
                .append("            background-color: #ffffff;\n")
                .append("            padding: 30px;\n")
                .append("            border: 1px solid #e0e0e0;\n")
                .append("        }\n")
                .append("        .access-details {\n")
                .append("            background-color: rgb(5, 15, 76);\n")
                .append("            color: white;\n")
                .append("            padding: 20px;\n")
                .append("            margin: 20px 0;\n")
                .append("            border-radius: 5px;\n")
                .append("        }\n")
                .append("        .important-notes {\n")
                .append("            background-color: #f8f8f8;\n")
                .append("            padding: 20px;\n")
                .append("            margin: 20px 0;\n")
                .append("            border-left: 4px solid rgb(214, 132, 13);\n")
                .append("        }\n")
                .append("        .footer {\n")
                .append("            text-align: center;\n")
                .append("            padding: 20px;\n")
                .append("            color: rgb(5, 15, 76);\n")
                .append("            font-size: 14px;\n")
                .append("        }\n")
                .append("        @media only screen and (max-width: 480px) {\n")
                .append("            .container {\n")
                .append("                padding: 10px;\n")
                .append("            }\n")
                .append("            .content {\n")
                .append("                padding: 15px;\n")
                .append("            }\n")
                .append("            .header-image {\n")
                .append("                max-width: 200px;\n")
                .append("            }\n")
                .append("        }\n")
                .append("    </style>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("    <div class=\"container\">\n")
                .append("        <div class=\"header\">\n")
                .append("            <img src=\"https://github.com/kavindu-udara/dazzle-auto-images/blob/main/dazzle_auto0.png?raw=true\" alt=\"[Service Station Name]\" class=\"header-image\">\n")
                .append("            <h1>Dazzle Auto</h1>\n")
                .append("            <p>Software Access</p>\n")
                .append("        </div>\n")
                .append("        <div class=\"content\">\n")
                .append("            <p>Dear " + employeeName + ",</p>\n")
                .append("            <br>\n")
                .append("            <p>I hope this email finds you well. I'm pleased to inform you that you have been appointed as a new operator for our Vehicle Service Station Management Software.</p>\n")
                .append("            <div class=\"access-details\">\n")
                .append("                <h2>Access Details:</h2>\n")
                .append("                <p>Username: " + Username + "</p>\n")
                .append("                <p>Password: " + Password + "</p>\n")
                .append("                <p>Access Role: " + accessRole + "</p>\n")
                .append("            </div>\n")
                .append("            <div class=\"important-notes\">\n")
                .append("                <h3>Important Notes:</h3>\n")
                .append("                <p>1. Please change your password upon your first login</p>\n")
                .append("                <p>2. Your account will be activated from [start_date]</p>\n")
                .append("                <p>3. For security reasons, do not share your credentials with anyone</p>\n")
                .append("            </div>\n")
                .append("            <p>If you experience any difficulties logging in or have questions about your access permissions, please contact our IT support team at evogen@gmail.com.</p>\n")
                .append("        </div>\n")
                .append("        <div class=\"footer\">\n")
                .append("            <p>This is an automated message. Please do not reply directly to this email.</p>\n")
                .append("        </div>\n")
                .append("    </div>\n")
                .append("</body>\n")
                .append("</html>");
        return mailerBody.toString();
    }

    public String otpSendMailTemplate(String otp) {
        StringBuilder mailerBody = new StringBuilder();
        mailerBody.append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">")
                .append("<title>OTP Verification Email</title>")
                .append("<style>")
                .append("body {font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #F5F5F5; color: #333;}")
                .append(".container {max-width: 600px; margin: 20px auto; padding: 20px; background: white; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);}")
                .append(".header {text-align: center;}")
                .append(".header img {max-width: 100%; height: auto;}")
                .append(".content {text-align: center; padding: 20px;}")
                .append(".otp-code {font-size: 24px; font-weight: bold; color: rgb(214, 132, 13);}")
                .append(".footer {text-align: center; padding: 20px; background: rgb(5, 15, 76); color: white; border-radius: 0 0 10px 10px;}")
                .append(".button {display: inline-block; padding: 10px 20px; margin-top: 20px; color: white; background-color: rgb(214, 132, 13); text-decoration: none; border-radius: 5px;}")
                .append("@media (max-width: 600px) {.container {padding: 10px;}}")
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append("<div class=\"container\">")
                .append("<div class=\"header\">")
                .append("<img src=\"https://github.com/kavindu-udara/dazzle-auto-images/blob/main/dazzle_auto0.png?raw=true\" alt=\"Dazzle Auto Logo\">")
                .append("</div>")
                .append("<div class=\"content\">")
                .append("<h2>Password Change Request</h2>")
                .append("<p>You have requested to change your password. Use the OTP code below to proceed:</p>")
                .append("<p class=\"otp-code\">"+otp+"</p>")
                .append("<p>If you didn't request this, please ignore this email.</p>")
                .append("</div>")
                .append("<div class=\"footer\">")
                .append("<p>&copy; 2025 Dazzle Auto. All rights reserved.</p>")
                .append("</div>")
                .append("</div>")
                .append("</body>")
                .append("</html>");
        return mailerBody.toString();
    }

}
