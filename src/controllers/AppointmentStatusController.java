/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import includes.MySqlConnection;
import java.sql.ResultSet;
import models.AppointmentStatusModel;

/**
 *
 * @author kavindu
 */
public class AppointmentStatusController {

    private final String tableName = "appointment_status";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(AppointmentStatusModel appointmentStatusModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`status`) VALUES "
                + "('" + appointmentStatusModel.getStatus() + "') ");
    }

    public ResultSet update(AppointmentStatusModel appointmentStatusModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`status`='" + appointmentStatusModel.getStatus() + "', "
                + "WHERE `id`='" + appointmentStatusModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`status` LIKE '%" + searchText + "%' ");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }
}
