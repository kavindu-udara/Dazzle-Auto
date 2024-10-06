/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.ServiceInvoiceItemsModel;
import includes.MySqlConnection;

/**
 *
 * @author kavindu
 */
public class ServiceInvoiceItemsController {

    private final String tableName = "service_invoice_items";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(ServiceInvoiceItemsModel serviceInvoiceItemsModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`services_id`, `description`) VALUES "
                + "('" + serviceInvoiceItemsModel.getServiceId() + "', '" + serviceInvoiceItemsModel.getDescription() + "') ");
    }
}
