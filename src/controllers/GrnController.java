/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import includes.MySqlConnection;
import java.sql.ResultSet;
import models.GrnModel;

/**
 *
 * @author kavindu
 */
public class GrnController {

    private final String tableName = "grn";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(GrnModel grnModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`id`,`date`, `paid_amount`, `supplier_id`,`employee_id`,`balance`) VALUES "
                + "('" + grnModel.getGrnId() + "','" + grnModel.getDate() + "', "
                + "'" + grnModel.getPaidAmount() + "', "
                + "'" + grnModel.getSupplierId() + "','" + grnModel.getEmployeeId() + "','" + grnModel.getBalance() + "') ");
    }

    public ResultSet update(GrnModel grnModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`date`='" + grnModel.getDate() + "', "
                + "`paid_amount`='" + grnModel.getPaidAmount() + "', "
                + "`supplier_id`='" + grnModel.getSupplierId() + "', "
                + "WHERE `id`='" + grnModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`date` LIKE '%" + searchText + "%' OR "
                + "`paid_amount` LIKE '%" + searchText + "%' OR "
                + "`paid_amount` LIKE '%" + searchText + "%' OR "
                + "`supplier_id` LIKE '%" + searchText + "%'");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }
}
