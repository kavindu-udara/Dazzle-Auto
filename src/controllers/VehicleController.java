/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.VehicleModel;
import includes.MySqlConnection;

/**
 *
 * @author kavindu
 */
public class VehicleController {

    private final String tableName = "vehicle";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet showDataForChart() throws Exception {
        return MySqlConnection.executeSearch("SELECT `vehicle_type`.`name`, COUNT(`vehicle`.`vehicle_number`) AS numofvehicles "
                + "FROM `vehicle` "
                + "INNER JOIN vehicle_type ON vehicle.vehicle_type_id=vehicle_type.id "
                + "GROUP BY `vehicle_type`.`name`");
    }

    public ResultSet show(String number) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `vehicle_number`='" + number + "'");
    }

    public ResultSet store(VehicleModel vehicleModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`vehicle_number`, `customer_id`, `vehicle_brand_id`, `model`, `vehicle_type_id`, "
                + "`chassis_no`, `engine_no`, `engine_name`, `powertrain_types_id`, `drive_types_id`) VALUES "
                + "('" + vehicleModel.getVehicleNumber() + "', "
                + "'" + vehicleModel.getCustomerId() + "', "
                + "'" + vehicleModel.getVehicleBrandId() + "', "
                + "'" + vehicleModel.getModel() + "', "
                + "'" + vehicleModel.getVehicleTypeId() + "', "
                + " '" + vehicleModel.getChassisNumber() + "', "
                + " '" + vehicleModel.getEngineNumber() + "', "
                + " '" + vehicleModel.getEngineName() + "', "
                + " '" + vehicleModel.getPowertrainTypeId() + "', "
                + "'" + vehicleModel.getDriveTypesId() + "' ) ");
    }

    public ResultSet update(VehicleModel vehicleModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`customer_id`='" + vehicleModel.getCustomerId() + "', "
                + "`vehicle_brand_id`='" + vehicleModel.getVehicleBrandId() + "', "
                + "`model`='" + vehicleModel.getModel() + "', "
                + "`vehicle_type_id`='" + vehicleModel.getVehicleTypeId() + "', "
                + "`chassis_no`='" + vehicleModel.getChassisNumber() + "', "
                + "`engine_no`='" + vehicleModel.getEngineNumber() + "', "
                + "`engine_name`='" + vehicleModel.getEngineName() + "', "
                + "`powertrain_types_id`='" + vehicleModel.getPowertrainTypeId() + "', "
                + "`drive_types_id`='" + vehicleModel.getDriveTypesId() + "' "
                + "WHERE `vehicle_number`='" + vehicleModel.getVehicleNumber() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`customer_id` LIKE '%" + searchText + "%' OR "
                + "`vehicle_brand_id` LIKE '%" + searchText + "%' OR "
                + "`model` LIKE '%" + searchText + "%' OR "
                + "`vehicle_type_id` LIKE '%" + searchText + "%'");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }

}
