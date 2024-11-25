/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.ServiceInvoiceModel;
import includes.MySqlConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author kavindu
 */
public class ServiceInvoiceController {

    private final String tableName = "service_invoice";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(ServiceInvoiceModel serviceInvoiceModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`id`,`vehicle_number`, `date`, `total`, "
                + "`paid_amount`, `balance`, `payment_method_id`, `employee_id`, `odometer`, `next_service`) VALUES ("
                + "'" + serviceInvoiceModel.getId() + "',"
                + "'" + serviceInvoiceModel.getVehicleNumber() + "', "
                + "'" + serviceInvoiceModel.getDate() + "', "
                + "'" + serviceInvoiceModel.getTotal() + "', "
                + "'" + serviceInvoiceModel.getPaidAmount() + "', "
                + "'" + serviceInvoiceModel.getBalance() + "', "
                + "'" + serviceInvoiceModel.getPaymentMethodId() + "', "
                + "'" + serviceInvoiceModel.getEmployeeId() + "',"
                + "'" + serviceInvoiceModel.getOdometer() + "',"
                + "'" + serviceInvoiceModel.getNext_service() + "') ");
    }

    public ResultSet update(ServiceInvoiceModel serviceInvoiceModel) throws Exception {
        return MySqlConnection.executeIUD("UPDATE `" + tableName + "` SET "
                + "`vehicle_number`='" + serviceInvoiceModel.getVehicleNumber() + "', "
                + "`date`='" + serviceInvoiceModel.getDate() + "', "
                + "`total`='" + serviceInvoiceModel.getTotal() + "', "
                + "`paid_amount`='" + serviceInvoiceModel.getPaidAmount() + "', "
                + "`balance`='" + serviceInvoiceModel.getBalance() + "', "
                + "`payment_method_id`='" + serviceInvoiceModel.getPaymentMethodId() + "', "
                + "`employee_id`='" + serviceInvoiceModel.getEmployeeId() + "', "
                + "WHERE `id`='" + serviceInvoiceModel.getId() + "' ");
    }

    public ResultSet search(String searchText) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` "
                + "INNER JOIN payment_method ON service_invoice.payment_method_id=payment_method.id "
                + "INNER JOIN employee ON service_invoice.employee_id=employee.id WHERE "
                + "`vehicle_number` LIKE '%" + searchText + "%' OR `service_invoice`.`id` LIKE '%" + searchText + "%' ");
    }

    public ResultSet delete(int id) throws Exception {
        return MySqlConnection.executeIUD("DELETE FROM `" + tableName + "` WHERE `id`='" + id + "' ");
    }

    public ResultSet getMonthlyTotal(int month, int year) throws SQLException, Exception {
        return MySqlConnection.executeSearch("SELECT SUM(total) AS total_income FROM `" + tableName + "` WHERE MONTH(date) = '" + month + "' AND YEAR(date) = '" + year + "'");
    }

    public ResultSet getYearlyTotal(int year) throws SQLException, Exception {
        return MySqlConnection.executeSearch("SELECT SUM(total) AS total_income FROM `" + tableName + "` WHERE YEAR(date) = '" + year + "'");
    }
}
