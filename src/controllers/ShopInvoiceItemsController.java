/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import includes.MySqlConnection;
import java.sql.ResultSet;
import models.ShopInvoiceItemModel;

/**
 *
 * @author Dinuka
 */
public class ShopInvoiceItemsController {

    private final String tableName = "shop_invoice_items";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(ShopInvoiceItemModel shopInvoiceItemModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`stock_id`, `qty`, `description`, `shop_invoice_id`) VALUES "
                + "('" + shopInvoiceItemModel.getStockID() + "', "
                + "'" + shopInvoiceItemModel.getQty() + "', "
                + "'" + shopInvoiceItemModel.getDescription() + "',"
                + "'" + shopInvoiceItemModel.getShopInvoiceId() + "' ) ");
    }

}
