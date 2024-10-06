/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.VehicleBrandModel;
import includes.MySqlConnection;

/**
 *
 * @author kavindu
 */
public class VehicleBrandController {

    private final String tableName = "vehicle_brand";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(VehicleBrandModel vehicleBrandModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`brand_name`) VALUES "
                + "('" + vehicleBrandModel.getName() + "') ");
    }

}
