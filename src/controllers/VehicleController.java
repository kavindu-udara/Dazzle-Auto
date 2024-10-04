/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.VehicleModel;
import services.MySqlConnection;

/**
 *
 * @author kavindu
 */
public class VehicleController {

    private final String tableName = "status";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `vehicle_number`='" + id + "'");
    }

    public ResultSet store(VehicleModel vehicleModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`vehicle_number`, `customer_id`, `vehicle_brand_id`, `model`, `vehicle_type_id`) VALUES "
                + "('" + vehicleModel.getVehicleNumber() + "', '" + vehicleModel.getCustomerId() + "', '" + vehicleModel.getVehicleBrandId() + "', '" + vehicleModel.getModel() + "', "
                + "'" + vehicleModel.getVehicleTypeId() + "') ");
    }

}
