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
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `idaddress`='" + id + "'");
    }

    public ResultSet store(AddressModel addressModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`employee_emp_id`, `supplier_sup_id`, `lane1`, `lane2`, `city`, `Postalcode`) VALUES "
                + "('" + addressModel.getEmployeeId() + "', '" + addressModel.getSupplierId() + "', '" + addressModel.getLane1() + "', '" + addressModel.getLane2() + "', "
                + "'" + addressModel.getCity() + "', '" + addressModel.getPostalCode() + "') ");
    }
}
