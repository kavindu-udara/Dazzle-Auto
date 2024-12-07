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

    

    public ResultSet update(AddressModel addressModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`employee_id`=null, "
                +"`lane1`='" + addressModel.getLane1() + "', "
                + "`lane2`='" + addressModel.getLane2() + "', "
                + "`city_id`='" + addressModel.getCity() + "' "
                + "WHERE `supplier_id`='" + addressModel.getSupId() + "' ");
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

    public String retrieveAddressId(String supplierId) throws Exception {
        ResultSet resultSet = MySqlConnection.executeSearch(
                "SELECT `supplier_id` FROM `" + tableName + "` WHERE `supplier_id` = '" + supplierId + "'");
        if (resultSet.next()) {
            return resultSet.getString("supplier_id");
        }
        return null; 
    }
    
    public ResultSet create(AddressModel addressModel) throws Exception {
    return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "` (`supplier_id`, `lane1`, `lane2`, `city_id`) VALUES ("
            
            + (addressModel.getSupId() == null ? "" : "'" + addressModel.getSupId() + "'") + ", "
            + (addressModel.getLane1() == null ? "" : "'" + addressModel.getLane1() + "'") + ", "
            + (addressModel.getLane2() == null ? "" : "'" + addressModel.getLane2() + "'") + ", "
            + (addressModel.getCity() == null ? "" : "'" + addressModel.getCity() + "'") + ")");
}

    public ResultSet create2(AddressModel addressModel) throws Exception {
    return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "` (`employee_id`, `lane1`, `lane2`, `city_id`) VALUES ("
            
            + (addressModel.getEmpId()== null ? "" : "'" + addressModel.getEmpId() + "'") + ", "
            + (addressModel.getLane1() == null ? "" : "'" + addressModel.getLane1() + "'") + ", "
            + (addressModel.getLane2() == null ? "" : "'" + addressModel.getLane2() + "'") + ", "
            + (addressModel.getCity() == null ? "" : "'" + addressModel.getCity() + "'") + ")");
}
    
}


