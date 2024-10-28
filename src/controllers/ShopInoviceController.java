/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import includes.MySqlConnection;
import java.sql.ResultSet;
import models.ShopInvoiceModel;

/**
 *
 * @author kavindu
 */
public class ShopInoviceController {

    private final String tableName = "shop_invoice";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(String id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(ShopInvoiceModel shopInvoiceModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`id`, `date`, `total`, `paid_amount`, `balance`, `payment_method_id`, `employee_id`) VALUES "
                + "('" + shopInvoiceModel.getId() + "', "
                + "'" + shopInvoiceModel.getDate() + "', "
                + "'" + shopInvoiceModel.getTotal() + "', "
                + "'" + shopInvoiceModel.getPaidAmount() + "', "
                + "'" + shopInvoiceModel.getBalance() + "', "
                + "'" + shopInvoiceModel.getPaymentMethodId() + "', "
                + "'" + shopInvoiceModel.getEmployeeId() + "') ");
    }

    public ResultSet update(ShopInvoiceModel shopInvoiceModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`date`='" + shopInvoiceModel.getDate() + "', "
                + "`total`='" + shopInvoiceModel.getTotal() + "', "
                + "`paid_amount`='" + shopInvoiceModel.getPaidAmount() + "', "
                + "`balance`='" + shopInvoiceModel.getBalance() + "', "
                + "`payment_method_id`='" + shopInvoiceModel.getPaymentMethodId() + "', "
                + "`employee_id`='" + shopInvoiceModel.getEmployeeId() + "', "
                + "WHERE `id`='" + shopInvoiceModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` "
                + "INNER JOIN payment_method ON shop_invoice.payment_method_id=payment_method.id "
                + "INNER JOIN employee ON shop_invoice.employee_id=employee.id "
                + "WHERE `shop_invoice`.`id`LIKE'%" + searchText + "%'");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }
}
