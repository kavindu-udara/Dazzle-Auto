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

    public ResultSet show(String id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `service_invoice_id`='" + id + "'");
    }

    public ResultSet store(ServiceInvoiceItemsModel serviceInvoiceItemsModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`service_invoice_id`, `services_id`, `description`) VALUES "
                + "('" + serviceInvoiceItemsModel.getServiceInvoiceId() + "', "
                + "'" + serviceInvoiceItemsModel.getServiceId() + "', "
                + "'" + serviceInvoiceItemsModel.getDescription() + "') ");
    }

    public ResultSet update(ServiceInvoiceItemsModel serviceInvoiceItemsModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`service_invoice_id`='" + serviceInvoiceItemsModel.getServiceInvoiceId() + "', "
                + "`services_id`='" + serviceInvoiceItemsModel.getServiceId() + "', "
                + "`description`='" + serviceInvoiceItemsModel.getDescription() + "', "
                + "WHERE `id`='" + serviceInvoiceItemsModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`service_invoice_id` LIKE '%" + searchText + "%' OR "
                + "`services_id` LIKE '%" + searchText + "%' OR "
                + "`description` LIKE '%" + searchText + "%' OR ");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }
}
