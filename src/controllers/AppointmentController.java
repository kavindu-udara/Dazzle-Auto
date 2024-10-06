/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.AppointmentModel;
import includes.MySqlConnection;

/**
 *
 * @author kavindu
 */
public class AppointmentController {

    private final String tableName = "appointment";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(AppointmentModel appointmentModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`vehicle_number`, `services_id`, `date`, `note`) VALUES "
                + "('" + appointmentModel.getVehicleNumber() + "', '" + appointmentModel.getServiceId() + "', '" + appointmentModel.getDate() + "', "
                + "'" + appointmentModel.getNote() + "') ");
    }

}
