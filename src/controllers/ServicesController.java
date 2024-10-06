/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.ServicesModel;
import includes.MySqlConnection;

/**
 *
 * @author kavindu
 */
public class ServicesController {

    private final String tableName = "services";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(ServicesModel servicesModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`service_name`, `vehicle_type_id`, `charge`) VALUES "
                + "('" + servicesModel.getServiceName() + "', '" + servicesModel.getVehicleTypeId() + "', '" + servicesModel.getCharge() + "') ");
    }
}
