/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.SupplierModel;
import includes.MySqlConnection;
import models.EmployeeModel;

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

        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`id`,`first_name`, `last_name`, `email`, `mobile`, `status_id`) VALUES "
                + "('" + supplierModel.getId() + "', "
                + "'" + supplierModel.getFirstName() + "', "
                + "'" + supplierModel.getLastName() + "', "
                + "'" + supplierModel.getEmail() + "', "
                + "'" + supplierModel.getMobile() + "', "
                + "'" + supplierModel.getStatusId() + "') ");

    }



 public ResultSet update(SupplierModel supplierModel) throws Exception {
    return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
            + "`first_name`='" + supplierModel.getFirstName() + "', "
            + "`last_name`='" + supplierModel.getLastName() + "', "
            + "`email`='" + supplierModel.getEmail() + "', "
            + "`mobile`='" + supplierModel.getMobile() + "', "
            + "`status_id`='" + supplierModel.getStatusId() + "' "
            + "WHERE `id`='" + supplierModel.getId() + "' ");
}


    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`id` LIKE '%" + searchText + "%' OR "
                + "`first_name` LIKE '%" + searchText + "%' OR "
                + "`last_name` LIKE '%" + searchText + "%' OR "
                + "`email` LIKE '%" + searchText + "%' OR "
                + "`mobile` LIKE '%" + searchText + "%' OR "
                + "`status_id` LIKE '%" + searchText + "%'");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }

}
