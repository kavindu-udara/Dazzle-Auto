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
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`name`, `vehicle_type_id`, `charge`) VALUES "
                + "('" + servicesModel.getName() + "', "
                + "'" + servicesModel.getVehicleTypeId() + "', "
                + "'" + servicesModel.getCharge() + "') ");
    }

    public ResultSet update(ServicesModel servicesModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`name`='" + servicesModel.getName() + "', "
                + "`vehicle_type_id`='" + servicesModel.getVehicleTypeId() + "', "
                + "`charge`='" + servicesModel.getCharge() + "' "
                + "WHERE `id`='" + servicesModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`name` LIKE '%" + searchText + "%'");
    }

    public ResultSet search(String searchText, String vehicleTypeId) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`name` LIKE '%" + searchText + "%' AND `vehicle_type_id`='" + vehicleTypeId + "' ");
    }
public ResultSet showSorted() throws Exception {
    return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` ORDER BY `name` ASC");
}

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }

    public ResultSet customQuery(String query) throws Exception {
        return MySqlConnection.executeSearch(query);
    }
}
