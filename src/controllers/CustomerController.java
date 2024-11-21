/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.CustomerModel;
import includes.MySqlConnection;
import includes.TimestampsGenerator;

/**
 *
 * @author kavindu
 */
public class CustomerController {

    private final String tableName = "customer";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(CustomerModel customerModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`first_name`, `last_name`, `mobile`, `registered_date`,`email`) VALUES "
               
                 + "('" + customerModel.getFirstName() + "', "
            + "'" + customerModel.getLastName() + "', "
            + "'" + customerModel.getMobile() + "', "
            + "'" + TimestampsGenerator.getFormattedDateTime() + "', "
            + "'" + customerModel.getEmail() + "') ");
                
    }

public ResultSet update(CustomerModel customerModel) throws Exception {
    return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
            + "`first_name`='" + customerModel.getFirstName() + "', "
            + "`last_name`='" + customerModel.getLastName() + "', "
            + "`mobile`='" + customerModel.getMobile() + "', "
            + "`registered_date`='" + customerModel.getRegisteredDate() + "' "
            + "WHERE `id`='" + customerModel.getId() + "' ");
}


    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`first_name` LIKE '%" + searchText + "%' OR "
                + "`last_name` LIKE '%" + searchText + "%' OR "
                + "`mobile` LIKE '%" + searchText + "%' OR "
                + "`registered_date` LIKE '%" + searchText + "%'");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }
}
