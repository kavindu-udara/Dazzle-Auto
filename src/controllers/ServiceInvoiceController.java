/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.ServiceInvoiceModel;
import services.MySqlConnection;

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
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`customer_id`, `vehicle_vehicle_number`, `date`, `total`, "
                + "`paid_amount`, `balance`, `payment_method_id`, `employee_emp_id`) VALUES ('" + serviceInvoiceModel.getCustomerId() + "', "
                + "'" + serviceInvoiceModel.getVehicleNumber() + "', '" + serviceInvoiceModel.getDate() + "', '" + serviceInvoiceModel.getTotal() + "', "
                + "'" + serviceInvoiceModel.getPaidAmount() + "', '" + serviceInvoiceModel.getBalance() + "', '" + serviceInvoiceModel.getPaymentMethodId() + "', "
                + "'" + serviceInvoiceModel.getEmployeeId() + "') ");
    }

}
