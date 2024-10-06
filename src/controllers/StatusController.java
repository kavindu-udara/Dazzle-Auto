/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.StatusModel;
import includes.MySqlConnection;

/**
 *
 * @author kavindu
 */
public class StatusController {

    private final String tableName = "status";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(StatusModel statusModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`status`) VALUES "
                + "('" + statusModel.getStatus() + "') ");
    }

}
