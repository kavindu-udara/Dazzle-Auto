/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import includes.MySqlConnection;
import java.sql.ResultSet;
import models.ServiceHistoryModel;

/**
 *
 * @author kavindu
 */
public class ServiceHistoryController {

    private final String tableName = "service_history";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(ServiceHistoryModel serviceHistoryModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`service_invoice_id`) VALUES ('" + serviceHistoryModel.getServiceInvoiceId() + "') ");
    }

    public ResultSet update(ServiceHistoryModel serviceHistoryModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`service_invoice_id`='" + serviceHistoryModel.getServiceInvoiceId() + "', "
                + "WHERE `id`='" + serviceHistoryModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE "
                + "`service_invoice_id` LIKE '%" + searchText + "%' ");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }
}
