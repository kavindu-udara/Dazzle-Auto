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
    public String appointmentScheduledMail(String name, String appointmentNumber, String vehicleNumber, String vehicleModel, String vehicleType, String appointmentDate, String service, String additionalNote) {
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

        mailerBody.append("<tr><td style='background-color: #4CAF50; color: #ffffff; padding: 20px; text-align: center;'>");
        mailerBody.append("<h1>Dazzle Auto</h1></td></tr>");

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

        mailerBody.append("<tr><td width='50%' style='padding: 10px; border-bottom: 1px solid #dddddd;'>Service</td>");
//        Service
        mailerBody.append("<td width='50%' style='padding: 10px; font-weight: bold; border-bottom: 1px solid #dddddd;'>" + service + "</td></tr>");

        mailerBody.append("<tr><td width='50%' style='padding: 10px; border-bottom: 1px solid #dddddd;'>Additional Note</td>");
//        Additional Note
        mailerBody.append("<td width='50%' style='padding: 10px; font-weight: bold; border-bottom: 1px solid #dddddd;'>Additional Note</td></tr>");
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
    public String invoiceMailTemplate(String name){
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

        mailerBody.append("<tr><td style='background-color: #4CAF50; color: #ffffff; padding: 20px; text-align: center;'>");
        mailerBody.append("<h1>Dazzle Auto</h1></td></tr>");

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

}
