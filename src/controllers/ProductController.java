/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import includes.MySqlConnection;
import java.sql.ResultSet;
import models.ProductModel;

/**
 *
 * @author kavindu
 */
public class ProductController {

    private final String tableName = "product";
    private final String tableName2 = "product_brand";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(String id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(ProductModel productModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`name`, `brand_id`) VALUES ("
                + "'" + productModel.getName() + "', "
                + "'" + productModel.getBrandId() + "') ");
    }

    // To Register Products in RegisterItems.java
    public ResultSet store2(ProductModel productModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`id`,`name`, `brand_id`,`description`) VALUES ("
                + "'" + productModel.getItemId() + "',"
                + "'" + productModel.getName() + "', "
                + "'" + productModel.getBrandId() + "','" + productModel.getDescription() + "') ");
    }

    public ResultSet update(ProductModel productModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`name`='" + productModel.getName() + "', "
                + "`brand_id`='" + productModel.getBrandId() + "', "
                + "WHERE `id`='" + productModel.getId() + "' ");
    }

    //Update using ItemId 
    public ResultSet update2(ProductModel productModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`name`='" + productModel.getName() + "', "
                + "`brand_id`='" + productModel.getBrandId() + "',`description`='" + productModel.getDescription()+ "'  "
                + "WHERE `id`='" + productModel.getItemId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`name` LIKE '%" + searchText + "%' OR `brand_id` LIKE '%" + searchText + "%' ");
    }

    //search from product name or product id or description
    public ResultSet searchProductId(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`name` LIKE '%" + searchText + "%' OR `id` LIKE '%" + searchText + "%' OR `description` LIKE '%" + searchText + "%' ");
    }

    public ResultSet searchBrand(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` INNER JOIN `" + tableName2 + "` "
                + "ON `" + tableName + "`.`brand_id` = `" + tableName2 + "`.`id` WHERE `" + tableName2 + "`.`name` LIKE '%" + searchText + "%' ");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }

}
