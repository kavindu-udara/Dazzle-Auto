/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import includes.MySqlConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.GrnItemsModel;

/**
 *
 * @author kavindu
 */
public class GrnItemsController {

    private final String tableName = "grn_items";
    private final String tableName2 = "grn";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(GrnItemsModel grnItemsModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`grn_id`, `qty`, `price`, `stock_id`) VALUES "
                + "('" + grnItemsModel.getGrnId() + "', "
                + "'" + grnItemsModel.getQty() + "', "
                + "'" + grnItemsModel.getPrice() + "', "
                + "'" + grnItemsModel.getStockId() + "') ");
    }

    public ResultSet update(GrnItemsModel grnItemsModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`grn_id`='" + grnItemsModel.getGrnId() + "', "
                + "`qty`='" + grnItemsModel.getQty() + "', "
                + "`price`='" + grnItemsModel.getPrice() + "', "
                + "`stock_id`='" + grnItemsModel.getStockId() + "', "
                + "WHERE `id`='" + grnItemsModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`grn_id` LIKE '%" + searchText + "%' OR "
                + "`qty` LIKE '%" + searchText + "%' OR "
                + "`price` LIKE '%" + searchText + "%' OR "
                + "`stock_id` LIKE '%" + searchText + "%'");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }

    public ResultSet getMonthlyTotal(int month, int year) throws SQLException, Exception {
        return MySqlConnection.executeSearch("SELECT SUM(" + tableName + ".price) AS total_income FROM `" + tableName2 + "` "
                + "INNER JOIN `" + tableName + "` ON `" + tableName2 + "`.id = `" + tableName + "`.grn_id "
                + "WHERE MONTH(" + tableName2 + ".date) = `" + month + "` AND YEAR(" + tableName2 + ".date) = `" + year + "` ");
    }

    public ResultSet getYearlyTotal(int year) throws SQLException, Exception {
        return MySqlConnection.executeSearch("SELECT SUM(" + tableName + ".price) AS total_income "
                + "FROM `" + tableName2 + "` "
                + "INNER JOIN `" + tableName + "` ON `" + tableName2 + "`.id = `" + tableName + "`.grn_id "
                + "WHERE YEAR(" + tableName2 + ".date) = `" + year + "`");
    }
}
