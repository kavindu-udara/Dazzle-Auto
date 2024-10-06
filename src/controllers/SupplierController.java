/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.SupplierModel;
import includes.MySqlConnection;

/**
 *
 * @author kavindu
 */
public class SupplierController {

    private final String tableName = "supplier";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `sup_id`='" + id + "'");
    }

    public ResultSet store(SupplierModel supplierModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`first_name`, `last_name`, `email`, `mobile`, `status_id`) VALUES "
                + "('" + supplierModel.getFirstName() + "', '" + supplierModel.getLastName() + "', '" + supplierModel.getEmail() + "', "
                + "'" + supplierModel.getMobile() + "', '" + supplierModel.getStatusId() + "') ");
    }

}
