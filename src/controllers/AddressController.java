/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.AddressModel;
import includes.MySqlConnection;

/**
 *
 * @author kavindu
 */
public class AddressController {

    private final String tableName = "address";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(AddressModel addressModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`employee_id`, `supplier_id`, `lane1`, `lane2`, `city_id`, `postalcode`) VALUES "
                + "('" + addressModel.getEmployeeId() + "', "
                + "'" + addressModel.getSupplierId() + "', "
                + "'" + addressModel.getLane1() + "', "
                + "'" + addressModel.getLane2() + "', "
                + "'" + addressModel.getCity() + "', "
                + "'" + addressModel.getPostalCode() + "') ");
    }
    
    public ResultSet storeSupplierAddress(AddressModel addressModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`supplier_id`, `lane1`, `lane2`, `city_id`, `postalcode`) VALUES "
                + "('" + addressModel.getSupplierId() + "', "
                + "'" + addressModel.getLane1() + "', "
                + "'" + addressModel.getLane2() + "', "
                + "'" + addressModel.getCity() + "', "
                + "'" + addressModel.getPostalCode() + "') ");
    }

    public ResultSet update(AddressModel addressModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`employee_id`='" + addressModel.getEmployeeId() + "', "
                + "`supplier_id`='" + addressModel.getSupplierId() + "',"
                + "`lane1`='" + addressModel.getLane1() + "', "
                + "`lane2`='" + addressModel.getLane2() + "', "
                + "`city`='" + addressModel.getCity() + "', "
                + "`postalcode`='" + addressModel.getPostalCode() + "'  "
                + "WHERE `id`='" + addressModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`employee_id` LIKE '%" + searchText + "%' OR "
                + "`supplier_id` LIKE '%" + searchText + "%' OR "
                + "`lane1` LIKE '%" + searchText + "%' OR "
                + "`lane2` LIKE '%" + searchText + "%' OR "
                + " `city` LIKE '%" + searchText + "%' OR "
                + "`postalcode` LIKE '%" + searchText + "%' ");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }
}
