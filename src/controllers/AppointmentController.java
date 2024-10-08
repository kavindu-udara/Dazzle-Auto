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
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`vehicle_number`, `services_id`, `date`, `note`, `appointment_status_id`) VALUES "
                + "('" + appointmentModel.getVehicleNumber() + "', "
                + "'" + appointmentModel.getServiceId() + "', "
                + "'" + appointmentModel.getDate() + "', "
                + "'" + appointmentModel.getNote() + "',"
                + "'" + appointmentModel.getAppointmentStatusId() + "') ");
    }

    public ResultSet update(AppointmentModel appointmentModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`vehicle_number`='" + appointmentModel.getVehicleNumber() + "', "
                + "`services_id`='" + appointmentModel.getServiceId() + "',"
                + "`date`='" + appointmentModel.getDate() + "', "
                + "`note`='" + appointmentModel.getNote() + "', "
                + "`appointment_status_id`='" + appointmentModel.getAppointmentStatusId() + "', "
                + "WHERE `id`='" + appointmentModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`vehicle_number` LIKE '%" + searchText + "%' OR "
                + "`services_id` LIKE '%" + searchText + "%' OR "
                + "`date` LIKE '%" + searchText + "%' OR "
                + "`note` LIKE '%" + searchText + "%' OR "
                + " `appointment_status_id` LIKE '%" + searchText + "%'");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }

}
