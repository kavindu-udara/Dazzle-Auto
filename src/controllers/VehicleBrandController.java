package controllers;

import java.sql.ResultSet;
import services.MysqlConnection;

public class VehicleBrandController {

    private final String tableName = "vehicle_brand";

    public ResultSet show() throws Exception {
        return MysqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MysqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }
    
}
