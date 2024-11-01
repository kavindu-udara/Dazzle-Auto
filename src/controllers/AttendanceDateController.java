/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import includes.MySqlConnection;
import java.sql.ResultSet;
import models.AttendanceDateModel;

/**
 *
 * @author kavindu
 */
public class AttendanceDateController {
    
    private final String tableName = "attendance_date";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet show(String date) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `date`='" + date + "'");
    }

    public ResultSet store(AttendanceDateModel model) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`date`) VALUES ("
                + "'" + model.getDate()+ "') ");
    }
}
