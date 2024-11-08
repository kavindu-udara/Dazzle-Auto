/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import includes.MySqlConnection;
import java.sql.ResultSet;
import models.StockModel;

/**
 *
 * @author kavindu
 */
public class StockController {

    private final String tableName = "stock";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(StockModel stockModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`price`, `qty`, `product_id`) VALUES "
                + "('" + stockModel.getPrice() + "', "
                + "'" + stockModel.getQty() + "', "
                + "'" + stockModel.getProductId() + "') ");
    }
    
    public ResultSet update(double qty, int stockid) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`qty`=`qty`-'" + qty + "' "
                + "WHERE `id`='" + stockid + "' ");
    }

    public ResultSet update(StockModel stockModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`price`='" + stockModel.getPrice() + "', "
                + "`qty`='" + stockModel.getQty() + "', "
                + "`product_id`='" + stockModel.getProductId() + "', "
                + "WHERE `id`='" + stockModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`price` LIKE '%" + searchText + "%' OR "
                + "`qty` LIKE '%" + searchText + "%' OR "
                + "`product_id` LIKE '%" + searchText + "%'");
    }
    
    public ResultSet customQuery(String query) throws Exception {
        return MySqlConnection.executeSearch(query);
    }
    
     public ResultSet findByProductIdAndPrice(String productId, double price) throws Exception {
        String query = "SELECT id, qty FROM `" + tableName + "` WHERE `product_id` = '" + productId + "' AND `price` = " + price;
        return MySqlConnection.executeSearch(query);
    }
    
      public void updateQuantity(int stockId, double newQty) throws Exception {
        String updateQuery = "UPDATE `" + tableName + "` SET `qty` = " + newQty + " WHERE `id` = " + stockId;
        MySqlConnection.executeIUD(updateQuery);
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }

}
