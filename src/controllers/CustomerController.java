/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.CustomerModel;
import includes.MySqlConnection;

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
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`first_name`, `last_name`, `mobile`) VALUES "
                + "('" + customerModel.getFirstName() + "', '" + customerModel.getLastName() + "', '" + customerModel.getMobile() + "') ");
    }
}
