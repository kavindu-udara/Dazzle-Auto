package controllers;

import java.sql.ResultSet;
import models.VehicleBrandModel;
import services.MysqlConnection;

public class VehicleBrandController {

    private final String tableName = "vehicle_brand";

    public ResultSet show() throws Exception {
        return MysqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MysqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }

    public ResultSet store(VehicleBrandModel vehicleBrandModel) throws Exception {
        return MysqlConnection.executeIUD("INSERT INTO (`brand_name`) VALUES ('" + vehicleBrandModel.getName() + "') ");
    }
}
