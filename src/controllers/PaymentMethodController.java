/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import models.PaymentMethodModel;
import services.MySqlConnection;

/**
 *
 * @author kavindu
 */
public class PaymentMethodController {

    private final String tableName = "payment_method";

    public ResultSet show() throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "`");
    }

    public ResultSet show(int id) throws Exception {
        return MySqlConnection.executeSearch("SELECT * FROM `" + tableName + "` WHERE `id`='" + id + "'");
    }

    public ResultSet store(PaymentMethodModel paymentMethodModel) throws Exception {
        return MySqlConnection.executeIUD("INSERT INTO `" + tableName + "`(`method`) VALUES ('" + paymentMethodModel.getMethod() + "') ");
    }

}
